import java.awt.*;
import javax.swing.*;

/**
 * This is a sample animated application
 */
    
public class AnimatedApplication 
{    
        
    public static void main (String args[]) 
    {
        JPanel myPanel;
        Stepper myStepper;
        ActionThread myThread;
        Object dispatcher;
        
        myThread = new ArithmeticSequenceActionThread();        // Change this line for different
                                                                // applications
        myPanel =  myThread.getAnimationPanel();
 
        dispatcher = new Object();
        AnimatedApplicationFrame myFrame = 
            new AnimatedApplicationFrame(myThread.getApplicationTitle(),
                                        dispatcher, myPanel, 
                                        myThread);
        myStepper = myFrame.getStepper();
        
        // must set the stepper before we start the thread
        myThread.setStepper(myStepper);
        myThread.start();
    }
    
    
 
}
