/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/



// line 1 "StateMachine.ump"
public class GameOccurence
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //GameOccurence State Machines
  public enum GameStatus { Begin, Playing, Paused, Finish }
  private GameStatus gameStatus;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public GameOccurence()
  {
    setGameStatus(GameStatus.Begin);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getGameStatusFullName()
  {
    String answer = gameStatus.toString();
    return answer;
  }

  public GameStatus getGameStatus()
  {
    return gameStatus;
  }

  public boolean startGame()
  {
    boolean wasEventProcessed = false;
    
    GameStatus aGameStatus = gameStatus;
    switch (aGameStatus)
    {
      case Begin:
        if (isGameAdmin()&&ofTestMode()&&hasAllBlocks())
        {
        // line 11 "StateMachine.ump"
          
          setGameStatus(GameStatus.Playing);
          wasEventProcessed = true;
          break;
        }
        if (!(ofTestMode())&&hasAllBlocks())
        {
        // line 13 "StateMachine.ump"
          displayScore(HallOfFameEntry aHallOfFameEntry);
          setGameStatus(GameStatus.Playing);
          wasEventProcessed = true;
          break;
        }
        if (isGameAdmin()&&ofTestMode()&&!(hasEnoughBlocks()))
        {
        // line 16 "StateMachine.ump"
          addRandomBlocks();
          setGameStatus(GameStatus.Playing);
          wasEventProcessed = true;
          break;
        }
        if (!(ofTestMode())&&!(hasAllBlocks()))
        {
        // line 20 "StateMachine.ump"
          addRandomBlocks();
		displayScore(HallOfFameEntry aHallOfFameEntry);
          setGameStatus(GameStatus.Playing);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean moveBall(BallOccurrence aBallOccurrence)
  {
    boolean wasEventProcessed = false;
    
    GameStatus aGameStatus = gameStatus;
    switch (aGameStatus)
    {
      case Playing:
        if (isBlockHit()&&!(isLastBlock()))
        {
        // line 35 "StateMachine.ump"
          doBlockHit(BallOccurrence aBallOccurrence);
			deleteBlock(BlockAssignmentOccurence aBlockAssignmentOccurrence);
			increaseScore(HallOfEntry aHallOfFameEntry);
          setGameStatus(GameStatus.Playing);
          wasEventProcessed = true;
          break;
        }
        if (isBlockHit()&&isLastBlock()&&!(isLastLevel()))
        {
        // line 41 "StateMachine.ump"
          doBlockHit(BallOccurrence aBallOccurrence);
			deleteBlock(SpecificBlock aBlock);
			resetPaddlePos(PaddleOccurrence aPaddleOccurrence);
			resetBallPos(BallOccurrence aBallOccurrence);
			increaseScore(HallOfFameEntry aHallOfFameEntry);
			currentLevel++;
          setGameStatus(GameStatus.Paused);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean resumeGame()
  {
    boolean wasEventProcessed = false;
    
    GameStatus aGameStatus = gameStatus;
    switch (aGameStatus)
    {
      case Paused:
        if (noBlocksLeft())
        {
        // line 90 "StateMachine.ump"
          initializeBlocks();
			addRandomBlocks();
			initializeBall();
			initializePaddle();
          setGameStatus(GameStatus.Playing);
          wasEventProcessed = true;
          break;
        }
        if (!(noBlocksLeft()))
        {
        // line 96 "StateMachine.ump"
          initializeBlocks();
			initializeBall();
			initializePaddle();
          setGameStatus(GameStatus.Playing);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setGameStatus(GameStatus aGameStatus)
  {
    gameStatus = aGameStatus;

    // entry actions and do activities
    switch(gameStatus)
    {
      case Begin:
        // line 5 "StateMachine.ump"
        initializeBlocks();
		initializeBall();
		initializePaddle();
        break;
      case Playing:
        // line 31 "StateMachine.ump"
        moveBall(BallOccurrence aBallOccurrence);
        break;
    }
  }

  public void delete()
  {}
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 117 "StateMachine.ump"
  private boolean isGameAdmin() ;
// line 118 "StateMachine.ump"
  private boolean ofTestMode() ;
// line 119 "StateMachine.ump"
  private boolean hasEnoughBlocks() ;
// line 120 "StateMachine.ump"
  private boolean hasNoBlocks() ;
// line 121 "StateMachine.ump"
  private boolean isBlockHit() ;
// line 122 "StateMachine.ump"
  private boolean isWallHit() ;
// line 123 "StateMachine.ump"
  private boolean isPaddleHit() ;
// line 124 "StateMachine.ump"
  private boolean isOutOfBounds() ;
// line 125 "StateMachine.ump"
  private boolean isLastBlock() ;
// line 126 "StateMachine.ump"
  private boolean hasLifeLeft() ;
// line 128 "StateMachine.ump"
  private boolean isLastLevel() ;
// line 131 "StateMachine.ump"
  private initializeBlocks() ;
// line 132 "StateMachine.ump"
  private initializeBall() ;
// line 133 "StateMachine.ump"
  private initializePaddle() ;
// line 134 "StateMachine.ump"
  private displayScore() ;
// line 135 "StateMachine.ump"
  private addRandomBlocks() ;
// line 136 "StateMachine.ump"
  private doBlockHit()
	private increaseScore()
	
	private doBlockHit(BallOccurrece aBallOccurrence) ;
// line 140 "StateMachine.ump"
  private deleteBlock(SpecificBlock aBlock) ;
// line 141 "StateMachine.ump"
  private resetPaddlePos(PaddleOccurrence aPaddleOccurrence) ;
// line 142 "StateMachine.ump"
  private resetBallPos(BallOccurrence aBallOccurrence) ;
// line 143 "StateMachine.ump"
  private increaseScore(HallOfFameEntry aHallOfFameEntry) ;
// line 145 "StateMachine.ump"
  private deleteBlock(BlockAssignmentOccurrence aBlockAssignmentOccurrence) ;
// line 147 "StateMachine.ump"
  private doWallHit(BallOccurrence aBallOccurrence) ;
// line 148 "StateMachine.ump"
  private doOutOfBounds(BallOccurrence aBallOccurence) ;
// line 149 "StateMachine.ump"
  private resetPaddlePos(PaddleOccurrence aPaddleOccurence) ;
// line 150 "StateMachine.ump"
  private resetBallPos(BallOccurrence aBallOccurrence) ;
// line 152 "StateMachine.ump"
  private doWallHit(BallOccurrence aBallOccurrence) ;
// line 153 "StateMachine.ump"
  private doPaddleHit(BallOccurrence aBallOccurrence) ;
// line 155 "StateMachine.ump"
  private doOutOfBounds(BallOccurrence aBallOccurence) ;
// line 156 "StateMachine.ump"
  private stopBall(BallOccurrence aBallOccurrence) ;
// line 157 "StateMachine.ump"
  private stopPaddle(BallOccurrence aBallOccurrence) ;
// line 158 "StateMachine.ump"
  private displayGameOver() ;

  
}