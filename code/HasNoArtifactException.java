/**
 * HasNoArtifactException.java
 *
 * Name: Luis Enrique Love
 * NetID: llove3
 *
 * Homework #4
 * Group: 20
 * CS 342, FALL 2018, CRN: 37162
 * November 14, 2018
 *
 * This class is intended to function as a Character that doesn't have a 
 * particular Artifact Exception, where the Exception is thrown when the 
 * Charcter tries to use an Artifact not in their possession.
*/

/*-----------------------------------------------------------------------------
 * Class: HasNoArtifactException
 *
 * This class is intended to function as an Exception, thrown when a Character
 * tries to use an Aritfact not in their possession.
 *
 * Attribute(s):
 *      - None
 *
 * Method(s):
 *      - None
*/
public class HasNoArtifactException 
    extends Exception 
{
    /*-------------------------------------------------------------------------
     * Constructor: HasNoArtifactException()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - None
     *
     * Constructs a new instance of the Exception class, functioning as an
     * indicator that a Character doesn't have an Artifact.
    */
    public HasNoArtifactException(String message)
    {
        super(message);
    }//end Constructor

}//end HasNoArtifactException
