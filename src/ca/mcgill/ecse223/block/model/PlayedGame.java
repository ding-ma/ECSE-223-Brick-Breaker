/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

// line 107 "../../../../../Block223Persistence.ump"
// line 11 "../../../../../Block223PlayMode.ump"
// line 1 "../../../../../Block223States.ump"
public class PlayedGame implements Serializable
{

  //------------------------
  // STATIC VARIABLES
  //------------------------


  /**
   * at design time, the initial wait time may be adjusted as seen fit
   */
  public static final int INITIAL_WAIT_TIME = 1000;
  private static int nextId = 1;
  public static final int NR_LIVES = 3;

  /**
   * the PlayedBall and PlayedPaddle are not in a separate class to avoid the bug in Umple that occurred for the second constructor of Game
   * no direct link to Ball, because the ball can be found by navigating to Game and then Ball
   */
  public static final int BALL_INITIAL_X = Game.PLAY_AREA_SIDE / 2;
  public static final int BALL_INITIAL_Y = Game.PLAY_AREA_SIDE / 2;

  /**
   * no direct link to Paddle, because the paddle can be found by navigating to Game and then Paddle
   * pixels moved when right arrow key is pressed
   */
  public static final int PADDLE_MOVE_RIGHT = 1;

  /**
   * pixels moved when left arrow key is pressed
   */
  public static final int PADDLE_MOVE_LEFT = -1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PlayedGame Attributes
  private int score;
  private int lives;
  private int currentLevel;
  private double waitTime;
  private String playername;
  private double ballDirectionX;
  private double ballDirectionY;
  private double currentBallX;
  private double currentBallY;
  private double currentPaddleLength;
  private double currentPaddleX;
  private double currentPaddleY;

  //Autounique Attributes
  private int id;

  //PlayedGame State Machines
  public enum PlayStatus { Ready, Moving, Paused, GameOver }
  private PlayStatus playStatus;

  //PlayedGame Associations
  private Player player;
  private Game game;
  private List<PlayedBlockAssignment> blocks;
  private BouncePoint bounce;
  private Block223 block223;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PlayedGame(String aPlayername, Game aGame, Block223 aBlock223)
  {
    // line 47 "../../../../../Block223PlayMode.ump"
    boolean didAddGameResult = setGame(aGame);
          if (!didAddGameResult)
          {
             throw new RuntimeException("Unable to create playedGame due to game");
          }
    // END OF UMPLE BEFORE INJECTION
    score = 0;
    lives = NR_LIVES;
    currentLevel = 1;
    waitTime = INITIAL_WAIT_TIME;
    playername = aPlayername;
    resetBallDirectionX();
    resetBallDirectionY();
    resetCurrentBallX();
    resetCurrentBallY();
    currentPaddleLength = getGame().getPaddle().getMaxPaddleLength();
    resetCurrentPaddleX();
    currentPaddleY = Game.PLAY_AREA_SIDE - Paddle.VERTICAL_DISTANCE - Paddle.PADDLE_WIDTH;
    id = nextId++;
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create playedGame due to game");
    }
    blocks = new ArrayList<PlayedBlockAssignment>();
    boolean didAddBlock223 = setBlock223(aBlock223);
    if (!didAddBlock223)
    {
      throw new RuntimeException("Unable to create playedGame due to block223");
    }
    setPlayStatus(PlayStatus.Ready);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setScore(int aScore)
  {
    boolean wasSet = false;
    score = aScore;
    wasSet = true;
    return wasSet;
  }

  public boolean setLives(int aLives)
  {
    boolean wasSet = false;
    lives = aLives;
    wasSet = true;
    return wasSet;
  }

  public boolean setCurrentLevel(int aCurrentLevel)
  {
    boolean wasSet = false;
    currentLevel = aCurrentLevel;
    wasSet = true;
    return wasSet;
  }

