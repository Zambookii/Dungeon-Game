/**
 * Go_Wait.java
 *
 * Name: Luis Enrique Love
 * NetID: llove3
 *
 * Homework #4
 * Group: 20
 * CS 342, FALL 2018, CRN: 37162
 * November 14, 2018
 *
 * This class is intended to function as a "Use an Artifact" action performed
 * by a Character in an "adventure" type game.
*/

/*-----------------------------------------------------------------------------
 * Class: Go_Direction
 *
 * This class is intended to function as a "move in a given direction" action.
 *
 * Attribute(s):
 *      - (Private) Character -> character: Character to act upon.
 *      - (Private) Place -> place: Current Place of the Character.
 *      - (Private) String -> direction: Executes obtained move.
 *      
 * Method(s):
 *      - (Public) Go_Direction(): Constructor
 *      - (Public) void -> execute(): Executes move.
*/
public class Go_Wait
    extends Move
{
    public Go_Wait() {}//end Constructor

    public void execute()
    {
        return;
    }//end execute()
}