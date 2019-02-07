/**
 * CleanLineScanner.java
 *
 * Name: Jesse Palasz
 * NetID: jpalas3
 *
 * Homework #4
 * Group: 20
 * CS 342, FALL 2018, CRN: 37162
 * November 14, 2018
 *
 * This class is intended to function as an enhancement to a Scanner object,
 * allowing for it to return the next lien of data devoid of comments.
*/

import java.io.*;
import java.util.*;

/*-----------------------------------------------------------------------------
 * Class: CleanLineScanner
 *
 * This class is intended to function as an enhancement to a Scanner object,
 * allowing for it to return the next lien of data devoid of comments.
 *
 * Attribute(s):
 *      - None
 *      
 * Method(s):
 *      - (Public Static) String -> getCleanLine(): Obtains the next non-blank
 *                                                  line without any comments.
*/
public class CleanLineScanner
{
    /*-------------------------------------------------------------------------
     * Constructor: CleanLineScanner()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - None
     *
     * Default constructor for a CleanLineScanner object.
    */
    public CleanLineScanner() {}//end Constructor

    /*-------------------------------------------------------------------------
     * Function: getCleanLine()
     *
     * Parameter(s):
     *      - Scanner -> scanner: Scanner object containing data.
     *
     * Return:
     *      - String: Next clean (formatted) line from the File object. A blank
     *                line is returned if no more data is present in the File
     *                object.
     *
     * This function obtains the next clean line from the File object. A clean
     * line is defined as a non-blank line, stripped of comments. All blank
     * lines and lines that are solely comments in the File object are ignored.
     * Comments start with "//".
    */
    public static String getCleanLine(Scanner scanner)
    {
        String line = ""; // Line from the File

        // Keep on searching the File as long as there is data
        while(scanner.hasNextLine()) {

            // Gets the next line from the File
            line = scanner.nextLine().trim();

            // Obtained line is not a blank line or just a comment
            if (!line.equals("") && !line.startsWith("//")) {
                break;
            }
        }

        // Strips comments from lines with actual data
        int index = line.indexOf("//"); // Starting index of comment
        if (index != -1) {
            // Strips the accompanying comment, if present, and removes
            // any trailing whitespace
            line = line.substring(0, index).trim();
        }

        return line;
    }//end getCleanLine()

}//end CleanLineScanner