  public boolean setWaitTime(double aWaitTime)
  {
    boolean wasSet = false;
    waitTime = aWaitTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setPlayername(String aPlayername)
  {
    boolean wasSet = false;
    playername = aPlayername;
    wasSet = true;
    return wasSet;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setBallDirectionX(double aBallDirectionX)
  {
    boolean wasSet = false;
    ballDirectionX = aBallDirectionX;
    wasSet = true;
    return wasSet;
  }

  public boolean resetBallDirectionX()
  {
    boolean wasReset = false;
    ballDirectionX = getDefaultBallDirectionX();
    wasReset = true;
    return wasReset;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setBallDirectionY(double aBallDirectionY)
  {
    boolean wasSet = false;
    ballDirectionY = aBallDirectionY;
    wasSet = true;
    return wasSet;
  }

  public boolean resetBallDirectionY()
  {
    boolean wasReset = false;
    ballDirectionY = getDefaultBallDirectionY();
    wasReset = true;
    return wasReset;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setCurrentBallX(double aCurrentBallX)
  {
    boolean wasSet = false;
    currentBallX = aCurrentBallX;
    wasSet = true;
    return wasSet;
  }

  public boolean resetCurrentBallX()
  {
    boolean wasReset = false;
    currentBallX = getDefaultCurrentBallX();
    wasReset = true;
    return wasReset;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setCurrentBallY(double aCurrentBallY)
  {
    boolean wasSet = false;
    currentBallY = aCurrentBallY;
    wasSet = true;
    return wasSet;
  }

  public boolean resetCurrentBallY()
  {
    boolean wasReset = false;
    currentBallY = getDefaultCurrentBallY();
    wasReset = true;
    return wasReset;
  }

  public boolean setCurrentPaddleLength(double aCurrentPaddleLength)
  {
    boolean wasSet = false;
    currentPaddleLength = aCurrentPaddleLength;
    wasSet = true;
    return wasSet;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setCurrentPaddleX(double aCurrentPaddleX)
  {
    boolean wasSet = false;
    currentPaddleX = aCurrentPaddleX;
    wasSet = true;
    return wasSet;
  }

  public boolean resetCurrentPaddleX()
  {
    boolean wasReset = false;
    currentPaddleX = getDefaultCurrentPaddleX();
    wasReset = true;
    return wasReset;
  }

  public int getScore()
  {
    return score;
  }

  public int getLives()
  {
    return lives;
  }

  public int getCurrentLevel()
  {
    return currentLevel;
  }

  public double getWaitTime()
  {
    return waitTime;
  }

  /**
   * added here so that it only needs to be determined once
   */
  public String getPlayername()
  {
    return playername;
  }

  /**
   * 0/0 is the top left corner of the play area, i.e., a directionX/Y of 0/1 moves the ball down in a straight line
   */
  public double getBallDirectionX()
  {
    return ballDirectionX;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultBallDirectionX()
  {
    return getGame().getBall().getMinBallSpeedX();
  }

  public double getBallDirectionY()
  {
    return ballDirectionY;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultBallDirectionY()
  {
    return getGame().getBall().getMinBallSpeedY();
  }

  /**
   * the position of the ball is at the center of the ball
   */
  public double getCurrentBallX()
  {
    return currentBallX;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultCurrentBallX()
  {
    return BALL_INITIAL_X;
  }

  public double getCurrentBallY()
  {
    return currentBallY;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultCurrentBallY()
  {
    return BALL_INITIAL_Y;
  }

  public double getCurrentPaddleLength()
  {
    return currentPaddleLength;
  }

  /**
   * the position of the paddle is at its top left corner
   */
  public double getCurrentPaddleX()
  {
    return currentPaddleX;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultCurrentPaddleX()
  {
    return (Game.PLAY_AREA_SIDE - currentPaddleLength) / 2;
  }

  public double getCurrentPaddleY()
  {
    return currentPaddleY;
  }

  public int getId()
  {
    return id;
  }

  public String getPlayStatusFullName()
  {
    String answer = playStatus.toString();
    return answer;
  }

  public PlayStatus getPlayStatus()
  {
    return playStatus;
  }

  public boolean play()
  {
    boolean wasEventProcessed = false;
    
    PlayStatus aPlayStatus = playStatus;
    switch (aPlayStatus)
    {
      case Ready:
        setPlayStatus(PlayStatus.Moving);
        wasEventProcessed = true;
        break;
      case Paused:
        setPlayStatus(PlayStatus.Moving);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean pause()
  {
    boolean wasEventProcessed = false;
    
    PlayStatus aPlayStatus = playStatus;
    switch (aPlayStatus)
    {
      case Moving:
        setPlayStatus(PlayStatus.Paused);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean move()
  {
    boolean wasEventProcessed = false;
    
    PlayStatus aPlayStatus = playStatus;
    switch (aPlayStatus)
    {
      case Moving:
        if (hitPaddle())
        {
        // line 12 "../../../../../Block223States.ump"
          doHitPaddleOrWall();
          setPlayStatus(PlayStatus.Moving);
          wasEventProcessed = true;
          break;
        }
        if (isOutOfBoundsAndLastLife())
        {
        // line 13 "../../../../../Block223States.ump"
          doOutOfBounds();
          setPlayStatus(PlayStatus.GameOver);
          wasEventProcessed = true;
          break;
        }
        if (isOutOfBounds())
        {
        // line 14 "../../../../../Block223States.ump"
          doOutOfBounds();
          setPlayStatus(PlayStatus.Paused);
          wasEventProcessed = true;
          break;
        }
        if (hitLastBlockAndLastLevel())
        {
        // line 15 "../../../../../Block223States.ump"
          doHitBlock();
          setPlayStatus(PlayStatus.GameOver);
          wasEventProcessed = true;
          break;
        }
        if (hitLastBlock())
        {
        // line 16 "../../../../../Block223States.ump"
          doHitBlockNextLevel();
          setPlayStatus(PlayStatus.Ready);
          wasEventProcessed = true;
          break;
        }
        if (hitBlock())
        {
        // line 17 "../../../../../Block223States.ump"
          doHitBlock();
          setPlayStatus(PlayStatus.Moving);
          wasEventProcessed = true;
          break;
        }
        if (hitWall())
        {
        // line 18 "../../../../../Block223States.ump"
          doHitPaddleOrWall();
          setPlayStatus(PlayStatus.Moving);
          wasEventProcessed = true;
          break;
        }
        // line 19 "../../../../../Block223States.ump"
        doHitNothingAndNotOutOfBounds();
        setPlayStatus(PlayStatus.Moving);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setPlayStatus(PlayStatus aPlayStatus)
  {
    playStatus = aPlayStatus;

    // entry actions and do activities
    switch(playStatus)
    {
      case Ready:
        // line 7 "../../../../../Block223States.ump"
        doSetup();
        break;
      case GameOver:
        // line 25 "../../../../../Block223States.ump"
        doGameOver();
        break;
    }
  }
  /* Code from template association_GetOne */
  public Player getPlayer()
  {
    return player;
  }

  public boolean hasPlayer()
  {
    boolean has = player != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_GetMany */
  public PlayedBlockAssignment getBlock(int index)
  {
    PlayedBlockAssignment aBlock = blocks.get(index);
    return aBlock;
  }

  public List<PlayedBlockAssignment> getBlocks()
  {
    List<PlayedBlockAssignment> newBlocks = Collections.unmodifiableList(blocks);
    return newBlocks;
  }

  public int numberOfBlocks()
  {
    int number = blocks.size();
    return number;
  }

  public boolean hasBlocks()
  {
    boolean has = blocks.size() > 0;
    return has;
  }

  public int indexOfBlock(PlayedBlockAssignment aBlock)
  {
    int index = blocks.indexOf(aBlock);
    return index;
  }
  /* Code from template association_GetOne */
  public BouncePoint getBounce()
  {
    return bounce;
  }

  public boolean hasBounce()
  {
    boolean has = bounce != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Block223 getBlock223()
  {
    return block223;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setPlayer(Player aPlayer)
  {
    boolean wasSet = false;
    Player existingPlayer = player;
    player = aPlayer;
    if (existingPlayer != null && !existingPlayer.equals(aPlayer))
    {
      existingPlayer.removePlayedGame(this);
    }
    if (aPlayer != null)
    {
      aPlayer.addPlayedGame(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setGame(Game aGame)
  {
    boolean wasSet = false;
    if (aGame == null)
    {
      return wasSet;
    }

    Game existingGame = game;
    game = aGame;
    if (existingGame != null && !existingGame.equals(aGame))
    {
      existingGame.removePlayedGame(this);
    }
    game.addPlayedGame(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlocks()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public PlayedBlockAssignment addBlock(int aX, int aY, Block aBlock)
  {
    return new PlayedBlockAssignment(aX, aY, aBlock, this);
  }

  public boolean addBlock(PlayedBlockAssignment aBlock)
  {
    boolean wasAdded = false;
    if (blocks.contains(aBlock)) { return false; }
    PlayedGame existingPlayedGame = aBlock.getPlayedGame();
    boolean isNewPlayedGame = existingPlayedGame != null && !this.equals(existingPlayedGame);
    if (isNewPlayedGame)
    {
      aBlock.setPlayedGame(this);
    }
    else
    {
      blocks.add(aBlock);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBlock(PlayedBlockAssignment aBlock)
  {
    boolean wasRemoved = false;
    //Unable to remove aBlock, as it must always have a playedGame
    if (!this.equals(aBlock.getPlayedGame()))
    {
      blocks.remove(aBlock);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBlockAt(PlayedBlockAssignment aBlock, int index)
  {  
    boolean wasAdded = false;
    if(addBlock(aBlock))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlocks()) { index = numberOfBlocks() - 1; }
      blocks.remove(aBlock);
      blocks.add(index, aBlock);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBlockAt(PlayedBlockAssignment aBlock, int index)
  {
    boolean wasAdded = false;
    if(blocks.contains(aBlock))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlocks()) { index = numberOfBlocks() - 1; }
      blocks.remove(aBlock);
      blocks.add(index, aBlock);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBlockAt(aBlock, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetUnidirectionalOptionalOne */
  public boolean setBounce(BouncePoint aNewBounce)
  {
    boolean wasSet = false;
    bounce = aNewBounce;
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setBlock223(Block223 aBlock223)
  {
    boolean wasSet = false;
    if (aBlock223 == null)
    {
      return wasSet;
    }

    Block223 existingBlock223 = block223;
    block223 = aBlock223;
    if (existingBlock223 != null && !existingBlock223.equals(aBlock223))
    {
      existingBlock223.removePlayedGame(this);
    }
    block223.addPlayedGame(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    if (player != null)
    {
      Player placeholderPlayer = player;
      this.player = null;
      placeholderPlayer.removePlayedGame(this);
    }
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removePlayedGame(this);
    }
    while (blocks.size() > 0)
    {
      PlayedBlockAssignment aBlock = blocks.get(blocks.size() - 1);
      aBlock.delete();
      blocks.remove(aBlock);
    }
    
    bounce = null;
    Block223 placeholderBlock223 = block223;
    this.block223 = null;
    if(placeholderBlock223 != null)
    {
      placeholderBlock223.removePlayedGame(this);
    }
  }


  /**
   * Guards
   */
  // line 32 "../../../../../Block223States.ump"
   private boolean hitPaddle(){
    // George
    BouncePoint bp = calculateBouncePointPaddle();
    
    setBounce(bp);

     return bp != null;
   }

  // line 45 "../../../../../Block223States.ump"
   private boolean isOutOfBoundsAndLastLife(){
    int lives = getLives(); 
   boolean outOfBounds = isOutOfBounds();
   if(outOfBounds == true){
     return lives == 1;
  }
 return false;
  }

  // line 56 "../../../../../Block223States.ump"
   private boolean isOutOfBounds(){
	   return isBallOutOfBounds();
   /* double xI = getCurrentBallX();
     double yI = getCurrentBallY();
     double dx = getBallDirectionX();
     double dy = getBallDirectionY();

     double xF = xI+dx;
     double yF = yI+dy;
     
    Rectangle rect = new Rectangle(0,Game.PLAY_AREA_SIDE, Game.PLAY_AREA_SIDE, (Ball.BALL_DIAMETER / 2));
    
    boolean outOfBounds = rect.intersectsLine(xI, yI, xF, yF);
    return outOfBounds;*/
  }
   
   private boolean isBallOutOfBounds() {
	   double ballY = getCurrentBallY()+Ball.BALL_DIAMETER;
	   double paddleTopY = getCurrentPaddleY(); 
	   return(paddleTopY > ballY);
   }

  // line 72 "../../../../../Block223States.ump"
   private boolean hitLastBlockAndLastLevel(){
    // Yannick
	game = getGame();

	int nrLevels = game.numberOfLevels();
	
	setBounce(null);

	if(nrLevels == currentLevel){
		int nrBlocks = numberOfBlocks();

		if(nrBlocks == 1){
			PlayedBlockAssignment block = getBlock(0);
			BouncePoint bp = calculateBouncePointBlock(block);
			if(bp == null){
				return false;
			}
			setBounce(bp);
			return true;
		}
	}
    return false;
  }

  // line 96 "../../../../../Block223States.ump"
   private boolean hitLastBlock(){
    // Yannick
    	int nrBlocks = numberOfBlocks();
    
    	setBounce(null);
    
    	if(nrBlocks == 1){
    
    		PlayedBlockAssignment block = getBlock(0);
    
    		BouncePoint bp = calculateBouncePointBlock(block);
    
    		if(bp == null){
    			return false;
    		}
    	
    		setBounce(bp);
			return true;
		}
    
		return false;
  }

  // line 119 "../../../../../Block223States.ump"
   private boolean hitBlock(){
    // Yannick
    
    int nrBlocks = numberOfBlocks();
    
    setBounce(null);
    
    for(int i = 0 ; i < numberOfBlocks() - 1 ; i++){
    
    	 PlayedBlockAssignment block = getBlock(i);
    	 
    	 BouncePoint bp = calculateBouncePointBlock(block);
    	 
    	 bounce = getBounce();
    	 
    	 if(bp != null && bounce != null){
    	 	Boolean closer = isCloser(bp, bounce);
    	 
    	 	if(closer){
    	 	
    	 		setBounce(bp);
    	 	
    		}
    		return true;
    	}
    }
    return false;
  }

  // line 148 "../../../../../Block223States.ump"
   private boolean hitWall(){
    // George
    BouncePoint bp = calculateBouncePointWall();
    
    setBounce(bp);

     return bp != null;
   }


  /**
   * Actions
   */
  // line 162 "../../../../../Block223States.ump"
   private void doSetup(){
    resetCurrentBallX();
                              resetCurrentBallY();
                              resetCurrentBallX();
                              resetCurrentBallY();
                              resetCurrentPaddleX();
                              getGame();
                              Level assignment = game.getLevel(currentLevel - 1);
                              List<BlockAssignment> assignments = assignment.getBlockAssignments();
                              for (BlockAssignment a : assignments) {
                                PlayedBlockAssignment pblock = new PlayedBlockAssignment(Game.WALL_PADDING + (Block.SIZE +
                                        Game.COLUMNS_PADDING) * (a.getGridHorizontalPosition() - 1), Game.WALL_PADDING + (Block.SIZE + Game.ROW_PADDING) *
                                        (a.getGridVerticalPosition() - 1), a.getBlock(), this);
                              }

                              while (numberOfBlocks() < game.getNrBlocksPerLevel()) {
                                int x = ThreadLocalRandom.current().nextInt(1, 15);
                                int y = ThreadLocalRandom.current().nextInt(1, 15);



                                for (BlockAssignment ablockAssignment : assignments) {
                                  if (ablockAssignment.getGridHorizontalPosition() == x && ablockAssignment.getGridVerticalPosition() == y) {
                                    x=x++;
                                    y=y++;
                                  }
                                  if (ablockAssignment.getGridHorizontalPosition() != x && ablockAssignment.getGridVerticalPosition() != y){
                                    PlayedBlockAssignment pblock = new PlayedBlockAssignment(x, y, game.getRandomBlock(), this);
                                  }
                                }
                              }
  }

  // line 195 "../../../../../Block223States.ump"
   private void doHitPaddleOrWall(){
    // George
    bounceBall();
  }

  // line 201 "../../../../../Block223States.ump"
   private void doOutOfBounds(){
	   setLives(lives-1);
     resetCurrentBallX();
     resetCurrentBallY();
     resetBallDirectionX();
     resetBallDirectionY();
     resetCurrentPaddleX();
  }

  // line 210 "../../../../../Block223States.ump"
   private void doHitBlock(){
    // Yannick
    int score = getScore();
    bounce = getBounce();
    
    PlayedBlockAssignment pblock = bounce.getHitBlock();
    
    Block block = pblock.getBlock();
    
    int points = block.getPoints();
    
    setScore(score + points);
    
    pblock.delete();
    
    bounceBall();
  }

  // line 229 "../../../../../Block223States.ump"
   private void doHitBlockNextLevel(){
    // Yannick
    doHitBlock();
    
    int level = getCurrentLevel();
    
    setCurrentLevel(level + 1);
    
    setCurrentPaddleLength(getGame().getPaddle().getMaxPaddleLength() -
    	(getGame().getPaddle().getMaxPaddleLength() - getGame().getPaddle().getMinPaddleLength()) / 
    	(getGame().numberOfLevels() - 1) * (getCurrentLevel() - 1));
    	
    setWaitTime(INITIAL_WAIT_TIME * Math.pow(getGame().getBall().getBallSpeedIncreaseFactor(), (getCurrentLevel() - 1)));
  }

  // line 245 "../../../../../Block223States.ump"
   private void doHitNothingAndNotOutOfBounds(){
    double x = getCurrentBallX();
    double y = getCurrentBallY();
    double dx = getBallDirectionX();
    double dy = getBallDirectionY();
    
    setCurrentBallX(x+dx);
    setCurrentBallY(y+dy);
  }



  // line 203 "../../../../../Block223States.ump"

 private void doGameOver(){
     
     Block223 block223 = getBlock223();
   Player p = getPlayer();
   Game pg = getGame();
     if(p!=null){
       HallOfFameEntry hof = new HallOfFameEntry(score, playername, p, pg, block223);
       pg.setMostRecentEntry(hof);
     }

   delete();
  }


  /**
   * !!!GEORGE!!! BouncePoint calculateBouncePointPaddle
   */
  // line 273 "../../../../../Block223States.ump"
   private BouncePoint calculateBouncePointPaddle(){
    int radius = Ball.BALL_DIAMETER/2;
		BouncePoint bP = null;

		//ball coords
		double xCurrent = this.getCurrentBallX();
		double yCurrent = this.getCurrentBallY();
		double xNext = xCurrent + this.getBallDirectionX();
		double yNext = yCurrent + this.getBallDirectionY();

		//paddle rectangles
		Rectangle2D.Double rectA = new Rectangle2D.Double(this.getCurrentPaddleX(), this.getCurrentPaddleY() - radius, this.getCurrentPaddleLength(), radius);
		Rectangle2D.Double rectB = new Rectangle2D.Double(this.getCurrentPaddleX() - radius, this.getCurrentPaddleY(), radius, 5.);
		Rectangle2D.Double rectC = new Rectangle2D.Double(this.getCurrentPaddleX() + this.getCurrentPaddleLength(), this.getCurrentPaddleY(), radius, 5);
		Rectangle2D.Double rectE = new Rectangle2D.Double(this.getCurrentPaddleX() - radius, this.getCurrentPaddleY() - radius, radius, radius);
		Rectangle2D.Double rectF = new Rectangle2D.Double(this.getCurrentPaddleX() + this.getCurrentPaddleLength(), this.getCurrentPaddleY() - radius, radius, radius);

		//intersections
		boolean interA = rectA.intersectsLine(xCurrent, yCurrent, xNext, yNext);
		boolean interB = rectB.intersectsLine(xCurrent, yCurrent, xNext, yNext);
		boolean interC = rectC.intersectsLine(xCurrent, yCurrent, xNext, yNext);
		boolean interE = rectE.intersectsLine(xCurrent, yCurrent, xNext, yNext);
		boolean interF = rectF.intersectsLine(xCurrent, yCurrent, xNext, yNext);

		//the ball does not intersect anything
		if (!interA && !interB && !interC && !interE && !interF) {
			return null;
		}

		//intersectionA
		if (interA) {
			Line2D.Double paddleBounds = new Line2D.Double(this.getCurrentPaddleX(),
					this.getCurrentPaddleY() - radius,
					this.getCurrentPaddleX() + this.getCurrentPaddleLength(),
					this.getCurrentPaddleY() - radius);

			Line2D.Double ballPath = new Line2D.Double (xCurrent, yCurrent, xNext, yNext);

			bP = new BouncePoint(getIntersection(paddleBounds,ballPath).getX(),getIntersection(paddleBounds,ballPath).getY(), BouncePoint.BounceDirection.FLIP_Y);
			return bP;
		}

		//intersectionB
		if (interB) {
			Line2D.Double paddleBounds = new Line2D.Double(this.getCurrentPaddleX() - radius, 
					this.getCurrentPaddleY(),
					this.getCurrentPaddleX() - radius,
					this.getCurrentPaddleY() + 5);

			Line2D.Double ballPath = new Line2D.Double (xCurrent, yCurrent, xNext, yNext);
			bP = new BouncePoint(getIntersection(paddleBounds,ballPath).getX(),getIntersection(paddleBounds,ballPath).getY(), BouncePoint.BounceDirection.FLIP_X);
			return bP;
		}


		//intersectionC
		if (interC) {
			Line2D.Double paddleBounds = new Line2D.Double(this.getCurrentPaddleX() + this.getCurrentPaddleLength() + radius, 
					this.getCurrentPaddleY(),
					this.getCurrentPaddleX() + this.getCurrentPaddleLength()+ radius,
					this.getCurrentPaddleY() + 5);

			Line2D.Double ballPath = new Line2D.Double (xCurrent, yCurrent, xNext, yNext);
			bP = new BouncePoint(getIntersection(paddleBounds,ballPath).getX(),getIntersection(paddleBounds,ballPath).getY(), BouncePoint.BounceDirection.FLIP_X);
			return bP;
		}

		//intersectionE
		if (interE) {
			Line2D.Double paddleBounds = new Line2D.Double(	this.getCurrentPaddleX(),
					this.getCurrentPaddleY() - radius,
					this.getCurrentPaddleX() - radius, 
					this.getCurrentPaddleY());

			Line2D.Double ballPath = new Line2D.Double (xCurrent, yCurrent, xNext, yNext);

			if (xCurrent > xNext) {
				bP = new BouncePoint(getIntersection(paddleBounds,ballPath).getX(),getIntersection(paddleBounds,ballPath).getY(), BouncePoint.BounceDirection.FLIP_Y);
			}else {
				bP = new BouncePoint(getIntersection(paddleBounds,ballPath).getX(),getIntersection(paddleBounds,ballPath).getY(),BouncePoint.BounceDirection.FLIP_X);
			}
			return bP;
		}

		//intersectionF
		if (interF) {
			Line2D.Double paddleBounds = new Line2D.Double(this.getCurrentPaddleX() + this.getCurrentPaddleLength() - radius,
					this.getCurrentPaddleY() - radius,
					this.getCurrentPaddleX() + this.getCurrentPaddleLength() + radius, 
					this.getCurrentPaddleY());



			Line2D.Double ballPath = new Line2D.Double (xCurrent, yCurrent, xNext, yNext);

			if (xCurrent > xNext) {
				bP = new BouncePoint(getIntersection(paddleBounds,ballPath).getX(),getIntersection(paddleBounds,ballPath).getY(), BouncePoint.BounceDirection.FLIP_X);
			}else {
				bP = new BouncePoint(getIntersection(paddleBounds,ballPath).getX(),getIntersection(paddleBounds,ballPath).getY(),BouncePoint.BounceDirection.FLIP_Y);
			}
			return bP;
		}
		return bP;
  }


  /**
   * !!!GEORGE!!! BouncePoint calculateBouncePointWall
   */
  // line 380 "../../../../../Block223States.ump"
   private BouncePoint calculateBouncePointWall(){
    int radius = Ball.BALL_DIAMETER/2;
		BouncePoint bP = null;

		//ball coords
		double xCurrent = this.getCurrentBallX();
		double yCurrent = this.getCurrentBallY();
		double xNext = xCurrent + this.getBallDirectionX();
		double yNext = yCurrent + this.getBallDirectionY();

		//sides rectangles
		Rectangle2D.Double rectA = new Rectangle2D.Double(0, 0, radius, Game.PLAY_AREA_SIDE);
		Rectangle2D.Double rectB = new Rectangle2D.Double(0, 0, Game.PLAY_AREA_SIDE, radius);
		Rectangle2D.Double rectC = new Rectangle2D.Double(Game.PLAY_AREA_SIDE - radius, 0, radius, Game.PLAY_AREA_SIDE);

		boolean interA = rectA.intersectsLine(xCurrent, yCurrent, xNext, yNext);
		boolean interB = rectB.intersectsLine(xCurrent, yCurrent, xNext, yNext);
		boolean interC = rectC.intersectsLine(xCurrent, yCurrent, xNext, yNext);

		if (!interA && !interB && !interC ) {
			return null;
		}

		if (interA && !interB ) {
			Line2D.Double wallBounds = new Line2D.Double(radius,
					0,
					radius,
					Game.PLAY_AREA_SIDE);

			Line2D.Double ballPath = new Line2D.Double (xCurrent, yCurrent, xNext, yNext);

			bP = new BouncePoint(getIntersection(wallBounds,ballPath).getX(),getIntersection(wallBounds,ballPath).getY(), BouncePoint.BounceDirection.FLIP_X);
			return bP;
		}

		if (interA && interB ) {

			bP = new BouncePoint(radius, radius, BouncePoint.BounceDirection.FLIP_BOTH);
			return bP;
		}

		if (interC && interB ) {

			bP = new BouncePoint(Game.PLAY_AREA_SIDE - radius, radius, BouncePoint.BounceDirection.FLIP_BOTH);
			return bP;
		}
		//intersectionB
		if (interB && !interA && !interC) {
			Line2D.Double wallBounds = new Line2D.Double(0, 
					radius,
					Game.PLAY_AREA_SIDE,
					radius);

			Line2D.Double ballPath = new Line2D.Double (xCurrent, yCurrent, xNext, yNext);
			bP = new BouncePoint(getIntersection(wallBounds,ballPath).getX(),getIntersection(wallBounds,ballPath).getY(), BouncePoint.BounceDirection.FLIP_Y);
			return bP;
		}
		//intersectionC
		if (interC && !interB) {
			Line2D.Double wallBounds = new Line2D.Double(Block.SIZE - radius, 
					0,
					Block.SIZE - radius,
					Block.SIZE);

			Line2D.Double ballPath = new Line2D.Double (xCurrent, yCurrent, xNext, yNext);
			bP = new BouncePoint(getIntersection(wallBounds,ballPath).getX(),getIntersection(wallBounds,ballPath).getY(), BouncePoint.BounceDirection.FLIP_X);
			return bP;
		}
		return bP;
  }


  /**
   * !!!Yannick!!! BouncePoint calculateBouncePointBlock
   */
  // line 452 "../../../../../Block223States.ump"
   private BouncePoint calculateBouncePointBlock(PlayedBlockAssignment block){
    int radius = Ball.BALL_DIAMETER/2;
		BouncePoint bP = null;

		//ball coords
		double xCurrent = this.getCurrentBallX();
		double yCurrent = this.getCurrentBallY();
		double xNext = xCurrent + this.getBallDirectionX();
		double yNext = yCurrent + this.getBallDirectionY();

		//Block rectangles
		Rectangle2D.Double rectA = new Rectangle2D.Double(block.getX(), block.getY() - radius, Block.SIZE, radius);
		Rectangle2D.Double rectB = new Rectangle2D.Double(block.getX() - radius, block.getY(), radius, Block.SIZE);
		Rectangle2D.Double rectC = new Rectangle2D.Double(block.getX() + Block.SIZE, block.getY(), radius, Block.SIZE);
		Rectangle2D.Double rectD = new Rectangle2D.Double(block.getX(), block.getY() + Block.SIZE, Block.SIZE, radius);
		Rectangle2D.Double rectE = new Rectangle2D.Double(block.getX() - radius, block.getY() - radius, radius, radius);
		Rectangle2D.Double rectF = new Rectangle2D.Double(block.getX() + Block.SIZE, block.getY() - radius, radius, radius);
		Rectangle2D.Double rectG = new Rectangle2D.Double(block.getX() - radius, block.getY() + Block.SIZE, radius, radius);
		Rectangle2D.Double rectH = new Rectangle2D.Double(block.getX() + Block.SIZE, block.getY() + Block.SIZE, radius, radius);

		//intersections
		boolean interA = rectA.intersectsLine(xCurrent, yCurrent, xNext, yNext);
		boolean interB = rectB.intersectsLine(xCurrent, yCurrent, xNext, yNext);
		boolean interC = rectC.intersectsLine(xCurrent, yCurrent, xNext, yNext);
		boolean interD = rectD.intersectsLine(xCurrent, yCurrent, xNext, yNext);
		boolean interE = rectE.intersectsLine(xCurrent, yCurrent, xNext, yNext);
		boolean interF = rectF.intersectsLine(xCurrent, yCurrent, xNext, yNext);
		boolean interG = rectG.intersectsLine(xCurrent, yCurrent, xNext, yNext);
		boolean interH = rectH.intersectsLine(xCurrent, yCurrent, xNext, yNext);

		//the ball does not intersect anything
		if (!interA && !interB && !interD && !interG && !interH && !interC && !interE && !interF) {
			return null;
		}

		//intersectionA
		if (interA) {
			Line2D.Double BlockBounds = new Line2D.Double(block.getX(),
					block.getY() - radius,
					block.getX() + Block.SIZE,
					block.getY() - radius);

			Line2D.Double ballPath = new Line2D.Double (xCurrent, yCurrent, xNext, yNext);

			bP = new BouncePoint(getIntersection(BlockBounds,ballPath).getX(),getIntersection(BlockBounds,ballPath).getY(), BouncePoint.BounceDirection.FLIP_Y);
			return bP;
		}

		//intersectionB
		if (interB) {
			Line2D.Double blockBounds = new Line2D.Double(block.getX() - radius, 
					block.getY(),
					block.getX() - radius,
					block.getY() + Block.SIZE);

			Line2D.Double ballPath = new Line2D.Double (xCurrent, yCurrent, xNext, yNext);
			bP = new BouncePoint(getIntersection(blockBounds,ballPath).getX(),getIntersection(blockBounds,ballPath).getY(), BouncePoint.BounceDirection.FLIP_X);
			return bP;
		}


		//intersectionC
		if (interC) {
			Line2D.Double blockBounds = new Line2D.Double(block.getX() + Block.SIZE + radius, 
					block.getY(),
					block.getX() + Block.SIZE + radius,
					block.getY() + Block.SIZE);

			Line2D.Double ballPath = new Line2D.Double (xCurrent, yCurrent, xNext, yNext);
			bP = new BouncePoint(getIntersection(blockBounds,ballPath).getX(),getIntersection(blockBounds,ballPath).getY(), BouncePoint.BounceDirection.FLIP_X);
			return bP;
		}

		//intersectionD
		if (interD) {
			Line2D.Double blockBounds = new Line2D.Double(block.getX(),
					block.getY() + Block.SIZE + radius,
					block.getX() + Block.SIZE,
					block.getY() + Block.SIZE + radius);

			Line2D.Double ballPath = new Line2D.Double (xCurrent, yCurrent, xNext, yNext);
			bP = new BouncePoint(getIntersection(blockBounds,ballPath).getX(),getIntersection(blockBounds,ballPath).getY(), BouncePoint.BounceDirection.FLIP_Y);
			return bP;
		}

		//intersectionE
		if (interE) {
			Line2D.Double blockBounds = new Line2D.Double(block.getX(),
					block.getY() - radius,
					block.getX() - radius, 
					block.getY());

			Line2D.Double ballPath = new Line2D.Double (xCurrent, yCurrent, xNext, yNext);

			if (xCurrent > xNext) {
				bP = new BouncePoint(getIntersection(blockBounds,ballPath).getX(),getIntersection(blockBounds,ballPath).getY(), BouncePoint.BounceDirection.FLIP_Y);
			}else {
				bP = new BouncePoint(getIntersection(blockBounds,ballPath).getX(),getIntersection(blockBounds,ballPath).getY(),BouncePoint.BounceDirection.FLIP_X);
			}
			return bP;
		}

		//intersectionF
		if (interF) {
			Line2D.Double blockBounds = new Line2D.Double(block.getX() + Block.SIZE ,
					block.getY() - radius,
					block.getX() +Block.SIZE + radius,
					block.getY() + Block.SIZE);

			Line2D.Double ballPath = new Line2D.Double (xCurrent, yCurrent, xNext, yNext);

			if (xCurrent > xNext) {
				bP = new BouncePoint(getIntersection(blockBounds,ballPath).getX(),getIntersection(blockBounds,ballPath).getY(), BouncePoint.BounceDirection.FLIP_X);
			}else {
				bP = new BouncePoint(getIntersection(blockBounds,ballPath).getX(),getIntersection(blockBounds,ballPath).getY(),BouncePoint.BounceDirection.FLIP_Y);
			}
			return bP;
		}

		//intersectionG
		if (interG) {
			Line2D.Double blockBounds = new Line2D.Double(block.getX() - radius,
					block.getY() + Block.SIZE,
					block.getX(),
					block.getY() + Block.SIZE + radius);

			Line2D.Double ballPath = new Line2D.Double (xCurrent, yCurrent, xNext, yNext);

			if (xCurrent > xNext) {
				bP = new BouncePoint(getIntersection(blockBounds,ballPath).getX(),getIntersection(blockBounds,ballPath).getY(), BouncePoint.BounceDirection.FLIP_Y);
			}else {
				bP = new BouncePoint(getIntersection(blockBounds,ballPath).getX(),getIntersection(blockBounds,ballPath).getY(),BouncePoint.BounceDirection.FLIP_X);
			}
			return bP;
		}

		//intersectionH
		if (interH) {
			Line2D.Double blockBounds = new Line2D.Double(block.getX() + Block.SIZE + radius,
					block.getY() + Block.SIZE,
					block.getX(),
					block.getY() + Block.SIZE + radius);

			Line2D.Double ballPath = new Line2D.Double (xCurrent, yCurrent, xNext, yNext);

			if (xCurrent > xNext) {
				bP = new BouncePoint(getIntersection(blockBounds,ballPath).getX(),getIntersection(blockBounds,ballPath).getY(), BouncePoint.BounceDirection.FLIP_X);
			}else {
				bP = new BouncePoint(getIntersection(blockBounds,ballPath).getX(),getIntersection(blockBounds,ballPath).getY(),BouncePoint.BounceDirection.FLIP_Y);
			}
			return bP;
		}
		return bP;
  }


  /**
   * !!!GEORGE!!! bounceBall
   */
  // line 622 "../../../../../Block223States.ump"
   private void bounceBall(){
    BouncePoint bp = getBounce();
			   
		if(bp.getDirection().equals(BouncePoint.BounceDirection.FLIP_Y)) {

			double newBallDirectionX;
			double newBallDirectionY;

			if (ballDirectionX * ballDirectionY < 0) {
				newBallDirectionY = ballDirectionY *-1;
				newBallDirectionX = ballDirectionX + newBallDirectionY*0.1; 
			}
			else {
				newBallDirectionX = ballDirectionX + ballDirectionY*0.1;
				newBallDirectionY = ballDirectionY *-1;
			}			   
			setCurrentBallY(bp.getY());
			setCurrentBallX(bp.getX());			   
			setBallDirectionX(newBallDirectionX);
			setBallDirectionY(newBallDirectionY);

		}else if(bp.getDirection().equals(BouncePoint.BounceDirection.FLIP_X)) {

			double newBallDirectionX;
			double newBallDirectionY;

			if (ballDirectionX * ballDirectionY < 0) {
				newBallDirectionX = ballDirectionX *-1;
				newBallDirectionY = ballDirectionY + newBallDirectionX*0.1; 
			}
			else {
				newBallDirectionY = ballDirectionY + ballDirectionX*0.1;
				newBallDirectionX = ballDirectionX *-1;
			}
			setCurrentBallY(bp.getY());
			setCurrentBallX(bp.getX());
			setBallDirectionX(newBallDirectionX);
			setBallDirectionY(newBallDirectionY);

		}else if(bp.getDirection().equals(BouncePoint.BounceDirection.FLIP_BOTH)) {
			double newBallDirectionX = ballDirectionX *-1;
			double newBallDirectionY = ballDirectionY *-1;
			setCurrentBallY(bp.getY());
			setCurrentBallX(bp.getX());
			setBallDirectionX(newBallDirectionX);
			setBallDirectionY(newBallDirectionY);
		}
		if(bp.hasHitBlock()) {
			bounce.setHitBlock(null);
		}
  }


  /**
   * Yanick!!!
   */
  // line 675 "../../../../../Block223States.ump"
   private boolean isCloser(BouncePoint bp, BouncePoint bounce){
    if(bp == null) {
			return false;
		}
		if(bounce == null) {
			return true;
		}
		double distance =Math.sqrt(Math.pow(Math.abs(currentBallX-bp.getX()),2)+Math.pow(Math.abs(currentBallY-bp.getY()),2));
     double bounceDistance = Math.sqrt(Math.pow(Math.abs(currentBallX - bounce.getX()), 2) + Math.pow(Math.abs(currentBallY - bounce.getY()), 2));
     return distance <= bounceDistance;

  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "score" + ":" + getScore()+ "," +
            "lives" + ":" + getLives()+ "," +
            "currentLevel" + ":" + getCurrentLevel()+ "," +
            "waitTime" + ":" + getWaitTime()+ "," +
            "playername" + ":" + getPlayername()+ "," +
            "ballDirectionX" + ":" + getBallDirectionX()+ "," +
            "ballDirectionY" + ":" + getBallDirectionY()+ "," +
            "currentBallX" + ":" + getCurrentBallX()+ "," +
            "currentBallY" + ":" + getCurrentBallY()+ "," +
            "currentPaddleLength" + ":" + getCurrentPaddleLength()+ "," +
            "currentPaddleX" + ":" + getCurrentPaddleX()+ "," +
            "currentPaddleY" + ":" + getCurrentPaddleY()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "player = "+(getPlayer()!=null?Integer.toHexString(System.identityHashCode(getPlayer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "bounce = "+(getBounce()!=null?Integer.toHexString(System.identityHashCode(getBounce())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "block223 = "+(getBlock223()!=null?Integer.toHexString(System.identityHashCode(getBlock223())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 110 "../../../../../Block223Persistence.ump"
  private static final long serialVersionUID = 8597675110221231714L ;

// line 606 "../../../../../Block223States.ump"
  private static Point2D getIntersection (final Line2D.Double line1, final Line2D.Double line2) 
  {
    final double x1,y1, x2,y2, x3,y3, x4,y4;
		x1 = line1.x1; y1 = line1.y1; x2 = line1.x2; y2 = line1.y2;
		x3 = line2.x1; y3 = line2.y1; x4 = line2.x2; y4 = line2.y2;
		final double x = ((x2 - x1) * (x3*y4 - x4*y3) - (x4 - x3)*(x1*y2 - x2*y1)) /
				((x1 - x2)*(y3 - y4) - (y1 - y2)*(x3 - x4));
		final double y = ((y3 - y4)*(x1*y2 - x2*y1) - (y1 - y2)*(x3*y4 - x4*y3)) /
				(
						(x1 - x2)*(y3 - y4) - (y1 - y2)*(x3 - x4)
						);
		return new Point2D.Double(x, y);
  }

  
}
