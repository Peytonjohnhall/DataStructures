/** An interface to represent a queue.
   @author Peyton J. Hall
   @param <T>     the type of elements stored in the queue
   Precondition:  None.
   Postcondition: runSimulation method can call QueueInterface.
                  An instance of the QueueInterface is created.
 */
public interface QueueInterface<T> 
{
   void enqueue(T newEntry);
   T dequeue();
   T getFront();
   boolean isEmpty();
   void clear();
}