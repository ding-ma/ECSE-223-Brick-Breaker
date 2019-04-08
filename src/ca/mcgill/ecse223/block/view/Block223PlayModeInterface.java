package ca.mcgill.ecse223.block.view;

import ca.mcgill.ecse223.block.controller.TOHallOfFameEntry;

public interface Block223PlayModeInterface {

    String takeInputs();


    void refresh();


	void endGame(int nrOfLives, TOHallOfFameEntry hof);

}
