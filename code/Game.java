/**
 * Game.java
 *
 * Name: Luis Enrique Love
 * NetID: llove3
 *
 * Homework #4
 * Group: 20
 * CS 342, FALL 2018, CRN: 37162
 * November 14, 2018
 *
 * This class is intended to function as a Game/board for an "adventure"
 * type game. The game has atleast one human player, and any number of NPCs.
 * In order to "win" the game, the humnan players must reach the "Exit" Place.
 * If all human players reach the "Exit" place, or all human players quit the
 * game, then the Game ends.
*/

import java.io.*;
import java.util.*;

/*-----------------------------------------------------------------------------
 * Class: Game
 *
 * This class is intended to function as a Game/board for an "adventure"
 * type game.
 *
 * Attribute(s):
 *      - (Private) String -> name: Name of the present Game.
 *      - (Private) int -> version: Version of the Game created.
 *      - (Private) int -> numPlaces: Number of Places in the Game.
 *      - (Private) int -> numDirections: Number of Directions in the Game.
 *      - (Private) int -> numArtifacts: Number of Artifacts in the Game.
 *      - (Private) int -> numCharacters: Number of Characters in the Game.
 *      - (Private) TreeMap -> characters: All Character Objects in the Game.
 *      - (Private) TreeMap -> playersReal: All Human Character Objects in the
 *                                          Game.
 *      - (Private) TreeMap -> playersAI: All AI Character Objects in the Game.
 *      
 * Method(s):
 *      - (Public) Game(): Constructors
 *      - (Public) void -> play(): Plays the Game.
 *      - (Public) void -> print(): Prints debug info.
 *      - (Private) void -> removeCharacterByID(): Removes a character from the
 *                                                 game by thier ID.
 *      - (Private) void -> createCharacters(): Creates a specified number of
 *                                              Characters to play the game.
*/
public class Game
{
    private String name = null;
    private int version = 0;
    private int numPlaces = 0;
    private int numDirections = 0;
    private int numCharacters = 0;
    private int numArtifacts = 0;
    private int numBosses = 0;
    private TreeMap<Integer, Character> characters = null;
    private TreeMap<Integer, Character> playersReal = null;
    private TreeMap<Integer, Character> playersAI = null;
    private TreeMap<Integer, Character> bosses = null;

    private int numCharactersWanted = 0;
    private Place firstPlace = null;

    /*-------------------------------------------------------------------------
     * Constructor: Game()
     *
     * Parameter(s):
     *      - Scanner -> dataFile: Scanner containing data file to create 
     *                             the Place.
     *
     * Return:
     *      - None
     *
     * Constructs a new instance of the Game class, requiring the user to
     * provide a Scanner object which contains a file to create the Game.
    */
    public Game(Scanner dataFile) 
        throws InvalidParseException
    {
        // Initialize Character containers
        this.characters = new TreeMap<Integer, Character>();
        this.playersReal = new TreeMap<Integer, Character>();
        this.playersAI = new TreeMap<Integer, Character>();
        this.bosses = new TreeMap<Integer, Character>();

        // Parses the data file
        parseFile(dataFile);
    }//end Constructor


    /*-------------------------------------------------------------------------
     * Constructor: Game()
     *
     * Parameter(s):
     *      - Scanner -> dataFile: Scanner containing data file to create 
     *                             the Place.
     *      - int -> numPlayers: Number of players in the Game.
     *
     * Return:
     *      - None
     *
     * Constructs a new instance of the Game class, requiring the user to
     * provide a Scanner object which contains a file to create the Game and
     * the total number of Characters to create.
    */
    public Game(Scanner dataFile, int numPlayers) 
        throws InvalidParseException
    {
        // Initialize Character containers
        this.characters = new TreeMap<Integer, Character>();
        this.playersReal = new TreeMap<Integer, Character>();
        this.playersAI = new TreeMap<Integer, Character>();
        this.bosses = new TreeMap<Integer, Character>();
        this.numCharactersWanted = numPlayers;

        // Parses the data file
        parseFile(dataFile);
    }//end Constructor


