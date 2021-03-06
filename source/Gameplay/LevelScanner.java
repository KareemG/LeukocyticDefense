/** This class is reads from a .psq file which contains the map of each level. The information from the level file 
  * will be stored in the block array.
  * @author Peter Huang, modified by Kareem Golaub.
  * @version 1.0 May 17th, 2014
  */

package Gameplay;
import java.io.*;
import java.util.*; // for scanner
import GameState.StageOneState;

public class LevelScanner
{
  /**
   * This method loads the file, then reads and stores the variables.
   * @param loadSave - This is the directory of where the level map file is stored.
   * @param sc - Reference variable for scanner to read the file
   * @param e - reference variable for InputMismatchException.
   */
  public void loadSave(String loadPath)
  {
    try
    {
      //reads in
      Scanner sc = new Scanner (getClass().getResourceAsStream(loadPath));
      while (sc.hasNext())
      {
        StageOneState.killsToWin = sc.nextInt();
        
        /**
         * Nested for loop storing the 2D array of blocks, assigning each block a ground tile value.*/
        for (int y = 0; y < StageOneState.map.block.length; y++)
        {
          for (int x = 0; x < StageOneState.map.block[0].length; x++)
          {
            StageOneState.map.block[y][x].groundID = sc.nextInt();
            StageOneState.map.block[y][x].tower = null;
          }
        }
        
        /**
         * Nested for loop storing the 2D array of blocks, assigning each block an air tile value.*/
        for (int y = 0; y < StageOneState.map.block.length; y++)
        {
          for (int x = 0; x < StageOneState.map.block[0].length; x++)
          {
            StageOneState.map.block[y][x].airID = sc.nextInt();
          }
        }
      }
      sc.close();
    }
    catch (InputMismatchException e) //if weird things happen change back to Exception
    {
      e.printStackTrace();
    }
  }
}