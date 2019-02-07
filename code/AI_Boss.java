/**
 * AI_Boss.java
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

public class AI_Boss
    extends AI
{
    public AI_Boss()
    {
        super.randNumGen = new Random();
        super.actions = new ArrayList<String>();
        
        super.actions.add("GO");
        super.actions.add("HEAL");
        super.actions.add("ATTACK");
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
        Character randPlaceCharacter = p.getRandomCharacter();

        // Decides on what action to take
        ndx = randNumGen.nextInt(super.actions.size());
        String action = super.actions.get(ndx);
        
        try {
            if (action.equals("GO")) {
                Place nextPlace = Place.getRandomPlace();
                p.removeCharacter(c);
                c.setCurrentPlace(nextPlace);
                nextPlace.addCharacter(c);
            }
            // Heals itself
            else if (action.equals("HEAL")) {
                c.heal(100);
            }
            // Attacks another Player
            else if (action.equals("ATTACK")) {
                if (randPlaceCharacter != null) {
                    randPlaceCharacter.heal(-100);
                }
            }
        }
        catch(CharDeathException e) {
            c.displayOutputMessage(e.getMessage());
        }
        
        return new Go_Wait();
    }//end getMove()
}

