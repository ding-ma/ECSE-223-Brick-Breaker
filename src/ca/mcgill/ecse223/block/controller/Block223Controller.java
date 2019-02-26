package ca.mcgill.ecse223.block.controller.;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse.btms.application.BtmsApplication;
import ca.mcgill.ecse.btms.controller.TOBusVehicle;
import ca.mcgill.ecse.btms.model.BusVehicle;
import ca.mcgill.ecse.btms.model.Driver;

public class Block223Controller {

    // ****************************
    // Modifier methods
    // ****************************
    public static void createGame(String name) throws InvalidInputException {
    }

    public static void setGameDetails(int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
                                      Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {
    }

    public static void deleteGame(String name) throws InvalidInputException {
        Game game = getGameByName(name);
        if (game != null) {
            game.deleteGame();
            try {
                //BtmsPersistence.save(BtmsApplication.getBtms());
                //TODO: Save with persistence
            }
            catch (RuntimeException e) {
                throw new InvalidInputException(e.getMessage());
            }
        }

    }

    public static void selectGame(String name) throws InvalidInputException {
    }

    public static void updateGame(String name, int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
                                  Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {
    }

    public static void addBlock(int red, int green, int blue, int points) throws InvalidInputException {
        String error = "";
        if (red <0){
            error = "value of red has to be greater or equal than 0";
        }
        if (green<0){
            error = "value of green has to be greater or equal than 0";
        }
        if (blue<0){
            error = "value of blue has to be greater or equal than 0";
        }
        if (red >255){
            error ="value of red has to be smaller or equal to 255";
        }
        if (green >255){
            error ="value of green has to be smaller or equal to 255";
        }
        if (blue >255){
            error ="value of blue has to be smaller or equal to 255";
        }
        if (points<0){
            error = "value of points have to be greater or equal to 0";
        }
        if (points>1000){
            error="value of points have to be smaller or equal to 1000";
        }
//admin exception
    }

    public static void deleteBlock(int id) throws InvalidInputException {
        Block block = Block223Controller.deleteBlock(id);
        if (block !=null) {
            block.deleteBlock();
            try {
                Block223Persistence.save(Block223Application.getBlock223());
            } catch (RuntimeException e) {
                throw new InvalidInputException(e.getMessage());
            }
        }
    }

    public static void updateBlock(int id, int red, int green, int blue, int points) throws InvalidInputException {
    }

    public static void positionBlock(int id, int level, int gridHorizontalPosition, int gridVerticalPosition)
            throws InvalidInputException {
    }

    public static void moveBlock(int level, int oldGridHorizontalPosition, int oldGridVerticalPosition,
                                 int newGridHorizontalPosition, int newGridVerticalPosition) throws InvalidInputException {
    }

    public static void removeBlock(int level, int gridHorizontalPosition, int gridVerticalPosition)
            throws InvalidInputException {
    }

    public static void saveGame() throws InvalidInputException {
    }

    public static void register(String username, String playerPassword, String adminPassword)
            throws InvalidInputException {
    }

    public static void login(String username, String password) throws InvalidInputException {
    }

    public static void logout() {
    }

    // ****************************
    // Query methods
    // ****************************
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


    public static TOGame getCurrentDesignableGame() {
    }

    public static List<TOBlock> getBlocksOfCurrentDesignableGame() {
    }
    //George
    public static List<TOBlock> getBlocksOfCurrentDesignableGame() {
        ArrayList <TOBlock> blocks= new ArrayList<TOBlock>();
        for (Block block: Block223Application.getBlock223().getCurrentGame().getBlocks()) {
            TOBlock toBlock =  new TOBlock( block.getId(), block.getRed(), block.getBlue(), block.getGreen(), block.getPoints());
            TOBlock.add(toBlock);
        }
        return blocks;
    }

    public List<TOGridCell> getBlocksAtLevelOfCurrentDesignableGame(int level) throws InvalidInputException {
    }

    public static TOUserMode getUserMode() {
    }

}
