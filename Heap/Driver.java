/**
   A driver to demonstrate the class BstMaxHeap.
   @author Peyton J. Hall
*/
public class Driver 
{
	public static void main(String[] args) 
	{
		String justin   = "Justin";
		String brad     = "Brad";
		String blake    = "Blake";
		String daniel   = "Daniel";
		String michael  = "Michael";
		String jack     = "Jack";
		String william	 = "William";
		String mason    = "Mason";
		String richard  = "Richard";

		// create an instance of BstMaxHeap
		MaxHeapInterface<String> theHeap = new BstMaxHeap<>();
		theHeap.add(justin);		
		theHeap.add(brad);
		theHeap.add(blake);
		theHeap.add(daniel);		
		theHeap.add(michael);
		theHeap.add(jack);
		theHeap.add(william);		
		theHeap.add(mason);
		theHeap.add(richard);
		
		if (theHeap.isEmpty())
		{
			System.out.println("The heap is empty.");
		}
		else
		{
			System.out.println("The heap is not empty; it contains " +
			                   theHeap.getSize() + " entries.");
		}
		
		System.out.println("The largest entry is " + theHeap.getMax());
		
		System.out.println("\n\nRemoving entries in descending order:");
		while (!theHeap.isEmpty())
		{
			System.out.println("Removing " + theHeap.removeMax());
		}

			
		System.out.println("\n\nTesting constructor with array parameter:\n");
		String[] nameArray = {justin, brad, blake, daniel, michael, 
		                      jack, william, mason, richard};
		MaxHeapInterface<String> anotherHeap = new BstMaxHeap<>(nameArray);
		
		if (anotherHeap.isEmpty())
		{
			System.out.println("The heap is empty.");
		}
		else
		{
			System.out.println("The heap is not empty; it contains " +
			                    anotherHeap.getSize() + " entries.");
		}
		
		System.out.println("The largest entry is " + anotherHeap.getMax());
		
		System.out.println("\n\nRemoving entries in descending order:");
		while (!anotherHeap.isEmpty())
		{
			System.out.println("Removing " + anotherHeap.removeMax());
		}
	}  // end main
}  // end Driver