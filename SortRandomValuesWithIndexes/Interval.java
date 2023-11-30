import java.util.Arrays;
import java.util.Random;

/** A class that generates and prints randomly generated data.
    @author Peyton J. Hall
*/

public class Interval
{
    private int[] data;

    /**
        Precondition:  None.
        Postcondition: Returns the data array.
    */
    public int[] getData()
    {
        return data;
    }

    /**
        @param num     The number of elements to generate.
        Precondition:  num is positive.
        Postcondition: The data array is populated with num random 
                       integers, between 0 and 99, inclusive.
                       The data array is sorted in ascending order.
    */
    public void generateData(int num)
    {
        data = new int[num]; // Initialize data array with number of elements.
        Random random = new Random();
        for (int i = 0; i < num; i++)
        {
            data[i] = random.nextInt(100);
        }
        Arrays.sort(data); // Sort the data array in ascending order.
    }

    /**
        Precondition:  None.
        Postcondition: Elements in the data array are printed to the console.
    */
    public void printData()
    {
        System.out.println("The sorted data is:");
        for (int i = 0; i < data.length; i++)
        {
            System.out.print(data[i] + " "); // Print each element.
        }
        System.out.println(""); // leave a space and move to the next line.
    }
}