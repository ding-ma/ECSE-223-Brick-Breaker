package ca.mcgill.ecse223.block.application;

import ca.mcgill.ecse223.block.model.*;
import ca.mcgill.ecse223.block.persistence.*;

public class Block223Application {

	private static Block223 block223;
	private static Game game;
	private static UserRole userRole;
	private static Block223Persistence persistence;

	public static void main(String[] args) {
		// start UI
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

	public static Game getCurrentGame() {
		if (game == null) {
			throw new IllegalAccessException("This game does not exist");
		}
		return game;

	}

	public static BReset resetBlock223() {
		persistence.load();
		return 
	}
}
