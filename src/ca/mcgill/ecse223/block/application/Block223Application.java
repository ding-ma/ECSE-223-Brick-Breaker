package ca.mcgill.ecse223.block.application;

import ca.mcgill.ecse223.block.model.*;

public class Block223Application {
	
	private static Block223 block223;
	private static Game game;
	private static UserRole userRole;
	
	public static void main(String[] args) {
		// start UI
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Blockpage().setVisible(true);
            }
        });
	}
	//George
	public static Block223 getBlock223() {
		if (block223 == null) {
			block223 = new Block223();
		}
		 return block223;
	}
	
	//George	
	public static void setCurrentGame (Game aGame) {
		game = aGame;
		}
	
	//George
	public static Game getCurrentGame() {
		if (game == null) {
			return null;
		}
 		return game;
		
	}
	//George
	public static void setCurrentUserRole(UserRole aUserRole) {
		userRole = aUserRole;
	}
	//George
	public static UserRole getCurrentUserRole() {
		if (userRole == null) {
			return null;
		}
		return userRole;
		
	}
	
}
