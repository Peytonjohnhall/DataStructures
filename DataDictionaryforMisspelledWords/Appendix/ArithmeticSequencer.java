import java.util.*;
import java.awt.*;

/**
 * A class that will be used to compute an arithmetic sequence.  Since it may be accessed by user interface threads
 * (paint) and the application thread, make all methods synchronized.
 */
        
public class ArithmeticSequencer
    {
        private int value;
        private int delta;
        private int count;
        private java.util.List<String> values;
        
        public ArithmeticSequencer(int start, int changeBy)
        {
            values = new ArrayList<String>();
            value = start;
            count = 0;
            delta = changeBy;
            values.add("Initial is " + value);
        }
        
                        
        
        /**
         * addNext - Add an amount to the current value.
         */
        synchronized 
        public void addNext()
        {
            value += delta;
            count++;
            values.add("Next value is " +value);
        }
        
                
    public static final int LINE_HEIGHT = 15;
    public static final int STAR = 15;
        
        
    /**
     * Braw a representation of the arithmetic sequencer at the given location
     * 
     * @param   g  The graphics context to draw on.  
     * @param   topCenterX  The x position of the center of the top of our drawing.
     * @param   topY  The y position of the top.
     * 
     */
    synchronized 
    public void drawOn(Graphics g, int topCenterX, int topY)
    {
       
        // Draw a star like thing 
        int starCenterX = topCenterX;
        int starCenterY = topY + STAR;
        double angle = 0;
        double angleChange = Math.PI;
        if(count !=0)
            angleChange = angleChange/count;
            
        for(int i = 0; i<count; i++)
        {
            int xDiff = (int) (STAR * Math.cos(angle));
            int yDiff = (int) (STAR * Math.sin(angle));
            g.drawLine(starCenterX+xDiff, starCenterY+yDiff, starCenterX-xDiff, starCenterY-yDiff);
            angle += angleChange;
        }
        

        // Draw each string
        FontMetrics fm = g.getFontMetrics();
        Iterator iter = values.iterator();
        int stringsStartY = topY + LINE_HEIGHT + 2*STAR;

        for(int i = 0; i<values.size(); i++)
        {
            String toDraw = (String) iter.next();
            // Center the strings
            int width = fm.stringWidth(toDraw);
            g.drawString(toDraw, topCenterX-width/2, stringsStartY+i*LINE_HEIGHT);
        }

    }
        
}