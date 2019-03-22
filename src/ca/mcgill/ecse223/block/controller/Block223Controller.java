package ca.mcgill.ecse223.block.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse223.block.application.*;
import ca.mcgill.ecse223.block.model.*;
import ca.mcgill.ecse223.block.persistence.*;
import ca.mcgill.ecse223.block.controller.TOUserMode.Mode;

public class Block223Controller implements Serializable {
    private static Game game;

    // ****************************
    // Modifier methods
    // ****************************

    //Yannick
    public static void createGame(String aName) throws InvalidInputException {
        String name = aName;
        String error = "";
        Block223 block223 = Block223Application.getBlock223();

        UserRole admin = Block223Application.getCurrentUserRole();
        if (admin instanceof Player || admin == null) {
            error = "Admin privileges are required to create a game.";
            throw new InvalidInputException(error);
        }

        error = checkGameNameIsUnique(name, block223);
        if (error != null) {
            throw new InvalidInputException(error);
        }

        if (name == null || name == "") {
            error = "The name of a game must be specified.";
            throw new InvalidInputException(error);
        }

        Game game = new Game(name, 1, (Admin)admin, 1, 1, 1, 10, 10, block223);

        Block223Application.setCurrentGame(game);
        block223.addGame(game);
        //Block223Persistence.save(block223);

    }

    public static String checkGameNameIsUnique(String name, Block223 block223) {
        for (Game game : block223.getGames()) {
            if (game.getName().equals(name)) {
                String error = "The name of a game must be unique.";
                return error;
            }
        }
        return null;
    }

