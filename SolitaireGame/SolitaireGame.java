import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;			
import java.util.Random;
/**     		
   A solitaire matching game in which you have a list of random
   integer values between 10 and 99. You remove from the list any
   pair of adjacent integers whose first or second digits match.
   If all values are removed, you win.
   @author Peyton Hall
   Note: NUMBER_COUNT can be changed at discretion, based on gameplay.
         For example, in the comments at the very bottom, it was 40.
 */
public class SolitaireGame
{
	private static final int NUMBER_COUNT = 6; // number count to begin with
   private static final int MIN_VALUE = 10; // numbers can't be single digit
   private static final int MAX_VALUE = 99; // numbers can't be triple digit
	private static List<Integer> theNumbers; // store ArrayList of random #'s

	/** 
		Initializes the list with 40 random 2 digit numbers. 
		@param theList The list to initialize.
	*/
	public static void initializeList(List<Integer> theList)
	{
      Random m = new Random();
		for (int i = 0; i < NUMBER_COUNT; i++) 
		{
         int num = m.nextInt(100);
         if (num < 10)
         {
            i--; // repeat iteration
         }
         else 
         {
            theList.add(num);
         }
      }
	} // end initializeList

	/** Sees whether two numbers are removable.
		 @param x  The first 2 digit integer value.
		 @param y  The second 2 digit integer value.
 		 @return  True if x and y match in the first or second digit. */
	public static boolean removable(Integer x, Integer y)
	{
      int x1 = x / 10; // integer division
      int x2 = x % 10; // modulo/modulus - returns remainder after division
      int y1 = y / 10;
      int y2 = y % 10;
      return (x1 == y1 || x2 == y2);
	} // end removable

	/** Scans over the list and removes any pairs of values that are removable.
		 @param theList  The list of 2 digit integers to scan over.
		 @return  True if any pair of integers was removed.  */
	public static boolean scanAndRemovePairs(List<Integer> theList)
	{
		boolean removed = false; // assume removed is false
      ListIterator<Integer> iterator = theList.listIterator();

      while (iterator.hasNext()) // Scan over each element in the list
      {
         int index = iterator.nextIndex(); // Get index of current element
         Integer current = iterator.next(); // Get current element
         if (index + 1 < theList.size()) // Check if a next element exists
         {
            Integer next = theList.get(index + 1); // Get the next element
            if (removable(current, next)) // call removable method
            {
               iterator.remove();
               iterator.next(); // Move the iterator to the next element
               iterator.remove();
               removed = true; // Set the boolean to indicate pair removal
               System.out.println("Removed: " + current + " " + next);
            }
         }
      }
      return removed;
	} // end scanAndRemovePairs

	public static void main(String args[])
	{
        theNumbers = new ArrayList<>();
       initializeList(theNumbers);
       System.out.println("The list is originally: " + theNumbers);

      while (scanAndRemovePairs(theNumbers))
         System.out.println("The list is now: " + theNumbers);
      System.out.println("No more pairs to remove.");
	} // end main
}  //  end SolitaireGame

/*
The list is originally: [81, 50, 11, 61, 42, 74, 16, 65, 49, 49, 11, 19, 67, 79, 33, 95, 85, 52, 59, 67, 46, 81, 62, 30, 60, 66, 80, 96, 30, 81, 37, 30, 34, 30, 15, 80, 11, 61, 55, 46]
   Removed: 11  61
   Removed: 49  49
   Removed: 11  19
   Removed: 95  85
   Removed: 52  59
   Removed: 30  60
   Removed: 37  30
   Removed: 34  30
   Removed: 11  61
The list is now: [81, 50, 42, 74, 16, 65, 67, 79, 33, 67, 46, 81, 62, 66, 80, 96, 30, 81, 15, 80, 55, 46]
   Removed: 65  67
   Removed: 62  66
The list is now: [81, 50, 42, 74, 16, 79, 33, 67, 46, 81, 80, 96, 30, 81, 15, 80, 55, 46]
   Removed: 81  80
The list is now: [81, 50, 42, 74, 16, 79, 33, 67, 46, 96, 30, 81, 15, 80, 55, 46]
   Removed: 46  96
The list is now: [81, 50, 42, 74, 16, 79, 33, 67, 30, 81, 15, 80, 55, 46]
No more pairs to remove.

 */
