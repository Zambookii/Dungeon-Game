/**
 * Go_Quit.java
 *
 * Name: Luis Enrique Love
 * NetID: llove3
 *
 * Homework #4
 * Group: 20
 * CS 342, FALL 2018, CRN: 37162
 * November 14, 2018
 *
 * This class is intended to function as an "I quit the game" action performed
 * by a character in an "adventure" type game.
*/

/*-----------------------------------------------------------------------------
 * Class: Go_Quit
 *
 * This class is intended to function as a "I quit the game" action.
 *
 * Attribute(s):
 *      - (Private) Character -> character: Character to act upon.
 *      - (Private) Place -> place: Current Place of the Character.
 *      
 * Method(s):
 *      - (Public) Go_Drop): Constructor
 *      - (Public) void -> execute(): Executes move.
*/
public class Go_Quit
    extends Move
{
    Character character = null;
    Place place = null;

    /*-------------------------------------------------------------------------
     * Constructor: Go_Quit()
     *
     * Parameter(s):
     *      - Character -> c: Character to act upon.
     *      - Place -> p: Current Place of the Character.
     *
     * Return:
     *      - None
     *
     * Constructs a new instance of the Go_Quit class, assigning the
     * various objects to be acted upon.
    */
    public Go_Quit(Character c, Place p)
    {
        this.character = c;
        this.place = p;
    }//end Constructor

    
    /*-------------------------------------------------------------------------
     * Function: execute()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - None
     *
     * This function quits the game for a Character.
    */
    public void execute()
    {
        // Character can no longer play the game
        this.character.quit();

        // Removes the Character from its current Place
        this.place.removeCharacter(this.character);

        return;
    }//end execute()

}//end Go_Quit