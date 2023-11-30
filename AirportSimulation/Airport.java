import java.util.Scanner;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileWriter;   // Import the FileWriter class

/** Driver class to simulate an airport.
    @author Peyton J. Hall
*/

public class Airport 
{
    private double landingProbability;
    private double takeoffProbability;
    private int maxLandingQueueWait;
    private Runway runway;
    private int landingCount = 0;
    private int takeoffCount = 0;
    private int crashCount = 0;
    private int timeInLandingQueue = 0;
    private int timeInTakeoffQueue = 0;
    private int simulationLength;
    private LinkedQueue<Integer> landingQueue;
    private LinkedQueue<Integer> takeoffQueue;
    private double averageTimeInTakeoffQueue = 0;
    private double averageTimeInLandingQueue = 0; 
    private static FileWriter myWriter;

    /** 
        Precondition:  None.
        Postcondition: The simulation has run to completion, and the desired
                       statistics have been returned.
    */
    public static void main(String[] args) 
    {
        Airport airport = new Airport();
        airport.inputData();

        try 
        {
            myWriter = new FileWriter("debugoutput" + 
                                      ".txt"); // Create Writer object
        } 
        catch (IOException e) 
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        airport.runSimulation(myWriter);
    }

    /** 
        Precondition:  None.
        Postcondition: The user provided input values for the simulation.
    */
    public void inputData()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter landing time for a plane (in minutes): ");
        int landingTime = scanner.nextInt();
        System.out.print("Enter takeoff time for a plane (in minutes): ");
        int takeoffTime = scanner.nextInt();
        
        // Prompt for probability percentages (without percent % symbol)
        System.out.print("Enter landing probability as a percentage: ");
        landingProbability = scanner.nextDouble() / 100.0;
        System.out.print("Enter takeoff probability as a percentage: ");
        takeoffProbability = scanner.nextDouble() / 100.0;

        System.out.print("Enter maximum landing queue wait time " + 
                         "(in minutes): ");
        maxLandingQueueWait = scanner.nextInt();

        System.out.print("Enter the simulation length " + 
                         "(in minutes): ");
        simulationLength = scanner.nextInt();
        runway = new Runway(takeoffTime, landingTime);

