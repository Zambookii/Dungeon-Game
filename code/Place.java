/**
 * Place.java
 *
 * Name: Jesse Palasz
 * NetID: jpalas3
 *
 * Homework #4
 * Group: 20
 * CS 342, FALL 2018, CRN: 37162
 * November 14, 2018
 *
 * This class is intended to function as a Place / room / possible destination
 * in an "adventure" type game. Each Place has a list of Places that can be 
 * reached via entry ways between the Places, using directions of travel to 
 * specify which Place to traverse too.
*/

import java.io.*;
import java.util.*;

/*-----------------------------------------------------------------------------
 * Class: Place
 *
 * This class is intended to function as a Place / room / possible destination
 * in an "adventure" type game.
 *
 * Attribute(s):
 *      - (Private Static) TreeMap -> knownPlaces: Hash map of all created
 *                                                 Places.
 *      - (Private) int -> ID: Unique Place identifier.
 *      - (Private) String -> name: Name of the Place.
 *      - (Private) int -> version: GDF File version.
 *      - (Private) String -> description: Description of a Place.
 *      - (Private) ArrayList -> directions: A list of Direction objects used
 *                                           as entry ways between two Places.
 *      - (Private) TreeMap -> artifacts: Artifacts currently in the Place.
 *      - (Private) TreeMap -> characters: Characters currently in the Place.
 *
 * Method(s):
 *      - (Public) Place(): Constructors
 *      - (Publuc Static) -> getPlaceByID(): Returns the Place object indicated
 *                                           by the provided ID.
 *      - (Public) void -> addDirection(): Adds a description to the Place.
 *      - (Public) void -> addArtifact(): Adds an Artifact to the Place.
 *      - (Public) Artifact -> removeArtfiactByName(): Removes an Artifact from
 *                                                     the Place.
 *      - (Public) void -> addCharacter(): Adds a character to the Place.
 *      - (Public) void -> removeCharacter(): Removes a character from the 
 *                                            Place.
 *      - (Public) void -> useKey(): Uses an Artifact on the Place.
 *      - (Public) Place -> followDirection(): Travels to the Place in the
 *                                             given direction.
 *      - (Public Static) void -> printAll(): Prints all Place debug info.
 *      - (Public) void -> print(): Prints debug info.
 *      - (Public) void -> display(): Displays Place info.
 *      - (Public) String -> name(): Indicates name of the Place.
 *      - (Public) String -> description(): Indicates description of the Place.
 *      - (Public) boolean -> isExit(): Inidicates if the Place is the Exit.
 *      - (Public) boolean -> hasArtifact(): Indicates if the Place has an
 *                                           Artifact.
 *      - (Public) TreeMap -> getArtifacts(): Gets the Place's Artifacts.
 *      - (Public) ArrayList -> getDirections(): Gets the Place's Directions.
 *      - (Public) int -> getID(): Obtains Place ID.
 *      - (Public Static) Place -> getRandomPlace(): Gets a random Place.
 *      - (Public) Artifact -> getRandomArtifact(): Gets a random Artifact.
 *      - (Public) Direction -> getRandomDirection(): Gets a random Direction. 
*/
public class Place
{
    private static TreeMap<Integer, Place> knownPlaces 
                                           = new TreeMap<Integer, Place>();
    private int ID = 0;
    private String name = null;
    private int version = 0;
    private String description = null;
    private ArrayList<Direction> directions = null;
    private TreeMap<String, Artifact> artifacts = null;
    private TreeMap<Integer, Character> characters = null;

