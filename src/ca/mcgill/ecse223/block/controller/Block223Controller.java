package ca.mcgill.ecse223.block.controller;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.TOUserMode.Mode;
import ca.mcgill.ecse223.block.model.*;
import ca.mcgill.ecse223.block.model.PlayedGame.PlayStatus;
import ca.mcgill.ecse223.block.persistence.Block223Persistence;
import ca.mcgill.ecse223.block.view.Block223PlayModeInterface;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
		Game game = new Game(name, 1, (Admin) admin, 1, 1, 1, 10, 10, block223);
		//Block223Application.setCurrentGame(game);
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
		if (minBallSpeedX <= 0 && minBallSpeedY <= 0) {
			throw new InvalidInputException ("The minimum speed of the ball must be greater than zero.");
		}
		if (nrLevels < 1 || nrLevels > 99) {
			throw new InvalidInputException("The number of levels must be between 1 and 99.");
		}
		if (userRole instanceof Player || userRole == null) {
			throw new InvalidInputException("Admin privileges are required to define game settings.");
		}
		if (game != null) {
			if (game.getAdmin() != userRole) {
				throw new InvalidInputException("Only the admin who created the game can define its game settings.");
			}
			Ball ball = game.getBall();
			Paddle paddle = game.getPaddle();
			try {
				for (Level level : game.getLevels()) {
					if (level.getBlockAssignments().size() > nrBlocksPerLevel) {
						throw new InvalidInputException("The maximum number of blocks per level cannot be less than the number of existing blocks in a level.");
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
		if (minBallSpeedX <= 0 || minBallSpeedY <= 0) {
			throw new InvalidInputException ("The minimum speed of the ball must be greater than zero.");
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
		
		if(name==null || name.isEmpty() || name.equals(""))
			throw new InvalidInputException("The name of a game must be specified.");
		
		String currentName = game.getName();
		if (!currentName.equals(name)) {
            for (Game aGame : Block223Application.getBlock223().getGames()) {
                if (aGame.getName().equals(name)) {
                    throw new InvalidInputException("The name of a game must be unique.");
                }
            }
			game.setName(name);
		}
		setGameDetails(nrLevels, nrBlocksPerLevel, minBallSpeedX, minBallSpeedY,
				ballSpeedIncreaseFactor, maxPaddleLength, minPaddleLength);
        Block223Persistence.save(block223);
	}

	public static void addBlock(int red, int green, int blue, int points) throws InvalidInputException {
		Game game = Block223Application.getCurrentGame();
		String error = "";

		UserRole userRole = Block223Application.getCurrentUserRole();
		if (game == null) {
			error += "A game must be selected to add a block.";
		} else if (!userRole.equals(Block223Application.getCurrentGame().getAdmin())) {
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
		if (game != null) {
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
            Block223Persistence.save(Block223Application.getBlock223());
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
		if (game == null) {
			error += "A game must be selected to delete a block.";
			throw new InvalidInputException(error);
		}
		if (userRole instanceof Player) {
			error += "Admin privileges are required to delete a block.";
			throw new InvalidInputException(error);
		}
		if (Block223Application.getCurrentGame().findBlock(id) == null) {
			error += "There was an exception while deleting a non-existing block.";
		}
		if (!userRole.equals(Block223Application.getCurrentGame().getAdmin())) {
			error += "Only the admin who created the game can delete a block.";
			throw new InvalidInputException(error);
		} else if (Block223Application.getCurrentGame().findBlock(id) != null) {
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
            Block223Persistence.save(Block223Application.getBlock223());
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
            Block223Persistence.save(block223);
		} catch (RuntimeException e) {
			error = e.getMessage();
			throw new InvalidInputException(e.getMessage());
		}
	}

	//Mert
	public static void moveBlock(int level, int oldGridHorizontalPosition, int oldGridVerticalPosition,
			int newGridHorizontalPosition, int newGridVerticalPosition) throws InvalidInputException {
				UserRole userole =Block223Application.getCurrentUserRole();
				Game game = Block223Application.getCurrentGame();
				Block223 block223 = Block223Application.getBlock223();
				if (!(userole instanceof Admin)) {
					throw new InvalidInputException("Admin privileges are required to move a block.");
				}
				if (game==null) {
					throw new InvalidInputException("A game must be selected to move a block.");
				}
				if (userole != game.getAdmin()) {
					throw new InvalidInputException("Only the admin who created the game can move a block.");
				}
				if (game.getPublished()){
					throw new InvalidInputException("A published game cannot be edited.");
				}
				Level aLevel;
				try {
					aLevel = game.getLevel(level - 1);
				}
				catch (IndexOutOfBoundsException e) {
					throw new InvalidInputException("Level " + level + " does not exist for the game.");
				}
				List<BlockAssignment> blockAssignments = game.getBlockAssignments();
				for (BlockAssignment blockAssignment: blockAssignments) {
					if (blockAssignment.getGridHorizontalPosition() == newGridHorizontalPosition && blockAssignment.getGridVerticalPosition() == newGridVerticalPosition) {
						throw new InvalidInputException("A block already exists at location " + newGridHorizontalPosition + "/" + newGridVerticalPosition + ".");
					}
				}
				BlockAssignment blockAssignment = aLevel.findBlockAssignment(oldGridHorizontalPosition, oldGridVerticalPosition);
				if (blockAssignment == null) {
					throw new InvalidInputException("A block does not exist at location " + oldGridHorizontalPosition + "/" + oldGridVerticalPosition + ".");
				}
				blockAssignment.setGridHorizontalPosition(newGridHorizontalPosition);
				Block223Persistence.save(block223);
				if((blockAssignment.getGridHorizontalPosition() <=0) || (blockAssignment.getGridHorizontalPosition() >= 16) )
					throw new InvalidInputException("The horizontal position must be between 1 and 15.");	       
				blockAssignment.setGridVerticalPosition(newGridVerticalPosition);
				Block223Persistence.save(block223);
				if(blockAssignment.getGridVerticalPosition() <=0 || blockAssignment.getGridVerticalPosition() > 15 )
					throw new InvalidInputException("The vertical position must be between 1 and 15.");
			}

	//Mert
	public static void removeBlock(int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {
		Game game = Block223Application.getCurrentGame();
		String error = "";
		UserRole userRole = Block223Application.getCurrentUserRole();
		if (userRole instanceof Player) {
			error += "Admin privileges are required to remove a block.";
			throw new InvalidInputException(error);
		}
		if (game == null) {
			error += "A game must be selected to remove a block.";
			throw new InvalidInputException(error);
		} else if (!userRole.equals(Block223Application.getCurrentGame().getAdmin())) {
			error += "Only the admin who created the game can remove a block.";
			throw new InvalidInputException(error);
		}
		Level recent;
		recent = game.getLevel(level - 1);
		BlockAssignment position = recent.findBlockAssignment(gridHorizontalPosition, gridVerticalPosition);
		if (gridHorizontalPosition < 10 && gridVerticalPosition < 10) {
			error += "There was an exception while deleting a non-existing block assignment.";
		}
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
		if (game == null) {
			error += "A game must be selected to save it.";
			throw new InvalidInputException(error);
		}
		UserRole userRole = Block223Application.getCurrentUserRole();
		if (userRole instanceof Player || userRole == null) {
			error = "Admin privileges are required to save a game.";
			throw new InvalidInputException(error);
		}
		if (game != null) {
			if (game.getAdmin() != Block223Application.getCurrentUserRole()) {
				throw new InvalidInputException("Only the admin who created the game can save it.");
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
		if ((playerPassword == null) || (playerPassword.equals(""))) {
			throw new InvalidInputException("The player password needs to be specified.");
		}
		if (playerPassword.equals(adminPassword)) {
			error = "The passwords have to be different.";
			throw new InvalidInputException(error);
		}
		if (Block223Application.getCurrentUserRole() != null) {
			error += "Cannot register a new user while a user is logged in.";
			throw new InvalidInputException(error);
		}
		if ((playerPassword == null) || (playerPassword.equals(""))) {
			throw new InvalidInputException("The player password needs to be specified.");
		}
		if (username == null || username.equals("")) {
			error = "The username must be specified.";
			throw new InvalidInputException(error);
		}
		if (playerPassword.equals(adminPassword)) {
			error = "The passwords have to be different.";
			throw new InvalidInputException(error);
		}
		Player player;
		try {
			player = new Player(playerPassword, block223);
		} catch (RuntimeException e) {

			throw new InvalidInputException("The player password needs to be specified ");
		}
		User user;
		try {
			user = new User(username, block223, player);
		} catch (RuntimeException e) {
			if ((e.getMessage()).equals("The username has already been taken")) {
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
//		Block223Application.setCurrentGame(null);
//		Block223Application.block223 = Block223Persistence.load();
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
			error = "A game must be selected to test it.";
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
		if(!game.hasBlocks()) {
			error = "At least one block must be defined for a game to be published.";
			throw new InvalidInputException(error);
		}
		game.setPublished(true);
        Block223Persistence.save(Block223Application.getBlock223());
	}

	//Ding
	public static void selectPlayableGame(String name, int id) throws InvalidInputException {
		if (!(Block223Application.getCurrentUserRole() instanceof Player)) {
			throw new InvalidInputException("Player privileges are required to play a game.");
		}
		Game game = Block223Application.getBlock223().findGame(name);
		Block223 block223 = Block223Application.getBlock223();
		PlayedGame pgame;

		if (game != null) {
			Player player = (Player) Block223Application.getCurrentUserRole();
			String username = User.findUsername(player);

			PlayedGame result = new PlayedGame(username, game, block223);
			pgame = result;
			pgame.setPlayer(player);
		} else {
			pgame = block223.findPlayableGame(id);
		}
		if ((game == null) && (pgame == null))
			throw new InvalidInputException("The game does not exist.");

		if ((game == null) && (Block223Application.getCurrentUserRole() != pgame.getPlayer()))
			throw new InvalidInputException("Only the player that started a game can continue the game.");

		Block223Application.setCurrentPlayableGame(pgame);
	}


	//Ding
	public static void startGame(Block223PlayModeInterface ui) throws InvalidInputException {
		String error = "";
		UserRole userRole = Block223Application.getCurrentUserRole();
		if (userRole == null) {
			throw new InvalidInputException("Player privileges are required to play a game.");
		}
		if ((Block223Application.getCurrentPlayableGame() == null)) {
			throw new InvalidInputException("A game must be selected to play it.");
		}
		if ((userRole instanceof Admin) && (Block223Application.getCurrentPlayableGame().getPlayer() != null)) {
			throw new InvalidInputException("Player privileges are required to play a game.");
		}
		if ((userRole instanceof Admin) && (Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin())) {//Check for the admin of the function
			throw new InvalidInputException("Only the admin of a game can test the game.");
		}
		if ((userRole instanceof Player) && (Block223Application.getCurrentPlayableGame().getPlayer() == null)) {
			throw new InvalidInputException("Admin privileges are required to test a game.");
		}
		PlayedGame game = Block223Application.getCurrentPlayableGame();
		game.play();
		ui.takeInputs();
		while (game.getPlayStatus() == PlayStatus.Moving) { 
			String userInputs = ui.takeInputs();

			updatePaddlePosition(userInputs);
			game.move();

			if (userInputs.contains(" ")) { 
				game.pause();
			}

			try {
				TimeUnit.MILLISECONDS.sleep(25);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			ui.refresh(); 
		}

		if (game.getPlayStatus() == PlayStatus.GameOver) {
			Block223Application.setCurrentPlayableGame(null);
		} else if (game.getPlayer() != null) {
			Block223 block223 = Block223Application.getBlock223();
			Block223Persistence.save(block223);
		}
	}

	//Ding
	private static void updatePaddlePosition(String userInputs) {


		PlayedGame game = Block223Application.getCurrentPlayableGame();
		String strArray[] = userInputs.split("");

		for (String s : strArray)
		{
			//current paddle x position
			double curPaddleX = Block223Application.getCurrentPlayableGame().getCurrentPaddleX();

			//paddle length for constraint
			double curPaddleLength = Block223Application.getCurrentPlayableGame().getCurrentPaddleLength();

			if (s.equals("l"))				
			{
				if(curPaddleX > 0){
					if(curPaddleX + PlayedGame.PADDLE_MOVE_LEFT > 0) {
						game.setCurrentPaddleX(game.getCurrentPaddleX() + PlayedGame.PADDLE_MOVE_LEFT);
					}else {
						game.setCurrentPaddleX(0);
					}
				}
			}
			else if (s.equals("r"))
			{

				if(curPaddleX < Game.PLAY_AREA_SIDE-curPaddleLength) {
					if(curPaddleX + PlayedGame.PADDLE_MOVE_RIGHT < Game.PLAY_AREA_SIDE-curPaddleLength) {
						game.setCurrentPaddleX(game.getCurrentPaddleX() + PlayedGame.PADDLE_MOVE_RIGHT);
					}else {
						game.setCurrentPaddleX(Game.PLAY_AREA_SIDE-curPaddleLength);
					}
				}
			}
			else if (s.equals(" "))
			{
				break;
			}
		}
	}

	private static void Left(PlayedGame pgame) {
		double left = PlayedGame.PADDLE_MOVE_LEFT;
		double currentPaddleX = pgame.getCurrentPaddleX();
		if (currentPaddleX > 0)
			pgame.setCurrentPaddleX(pgame.getCurrentPaddleX() + left);
	}

	private static void Right(PlayedGame pgame) {
		double right = PlayedGame.PADDLE_MOVE_RIGHT;
		double currentPaddleX = pgame.getCurrentPaddleX();
		double currentPaddleLength = pgame.getCurrentPaddleLength();
		if (Game.PLAY_AREA_SIDE - currentPaddleLength > currentPaddleX)
			pgame.setCurrentPaddleX(pgame.getCurrentPaddleX() + right);
	}

	// ****************************
	// Query methods
	// ****************************
	//George

	public static List<TOGame> getDesignableGames() throws InvalidInputException {
		String error = "";
		UserRole admin = Block223Application.getCurrentUserRole();
		if(!(admin instanceof Admin)) {
			error = "Admin privileges are required to access game information.";
		}
		if(error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		Block223 block223 = Block223Application.getBlock223();

		List<TOGame> result = new ArrayList<TOGame>();

		List<Game> games = block223.getGames();

		for(Game game : games) {
			Admin gameAdmin = game.getAdmin();
			if(gameAdmin.equals(admin) && !game.isPublished()){
				TOGame to = new TOGame(game.getName(), game.getLevels().size(), game.getNrBlocksPerLevel(), game.getBall().getMinBallSpeedX(), 
						game.getBall().getMinBallSpeedY(), game.getBall().getBallSpeedIncreaseFactor(), game.getPaddle().getMaxPaddleLength(), game.getPaddle().getMinPaddleLength());
				result.add(to);
			}
		}
		return result;
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
		return to;
	}

	// play mode
	//Ding
	public static List<TOPlayableGame> getPlayableGames() throws InvalidInputException {
		String error;
		UserRole userRole = Block223Application.getCurrentUserRole();
		if (userRole instanceof Admin) {
			throw new InvalidInputException("Player privileges are required to play a game.");
		}
		Block223 block223 = Block223Application.getBlock223();
		Player player = (Player) Block223Application.getCurrentUserRole();
		List<TOPlayableGame> result = new ArrayList<TOPlayableGame>();
		List<Game> games = block223.getGames();
		for (Game agame : games) {
			if (agame.isPublished()) {
				TOPlayableGame to = new TOPlayableGame(agame.getName(), -1, 0);
				result.add(to);
			}
		}
		List<PlayedGame> played = player.getPlayedGames();
		for (PlayedGame agame : played) {
			TOPlayableGame to = new TOPlayableGame(agame.getGame().getName(), agame.getId(), agame.getCurrentLevel());
			result.add(to);
		}
		return result;

	}

	//Ding
	public static TOCurrentlyPlayedGame getCurrentPlayableGame() throws InvalidInputException {

		UserRole currentUserRole = Block223Application.getCurrentUserRole();
		if (currentUserRole == null) {
			throw new InvalidInputException("Player privileges are required to play a game.");
		}
		PlayedGame pgame = Block223Application.getCurrentPlayableGame();
		if (pgame == null) {
			throw new InvalidInputException("A game must be selected to play it.");
		}
		Player currentPlayer = pgame.getPlayer();
		if (currentUserRole instanceof Admin && currentPlayer != null) {
			throw new InvalidInputException("Player privileges are required to play a game.");
		}
		Admin gameAdmin = pgame.getGame().getAdmin();
		if (currentUserRole instanceof Admin && currentUserRole != gameAdmin) {
			throw new InvalidInputException("Only the admin of a game can test the game.");
		}

		if (currentUserRole instanceof Player && currentPlayer == null) {
			throw new InvalidInputException("Admin privileges are required to test a game.");
		}
		boolean paused = pgame.getPlayStatus() == PlayStatus.Ready || pgame.getPlayStatus() == PlayStatus.Paused;

		TOCurrentlyPlayedGame result = new TOCurrentlyPlayedGame(pgame.getGame().getName(), paused, pgame.getScore(), pgame.getLives(),
				pgame.getCurrentLevel(), pgame.getPlayername(), (int) pgame.getCurrentBallX(), (int) pgame.getCurrentBallY(), (int) pgame.getCurrentPaddleLength(),
				(int) pgame.getCurrentPaddleX());
		List<PlayedBlockAssignment> blocks = pgame.getBlocks();
		for (PlayedBlockAssignment pblocks : blocks) {
			new TOCurrentBlock(pblocks.getBlock().getRed(), pblocks.getBlock().getGreen(), pblocks.getBlock().
					getBlue(), pblocks.getBlock().getPoints(), pblocks.getX(), pblocks.getY(), result);
		}
		return result;

	}
	
	
//Mert - fixed by Mairead
	public static TOHallOfFame getHallOfFame(int start, int end) throws InvalidInputException {
		String error = "";
		UserRole userRole = Block223Application.getCurrentUserRole();
		if(!(userRole instanceof Player)) {
			error = "Player privileges are required to access a game's hall of fame.";
			throw new InvalidInputException(error.trim());
		}
		PlayedGame pgame = Block223Application.getCurrentPlayableGame();
		if(pgame == null) {
			error = "A game must be selected to view its hall of fame.";
			throw new InvalidInputException(error.trim());
		}
		Game game = pgame.getGame();
		TOHallOfFame result = new TOHallOfFame(game.getName());

		if(start < 1) {
			start = 1;
		}
		if(end > game.numberOfHallOfFameEntries()) {
			end = game.numberOfHallOfFameEntries();
		}
		start = game.numberOfHallOfFameEntries() - start;
		end = game.numberOfHallOfFameEntries() - end;

		for(int i = start; i >= end; i--) {
			new TOHallOfFameEntry(i + 1, game.getHallOfFameEntry(i).getPlayername(), game. getHallOfFameEntry(i).getScore(), result);
		}
		return result;
	}

//Mert - Fixed by Mairead
	public static TOHallOfFame getHallOfFameWithMostRecentEntry(int numberOfEntries) throws InvalidInputException {
		String error = "";
		UserRole userRole = Block223Application.getCurrentUserRole();
		if(!(userRole instanceof Player)) {
			error = "Player privileges are required to access a game's hall of fame.";
			throw new InvalidInputException(error.trim());
		}
		PlayedGame pgame = Block223Application.getCurrentPlayableGame();
		if(pgame == null) {
			error = "A game must be selected to view its hall of fame.";
			throw new InvalidInputException(error.trim());
		}
		Game game = pgame.getGame();
		//add
		TOHallOfFame result = new TOHallOfFame(game.getName());
		HallOfFameEntry recent = game.getMostRecentEntry();
		int indexR = game.indexOfHallOfFameEntry(recent);
		int start = indexR + numberOfEntries/2;
		if(start > game.numberOfHallOfFameEntries() - 1) {
			start = game.numberOfHallOfFameEntries() - 1;
		}
		int end = start - numberOfEntries + 1;
		if(end < 0) {
			end = 0;
		}
		for(int i = start; i >= end; i--) {
			new TOHallOfFameEntry(i + 1, game.getHallOfFameEntry(i).getPlayername(), game. getHallOfFameEntry(i).getScore(), result);
		}
		return result;
	}

}
