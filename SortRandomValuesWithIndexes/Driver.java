import java.util.Scanner;

/** The driving class that processes user input.
    @author Peyton J. Hall
*/

public class Driver
{
    /**
        Precondition:  None.
        Postcondition: The program generates data, processes user 
                       input, and provides the desired output.
    */
    public static void main(String[] args)
    {
        IntervalFinder intervalFinder = new IntervalFinder();
        intervalFinder.generateData();
        intervalFinder.printData();
        
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the list of integer values (all on one line)"
                           + ", or just press enter if you are done.");
        String userInput = input.nextLine();

        while (!userInput.isEmpty()) // find the interval.
        {
            String[] userInputList = userInput.split(" ");
            int[] numbers = new int[userInputList.length];
            for (int i = 0; i < userInputList.length; i++)
            {
                numbers[i] = Integer.parseInt(userInputList[i]);
            } // input turned to integer, with Integer.parseInt

            intervalFinder.findInterval(numbers);

            System.out.println("Enter the list of integer values (all on one" +
                               " line), or just press enter if you are done.");
            userInput = input.nextLine();
        }
        System.out.println("Goodbye!");
    }
}
