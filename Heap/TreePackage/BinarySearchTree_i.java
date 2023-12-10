package TreePackage;

/**
   A class that implements the ADT binary search tree by extending BinaryTree.
   Iterative version.
*/
public class BinarySearchTree_i<T extends Comparable<? super T>>
             extends BinaryTree<T> implements SearchTreeInterface<T>
{
   public BinarySearchTree_i()
   {
      super();
   } // end default constructor

   public BinarySearchTree_i(T rootEntry)
   {
      super();
      setRootNode(new BinaryNode<>(rootEntry));
   } // end constructor

   // Disable setTree (see Segment 26.6)
   public void setTree(T rootData, BinaryTreeInterface<T> leftTree,
                                   BinaryTreeInterface<T> rightTree)
   {
      throw new UnsupportedOperationException();
   } // end setTree
  
	public T getEntry(T entry) 
	{
		T result = null;
		boolean found = false;
		
		BinaryNode<T> currentNode = getRootNode();
		while (!found && (currentNode != null) )
		{
			T currentEntry = currentNode.getData();
			
			if (entry.equals(currentEntry))
			{
				result = currentEntry;
				found = true;
			}
			else if (entry.compareTo(currentEntry) < 0)
         {
				currentNode = currentNode.getLeftChild(); 
         }
			else
         {
				currentNode = currentNode.getRightChild(); 
         }
		} // end while
		
		return result;
	} // end getEntry
	
	public boolean contains(T entry)
	{
		return getEntry(entry) != null;
	} // end contains
	
	public T add(T newEntry)
	{
	   T result = null;

      if (isEmpty())
      {
         setRootNode(new BinaryNode<>(newEntry));
      }
      else
      {
         result = addEntry(newEntry);
      }

      return result;
	} // end add

   // Adds newEntry to the nonempty subtree rooted at rootNode.
   private T addEntry(T anEntry)
   {
      BinaryNode<T> currentNode = getRootNode();
      // Assertion: currentNode != null
      T result = null;
      boolean found = false;
      
      while (!found)
      {
         T currentEntry = currentNode.getData();
         int comparison = anEntry.compareTo(currentEntry);
         
         if (comparison == 0)
         {  // anEntry matches currentEntry;
            // return and replace currentEntry
            found = true;
            result = currentEntry;
            currentNode.setData(anEntry);
         }
         else if (comparison < 0)
         {
            if (currentNode.hasLeftChild())
            {
               currentNode = currentNode.getLeftChild();
            }
            else
            {
               found = true;
               currentNode.setLeftChild(new BinaryNode<>(anEntry));
            } // end if
         }
         else
         {
            // Assertion: comparison > 0
            
            if (currentNode.hasRightChild())
            {
               currentNode = currentNode.getRightChild();
            }
            else
            {
               found = true;
               currentNode.setRightChild(new BinaryNode<>(anEntry));
            } // end if
         } // end if
      } // end while
      
      return result;
   } // end addEntry

	public T remove(T entry)
   {
      T result = null;

      // Locate node (and its parent) that contains a match for entry
      NodePair pair = findNode(entry);
      BinaryNode<T> currentNode = pair.getFirst();
      BinaryNode<T> parentNode = pair.getSecond();

      if (currentNode != null)           // Entry is found
      {
         result = currentNode.getData(); // Get entry to be removed

         // Case 1: currentNode has two children
         if (currentNode.hasLeftChild() && currentNode.hasRightChild())
         {
            // Replace entry in currentNode with the entry in another node
            // that has at most one child; that node can be deleted

            // Get node to remove (contains inorder predecessor; has at
            // most one child) and its parent
            pair = getNodeToRemove(currentNode);
            BinaryNode<T> nodeToRemove = pair.getFirst();
            parentNode = pair.getSecond();

            // Copy entry from nodeToRemove to currentNode
            currentNode.setData(nodeToRemove.getData());

            currentNode = nodeToRemove;
            // Assertion: currentNode is the node to be removed; it has at 
            //            most one child
            // Assertion: Case 1 has been transformed to Case 2
         } // end if

         // Case 2: currentNode has at most one child; delete it
         removeNode(currentNode, parentNode);
      } // end if

      return result;
   } // end remove

	// Locate node that contains a match for entry
	private NodePair findNode(T entry)
	{
      NodePair result = new NodePair();
      boolean found = false;

      BinaryNode<T> currentNode = getRootNode();
      BinaryNode<T> parentNode = null;

      while (!found && (currentNode != null) )
      {
         T currentEntry = currentNode.getData();
         int comparison = entry.compareTo(currentEntry);
       
         if (comparison < 0)
         {
            parentNode = currentNode;
            currentNode = currentNode.getLeftChild(); 
         }
         else if (comparison > 0)
         {
            parentNode = currentNode;
            currentNode = currentNode.getRightChild(); 
         }
         else  // comparison == 0
         {
            found = true;
         }
      } // end while

      if (found)
      {
         // Located entry is currentNode.getData()
         result = new NodePair(currentNode, parentNode);
      }

      return result;
	} // end findNode

	// Gets the node that contains the inorder predecessor of currentNode.
	// Precondition: currentNode has two children 
   private NodePair getNodeToRemove(BinaryNode<T> currentNode)
   {
      // Find node with largest entry in left subtree by
      // moving as far right in the subtree as possible
      BinaryNode<T> leftSubtreeRoot = currentNode.getLeftChild();
      BinaryNode<T> rightChild = leftSubtreeRoot;
      BinaryNode<T> priorNode = currentNode;
      
      while (rightChild.hasRightChild())
      {
         priorNode = rightChild;
         rightChild = rightChild.getRightChild();
      } // end while
      
      // rightChild contains the inorder predecessor and is the node to
      // remove; priorNode is its parent
      
      return new NodePair(rightChild, priorNode);
   } // end getNodeToRemove

	// Removes a node having at most one child. 
   private void removeNode(BinaryNode<T> nodeToRemove, BinaryNode<T> parentNode)
   {
      BinaryNode<T> childNode;
      
      if (nodeToRemove.hasLeftChild())
      {
         childNode = nodeToRemove.getLeftChild();
      }
      else
      {
         childNode = nodeToRemove.getRightChild();
      }
      
      // Assertion: if nodeToRemove is a leaf, childNode is null
      
      if (nodeToRemove == getRootNode())
      {
         setRootNode(childNode);
      }
      else if (parentNode.getLeftChild() == nodeToRemove)
      {
         parentNode.setLeftChild(childNode);
      }
      else
      {
         parentNode.setRightChild(childNode);
      }
   } // end removeNode

   // Other public methods in SearchTreeInterface are inherited from BinaryTree.

	private class NodePair
	{
		private BinaryNode<T> first, second;
		
		public NodePair()
		{
			first = null;
			second = null;
		} // end default constructor

		public NodePair(BinaryNode<T> firstNode, BinaryNode<T> secondNode)
		{
			first = firstNode;
			second = secondNode;
		} // end constructor
		
		public BinaryNode<T> getFirst()
		{
			return first;
		} // end getFirst
		
		public BinaryNode<T> getSecond()
		{
			return second;
		} // end getSecond
	} // end NodePair
} // end BinarySearchTree_i