    /*-------------------------------------------------------------------------
     * Constructor: Place()
     *
     * Parameter(s):
     *      - Scanner -> dataFile: Scanner containing data file to create 
     *                               the Place.
     *      - int -> version: Version of the GDF File.
     *
     * Return:
     *      - None
     *
     * Constructs a new instance of the Place class, requiring the user to
     * provide a Scanner object which contains a file to create the Place.
     * The Place is added to a collection of created Places by the class.
    */
    public Place(Scanner dataFile, int version)
    {
        // Initialize containers
        this.description = "";
        this.directions = new ArrayList<Direction>();
        this.artifacts = new TreeMap<String, Artifact>();
        this.characters = new TreeMap<Integer, Character>();

        // Obtains GDF File version
        this.version = version;

        // Obtains and parses the next valid line of data into tokens
        String line = CleanLineScanner.getCleanLine(dataFile);
        String[] tokens = line.split("\\s+");

        /* Assigns the tokens to their corresponding attributes */

        this.ID = Integer.parseInt(tokens[0]);

        // Constructs Place name
        this.name = tokens[1];
        for (int i = 2; i < tokens.length; i++) {
            this.name += " " + tokens[i];
        }

        // Determines the total number of description lines and and reads them
        // in line-by-line
        line = CleanLineScanner.getCleanLine(dataFile);
        int numDescLines = Integer.parseInt(line);
        for (int i = 0; i < numDescLines; i++) {
            line = CleanLineScanner.getCleanLine(dataFile);
            this.description += line + "\n";
        }

        // Adds the Place to the collection of created Places.
        knownPlaces.put(this.ID, this);
    }//end Constructor


    /*-------------------------------------------------------------------------
     * Constructor: Place()
     *
     * Parameter(s):
     *      - int -> ID: Unique Place identifier.
     *      - String -> name: Name of the Place.
     *      - String -> description: Description of the Place.
     *      - int -> version: Version of the GDF File.
     *
     * Return:
     *      - None
     *
     * Constructs a new instance of the Place class, requiring the user to
     * provide the ID, name, and description of the place. The Place is added 
     * to a collection of created Places by the class.
    */
    public Place(int ID, String name, String description, int version)
    {
        this.directions = new ArrayList<Direction>();
        this.artifacts = new TreeMap<String, Artifact>();
        this.characters = new TreeMap<Integer, Character>();

        this.ID = ID;
        this.name = name;
        this.description = description;
        this.version = version;

        // Adds the Place to the collection of created Places.
        knownPlaces.put(this.ID, this);
    }//end Constructor


    /*-------------------------------------------------------------------------
     * Function: getPlaceByID()
     *
     * Parameter(s):
     *      - int - > ID: Unique ID of a Place.
     *
     * Return:
     *      - Place: Place object that corresponds to the given unique ID.
     *
     * This function indicates the Place object assicaited with a given ID.
    */
    public static Place getPlaceByID(int ID)
    {
        return knownPlaces.get(ID);
    }//end getPlaceByID()


     /*-------------------------------------------------------------------------
      * Function: addDirection()
      *
      * Parameter(s):
      *      - Direction -> dir: Direction object used by the Place.
      *
      * Return:
      *      - None
      *
      * This function appends a Direction object to the list of Direction
      * objects stored by the Place for use.
     */
    public void addDirection(Direction dir)
    {
        this.directions.add(dir);

        return;
    }//end addDirection()


    /*-------------------------------------------------------------------------
     * Function: addArtifact()
     *
     * Parameter(s):
     *      - Artifact -> a: Artifact of interest.
     *
     * Return:
     *      - None
     *
     * This function adds an Artifact to the Place's collection of Artifacts.
    */
    public void addArtifact(Artifact a)
    {
        this.artifacts.put(a.name().toUpperCase(), a);

        return;
    }//end addArtifact()


    /*-------------------------------------------------------------------------
     * Function: removeArtifactByName()
     *
     * Parameter(s):
     *      - String -> name: Name of the Artifact.
     *
     * Return:
     *      - Artifact: The corresponding artifact is returned if it exists and
     *                  movable.
     *
     * This function obtains an Artifact from the Place, removing it from its
     * collection of Artifacts.
    */
    public Artifact removeArtifactByName(String name)
    {
        // Removes the Artifact of interest
        Artifact a = artifacts.remove(name.toUpperCase());

        // Checks to see if the Artifact is immovable
        if (a.weight() < 0) {
            artifacts.put(name.toUpperCase(), a);

            return null;
        }

        return a; // Artifact if found; otherwises, null
    }//end removeArtifactByName()
    

