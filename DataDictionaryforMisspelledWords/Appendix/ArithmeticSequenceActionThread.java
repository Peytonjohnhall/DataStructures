import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * A Thread that specializes for the summation application. 
 */
   
    
public class ArithmeticSequenceActionThread extends ActionThread
{
    
    /**
     * Constructor for objects of class ActionThread
     */
    public ArithmeticSequenceActionThread()
    {
        // Just need to invoke the constructor for the abstract super class.
        super();      
    }
    
    public String getApplicationTitle()
    {
        return "Arithmetic Sequences (Sample Application)";
    }


    // **************************************************************************
    // This is application specific code
    // **************************************************************************    

    // These are the variables that are parameters of the application and can be
    // set via the application specific GUI.
    // Make sure they are initialized.
    private int start = 1;
    private int delta = 1;
   
    
    // Displayed items
    private ArithmeticSequencer mySequencer;
    private int count;

    
     /**
     * Initialize the displayed items.
     */
public void init() 
    {
        count = 0;
        mySequencer = new ArithmeticSequencer(start, delta);
    }
        
     /**
     * Execute the application one time.
     */
    public void executeApplication()
    {
        for(int i=0; i<10; i++)
        {
            count = i;
            mySequencer.addNext();
            animationPause();
        }
 
    }
    

    private static int DISPLAY_HEIGHT = 500;
    private static int DISPLAY_WIDTH = 500;

     /**
     * We need a panel to draw on for the application.
     */
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
        
        // This method is specific to the application and displays the results.
        public void paintComponent(Graphics g)
        {
            String toDraw;
            super.paintComponent(g);
            toDraw = "i is " + count;
            g.drawString(toDraw, 20,20);
            
            // Now draw the sequencer if it is available
            if(mySequencer != null)
                mySequencer.drawOn(g, DISPLAY_WIDTH/2, 40);
            
        }
    }
    
    // **************************************************************************
    // These are the handlers for the application specific controls
    // **************************************************************************    

    private JTextField countByTextField;
    private JTextField startAtTextField;
    private JLabel setupStatusLabel;
    private JPanel setupPanel;
    
    public void setUpApplicationSpecificControls()
    {
        getAnimationPanel().setLayout(new BorderLayout());
        
        
        countByTextField = new JTextField("1");
        countByTextField.addActionListener(
            new ActionListener() 
            {
                public void actionPerformed(ActionEvent event) 
                {
                    countByTextFieldHandler();
                    getAnimationPanel().repaint();
                }
            }
        );

        startAtTextField = new JTextField("1");
        startAtTextField.addActionListener(
            new ActionListener() 
            {
                public void actionPerformed(ActionEvent event) 
                {
                    startAtTextFieldHandler();
                    getAnimationPanel().repaint();
                }
            }
        );
        
        setupStatusLabel = new JLabel("");
        
        setupPanel = new JPanel();
        setupPanel.setLayout(new GridLayout(3,2));
        
        setupPanel.add(new JLabel("Start counting at (positive integer):"));
        setupPanel.add(startAtTextField);
        setupPanel.add(new JLabel("Count by:"));
        setupPanel.add(countByTextField);
        setupPanel.add(setupStatusLabel);
        
        getAnimationPanel().add(setupPanel,BorderLayout.SOUTH);
               
    }

   
   
    private void countByTextFieldHandler()
    {
    try
        {
            if(applicationControlsAreActive())   // Only change if we are in the setup phase
            {
                String input = countByTextField.getText().trim();
                delta = Integer.parseInt(input);
                setupStatusLabel.setText("Counting by " + delta);
                
            }
        }
        catch(Exception e)
        {
            // don't change the delta if we had an exception
            setupStatusLabel.setText("Need integer value to count by");

        }
    
    }


    private void startAtTextFieldHandler()
    {
    try
        {
            if(applicationControlsAreActive())   // Only change if we are in the setup phase
            {
                String input = startAtTextField.getText().trim();
                int value = Integer.parseInt(input);
                if(value>=0)
                {
                    start = value;
                    setupStatusLabel.setText("Starting at " + start);
                }
                else
                {
                    setupStatusLabel.setText("Need a positive value ");
                }
                
            }
        }
        catch(Exception e)
        {
            // don't change the delta if we had an exception
            setupStatusLabel.setText("Need integer value to start at");
        }
    
    }    
    

    
            
} // end class SummationActionThread

