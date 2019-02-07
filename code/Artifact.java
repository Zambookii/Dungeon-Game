/**
 * Artifact.java
 *
 * Name: Luis Enrique Love
 * NetID: llove3
 *
 * Homework #4
 * Group: 20
 * CS 342, FALL 2018, CRN: 37162
 * November 14, 2018
 *
 * This class is intended to function as an Artifact within an "adventure" 
 * type Game. An Artifact posses properties such as a Point value associated
 * with possessing the item, the weight of the object, and, if the object is
 * usable, a key pattern is associated with the Artifact which may be used to 
 * unlock a given entry way to a Place object in the Game.
*/

import java.io.*;
import java.util.*;

/*-----------------------------------------------------------------------------
 * Class: Artifact
 *
 * This class is intended to function as an Artifact within an "adventture"
 * type Game.
 *
 * Attribute(s):
 *      - (Private) int -> placeOrCharID: Original location of the Artifact.
 *      - (Private) int -> ID: ID of the Artifact.
 *      - (Private) String -> name: Name of the Artifact.
 *      - (Private) int -> points: Point value of the Artifact.
 *      - (Private) int -> weight: Weight of the Artifact.
 *      - (Private) int -> keyPattern: Key Pattern of the Artifact.
 *      - (Private) ArrayList -> description: Description of the Artifact.
 *
 * Method(s):
 *      - (Public) Artifact(): Constructor
 *      - (Public) void -> use(): Uses the Artifact within a Place.
 *      - (Public) boolean -> match(): Determines if String matches the
 *                                     Artifact.
 *      - (Public) void -> print(): Displays all Artifact information.
 *      - (Public) void -> display(): Displays Artifact info.
 *      - (Public) int -> value(): Returns the point value of the Artifact.
 *      - (Public) int -> weight(): Returns the weight of the Artifact.
 *      - (Public) String -> name(): Returns the name of the Artifact.
 *      - (Public) String -> description: Returns the description of the 
 *                                        Artifact.
 *      - (Public) boolean -> isMatchingLockPattern(): Indicates if a given
 *                                                   lock pattern matches the
 *                                                   Artifact's key pattern.
 *      - (Public) void -> sendToOriginalPlace(): Returns an Artifact to its
 *                                                starting location.
*/
public class Artifact
{   
    private int placeOrCharID = 0;
    private int ID = 0;
    private String name = null;
    private int points = 0;
    private int mobility = 0;
    private int keyPattern = 0;
    private int useType = 0;
    private boolean oneTimeUse = true;
    private int useValue = 0;
    private String description = null;
    private int version = 0;

    /*-------------------------------------------------------------------------
     * Constructor: Artifact()
     *
     * Parameter(s):
     *      - MyScanner -> dataFile: Scanner containing data file to create 
     *                               the Artifact.
     *
     * Return:
     *      - None
     *
     * Constructs a new instance of the Artifact class, requiring the user to
     * provide a MyScanner object which contains a file to create the Artifact.
    */
    public Artifact(Scanner dataFile, int version)
        throws InvalidParseException
    {
        this.version = version;

        if (version <= 50) {
            fileParser50(dataFile);
        }
        else {
            fileParser5X(dataFile);
        }
        
    }//end Constructor


    private void fileParser50(Scanner dataFile)
        throws InvalidParseException
    {
        // Gets the original location of the Artifact from the data file
        String line = CleanLineScanner.getCleanLine(dataFile);
        this.placeOrCharID = Integer.parseInt(line);
        
        // Parses the line of the data file into the various fields used to
        // create the object
        line = CleanLineScanner.getCleanLine(dataFile);
        String[] tokens = line.split("\\s+");

        /* Assigns the tokens to their corresponding attributes */

        this.ID = Integer.parseInt(tokens[0]);
        this.points = Integer.parseInt(tokens[1]);
        this.mobility = Integer.parseInt(tokens[2]);
        this.keyPattern = Integer.parseInt(tokens[3]);
        this.description = "";

        // Constructs Artifact name
        this.name = tokens[4];
        for (int i = 5; i < tokens.length; i++) {
            this.name += " " + tokens[i];
        }

        // Obtains the number of Artifact description lines in the data file,
        // then reads in the corresponding number of lines
        line = CleanLineScanner.getCleanLine(dataFile);
        int numDescLines = Integer.parseInt(line);
        for (int i = 0; i < numDescLines; i++) {
            line = CleanLineScanner.getCleanLine(dataFile);
            this.description += line + "\n";
        }

        /* Adds the Artifact to the corresponding starting Place indicated */
        this.sendToOriginalPlace();
    }


