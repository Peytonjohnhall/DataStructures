import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import DictionaryPackage.*;
 
/**
 * A Thread that contains the application we are going to animate
 *
 * @author Peyton J. Hall
 */
 
public class MisspellActionThread extends ActionThread 
{
 
    /**
     * Constructor for objects of class MisspellActionThread
     */
    public MisspellActionThread() 
    {
        super();
    }
 
    public String getApplicationTitle() 
    {
        return "Spelling Checker (Skeleton)";
    }
 
    // ************************************************************************
    // This is application specific code
    // ************************************************************************
 
    // These are the variables that are parameters of the application and can 
    // be set via the application specific GUI
    // Make sure they are initialized
 
    // Use two static variables to hold the intial values for the String names
    private static final String initialTextFileName = "check.txt";
    private static final String initialDictionaryFileName = "sampleDictionar" +
                                                            "y.txt";
 
    private String textFileName = initialTextFileName;
    private String dictionaryFileName = initialDictionaryFileName;
 
    // Displayed items
    private LinesToDisplay myLines;
    private DictionaryInterface<String, String> myDictionary;
    private boolean textFileNotFound = false;
    private boolean dictionaryFileNotFound = false;
    private boolean dictionaryLoaded = false;
 
    public void init() 
    {
        myDictionary = new HashedMapAdaptor<String, String>();
        myLines = new LinesToDisplay();
        dictionaryLoaded = false;
        textFileNotFound = false;
        dictionaryFileNotFound = false;
    }
 
    public void executeApplication() 
    {
        // ADD CODE HERE FOR A SINGLE RUN OF THE APPLICATION
        loadDictionary(dictionaryFileName, myDictionary);
        dictionaryLoaded = true;
        animationPause();
        // checkWords(dictionaryFileName, myDictionary);
        checkWords(textFileName, myDictionary);
        // myLines.addWordlet(new Wordlet("abc", true));
        // myLines.nextLine();
        // myLines.addWordlet(new Wordlet("def", false));
        // myLines.nextLine();
    }
 
    /**
     * Load the words into the dictionary.
     *
     * @param theFileName   The name of the file holding the words to put in
     *                      the dictionary.
     * @param theDictionary The dictionary to load.
     */
    public void loadDictionary(String theFileName, DictionaryInterface<String, 
                               String> theDictionary) 
    {
        Scanner input;
        try 
        {
            String inString;
            // String correctWord;
 
            input = new Scanner(new File(theFileName));
 
            // ADD CODE HERE TO READ WORDS INTO THE DICTIONARY
            // Read words from the input file, and add them to the dictionary
            input.useDelimiter("[\\s+|\\n+]+");
            while (input.hasNext()) 
            {
                inString = input.next();
                // Add the word to the dictionary.
                // Use identical duplicate parameters because the
                // self-contained dictionary stores words as both
                // keys and values.
                theDictionary.add(inString, inString);
            }
            System.out.println(theDictionary);
        } 
        catch (IOException e) 
        {
            System.out.println("There was an error in reading or opening the" +
                               " file: " + theFileName);
            System.out.println(e.getMessage());
            dictionaryFileNotFound = true;
            forceLastPause();
            throw new ResetApplicationException("Could not read dictionary F" +
                                                "ile");
        }
 
    }
 
    /**
     * Get the words to check, check them, then put Wordlets into myLines.
     * When a single line has been read do an animation step to wait for the 
     * user.
     * @param theFileName   The name of the file holding the text to check.
     * @param theDictionary The dictionary to check against.
     */
    public void checkWords(String theFileName, DictionaryInterface<String, 
                           String> theDictionary) 
    {
        Scanner input;
        try 
        {
            String inString;
            // String aWord;
 
            input = new Scanner(new File(theFileName));
            // ADD CODE HERE
            // Read words from the input file
            while (input.hasNext()) 
            {
                inString = input.next();
                // Create a Wordlet for the current word, 
                // specifying whether it is spelled correctly
                myLines.addWordlet(new Wordlet(inString, checkWord(inString,
                                   theDictionary)));
                // Move to the next line of the display
                myLines.nextLine();
                // Pause for user interaction
                animationPause();
            }
        } 
        catch (IOException e) 
        {
            System.out.println("There was an error in reading or opening the" +
                               " file: " + theFileName);
            System.out.println(e.getMessage());
            textFileNotFound = true;
            forceLastPause();
            throw new ResetApplicationException("Could not read text File");
        }
 
    }
 
