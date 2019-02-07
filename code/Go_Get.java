/**
 * Go_Get.java
 *
 * Name: Luis Enrique Love
 * NetID: llove3
 *
 * Homework #4
 * Group: 20
 * CS 342, FALL 2018, CRN: 37162
 * November 14, 2018
 *
 * This class is intended to function as a "get an Artifact" action
 * performed by a Character in an "adventure" type game.
*/

/*-----------------------------------------------------------------------------
 * Class: Go_Get
 *
 * This class is intended to function as a "move in a given direction" action.
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
public class Go_Get
    extends Move
{
    Character character = null;
    Place place = null;
    String artifactName = null;

    /*-------------------------------------------------------------------------
     * Constructor: Go_Get()
     *
     * Parameter(s):
     *      - Character -> c: Character to act upon.
     *      - Place -> p: Current Place of the Character.
     *      - String -> n: Name of Artifact.
     *
     * Return:
     *      - None
     *
     * Constructs a new instance of the Go_Get class, assigning the
     * various objects to be acted upon.
    */
    public Go_Get(Character c, Place p, String n)
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
     * This function obtains an Artifact from the given Place.
    */
    public void execute()
        throws ImmovableArtifactException, HasNoArtifactException
    {
        // Determines if the requested Artifact exists
        if (this.place.hasArtifact(this.artifactName)) {
            Artifact a = this.place.removeArtifactByName(this.artifactName);

            // Gives the Artifact to the Character
            if (a != null) {
                this.character.addArtifact(a);
            }
            // Artifact if immovable
            else {
                throw new ImmovableArtifactException(
                    String.format("You do not have the Artifact \"%s\".\n", 
                                this.artifactName)
                );
            }
        }
        // Place doesn't have the Artifact
        else {
            throw new HasNoArtifactException(
                String.format("You do not have the Artifact \"%s\".\n", 
                              this.artifactName)
            );
        }

        return;
    }//end execute()

}//end Go_Get