
import java.io.*;
import java.util.*;

public class TextInterface
    implements UserInterface
{
    private Scanner scanner = null;

    public TextInterface()
    {
        this.scanner = KeyboardScanner.getKeyboardScanner();
    }

    public void update(Character c, Place p)
    {
        return;
    }

    public void display(String message)
    {
        System.out.println(message);

        return;
    }


    public String getLine()
    {
        return scanner.nextLine().trim();
    }
}