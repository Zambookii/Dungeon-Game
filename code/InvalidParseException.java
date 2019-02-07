/**
 * InvalidParseException.java
 *
 * Name: Luis Enrique Love
 * NetID: llove3
 *
 * Homework #4
 * Group: 20
 * CS 342, FALL 2018, CRN: 37162
 * November 14, 2018
 *
 * This class is intended to function as a file parsing Exception, where the
 * Exception is thrown when the corresponding .gdf file is of the improper
 * format / erroneous data is present.
*/

/*-----------------------------------------------------------------------------
 * Class: InvalidParseException
 *
 * This class is intended to function as an Exception, thrown when an invalid
 * parse of the .gdf file occurs.
 *
 * Attribute(s):
 *      - None
 *
 * Method(s):
 *      - None
*/
public class InvalidParseException 
    extends Exception 
{
    /*-------------------------------------------------------------------------
     * Constructor: InvalidParseException()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - None
     *
     * Constructs a new instance of the Exception class, functioning as an
     * indicator of an invalid parse of a .gdf file.
    */
    public InvalidParseException(String message)
    {
        super(message);
    }//end Constructor

}//end InvalidParseException