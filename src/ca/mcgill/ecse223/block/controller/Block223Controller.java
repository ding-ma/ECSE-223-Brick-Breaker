package ca.mcgill.ecse223.block.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.UserDataHandler;

import ca.mcgill.ecse223.block.application.*;
import ca.mcgill.ecse223.block.model.*;
import ca.mcgill.ecse223.block.persistence.*;

public class Block223Controller implements Serializable {
    private static Game game;
    private Block223 block223;
    // ****************************
    // Modifier methods
    // ****************************
	
	//Yannick
    public static void createGame(String aName) throws InvalidInputException {
        String name = aName;
        String error;
        Block223 block223 = Block223Application.getBlock223();

        if(name == null){
            error = "The name of the game must be specified";
            throw new InvalidInputException(error);
        }

        error = checkGameNameIsUnique(name, block223);
        if (error != null) {
            throw new InvalidInputException(error);
        }

        
        /*
        UserRole userRole = Block223Application.getCurrentUserRole();
        if(userRole instanceof Player || userRole == null){
            error = "Admin privileges are required to create a game.";
            throw new InvalidInputException(error);
        }
        String adminPassword = userRole.getPassword();
        */
        

        Admin admin = new Admin("adminPassword", block223);

        Game game = new Game(name, 1, admin, 1, 1,
                1, 10, 10, block223);

        Block223Application.setCurrentGame(game);
    }

    public static String checkGameNameIsUnique(String name, Block223 block223) {
        for (Game game : block223.getGames()) {
            if (game.getName().equals(name)) {
                String error = "The name of a game must be unique";
                return error;
            }
        }
        return null;
    }