    /*-------------------------------------------------------------------------
     * Function: addCharacter()
     *
     * Parameter(s):
     *      - Character -> c: Character object.
     *
     * Return:
     *      - None
     *
     * This function adds a character to the collection of characters in the
     * Place.
    */
    public void addCharacter(Character c)
    {
        this.characters.put(c.getID(), c);

        return;
    }//end addCharacter()


    /*-------------------------------------------------------------------------
     * Function: removeCharacter()
     *
     * Parameter(s):
     *      - Character -> c: Character object.
     *
     * Return:
     *      - None
     *
     * This function removes a character from the collection of characters in
     * the Place.
    */
    public void removeCharacter(Character c)
    {
        // Removes a given character
        if (this.characters.containsKey(c.getID())) {
            this.characters.remove(c.getID());
        }

        return;
    }//end removeCharacter()


    /*-------------------------------------------------------------------------
     * Function: useKey()
     *
     * Parameter(s):
     *      - Artifact -> a: Artifact to be used.
     *
     * Return:
     *      - None
     *
     * This function uses an Artifact on a as a key to unlock entry ways within
     * the present Place.
    */
    public void useKey(Artifact a)
        throws UsedKeyException
    {
        String messages = "";

        int result = 0;
        for (Direction d : directions) {
            try {
                result = d.useKey(a);
            }
            catch (UsedKeyException e) {
                messages += e.getMessage() + "\n";
            }
        }

        if (messages.equals("")) {
            throw new UsedKeyException(messages);
        }

        if (result == 0) {
            throw new UsedKeyException(
                String.format("\"%s\" didn't do anything!", a.name())
            );
        }

        return;
    }//useKey()


    /*-------------------------------------------------------------------------
     * Function: followDirection()
     *
     * Parameter(s):
     *      - String -> s: Direction of travel.
     *
     * Return:
     *      - Place: The Place associated with the given direction is returned,
     *               if accessible; otherwise, the present Place is returned.
     *
     * This function checks to see if the provided direction of travel is
     * accessible from the Place. If not, the present Place is returned;
     * otherwise, the Place associated with the given direction is returned.
    */
    public Place followDirection(String s)
        throws InvalidDirectionException, LockedDoorException
    {
        // Loops through all the Place's directions
        for (Direction d : directions) {
            // Matches the given direction with the Place's direction
            if (d.match(s)) {
                // Checks to see if the entry way is locked
                if (d.isLocked()) {
                    throw new LockedDoorException(
                        "This entry way appears to be locked...\n"
                    );
                }
                // Directions match
                return d.follow();
            }
        }

        // Direction did not match
        throw new InvalidDirectionException(
            String.format("\"%s\" is not a valid direction of travel.", s)
        );
    }//end followDirection()


    /*-------------------------------------------------------------------------
     * Function: printAll()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - None
     *
     * This function prints out Place information for all known Places.
    */
    public static void printAll()
    {
        System.out.println("All Created Place Objects:");
        System.out.println("--------------------------");
        for (Map.Entry<Integer, Place> entry : knownPlaces.entrySet()) {
            entry.getValue().print();
        }

        return;
    }//end printAll()


    /*-------------------------------------------------------------------------
     * Function: print()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - None
     *
     * This function prints out Place information, used for debugging purposes.
    */
    public void print()
    {
        System.out.println(String.format("Place ID: %s", this.ID));
        System.out.println(String.format("Place Name: %s", this.name));
        System.out.print("Place Description: ");
        System.out.println(this.description);
        System.out.println(String.format("Place Number of Directions Present:"+
                                         " %s", this.directions.size()));
        System.out.println("Place Directions:");
        for (Direction d : this.directions) {
            System.out.println(d.toString());
        }
        System.out.println(String.format("Place Number of Artifacts Present:"+
                                         " %s", this.artifacts.size()));
        System.out.println("Place Artifacts:");
        for (Map.Entry<String, Artifact> pair : this.artifacts.entrySet()) {
            System.out.println(pair.getValue().name());
        }
        System.out.println(String.format("Place Number of Characters Present:"+
                                         " %s", this.characters.size()));
        System.out.println("Place Characters:");
        for (Map.Entry<Integer, Character> pair : this.characters.entrySet()) {
            System.out.println(pair.getValue().name());
        }

        return;
    }//end print()


