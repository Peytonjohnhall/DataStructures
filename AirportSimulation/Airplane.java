/** A class to represent an airplane.
    @author Peyton J. Hall
*/

public class Airplane
{
    private int timeInQueue;
    private int startTime;
    private int currentTime;

    /** 
    	@param startTime is an integer representing the start
    					 time of the airplane.
		Precondition:    startTime is a non-negative integer.
		Postcondition:   An instance of the Airplane class is created
						 with a start time.
	*/
    public Airplane(int startTime)
    {
    	// The constructor will save the startTime into the field
        this.startTime = startTime;
        timeInQueue = 0;
    }

    /** 
		Precondition:  the airplane has entered a queue and the time in has
					   been saved into the feld.
		Postcondition: the timelnQueue has been returned.
	*/
    public int getTimeInQueue()
    {
    	// Getter to retrieve the amount of time 
        // an object has spent in a queue.
        return timeInQueue;
    }

    /** 
    	@param startTime is an integer representing the time in which
    					 the airplane entered the queue.
		Precondition:    None.
		Postcondition:   The timeInQueue field of the Airplane object is
					     set to the startTime value.
	*/
    public void setTimeInQueue(int startTime)
    {
    	// Setter to set the amount of time 
        // an object has spent in a queue.
        this.currentTime = currentTime; // Assign the argument to the variable
        timeInQueue = this.currentTime - startTime; // calculate timeInQueue
    }
}