/**
 * ImmovableArtifactException.java
 *
 * Name: Luis Enrique Love
 * NetID: llove3
 *
 * Homework #4
 * Group: 20
 * CS 342, FALL 2018, CRN: 37162
 * November 14, 2018
 *
 * This class is intended to function as a Character tries to pick up an
 * immovable Artifact Exception, where the Exception is thrown when the 
 * Charcter tries to pick up an immovable object.
*/

/*-----------------------------------------------------------------------------
 * Class: ImmovableArtifactException
 *
 * This class is intended to function as an Exception, thrown when a Character
 * tries to pick up an immovable Artifact.
 *
 * Attribute(s):
 *      - None
 *
 * Method(s):
 *      - None
*/
public class ImmovableArtifactException 
    extends Exception 
{
    /*-------------------------------------------------------------------------
     * Constructor: ImmovableArtifactException()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - None
     *
     * Constructs a new instance of the Exception class, functioning as an
     * indicator that a Character tried to pick up an immovable Artifact.
    */
    public ImmovableArtifactException(String message)
    {
        super(message);
    }//end Constructor

}//end ImmovableArtifactException