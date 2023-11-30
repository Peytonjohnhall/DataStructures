import TreePackage.*;
import java.io.*;
import java.util.*;

/**
 * Find the unique identifiers from a .java file.
 * 
 * @author Peyton J. Hall
 */
    
public class Identifiers
{

    public static void main(String args[])
    {


        String fileName = getFileName();
        System.out.println();
        
        BinarySearchTree<String> unique = getPossibleIds(fileName);
        
        // Use an in-order iterator to print out the values
        // call the getInorderIterator method from the BinaryTree class
        System.out.println("The possible identifiers list is ");
        Iterator<String> iterator = unique.getInorderIterator();
        while(iterator.hasNext())
        {
            System.out.println(iterator.next());
        }
    }
   
    /**
     * Get the possible identifiers from the file.
     *
     * @return    A tree of possible identifiers from the file.
     */
    private static BinarySearchTree<String> getPossibleIds(String theFileName)
    {
        Scanner input;
        String inString = "";
        BinarySearchTree<String> possible = new BinarySearchTree<String>();
        
        try
        {
            input = new Scanner(new File(theFileName ) );

            while(input.hasNextLine())
            {
                // Use StringTokenizer to split the line into identifiers
                // Create a StringTokenizer object
                inString = input.nextLine();
                StringTokenizer tokenizer = new StringTokenizer(inString, "\"!@#$%^&*()=+-{}|");
                while(tokenizer.hasMoreTokens())
                {
                    possible.add(tokenizer.nextToken());
                }
            }
        }
        catch(IOException e)
        {
            System.out.println("There was an error with System.in");
            System.out.println(e.getMessage());            
        }
            
        return possible;
                                    
    }
       
    private static String getFileName()
    {
        Scanner input;
        String inString = "data.txt";
        
        try
        {
            input = new Scanner(System.in);
            
            System.out.println("Please enter the name of the file:");
            inString = input.next();            
        }
        catch(Exception e)
        {
            System.out.println("There was an error with System.in");
            System.out.println(e.getMessage());
            System.out.println("Will try the default file name data.txt");
        }
            
        return inString;
                                    
    }
}
