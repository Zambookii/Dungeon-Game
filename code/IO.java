
import java.io.*;
import java.util.*;

public class IO
{
    public final static int TEXT = 0;
    public final static int GUI_1 = 1;
    public final static int GUI_2 = 2;
    public final static int GUI_3 = 3;
    private int ioType = 0;
    private UserInterface implementor = null;

    public IO()
    {
        implementor = new TextInterface();
        ioType = TEXT;
    }

    public IO(int val)
    {
        selectInterface(val);
    }

    public int ioType() 
    {
        return ioType;
    }

    public void updateGUI(Character c, Place p)
    {
        implementor.update(c, p);
        return;
    }

    public void display(String message)
    {
        implementor.display(message);

        return;
    }


    public String getLine()
    {
        return implementor.getLine();
    }


    public void selectInterface(int val)
    {
        if (val == TEXT) {
            implementor = new TextInterface();
            ioType = TEXT;
        }
        else if (val == GUI_1) {
            implementor = new GUI_1();
            ioType = GUI_1;
        }
        else if (val == GUI_2) {
            implementor = new GUI_2();
            ioType = GUI_2;
        }
        else if (val == GUI_3) {
            implementor = new GUI_3();
            ioType = GUI_3;
        }
        else {
            if (implementor == null) {
                implementor = new TextInterface();
            }
            ioType = TEXT;
        }

        return;
    }
}