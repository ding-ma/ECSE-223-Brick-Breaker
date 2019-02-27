package ca.mcgill.ecse223.block.controller;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse223.block.application.*;
import ca.mcgill.ecse223.block.model.*;
import ca.mcgill.ecse223.block.persistence.*;

public class Block223Controller {
    private static Game game;
    // ****************************
    // Modifier methods
    // ****************************
	
	//Yanick
    public static void createGame(String name) throws InvalidInputException {
    }
    //Yanick
    public static void setGameDetails(int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
                                      Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {
    }
    //done 
    //TODO exception
    public static void deleteGame(String name) throws InvalidInputException {
        Game game = game.getName();
        if (game != null) {
            game.delete();
            try {
                //BtmsPersistence.save(BtmsApplication.getBtms());
                //TODO: Save with persistence
            }
            catch (RuntimeException e) {
                throw new InvalidInputException(e.getMessage());
            }
    }
    
    //Anne-Julie
    public static void selectGame(String name) throws InvalidInputException {
    }
    //Anne-Julie
    public static void updateGame(String name, int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
                                  Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {
    }
    //done
    //TODO exception
    public static void addBlock(int red, int green, int blue, int points) throws InvalidInputException {
        Game game = Block223Application.getCurrentGame();

        Block block = new Block(red, green, blue, points, game);

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
    		   for(Block ablock : Block223Application.getCurrentGame().getBlocks()){
    		   if(red == ablock.getRed()&& green == ablock.getGreen() && blue == ablock.getBlue()){
    			error = "A block with this the same color already exists. ";
    		    break;
    		   }
     		   else {
     		    	block.setBlue(red);
     		    	block.setBlue(green);
     		    	block.setBlue(blue);
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
        Block223Application userRole = Block223Application.getCurrentUserRole();
        if (userRole == null) {
            TOUserMode toUserMode = new TOUserMode(Block223.);
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