    //Yannick
    public static void setGameDetails(int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY, Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {

        Game game = Block223Application.getCurrentGame();
        
        if(nrLevels < 1 || nrLevels > 99) {
        	throw new InvalidInputException ("The number of levels must be between 1 and 99.");
        }

        if (game != null) {
        	
	        if(game.getAdmin() != Block223Application.getCurrentUserRole()) {
	        	throw new InvalidInputException ("Only the admin who created the game can define its game settings.");
	        }
	        
	        Ball ball = game.getBall();
	
	        
	
	        Paddle paddle = game.getPaddle();
	        
	        try {
	        	game.setNrBlocksPerLevel(nrBlocksPerLevel);
	        	
	        	ball.setMinBallSpeedX(minBallSpeedX);
	        	ball.setMinBallSpeedY(minBallSpeedX);
	        	ball.setBallSpeedIncreaseFactor(ballSpeedIncreaseFactor);
	        	
		        paddle.setMaxPaddleLength(maxPaddleLength);
		        paddle.setMinPaddleLength(minPaddleLength);
		        
	        } catch (RuntimeException e) {
	            throw new InvalidInputException(e.getMessage());
	        }
	
	        List<Level> levels = game.getLevels();
	        int size = levels.size();
	
	        while (nrLevels > size) {
	            game.addLevel();
	            size = levels.size();
	        }
	
	        while (nrLevels < size) {
	            Level level = game.getLevel(size - 1);
	            level.delete();
	            size = levels.size();
	        }

    
        } else {
			throw new InvalidInputException ("A game must be selected to define game settings.");
		}
    }

    //done
    //TODO exception
    public static void deleteGame(String name) throws InvalidInputException {
        String error = "";
        Game game;
        Block223 block223 = Block223Application.getBlock223();
        UserRole userRole = Block223Application.getCurrentUserRole();
        if (userRole instanceof Player || userRole == null) {
            error = "Admin privileges are required to delete a game.";
            throw new InvalidInputException(error);
        }

        try {
            game = block223.findGame(name);
        } catch (Exception e) {
            throw new InvalidInputException(e.getMessage());
        }

        if (userRole.getPassword() != Block223Application.getCurrentGame().getAdmin().getPassword()) {
            error = "Only the admin who created the game can delete the game. ";
            throw new InvalidInputException(error);
        }

        if (game != null) {
            game.delete();

        }

        Block223Persistence.save(block223);
    }


    //Anne-Julie
    public static void selectGame(String name) throws InvalidInputException {
        String error = "";

        Block223 block223 = Block223Application.getBlock223();
        UserRole userRole = Block223Application.getCurrentUserRole();
        if (userRole instanceof Player || userRole == null) {
            error = "Admin privileges are required to select a game.";
            throw new InvalidInputException(error);
        }
//TODO this dnt work
        String adminPassword = userRole.getPassword();
        if (userRole.getPassword() != Block223Application.getCurrentGame().getAdmin().getPassword()) {
            error = "Only the admin who created the game can select the game.";
            throw new InvalidInputException(error);
        }

        Game game = Block223Application.getBlock223().findGame(name);

        if (game == null) {
            error = "A game with name " + name + " does not exist.";
            throw new InvalidInputException(error);
        }


        Block223Application.setCurrentGame(game);
    }

    //Anne-Julie
    public static void updateGame(String name, int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
                                  Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {

        String error = "";
        Block223 block223 = Block223Application.getBlock223();

        UserRole userRole = Block223Application.getCurrentUserRole();
        if (userRole instanceof Player || userRole == null) {
            error = "Admin privileges are required to define game settings.";
            throw new InvalidInputException(error);
        }

        Game game = Block223Application.getCurrentGame();
        if (game == null) {
            error = "A game must be selected to define game settings.";
            throw new InvalidInputException(error);
        }

        if (userRole.getPassword() != Block223Application.getCurrentGame().getAdmin().getPassword()) {
            error = "Only admin who created the game can define its settings.";
            throw new InvalidInputException(error);

        }

        String currentName = game.getName();
        if (currentName == null) {
            error = "The name of a game must be specified.";
            throw new InvalidInputException(error);
        }


        if (currentName != name) {
            if (name == null || name.equals("")) {
                error = "The name of a game must be specified.";
                throw new InvalidInputException(error);
            }
            game.setName(name);
        }


        setGameDetails(nrLevels, nrBlocksPerLevel, minBallSpeedX, minBallSpeedY,
                ballSpeedIncreaseFactor, maxPaddleLength, minPaddleLength);

    }

    public static void addBlock(int red, int green, int blue, int points) throws InvalidInputException {
        Game game = Block223Application.getCurrentGame();
        String error = "";

        UserRole userRole = Block223Application.getCurrentUserRole();
        if (game==null) {
            error +="A game must be selected to add a block.";
        }
        else if(!userRole.equals(Block223Application.getCurrentGame().getAdmin())) {
            error += "Only the admin who created the game can add a block.";
        }

        if (userRole instanceof Player) {
            error += "Admin privileges are required to add a block.";
        }

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
            error += "Points must be between 1 and 1000.";
        }
        if (game!=null) {
            for (Block block : game.getBlocks()) {
                if (red == block.getRed() && green == block.getGreen() && blue == block.getBlue()) {
                    error += "A block with the same color already exists for the game.";
                }
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

    }



    public static void deleteBlock(int id) throws InvalidInputException {
        Game game = Block223Application.getCurrentGame();

        String error = "";

        UserRole userRole = Block223Application.getCurrentUserRole();


        if (game==null) {
            error += "A game must be selected to delete a block.";
            throw new InvalidInputException(error);
        }


        if (userRole instanceof Player) {
            error += "Admin privileges are required to delete a block.";
            throw new InvalidInputException(error);
        }




        if (Block223Application.getCurrentGame().findBlock(id) == null) {
            error +="There was an exception while deleting a non-existing block.";

        }
        if(!userRole.equals(Block223Application.getCurrentGame().getAdmin())) {
            error += "Only the admin who created the game can delete a block.";
            throw new InvalidInputException(error);
        }
        else if (Block223Application.getCurrentGame().findBlock(id) != null) {
            try {
                Block223Application.getCurrentGame().findBlock(id).delete();
            } catch (RuntimeException e) {
                if (error.length() > 0)
                    throw new InvalidInputException(error.trim());
            }
        }

    }

    //Done
    public static void updateBlock(int id, int red, int green, int blue, int points) throws InvalidInputException {
        String error = "";
        UserRole userRole = Block223Application.getCurrentUserRole();
        Block223 block223 = Block223Application.getBlock223();

        Block block = Block223Application.getCurrentGame().findBlock(id);
        //error 1.
        if (userRole instanceof Player || userRole == null) {
            error += "Admin privileges are required to position a block.";
            throw new InvalidInputException(error);
        }

        //error2.
        if (Block223Application.getCurrentGame() == null) {
            error += "A game must be selected to position a block. ";
            throw new InvalidInputException(error);
        }

        //error3.
        if (userRole.getPassword() != Block223Application.getCurrentGame().getAdmin().getPassword()) {
            error += "Only admin who created the game can update a block. ";
            throw new InvalidInputException(error);

        }
        //error4.
        if (block == null) {
            error += "The block doesn't exist. ";
            throw new InvalidInputException(error);
        }

        for (Block ablock : Block223Application.getCurrentGame().getBlocks()) {
            if (red == ablock.getRed() && green == ablock.getGreen() && blue == ablock.getBlue()) {
                error += "A block with this the same color already exists. ";
                throw new InvalidInputException(error);
            }
        }
        try {
            block.setRed(red);
            block.setGreen(green);
            block.setBlue(blue);
            block.setPoints(points);
            Block223Persistence.save(block223);
        } catch (RuntimeException e) {
            throw new InvalidInputException(e.getMessage());
        }
    }

    //George
    public static void positionBlock(int id, int level, int gridHorizontalPosition, int gridVerticalPosition)
            throws InvalidInputException {

        Block223 block223 = Block223Application.getBlock223();
        String error = "";
        UserRole userRole = Block223Application.getCurrentUserRole();

        //error 1.
        if (userRole instanceof Player || userRole == null) {
            error = "Admin privileges are required to position a block. ";
            throw new InvalidInputException(error);
        }

        //error2.
        if (Block223Application.getCurrentGame() == null) {
            error += "A game most be selected to position a block. ";
            throw new InvalidInputException(error);
        }

        //error3.
        if (userRole.getPassword() != Block223Application.getCurrentGame().getAdmin().getPassword()) {
            error += "Only admin who created the game can position a block. ";
            throw new InvalidInputException(error);

        }
        //error4.
        if (level <= 0 || level > Block223Application.getCurrentGame().numberOfLevels()) {
            error += "Level " + level + " doesn't exist. ";
            throw new InvalidInputException(error);
        }
        Block aBlock = Block223Application.getCurrentGame().findBlock(id);
        //error5.
        if (aBlock == null) {
            error += "Block doesn't exist. ";
            throw new InvalidInputException(error);
        }
        Level aLevel = Block223Application.getCurrentGame().getLevel(level - 1);

        //error6.
        if (Block223Application.getCurrentGame().getNrBlocksPerLevel() == aLevel.numberOfBlockAssignments()) {
            error += "The number of blocks has reached the maximum number. ";
            throw new InvalidInputException(error);
        }
        //error7.
        for (BlockAssignment ablockAssignment : aLevel.getBlockAssignments()) {
            if (ablockAssignment.getGridHorizontalPosition() == gridHorizontalPosition && ablockAssignment.getGridVerticalPosition() == gridVerticalPosition) {
                error = "A block already exists at location " + gridHorizontalPosition + "/" + gridVerticalPosition;
                throw new InvalidInputException(error);
            }
        }

        try {
            BlockAssignment blockAssignment = new BlockAssignment(gridHorizontalPosition, gridVerticalPosition, aLevel,
                    aBlock, Block223Application.getCurrentGame());
            Block223Persistence.save(block223);
        } catch (RuntimeException e) {
            throw new InvalidInputException(e.getMessage());
        }
    }

    //Mert
    public static void moveBlock(int level, int oldGridHorizontalPosition, int oldGridVerticalPosition,
                                 int newGridHorizontalPosition, int newGridVerticalPosition) throws InvalidInputException {

        String error = "";
        //check if the current user is admin
        if (Block223Application.getCurrentUserRole().toString() != "admin") {
            error += "Admin priveleges are required to move a block";
            throw new InvalidInputException(error);
        }
        // check if the game exists
        Game game = Block223Application.getCurrentGame();
        if (game == null) {
            error += "A game must be selected to move a block";
        }
        //check if the it is the admin of the current game
        if (game.getAdmin().toString() != Block223Application.getCurrentUserRole().toString()) {
            error += "Only the admin who created the game can move the block.";
            throw new InvalidInputException(error);

        }
        //reset error
        error = "";

        // check that the level is within the limits
        Level recent = null;
        if (level <= game.getLevels().size() && level >= game.MIN_NR_LEVELS) {
            recent = game.getLevel(level);
        } else {
            error += "Level" + level + "does not exist";
        }
        //finding blockAssignment
        BlockAssignment position = recent.findBlockAssignment(oldGridHorizontalPosition, oldGridVerticalPosition);
        if (position != null) {
            position.delete();
        } else {
            error += "A block does not exist at location" + oldGridHorizontalPosition + "/" + oldGridVerticalPosition + ".";
        }
        if (recent.findBlockAssignment(newGridHorizontalPosition, newGridVerticalPosition) != null) {
            error += "A block already exists at location" + newGridHorizontalPosition + "/" + newGridVerticalPosition + ".";
        } else if (newGridVerticalPosition > 0 && newGridVerticalPosition < (Game.PLAY_AREA_SIDE + Game.WALL_PADDING) / Block.SIZE) {
            error += "The vertical position must be between 1 and " + (Game.PLAY_AREA_SIDE + Game.WALL_PADDING) / Block.SIZE + ".";
        } else {
            position.setGridVerticalPosition(newGridVerticalPosition);
            position.setGridHorizontalPosition(newGridHorizontalPosition);

        }
    }

    //Mert
    public static void removeBlock(int level, int gridHorizontalPosition, int gridVerticalPosition)
            throws InvalidInputException {

        String error = "";
        //check if the current user is admin
        if (Block223Application.getCurrentUserRole().toString() != "admin") {
            error += "Admin priveleges are required to move a block";
            throw new InvalidInputException(error);
        }

        // checks if game exists
        Game game = Block223Application.getCurrentGame();
        if (game == null) {
            error += "A game must be selected to remove a block.";
        }
        //check if the it is the admin of the current game
        if (game.getAdmin().toString() != Block223Application.getCurrentUserRole().toString()) {
            error += "Only the admin who created the game can remove the block.";
            throw new InvalidInputException(error);
        }

        Level recent;
        recent = game.getLevel(level);
        BlockAssignment position = recent.findBlockAssignment(gridHorizontalPosition, gridVerticalPosition);
        if (position != null) {
            position.delete();
        }
        Block223Persistence.save(Block223Application.getBlock223());
    }

    //Mairead
    public static void saveGame() throws InvalidInputException {
        Block223 block223 = Block223Application.getBlock223();
        Block223Persistence.save(block223);
    }

    //Mairead
    public static void register(String username, String playerPassword, String adminPassword)
            throws InvalidInputException {
        Block223 block223 = Block223Application.resetBlock223();

        String error = "";
        UserRole oldRole = Block223Application.getCurrentUserRole();

        if (oldRole != null) {
            error += "Cannot register while a user is logged in";
        }

        try {
            Player player = new Player(playerPassword, block223);
            User user = new User(username, block223, player);
            if ((adminPassword != null) && (adminPassword != "")) {
                Admin admin = new Admin(adminPassword, block223);
                //UserRole role = new UserRole(adminPassword, block223);
                user.addRole(admin);
            }
            Block223Persistence.save(block223);
        } catch (RuntimeException e) {
            throw new InvalidInputException(e.getMessage());
        }
        Block223Persistence.save(block223);
    }

    //Mairead
    public static void login(String username, String password) throws InvalidInputException {
        String error = "";
        UserRole oldRole = Block223Application.getCurrentUserRole();

        if (oldRole != null) {
            error += "Cannot register while a user is logged in";
        }
        Block223Application.resetBlock223();

        User user = User.getWithUsername(username);
        if (user == null) {
            error += "Username and password do not match";
        }
        ///List<UserRole> roles = user.getRoles();
        UserRole role = User.findPassword(password, user);
        Block223Application.setCurrentUserRole(role);
        if (role == null) {
            error += "player password needs to be specified";
        }
    }

    //Mairead
    public static void logout() {
        Block223Application.setCurrentUserRole(null);
        return;
    }

    // ****************************
    // Query methods
    // ****************************
    //George
    public static List<TOGame> getDesignableGames() throws InvalidInputException {
        String error;
        UserRole userRole = Block223Application.getCurrentUserRole();
        if (userRole instanceof Player || userRole == null) {
            error = "Admin privileges are required to access game information.";
            throw new InvalidInputException(error);
        }
        ArrayList<TOGame> games = new ArrayList<TOGame>();
        for (Game game : Block223Application.getBlock223().getGames()) {
            //NOT sure about the numberOfBlocks() method.
            TOGame toGame = new TOGame(game.getName(), game.numberOfLevels(), game.getNrBlocksPerLevel(),
                    game.getBall().getMinBallSpeedX(), game.getBall().getMinBallSpeedY(), game.getBall().getBallSpeedIncreaseFactor(),
                    game.getPaddle().getMaxPaddleLength(), game.getPaddle().getMinPaddleLength());
            games.add(toGame);
        }
        return games;
    }

    //Ding
    public static TOGame getCurrentDesignableGame() throws InvalidInputException {

        String error;
        UserRole userRole = Block223Application.getCurrentUserRole();
        if (userRole instanceof Player || userRole == null) {
            error = "Admin privileges are required to access game information.";
            throw new InvalidInputException(error);
        }

        Game game = Block223Application.getCurrentGame();

        if (game == null) {
            error = "A game must be selected to access its information.";
            throw new InvalidInputException(error);
        }

        if (userRole.getPassword() != Block223Application.getCurrentGame().getAdmin().getPassword()) {
            error = "Only the admin who created the game can access its information.";
            throw new InvalidInputException(error);
        }

        TOGame toGame = new TOGame(game.getName(), game.getLevels().size(), game.getNrBlocksPerLevel(),
                game.getBall().getMinBallSpeedX(), game.getBall().getMinBallSpeedY(),
                game.getBall().getBallSpeedIncreaseFactor(), game.getPaddle().getMaxPaddleLength(), game.getPaddle().getMinPaddleLength());
        return toGame;
    }

    //George
    public static List<TOBlock> getBlocksOfCurrentDesignableGame() throws InvalidInputException{
        Game game = Block223Application.getCurrentGame();
        UserRole userRole = Block223Application.getCurrentUserRole();
        String error ="";

        if(game==null) {
            error+="A game must be selected to access its information.";
            throw new InvalidInputException(error);

        }
        if(userRole instanceof Player) {
            error+="Admin privileges are required to access game information.";
            throw new InvalidInputException(error);
        }
        if (!(userRole.equals(game.getAdmin())) || userRole ==null) {
            error+="Only the admin who created the game can access its information.";
            throw new InvalidInputException(error);
        }



        ArrayList<TOBlock> blocks = new ArrayList<TOBlock>();
        for (Block block : Block223Application.getCurrentGame().getBlocks()) {
            TOBlock toBlock = new TOBlock(block.getId(), block.getRed(), block.getBlue(), block.getGreen(), block.getPoints());
            blocks.add(toBlock);
        }
        return blocks;
    }



    //George
    public static TOBlock getBlockOfCurrentDesignableGame(int id) {
        String error;
        UserRole userRole = Block223Application.getCurrentUserRole();
        /*if(userRole instanceof Player || userRole == null){
            error = "Admin privileges are required to access game information.";
            throw new InvalidInputException(error);
        }
		Game game = Block223Application.getCurrentGame();
		if(game==null) {
			error = "A game must be selected to access its information.";
			throw new InvalidInputException(error);
		}
		if (userRole.getPassword() != Block223Application.getCurrentGame().getAdmin().getPassword()) {
            error = "Only the admin who created the game can access its information.";
            throw new InvalidInputException(error);
        }*/
        Block block = Block223Application.getCurrentGame().findBlock(id);
        TOBlock toBlock = new TOBlock(id, block.getRed(), block.getGreen(), block.getBlue(), block.getPoints());
        return toBlock;
    }

    //George
    public static List<TOGridCell> getBlocksAtLevelOfCurrentDesignableGame(int level) {
        String error;
        UserRole userRole = Block223Application.getCurrentUserRole();
        /*if(userRole instanceof Player || userRole == null){
            error = "Admin privileges are required to access game information.";
            throw new InvalidInputException(error);
        }
		Game game = Block223Application.getCurrentGame();
		if(game==null) {
			error = "A game must be selected to access its information.";
			throw new InvalidInputException(error);
		}
		if (userRole.getPassword() != Block223Application.getCurrentGame().getAdmin().getPassword()) {
            error = "Only the admin who created the game can access its information.";
            throw new InvalidInputException(error);
		}*/
        ArrayList<TOGridCell> gridCells = new ArrayList<TOGridCell>();
        for (BlockAssignment blockAssignment : Block223Application.getCurrentGame().getLevel(level - 1).getBlockAssignments()) {
            TOGridCell toGridCell = new TOGridCell(blockAssignment.getGridHorizontalPosition(), blockAssignment.getGridVerticalPosition(),
                    blockAssignment.getBlock().getId(), blockAssignment.getBlock().getRed(), blockAssignment.getBlock().getGreen(),
                    blockAssignment.getBlock().getBlue(), blockAssignment.getBlock().getPoints());
            gridCells.add(toGridCell);
        }
        return gridCells;
    }

    //Mairead
    public static TOUserMode getUserMode() { //put in refresh data class
        UserRole userRole = Block223Application.getCurrentUserRole();
        Mode i;
        TOUserMode to = new TOUserMode(Mode.None);

        if (userRole == null) {

            to.setMode(Mode.None);

        } else if (userRole instanceof Player) {

            to.setMode(Mode.Play);
        } else if (userRole instanceof Admin) {
            to.setMode(Mode.Design);
        }


        return to;
    }

    // play mode
//TODO returned null to remove the errors
    public static List<TOPlayableGame> getPlayableGames() throws InvalidInputException {
        return null;
    }

    public static List<TOCurrentlyPlayedGame> getCurrentPlayableGame() throws InvalidInputException {
        return null;
    }

    public static TOHallOfFame getHallOfFame(int start, int end) throws InvalidInputException {
        return null;
    }

    public static TOHallOfFame getHallOfFameWithMostRecentEntry(int numberOfEntries) throws InvalidInputException {
        return null;
    }
}

