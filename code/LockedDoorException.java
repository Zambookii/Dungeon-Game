/**
 * LockedDoorException.java
 *
 * Name: Luis Enrique Love
 * NetID: llove3
 *
 * Homework #4
 * Group: 20
 * CS 342, FALL 2018, CRN: 37162
 * November 14, 2018
 *
 * This class is intended to function as a Character that tries to go through
 * a locked door Exception, where the Exception is thrown when the 
 * Charcter tries to go through a locked door.
*/

/*-----------------------------------------------------------------------------
 * Class: LockedDoorException
 *
 * This class is intended to function as an Exception, thrown when a Character
 * tries to go through a locked door.
 *
 * Attribute(s):
 *      - None
 *
 * Method(s):
 *      - None
*/
public class LockedDoorException 
    extends Exception 
{
    /*-------------------------------------------------------------------------
     * Constructor: LockedDoorException()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - None
     *
     * Constructs a new instance of the Exception class, functioning as an
     * indicator that a Character tried to go through a locked door.
    */
    public LockedDoorException(String message)
    {
        super(message);
    }//end Constructor

}//end LockedDoorException