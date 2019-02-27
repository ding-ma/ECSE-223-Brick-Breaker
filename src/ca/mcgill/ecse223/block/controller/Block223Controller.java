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
    public static void createGame(String name) throws InvalidInputException {
    }

    public static void setGameDetails(int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
                                      Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {
    }

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
        /* check for admin
        if (getUserMode() != admin){
            error="You must be an admin to create a block";
        }
        */
        throw new InvalidInputException(error);
        Game game = Block223Application.getCurrentGame();
        try{
            Block block = game.addBlock( red,  green,  blue,  points);

            block.setRed(red);
            block.setGreen(green);
            block.setBlue(blue);
            block.setPoints(points);

            Block223Persistence.save(block);
        }
        catch (RuntimeException e){
            error = e.getMessage();
            if (error.equals("Cannot create block with same ID")) {
                error = "A ";
            }
            throw new InvalidInputException(error);
        }
    }

    public static void deleteBlock(int id) throws InvalidInputException {
        Game game = game.findBlock(id);
        if (game !=null) {
            game.delete();
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

    //Ding
    public static TOGame getCurrentDesignableGame() {
        Game currentGame = Block223Application.getCurrentGame();
        TOGame toGame = new TOGame(game.getName(), game.getLevels().size(), game.getNrBlocksPerLevel(), game.getBall.getminBallSpeedX(), game.getBall().getminballBallSpeedY(),
                game.getBall().getBallSpeedIncreaseFactor(), game.getPaddle().getMaxPaddleLength(),game.getPaddle().getMinPaddleLength());
        return CurrentGame;
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

    public static TOUserMode getUserMode() {
        Block223Application user = Block223Application.getCurrentUserRole();
        if (user == null) {
            TOUserMode toUserMode = new TOUserMode();
        }
        if (user == player){
            TOUserMode toUserMode = new TOUserMode();
        }
        if (user == admin){
            TOUserMode toUserMode = new TOUserMode();
        }

        return user;
    }
}
