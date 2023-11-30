import java.io.*;
import java.util.*;

/**
 * CountingGame is a program that will simulate a children's counting game used 
 * to select someone.
 * 
 * @author Peyton Hall
 */
    
public class CountingGame
{
    public static void main(String args[])
    {
        ListInterface<Integer> players = null;
        ListInterface<String> rhyme = null;
        
        int max;
        int position = 1;       // always start with the first player
        
        System.out.println("Please enter the number of players.");
        max = getInt("   It should be an integer value greater than or equal to 2.");
        System.out.println("Constructing list of players");
        
        // create a list of players
        players = new AList<>();
        for (int i = 1; i <= max; i++)
        {
            players.add(i);
        }
        
        System.out.println("The players list is " + players);
        rhyme = getRhyme(); // call the getRhyme method

        // Call doRhyme(players, rhyme, position) in a loop, 
        // as long as there is more than one player left
        while (players.getLength() > 1) 
        {
            System.out.println("The updated players list is " + players);
            position = doRhyme(players, rhyme, position);
        }  

        System.out.println("The winner is " + players.getEntry(1));
    }
    
    
    /**
     * Do the rhyme with the players in the list and remove the selected
     * player.
     *
     * @param  players   A list holding the players.
     * @param  rhyme   A list holding the words of the rhyme.
     * @param  startAt A position to start the rhyme at.
     * 
     * @return The position of the player eliminated.
     */
    public static int doRhyme(ListInterface<Integer> players, 
                              ListInterface<String> rhyme, 
                              int startAt)
    {
        // for each word in the rhyme
        // print the word in the rhyme and the player that says it
        // print the name of the player to be removed
        // remove the player from the list
        // return the index of the player that will start the next round
        int currentPlayer = startAt;
        int rhymeSize = rhyme.getLength();
        int numPlayers = players.getLength();

        // Loop through each word in the rhyme
        for (int i = 1; i <= rhymeSize; i++) 
        {
            String word = rhyme.getEntry(i);

            // Print the word and the player that says it
            System.out.println("Player " + currentPlayer + " says: " + word);

            // Calculate the index of the player to be removed
            int playerToRemove = (currentPlayer + i - 1) % numPlayers;

            // print the name of the player to be removed
            System.out.println("Player " + players.getEntry(playerToRemove + 1) + " is eliminated.");

            // remove the player from the list
            players.remove(playerToRemove + 1);

            // Adjust the current player index
            if (playerToRemove < currentPlayer) 
            {
                currentPlayer--;
            }

            // Wrap back around to the first player
            if (currentPlayer > players.getLength()) 
            {
                currentPlayer = 1;
            }

            numPlayers--;

            if (numPlayers == 1) // If only one player remains
            {
                // declare them as the winner
                System.out.println("The winner is Player " + players.getEntry(1));
                return players.getEntry(1); // Return the index of the winner
            }
            // Update the values after elimination
            currentPlayer = (currentPlayer % numPlayers) + 1;
            numPlayers--;
        }

        // return the index of the player that will start the next round
        return currentPlayer;
    }
    
    
    /**
     * Get an integer value.
     *
     * @return     An integer. 
     */
    private static int getInt(String rangePrompt)
    {
        Scanner input;
        int result = 10;        //Default value is 10
        try
        {
            input = new Scanner(System.in);
            System.out.println(rangePrompt);
            result = input.nextInt();
        }
        catch(NumberFormatException e)
        {
            System.out.println("Could not convert input to an integer");
            System.out.println(e.getMessage());
            System.out.println("Will use 10 as the default value");
        }        
        catch(Exception e)
        {
            System.out.println("There was an error with System.in");
            System.out.println(e.getMessage());
            System.out.println("Will use 10 as the default value");
        }
        return result;
    }
    

    /**
     * getRhyme - Get the rhyme.
     *
     * @return    A list of words that is the rhyme.
     */
    private static ListInterface<String> getRhyme()
    {
        Scanner input = new Scanner(System.in);
        String inString = "";
        ListInterface<String> rhyme = new AList<String>();
        
        try
        {
            System.out.println("Please enter a rhyme");
            inString = input.nextLine().trim();
            String [] tokens = inString.split(" ");

            for(int i = 0; i < tokens.length; i++)
            {
                rhyme.add(tokens[i]);
            }
        }
        catch(Exception e)
        {
            System.out.println("There was an error with System.in");
            System.out.println(e.getMessage());
            System.out.println("Will use a rhyme of size one");
        }
        // Make sure there is at least one word in the rhyme
        if(rhyme.getLength() < 1)
        {
            rhyme.add("Default");
        }
            
        return (ListInterface<String>)rhyme;
    }
}