        scanner.close();
    }

    /** 
        @param myWriter         is passed to enable utilization of the writer 
                                object, created in the main method.
        Precondition:           The airport and its components (runway, landing
                                queue, takeoff queue) are initialized and set
                                up with appropriate values.
        Postcondition:          The simulation of the airport is completed, and
                                the results are ready for output.
    */
    public void runSimulation(FileWriter myWriter)
    {
        try
        {
        	// queue for airplanes waiting to land
        	QueueInterface<Airplane> landingQueue = new LinkedQueue<>();

        	// queue for airplanes waiting to take off
        	QueueInterface<Airplane> takeoffQueue = new LinkedQueue<>();

            for (int currentTime = 0; currentTime <= simulationLength; 
                 currentTime++)
            {
            	// Create an Airplane object
				Airplane airplane = new Airplane(currentTime);

                // x is a placeholder for Math.random(). The landing
                // probability is updated so it is not a fixed number
                double x = Math.random();
                if (x < landingProbability)
                {
                    landingQueue.enqueue(airplane);
                    landingCount++; // landing queue plane arrival
                }
                // y is a placeholder for Math.random(). The takeoff
                // probability is updated so it is not a fixed number
                double y = Math.random();
                if (y < takeoffProbability) 
                {
                    takeoffQueue.enqueue(airplane);
                    takeoffCount++; // takeoff queue plane arrival
                }
                // Check runway status
                if (runway.isRunwayOccupied()) 
                {
                    myWriter.write("  " + "minutes: " + currentTime + 
                                       ", runway is not clear" + "\n");
                } 
                else 
                {
                    myWriter.write("  " + "minutes: " + currentTime + 
                                       ", runway is clear" + "\n");
                }

                // Check landing queue status
                if (!landingQueue.isEmpty()) 
                {
                    myWriter.write("  " + "landing queue is not empty" + "\n");
                }
                else
                {
                	myWriter.write("  " + "landing queue is empty" + "\n");
                }
                // Check takeoff queue status
                if (!takeoffQueue.isEmpty()) 
                {
                    myWriter.write("  " + "take off queue is not empty" +
                                    "\n");
                }
                else
                {
                	myWriter.write("  " + "take off queue is empty" +
                                    "\n");
                }

                if (!runway.isRunwayOccupied()) 
                {
                    myWriter.write("minutes: " + currentTime + "\n");
                    myWriter.write("minutes: " + currentTime + 
                    			   " landing prob: " + x + "\n");
                    myWriter.write("minutes: " + currentTime + 
                    			   " take off prob: " + y + "\n");
                
                    // landing queue logic implementation:
                    if (!landingQueue.isEmpty()) 
                    {
						Airplane landingAirplane = landingQueue.dequeue();
						int landingTime = landingAirplane.getTimeInQueue();
						int waitTime = currentTime - landingTime;

                        if (waitTime > maxLandingQueueWait)
                        {
                            crashCount++; // landing queue wait time overflow
                            myWriter.write("Plane crashed!!\n");
                        } 
                        else 
                        {
                            timeInLandingQueue = timeInLandingQueue + waitTime;
                            runway.setCurrentPlaneLanding(true);
                        } // in use is assumed to be true
                    } 
                    else 
                    {
                        // takeoff queue logic implementation
                        if (!takeoffQueue.isEmpty() && 
                        	!runway.isRunwayOccupied()) 
                        {
                            Airplane takeoffAirplane = takeoffQueue.dequeue();
							int takeoffTime = takeoffAirplane.getTimeInQueue();
							int waitTime = currentTime - takeoffTime;
							timeInTakeoffQueue += waitTime;
                        }
                    }
                }

            // Calculate average times, using double data type:
            averageTimeInLandingQueue = (double) timeInLandingQueue 
                                        / landingCount;
            averageTimeInTakeoffQueue = (double) timeInTakeoffQueue 
                                        / takeoffCount;
            outputResults();
            } // end for loop
        } // end try block
        catch (IOException e) 
        {
            System.out.println("An error occurred.");
                e.printStackTrace();
        }
    }

    /** 
        Precondition:  The simulation has completed and the required data has 
                       been saved.
        Postcondition: the following information has been printed out to the 
                       console: 
                       number of planes that took off in the simulation time, 
                       number of planes that landed in the simulation time,
                       number of planes that exceed the maximum length of time
                       allowed in the queue, and crashed,
                       average time that a plane spent in the takeoff queue,
                       average time that a plane spent in the landing queue.
    */
    public void outputResults() 
    {
        if (landingCount > 0) 
        {
            averageTimeInLandingQueue = (double) timeInLandingQueue 
                                        / landingCount;
        } 
        else 
        {
            averageTimeInLandingQueue = 0;
        }

        if (takeoffCount > 0) 
        {
            averageTimeInTakeoffQueue = (double) timeInTakeoffQueue 
                                        / takeoffCount;
        } 
        else 
        {
            averageTimeInTakeoffQueue = 0;
        }

        try 
        {
            // myWriter.write("Simulation completed!" + "\n");
            myWriter.write("Number of planes landed: " + landingCount + "\n");
            myWriter.write("Number of planes took off: " + 
                           takeoffCount + "\n");
            myWriter.write("Number of planes crashed: " + crashCount + "\n");
            myWriter.write("Average time in landing queue: " + 
                           averageTimeInLandingQueue + "\n");
            myWriter.write("Average time in takeoff queue: " + 
                           averageTimeInTakeoffQueue + "\n" + "");
        } 
        catch (IOException e) 
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
