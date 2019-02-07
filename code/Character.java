/**
 * Character.java
 *
 * Name: Aleksandar Ilic
 * NetID: ailic3
 *
 * Homework #4
 * Group: 20
 * CS 342, FALL 2018, CRN: 37162
 * November 14, 2018
 *
 * This class is intended to function as a Character in an "adventure" type 
 * game. The Character is capable of making decisions about what course of
 * action he/she/it will take in order to obtain its goal.
*/

import java.io.*;
import java.util.*;

/*-----------------------------------------------------------------------------
 * Class: Character
 *
 * This class is intended to function as a Character in an "adventure" type 
 * game.
 *
 * Attribute(s):
 *      - (Private Static) TreeMap -> allCharacters: Hash map of all created
 *                                                   Characters.
 *      - (Private) int -> ID: Unique Character identifier.
 *      - (Private) String -> name: Name of the Character.
 *      - (Private) String -> description: Description of the Character.
 *      - (Private) int -> version: Version of the Game created.
 *      - (Private) boolean -> activeStatus: Indicates if character is still
 *                                           in the game.
 *      - (Private) Place -> currentPlace: Current Place of the character.
 *      - (Private) TreeMap -> artifacts: Artifacts possessed by the Character.
 *      - (Protected) DecisionMaker -> decisionMaker: Makes move decisions.
 *
 * Method(s):
 *      - (Public) Character(): Constructors
 *      - (Public Static) Artifact -> getCharacterByName(): Obtains a Character
 *                                                          by its ID.
 *      - (Public) void -> makeMove(): Chooses a move to take.
 *      - (Public) void -> print(): Prints debug info.
 *      - (Public) void -> display(): Displays Character info.
 *      - (Public) Artifact -> getArtifactByName(): Obtains the desired
 *                                                  Artifact.
 *      - (Public) TreeMap -> getArtifacts(): Obtains Character Artifacts.
 *      - (Public) int -> getID(): Obtains Character ID.
 *      - (Public) String -> name(): Obtains Character name.
 *      - (Public) void -> quit(): Character no longer playable.
 *      - (Public) boolean -> isActive(): Indicates of Character is playable.
 *      - (Public) void -> addArtifact(): Gives the Character an Artifact.
 *      - (Public) void -> setDecisionMaker(): Assigns Character Decision 
 *                                             Maker.
 *      - (Public) void -> setCurrentPlace(): Sets Character's current Place.
 *      - (Public Static) TreeMap -> getCharacters(): Obtains all Characters.
*/
public class Character
{
    private static TreeMap<Integer, Character> allCharacters 
                                           = new TreeMap<Integer, Character>();
    private int ID = 0;
    private String name = null;
    private String description = null;
    private int version = 0;
    private boolean activeStatus = true;
    private int health = 100;
    private int hunger = 100;
    private int strength = 5;
    private Place currentPlace = null;
    private TreeMap<String, Artifact> artifacts = null;
    protected DecisionMaker decisionMaker = null;
    protected IO io = null;

    /*-------------------------------------------------------------------------
     * Constructor: Character()
     *
     * Parameter(s):
     *      - Scanner -> dataFile: Scanner containing data file to create 
     *                               the Character.
     *      - int -> version: Version of the GDF File.
     *
     * Return:
     *      - None
     *
     * Constructs a new instance of the Character class, requiring the user to
     * provide a Scanner object which contains a file to create the Character.
     * The Character is added to a collection of created Characters by the 
     * class.
    */
    public Character(Scanner dataFile, int version)
        throws InvalidParseException
    {
        // Instantiates attributes
        this.artifacts = new TreeMap<String, Artifact>();
        this.description = "";
        this.version = version;
        this.io = new IO();

        if (version < 50) {
            fileParser4X(dataFile);
        }
        else if (version == 50) {
            fileParser50(dataFile);
        }
        else if (version > 50) {
            fileParser5X(dataFile);
        }

        this.decisionMaker = null;
    }//end Constructor