    private void fileParser5X(Scanner dataFile)
        throws InvalidParseException
    {
        // Gets the original location of the Artifact from the data file
        String line = CleanLineScanner.getCleanLine(dataFile);
        this.placeOrCharID = Integer.parseInt(line);
        
        // Parses the line of the data file into the various fields used to
        // create the object
        line = CleanLineScanner.getCleanLine(dataFile);
        String[] tokens = line.split("\\s+");

        /* Assigns the tokens to their corresponding attributes */

        this.ID = Integer.parseInt(tokens[0]);
        this.points = Integer.parseInt(tokens[1]);
        this.mobility = Integer.parseInt(tokens[2]);
        this.keyPattern = Integer.parseInt(tokens[3]);
        this.useType = Integer.parseInt(tokens[4]);
        this.oneTimeUse = Boolean.parseBoolean(tokens[5]);
        this.useValue = Integer.parseInt(tokens[6]);
        this.description = "";

        // Constructs Artifact name
        this.name = tokens[7];
        for (int i = 8; i < tokens.length; i++) {
            this.name += " " + tokens[i];
        }

        // Obtains the number of Artifact description lines in the data file,
        // then reads in the corresponding number of lines
        line = CleanLineScanner.getCleanLine(dataFile);
        int numDescLines = Integer.parseInt(line);
        for (int i = 0; i < numDescLines; i++) {
            line = CleanLineScanner.getCleanLine(dataFile);
            this.description += line + "\n";
        }

        /* Adds the Artifact to the corresponding starting Place indicated */
        this.sendToOriginalPlace();
    }


    /*-------------------------------------------------------------------------
     * Function: use()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - None
     *
     * This function "uses" the Artifact in its current Place.
    */
    public void use(Character c, Place p)
        throws UnusableArtifactException, UsedKeyException
    {
        // Determines the current Place of the Artifact & uses the Artifact
        if (this.keyPattern == 0) {
            throw new UnusableArtifactException(
                String.format("\"%s\" is not a usable item.\n", this.name)
            );
        }

        // Usess the Artifact in the current Place
        try {
            p.useKey(this);
        }
        catch (UsedKeyException e) {
            throw new UsedKeyException(e.getMessage());
        }        

        return;
    }//end use()


    /*-------------------------------------------------------------------------
     * Function: match()
     *
     * Parameter(s):
     *      - String -> a: Artifact name.
     *
     * Return:
     *      - boolean: TRUE if the provided Artifact name matches the
     *                 Artifact object's name; otherwise, FALSE.
     *
     * This function indicates whether a provided Artifact name matches
     * the Artifact object's name.
    */
    public boolean match(String a)
    {
        if (this.name.toUpperCase().equals(a.toUpperCase())) {
            return true; // Match
        }

        return false; // Not a match
    }//end match()


    /*-------------------------------------------------------------------------
     * Function: print()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - None
     *
     * This function prints out Artifact information, used for debugging
     * purposes.
    */
    public void print()
    {
        System.out.println(String.format("Artifact Name: %s", this.name));
        System.out.println(String.format("Artifact ID: %s", this.ID));
        System.out.println(String.format("Artifact Original Place ID: %s", this.placeOrCharID));
        System.out.println(String.format("Artifact Points: %s", this.points));
        System.out.println(String.format("Artifact Weight: %skg", this.mobility));
        System.out.println(String.format("Artifact Key Pattern: %s", this.keyPattern));
        System.out.print("Artifact Description: ");
        System.out.println(this.description);

        return;
    }//end print()


    /*-------------------------------------------------------------------------
     * Function: display()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - None
     *
     * This function prints out Artifact information.
    */
    public void display()
    {
        // System.out.println(this.name);
    }//end display()


    /*-------------------------------------------------------------------------
     * Function: value()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - int: Point value of the Artifact.
     *
     * This function returns the point value associated with the Artifact.
    */
    public int value()
    {
        return this.points;
    }//end value()


    /*-------------------------------------------------------------------------
     * Function: weight()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - int: Mobility factor of the Artifact.
     *
     * This function returns the weight associated with the Artifact.
    */
    public int weight()
    {
        return this.mobility;
    }//end weight()


