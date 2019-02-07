/**
 * CharDeathException.java
 *
 * Name: Luis Enrique Love
 * NetID: llove3
 *
 * Homework #4
 * Group: 20
 * CS 342, FALL 2018, CRN: 37162
 * November 14, 2018
 *
 * This class is intended to function as a Character dies Exception, where the
 * Exception is thrown when the Charcter dies.
*/

/*-----------------------------------------------------------------------------
 * Class: CharDeathException
 *
 * This class is intended to function as an Exception, thrown when a Character
 * dies.
 *
 * Attribute(s):
 *      - None
 *
 * Method(s):
 *      - None
*/
public class CharDeathException 
    extends Exception 
{
    /*-------------------------------------------------------------------------
     * Constructor: CharDeathException()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - None
     *
     * Constructs a new instance of the Exception class, functioning as an
     * indicator that a Character has died.
    */
    public CharDeathException(String message)
    {
        super(message);
    }//end Constructor

}//end CharDeathException