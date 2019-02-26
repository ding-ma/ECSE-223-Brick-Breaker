package ca.mcgill.ecse223.block.controller;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse223.block.application.*;
import ca.mcgill.ecse223.block.model.*;

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

    }
    //George (Ding this is yours) 
    public static void deleteBlock(int id) throws InvalidInputException {
    Block block = Block223Application.getCurrentGame().findBlock(id);
    if (block!=null ) {
    	block.delete();
    } 
    }
    
    //George
    public static void updateBlock(int id, int red, int green, int blue, int points) throws InvalidInputException {
    	Block block = Block223Application.getCurrentGame().findBlock(id);
    	block.setBlue(red);
    	block.setBlue(green);
    	block.setBlue(blue);
    	block.setPoints(points);
    }
    //George
    public static void positionBlock(int id, int level, int gridHorizontalPosition, int gridVerticalPosition)
            throws InvalidInputException {
    	Block aBlock = Block223Application.getCurrentGame().findBlock(id);
    	Level aLevel =  Block223Application.getCurrentGame().getLevel(level-1);
		BlockAssignment blockAssignment = new BlockAssignment(gridHorizontalPosition, gridVerticalPosition, aLevel,
					aBlock, Block223Application.getCurrentGame());
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


    public static TOGame getCurrentDesignableGame() {
    	
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
    }
   
