/**
 * AI.java
 *
 * Name: Aleksandar Ilic
 * NetID: ailic3
 *
 * Homework #4
 * Group: 20
 * CS 342, FALL 2018, CRN: 37162
 * November 14, 2018
 *
 * This class is intended to function as a AI for a Character, which performs
 * actions for the Character. The AI randomly selects what move to make next.
*/

import java.io.*;
import java.util.*;

/*-----------------------------------------------------------------------------
 * Class: AI
 *
 * This class is intended to function as a AI for a Character, which performs
 * actions for the Character.
 *
 * Attribute(s):
 *      - None
 *      
 * Method(s):
 *      - (Public) UI(): Constructor
 *      - (Public) Move -> getMove(): Randomly Obtains the Characters next
 *                                    move.
*/
public class AI 
    implements DecisionMaker
{
    protected ArrayList<String> actions = null;
    protected Random randNumGen = null;

    /*-------------------------------------------------------------------------
     * Constructor: AI()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - None
     *
     * Default constructor for the AI class. Instantiates the random number
     * generator and set of valid moves a Character can make.
    */
    public AI()
    {
        // Instantiates the Action List and Random NUmber Generator
        this.randNumGen = new Random();
        this.actions = new ArrayList<String>();

        // Initialize the valid set of actions
        this.actions.add("GET");
        this.actions.add("DROP");
        this.actions.add("USE");
        this.actions.add("GO");
        this.actions.add("WAIT");
        this.actions.add("EAT");
        this.actions.add("HEAL");
    }//end Constructor


    /*-------------------------------------------------------------------------
     * Function: getMove()
     *
     * Parameter(s):
     *      - Chaaracter -> c: Character object.
     *      - Place -> p: Place object of the Character's current Place.
     *
     * Return:
     *      - Move: Move the Character will perform.
     *
     * This funciton obtains the next move a Character will make when playing
     * the game, randomly.
    */
    public Move getMove(Character c, Place p)
    {
        int ndx = 0;    // Index of a possible move to make
        int candx = 0;  // Index of a Character owned Artifact
        int pandx = 0;  // Index of a Place Artifact
        int dndx = 0;   // Index of a Direction

        // Obtains random Artifacts and Directions
        Artifact randCharArtifact = c.getRandomArtifact();
        Artifact randPlaceArtifact = p.getRandomArtifact();
        Direction randPlaceDirection = p.getRandomDirection();
        Character randPlaceCharacter = p.getRandomCharacter();

        // Decides on what action to take
        ndx = randNumGen.nextInt(this.actions.size());
        String action = actions.get(ndx);
        
        // Attempts to derive a move, provided all necessary components
        // (Artifact and/or Direction also exist, if needed).
        while (true) {

            // Gets an Artifact from the current Place
            if (action.equals("GET") && randPlaceArtifact != null) {
                return new Go_Get(c, p, randPlaceArtifact.name());
            }
            // Drops an Artifact onto the current Place
            else if (action.equals("DROP") && randCharArtifact != null) {
                return new Go_Drop(c, p, randCharArtifact.name());
            }
            // Uses an Artifact in the current Place
            else if (action.equals("USE") && randCharArtifact != null) {
                return new Go_Use(c, p, randCharArtifact.name());
            }
            // Go's in a particular Direction
            else if (action.equals("GO") && randPlaceDirection != null) {
                return new Go_Direction(c, p, randPlaceDirection.toString());
            }
            // Skips turn
            else if (action.equals("WAIT")) {
                return new Go_Wait();
            }
            // Eats food
            else if (action.equals("EAT") && randCharArtifact != null) {
                return new Go_Eat(c, randCharArtifact.name());
            }
            // Heals itself
            else if (action.equals("HEAL") && randPlaceCharacter != null &&
                     randCharArtifact != null) 
            {
                return new Go_Heal(c, randCharArtifact.name());
            }

            // Decides on what action to take
            ndx = randNumGen.nextInt(this.actions.size());
            action = actions.get(ndx);
        }
        
    }//end getMove()

}//end UI