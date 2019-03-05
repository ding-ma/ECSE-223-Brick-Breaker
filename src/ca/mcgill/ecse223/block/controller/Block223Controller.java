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

    //Yanick
    public static void createGame(String aName) throws InvalidInputException {
        String name = aName;
        String error;

        Block223 block223 = Block223Application.getBlock223();


        error = checkGameNameIsUnique(name, block223);
        if (error != null) {
            throw new InvalidInputException(error);
        }

        if(name == null){
            error = "The name of the game must be specified";
            throw new InvalidInputException(error);
        }

        UserRole userRole = Block223Application.getCurrentUserRole();
        if(userRole instanceof Player || userRole == null){
            error = "Admin in privileges are required to create a game.";
            throw new InvalidInputException(error);
        }
        String adminPassword = userRole.getPassword();

        Admin admin = new Admin(adminPassword, block223);

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


        Game game = Block223Application.getBlock223().findGame(name);

        if (game != null) {
            Block223 block223 = Block223Application.getBlock223();

            game.delete();
        }

    }


    //Anne-Julie
    public static void selectGame(String name) throws InvalidInputException {
        String error = "";

        Block223 block223 = Block223Application.getBlock223();
        UserRole userRole = Block223Application.getCurrentUserRole();
        if(userRole instanceof Player || userRole == null){
            error = "Admin in privileges are required to create a game.";
            throw new InvalidInputException(error);
        }

        String adminPassword = userRole.getPassword();
        Game game = Block223Application.getBlock223().findGame(name);

        if (userRole.getPassword() != Block223Application.getCurrentGame().getAdmin().getPassword()) {
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
        if(userRole instanceof Player || userRole == null){
            error = "Admin in privileges are required to create a game.";
            throw new InvalidInputException(error);
        }

        Game game = Block223Application.getCurrentGame();
        if (game == null) {
            error = "A game must be selected to define game settings.";
            throw new InvalidInputException(error);
        }

        if (userRole.getPassword() != Block223Application.getCurrentGame().getAdmin().getPassword()) {
            error += "Only admin who created the game can define its settings.";
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
        Block223 block223 = Block223Application.getBlock223();

        String error = "";
        UserRole userRole = Block223Application.getCurrentUserRole();
        if (userRole instanceof Player || userRole == null) {
            error += "Admin privileges are required to create a game.";
            throw new InvalidInputException(error);
        }
        if (Block223Application.getCurrentGame() == null) {
            error += "A game must be selected to position a block. ";
            throw new InvalidInputException(error);
        }

        if (userRole.getPassword() != Block223Application.getCurrentGame().getAdmin().getPassword()) {
            error += "Only admin who created the game can update a block. ";
            throw new InvalidInputException(error);

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
            Block223Controller.getBlocksOfCurrentDesignableGame();
            Block223Persistence.save(block223);

        } catch (RuntimeException e) {
            error = e.getMessage();
            throw new InvalidInputException(error);
        }

    }


    public static void deleteBlock(int id) throws InvalidInputException {
        String error = "";

        UserRole userRole = Block223Application.getCurrentUserRole();
        if (userRole instanceof Player || userRole == null) {
            error += "Admin privileges are required to create a game.";
            throw new InvalidInputException(error);
        }

        if (Block223Application.getCurrentGame() == null) {
            error += "A game most be selected to position a block. ";
            throw new InvalidInputException(error);
        }

        if (userRole.getPassword() != Block223Application.getCurrentGame().getAdmin().getPassword()) {
            error += "Only admin who created the game can update a block. ";
            throw new InvalidInputException(error);
        }
        if (error.length() > 0)
            throw new InvalidInputException(error.trim());


        Block block = Block223Application.getCurrentGame().findBlock(id);
        if (block != null) {
            block.delete();
            //TODO save in persistence???
            try {
                Block223Persistence.save(Block223Application.getBlock223());
            } catch (RuntimeException e) {
                throw new InvalidInputException(e.getMessage());

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
        Block223 block223 = Block223Application.getBlock223();

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
    public static List<TOGame> getDesignableGames() {
        ArrayList<TOGame> games = new ArrayList<TOGame>();
        for (Game game : Block223Application.getBlock223().getGames()) {
            //NOT sure about the numberOfBlocks() method.
            TOGame toGame = new TOGame(game.getName(), game.numberOfLevels(), game.numberOfBlocks(),
                    game.getBall().getMinBallSpeedX(), game.getBall().getMinBallSpeedY(), game.getBall().getBallSpeedIncreaseFactor(),
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
                game.getBall().getBallSpeedIncreaseFactor(), game.getPaddle().getMaxPaddleLength(), game.getPaddle().getMinPaddleLength());
        return toGame;
    }

    //George
    public static List<TOBlock> getBlocksOfCurrentDesignableGame() {
        ArrayList<TOBlock> blocks = new ArrayList<TOBlock>();
        for (Block block : Block223Application.getCurrentGame().getBlocks()) {
            TOBlock toBlock = new TOBlock(block.getId(), block.getRed(), block.getBlue(), block.getGreen(), block.getPoints());
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
    public static List<TOGridCell> getBlocksAtLevelOfCurrentDesignableGame(int level) throws InvalidInputException {
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
}

