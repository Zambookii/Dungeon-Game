import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI_1
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
    private JButton resetBtn = null;
    private JButton inveBtn = null;
    private JButton lookBtn = null;
    private JButton quitBtn = null;
    private JScrollPane scrollArea = null;

    private boolean created = false;
    private boolean gotText = false;
    private String buffer = null;
    private String bufferWait = "";

    public GUI_1()
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

    private void makeGUI()
    {
        frame = new JFrame(String.format("%s", "bob"));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 500));

        nPanel = new JPanel();
        label = new JLabel("Please enter your game command:");
        
        submit = new JButton("Proceed");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                buffer = textField.getText();
                txtarea.append(buffer + "\n");
                textField.selectAll();
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

        
        txtarea.setFont(new Font("Serif", Font.BOLD, 20));
        txtarea.setLineWrap(true);
        txtarea.setEditable(false);

        scrollArea = new JScrollPane(txtarea);
        scrollArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollArea.setPreferredSize(new Dimension (300,300));
        
        cPanel.add(scrollArea);

        // cPanel.add(scrollArea);
        sPanel = new JPanel();
        inveBtn = new JButton("Inventory");
        lookBtn = new JButton("Look");
        quitBtn = new JButton("Quit");
        resetBtn = new JButton("Reset");

        sPanel.add(lookBtn);
        sPanel.add(inveBtn);
        sPanel.add(quitBtn);
        sPanel.add(resetBtn);

        frame.getContentPane().add(BorderLayout.NORTH, nPanel);
        frame.getContentPane().add(BorderLayout.CENTER, cPanel);
        frame.getContentPane().add(BorderLayout.PAGE_END, sPanel);

        frame.pack();
        frame.setVisible(true);
    }

    public void update(Character c, Place p)
    {
        frame.setVisible(true);
        return;
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

        // return textField.getText();
        return buffer;
    }
}
