/**
 * Go_Use.java
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
 * Class: Go_Use
 *
 * This class is intended to function as a "Use an Artifact" action.
 *
 * Attribute(s):
 *      - (Private) Character -> character: Character to act upon.
 *      - (Private) Place -> place: Current Place of the Character.
 *      - (Private) String -> direction: Executes obtained move.
 *      
 * Method(s):
 *      - (Public) Go_Drop): Constructor
 *      - (Public) void -> execute(): Executes move.
*/
public class Go_Use
    extends Move
{
    Character character = null;
    Place place = null;
    String artifactName = null;

    /*-------------------------------------------------------------------------
     * Constructor: Go_Use()
     *
     * Parameter(s):
     *      - Character -> c: Character to act upon.
     *      - Place -> p: Current Place of the Character.
     *      - String -> n: Name of Artifact.
     *
     * Return:
     *      - None
     *
     * Constructs a new instance of the Go_Use class, assigning the
     * various objects to be acted upon.
    */
    public Go_Use(Character c, Place p, String n)
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
     * This function uses a Character's Artifact.
    */
    public void execute()
        throws HasNoArtifactException, UnusableArtifactException, UsedKeyException
    {
        // Obtains a Character's Artifact
        Artifact a = this.character.getArtifactByName(this.artifactName);

        // Character doesn't have the stated Artifact.
        if (a == null) {
            throw new HasNoArtifactException(
                String.format("You do not have the Artifact \"%s\".\n", 
                              this.artifactName)
            );
        }
        else {
            // Uses the Artifact on the current Place.
            try {
                a.use(this.character, this.place);
            }
            catch (UnusableArtifactException e) {
                throw new UnusableArtifactException(e.getMessage());
            }
            catch (UsedKeyException e) {
                throw new UsedKeyException(e.getMessage());
            }
        }

        return;
    }//end execute()

}//end Go_Use