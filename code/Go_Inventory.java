/**
 * Go_Inventory.java
 *
 * Name: Luis Enrique Love
 * NetID: llove3
 *
 * Homework #4
 * Group: 20
 * CS 342, FALL 2018, CRN: 37162
 * November 14, 2018
 *
 * This class is intended to function as a "display my inventory" action
 * performed by a Character in an "adventure" type game.
*/

import java.io.*;
import java.util.*;

/*-----------------------------------------------------------------------------
 * Class: Go_Inventory
 *
 * This class is intended to function as a "display my inventory" action.
 *
 * Attribute(s):
 *      - (Private) Character -> character: Character to act upon.
 *      
 * Method(s):
 *      - (Public) Go_Drop): Constructor
 *      - (Public) void -> execute(): Executes move.
*/
public class Go_Inventory
    extends Move
{
    Character character = null;

    /*-------------------------------------------------------------------------
     * Constructor: Go_Inventory()
     *
     * Parameter(s):
     *      - Character -> c: Character to act upon.
     *
     * Return:
     *      - None
     *
     * Constructs a new instance of the Go_Inventory class, assigning the
     * various objects to be acted upon.
    */
    public Go_Inventory(Character c)
    {
        this.character = c;
    }//end Constructor


    /*-------------------------------------------------------------------------
     * Function: execute()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - None
     *
     * This function displays the inventory of the given Character.
    */
    public void execute()
    {
        // Obtains the Artifacts possessed by the Charater
        TreeMap<String, Artifact> artifacts = this.character.getArtifacts();

        // Inventory is empty
        if (artifacts.size() == 0) {
            this.character.displayOutputMessage("Inventory is currently empty!\n");
            return;
        }

        int totalPoints = 0; // Total points of all Artifacts
        int totalWeight = 0; // Total weight of all Artifacts

        // Displays Artifact properties
        this.character.displayOutputMessage(String.format("%-20s%10s%10s", "Name", "Points", "Weight"));
        for (Map.Entry<String, Artifact> pair : artifacts.entrySet()) {
            Artifact a = pair.getValue();
            String weight = String.format("%skg", a.weight());
            this.character.displayOutputMessage(String.format("%-20s%10s%10s", a.name(), a.value(), weight));

            totalPoints += a.value();
            totalWeight += a.weight();
        }
        // Displays total weight and point value of all Artifacts obtained
        this.character.displayOutputMessage(String.format("\n%-20s%10s%8skg\n", "Total", totalPoints, totalWeight));

        return;
    }//end execute()

}//end Go_Inventory