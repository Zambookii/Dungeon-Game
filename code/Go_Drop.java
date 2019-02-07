/**
 * Go_Drop.java
 *
 * Name: Luis Enrique Love
 * NetID: llove3
 *
 * Homework #4
 * Group: 20
 * CS 342, FALL 2018, CRN: 37162
 * November 14, 2018
 *
 * This class is intended to function as a "drop one of my Artifacts" action
 * performed by a Character in an "adventure" type game.
*/

import java.io.*;
import java.util.*;

/*-----------------------------------------------------------------------------
 * Class: Go_Drop
 *
 * This class is intended to function as a "drop one of my Artifacts" action.
 *
 * Attribute(s):
 *      - (Private) Character -> character: Character to act upon.
 *      - (Private) Place -> place: Current Place of the Character.
 *      - (Private) String -> artifactName: Name of Artifact.
 *      
 * Method(s):
 *      - (Public) Go_Drop): Constructor
 *      - (Public) void -> execute(): Executes move.
*/
public class Go_Drop
    extends Move
{
    Character character = null;
    Place place = null;
    String artifactName = null;

    /*-------------------------------------------------------------------------
     * Constructor: Go_Drop()
     *
     * Parameter(s):
     *      - Character -> c: Character to act upon.
     *      - Place -> p: Current Place of the Character.
     *      - String -> n: Name of Artifact.
     *
     * Return:
     *      - None
     *
     * Constructs a new instance of the Go_Drop class, assigning the
     * various objects to be acted upon.
    */
    public Go_Drop(Character c, Place p, String n)
    {
        this.character = c;
        this.place = p;
        this.artifactName = n;
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
     * This function drops an Artifact in the given Place.
    */
    public void execute()
        throws HasNoArtifactException
    {
        // Obtains the character's Artifacts
        TreeMap<String, Artifact> artifacts = this.character.getArtifacts();

        // Determines if the requested Artifact exists
        Artifact a = artifacts.remove(this.artifactName.toUpperCase());

        // Character doesn't have the Artifact
        if (a == null) {
            // User doesn't possess the Artifact
            throw new HasNoArtifactException(
                String.format("You do not have the Artifact \"%s\".\n", 
                              this.artifactName)
            );
        }
        // Drop the Artifact in the current Place
        else {
            this.place.addArtifact(a);
        }

        return;
    }//end execute()

}//end Go_Drop