    //Yannick
    public static void setGameDetails(int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY, Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {

        Game game = Block223Application.getCurrentGame();

        game.setNrBlocksPerLevel(nrBlocksPerLevel);

        Ball ball = game.getBall();

        ball.setMinBallSpeedX(minBallSpeedX);
        ball.setMinBallSpeedY(minBallSpeedX);
        ball.setBallSpeedIncreaseFactor(ballSpeedIncreaseFactor);

        Paddle paddle = game.getPaddle();

        paddle.setMaxPaddleLength(maxPaddleLength);
        paddle.setMinPaddleLength(minPaddleLength);

        List<Level> levels = game.getLevels();
        int size = levels.size();

        while(nrLevels > size){
            game.addLevel();
            size = levels.size();
        }

        while(nrLevels < size){
            Level level = game.getLevel(size-1);
            level.delete();
            size = levels.size();
        }

    }
    //done 
    //TODO exception
    public static void deleteGame(String name) throws InvalidInputException {
        String error = "";
        Game game;
        Block223 block223 = Block223Application.getBlock223();  
        UserRole userRole = Block223Application.getCurrentUserRole();
        if(userRole instanceof Player || userRole == null){
            error = "Admin in privileges are required to create a game.";
            throw new InvalidInputException(error);
        }
        

        //TODO do I need this?
        try {
            game = block223.findGame(name);
        } catch (Exception e) {
            throw new InvalidInputException(e.getMessage());
        }
        
        String adminPassword = userRole.getPassword();
        Admin admin = new Admin(adminPassword, block223);
        if(game.getAdmin() != admin) {
            error = "Only the admin who created the game can delete the game.";
            throw new InvalidInputException(error);
        }

        if (game != null) {
            game.delete();
            
        }
        
        Block223Persistence.save(block223);
        
    }
    
    //Anne-Julie
    public static void selectGame(String name) throws InvalidInputException {
    }
    //Anne-Julie
    public static void updateGame(String name, int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
                                  Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {

        String error = "";
        Block223 block223 = Block223Application.getBlock223();  

        UserRole userRole = Block223Application.getCurrentUserRole();
        if(userRole instanceof Player || userRole == null){
            error = "Admin in privileges are required to create a game.";
            throw new InvalidInputException(error);
        }

        Game game = Block223Application.getCurrentGame();
        if (game == null) {
            error = "A game must be selected to define game settings.";
            throw new InvalidInputException(error);
        }
    
        String adminPassword = userRole.getPassword();
        Admin admin = new Admin(adminPassword, block223);
        
        if(game.getAdmin() != admin) {
            error = "Only the admin who created the game can delete the game.";
            throw new InvalidInputException(error);
        }
        
        String currentName;
        try {
            currentName = game.getName();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        if(currentName != name) {
            try {
                game.setName(name);
            } catch (Exception e) {
                throw new InvalidInputException(e.getMessage());
            }
        }
            

        setGameDetails(nrLevels, nrBlocksPerLevel, minBallSpeedX, minBallSpeedY, 
        ballSpeedIncreaseFactor, maxPaddleLength, minPaddleLength);
        
    }
    //done
    //TODO exception
    public static void addBlock(int red, int green, int blue, int points) throws InvalidInputException {
        Game game = Block223Application.getCurrentGame();
        String error = "";

        if (red < 0 || red > 255) {
            error += "Red must be between 0 and 255.";
        }
        if (green < 0 || green > 255) {
            error += "Green must be between 0 and 255.";
        }
        if (blue < 0 || blue > 255) {
            error += "Blue must be between 0 and 255.";
        }
        if (points < 0 || points > 1000) {
            error += "Points need to be between 1 and 1000";
        }

        for (Block block : Block223Application.getCurrentGame().getBlocks()) {
            if (red == block.getRed() && green == block.getGreen() && blue == block.getBlue()) {
                error += "A block with this the same color already exists.";
            }
        }

        if (error.length() > 0)
            throw new InvalidInputException(error.trim());

        try {
            Block block = new Block(red, green, blue, points, game);
            block.setRed(red);
            block.setGreen(green);
            block.setBlue(blue);
            block.setPoints(points);
            //TODO PERSISTENCE DOESNT WORK
         //   Block223Persistence.save(Block223Application.getBlock223());
            Block223Controller.getBlocksOfCurrentDesignableGame();

        } catch (RuntimeException e) {
            error = e.getMessage();
            throw new InvalidInputException(error);
        }

        block.setRed(red);
        block.setGreen(green);
        block.setBlue(blue);
        block.setPoints(points);

       // Block223Persistence.save(block);
        //TODO add persistence
        //try catch??
    }



    public static void deleteBlock(int id) throws InvalidInputException {
        Block block = Block223Application.getCurrentGame().findBlock(id);
        if (block !=null) {
            block.delete();
            //TODO save in persistence???
            try {
                Block223Persistence.save(Block223Application.getBlock223());
            } catch (RuntimeException e) {
                throw new InvalidInputException(e.getMessage());
            }
        }
    	
    	Game game = Block223Application.getCurrentGame();
            Block block = new Block( red, green,  blue,  points, game);
    }
    //done
    //TODO exception
    //Question about the persistence 
    public static void deleteBlock(int id) throws InvalidInputException {
    Block block = Block223Application.getCurrentGame().findBlock(id);
    if (block!=null ) {
    	block.delete();
    } 
    }
    
    //George
    //TODO exception
    public static void updateBlock(int id, int red, int green, int blue, int points) throws InvalidInputException {
        String error = "";
        Block block = Block223Application.getCurrentGame().findBlock(id);
        if (block == null) {
            error = "A block with this id does not exist. ";
        }
        for (Block ablock : Block223Application.getCurrentGame().getBlocks()) {
            if (red == ablock.getRed() && green == ablock.getGreen() && blue == ablock.getBlue()) {
                error = "A block with this the same color already exists. ";
                break;
            } else {
                block.setBlue(blue);
                block.setGreen(green);
                block.setRed(red);
                block.setPoints(points);
            }

        }
    }
    //George
    //TODO exception.
    public static void positionBlock(int id, int level, int gridHorizontalPosition, int gridVerticalPosition)
            throws InvalidInputException {
    	Block aBlock = Block223Application.getCurrentGame().findBlock(id);
    	Level aLevel =  Block223Application.getCurrentGame().getLevel(level-1);
		BlockAssignment blockAssignment = new BlockAssignment(gridHorizontalPosition, gridVerticalPosition, aLevel,
					aBlock, Block223Application.getCurrentGame());
    	}
   
    //Mert
    public static void moveBlock(int level, int oldGridHorizontalPosition, int oldGridVerticalPosition,
                                 int newGridHorizontalPosition, int newGridVerticalPosition) throws InvalidInputException {
    }
    //Mert
    public static void removeBlock(int level, int gridHorizontalPosition, int gridVerticalPosition)
            throws InvalidInputException {
    }
    //Mairead
    public static void saveGame() throws InvalidInputException {
    }
    //Mairead
    public static void register(String username, String playerPassword, String adminPassword)
            throws InvalidInputException {
    }
    //Mairead
    public static void login(String username, String password) throws InvalidInputException {
    }
    //Mairead
    public static void logout() {
    }

    // ****************************
    // Query methods
    // ****************************
    //George
    public static List<TOGame> getDesignableGames() {
        ArrayList<TOGame> games = new ArrayList<TOGame>();
        for (Game game : Block223Application.getBlock223().getGames()) {
            //NOT sure about the numberOfBlocks() method.
            TOGame toGame = new TOGame(game.getName(), game.numberOfLevels(), game.numberOfBlocks(),
                    game.getBall().getMinBallSpeedX(),game.getBall().getMinBallSpeedY(), game.getBall().getBallSpeedIncreaseFactor(),
                    game.getPaddle().getMaxPaddleLength(), game.getPaddle().getMinPaddleLength());
            games.add(toGame);
        }
        return games;
    }

    //Ding
    public static TOGame getCurrentDesignableGame() {

    	Game game = Block223Application.getCurrentGame();
        TOGame toGame = new TOGame(game.getName(), game.getLevels().size(), game.getNrBlocksPerLevel(),
        		game.getBall().getMinBallSpeedX(), game.getBall().getMinBallSpeedY(),
                game.getBall().getBallSpeedIncreaseFactor(), game.getPaddle().getMaxPaddleLength(),game.getPaddle().getMinPaddleLength());
        return toGame;	
    }
    
    //George
    public static List<TOBlock> getBlocksOfCurrentDesignableGame() {
        ArrayList <TOBlock> blocks= new ArrayList<TOBlock>();
        for (Block block: Block223Application.getCurrentGame().getBlocks()) {
            TOBlock toBlock =  new TOBlock( block.getId(), block.getRed(), block.getBlue(), block.getGreen(), block.getPoints());
            blocks.add(toBlock);
        }
        return blocks;   
    }
    //George
	public static TOBlock getBlockOfCurrentDesignableGame(int id) throws InvalidInputException {
		Block block = Block223Application.getCurrentGame().findBlock(id);
		TOBlock toBlock = new TOBlock(id, block.getRed(), block.getGreen(), block.getBlue(), block.getPoints());
		return toBlock;
		}
    //George
    public List<TOGridCell> getBlocksAtLevelOfCurrentDesignableGame(int level) throws InvalidInputException {
    ArrayList <TOGridCell>  gridCells = new ArrayList<TOGridCell>();
    for (BlockAssignment blockAssignment : Block223Application.getCurrentGame().getLevel(level-1).getBlockAssignments()) {
		TOGridCell toGridCell = new TOGridCell (blockAssignment.getGridHorizontalPosition(), blockAssignment.getGridVerticalPosition(),
				blockAssignment.getBlock().getId(), blockAssignment.getBlock().getRed(), blockAssignment.getBlock().getGreen(),
				blockAssignment.getBlock().getBlue(), blockAssignment.getBlock().getPoints()) ;
		gridCells.add(toGridCell);
    }
    return gridCells;   
    }
    
    //Mairead
    public static TOUserMode getUserMode() {
        UserRole userRole = Block223Application.getCurrentUserRole();
        if (userRole == null) {
            TOUserMode toUserMode = new TOUserMode(Block223);
        }
        if (userRole == player){
            TOUserMode toUserMode = new TOUserMode();
        }
        if (userRole == admin){
            TOUserMode toUserMode = new TOUserMode();
        }

        return userRole;
    }
}