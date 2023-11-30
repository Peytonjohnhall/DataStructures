/** An exception indicating that the queue is empty.
   @author Peyton J. Hall
   Precondition:  None.
   Postcondition: runSimulatiion method can call EmptyQueueException.
*/
public class EmptyQueueException extends RuntimeException 
{
   public EmptyQueueException() 
   {
   super("The queue is empty.");
   }
}