    /*-------------------------------------------------------------------------
     * Function: display()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - None
     *
     * This function displays the name and desciption of the Place.
    */
    public void display(Character c)
    {
        if (c.typeIO() == IO.GUI_3) {
            return;
        }
        else {
            displayContent(c);
        }

        return;
    }//end display()


    private void displayContent(Character c)
    {
        // String for improved information output
        String underline = "";
        for (int i = 0; i < this.name.length(); i++)
            underline += "-";

        // Displays Place name
        c.displayOutputMessage(String.format("%s", this.name));
        c.displayOutputMessage(underline);
        c.displayOutputMessage(this.description);

        // Displays the characters in the Place
        c.displayOutputMessage(">> Characters Present <<\n");
        if (this.characters.size() == 0) {
            c.displayOutputMessage("None\n"); // Place has no Artifacts
        }
        else {
            for (Map.Entry<Integer, Character> pair : this.characters.entrySet()) {
                Character ch = pair.getValue();
                c.displayOutputMessage(String.format("%s | Health: %s Hunger: %s", 
                                                 ch.name(), ch.health(), ch.hunger()));
            }
            c.displayOutputMessage("");
        }

        // Displays Place's accompanying Artifacts
        c.displayOutputMessage(">> Artifacts Present <<\n");
        if (this.artifacts.size() == 0) {
            c.displayOutputMessage("None\n"); // Place has no Artifacts
        }
        else {
            for (Map.Entry<String, Artifact> pair : this.artifacts.entrySet()) {
                Artifact a = pair.getValue();
                c.displayOutputMessage(a.name());
                c.displayOutputMessage(String.format("Points: %s", a.value()));
                c.displayOutputMessage(String.format("Mobility: %s", a.weight()));
                c.displayOutputMessage(a.description());
            }
        }

        return;
    }   

    public String playersPresent()
    {
        String output = "";
        if (this.characters.size() == 0) {
            output += "None"; // Place has no Artifacts
        }
        else {
            for (Map.Entry<Integer, Character> pair : this.characters.entrySet()) {
                Character ch = pair.getValue();
                output += String.format("%s | Health: %s Hunger: %s\n\n", 
                                        ch.name(), ch.health(), ch.hunger());
            }
        }

        return output;
    }


    public String artifactsPresent()
    {
        String output = "";
        if (this.artifacts.size() == 0) {
            output += "None"; // Place has no Artifacts
        }
        else {
            for (Map.Entry<String, Artifact> pair : this.artifacts.entrySet()) {
                Artifact a = pair.getValue();
                output += a.name() + "\n";
                output += String.format("Points: %s", a.value()) + "\n";
                output += String.format("Mobility: %s", a.weight()) + "\n";
                output += a.description() + "\n";
            }
        }

        return output;
    }


    /*-------------------------------------------------------------------------
     * Function: name()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - String: Name of the Place.
     *
     * This function indicates the name of the Place.
    */
    public String name()
    {
        return this.name;
    }//end name()


    /*-------------------------------------------------------------------------
     * Function: description()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - String: Description of the Place.
     *
     * This function indicates the description of the Place.
    */
    public String description()
    {
        return this.description;
    }//end description()


    /*-------------------------------------------------------------------------
     * Function: isExit()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - boolean: TRUE if the Place is the Exit; otherwise, FALSE.
     *
     * This function determines if the Place corresponds to the Exit Place
     * (Place ID of 1).
    */
    public boolean isExit()
    {
        return this.ID == 1;
    }//end isExit()


    /*-------------------------------------------------------------------------
     * Function: hasArtifact()
     *
     * Parameter(s):
     *      - String -> name: Name of the Artifact.
     *
     * Return:
     *      - boolean: TRUE if the Place has the stated Artifact; otherwise,
     *                 FALSE.
     *
     * This function returns indicates if the Place has the stated Artifact.
    */
    public boolean hasArtifact(String name)
    {
        // Locates the Artifact
        Artifact a = artifacts.get(name.toUpperCase());
        if (a != null) {
            return true; // Artifact found
        }

        return false; // Artfact not found
    }//end hasArtifact()


