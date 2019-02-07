
Name: Luis Enrique Love
      Jesse Palasz
      Aleksandar Ilic
NetID: llove3
       jpalas3
       ailic3

Homework #4
Group: 20
CS 342, FALL 2018, CRN: 37162
November 14, 2018

                                            README: Homework #4

Files Worked On:
---------------

    Luis:                            Jesse:                       Aleksandar:                   GDF Files:

    - readme.txt                     - GameTester.java            - makefile                    - MC_31.gdf
    - Game.java                      - CleanLineScanner.java      - AI.java                     - MC_40.gdf
    - Move.java                      - KeyboardScanner.java       - UI.java                     - MC_50.gdf
    - Go_Direction.java              - Place.java                 - Character.java              - MC_51.gdf
    - Go_Drop.java                   - Direction.java             - NPC.java
    - Go_Get.java                    - Boss.java                  - Player.java
    - Go_Inventory.java              - UML_P1.png                 - DecisionMaker.java
    - Go_Eat.java                    - UML_P2.png                 - AI_Passive.java
    - Go_Attack.java                 - UML.mdj                    - AI_Aggressive.java
    - Go_Heal.java                   - AI_Boss.java
    - Go_Look.java                                                
    - Go_Quit.java
    - Go_Use.java
    - Go_Wait.java
    - HasNoArtifactException.java
    - ImmovableArtifactException.java
    - InvalidDirectionException.java
    - InvalidParseException.java
    - LockedDoorException.java
    - UnusableArtifactException.java
    - UsedKeyException.java
    - BossFightException.java
    - CharDeathException.java
    - StarveDeathException.java
    - GDF_51_Format.pdf


Running the Program (For Grading):
---------------------------------

    The program accepts at most 2 command line arguments. The first argument, if provided, must be the
    filename of the .gdf file. If a second command line argument is provided, it must be an integer
    indicating the number of desired players for the game. All players created in this manner will
    be human players (non NPC).

    NOTE: If the number of provided players is less than the number provided in the .gdf file, then
          the user provided number of players is ignored.

    Simply type:

        java GameTester <filename> <number of players> 

    in the command line, where "filename" is a .gdf file and "number of players" is an integer.
    

Description:
-----------

    The *.java class files located in this directory are intended to function as an "adventure" type of game
    for Homework #2. The implementation of this game can take any .gdf file as input to construct the game
    (provided the .gdf file follows the correct format).

    When the program is run in terminal ( java GameTester <filename> <number of players>), The program will build
    the game based on the provided filename. If the filename doesn't exist, then the program will repeatedly ask
    the user for a filename to build the game. Once the user has provided a valid filename, the game is created
    with hte specified number of humand and NPC players. A player can type in the following commands to invoke 
    certain actions; commands include:

                    - EXIT    : Quits the game.
                    - QUIT    : Quits the game.
                    - LOOK    : Display information relating to the current Place.
                    - GO XXXX : Where XXXX represents a supported direction of travel; travel in direction XXXX.
                    - XXXX    : Where XXXX represents a supported direction of travel; travel in direction XXXX.
                    - INVENTORY or INVE : Lists the player's inventory.
                    - GET XXXX : Where XXXX represents an artifact; Add an artifact to the player's inventory.
                    - DROP XXXX : Where XXXX represents an artifact; Drop a player's artifact off in the current Place.
                    - USE XXXX : Where XXXX represents an artifact; Use a player's artifact on the current Place.
                    - WAIT : Skips the current turn.
                    - HEAL XXXX : Where XXXX represents an artifact; Heals the character.
                    - ATTACK XXXX : Where XXXX represents an artifact; Attacks all characters in the vicinity.
                    - EAT XXXX : Where XXXX represents an artifact; Character eats food.
    
    The following are supported directions (Cardinal directions on a compass rose):

                N, S, E, W U, D, NW, NE,. NNW, NNE, SW, SE, SSW, SSE, ENE. ESE. WNW, WSW

    The player is capable of traversing to any Place in which the entry way to the corresponding Place is unlocked
    (the user is informed if the Place is locked, and remains in the present Place). If the player provides an
    incorrect direction of travel, the player remains in the present Place. The player is able to unlock entry ways
    by obtaining certain artifacts, and using them in their current Place. The player is informed if the artifact
    had any effect. The Players and NPCs are capably of eating food, starving to death, healing themselves, and
    attacking eachother.

    A character "wins" the game if they are able to traverse to the "Exit" place, or chooses to quit the game.
    
    If all the human players "win" or "exit" the game, then the game ends. NPCs are able perform the same actions
    as Human players. 

    There is a Boss Character (if provided), which is capable of teleporting from place-to-place, and has the ability
    to attack other players.

    NOTE: A "Move" was implemented using the command design pattern approach discussed in class.
    
    NOTE: Additional public methods were added; detailed descriptions can be viewed from the documentation
          associated with each function in their respective class files).


Testcases:
---------

    The Place & Direction classes were tested in the GameTester class for validity. The Testcases
    tested the validity of the outputs from the various methods associated for each of the classes.
    The following functions tested these classes:

            - testPlace()
            - testDirection()
            - testArtifact()

    The code associated with these testcases has been commented out.

    The Game glass was tested by playing the Game.


Errors:
------

    There are no known errors associated with this homework assignment.

Makefile Invocation:
-------------------

    Simply typing "make" will compile the associated .java files.

    Typing "make run" will run the GameTester class file, though with no command line input.

    Typing "make clean" will remove all .class files compiled are a result of typing "make".
