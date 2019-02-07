/**
 * BossFightException.java
 *
 * Name: Luis Enrique Love
 * NetID: llove3
 *
 * Homework #4
 * Group: 20
 * CS 342, FALL 2018, CRN: 37162
 * November 14, 2018
 *
 * This class is intended to function as an indicator that a Character has 
 * encountered a Boss, where the Exception is thrown when the Character 
 * encounters a Boss.
*/

/*-----------------------------------------------------------------------------
 * Class: BossFightException
 *
 * This class is intended to function as an Exception, thrown when a Character
 * has encountered a Boss.
 * 
 * Attribute(s):
 *      - None
 *
 * Method(s):
 *      - None
*/
public class BossFightException 
    extends Exception 
{
    /*-------------------------------------------------------------------------
     * Constructor: BossFightException()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - None
     *
     * Constructs a new instance of the Exception class, functioning as an
     * indicator that a Character is in a Boss fight.
    */
    public BossFightException(String message)
    {
        super(message);
    }//end Constructor

}//end BossFightException