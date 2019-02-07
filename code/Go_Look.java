/**
 * Go_Look.java
 *
 * Name: Luis Enrique Love
 * NetID: llove3
 *
 * Homework #4
 * Group: 20
 * CS 342, FALL 2018, CRN: 37162
 * November 14, 2018
 *
 * This class is intended to function as a "display what Place I'm currently
 * in" action performed by a Character in an "adventure" type game.
*/

/*-----------------------------------------------------------------------------
 * Class: Go_Look
 *
 * This class is intended to function as a "display my inventory" action.
 *
 * Attribute(s):
 *      - (Private) Place -> place: Place to act upon.
 *      
 * Method(s):
 *      - (Public) Go_Drop): Constructor
 *      - (Public) void -> execute(): Executes move.
*/
public class Go_Look
    extends Move
{
    Place place = null;
    Character character = null;

    /*-------------------------------------------------------------------------
     * Constructor: Go_Look()
     *
     * Parameter(s):
     *      - Character -> c: Character to act upon.
     *
     * Return:
     *      - None
     *
     * Constructs a new instance of the Go_Look class, assigning the
     * various objects to be acted upon.
    */
    public Go_Look(Character c, Place p)
    {
        this.place = p;
        this.character = c;
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
     * This function displays the current Place information.
    */
    public void execute()
    {
        this.place.display(this.character);

        return;
    }//end execute()

}//end Go_Look