/** A demonstration of the class ArrayBag
    @author Peyton J. Hall
*/
public class ArrayBagDemo
{
	public static void main(String[] args) 
	{
		String[] contentsOfBag = {"A", "B", "C", "D", "E", "F"};

		// Tests on an empty bag
		BagInterface<String> aBag = new ArrayBag<>(contentsOfBag.length);

		testAdd(aBag, contentsOfBag);
		displayBag(aBag);
	} // end main
   
	// Tests the method add.
	private static void testAdd(BagInterface<String> aBag, String[] content)
	{
		System.out.print("Adding ");
		for (int index = 0; index < content.length; index++)
		{
			aBag.add(content[index]);
			System.out.print(content[index] + " ");
		} // end for
		System.out.println();
      
		displayBag(aBag);
	} // end testAdd

	/**
		@param aBag is the bag to display.
		Precondition:   aBag parameter is not null.
		Postcondition:  Tests the method toArray, while displaying the bag.
	*/
    private static void displayBag(BagInterface<String> aBag) 
    {
        System.out.println("The bag contains " + aBag.getCurrentSize() +
                " string(s), in reverse order:");
        Object[] bagArray = aBag.toArray();
        reverseArray(bagArray, aBag.getCurrentSize() - 1); // call reverseArray
        System.out.println(); // Print a new line after reversing
    } // end displayBag

    /**
		@param array is the array reversed and printed.
		@param currentIndex is the current index of the array.
		Precondition:  array parameter is not null.
		Postcondition: Recursively prints the contents of the array
					   in reverse order.
    */
    private static void reverseArray(Object[] array, int currentIndex) 
    {
        if (currentIndex >= 0) 
        {
            System.out.print(array[currentIndex] + " ");
            reverseArray(array, currentIndex - 1); // function calls itself
        }
    } // end reverseArray
} // end ArrayBagDemo