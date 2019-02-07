/**
 * DecisionMaker.java
 *
 * Name: Aleksandar Ilic
 * NetID: ailic3
 *
 * Homework #4
 * Group: 20
 * CS 342, FALL 2018, CRN: 37162
 * November 14, 2018
 *
 * This Interface is intended to function as a DecisionMaker for a Character
 * in an "adventure" type game. The class that implements this interface must
 * provide a way to obtain a move for the Character to perform.
*/

/*-----------------------------------------------------------------------------
 * Interface: DecisionMaker
 * 
 * This Interface is intended to function as a DecisionMaker for a Character
 * in an "adventure" type game. The class that implements this interface must
 * provide a way to obtain a move for the Character to perform.
 * 
 * Method(s):
 *      - (Public) Move -> getMove(): Obtains the characters next desired
 *                                    move.
*/
public interface DecisionMaker
{
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
     * the game.
    */
    public Move getMove(Character c, Place p);
    
}//end DecisionMaker