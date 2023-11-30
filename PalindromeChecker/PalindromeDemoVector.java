import java.util.Vector;
import java.util.Scanner;

public class PalindromeDemoVector
{
    public static void main(String[] args) 
    {
        System.out.println("This program will determine if an entered string" +
                           " is a palindrome.\nEnter the string you want to" +
                           " check for palindrome:"); // aesthetic preference
        Scanner input = new Scanner(System.in);
        String enteredString = input.nextLine();
        String reversedStringInput = ""; // Stack: last in, first out (LIFO)
        String userInput = enteredString.toLowerCase(); 

        Vector<Character> stack = new Vector<>(); // used to implement a stack
        for (int i = 0; i < userInput.length(); i++) 
        {
            stack.add(userInput.charAt(i)); // goes to the character at index i
        } // Pushed the characters to a stack

        while (!stack.isEmpty()) // peek the stack
        {
            reversedStringInput += stack.remove(stack.size() - 1); 
        } // Popped characters from the stack

        if (userInput.equals(reversedStringInput)) 
        {
            System.out.println("\"" + enteredString + "\"" 
                               + " is a palindrome");
        } 
        else 
        {
            System.out.println("\"" + enteredString + "\"" + 
                               " is not a palindrome");
        }
    }
}
