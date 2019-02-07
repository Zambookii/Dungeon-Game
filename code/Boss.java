/**
 * Boss.java
 *
 * Name: Jesse Palasz
 * NetID: jpalas3
 *
 * Homework #4
 * Group: 20
 * CS 342, FALL 2018, CRN: 37162
 * November 14, 2018
 *
 * This class is intended to function as a AI for a Character, which performs
 * actions for the Character. The AI randomly selects what move to make next.
*/

import java.io.*;
import java.util.*;

public class Boss
    extends Character
{
    private static TreeMap<Integer, Boss> bosses 
                                           = new TreeMap<Integer, Boss>();
    private double regenRate = 0.0;
    private int regenAmount = 0;
    private int timeOfLastRegen = 0;
    private int version = 0;
    
    public Boss(Scanner dataFile, int version)
        throws InvalidParseException
    {
        // Call Character class constructor
        super(dataFile, version);

        super.decisionMaker = new AI_Boss();
    }
    
}
