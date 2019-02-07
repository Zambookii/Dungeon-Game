import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import java.lang.*;

public class GUI_3
    implements UserInterface
{
    private JFrame frame = null;
    private JTextArea playerArea = null; 
    private JTextArea placeArea = null; 
    private JTextArea artifactArea = null; 
    private JTextArea inventoryArea = null;
    private JTextArea history = null;
    private JTextField inputText;
    private JButton doneButton = null;

    private JTextArea aName = null; 
    private JTextArea aWeight = null;
    private JTextArea aPoints = null;
    private JTextArea aTotalWeight = null;
    private JTextArea aTotalPoints = null;
    private JTextArea healthArea = null;
    private JTextArea hungerArea = null;

    private JButton buttonText = null;
    private JButton buttonUI_1 = null;
    private JButton buttonUI_2 = null;
    private JButton skipButton = null;
    private JButton quitButton = null;



    private String name = null;
    private int health;
    private int hunger;

    private boolean initComplete = false;
    private boolean inputGiven = false;
    private boolean messageSent = false;
    private String inputBuffer = null;
    private final Object lock = new Object();

    public GUI_3()
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run() {
                initGUI();
                
                synchronized(lock) {
                    initComplete = true;
                }
            }   
        });
        
        while(!initComplete) { synchronized(lock) {} }
    }

    private void initGUI()
    {
        frame = new JFrame(String.format("%s", "bob"));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // frame.setPreferredSize(new Dimension(500, 500));

        // Adds other character related fields to the frame
        addComponents(frame.getContentPane());

        frame.pack();

        return;
    }

    private void addComponents(Container pane)
    {
        JScrollPane scrollPane = null;
        JPanel tempPanel = null;
        JLabel tempLabel = null;

        pane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.weighty = 0;
        // Sets Components in Row #1
        //-----------------------------------------------------------
        tempPanel = new JPanel();
        tempLabel = new JLabel("Players Present");
        tempPanel.setBorder(new LineBorder(Color.BLACK));
        tempPanel.add(tempLabel);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        pane.add(tempPanel, c);

        tempPanel = new JPanel();
        tempLabel = new JLabel("Current Place");
        tempPanel.setBorder(new LineBorder(Color.BLACK));
        tempPanel.add(tempLabel);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        pane.add(tempPanel, c);

        tempPanel = new JPanel();
        tempLabel = new JLabel("History");
        tempPanel.setBorder(new LineBorder(Color.BLACK));
        tempPanel.add(tempLabel);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        c.weightx = 0.5;
        c.gridx = 4;
        c.gridy = 0;
        pane.add(tempPanel, c);

        // Sets Components in Row #2
        //-----------------------------------------------------------
        playerArea = new JTextArea();
        playerArea.setEditable(false);
        scrollPane = new JScrollPane(playerArea);
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 1;
        c.weighty = 4.0;
        c.gridx = 0;
        c.gridy = 1;
        pane.add(scrollPane, c);

        placeArea = new JTextArea();
        placeArea.setEditable(false);
        scrollPane = new JScrollPane(placeArea);
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 3;
        c.weighty = 4.0;
        c.gridx = 1;
        c.gridy = 1;
        pane.add(scrollPane, c);

        history = new JTextArea();
        history.setEditable(false);
        scrollPane = new JScrollPane(history);
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 3;
        c.weighty = 4.0;
        c.gridx = 4;
        c.gridy = 1;
        pane.add(scrollPane, c);

        // Sets Components in Row #3
        //-----------------------------------------------------------
        c.weighty = 0;

        tempPanel = new JPanel();
        tempLabel = new JLabel("Artifacts Present");
        tempPanel.setBorder(new LineBorder(Color.BLACK));
        tempPanel.add(tempLabel);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 2;
        pane.add(tempPanel, c);

        tempPanel = new JPanel();
        tempLabel = new JLabel("Inventory");
        tempPanel.setBorder(new LineBorder(Color.BLACK));
        tempPanel.add(tempLabel);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 2;
        pane.add(tempPanel, c);

        tempPanel = new JPanel();
        tempLabel = new JLabel(">");
        tempPanel.setBorder(new LineBorder(Color.BLACK));
        tempPanel.add(tempLabel);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.weightx = 0.1;
        c.gridx = 4;
        c.gridy = 2;
        pane.add(tempPanel, c);

        inputText = new JTextField(20);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.weightx = 0.5;
        c.gridx = 5;
        c.gridy = 2;
        pane.add(inputText, c);

        inputText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!inputGiven) {
                    display("Input: ");
                    inputBuffer = inputText.getText();
                    display(inputBuffer + "\n");
                }
                else {
                    display(String.format("*** Input already given! \"%s\" ignored.\n", 
                                          inputText.getText()));
                    inputBuffer = "Nothing here yet";
                }
                
                inputText.selectAll();

                synchronized(lock) {
                    inputGiven = true;
                }
            }
        });


        // Sets Components in Row #4
        //-----------------------------------------------------------
        artifactArea = new JTextArea();
        artifactArea.setEditable(false);
        scrollPane = new JScrollPane(artifactArea);
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 6;
        c.gridwidth = 1;
        c.weighty = 4.0;
        c.gridx = 0;
        c.gridy = 3;
        pane.add(scrollPane, c);

        c.weighty = 0;
        tempPanel = new JPanel();
        tempLabel = new JLabel("Name");
        tempPanel.setBorder(new LineBorder(Color.BLACK));
        tempPanel.add(tempLabel);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 3;
        pane.add(tempPanel, c);

        tempPanel = new JPanel();
        tempLabel = new JLabel("Weight");
        tempPanel.setBorder(new LineBorder(Color.BLACK));
        tempPanel.add(tempLabel);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 3;
        pane.add(tempPanel, c);

        tempPanel = new JPanel();
        tempLabel = new JLabel("Points");
        tempPanel.setBorder(new LineBorder(Color.BLACK));
        tempPanel.add(tempLabel);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.gridx = 3;
        c.gridy = 3;
        pane.add(tempPanel, c);

        tempPanel = new JPanel();
        tempLabel = new JLabel("Health");
        tempPanel.setBorder(new LineBorder(Color.BLACK));
        tempPanel.add(tempLabel);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridheight = 1;
        c.gridwidth = 2;
        c.weightx = 0.5;
        c.gridx = 4;
        c.gridy = 3;
        pane.add(tempPanel, c);

        tempPanel = new JPanel();
        tempLabel = new JLabel("Hunger");
        tempPanel.setBorder(new LineBorder(Color.BLACK));
        tempPanel.add(tempLabel);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.gridx = 6;
        c.gridy = 3;
        pane.add(tempPanel, c);


        // Sets Components in Row #5
        //-----------------------------------------------------------
        aName = new JTextArea();
        aName.setEditable(false);
        scrollPane = new JScrollPane(inventoryArea);
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 3;
        c.gridwidth = 1;
        c.weighty = 1;
        c.gridx = 1;
        c.gridy = 4;
        pane.add(scrollPane, c);

        aWeight = new JTextArea();
        aWeight.setEditable(false);
        scrollPane = new JScrollPane(inventoryArea);
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 3;
        c.gridwidth = 1;
        c.weighty = 1;
        c.gridx = 2;
        c.gridy = 4;
        pane.add(scrollPane, c);

        aPoints = new JTextArea();
        aPoints.setEditable(false);
        scrollPane = new JScrollPane(inventoryArea);
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 3;
        c.gridwidth = 1;
        c.weighty = 1;
        c.gridx = 3;
        c.gridy = 4;
        pane.add(scrollPane, c);

        healthArea = new JTextArea();
        healthArea.setEditable(false);
        scrollPane = new JScrollPane(inventoryArea);
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.gridwidth = 2;
        c.weighty = 1;
        c.gridx = 4;
        c.gridy = 4;
        pane.add(scrollPane, c);

        hungerArea = new JTextArea();
        hungerArea.setEditable(false);
        scrollPane = new JScrollPane(inventoryArea);
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weighty = 1;
        c.gridx = 6;
        c.gridy = 4;
        pane.add(scrollPane, c);

        // Sets Components in Row #6
        //-----------------------------------------------------------
        tempPanel = new JPanel();
        tempLabel = new JLabel("GUI Selector");
        tempPanel.setBorder(new LineBorder(Color.BLACK));
        tempPanel.add(tempLabel);
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.gridwidth = 3;
        c.weighty = 0.5;        
        c.weightx = 0.5;
        c.gridx = 4;
        c.gridy = 5;
        pane.add(tempPanel, c);

        // Sets Components in Row #7
        //-----------------------------------------------------------
        buttonText = new JButton("TEXT");
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.gridwidth = 1;   //2 columns wide
        c.weighty = 4;
        c.weightx = 0.5;
        c.gridx = 4;       //aligned with button 2
        c.gridy = 6;       //third row
        pane.add(buttonText, c);

        buttonUI_1 = new JButton("GUI 1");
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.gridwidth = 1;   //2 columns wide
        c.weighty = 4;
        c.weightx = 0.5;
        c.gridx = 5;       //aligned with button 2
        c.gridy = 6;       //third row
        pane.add(buttonUI_1, c);

        buttonUI_2 = new JButton("GUI 2");
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.gridwidth = 1;   //2 columns wide
        c.weighty = 4;
        c.weightx = 0.5;
        c.gridx = 6;       //aligned with button 2
        c.gridy = 6;       //third row
        pane.add(buttonUI_2, c);

        c.weighty = 0;
        // Sets Components in Row #8
        //-----------------------------------------------------------
        tempPanel = new JPanel();
        tempLabel = new JLabel("Total Weight");
        tempPanel.setBorder(new LineBorder(Color.BLACK));
        tempPanel.add(tempLabel);
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.gridwidth = 2;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 7;
        pane.add(tempPanel, c);

        tempPanel = new JPanel();
        tempLabel = new JLabel("Total Points");
        tempPanel.setBorder(new LineBorder(Color.BLACK));
        tempPanel.add(tempLabel);
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.gridx = 3;
        c.gridy = 7;
        pane.add(tempPanel, c);

        skipButton = new JButton("Skip Turn");
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.gridwidth = 3;   //2 columns wide
        c.weighty = 0;
        c.weightx = 0.5;
        c.gridx = 4;       //aligned with button 2
        c.gridy = 7;       //third row
        pane.add(skipButton, c);

        // Sets Components in Row #9
        //-----------------------------------------------------------
        aTotalWeight = new JTextArea();
        aTotalWeight.setEditable(false);
        scrollPane = new JScrollPane(inventoryArea);
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.gridwidth = 2;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 8;
        pane.add(scrollPane, c);

        aTotalPoints = new JTextArea();
        aTotalPoints.setEditable(false);
        scrollPane = new JScrollPane(inventoryArea);
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.gridx = 3;
        c.gridy = 8;
        pane.add(scrollPane, c);

        quitButton = new JButton("Quit");
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.gridwidth = 3;   //2 columns wide
        c.weighty = 0;
        c.weightx = 0.5;
        c.gridx = 4;       //aligned with button 2
        c.gridy = 8;       //third row
        pane.add(quitButton, c);

        return;
    }


    public void update(Character c, Place p)
    {
        placeArea.setText(null);
        placeArea.append(p.name() + "\n\n");
        placeArea.append(p.description() + "\n\n");

        playerArea.setText(null);
        playerArea.append(p.playersPresent());

        artifactArea.setText(null);
        artifactArea.append(p.artifactsPresent());

        // inventoryArea.setText(null);

        // // Obtains the Artifacts possessed by the Charater
        // TreeMap<String, Artifact> artifacts = c.getArtifacts();

        // // Inventory is empty
        // if (artifacts.size() == 0) {
        //     inventoryArea.append("Empty!\n");
        // }
        // else {
        //     int totalPoints = 0; // Total points of all Artifacts
        //     int totalWeight = 0; // Total weight of all Artifacts

        //     // Displays Artifact properties
        //     inventoryArea.append(String.format("%-20s%10s%10s\n", "Name", "Points", "Weight"));
        //     for (Map.Entry<String, Artifact> pair : artifacts.entrySet()) {
        //         Artifact a = pair.getValue();
        //         String weight = String.format("%skg", a.weight());
        //         inventoryArea.append(String.format("%-20s%10s%10s\n", a.name(), a.value(), weight));

        //         totalPoints += a.value();
        //         totalWeight += a.weight();
        //     }
        //     // Displays total weight and point value of all Artifacts obtained
        //     // this.character.displayOutputMessage(String.format("\n%-20s%10s%8skg\n", "Total", totalPoints, totalWeight));
        // }

        frame.setVisible(true);
        return;
    }


    public void display(String message)
    {
        history.append(message);
        return;
    }


    public String getLine()
    {
        while (!inputGiven) {
            synchronized(lock) {}
        }
        synchronized(lock) {
            inputGiven = false;
        }

        frame.setVisible(false);

        return inputBuffer;
    }
}