/**
 * Direction.java
 *
 * Name: Jesse Palasz
 * NetID: jpalas3
 *
 * Homework #4
 * Group: 20
 * CS 342, FALL 2018, CRN: 37162
 * November 14, 2018
 *
 * This class is intended to function as a Direction / entry way in an
 * "adventure" type Game. A Direction links 2 distinct Places in the Game
 * via an entry way, which can either be locked of unlocked. In order to
 * travel from the current Place to the destination Place, a direction of
 * travel must be provided that matches the Direction object's direction of
 * travel; otherwise, the user remains in the current Place.
*/

import java.io.*;
import java.util.*;

/*-----------------------------------------------------------------------------
 * Class: Direction
 *
 * This class is intended to function as a Direction / entry way in an
 * "adventure" type game, linking two distinct Places within
 * the game.
 *
 * Attribute(s):
 *      - (Private) int -> ID: Unique Direction identifier.
 *      - (Private) dirType -> direction: Direction of travel.
 *      - (Private) Place -> from: Starting Place.
 *      - (Private) Place -> to: Destination Place.
 *      - (Private) boolean -> locked: Indicates if the Destination Place is
 *                                     locked.
 *      - (Private) int -> lockPattern: Lock pattern on the Destination Place.
 *      - (Private) int -> version: GDF File version.
 *
 * Method(s):
 *      - (Public) Direction(): Constructor
 *      - (Private) int -> useKey(): Uses Artifact on the Destination Place.
 *      - (Public) Place -> follow(): Traverses to the Destination Place.
 *      - (Public) void -> print(): Prints debug info.
 *      - (Public) boolean -> match(): Determines if the direction of travel
 *                                     matches the Direction object.
 *      - (Public) void -> lock(): Locks the entrance to the Destination Place.
 *      - (Public) void -> unlock(): Unlocks the entrance to the Destination
 *                                   Place.
 *      - (Public) boolean -> isLocked(): Determines if the entrance way to the
 *                                        Destination Place is locked.
 *      - (Public) String -> toString(): String representation of Destination
 *                                       object.
*/
public class Direction
{
    /*-------------------------------------------------------------------------
     * Enum: dirType
     *
     * This enum represents a series of valid directions that can be traversed.
     * Valid directions include N, S, E, W, U, D, NW, NE, NNW, NNE, SW, SE, SSW
     * SSE, ENE, ESE, WNW, WSW.
     * 
     * Attribute(s):
     *      - (Private) String -> text: Fullname of the direction.
     *      - (Private) String -> abreviation: Abreviation of the direction.
     *
     * Method(s):
     *      - (Public) dirType(): Constructor
     *      - (Public) String -> toString(): Returns the fullname of a
     *                                       direction.
     *      - (Public) boolean -> match(): Indicates if a provided direction
     *                                     String matches a valid direction.
    */
    private enum dirType 
    {
        // Directions
        NONE("NONE", "NONE"),
        N("NORTH", "N"), S("SOUTH", "S"), E("EAST", "E"), W("WEST", "W"), 
        U("UP", "U"), D("DOWN", "D"),
        NW("NORTHWEST", "NW"), NE("NORTHEAST", "NE"), 
        NNW("NORTH-NORTHWEST", "NNW"), NNE("NORTH-NORTHEAST", "NNE"),
        SW("SOUTHWEST", "SW"), SE("SOUTHEAST", "SE"), 
        SSW("SOUTH-SOUTHWEST", "SSW"), SSE("SOUTH-SOUTHEAST", "SSE"),
        WNW("WEST-NORTHWEST", "WNW"), WSW("WEST-SOUTHWEST", "WSW"), 
        ENE("EAST-NORTHEAST", "ENE"), ESE("EAST-SOUTHEAST", "ESE");

        private final String text;
        private final String abbreviation;

        /*---------------------------------------------------------------------
         * Constructor: dirType()
         *
         * Parameter(s):
         *      - String -> t: Fullname of the enum direction.
         *      - String -> a: Abreviation of the enum direction.
         *
         * Return:
         *      - None
         *
         * Constructs a new instance of the dirType enum, indicating that the 
         * enum direction has a fullname and abbreviation.
        */
        dirType(String t, String a) 
        {
            this.text = t;
            this.abbreviation = a;
        }//end Constructor


        /*---------------------------------------------------------------------
         * Function: toString()
         *
         * Parameter(s):
         *      - None
         *
         * Return:
         *      - String: Text field of the direction.
         *
         * This function returns the text field of the associated direction.
        */
        public String toString()
        {
            return this.text;
        }//end toString()
       

        /*---------------------------------------------------------------------
         * Function: toString()
         *
         * Parameter(s):
         *      - String -> dir: Direction of travel.
         *
         * Return:
         *      - boolean: TRUE if the given direction of travel matches the
         *                 enum direction; otherwise, false.
         *
         * This function indicates if the given direction of travel matches
         * the enum direction.
        */
        public boolean match(String dir)
        {
            // Determines if the provided direction matches a known direction
            dir = dir.toUpperCase();
            if (this.text.equals(dir) || this.abbreviation.equals(dir)) {
                return true; // Match
            }

            return false; // No Match
        }//end match()

    }//end dirType
    
    private int ID;
    private dirType direction = null;
    private Place from;
    private Place to;
    private boolean locked = false;
    private int lockPattern = 0;
    private int version = 0;
    
