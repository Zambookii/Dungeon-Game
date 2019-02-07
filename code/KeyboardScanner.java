/**
 * KeyboardScanner.java
 *
 * Name: Luis Enrique Love
 * NetID: llove3
 *
 * Homework #4
 * Group: 20
 * CS 342, FALL 2018, CRN: 37162
 * November 14, 2018
 *
 * This class is intended to function as a singleton, providing a way to
 * obtain a single Scanner for obtaining user input from the Keyboard.
*/

import java.io.*;
import java.util.*;

/*-----------------------------------------------------------------------------
 * Class: KeyboardScanner
 *
 * This class is intended to function as a singleton, providing a way to
 * obtain a single Scanner for obtaining user input from the Keyboard.
 *
 * Attribute(s):
 *      - (Private Static) KeyboardScanner: onlyInstance: Singleton instance of
 *                                                        KeyboardScanner 
 *                                                        object.
 *      - (Private Static) Scanner -> scanner: Scanner object for user input.
 *      
 * Method(s):
 *      - (Public Static) Scanner -> getKeyboardScanner(): Obtains Scanner.
*/
public class KeyboardScanner
{
    private static KeyboardScanner onlyInstance = null;
    private static Scanner scanner = null;

    /*-------------------------------------------------------------------------
     * Constructor: KeyboardScanner()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - None
     *
     * Constructor initializes Scanner object taking user input from the 
     * keyboard.
    */
    private KeyboardScanner()
    {
        scanner = new Scanner(System.in);
    }//end Constructor

    
    /*-------------------------------------------------------------------------
     * Function: getKeyboardScanner()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - Scanner: Scanner object connected to stdin.
     *
     * Function returns a Scanner object connected to stdin.
    */
    public static Scanner getKeyboardScanner()
    {
        // Instantiates a KeyboardScanner to obtain the Scanner object.
        if (onlyInstance == null) {
            onlyInstance = new KeyboardScanner();
        }

        return scanner;
    }//end getKeyboardScanner()

}//end KeyboardScanner