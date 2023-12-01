package TreePackage;

/**
   A class that represents nodes in a binary tree (clonable version).
*/
public class CloneableBinaryNode<T> implements Cloneable
{
   private T                      data;
   private CloneableBinaryNode<T> leftChild;  // Reference to left child
   private CloneableBinaryNode<T> rightChild; // Reference to right child

   public CloneableBinaryNode()
   {
      this(null); // Call next constructor
   } // end default constructor

   public CloneableBinaryNode(T dataPortion)
   {
      this(dataPortion, null, null); // Call next constructor
   } // end constructor

   public CloneableBinaryNode(T dataPortion, CloneableBinaryNode<T> theLeftChild,
                                             CloneableBinaryNode<T> theRightChild)
   {
      data = dataPortion;
      leftChild = theLeftChild;
      rightChild = theRightChild;
   } // end constructor

   /** Retrieves the data portion of this node.
      @return  The object in the data portion of the node. 
   */
   public T getData()
   {
      return data;
   } // end getData
   
   /** Sets the data portion of this node.
      @param newData  The data object. 
   */
   public void setData(T newData)
   {
      data = newData;
   } // end setData
   
   /** Retrieves the left child of this node.
      @return  A reference to this node's left child. 
   */
   public CloneableBinaryNode<T> getLeftChild()
   {
      return leftChild;
   } // end getLeftChild
   
   /** Sets this node’s left child to a given node.
      @param newLeftChild  A node that will be the left child. 
   */
   public void setLeftChild(CloneableBinaryNode<T> theLeftChild)
   {
      leftChild = theLeftChild;
   } // end setLeftChild
   
   /** Detects whether this node has a left child.
      @return  True if the node has a left child. 
   */
   public boolean hasLeftChild()
   {
      return leftChild != null;
   } // end hasLeftChild
   
   /** Retrieves the right child of this node.
      @return  A reference to this node's right child. 
   */
   public CloneableBinaryNode<T> getRightChild()
   {
      return rightChild;
   } // end getRightChild
   
   /** Sets this node’s right child to a given node.
      @param newRightChild  A node that will be the right child. 
   */
   public void setRightChild(CloneableBinaryNode<T> theRightChild)
   {
      rightChild = theRightChild;
   } // end setRightChild
   
   /** Detects whether this node has a right child.
      @return  True if the node has a right child. 
   */
   public boolean hasRightChild()
   {
      return rightChild != null;
   } // end hasRightChild
   
   /** Detects whether this node is a leaf.
      @return  True if the node is a leaf. 
   */
   public boolean isLeaf()
   {
      return (leftChild == null) && (rightChild == null);
   } // end isLeaf
   
   /** Counts the nodes in the subtree rooted at this node.
      @return  The number of nodes in the subtree rooted at this node. 
   */
   public int getNumberOfNodes()
   {
      int leftNumber = 0;
      int rightNumber = 0;
      
      if (leftChild != null)
      {
         leftNumber = leftChild.getNumberOfNodes();
      }
      
      if (rightChild != null)
      {
         rightNumber = rightChild.getNumberOfNodes();
      }
      
      return 1 + leftNumber + rightNumber;
   } // end getNumberOfNodes
   
   /** Computes the height of the subtree rooted at this node.
      @return  The height of the subtree rooted at this node. 
   */
   public int getHeight()
   {
      return getHeight(this); // Call private getHeight
   } // end getHeight
   
   /** Makes a clone of this node and its subtrees.
      @return  The clone of the subtree rooted at this node. 
   */
   public Object clone()
   {
      CloneableBinaryNode<T> theCopy = null;
      try
      {
         @SuppressWarnings("unchecked")
         CloneableBinaryNode<T> temp = (CloneableBinaryNode<T>)super.clone();
         theCopy = temp;
      }
      catch (CloneNotSupportedException e)
      {
         throw new Error("CloneableBinaryNode cannot clone: " + e.toString());
      }
      
 //   theCopy.data = (T)data.clone(); // can't clone String
      
      if (leftChild != null)
      {
         @SuppressWarnings("unchecked")
         CloneableBinaryNode<T> temp = (CloneableBinaryNode<T>)leftChild.clone();
         theCopy.leftChild = temp;
      } // end if
      
      if (rightChild != null)
      {
         @SuppressWarnings("unchecked")
         CloneableBinaryNode<T> temp = (CloneableBinaryNode<T>)rightChild.clone();
         theCopy.rightChild = temp;
      } // end if
   
      return theCopy;
   } // end clone
   
   private int getHeight(CloneableBinaryNode<T> node)
   {
      int height = 0;
      if (node != null)
      {
         height = 1 + Math.max(getHeight(node.getLeftChild()),
                               getHeight(node.getRightChild()));
      }
      return height;
   } // end getHeight
} // end CloneableBinaryNode
