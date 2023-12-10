import TreePackage.*;
import java.util.Iterator;

/** A class to implement the interface.
    @author       Peyton J. Hall
    @param <T>    The type of elements in the heap, 
                  which must be comparable.
    Precondition: The type T must implement the Comparable
                  interface, to ensure that the elements 
                  can be compared, for ordering.
*/
public class BstMaxHeap<T extends Comparable<? super T>>
                          implements MaxHeapInterface<T>
{
    private SearchTreeInterface<T> heap;
    
    /** Creates an empty max heap.
        Precondition:  None.
        Postcondition: An empty max heap is created.
    */
    public BstMaxHeap()
    {
        heap = new BinarySearchTree<>();
    } // end default constructor

    /** Creates a max heap from an array of elements.
        @param array   The array of elements to be added to the heap.
        Precondition:  The type T must implement the Comparable interface.
        Postcondition: The heap is created, with added elements.
    */
    public BstMaxHeap(T[] array)
    {
        heap = new BinarySearchTree<>();
        for(int i = 0; i < array.length; i++)
        {
            add(array[i]);
        }
    } // end array constructor

    /** Adds a new entry to the max heap.
        @param newEntry The object to be added.
        Precondition:   None.
        Postcondition:  The new entry is added to the max heap.
    */
    @Override
    public void add(T newEntry)
    {
        heap.add(newEntry);
    } // end add

    /** Removes and returns the largest item in the max heap.
        @return        Either the largest object in the heap, 
                       or, if the heap is empty, null.
        Precondition:  The heap is not empty.
        Postcondition: The largest item is removed.
    */
    @Override
    public T removeMax()
    {
        T max = getMax();
        heap.remove(max);
        return max;
    } // end removeMax

    /** Retrieves the largest item in the max heap.
        @return        Either the largest object in the heap,
                       or, if the heap is empty, null.
        Precondition:  The heap is not empty.
        Postcondition: None.
    */
    @Override
    public T getMax()
    {
        T max = null;
        Iterator<T> iterator = heap.getInorderIterator();
        while (iterator.hasNext())
        {
            max = iterator.next();
        }
        return max;
    } // end getMax

    /** Detects whether this heap is empty.
        @return        True, if the heap is empty.
        Precondition:  None.
        Postcondition: None.
    */
    @Override
    public boolean isEmpty()
    {
        return heap.isEmpty();
    } // end isEmpty

    /** Gets the size of this heap.
        @return        The number of entries in the heap.
        Precondition:  None.
        Postcondition: None.
    */
    @Override
    public int getSize()
    {
        return heap.getNumberOfNodes();
    } // end getSize

    /** Removes all entries from this heap.
        Precondition:  None.
        Postcondition: The heap is empty.
    */
    @Override
    public void clear()
    {
        heap.clear();
    } // end clear
} // end BstMaxHeap
