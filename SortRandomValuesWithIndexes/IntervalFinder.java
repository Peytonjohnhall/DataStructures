/** A class that finds the interval
	@author Peyton J. Hall
*/

public class IntervalFinder 
{
    private static final int NUMBER_OF_ELEMENTS = 15;
    private Interval interval;
    private int start;
    private int end;

    /**
		Precondition:  None.
		Postcondition: Initializes an instance of IntervalFinder,
					   with an Interval object.
    */
    public IntervalFinder()
    {
        interval = new Interval();
    }

    /**
		@param num     The number of elements to generate.
		Precondition:  num is positive.
		Postcondition: The data array in the Interval object is populated with
					   num random integers, between 0 and 99, inclusive, 
					   and is sorted in ascending order.
    */
    public void generateData()
    {
        interval.generateData(NUMBER_OF_ELEMENTS); // call generateData.
    }

    /**
		Precondition:  None.
		Postcondition: Prints the sorted data in the Interval object.
    */
    public void printData()
    {
        interval.printData(); // call printData.
    }

    /**
		@param numbers An array of target values to find in the data array.
		Precondition:  The data array is sorted.
		Postcondition: Determines and prints the start and end indices of the
					   interval, containing the given target values.
    */
    public void findInterval(int[] numbers)
    {
	    int[] data = interval.getData();

	    // Check if the first number in the user input 
	    // is less than the first number in the data array.
	    if (numbers[0] < data[0])
	    {
	    	// If it is, set the start index to -1,
	    	// to indicate that there is no valid interval.
	        start = -1;
	    } 
	    else
	    {
	    	// Initialize the start index as 0, by default.
			start = 0;
	        // Search for the correct start index within the data array.
	        for (int i = 0; i < NUMBER_OF_ELEMENTS - 1; i++)
	        {
	        	// If the current element in the data array is less than 
	        	// the first number in the user input, and 
	        	// if the next element is greater:
	            if ((data[i] < numbers[0]) && (data[i + 1] > numbers[0]))
	            {
	            	// Set the start index to the current index i.
	                start = i;
	            } 
	            // If the current element in the data array is less than the 
	            // first number in the user input, and if the next element
	            // is equal to the first number:
	            else if ((data[i] <= numbers[0]) && (data[i + 1] == numbers[0]))
	            {
	            	// Set the start index to the next index i + 1.
	                start = i + 1;
	            }
	        } // end for
	    } // end else

	    // If the last number in the user input is greater than 
	    // the last number in the data array:
	    if (numbers[numbers.length - 1] > data[NUMBER_OF_ELEMENTS - 1])
	    {
	    	// Set the end index to the length of the data array.
	        end = NUMBER_OF_ELEMENTS;
	    } 
	    else
	    {
	    	// Initialize the end index as not defined initially.
    		// It will be calculated based on the user input and data array.
	        for (int i = 0; i < NUMBER_OF_ELEMENTS - 1; i++)
	        {
	        	// If the current element in the data array is less than
	        	// the last number in the user input, and 
				// if the next element is greater or equal:
	            if ((data[i] < numbers[numbers.length - 1]) && 
	            	(data[i + 1] >= numbers[numbers.length - 1]))
	            {
	            	// Set the end index to the current index i + 1.
	                end = i + 1;
	                break;
	            }
	        } // end for
	    } // end else

	    System.out.println("The pair of indices that bound the interval cont" +
	    				   "aining the given values is (" + start + ", " + end 
	    				   + ")" + "\n");
	}
}