    private void fileParser4X(Scanner scanner)
    {
        // Obtains and parses the next valid line of data into tokens
        String line = CleanLineScanner.getCleanLine(scanner);
        String [] tokens = line.split("\\s+");

        /* Assigns the tokens to their corresponding attributes */
        
        this.ID = Integer.parseInt(tokens[0]);

        // Constructs the Character name
        this.name = tokens[1];
        for (int i = 2; i < tokens.length; i++) {
            this.name += " " + tokens[i];
        }

        // Determines the total number of description lines and and reads them
        // in line-by-line
        line = CleanLineScanner.getCleanLine(scanner);
        int numDescLines = Integer.parseInt(line);
        for (int i = 0; i < numDescLines; i++) {
            line = CleanLineScanner.getCleanLine(scanner);
            this.description += line + "\n";
        }

        // Adds the Character to the collection of created Characters
        allCharacters.put(this.ID, this);

        return;
    }


    private void fileParser50(Scanner scanner)
        throws InvalidParseException
    {
        // Obtains and parses the next valid line of data into tokens
        String line = CleanLineScanner.getCleanLine(scanner);
        String [] tokens = line.split("\\s+");

        /* Assigns the tokens to their corresponding attributes */
        
        // Assigns starting Place
        int placeID = Integer.parseInt(tokens[0]);
        
        if (placeID == 0) {
            this.currentPlace = Place.getRandomPlace();
            
            // No Places exist
            if (this.currentPlace == null) {
                throw new InvalidParseException(
                    "Error: No Place objects exist to randomly "+
                    "assign a Character"
                );
            }
        }
        else {
            this.currentPlace = Place.getPlaceByID(placeID);
        }

        // Assigns Character ID
        line = CleanLineScanner.getCleanLine(scanner);
        tokens = line.split("\\s+");
        this.ID = Integer.parseInt(tokens[0]);

        // Constructs the Character name
        this.name = tokens[1];
        for (int i = 2; i < tokens.length; i++) {
            this.name += " " + tokens[i];
        }

        // Determines the total number of description lines and and reads them
        // in line-by-line
        line = CleanLineScanner.getCleanLine(scanner);
        int numDescLines = Integer.parseInt(line);
        for (int i = 0; i < numDescLines; i++) {
            line = CleanLineScanner.getCleanLine(scanner);
            this.description += line + "\n";
        }

        // Adds the Character to the collection of created Characters
        allCharacters.put(this.ID, this);

        return;
    }


    private void fileParser5X(Scanner scanner)
        throws InvalidParseException
    {
        // Obtains and parses the next valid line of data into tokens
        String line = CleanLineScanner.getCleanLine(scanner);
        String [] tokens = line.split("\\s+");

        /* Assigns the tokens to their corresponding attributes */
        
        // Assigns starting Place
        int placeID = Integer.parseInt(tokens[0]);
        
        if (placeID == 0) {
            this.currentPlace = Place.getRandomPlace();
            
            // No Places exist
            if (this.currentPlace == null) {
                throw new InvalidParseException(
                    "Error: No Place objects exist to randomly "+
                    "assign a Character"
                );
            }
        }
        else {
            this.currentPlace = Place.getPlaceByID(placeID);
        }

        // Assigns Character ID
        line = CleanLineScanner.getCleanLine(scanner);
        tokens = line.split("\\s+");
        this.ID = Integer.parseInt(tokens[0]);

        // Constructs the Character name
        this.name = tokens[1];
        for (int i = 2; i < tokens.length; i++) {
            this.name += " " + tokens[i];
        }

        // Determines the total number of description lines and and reads them
        // in line-by-line
        line = CleanLineScanner.getCleanLine(scanner);
        int numDescLines = Integer.parseInt(line);
        for (int i = 0; i < numDescLines; i++) {
            line = CleanLineScanner.getCleanLine(scanner);
            this.description += line + "\n";
        }

        //Determines the health and hunger of the Character
        line = CleanLineScanner.getCleanLine(scanner);
        this.health = Integer.parseInt(line);

        line = CleanLineScanner.getCleanLine(scanner);
        this.hunger = Integer.parseInt(line);

        // Adds the Character to the collection of created Characters
        allCharacters.put(this.ID, this);

        return;
    }


