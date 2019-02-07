/**
 * GameTester.java
 *
 * Name: Jesse Palasz
 * NetID: jpalas3
 *
 * Homework #4
 * Group: 20
 * CS 342, FALL 2018, CRN: 37162
 * November 14, 2018
 *
 * This class is intdened to function as a driver for an "adventure"
 * type Game. A Game is started by importing a series of Places that
 * function as accessible rooms to be traveled too. Each Place is
 * connected by some form of entry-way in a given direction. To play the
 * Game, provide a direction of travel, and the Place in that direction of
 * travel will be entered, provided that the Place is not locked. To win the
 * Game, enter the "Exit Room". Entry ways can be unlocked by obtaining and
 * using Artifacts found around the enviornment.
 *
 * Commands:
 *      - "GO XXXX": XXXX is a direction of travel. The user traverses from
 *                   the present Place to the Place in the direction of travel.
 *      - "XXXX": Same os "GO XXXX".
 *      - "EXIT": Quits the Game, terminates the program.
 *      - "QUIT": Quits the Game, terminates the program.
 *      - "LOOK": Displays all information pertaining to the current Place.
 *      - INVENTORY or INVE : Lists the player's inventory.
 *      - GET XXXX : Where XXXX represents an artifact; Add an artifact to the player's inventory.
 *      - DROP XXXX : Where XXXX represents an artifact; Drop a player's artifact off in the current Place.
 *      - USE XXXX : Where XXXX represents an artifact; Use a player's artifact on the current Place.
 *
 * Direction of Travel (XXXX):
 *      - "N": North
 *      - "S": South
 *      - "E": East
 *      - "W": West
 *      - "U": Up
 *      - "D": Down
 *      - "NE": Northeast
 *      - "NW": Northwest
 *      - "NNE": North by Northeast
 *      - "NNW": North by Northwest
 *      - "SE": Southeast
 *      - "SW": Southwest
 *      - "SSE": South by Southeast
 *      - "SSW": South by Southwest
 *      - "WNW": West by Northwest
 *      - "WSW": West by Southwest
 *      - "ENE": East by Northeast
 *      - "ESE": East by Southeast
*/

import java.io.*;
import java.util.*;

/*-----------------------------------------------------------------------------
 * Class: GameTester
 *
 * This class is intended to function as a test driver for the Game class.
 *
 * Attribute(s):
 *      - None
 * 
 * Method(s):
 *      - (Public) void -> main(): Test Driver.
 *      - (Private) void -> printTestResults(): Reports output of testcase.
 *      - (Private) void -> tessPlace(): Tests Place object.
 *      - (Private) void -> testDirection(): Tests Direction object.
 *      - (Private) void -> testArtifact(): Tests Direction object.
*/
public class GameTester
{
    /*-------------------------------------------------------------------------
     * Function: main()
     *
     * Parameter(s):
     *      - String[] -> args: Command line arguments. 
     *
     * Return:
     *      - None
     *
     * Test Driver.
    */
    public static void main(String[] args)
    {
        // Prints header information
        System.out.println("Group: 20");
        System.out.println("Name: Luis Enrique Love");
        System.out.println("      Jesse Palasz");
        System.out.println("      Aleksandar Ilic");
        System.out.println("NetID: llove3\n");
        System.out.println("       jpalas3\n");
        System.out.println("       ailic3\n");

        // Obtains a filename from the command-line, if provided
        String filename = "";
        int numCharacters = 0;
        if (args.length == 0) {
            System.out.println("Warning: Expected a filename from the command-line.\n");
        }
        else if (args.length == 1) {
            filename = args[0];
        }
        else if (args.length > 1) {
            filename = args[0];
            numCharacters = Integer.parseInt(args[1]);
        }
        else {
            System.out.println(
                String.format("Error: Too many command-line arguments given. Got %s, "+
                              "expected at most 2.", args.length)
            );
        }

        // Positive number of Characters expected.
        if (numCharacters < 0) {
            System.out.println(
                String.format("Error: Expected a positive number of characters.")
            );
        }

        // Creates a File object based on the filename
        File file = new File(filename);

        // Verifies if that the provided filename exists; if not, then
        // continuously asks the user for a filename
        Scanner input = KeyboardScanner.getKeyboardScanner();
        while (!file.isFile()) {
            System.out.print(String.format("The file \"%s\" doesn't exist. Please provided another filename (type \"quit\" to exit): ", filename));
            filename = input.nextLine();
            System.out.println();

            // User wants to end the game.
            if (filename.equals("quit")) {
                return;
            }

            file = new File(filename);
        }
        
        // Creates a Scanner object used to parse the data file
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(file);
        }
        catch(FileNotFoundException e) {
            fileScanner.close();
            return;
        }

        // Declares Game object
        Game game = null;
        try {
            game = new Game(fileScanner, numCharacters);
        }
        catch (InvalidParseException e) {
            System.out.println(e.getMessage());
            System.out.println("Exiting...\n");
            return;
        }

        // Test cases
        // testDirection();
        // testPlace();
        // testArtifact();

        // Plays the Game
        game.play();

        // Close input streams
        input.close();
        fileScanner.close();

        return;
    }//end main()
}//end GameTester