    /*-------------------------------------------------------------------------
     * Function: parseFile()
     *
     * Parameter(s):
     *      - Scanner -> dataFile: Scanner containing data file to create 
     *                             the Place.
     *
     * Return:
     *      - None
     *
     * This function parses a data file to create the Game.
    */
    private void parseFile(Scanner dataFile)
        throws InvalidParseException
    {
        // Keeps track of the number of made Characters
        int numCharactersMade = 0;

        String line = null; // Line of data from the Scanner
        String[] tokens = null; // Tokenized line (whitespace as delimiter)

        /* Reads GDF Header Information */

        // Looks for the GDF magic word as the first line in the file
        line = CleanLineScanner.getCleanLine(dataFile);
        if (!line.startsWith("GDF")) {
            throw new InvalidParseException("Error: Expected GDF");
        }

        // Parses the header info
        tokens = line.split("\\s+");

        // Sets game version
        this.version = (int) (Double.parseDouble(tokens[1]) * 10);

        // Sets the name of the Game
        this.name = tokens[2];
        for (int i = 3; i < tokens.length; i++) {
            this.name += " " + tokens[i];
        }
        
        // Parses the rest of the GDF File Section Headers
        line = CleanLineScanner.getCleanLine(dataFile); // Gets next header
        while (!line.equals("")) {

            /* Parses PLACES GDF Section Header */
            if (line.startsWith("PLACES")) {

                // Gets the number of Place objects to create
                tokens = line.split("\\s+");
                this.numPlaces = Integer.parseInt(tokens[1]);

                // Creates the specified number of Place objects
                for (int i = 0; i < this.numPlaces; i++) {
                    Place temp = new Place(dataFile, this.version);

                    // First Place encountered in the Game
                    if (this.firstPlace == null) {
                        this.firstPlace = temp;
                    }
                }

                // Creates the reserved "Exit" and "Nowhere" Places.
                if (this.numPlaces != 0) {
                    new Place(0, "Nowhere", "Unknown...", this.version);
                    new Place(1, "Exit", "The world is yours!", this.version);
                    this.numPlaces += 2;
                }
            }            

            /* Parses DIRECTIONS GDF Section Header */
            else if (line.startsWith("DIRECTIONS")) {

                // Creates the specified number of Direction objects
                tokens = line.split("\\s+");
                this.numDirections = Integer.parseInt(tokens[1]);
                for (int i = 0; i < this.numDirections; i++) {
                    new Direction(dataFile, this.version);
                }
            }
            
            /* Parses CHARACTERS GDF Section Header */
            else if (line.startsWith("CHARACTERS")) {

                // Gets the number of Character objects to create
                tokens = line.split("\\s+");
                this.numCharacters = Integer.parseInt(tokens[1]);

                int maxCharacterID = 0;
                Character temp = null;

                // Creates the specified number of Character objects
                for (int i = 0; i < this.numCharacters; i++) {

                    // Gets next line of data, specifying the type of
                    // Character and starting Place ID
                    line = CleanLineScanner.getCleanLine(dataFile);
                    tokens = line.split("\\s+");
                    String type = tokens[0];

                    line = CleanLineScanner.getCleanLine(dataFile);
                    int decisionType = Integer.parseInt(line);
                    
                    // Human Player to be created
                    if (type.equals("PLAYER")) {
                        temp = new Player(dataFile, this.version);
                        this.playersReal.put(temp.getID(), temp);
                    }
                    // AI Player to be created
                    else if (type.equals("NPC")) {
                        temp = new NPC(dataFile, this.version);
                        this.playersAI.put(temp.getID(), temp);
                    }
                    // Invalid Character type provided
                    else {
                        throw new InvalidParseException("Error: Unknown" + 
                                                        "Character Type" + 
                                                        "Given.");                          
                    }
                    
                    if (this.version < 50) {

                        int placeID = Integer.parseInt(tokens[1]);

                        // Obtain the Character's current Place
                        Place currPlace = null;
                        if (placeID == 0) {
                            currPlace = Place.getRandomPlace();
                            
                            // No Places exist
                            if (currPlace == null) {
                                throw new InvalidParseException(
                                    "Error: No Place objects exist to randomly "+
                                    "assign a Character"
                                );
                            }
                        }
                        else {
                            currPlace = Place.getPlaceByID(placeID);
                        }
                        temp.setCurrentPlace(currPlace);
                    }
                    else {

                        if ((decisionType == 0 || decisionType == 1) && temp instanceof NPC) {
                            temp.setDecisionMaker(new AI_Passive());
                        }
                        else if (decisionType == 2 && temp instanceof NPC) {
                            temp.setDecisionMaker(new AI_Aggressive());
                        }
                        else {
                            temp.setDecisionMaker(new UI());
                        }
                    }
                    
                    this.characters.put(temp.getID(), temp);
                    

                    // Stores maximum Character ID
                    if (temp.getID() > maxCharacterID) {
                        maxCharacterID = temp.getID();
                    }
                }

                // Create the remaining number of desired Human Players
                if (this.numCharacters < this.numCharactersWanted) {
                    for (int i = this.numCharacters; 
                         i < this.numCharactersWanted; i++) {
                        String playerName = String.format("Player %s", i + 1);
                        temp = new Player(maxCharacterID + i, playerName,
                                          "N/A", this.version);
                        temp.setCurrentPlace(this.firstPlace);
                        this.characters.put(temp.getID(), temp);
                        this.playersReal.put(temp.getID(), temp);
                        this.numCharacters += 1;
                    }
                }
            }
            
            /* Parses ARTIFACTS GDF Section Header */
            else if (line.startsWith("ARTIFACTS")) {
                
                // Creates characters, if none already exist
                createCharacters();

                // Gets the number of Artifact objects to create
                tokens = line.split("\\s+");
                this.numArtifacts = Integer.parseInt(tokens[1]);

                // Creates the specified number of Artifact objects
                for (int i = 0; i < this.numArtifacts; i++) {
                    new Artifact(dataFile, this.version);
                }
            }
            
            /* Parses BOSSES GDF Section Header */
            else if (line.startsWith("BOSSES")) {

                // Gets the number of Artifact objects to create
                tokens = line.split("\\s+");
                this.numBosses = Integer.parseInt(tokens[1]);

                // Creates the specified number of Artifact objects
                for (int i = 0; i < this.numBosses; i++) {
                    // Temprorily remove specified decision maker
                    line = CleanLineScanner.getCleanLine(dataFile);
                    Character c = new Boss(dataFile, this.version);
                    c.setDecisionMaker(new AI_Boss());
                    this.bosses.put(c.getID(), c);
                    this.characters.put(c.getID(), c);
                }
            }

            /* Invalid Section Header provided */
            else {
                throw new InvalidParseException("Error: Invalid GDF Section " +
                                                "Header provided.");                            
            }

            // Parses the rest of the GDF File Section Headers
            line = CleanLineScanner.getCleanLine(dataFile); // Gets next header
        }

        // Creates characters, if none already exist.
        createCharacters();

    }//end parseFile()