    /*-------------------------------------------------------------------------
     * Constructor: Character()
     *
     * Parameter(s):
     *      - int -> ID: Unique Character identifier.
     *      - String -> name: Name of the Character.
     *      - String -> description: Description of the Character.
     *      - int -> version: Version of the GDF File.
     *
     * Return:
     *      - None
     *
     * Constructs a new instance of the Character class, requiring the user to
     * provide the ID, name, and description of the Character. The Character is
     * added to a collection of created Characters by the class.
    */
    public Character(int ID, String name, String description, int version)
    {
        this.artifacts = new TreeMap<String, Artifact>();
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.version = version;
        this.io = new IO();

        // Adds the Character to the collection of created Characters
        allCharacters.put(this.ID, this);
        this.decisionMaker = null;
    }//end Constructor


    /*-------------------------------------------------------------------------
     * Function: getCharacterByID()
     *
     * Parameter(s):
     *      - int - > ID: Unique ID of a Character.
     *
     * Return:
     *      - Character: Character object that corresponds to the given unique 
     *                   ID.
     *
     * This function obtains a Character object assicaited with a given ID.
    */
    public static Character getCharacterByID(int ID)
    {
        return allCharacters.get(ID);
    }//end getCharacterByID()


    /*-------------------------------------------------------------------------
     * Function: makeMove()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - Move: Move object to conduct a desired move.
     *
     * This function obtains a Move object representing the character's
     * desired move.
    */
    public Move makeMove()
    {
        updateGUI();
        
        return this.decisionMaker.getMove(this, this.currentPlace);
    }//end makeMove()


