package TreePackage;

/**
   An interface for the ADT general tree.
*/
public interface GeneralTreeInterface<T> extends TreeInterface<T>,
                                                 TreeIteratorInterface<T>
{
   /** Sets the data in the root of this general tree.
      @param rootData The object that is the data for the tree's root.
   */
   public void setRootData(T rootData);
   
   /** Sets this general tree to a new general tree.
      @param rootData An object that is the data in the root of
                      the new tree.
      @param children An array of subtrees of the root. 
   */
   public void setTree(T rootData, GeneralTreeInterface<T>[] children);
} // end GeneralTreeInterface