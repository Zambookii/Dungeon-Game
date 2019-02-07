/**
 * NPC.java
 *
 * Name: Aleksandar Ilic
 * NetID: ailic3
 *
 * Homework #4
 * Group: 20
 * CS 342, FALL 2018, CRN: 37162
 * November 14, 2018
 *
 * This class is intended to function as a NPC player in an "adventure" type
 * game. The NPC class extends the Character class.
*/

import java.io.*;
import java.util.*;

/*-----------------------------------------------------------------------------
 * Class: NPC
 *
 * This class is intended to function as a NPC player in an "adventure" type
 * game.
 *
 * Attribute(s):
 *      - None
 *
 * Method(s):
 *      - (Public) NPC(): Constructors
*/
public class NPC
    extends Character
{
    /*-------------------------------------------------------------------------
     * Constructor: Character()
     *
     * Parameter(s):
     *      - Scanner -> dataFile: Scanner containing data file to create 
     *                             the NPC.
     *      - int -> version: Version of the GDF File.
     *
     * Return:
     *      - None
     *
     * Constructs a new instance of the NPC class, requiring the user to
     * provide a Scanner object which contains a file to create the NPC.
     * The NPC is added to a collection of created Characters by the 
     * class.
    */
    public NPC(Scanner scanner, int version)
        throws InvalidParseException
    {
        // Call Character class constructor
        super(scanner, version);

        if (version <= 50) {
            // Assign AI decision maker
            super.decisionMaker = new AI();
        }
        
    }//end Constructor


    /*-------------------------------------------------------------------------
     * Constructor: NPC()
     *
     * Parameter(s):
     *      - int -> ID: Unique Character identifier.
     *      - String -> name: Name of the Character.
     *      - String -> description: Description of the Character.
     *      - int -> version: Version of the GDF File.
     *
     * Return:
     *      - None
     *
     * Constructs a new instance of the NPC class, requiring the user to
     * provide the ID, name, and description of the NPC. The NPC is
     * added to a collection of created Characters by the class.
    */
    public NPC(int ID, String name, String description, int version)
    {
        // Call Character class constructor
        super(ID, name, description, version);

        // Assign AI decision maker
        super.decisionMaker = new AI();
    }//end Constructor

}//end NPC