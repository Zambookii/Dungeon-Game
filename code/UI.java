/**
 * UI.java
 *
 * Name: Aleksandar Ilic
 * NetID: ailic3
 *
 * Homework #4
 * Group: 20
 * CS 342, FALL 2018, CRN: 37162
 * November 14, 2018
 *
 * This class is intended to function as a UI for a Player object in an
 * "adventure" type game. Tje UI prompts the user for a move to execute
 * next.
*/

import java.io.*;
import java.util.*;

/*-----------------------------------------------------------------------------
 * Class: UI
 *
 * This class is intended to function as a UI for a Player object in an
 * "adventure" type game.
 *
 * Attribute(s):
 *      - None
 *      
 * Method(s):
 *      - (Public) UI(): Constructor
 *      - (Public) Move -> getMove(): Obtains the Characters next desired
 *                                    move.
*/
public class UI 
    implements DecisionMaker
{
    /*-------------------------------------------------------------------------
     * Constructor: UI()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - None
     *
     * Default constructor for the UI class.
    */
    public UI() {}//end Constructor


    /*-------------------------------------------------------------------------
     * Function: getMove()
     *
     * Parameter(s):
     *      - Chaaracter -> c: Character object.
     *      - Place -> p: Place object of the Character's current Place.
     *
     * Return:
     *      - Move: Move the Character will perform.
     *
     * This funciton obtains the next move a Character will make when playing
     * the game.
    */
    public Move getMove(Character c, Place p)
    {
        // Obtain user input Scanner object
        Scanner scanner = KeyboardScanner.getKeyboardScanner();

        // Display the current Place of the Character
        p.display(c);

        // Obtains user input
        c.displayOutputMessage("> ");
        String input = c.getInput();
        c.displayOutputMessage("");

        // Detects if user requests to quit the Game
        if (input.equalsIgnoreCase("QUIT") || input.equalsIgnoreCase("EXIT")) {
            return new Go_Quit(c, p);
        }
        // Detects if user requests to view current Place information
        else if (input.equalsIgnoreCase("LOOK")) {
            return new Go_Look(c, p);
        }
        // Detects if user wants to list thier inventory of artifacts
        else if (input.equalsIgnoreCase("INVENTORY") || input.equalsIgnoreCase("INVE")) {
            return new Go_Inventory(c);
        }
        // Detects if the user wants to switch interfaces
        else if (input.equalsIgnoreCase("TEXT")) {
            c.switchInterface(0);
            c.updateGUI();
            return getMove(c, p);
        }
        else if (input.toUpperCase().startsWith("GUI ")) {
            // Removes "GUI" keyword for processing
            input = input.replaceFirst("(?i)gui ", "").trim();
            int val = 0;
            try {
                val = Integer.parseInt(input);
            }
            catch (Exception e) {}

            c.switchInterface(val);
            c.updateGUI();
            return getMove(c, p);
        }
        // Detects if user attempts to get an artifact
        else if (input.toUpperCase().startsWith("GET ")) {
            // Removes "GET" keyword for processing
            input = input.replaceFirst("(?i)get ", "").trim();
            return new Go_Get(c, p, input);
        }
        // Detects if user attempts to drop an artifact
        else if (input.toUpperCase().startsWith("DROP ")) {
            // Removes "DROP" keyword for processing
            input = input.replaceFirst("(?i)drop ", "").trim();
            return new Go_Drop(c, p, input);
        }
        // Detects if user attempts to use an artifact
        else if (input.toUpperCase().startsWith("USE ")) {
            // Removes "USE" keyword for processing
            input = input.replaceFirst("(?i)use ", "").trim();
            return new Go_Use(c, p, input);
        }
        // Detects if user attempts to skip thier turn
        else if (input.toUpperCase().equalsIgnoreCase("wait")) {
            return new Go_Wait();
        }
        // Detects if user attempts to eat food
        else if (input.toUpperCase().startsWith("EAT ")) {
            // Removes "EAT" keyword for processing
            input = input.replaceFirst("(?i)eat ", "").trim();
            return new Go_Eat(c, input);
        }
        // Detects if user attempts to heal everyone
        else if (input.toUpperCase().startsWith("HEAL ")) {
            // Removes "HEAL" keyword for processing
            input = input.replaceFirst("(?i)heal ", "").trim();
            return new Go_Heal(c, input);
        }
        // Detects if user attempts to attack everyone
        else if (input.toUpperCase().startsWith("ATTACK ")) {
            // Removes "ATTACK" keyword for processing
            input = input.replaceFirst("(?i)attack ", "").trim();
            return new Go_Attack(c, input);
        }
        // User has provided a direction of travel
        else {
            // Removes "GO" keyword for processing
            input = input.replaceFirst("(?i)go ", "").trim();
            return new Go_Direction(c, p, input);
        }
        
    }//end getMove()

}//end UI