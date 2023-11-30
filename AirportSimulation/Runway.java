/** A class to represent a runway.
	@author Peyton J. Hall
*/

public class Runway 
{
    private boolean isOccupied;
    private boolean currentPlaneTakingOff;
    private boolean currentPlaneLanding;
    private int takeoffTime;
    private int landingTime;
    private int startTimeOnRunway;

	/** 
    	@param tTime   tTime represents required take off time (in minutes).
    	@param lTime   lTime represents required landing time (in minutes).
		Precondition:  tTime is a non-negative integer representing the amount
					   of time needed for one plane to take off (in minutes).
					   lTime is a non-negative integer representing the amount
					   of time needed for one plane to land (in minutes).
		Postcondition: An instance of the Runway class is created with the 
					   specified takeoff time and landing time.
	*/
	public Runway(int tTime, int lTime) 
	{
		// Constructors for initializing the fields are like so:
        takeoffTime = tTime;
        landingTime = lTime;
        isOccupied = false;
        currentPlaneTakingOff = false;
        currentPlaneLanding = false;
        startTimeOnRunway = -1;
    }

    /** 
		Precondition: 	   currentTime is a non-negative integer representing
						   the current time in minutes.
		Postcondition: 	   Returns a boolean value to indicate whether the 
						   runway is occupied at the current time.
	*/
    public boolean isRunwayOccupied() 
	{
	    // Determines if the runway is occupied. If it is occupied, 
	    // then it is not yet ready for the next plane. 
	    // Returns true if the runway is occupied.
	    return isOccupied && (currentPlaneTakingOff || 
	    					 (startTimeOnRunway != -1 && currentPlaneLanding));
	}

    /** 
    	@param available boolean value to represent the availability status 
    					 of the runway.
		Precondition:    avail is a boolean value representing the availability
						 status of the runway.
		Postcondition:   The availability status of the runway is updated based
						 on the value of avail.
	*/
    public void setAvailable(boolean available)
    {
    	// Setter for available field. avail is true 
    	// if the runway is available for use
        isOccupied = !available;
        if (!available) 
		{
            startTimeOnRunway = -1;
    	}
    }

    /** 
		@param inUse   represents runway status.
		Precondition:  inUse is a boolean value representing whether the runway
					   is currently being used for a plane taking off (true) or
					   not (false).
		Postcondition: The state of the runway is updated to indicate whether
					   it is currently in use for a plane taking off or not.
	*/
    public void setCurrentPlaneTakingOff(boolean inUse)
    {
    	// Setter for the inUseForTakeoff field. inUse is true 
    	// if the runway is in use by an airplane taking off.
        currentPlaneTakingOff = inUse;
        isOccupied = inUse;
    } 

    /** 
		@param inUse   boolean represents the runway’s status for landing.
		Precondition:  None.
		Postcondition: Based on inUse parameter, the runway status is updated.
					   It indicates whether or not the runway is currently in
					   use for landing.
	*/
    public void setCurrentPlaneLanding(boolean inUse)
    {
    	// Setter to update the runway’s status on 
    	// if it is currently in use or not.
    	currentPlaneLanding = inUse;
        isOccupied = inUse;
    }

    /** 
		@param time    int to represent the start time set for the runway.
		Precondition:  None.
		Postcondition: The runway start time is updated to the time parameter.
	*/
    public void setStartTimeOnRunway(int time)
    {
    	// Setter method used to start the time for the runway.
    	startTimeOnRunway = time;
    }
}