    /**
     * Check the spelling of a single word.
     *
     * @param word          A single word to check.
     * @param theDictionary The dictionary to check against.
     * @return True if the word is correctly spelled.
     */
    public boolean checkWord(String word, DictionaryInterface<String, 
                             String> theDictionary) 
    {
        boolean result = false;
 
        // ADD CODE HERE
        // See if the word consists of only punctuation characters
        boolean notOnlyPunctuations = false;
        for (int i = 0; i < word.length(); i++) 
        {
            // See if the character in the word, at 
            // position i, is an alphabetic character
            notOnlyPunctuations = notOnlyPunctuations || 
                                  Character.isLetter(word.charAt(i));
        }
        // If the word contains only punctuation characters
        if (!notOnlyPunctuations) 
        {
            // Consider it correctly spelled
            result = true;
            return result;
        } 
        else 
        {
            // If the word exists in the dictionary
            if (theDictionary.contains(word)) 
            {
                // Consider it correctly spelled
                result = true;
                return result;
            }
        }
        // If the word is neither composed of only punctuation characters,
        // nor found in the dictionary, then it's considered misspelled
        return result;
 
    }
 
    private static int DISPLAY_HEIGHT = 500;
    private static int DISPLAY_WIDTH = 500;
 
    public JPanel createAnimationPanel() 
    {
        return new AnimationPanel();
    }
 
    // This privately defined class does the drawing the application needs
    public class AnimationPanel extends JPanel 
    {
        public AnimationPanel() 
        {
            super();
            setSize(DISPLAY_WIDTH, DISPLAY_HEIGHT);
        }
 
        public void paintComponent(Graphics g) 
        {
            String toDraw;
            super.paintComponent(g);
            toDraw = "Dictionary Loaded: " + dictionaryLoaded;
            g.drawString(toDraw, 20, 20);
 
            if (dictionaryFileNotFound) 
            {
                toDraw = "Dictionary file not found; aborting";
                g.setColor(Color.red);
                g.drawString(toDraw, 20, 35);
                g.setColor(Color.black);
            }
 
            if (textFileNotFound) 
            {
                toDraw = "Text file not found; aborting";
                g.setColor(Color.red);
                g.drawString(toDraw, 20, 50);
                g.setColor(Color.black);
            }
 
            // Now draw the lines display if it is available
            if (myLines != null)
                myLines.drawOn(g, 20, 40);
 
        }
    }
 
    // **************************************************************************
    // This is the application specific GUI code
    // **************************************************************************
 
    private JTextField textNameTextField;
    private JTextField dictionaryNameTextField;
    private JLabel setupStatusLabel;
    private JPanel setupPanel;
 
    public void setUpApplicationSpecificControls() 
    {
        getAnimationPanel().setLayout(new BorderLayout());
 
        textNameTextField = new JTextField(initialTextFileName);
        textNameTextField.addActionListener(
                new ActionListener() 
                {
                    public void actionPerformed(ActionEvent event) 
                    {
                        textNameTextFieldHandler();
                        getAnimationPanel().repaint();
                    }
                });
 
        dictionaryNameTextField = new JTextField(initialDictionaryFileName);
        dictionaryNameTextField.addActionListener(
                new ActionListener() 
                {
                    public void actionPerformed(ActionEvent event) 
                    {
                        dictionaryNameTextFieldHandler();
                        getAnimationPanel().repaint();
                    }
                });
 
        setupStatusLabel = new JLabel("");
 
        setupPanel = new JPanel();
        setupPanel.setLayout(new GridLayout(3, 2));
 
        setupPanel.add(new JLabel("Text file name:"));
        setupPanel.add(textNameTextField);
        setupPanel.add(new JLabel("Dictionary file name:"));
        setupPanel.add(dictionaryNameTextField);
        setupPanel.add(setupStatusLabel);
 
        getAnimationPanel().add(setupPanel, BorderLayout.SOUTH);
 
    }
 
    private void textNameTextFieldHandler() 
    {
        try 
        {
            if (applicationControlsAreActive()) // Only change if we are in the setup phase
            {
                String input = textNameTextField.getText().trim();
                File test = new File(input);
                if (test.canRead()) 
                {
                    textFileName = input;
                    setupStatusLabel.setText("text file is now " + input);
                } 
                else 
                {
                    setupStatusLabel.setText("Could not read " + input);
                    textNameTextField.setText("");
                }
 
            }
        } 
        catch (Exception e) 
        {
            // don't change the name if we had an exception
            setupStatusLabel.setText("bad text file name");
 
        }
 
    }
 
    private void dictionaryNameTextFieldHandler() 
    {
        try 
        {
            if (applicationControlsAreActive()) // Only change if we are in the setup phase
            {
                String input = dictionaryNameTextField.getText().trim();
 
                File test = new File(input);
                if (test.canRead()) 
                {
                    dictionaryFileName = input;
                    setupStatusLabel.setText("dictionary file is now " + input);
                } 
                else 
                {
                    setupStatusLabel.setText("Could not read " + input);
                    dictionaryNameTextField.setText("");
                }
 
            }
        } 
        catch (Exception e) 
        {
            // don't change the file name if we had an exception
            setupStatusLabel.setText("bad dictionary file name");
        }
 
    }
 
} // end class MisspellActionThread