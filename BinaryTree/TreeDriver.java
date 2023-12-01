import TreePackage.*;
import java.util.Iterator;

/**
    Driving class to demonstrate a binary search tree.
    @author Peyton J. Hall
*/
public class TreeDriver
{
    /**
        Main method to demonstrate the binary search tree.
    */
    public static void main(String[] args)
    {
        // Use an array of strings
        String[] dataArray = {"A", "B", "C", "D", "E", "F", "G",
                              "H", "I", "J", "K", "L", "M", "N"};
        // Instantiate and build the balanced binary search tree
        BinaryTree<String> binaryTree = new BinaryTree<String>();
        binaryTree.arrayToTree(dataArray, 0, dataArray.length - 1);
        testLevelOrder(binaryTree);
    }

    /**
        Test the level order traversal of the binary tree.
        @param binaryTree   The binary tree to test.
        Precondition:       The binary tree must be instantiated,
                            and it can not be empty.
        Postcondition:      The level order traversal of the 
                            binary tree is printed.
    */
    public static void testLevelOrder(BinaryTree<String> binaryTree)
    {
        Iterator<String> levelOrder = binaryTree.getLevelOrderIterator();
    
        // level 1
        System.out.print("\t\t\t" + levelOrder.next()); // tab (\t)
        System.out.print("\n");

        // level 2
        System.out.print("\t\t" + levelOrder.next());
        System.out.print("\t\t" + levelOrder.next());
        System.out.print("\n");

        // level 3
        System.out.print("\t" + levelOrder.next() + "\t");
        System.out.print(levelOrder.next() + "\t\t");
        System.out.print(levelOrder.next() + "\t");
        System.out.print(levelOrder.next());
        System.out.print("\n");

        // level 4
        while (levelOrder.hasNext())
        {
            System.out.print(levelOrder.next() + "\t");
        } // end while
        System.out.println("-"); // parent N has a left child, but not right.
    } // end testLevelOrder
} // end TreeDriver
