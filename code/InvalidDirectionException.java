/**
 * InvalidDirectionException.java
 *
 * Name: Luis Enrique Love
 * NetID: llove3
 *
 * Homework #4
 * Group: 20
 * CS 342, FALL 2018, CRN: 37162
 * November 14, 2018
 *
 * This class is intended to function as an inccorrectly provided direction
 * Exception, where the Exception is thrown when the user provides an invalid
 * direction of travel.
*/

/*-----------------------------------------------------------------------------
 * Class: InvalidDirectionException
 *
 * This class is intended to function as an Exception, thrown when the user
 * provides an invalid direction of travel.
 *
 * Attribute(s):
 *      - None
 *
 * Method(s):
 *      - None
*/
public class InvalidDirectionException 
    extends Exception 
{
    /*-------------------------------------------------------------------------
     * Constructor: InvalidDirectionException()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - None
     *
     * Constructs a new instance of the Exception class, functioning as an
     * indicator that the user provided an invalid direction.
    */
    public InvalidDirectionException(String message)
    {
        super(message);
    }//end Constructor

}//end InvalidDirectionException