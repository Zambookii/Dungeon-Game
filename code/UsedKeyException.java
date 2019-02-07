/**
 * UsedKeyException.java
 *
 * Name: Luis Enrique Love
 * NetID: llove3
 *
 * Homework #4
 * Group: 20
 * CS 342, FALL 2018, CRN: 37162
 * November 14, 2018
 *
 * This class is intended to function as a Character that uses a key on a door 
 * Exception, where the Exception is thrown when the a key is used on any door.
*/

/*-----------------------------------------------------------------------------
 * Class: UsedKeyException
 *
 * This class is intended to function as an Exception, thrown when a Character
 * tries to use a key on a door.
 *
 * Attribute(s):
 *      - None
 *
 * Method(s):
 *      - None
*/
public class UsedKeyException 
    extends Exception 
{
    /*-------------------------------------------------------------------------
     * Constructor: UsedKeyException()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - None
     *
     * Constructs a new instance of the Exception class, functioning as an
     * indicator that a Character tries to use a key on a door.
    */
    public UsedKeyException(String message)
    {
        super(message);
    }//end Constructor

}//end UsedKeyException