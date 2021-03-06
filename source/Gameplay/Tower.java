/** Description: This is the tower class that contains all the game play materials for generally towers. It includes its
  * range, speed, amount of rays it can shoot, damage, range, and its neutralizing ray's colour.
  * @author Kareem Golaub and Peter Huang
  * @version 1.5 June 12 2014
  */

package Gameplay;
import java.awt.*;
import GameState.StageOneState;
public class Tower
{  
  /** airID - int storing the value of the air ID.
   */
  int airID;
  /** rangeRec - Reference variable for rectangle, storing a rectangle which represents the shape of a tower's range.
   */
  Rectangle rangeRec;
  /** towerRange - int storing the magnitude of the tower's range.
   */
  int towerRange = 200;  
  /** shooting - boolean storing whether the first ray from a tower is shooting at the moment or not. It is instantiated to false.
   */
  boolean shooting = false;
  /** shooting2 - boolean storing whether the second ray from a tower is shooting at the moment or not. It is instantiated to false.
   */
  boolean shooting2 = false;
  /** shooting3 - boolean storing whether the third ray from a tower is shooting at the moment or not. It is instantiated to false.
   */
  boolean shooting3 = false;
  /** mobBeingShot - int storing whehter the mob is being shot or not.
   */
  int mobBeingShot = -1;
  /** mobBeingShot2 - int storing whehter the mob is being shot or not.
   */
  int mobBeingShot2 = -1;
  /** mobBeingShot3 - int storing whehter the mob is being shot or not.
   */
  int mobBeingShot3 = -1;
  /** x - int variable storing the x position.
   */
  int x;
  /** y - int variable storing the x position.
   */
  int y;
  /** width - int variable storing the width.
   */
  int width;
  /** height - int variable storing the height.
   */
  int height;
  /** shootFPS - int variable storing the shoot frames per second, speed of the game, as shootFrame restarts when the loop hits shootFPS's value.
   */    
  int shootFPS = 60;
  /** shootFrame - int variable storing the first shoot frame.
   */    
  int shootFrame = 0;
  /** shootFrame - int variable storing the second shoot frame.
   */    
  int shootFrame2 = 0;
  /** shootFrame - int variable storing the third shoot frame.
   */    
  int shootFrame3 = 0;
  /** damage - int variable storing damage that the tower deals.
   */    
  public int damage;
  
  /** This is the constructor, x, y, width, height, airID (type of tower) is passed in and the information is updated
    * within this class. It will set the correct range and damage of the tower you have selected with your click and
    * drop from the store.
    * @param x int, x position of tower.
    * @param y int, y position of tower.
    * @param width int, width of tower.
    * @param height int, height of tower.
    * @param airID int, type of tower the mouse is holding and about to be clicked and dropped on a tile.
   */
  public Tower (int x, int y, int width, int height, int airID)
  {
    this.airID = airID;
    this.x = x;
    this.y = y; 
    this.width = width;
    this.height = height;
    if (airID == Value.airNeutrophil)
    {
      towerRange = 600;
      damage = 2;
    }
    else if (airID == Value.airNaturalK)
    {
      towerRange = 300;
      damage = 7;
    }
    else if (airID == Value.airEosinophil || airID == Value.airBasophil)
    {
      towerRange = 180;
      damage = 3;
    }
    else if (airID == Value.airTCell)
    {
      towerRange = 60;
      damage = 0;
    }
    else
    {
      towerRange = 120;
      damage = 5;
    }
    rangeRec = new Rectangle (x - (towerRange/2), y - (towerRange/2), width + towerRange, height + towerRange);
  }
  
