package ca.mcgill.ecse223.block.application;

import ca.mcgill.ecse223.block.model;

public class Block223Application {
	
	private static Block223 block223;
	
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
			// for now, we are just creating an empty BTMS
			block223 = new Block223();
		}
 		return block223;
	}
	
}