    /*-------------------------------------------------------------------------
     * Function: play()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - None
     *
     * This function plays the Game. Each player will have one move to make,
     * before the next player can play.
    */
    public void play()
    {
        boolean playing = true; // Indicates if the game should still be played

        // User tries to play the Game without any Places
        if (this.numPlaces == 0) {
            System.out.println(String.format("Error: Cannot play the Game " +
                                             "with zero Places."));
            playing = false;
        }

        Move move = null; // Player's Move
        int characterID = 0;  // ID of the current Character
        Character character = null;   // Current Character object
        boolean isPlayer = false; // Indicates if the Character is human

        // Plays the Game until all Human players win/leave the game
        while(playing) {

            // Loops through all characters
            for (Map.Entry<Integer, Character> entry : characters.entrySet()) {
                characterID = entry.getKey();
                character = entry.getValue();

                if (character instanceof Player) {
                    isPlayer = true;
                }
                else {
                    isPlayer = false;
                }

                // No more human players are playing the game
                if (this.playersReal.size() == 0) {
                    playing = false;
                    break;
                }

                // Skip players who have won/quit
                if (!character.isActive()) {
                    continue;
                }

                // Indicates next Character's turn
                if (!(character instanceof Boss)) {
                    System.out.print(String.format("Next Player's Turn: \"%s\" | Health: %s Hunger: %s", 
                                                character.name(),
                                                character.health(),
                                                character.hunger()));
                    if (!isPlayer) {
                        System.out.println(" (NPC)\n");
                    }
                    else {
                        System.out.println("\n");
                    }
                }
                
                // Obtains and executes the character's desired move
                move = character.makeMove();

                // Executes the Character's moves
                try {
                    move.execute();
                }
                catch (HasNoArtifactException e){
                    if (isPlayer) {
                        character.displayOutputMessage(e.getMessage());
                    }
                }
                catch (ImmovableArtifactException e) {
                    if (isPlayer) {
                        character.displayOutputMessage(e.getMessage());
                    }
                }
                catch (InvalidDirectionException e) {
                    if (isPlayer) {
                        character.displayOutputMessage(e.getMessage());
                    }
                }
                catch (UnusableArtifactException e) {
                    if (isPlayer) {
                        character.displayOutputMessage(e.getMessage());
                    }
                }
                catch (LockedDoorException e) {
                    if (isPlayer) {
                        character.displayOutputMessage(e.getMessage());
                    }
                }
                catch (UsedKeyException e) {
                    if (isPlayer) {
                        character.displayOutputMessage(e.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                    return;
                }
                
                try {
                    character.starve(5);
                }
                catch (StarveDeathException e) {
                    character.displayOutputMessage(e.getMessage());
                }
                

                // Character is no longer playing the game
                if (!character.isActive()) {
                    removeCharacterByID(characterID);
                }
            }
        }

        System.out.println("All Human Players have either reached the Exit " + 
                           "or quit the game! Thanks for playing!");

        return;
    }//end play()


    /*-------------------------------------------------------------------------
     * Function: print()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - None
     *
     * This function prints out Game information, used for debugging purposes.
     * The game prints debugging information related to it's Places, as well.
    */
    public void print()
    {
        System.out.println(String.format("Game Name: %s", 
                                         this.name));
        System.out.println(String.format("Game Version: %s", 
                                         this.version));
        System.out.println(String.format("Game Number of Places: %s", 
                                         this.numPlaces));
        System.out.println(String.format("Game Number of Directions: %s", 
                                         this.numDirections));
        System.out.println(String.format("Game Number of Artifacts: %s", 
                                         this.numArtifacts));
        System.out.println(String.format("Game Number of Characters: %s", 
                                         this.numCharacters));

        return;
    }//end print()


    /*-------------------------------------------------------------------------
     * Function: removeCharacterByID()
     *
     * Parameter(s):
     *      - int -> ID: Character ID.
     *
     * Return:
     *      - None
     *
     * This function removes a given character from the human/AI collection, 
     * but NOT the collection of all known characters.
    */
    private void removeCharacterByID(int ID)
    {
        // Removes Human Player
        if (playersReal.containsKey(ID)) {
            playersReal.remove(ID);
        }

        // Removes AI Player
        if (playersAI.containsKey(ID)) {
            playersAI.remove(ID);
        }

        return;
    }//end removeCharacterByID()


    /*-------------------------------------------------------------------------
     * Function: createCharacters()
     *
     * Parameter(s):
     *      - None
     *
     * Return:
     *      - None
     *
     * This function create the neceassary number of Characters for the game if
     * the GDF File does not contain any characters.
    */
    private void createCharacters()
    {
        // Creates Characters, if none already exist
        if (this.numCharacters == 0) {
            Scanner scanner = KeyboardScanner.getKeyboardScanner();
            int num = this.numCharactersWanted;

            // Obtains the number of desired Characters
            while (num == 0) {
                System.out.print("How many Characters will be " +
                               "playing? > ");
                try {
                    num = Integer.parseInt(scanner.nextLine());
                }
                catch (NumberFormatException e) {}
                
                System.out.println();
            }

            // Creates the number of desired Characters
            for (int i = 0; i < num; i++) {
                String playerName = String.format("Player %s", i + 1);
                Player temp = new Player(i + 1, playerName,
                                         "N/A", this.version);
                temp.setCurrentPlace(this.firstPlace);
                this.characters.put(temp.getID(), temp);
                this.playersReal.put(temp.getID(), temp);
                this.numCharacters += 1;
            }
        }

        return;
    }

}//end Game
