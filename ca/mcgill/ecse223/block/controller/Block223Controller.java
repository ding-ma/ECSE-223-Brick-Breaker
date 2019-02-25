package ca.mcgill.ecse223.block.controller;

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
	}

	public static void selectGame(String name) throws InvalidInputException {
	}

	public static void updateGame(String name, int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
			Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {
	}

	public static void addBlock(int red, int green, int blue, int points) throws InvalidInputException {
	}

	public static void deleteBlock(int id) throws InvalidInputException {
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
		for (Game game : Block223Application.get().getVehicles()) {
			TOBusVehicle toBusVehicle = new TOBusVehicle(bus.getLicencePlate());
			buses.add(toBusVehicle);
		}
		return games;
	}

	public static TOGame getCurrentDesignableGame() {
	}

	public static List<TOBlock> getBlocksOfCurrentDesignableGame() {
	}
	//George
	public static TOBlock getBlockOfCurrentDesignableGame(int id) throws InvalidInputException {
		TOBlock gotBlock = null;
		for (Block block: Block223Application.getGame().getBlocks()) {
			if (block.getId() == id) {
				gotBlock = block;
				break;
			}
		}
		return gotBlock;
	}

	public List<TOGridCell> getBlocksAtLevelOfCurrentDesignableGame(int level) throws InvalidInputException {
	}

	public static TOUserMode getUserMode() {
	}

}