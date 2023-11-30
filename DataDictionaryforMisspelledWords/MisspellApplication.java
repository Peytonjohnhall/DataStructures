import java.awt.*;
import javax.swing.*;

/**
 * An application to detect possibly misspelled words
 */
    
public class MisspellApplication 
{

   public static void main (String args[]) 
    {
        JPanel myPanel;
        Stepper myStepper;
        ActionThread myThread;
        Object dispatcher;
        
        myThread = new MisspellActionThread();        
                                                               
        myPanel =  myThread.getAnimationPanel();
 
        dispatcher = new Object();
        AnimatedApplicationFrame myFrame = 
            new AnimatedApplicationFrame(myThread.getApplicationTitle(),
                                        dispatcher, myPanel, 
                                        myThread);
        myStepper = myFrame.getStepper();
        
        // Must set the stepper before we start the thread
        myThread.setStepper(myStepper);
        myThread.start();
    }
    
 
}
