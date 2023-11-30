import java.util.Scanner;

public class PalindromeDemoArray
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

        char[] stack = new char[userInput.length()];
        int topIndex = -1; 
        for (int i = 0; i < userInput.length(); i++) 
        {
            stack[++topIndex] = userInput.charAt(i);
        } // Pushed the characters to a stack

        while (topIndex >= 0) // peek the stack
        {
            reversedStringInput += stack[topIndex--];
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