    /*-------------------------------------------------------------------------
     * Function: name()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - String: Name of the Artifact.
     *
     * This function returns the name of the Artifact.
    */
    public String name()
    {
        return this.name;
    }//end name()


    /*-------------------------------------------------------------------------
     * Function: description()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - String: Description of the Artifact.
     *
     * This function returns the description of the Artifact.
    */
    public String description()
    {
        return this.description;
    }//end description()


    /*-------------------------------------------------------------------------
     * Function: isMatchingLockPattern()
     *
     * Parameter(s):
     *      - int -> lockPattern: Lock Pattern of a Direction Lock.
     *
     * Return:
     *      - boolean: TRUE if the Direction's Lock Pattern matches the 
     *                 Artifact's Key Pattern; otherwise, FALSE.
     *
     * This function indicates if a Direction's Lock Pattern matches the
     * Artifact's Key Pattern.
    */
    public boolean isMatchingLockPattern(int lockPattern)
    {
        // Determines if the Lock Pattern matches the Key Pattern
        if (this.keyPattern == lockPattern) {
            return true;
        }

        return false;
    }//end isMatchingLockPattern()


    /*-------------------------------------------------------------------------
     * Function: sendToOriginalPlace()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - None.
     *
     * This function sends an Artifact to its original starting Place. If the
     * Artifact has no original starting Place, then the Artifact will be 
     * randomly placed somwhere.
    */
    public void sendToOriginalPlace()
        throws InvalidParseException
    {
        // Using GDF File Version 3.X
        if (this.version < 40) {
            // Character owned Artifact
            if (this.placeOrCharID == 0) {
                TreeMap<Integer, Character> temp = Character.getCharacters();
                
                // No Characters exist
                if (temp.size() == 0) {
                    throw new InvalidParseException(
                        String.format("Error: No Characters exist exist to "+
                                      "assign Artifact ID \"%s\".", this.ID)
                    );
                }
                
                // Give all Characters the Artifact
                for (Map.Entry<Integer, Character> pair : temp.entrySet()) {
                    pair.getValue().addArtifact(this);
                }
            }
            // Invalid ID provided
            else if (this.placeOrCharID < 0) {
                throw new InvalidParseException(
                    String.format("Error: Unknown Character ID \"%s\" "+
                                "provided for Artifact ID \"%s\". Artifact "+
                                "will not be used.",this.placeOrCharID, 
                                this.ID)
                );
            }
        }
        // Using GDF File Version 4.X
        else {
            // Randomly assign the Artifact to a Place
            if (this.placeOrCharID == 0) {
                Place destination = Place.getRandomPlace();

                // No Places exist
                if (destination == null) {
                    throw new InvalidParseException(
                        String.format("Error: No Place objects exist to randomly "+
                                      "assign Artifact ID \"%s\".", this.ID)
                    );
                }
                destination.addArtifact(this);
            }
            // Character owned Artifact
            else if (this.placeOrCharID < 0) {
                Character c = Character.getCharacterByID(-1 * this.placeOrCharID);
                
                // Character doesn't exist
                if (c == null) {
                    throw new InvalidParseException(
                        String.format("Error: Unknown Character ID \"%s\" "+
                                    "provided for Artifact ID \"%s\". Artifact "+
                                    "will not be used.",this.placeOrCharID, 
                                    this.ID)
                    );
                }

                c.addArtifact(this);
            }
        }

        // Place Artifact
        if (this.placeOrCharID > 0) {
            // Artifact is sent to its corresponding Place
            Place temp = Place.getPlaceByID(this.placeOrCharID);

            // Place doesn't exist
            if (temp == null) {
                throw new InvalidParseException(
                    String.format("Error: Unknown Place ID \"%s\" provided "+
                                  "for Artifact ID \"%s\". Artifact will "+
                                  "not be used.",this.placeOrCharID, this.ID)
                );
            }

            temp.addArtifact(this);
        }

        return;
    }//end sendToOriginalPlace()


    public boolean isFood()
    {
        return (this.useType == 0) ? true : false;
    }


    public boolean isWeapon()
    {
        return (this.useType == 1) ? true : false;
    }


    public boolean isHealth()
    {
        return (this.useType == 2) ? true : false;
    }


    public boolean isItem()
    {
        return (this.useType == 3) ? true : false;
    }


    public int getUseValue()
    {
        return this.useValue;
    }

}//end Artifact
