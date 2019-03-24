package ca.mcgill.ecse223.block.controller;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse223.block.application.*;
import ca.mcgill.ecse223.block.model.*;
import ca.mcgill.ecse223.block.persistence.*;
import ca.mcgill.ecse223.block.view.Block223PlayModeInterface;
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

        Block223Persistence.save(block223);

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
        UserRole userRole = Block223Application.getCurrentUserRole();
        
        if(minBallSpeedX <= 0 && minBallSpeedY <= 0){
			throw new InvalidInputException ("The minimum speed of the ball must be greater than zero.");
		}
        
        if(nrLevels < 1 || nrLevels > 99) {
        	throw new InvalidInputException ("The number of levels must be between 1 and 99.");
        }
        if(userRole instanceof Player || userRole == null) {
				throw new InvalidInputException("Admin privileges are required to define game settings.");
			}
	        
        if (game != null) {
        	
	        if(game.getAdmin() != userRole) {
	        	throw new InvalidInputException ("Only the admin who created the game can define its game settings.");
	        }
	        
	        
	        Ball ball = game.getBall();
	
	        Paddle paddle = game.getPaddle();
	        
	        try {
	        	for (Level level : game.getLevels()){
	        		if(level.getBlockAssignments().size() > nrBlocksPerLevel) {
	        			throw new InvalidInputException ("The maximum number of blocks per level cannot be less than the number of existing blocks in a level.");
	        		}
	        	}
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
	public static void deleteGame(String name) throws InvalidInputException {
		String error = "";
        Game game;	        
        Block223 block223 = Block223Application.getBlock223();
        UserRole userRole = Block223Application.getCurrentUserRole();
        if (userRole instanceof Player || userRole == null) {	        
            error = "Admin privileges are required to delete a game.";	           
            throw new InvalidInputException(error);	            
        }	        

		game = block223.findGame(name);


		if (game != null) {
			if(!userRole.equals(game.getAdmin())) {
				error = "Only the admin who created the game can delete the game.";
				throw new InvalidInputException(error);
			}
			if(game.isPublished()) {
				error = "A published game cannot be deleted.";
				throw new InvalidInputException(error);
			}
			
			game.delete();
			Block223Persistence.save(block223);
		}

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

		Game game = Block223Application.getBlock223().findGame(name);
		if (game == null) {
			error = "A game with name " + name + " does not exist.";
			throw new InvalidInputException(error);
		}
		
		if(game.isPublished()) {
			error = "A published game cannot be changed.";
			throw new InvalidInputException(error);
		}
		
		if(!userRole.equals(game.getAdmin())) {
			error = "Only the admin who created the game can select the game.";
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

		if(!userRole.equals(game.getAdmin())) {
			error = "Only the admin who created the game can define its game settings.";
			throw new InvalidInputException(error);
		}

		String currentName = game.getName();
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
		//	String AdminLoggedIn = User.

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
	public static void updateBlock(int id, int red, int green, int blue, int points) throws InvalidInputException {
		Game game = Block223Application.getCurrentGame();
		String error = "";
		UserRole userRole = Block223Application.getCurrentUserRole();

		if (game==null) {
			error +="A game must be selected to update a block.";
		}   
		if (game!=null) {

			if (userRole instanceof Player) {
				error += "Admin privileges are required to update a block.";

			}
			if(!userRole.equals(Block223Application.getCurrentGame().getAdmin())) {
				error += "Only the admin who created the game can update a block.";
			}


			if (game.findBlock(id) == null) {
				error += "The block does not exist. ";
				throw new InvalidInputException(error);

			}  
			if(game.findBlock(id) != null)
				if (points < 0 || points > 1000) {
					error += "Points must be between 1 and 1000.";
					throw new InvalidInputException(error);

				}
			if (points > 0 && points < 1000) {
				for (Block block : game.getBlocks()) {
					if (red == block.getRed() && green == block.getGreen() && blue == block.getBlue()) {
						error += "A block with the same color already exists for the game.";
						throw new InvalidInputException(error);
					}
				}
			}		
		}
		if (error.length() > 0)
			throw new InvalidInputException(error.trim());
		try {

			Block block = Block223Application.getCurrentGame().findBlock(id);
			block.setRed(red);
			block.setGreen(green);
			block.setBlue(blue);
			block.setPoints(points);
			//Block223Persistence.save(block223);
		} catch (RuntimeException e) {
			error = e.getMessage();
			throw new InvalidInputException(error);
		}


	}

	//George
	public static void positionBlock(int id, int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {

		Block223 block223 = Block223Application.getBlock223();
		Game game = Block223Application.getCurrentGame();
		String error = "";
		UserRole userRole = Block223Application.getCurrentUserRole();
		Block aBlock;
		Level aLevel;

		if (game==null) {
			error +="A game must be selected to position a block.";
		} 
		if (game!=null) {

			if (userRole instanceof Player) {
				error += "Admin privileges are required to position a block.";

			}
			if(!userRole.equals(Block223Application.getCurrentGame().getAdmin())) {
				error += "Only the admin who created the game can position a block.";
			}
			if (game.findBlock(id) == null) {
				error += "The block does not exist. ";
			}  
			if (level <= 0 || level > Block223Application.getCurrentGame().numberOfLevels()) {
				error += "Level " + level + " does not exist for the game.";
				throw new InvalidInputException(error);
			}
			aLevel = Block223Application.getCurrentGame().getLevel(level - 1);
			if (Block223Application.getCurrentGame().getNrBlocksPerLevel() == aLevel.numberOfBlockAssignments()) {
				error += "The number of blocks has reached the maximum number (" + Block223Application.getCurrentGame().getNrBlocksPerLevel()  + ") allowed for this game.";
				throw new InvalidInputException(error);
			}
			for (BlockAssignment ablockAssignment : Block223Application.getCurrentGame().getLevel(level - 1).getBlockAssignments()) {
				if (ablockAssignment.getGridHorizontalPosition() == gridHorizontalPosition && ablockAssignment.getGridVerticalPosition() == gridVerticalPosition) {
					error = "A block already exists at location " + gridHorizontalPosition + "/" + gridVerticalPosition + ".";
					throw new InvalidInputException(error);
				}
			}
		}
		if (error.length() > 0)
			throw new InvalidInputException(error.trim());

		try {
			aBlock = Block223Application.getCurrentGame().findBlock(id);
			aLevel = Block223Application.getCurrentGame().getLevel(level - 1);
			BlockAssignment blockAssignment = new BlockAssignment(gridHorizontalPosition, gridVerticalPosition, aLevel,
					aBlock, Block223Application.getCurrentGame());
		//	Block223Persistence.save(block223);
		} catch (RuntimeException e) {
			error = e.getMessage();
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
    	
    	String error = "";
    	
       Block223 block223 = Block223Application.getBlock223();
       Block223Persistence.save(block223);
       Game game = Block223Application.getCurrentGame();
  
    	
        if(game == null) {
        	error+="A game must be selected to save it.";
        
        throw new InvalidInputException(error);
        }
        
        UserRole userRole = Block223Application.getCurrentUserRole();
		
            if(userRole instanceof Player || userRole == null){
                error = "Admin privileges are required to save a game.";
                throw new InvalidInputException(error);
            }

         if(game != null) {
      if(game.getAdmin() != Block223Application.getCurrentUserRole()) {
            throw new InvalidInputException ("Only the admin who created the game can save it.");
            }
         }
        
        }
    
	//Mairead
	public static void register(String username, String playerPassword, String adminPassword)
            throws InvalidInputException {
    	Block223 block223 = Block223Application.getBlock223();

        String error = "";
        
    	if (Block223Application.getCurrentUserRole() != null) {
            error += "Cannot register a new user while a user is logged in.";
        	throw new InvalidInputException(error);
        }
       
        
        if((playerPassword == null)||(playerPassword.equals(""))){
            throw new InvalidInputException("The player password needs to be specified.");
            }
  
        
        if(playerPassword.equals(adminPassword)) {
        	error = "The passwords have to be different.";
        	throw new InvalidInputException(error);
        }
       

        if (Block223Application.getCurrentUserRole() != null) {
            error += "Cannot register a new user while a user is logged in.";
        	throw new InvalidInputException(error);
        }
       
        
        if((playerPassword == null)||(playerPassword.equals(""))){
            throw new InvalidInputException("The player password needs to be specified.");}
        
        
       if(username == null||username.equals("")) {
        	error = "The username must be specified.";
        	throw new InvalidInputException(error);
        } 
        
        if(playerPassword.equals(adminPassword)) {
        	error = "The passwords have to be different.";
        	throw new InvalidInputException(error);
        }
       
          Player player; 
        try {
          player = new Player(playerPassword, block223);}
        catch (RuntimeException e) {
        	
        	    throw new InvalidInputException("The player password needs to be specified ");
        }
        
        User user;
        try {   	
       		user = new User(username, block223, player);
        	}
            catch (RuntimeException e) {
            	if((e.getMessage()).equals("The username has already been taken")) {
            		
            	    throw new InvalidInputException("The username must be specified.");
            	    
            	}
            	    throw new InvalidInputException("The username has already been taken.");
            	    }
            	    
            	    if ((adminPassword != null) && (adminPassword != "")) {
                        Admin admin = new Admin(adminPassword, block223);
                       user.addRole(admin);
            	  }
            	    
            	    Block223Persistence.save(block223);
            }

	

	//Mairead
	public static void login(String username, String password) throws InvalidInputException {
        String error = "";
        UserRole oldRole = Block223Application.getCurrentUserRole();

        if (oldRole != null) {
            error += "Cannot register while a user is logged in";
        	throw new InvalidInputException(error);
            
        }
        Block223Application.resetBlock223();

        User user = User.getWithUsername(username);
        if (user == null) {
            error += "The username and password do not match.";
        	throw new InvalidInputException(error);
        }
        
        UserRole role = User.findPassword(password, user);
        Block223Application.setCurrentUserRole(role);
        if (role == null) {
            error += "The username and password do not match.";
        	throw new InvalidInputException(error);

        }
    }

	//Mairead
	public static void logout() {
		Block223Application.setCurrentUserRole(null);
		return;
	}
	
	public static void testGame(Block223PlayModeInterface ui) throws InvalidInputException {
		String error = "";
		
		Game game = Block223Application.getCurrentGame();
		if(game==null) {
			error = "A game must be selected to publish it.";
			throw new InvalidInputException(error);
		}
		
		UserRole userRole = Block223Application.getCurrentUserRole();
		if (userRole instanceof Player || userRole == null) {
			error = "Admin privileges are required to test a game.";
			throw new InvalidInputException(error);
		}
		
		if(game.getAdmin() != userRole) {
			error = "Only the admin who created the game can test it.";
        	throw new InvalidInputException(error);
        }
		
		UserRole admin = Block223Application.getCurrentUserRole();
		
		String username = User.findUsername(admin);
		
		Block223 block223 = Block223Application.getBlock223();
		
		PlayedGame pgame = new PlayedGame(username, game, block223);
		pgame.setPlayer(null);
		Block223Application.setCurrentPlayableGame(pgame);
		//startGame(ui);
	}
	
	public static void publishGame() throws InvalidInputException {
		String error = "";
		Game game = Block223Application.getCurrentGame();
		if(game == null) {
			error = "A game must be selected to publish it.";
			throw new InvalidInputException(error);
		}
		
		UserRole userRole = Block223Application.getCurrentUserRole();
		if (userRole instanceof Player || userRole == null) {
			error = "Admin privileges are required to publish a game.";
			throw new InvalidInputException(error);
		}
		
		if(game.getAdmin() != userRole) {
			error = "Only the admin who created the game can publish it.";
        	throw new InvalidInputException(error);
        }
		if(game.getNrBlocksPerLevel()<1) {
			error = "At least one block must be defined for a game to be published.";
			throw new InvalidInputException(error);
		}
		game.setPublished(true);
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

		if (!userRole.equals(game.getAdmin())) {
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
		if (!userRole.equals(game.getAdmin())) {
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
	public static TOBlock getBlockOfCurrentDesignableGame(int id) throws InvalidInputException{
		String error;
		UserRole userRole = Block223Application.getCurrentUserRole();
		if(userRole instanceof Player || userRole == null){
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
		}
		if (!(userRole.equals(game.getAdmin())) || userRole ==null) {
			error="Only the admin who created the game can access its information.";
			throw new InvalidInputException(error);
		}
		if (game.findBlock(id) == null) {
			error = "The block does not exist. ";
			throw new InvalidInputException(error);
		}
		Block block = Block223Application.getCurrentGame().findBlock(id);
		TOBlock toBlock = new TOBlock(id, block.getRed(), block.getGreen(), block.getBlue(), block.getPoints());
		return toBlock;
	}

	public static List<TOGridCell> getBlocksAtLevelOfCurrentDesignableGame(int level) throws InvalidInputException {
		if (!(Block223Application.getCurrentUserRole() instanceof Admin))
			throw new InvalidInputException("Admin privileges are required to access game information. ");

		Game game = Block223Application.getCurrentGame();
		if (game == null)
			throw new InvalidInputException("A game must be selected to access its information. ");

		if (Block223Application.getCurrentGame().getAdmin() != Block223Application.getCurrentUserRole())
			throw new InvalidInputException("Only the admin who created the game can access its information.");

		List<TOGridCell> gridCells = new ArrayList<TOGridCell>();

		int numLevels = game.numberOfLevels();
		if(level > numLevels || level < 1)
			throw new InvalidInputException("Level " + level + " does not exist for the game.");

		Level alevel = game.getLevel(level - 1);
		List<BlockAssignment> assignments = alevel.getBlockAssignments();

		for (BlockAssignment assignment : assignments) {
			TOGridCell to = new TOGridCell(assignment.getGridHorizontalPosition(), assignment.getGridVerticalPosition(),
					assignment.getBlock().getId(), assignment.getBlock().getRed(), assignment.getBlock().getGreen(),
					assignment.getBlock().getBlue(), assignment.getBlock().getPoints());
			gridCells.add(to);
		}
		return gridCells;
	}
	
	//Mairead
	public static TOUserMode getUserMode(){ //put in refresh data class
		UserRole userRole = Block223Application.getCurrentUserRole();
		
		TOUserMode to = new TOUserMode(Mode.None);

		if (userRole == null) {

			to.setMode(Mode.None);

		} else if (userRole instanceof Player) {

			to.setMode(Mode.Play);
		} else if (userRole instanceof Admin) {
			to.setMode(Mode.Design);
		}


		return to
	}

	// play mode
	//TODO returned null to remove the errors
	/*
    public static List<TOPlayableGame> getPlayableGames() throws InvalidInputException {
        return null;
    }

    public static List<TOCurrentlyPlayedGame> getCurrentPlayableGame() throws InvalidInputException {
        return null;
    }
*/
      public static TOHallOfFame getHallOfFame(int start, int end) throws InvalidInputException {
		
    	String errorMessage;
		TOHallOfFame result;
		UserRole currentUserRole = Block223Application.getCurrentUserRole();
		
		try {
			
			if(!(currentUserRole instanceof Player)) {
				errorMessage = "Player privileges are required to access a game's hall of fame.";
				throw new InvalidInputException(errorMessage);
			}
			
			PlayedGame pgame = Block223Application.getCurrentPlayableGame();
			if(pgame.equals(null) || pgame==null ) {
				errorMessage = "A game must be selected to view its hall of fame.";
				throw new InvalidInputException(errorMessage);
			}
			Game game = pgame.getGame();			

			result = new TOHallOfFame(game.getName());
			
			if(start < 1) {
				start=1;
			}
			if(end > game.numberOfHallOfFameEntries()) {
				end=game.numberOfHallOfFameEntries();
			}
			start = start - 1;
			end = end - 1;
			
			for(int x=start; x<end; x++) {
				TOHallOfFameEntry to = new TOHallOfFameEntry(
						x+1,
						game.getHallOfFameEntry(x).getPlayername(),
						game.getHallOfFameEntry(x).getScore(),
						result
						);
				result.addEntry(to);
			}
			
		}
		catch(NullPointerException e) {
			throw new InvalidInputException(e.getMessage());
		}
		return result;
	}
	public static TOHallOfFame getHallOfFameWithMostRecentEntry(int numberOfEntries) throws InvalidInputException {
		String errorMessage;
		TOHallOfFame result;
		UserRole currentUserRole = Block223Application.getCurrentUserRole();
		
		try {
			
			if(!(currentUserRole instanceof Player)) {
				errorMessage = "Player privileges are required to access a game's hall of fame.";
				throw new InvalidInputException(errorMessage);
			}
			
			PlayedGame pgame = Block223Application.getCurrentPlayableGame();
			if(pgame.equals(null) || pgame==null) {
				errorMessage = "A game must be selected to view its hall of fame.";
				throw new InvalidInputException(errorMessage);
			}
			Game game = pgame.getGame();

			result = new TOHallOfFame(game.getName());
			
			HallOfFameEntry mostRecent = game.getMostRecentEntry();
			int indexR = game.indexOfHallOfFameEntry(mostRecent);
			
			int start = indexR - numberOfEntries/2;
			if(start<1) {
				start = 1;
			}
			
			int end = start + numberOfEntries - 1;
			if(end>game.numberOfHallOfFameEntries()) {
				end = game.numberOfHallOfFameEntries();
			}
			
			start = start - 1;
			end = end - 1;
			
			for(int x=start; x<end; x++) {
				TOHallOfFameEntry to = new TOHallOfFameEntry(
						x+1,
						game.getHallOfFameEntry(x).getPlayername(),
						game.getHallOfFameEntry(x).getScore(),
						result
						);
				result.addEntry(to);
			}
			
		}catch(NullPointerException e) {
			throw new InvalidInputException(e.getMessage());
		}
		return result;	}

}

