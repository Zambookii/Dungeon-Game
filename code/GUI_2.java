import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI_2
    implements UserInterface
{

    private JPanel nPanel = null;
    private JLabel label = null;
    private JTextField textField = new JTextField(15);
    private JButton submit = null;
    private JFrame frame = null;
    private JPanel cPanel = null;
    private JPanel sPanel = null;
    private JTextArea txtarea = new JTextArea();
    private JButton infoBtn = null;
    private JButton inveBtn = null;
    private JButton lookBtn = null;
    private JButton quitBtn = null;
    private JScrollPane scrollArea = null;
    
    private Character ch = null;
    private Place pl = null;

    private boolean created = false;
    private boolean gotText = false;
    private String buffer = null;
    private String bufferWait = "";

    public GUI_2()
    {
        // schedule this for the event dispatch thread (edt)
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run() {
                makeGUI();

                synchronized(bufferWait) {
                    created = true;
                }
            }   
        });

        while(!created)
            synchronized(bufferWait) {}
    }
    private void makeInfoGUI() {
    	JFrame frame1 = new JFrame("Information Menu");
        frame1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame1.setPreferredSize(new Dimension(500, 500));
        JTextArea txt = new JTextArea();
        txt.append("Information about the game\n\nAttack - Command used to attack other players\nUse - Command that uses an artifact\n"
        		+ "Go (direction) - Makes your character move a direction\nDrop - Command used to drop an artifact\n"
        		+ "Eat - Command used to eat food and gain health\nGet - Command used to get an artifact in the map\n"
        		+ "Heal - Command used to heal player\nInventory - Checks your current artifacts in inventory\n"
        		+ "Look - Displays current location and information again\nQuit - Quits the game, but why quit?");
        txt.setFont(new Font("Times New Roman", Font.BOLD, 20));
        txt.setLineWrap(true);
        txt.setEditable(false);
       
        frame1.add(txt);
        frame1.setLocationRelativeTo(null);
        frame1.pack();
        frame1.setVisible(true);
    	
    	
    }

    private void makeGUI()
    {
        frame = new JFrame(String.format("%s", "Jesse's GUI"));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(650, 650));

        nPanel = new JPanel();
        label = new JLabel("Enter command:");
        label.setForeground(Color.RED);
        
        submit = new JButton("Continue");
        submit.setBackground(Color.GREEN);
        submit.setForeground(Color.RED);
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                buffer = textField.getText();
                txtarea.append(buffer + "\n");
                textField.selectAll();
                System.out.println(buffer);
                synchronized(bufferWait) {
                    gotText = true;
               
                }
            }
        });
        
        frame.getRootPane().setDefaultButton(submit);

        nPanel.add(label);
        nPanel.add(textField);
        nPanel.add(submit);

        cPanel = new JPanel ();

        
        txtarea.setFont(new Font("Times New Roman", Font.BOLD, 20));
        txtarea.setLineWrap(true);
        txtarea.setEditable(false);
        txtarea.setCaretPosition(txtarea.getDocument().getLength());

        scrollArea = new JScrollPane(txtarea);
        scrollArea.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
            public void adjustmentValueChanged(AdjustmentEvent e) {  
                e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
            }
        });
        scrollArea.setPreferredSize(new Dimension (450,450));
        
        cPanel.add(scrollArea);

        // cPanel.add(scrollArea);
        sPanel = new JPanel();
        inveBtn = new JButton("Inventory");
        inveBtn.setBackground(Color.GREEN);
        inveBtn.setForeground(Color.RED);
        inveBtn.setPreferredSize(new Dimension(120, 60));
        inveBtn.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        inveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Go_Inventory go = new Go_Inventory(ch);
            	go.execute();
            }
        });
        lookBtn = new JButton("Look");
        lookBtn.setBackground(Color.GREEN);
        lookBtn.setForeground(Color.RED);
        lookBtn.setPreferredSize(new Dimension(120, 60));
        lookBtn.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lookBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Go_Look go = new Go_Look(ch, pl);
            	go.execute();
            }
        });
        quitBtn = new JButton("Quit");
        quitBtn.setBackground(Color.GREEN);
        quitBtn.setForeground(Color.RED);
        quitBtn.setPreferredSize(new Dimension(120, 60));
        quitBtn.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        quitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	buffer = "quit";
            	txtarea.append(buffer + "\n");
                textField.selectAll();
                System.out.println(pl.name() + " has quit the game!");
                synchronized(bufferWait) {
                    gotText = true;
                }
            }
        });
        infoBtn = new JButton("Info");
        infoBtn.setBackground(Color.GRAY);
        infoBtn.setForeground(Color.WHITE);
        infoBtn.setPreferredSize(new Dimension(120, 60));
        infoBtn.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        infoBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	makeInfoGUI();
            }
        });
        
        

        sPanel.add(lookBtn);
        sPanel.add(inveBtn);
        sPanel.add(quitBtn);
        sPanel.add(infoBtn);
        
        frame.getContentPane().add(BorderLayout.CENTER, nPanel);
        frame.getContentPane().add(BorderLayout.NORTH, cPanel);
        frame.getContentPane().add(BorderLayout.PAGE_END, sPanel);

        frame.pack();
        frame.setVisible(true);
    }

    public void update(Character c, Place p)
    {
    	ch = c;
    	pl = p;
        frame.setVisible(true);
    }
    

    public void display (String message) {
        // ta.setText("Message changed to\n" + message);
        txtarea.append(message+ "\n");

        return;
    }

    public String getLine(){
        while (!gotText) {
            synchronized(bufferWait) {}
        }
        synchronized(bufferWait) {
            gotText = false;
        }
        frame.setVisible(false);

        // return textField.getText();
        return buffer;
    }
}
