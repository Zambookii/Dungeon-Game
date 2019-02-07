/**
 * StarveDeathException.java
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
 * Exception is thrown when the Charcter starves to death.
*/

/*-----------------------------------------------------------------------------
 * Class: StarveDeathException
 *
 * This class is intended to function as an Exception, thrown when a Character
 * starves to death.
 *
 * Attribute(s):
 *      - None
 *
 * Method(s):
 *      - None
*/
public class StarveDeathException 
    extends Exception 
{
    /*-------------------------------------------------------------------------
     * Constructor: StarveDeathException()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - None
     *
     * Constructs a new instance of the Exception class, functioning as an
     * indicator that a Character has starved to death.
    */
    public StarveDeathException(String message)
    {
        super(message);
    }//end Constructor

}//end StarveDeathException