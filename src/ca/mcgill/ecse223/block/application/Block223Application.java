package ca.mcgill.ecse223.block.application;

import ca.mcgill.ecse223.block.model.*;
import ca.mcgill.ecse223.block.view.*;

public class Block223Application {
	
	private static Block223 block223;
	private static Game game;
	private static UserRole userRole;
	
	public static void main(String[] args) {
		// start UI
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MasterUI().setVisible(true);
            }
        });
	}
	//Done
	public static Block223 getBlock223() {
		if (block223 == null) {
			block223 = new Block223();
		}
 		return block223;
	}
	//TODO resetBlock223().
	public static void resetBlock223() {
		
	}
	//TODO: How do you actually setCurrentGame?
	public static void setCurrentGame (Game aGame) {
		game = aGame;
		}
	//TODO: Return an error when the game is null.
	public static Game getCurrentGame() {
		if (game == null) {
			game = new Game(null, 0, null, null, null, block223);
		}
 		return game;
		
	}
	//TODO:setCurrentUserRole
	public static void setCurrentUserRole(UserRole aUserRole) {
		userRole = aUserRole;
	}
	//TODO: getCurrentUserRole
	public static UserRole getCurrentUserRole() {
		if (userRole == null) {
			return null;
		}
		return userRole;
		
	}
	
}