/** A class to represent an exception thrown, when values are invalid.
    @author Peyton J. Hall
    Precondition:  Parameter(s) does/do not meet the specified condition(s).
    Postcondition: An exception is thrown, with a message describing the issue.
*/
public class BadVisitDateException extends Exception
{
    /**
        @param message The message that describes the cause of the exception.
        Precondition:  None.
        Postcondition: Prints the message to the console.
    */
    public BadVisitDateException(String message)
    {
        super(message); // Calls the constructor, to provide the message.
    } // end BadVisitDateException method
} // end BadVisitDateException class