    /*-------------------------------------------------------------------------
     * Function: print()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - None
     *
     * This function prints out Character information, used for debugging 
     * purposes.
    */
    public void print()
    {
        this.io.display(this.name);
        this.io.display(String.format("Character ID: %s", this.ID));
        this.io.display(String.format("Character Name: %s", this.name));
        this.io.display("Character Description: ");
        this.io.display(this.description);
        this.io.display(String.format("Character Still Playing: " +
                                         " %s", this.activeStatus));
        this.io.display(String.format("Character Current Place:" +
                                         " %s", this.currentPlace.name()));
        this.io.display(String.format("Character Number of Artifacts: "+
                                         "%s", this.artifacts.size()));

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
     * This function displays the Name and Description of a Character.
    */
    public void display()
    {
        this.io.display(this.name);
        this.io.display(this.description);
        // this.io.display();

        return;
    }//end display()


    public void displayOutputMessage(String message)
    {
        this.io.display(message);

        return;
    }


    public String getInput()
    {
        return this.io.getLine();
    }


    public void switchInterface(int val)
    {
        this.io.selectInterface(val);

        return;
    }

    public int typeIO() 
    {
        return this.io.ioType();
    }


    public void updateGUI()
    {
        this.io.updateGUI(this, this.currentPlace);

        return;
    }


    /*-------------------------------------------------------------------------
     * Function: getArtifactByName()
     *
     * Parameter(s):
     *      - String -> name: Name of the Artifact.
     *
     * Return:
     *      - Artifact: Desicred Artifact specified by name
     *
     * This function obtains an Artifact from the Character.
    */
    public Artifact getArtifactByName(String name)
    {
        return artifacts.get(name.toUpperCase());
    }//getArtifactByName()


    /*-------------------------------------------------------------------------
     * Function: getArtifacts()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - TreeMap<String, Artifact>: Artifacts possessed by the Character.
     *
     * This function returns the artifacts possessed by the Character.
    */
    public TreeMap<String, Artifact> getArtifacts()
    {
        return this.artifacts;
    }//end getArtifacts()


    /*-------------------------------------------------------------------------
     * Function: getID()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - int -> Character ID.
     *
     * This function returns the Character's ID.
    */
    public int getID()
    {
        return this.ID;
    }//end getID()


    /*-------------------------------------------------------------------------
     * Function: name()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - String -> Character name.
     *
     * This function returns the Character's name.
    */
    public String name()
    {
        return this.name;
    }//end name()


    /*-------------------------------------------------------------------------
     * Function: quit()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - None
     *
     * This function causes the Character to no longer be playable in the game.
    */
    public void quit()
    {
        this.activeStatus = false;

        // Redistributes the character owned Artifacts throughout the game
        for (Artifact a : this.artifacts.values()) {
            try {
                a.sendToOriginalPlace();
            }
            catch (InvalidParseException e) {
                return;
            } 
        }

        return;
    }//end quit()


    /*-------------------------------------------------------------------------
     * Function: isActive()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - boolean -> TRUE if the Character is playable; otherwise, FALSE.
     *
     * This function indicates if the Character is playable.
    */
    public boolean isActive()
    {
        return this.activeStatus;
    }//end isActive()


    /*-------------------------------------------------------------------------
     * Function: addArtifact()
     *
     * Parameter(s):
     *      - Artifact -> a: The Artifact to be added.
     *
     * Return:
     *      - None
     *
     * This function adds an Artifact to the Character's collection of 
     * Artifacts.
    */
    public void addArtifact(Artifact a)
    {
        artifacts.put(a.name().toUpperCase(), a);
        
        return;
    }//addArtifact()


    /*-------------------------------------------------------------------------
     * Function: setDecisionMaker
     *
     * Parameter(s):
     *      - DecisionMaker -> d: Character's decision maker.
     *
     * Return:
     *      - None
     *
     * This function assigns a decision maker to the Character.
    */
    public void setDecisionMaker(DecisionMaker d)
    {
        this.decisionMaker = d;

        return;
    }//end setDecisionMaker()


    /*-------------------------------------------------------------------------
     * Function: setCurrentPlace()
     *
     * Parameter(s):
     *      - Place -> p: Current Place of the Character.
     *
     * Return:
     *      - None
     *
     * This function sets the Character's current Place.
    */
    public void setCurrentPlace(Place p)
    {
        this.currentPlace = p;
        p.addCharacter(this);

        return;
    }//end setCurrentPlace()


    /*-------------------------------------------------------------------------
     * Function: getCharacters()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - TreeMap<Integer, Character>: All created Characters.
     *
     * This function obtains all created Characters.
    */
    public static TreeMap<Integer, Character> getCharacters()
    {
        return allCharacters;
    }//end getCharacters()


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


    public int hunger()
    {
        return this.hunger;
    }

    public int health()
    {
        return this.health;
    }

    public void heal(int val)
        throws CharDeathException
    {
        this.health += val;
        
        if (this.health <= 0) {
            quit();
            this.currentPlace.removeCharacter(this);
            throw new CharDeathException(
                String.format("\"%s\" has been killed!\n",
                              this.name)
            );
        }

        return;
    }

    public void eat(int val)
    {
        this.hunger += val;

        if (this.hunger > 100) {
            this.hunger = 100;
        }

        return;
    }

    public void starve(int val)
        throws StarveDeathException
    {
        if (this.hunger <= 0) {
            try {
                heal(-5);
            }
            catch (CharDeathException e) {
                throw new StarveDeathException(
                    String.format("\"%s\" has starved to death!\n",
                                  this.name)
                );
            }
        }
        else {
            this.hunger -= val;
            if (this.hunger <= 0) {
                this.hunger = 0;
            }
        }

        return;
    }

}//end Character