    /*-------------------------------------------------------------------------
     * Function: getArtifacts()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - TreeMap<String, Artifact>: Artifacts present in the Place.
     *
     * This function returns the artifacts in the Place.
    */
    public TreeMap<String, Artifact> getArtifacts()
    {
        return this.artifacts;
    }//end getArtifacts()


    /*-------------------------------------------------------------------------
     * Function: getDirections()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - ArrayList<Direction>: Directions present in the Place.
     *
     * This function returns the artifacts in the Place.
    */
    // public ArrayList<Direction> getDirections()
    // {
    //     return this.directions;
    // }//end getDirections()


    /*-------------------------------------------------------------------------
     * Function: getID()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - int -> Place ID.
     *
     * This function returns the Place's ID.
    */
    public int getID()
    {
        return this.ID;
    }//end getID()


    /*-------------------------------------------------------------------------
     * Function: getRandomPlace()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - Place: Randomly selected Place.
     *
     * This function obtains a radonly selected Place object.
    */
    public static Place getRandomPlace()
    {
        // Create random number generator
        Random rand = new Random();

        // Verify that the Place has Artifacts
        if (knownPlaces.size() == 0) {
            return null;
        }

        // Obtains Place IDs
        ArrayList<Integer> placeIDs = new ArrayList<Integer>();
        for (int id : knownPlaces.keySet()) {
            placeIDs.add(id);
        }
        
        // Obtains a randomly selected Place.
        Place temp = null;
        int ndx = 0;
        while (true) {

            // Obtains Random Artifact
            ndx = rand.nextInt(knownPlaces.size());
            temp = knownPlaces.get(placeIDs.get(ndx));

            if (temp.ID != 0 && temp.ID != 1) {
                break;
            }
        }
        
        return temp;
    }//end getRandomPlace()


    /*-------------------------------------------------------------------------
     * Function: getRandomArtifact()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - Artifact: Randomly selected Artifact.
     *
     * This function obtains a radomly selected Artifact object.
    */
    public Artifact getRandomArtifact()
    {
        // Craete random number generator
        Random rand = new Random();

        // Verify that the Place has Artifacts
        if (this.artifacts.size() == 0) {
            return null;
        }

        // Obtain a random Artifact index
        int ndx = rand.nextInt(this.artifacts.size());

        // Obtains Artifact names
        ArrayList<String> artifactNames = new ArrayList<String>();
        for (String n : this.artifacts.keySet()) {
            artifactNames.add(n);
        }
        String name = artifactNames.get(ndx);
        
        return this.artifacts.get(name.toUpperCase());
    }//end getRandomArtifact()


    /*-------------------------------------------------------------------------
     * Function: getRandomDirection()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - Artifact: Randomly selected Direction.
     *
     * This function obtains a radomly selected Direction object.
    */
    public Direction getRandomDirection()
    {
        // Create random number generator
        Random rand = new Random();

        // Verify that the Place has Artifacts
        if (this.directions.size() == 0) {
            return null;
        }

        // Obtain a random Artifact index
        int ndx = rand.nextInt(this.directions.size());
        
        return this.directions.get(ndx);
    }//end getRandomDirection()


    /*-------------------------------------------------------------------------
     * Function: getRandomCharacter()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - Character: Randomly selected Character.
     *
     * This function obtains a radomly selected Character object.
    */
    public Character getRandomCharacter()
    {
        // Create random number generator
        Random rand = new Random();

        // Verify that the Place has Characters
        if (this.characters.size() == 0) {
            return null;
        }

        // Obtains Place IDs
        ArrayList<Integer> characterIDs = new ArrayList<Integer>();
        for (int id : this.characters.keySet()) {
            characterIDs.add(id);
        }
        
        // Obtains a randomly selected Character.
        int ndx = rand.nextInt(characters.size());
        
        return characters.get(characterIDs.get(ndx));
    }//end getRandomCharacter()

}//end Place