  /** This method is the physics of the towers, it contains the mechanics of each individual tower dealing damage.
   *  Note how BCell has three shooting (shooting2 and shooting3) as it has three rays when it shoots while others 
   * only have one. Damage is calculated here!
   */
  public void process()
  {
    if (airID == Value.airTCell) {
      
      for (int x = 0; x < StageOneState.map.block.length; x++) {
        for (int y = 0; y < StageOneState.map.block[0].length; y++) {
          if (rangeRec.intersects(StageOneState.map.block[x][y]) && StageOneState.map.block[x][y].tower != null && StageOneState.map.block[x][y].airID != Value.airNaturalK && StageOneState.map.block[x][y].airID != Value.airTCell){
            StageOneState.map.block[x][y].tower.damage = 5;
          }
        }
      }
    }
    else if (airID == Value.airBCell) {
      
      if (mobBeingShot != -1 && rangeRec.intersects(StageOneState.mobs[mobBeingShot]))
        shooting = true;
      else
        shooting = false;
      
      if (mobBeingShot2 != -1 && rangeRec.intersects(StageOneState.mobs[mobBeingShot2]))
        shooting2 = true;
      else
        shooting2 = false;
      
      if (mobBeingShot3 != -1 && rangeRec.intersects(StageOneState.mobs[mobBeingShot3]))
        shooting3 = true;
      else
        shooting3 = false;
      
      if (!shooting)
      {
        for (int i = 0; i < StageOneState.mobs.length; i++) {
          if (StageOneState.mobs[i].inGame && i != mobBeingShot2 && i != mobBeingShot3) {
            if (rangeRec.intersects(StageOneState.mobs[i])) {
              shooting = true;
              mobBeingShot = i;
              break;
            }
          }
        }
      }
      if (!shooting2)
      {
        for (int i = 0; i < StageOneState.mobs.length; i++) {
          if (StageOneState.mobs[i].inGame && i != mobBeingShot && i != mobBeingShot3) {
            if (rangeRec.intersects(StageOneState.mobs[i])) {
              shooting = true;
              mobBeingShot2 = i;
              break;
            }
          }
        }
      }
      if (!shooting3)
      {
        for (int i = 0; i < StageOneState.mobs.length; i++) {
          if (StageOneState.mobs[i].inGame && i != mobBeingShot && i != mobBeingShot2) {
            if (rangeRec.intersects(StageOneState.mobs[i])) {
              shooting = true;
              mobBeingShot3 = i;
              break;
            }
          }
        }
      }
    }
    else
    {
      // checks if mobBeingShot is default value or if its still intersecting the mob it was shooting before
      if (mobBeingShot != -1 && rangeRec.intersects(StageOneState.mobs[mobBeingShot]))
        shooting = true;
      else
        shooting = false;
      
      if(!shooting)
      {
        //each time mob passes it will check all of the mobs in the rectangle and shoot 1 of them
        if(airID == Value.airLaserTower || airID == Value.airNeutrophil || airID == Value.airEosinophil || airID == Value.airNaturalK || airID == Value.airBasophil)/*|| OR whatever is the name of your tower*/ {    
          for (int i = 0; i < StageOneState.mobs.length; i++) {
            if (StageOneState.mobs[i].inGame) {
              if (rangeRec.intersects(StageOneState.mobs[i])) {
                shooting = true;
                mobBeingShot = i;
                break;
              }
            }
          }
        }
      }
    }
    
    if (airID != Value.airTCell)
    {
      if (shooting) {
        if(shootFrame >= shootFPS && StageOneState.mobs[mobBeingShot].health > 0)
        {
          if ((StageOneState.mobs[mobBeingShot].mobID == Value.parasite && airID == Value.airEosinophil) || (StageOneState.mobs[mobBeingShot].mobID == Value.virus && airID == Value.airBasophil))
            StageOneState.mobs[mobBeingShot].loseHealth(damage+5);
          else
            StageOneState.mobs[mobBeingShot].loseHealth(damage);
          shootFrame = 0;
        }
        else
          shootFrame++;
        
        if(StageOneState.mobs[mobBeingShot].isDead())
        {
          shooting = false;
          mobBeingShot = -1;
        }
      }
      if (airID == Value.airBCell)
      {
        if (shooting2) {
          if(shootFrame2 >= shootFPS && StageOneState.mobs[mobBeingShot2].health > 0)
          {
              StageOneState.mobs[mobBeingShot2].loseHealth(damage);
            shootFrame2 = 0;
          }
          else
            shootFrame2++;
          
          if(StageOneState.mobs[mobBeingShot2].isDead())
          {
            shooting = false;
            mobBeingShot2 = -1;
          }
        }
        
        if (shooting3) {
          if(shootFrame3 >= shootFPS && StageOneState.mobs[mobBeingShot3].health > 0)
          {
              StageOneState.mobs[mobBeingShot3].loseHealth(damage);
            shootFrame3 = 0;
          }
          else
            shootFrame3++;
          
          if(StageOneState.mobs[mobBeingShot3].isDead())
          {
            shooting = false;
            mobBeingShot3 = -1;
          }
        }
      }
    }
  }
  
  /** This method draws the tower (airID of tileset_air) onto the game board.
    * @param g Graphics reference variable
   */
  public void draw(Graphics g)
  {
    g.drawImage(StageOneState.tileset_air[airID], x, y, width, height, null);
  }
  
  /** This method contains the fighting physics or mechanics of towers. It displays the ray that the tower is shooting.
    * @param g Graphics reference variable
   */
  public void fight(Graphics g)
  {
    if (shooting && airID != Value.airTCell)
    {
      if (airID == Value.airNaturalK)
        g.setColor (new Color (181, 141, 192));
      else if (airID == Value.airNeutrophil)
        g.setColor (new Color (147, 255, 155));
      else if (airID == Value.airEosinophil)
        g.setColor (new Color (255, 187, 6));
      else if (airID == Value.airBasophil)
        g.setColor (new Color(140, 140, 103));
      else //airID == Value.airLaserTower
        g.setColor (Color.YELLOW);
      g.drawLine(x + width/2, y + height/2, StageOneState.mobs[mobBeingShot].x + StageOneState.mobs[mobBeingShot].width/2, StageOneState.mobs[mobBeingShot].y + StageOneState.mobs[mobBeingShot].height/2);
    }
    if (shooting2 && airID == Value.airBCell)
    {
      g.drawLine(x + width/2, y + height/2, StageOneState.mobs[mobBeingShot2].x + StageOneState.mobs[mobBeingShot2].width/2, StageOneState.mobs[mobBeingShot2].y + StageOneState.mobs[mobBeingShot2].height/2);
    }
    if (shooting3 && airID == Value.airBCell)
    {
      g.drawLine(x + width/2, y + height/2, StageOneState.mobs[mobBeingShot3].x + StageOneState.mobs[mobBeingShot3].width/2, StageOneState.mobs[mobBeingShot3].y + StageOneState.mobs[mobBeingShot3].height/2);   
    }
  }
}