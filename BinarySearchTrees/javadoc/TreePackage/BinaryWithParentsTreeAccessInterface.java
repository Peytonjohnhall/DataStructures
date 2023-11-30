package TreePackage;


/**
 * This interface extends the access to a binary tree that has parent
 * pointers.
 */

public interface BinaryWithParentsTreeAccessInterface<T> extends BinaryTreeAccessInterface<T>
{
 
    /** Determines if the current node has a parent.
     * @return True if the parent point is non-nul.l
     * */
    public boolean canGoBackToParent(); 
    
    /** Sets the current node to the parent of
    * the current node. */
    public void goBackToParent();
 
} // end BinaryWithParentsTreeAccessInterface