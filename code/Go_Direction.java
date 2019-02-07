/**
 * Go_Direction.java
 *
 * Name: Luis Enrique Love
 * NetID: llove3
 *
 * Homework #4
 * Group: 20
 * CS 342, FALL 2018, CRN: 37162
 * November 14, 2018
 *
 * This class is intended to function as a "move in a given direction" action
 * perforamed by a Character in an "adventure" type game.
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
public class Go_Direction
    extends Move
{
    Character character = null;
    Place place = null;
    String direction = null;

    /*-------------------------------------------------------------------------
     * Constructor: Go_Direction()
     *
     * Parameter(s):
     *      - Character -> c: Character to act upon.
     *      - Place -> p: Current Place of the Character.
     *      - String -> d: Direction of travel.
     *
     * Return:
     *      - None
     *
     * Constructs a new instance of the Go_Direction class, assigning the
     * various objects to be acted upon.
    */
    public Go_Direction(Character c, Place p, String d)
    {
        this.character = c;
        this.place = p;
        this.direction = d;
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
     * This function moves the Character in the given direction.
    */
    public void execute()
        throws InvalidDirectionException, LockedDoorException
    {
        // Removes the Character from the current Place
        this.place.removeCharacter(this.character);

        // Gets the destination Place
        Place nextPlace = null;
        try {
            nextPlace = this.place.followDirection(this.direction);
        }
        catch (InvalidDirectionException e) {
            this.place.addCharacter(this.character);
            throw new InvalidDirectionException(e.getMessage());
        }
        catch (LockedDoorException e) {
            this.place.addCharacter(this.character);
            throw new LockedDoorException(e.getMessage());
        }

        // Determines if the Exit has been reached
        if (nextPlace.isExit()) {
            this.character.displayOutputMessage(String.format("%s reached the Exit!\n", 
                                             this.character.name()));

            // Character can no longer play the game
            this.character.quit();
        }

        // Add the character to the Place
        this.character.setCurrentPlace(nextPlace);

        return;
    }//end execute()

}//end Go_Direction