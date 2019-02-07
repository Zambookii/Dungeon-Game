/**
 * Go_Heal.java
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

public class Go_Heal
    extends Move
{
    Character character = null;
    String artifactName = null;

    public Go_Heal(Character c, String n)
    {
        this.character = c;
        this.artifactName = n;
    }//end Constructor


    public void execute()
        throws HasNoArtifactException, UnusableArtifactException, CharDeathException
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

            if (a.isHealth()) {
                this.character.heal(a.getUseValue());
            }
            else {
                throw new UnusableArtifactException(
                    String.format("\"%s\" is not a healing item!", a.name())
                );
            }
        }

        return;
    }//end execute()

}//end Go_Use