    /*-------------------------------------------------------------------------
     * Constructor: Direction()
     *
     * Parameter(s):
     *      - Scanner -> dataFile: Scanner containing data file to create 
     *                               the Direction.
     *
     * Return:
     *      - None
     *
     * Constructs a new instance of the Direction class, requiring the user to
     * provide a Scanner object which contains a file to create the 
     * Direction.
    */
    public Direction(Scanner dataFile, int version)
        throws InvalidParseException
    {
        // Obtains and parses the next valid line of data into tokens
        String line = CleanLineScanner.getCleanLine(dataFile);
        String[] tokens = line.split("\\s+");

        /* Assigns the tokens to their corresponding attributes */
        
        this.ID = Integer.parseInt(tokens[0]);
        int fromID = Integer.parseInt(tokens[1]);
        int toID = Integer.parseInt(tokens[3]);
        this.lockPattern = Integer.parseInt(tokens[4]);
        this.version = version;

        // Determines if the provided direction is valid
        for (dirType d : dirType.values()) {
            if (d.match(tokens[2])) {
                this.direction = d;
            }
        }
        
        // Invalid direction provided
        if (this.direction == null) {
            throw new InvalidParseException(
                String.format("Error: Invalid Direction \"%s\" provided for "+
                              "Direction ID \"%s\".", tokens[2], this.ID)
            );
        }

        // Determines if the Destination Place should be locked
        if (toID <= 0) {
            this.locked = true;
            toID *= -1;
        }

        // Obtains Starting & Destination Place objects
        this.from = Place.getPlaceByID(fromID);
        this.to = Place.getPlaceByID(toID);

        // Determines if invalid Place objects were specified
        if (this.from == null || this.to == null) {
            int invalidID = (this.from == null) ? fromID : toID;
            throw new InvalidParseException(
                String.format("Error: Unknown Place ID \"%s\" provided for "+
                              "Direction ID \"%s\".", invalidID, this.ID)
            );
        }
        
        // Adds the current Direction to the Starting Place
        this.from.addDirection(this);
    }//end Constructor


    /*-------------------------------------------------------------------------
     * Function: useKey()
     *
     * Parameter(s):
     *      - Artifact -> a: Artifact Object.
     *
     * Return:
     *      - int: Returns 1 if the Artifact unlocks the entry way to the
     *             destination Place, -1 if the entry way that is unlocked by
     *             Artifact is already unlocked, or 0 if the Artifact doesn't
     *             unlock the entry way to the destination Place.
     *
     * This function unlocks the entry way to the destination Place if the 
     * provided Artifact has a Key Pattern that MATCHES the Direction's
     * Lock Pattern.
    */
    public int useKey(Artifact a)
        throws UsedKeyException
    {
        // Determines if the Lock Pattern Matches the Key Pattern of the
        // Artifact
        if (a.isMatchingLockPattern(this.lockPattern)) {

            // Unlocks the entry way to the destination Place.
            if (this.locked == true) {
                this.locked = false;
                throw new UsedKeyException(
                    String.format("The entry way to the \"%s\" has been unlocked.\n",
                                  this.direction.toString())
                );
            }
            // Destination Place entry already unlocked
            this.locked = true;
            throw new UsedKeyException(
                String.format("The entry way to the \"%s\" is now locked.\n",
                              this.direction.toString())
            );
        }

        // Artfiact doesn't unlock the destination Place
        return 0;
    }//end useKey()

    
    /*-------------------------------------------------------------------------
     * Function: follow()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - Place: The destination place ("to" place) is returned if the door
     *               is unlocked; otherwise, the present Place is returned.
     *
     * This function indicates that the present
    */
    public Place follow()
    {
        // Checks to see if the entry way is locked; if so, then the present
        // Place is returned
        if (this.locked) {
            return this.from;
        }

        // Destination Place is returned
        return this.to;
    }//end follow()


    /*-------------------------------------------------------------------------
     * Function: print()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - None
     *
     * This function prints out Direction information, used for debugging
     * purposes.
    */
    public void print()
    {
        System.out.println(String.format("Direction ID: %s", this.ID));
        System.out.println(String.format("Direction (from): %s", this.from.name()));
        System.out.println(String.format("Direction (to): %s", this.to.name()));
        System.out.println(String.format("Direction (direction): %s", this.direction.toString()));
        System.out.println(String.format("Direction (locked): %s", this.locked));
        System.out.println(String.format("Lock Pattern: %s", this.lockPattern));

        return;
    }//end print()


    /*-------------------------------------------------------------------------
     * Function: match()
     *
     * Parameter(s):
     *      - String -> dir: Direction of travel.
     *
     * Return:
     *      - boolean: TRUE if the provided direction of travel matches the
     *                 Direction object's; otherwise, FALSE.
     *
     * This function indicates whether a provided directin of travel matches
     * the Direction object's direction of travel.
    */
    public boolean match(String dir)
    {
        // Determines if user provided direction matches the class' direction
        if (this.direction.match(dir)) {
            return true; // Match
        }

        return false; // Not a match
    }//end match()


    /*-------------------------------------------------------------------------
     * Function: lock()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - None
     *
     * This function locks the entry way to the destination Place.
    */
    public void lock()
    {
        this.locked = true;

        return;
    }//end lock()


    /*-------------------------------------------------------------------------
     * Function: unlock()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - None
     *
     * This function unlocks the entry way to the destination Place.
    */
    public void unlock()
    {
        this.locked = false;

        return;
    }//end unlock()
    

    /*-------------------------------------------------------------------------
     * Function: isLocked()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - boolean: TRUE if the entry way to the destination Place is
     *                 locked; otherwise, FALSE.
     *
     * This function indicates if the entry way to the destination Place is
     * locked.
    */
    public boolean isLocked()
    {
        return this.locked;
    }//end isLocked()


    /*-------------------------------------------------------------------------
     * Function: toString()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - String: Text of the Direction.
     *
     * This function obtains the text representation of the Direction object.
    */
    public String toString()
    {
        return this.direction.toString();
    }//end toString()

}//end Direction
