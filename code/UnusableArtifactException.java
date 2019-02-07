/**
 * UnusableArtifactException.java
 *
 * Name: Luis Enrique Love
 * NetID: llove3
 *
 * Homework #4
 * Group: 20
 * CS 342, FALL 2018, CRN: 37162
 * November 14, 2018
 *
 * This class is intended to function as a Character that tries to use an
 * unusable Artifact Exception, where the Exception is thrown when the 
 * Charcter tries to use an unusable Artifact.
*/

/*-----------------------------------------------------------------------------
 * Class: UnusableArtifactException
 *
 * This class is intended to function as an Exception, thrown when a Character
 * tries to use an unusable Artifact.
 *
 * Attribute(s):
 *      - None
 *
 * Method(s):
 *      - None
*/
public class UnusableArtifactException 
    extends Exception 
{
    /*-------------------------------------------------------------------------
     * Constructor: UnusableArtifactException()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - None
     *
     * Constructs a new instance of the Exception class, functioning as an
     * indicator that a Character tried to use an unsuable Artifact.
    */
    public UnusableArtifactException(String message)
    {
        super(message);
    }//end Constructor

}//end UnusableArtifactException