package ca.mcgill.ecse223.block.application;

import ca.mcgill.ecse223.block.model.*;
import ca.mcgill.ecse223.block.persistence.*;

public class Block223Application {

	private static Block223 block223;
	private static Game game;
	private static UserRole userRole;
	private static Block223Persistence persistence;

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Blockpage().setVisible(true);
			}
		});
	}

	public static Block223 getBlock223() {
		if (block223 == null) {
			block223 = new Block223();
		}
		return block223;
	}
	//Ding
	public static Game resetBlock223() {
		game = Block223Persistence.load();
		return game;
	}
	//Ding
	public static Game getCurrentGame() {
		if (game == null) {
			throw new IllegalAccessException("This game does not exist");
		}
		return game;

	}

}
