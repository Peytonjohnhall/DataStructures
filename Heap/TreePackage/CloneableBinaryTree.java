package TreePackage;
import java.util.Iterator;
import java.util.NoSuchElementException;
import TreePackage.*;
import StackAndQueuePackage.*;
import java.util.EmptyStackException;

/**
   A class that implements the ADT binary tree (clonable version).
*/
public class CloneableBinaryTree<T> implements Cloneable
{
   private CloneableBinaryNode<T> root;

   public CloneableBinaryTree()
   {
      root = null;
   } // end default constructor

   public CloneableBinaryTree(T rootData)
   {
      root = new CloneableBinaryNode<>(rootData);
   } // end constructor

   public CloneableBinaryTree(T rootData, CloneableBinaryTree<T> leftTree,
                                          CloneableBinaryTree<T> rightTree)
   {
      initializeTree(rootData, leftTree, rightTree);
   } // end constructor

   public void setTree(T rootData, CloneableBinaryTree<T> leftTree,
                                   CloneableBinaryTree<T> rightTree)
   {
      initializeTree(rootData, leftTree, rightTree);
   } // end setTree
   
	public T getRootData()
	{
      T rootData = null;

      if (root != null)
      {
         rootData = root.getData();
      }
       
      return rootData;
	} // end getRootData

	public boolean isEmpty()
	{
      return root == null;
	} // end isEmpty

	public void clear()
	{
      root = null;
	} // end clear

//	protected void setRootData(T rootData)
	public void setRootData(T rootData)
	{
      root.setData(rootData);
	} // end setRootData

	public int getHeight()
	{
      return root.getHeight();
	} // end getHeight

	public int getNumberOfNodes()
	{
      return root.getNumberOfNodes();
	} // end getNumberOfNodes

   /** Makes a clone of this tree.
      @return The clone of this tree. 
   */
   public Object clone()
   {
      CloneableBinaryTree<T> theCopy = new CloneableBinaryTree<>();
      @SuppressWarnings("unchecked")
      CloneableBinaryNode<T> temp = (CloneableBinaryNode<T>)root.clone();
      theCopy.root = temp;
      return theCopy;
   } // end clone
   
   public Iterator<T> getPreorderIterator()
   {
      return new PreorderIterator();
   } // end getPreorderIterator
   
   public Iterator<T> getInorderIterator()
   {
      return new InorderIterator();
   } // end getInorderIterator
   
   public Iterator<T> getPostorderIterator()
   {
      return new PostorderIterator();
   } // end getPostorderIterator
   
   public Iterator<T> getLevelOrderIterator()
   {
      return new LevelOrderIterator();
   } // end getLevelOrderIterator
   
   protected void setRootNode(CloneableBinaryNode<T> rootNode)
   {
      root = rootNode;
   } // end setRootNode
   
   protected CloneableBinaryNode<T> getRootNode()
   {
      return root;
   } // end getRootNode

   private void initializeTree(T rootData, CloneableBinaryTree<T> leftTree,
                                           CloneableBinaryTree<T> rightTree)
   {
      root = new CloneableBinaryNode<>(rootData);
      
      if ((leftTree != null) && !leftTree.isEmpty())
      {
         root.setLeftChild(leftTree.root);
      }
      
      if ((rightTree != null) && !rightTree.isEmpty())
      {
         if (rightTree != leftTree)
         {
            root.setRightChild(rightTree.root);
         }
         else
         {
            // The cast is safe because the clone of the root is a node
            @SuppressWarnings("unchecked")
            CloneableBinaryNode<T> rootClone = (CloneableBinaryNode<T>)(rightTree.root.clone());
            root.setRightChild(rootClone);
         } // end if
      } // end if
      
      if ((leftTree != null) && (leftTree != this))
      {
         leftTree.clear();
      }
      
      if ((rightTree != null) && (rightTree != this))
      {
         rightTree.clear();
      }
   } // end initializeTree

	private class PreorderIterator implements Iterator<T>
	{
		private StackInterface<CloneableBinaryNode<T>> nodeStack;
		
		public PreorderIterator()
		{
			nodeStack = new LinkedStack<>();
			if (root != null)
			{
				nodeStack.push(root);
			}
		} // end default constructor
		
		public boolean hasNext() 
		{
			return !nodeStack.isEmpty();
		} // end hasNext
		
		public T next()
		{
			CloneableBinaryNode<T> nextNode;
			
			if (hasNext())
			{
				nextNode = nodeStack.pop();
				CloneableBinaryNode<T> leftChild = nextNode.getLeftChild();
				CloneableBinaryNode<T> rightChild = nextNode.getRightChild();
				
				// Push into stack in reverse order of recursive calls
				if (rightChild != null)
				{
					nodeStack.push(rightChild);
				}
					
				if (leftChild != null)
				{
					nodeStack.push(leftChild);
				}
			}
			else
			{
				throw new NoSuchElementException();
			}
		
			return nextNode.getData();
		} // end next
	
		public void remove()
		{
			throw new UnsupportedOperationException();
		} // end remove
	} // end PreorderIterator

	private class InorderIterator implements Iterator<T>
	{
      private StackInterface<CloneableBinaryNode<T>> nodeStack;
      private CloneableBinaryNode<T> currentNode;

      public InorderIterator()
      {
         nodeStack = new LinkedStack<>();
         currentNode = root;
      } // end default constructor

      public boolean hasNext() 
      {
         return !nodeStack.isEmpty() || (currentNode != null);
      } // end hasNext

      public T next()
      {
         CloneableBinaryNode<T> nextNode = null;

         // Find leftmost node with no left child
         while (currentNode != null)
         {
            nodeStack.push(currentNode);
            currentNode = currentNode.getLeftChild();
         } // end while

         // Get leftmost node, then move to its right subtree
         if (!nodeStack.isEmpty())
         {
            nextNode = nodeStack.pop();
            assert nextNode != null; // Since nodeStack was not empty
                                     // before the pop
            currentNode = nextNode.getRightChild();
         }
         else
         {
            throw new NoSuchElementException();
         }

         return nextNode.getData(); 
      } // end next

      public void remove()
      {
         throw new UnsupportedOperationException();
      } // end remove
	} // end InorderIterator

	private class PostorderIterator implements Iterator<T>
	{
		private StackInterface<CloneableBinaryNode<T>> nodeStack;
		private CloneableBinaryNode<T> currentNode;
		
		public PostorderIterator()
		{
			nodeStack = new LinkedStack<>();
			currentNode = root;
		} // end default constructor
		
		public boolean hasNext()
		{
			return !nodeStack.isEmpty() || (currentNode != null);
		} // end hasNext
		
		public T next()
		{
			CloneableBinaryNode<T> leftChild, rightChild, nextNode = null;
			
			// Find leftmost leaf
			while (currentNode != null)
			{
				nodeStack.push(currentNode);
				leftChild = currentNode.getLeftChild();
				if (leftChild == null)
				{
					currentNode = currentNode.getRightChild();
				}
				else
				{
					currentNode = leftChild;
				}
			} // end while
			
			// Stack is not empty either because we just pushed a node, or
			// it wasn't empty to begin with since hasNext() is true.
			// But Iterator specifies an exception for next() in case
			// hasNext() is false.
			
			if (!nodeStack.isEmpty())
			{
				nextNode = nodeStack.pop();
				// nextNode != null since stack was not empty before pop
            
            CloneableBinaryNode<T> parent = null;
            try
            {
               parent = nodeStack.peek();
               if (nextNode == parent.getLeftChild())
               {
                  currentNode = parent.getRightChild();
               }
               else
               {
                  currentNode = null;
               }
            }
            catch(EmptyStackException e)
            {
               currentNode = null;
            }
			}
			else
			{
				throw new NoSuchElementException();
			} // end if
			
			return nextNode.getData();
		} // end next
      
		public void remove()
		{
			throw new UnsupportedOperationException();
		} // end remove
	} // end PostorderIterator
	
	private class LevelOrderIterator implements Iterator<T>
	{
		private QueueInterface<CloneableBinaryNode<T>> nodeQueue;
		
		public LevelOrderIterator()
		{
			nodeQueue = new LinkedQueue<>();
			if (root != null)
			{
				nodeQueue.enqueue(root);
			}
		} // end default constructor
		
		public boolean hasNext() 
		{
			return !nodeQueue.isEmpty();
		} // end hasNext
		
		public T next()
		{
			CloneableBinaryNode<T> nextNode;
			
			if (hasNext())
			{
				nextNode = nodeQueue.dequeue();
				CloneableBinaryNode<T> leftChild = nextNode.getLeftChild();
				CloneableBinaryNode<T> rightChild = nextNode.getRightChild();
				
				// Add to queue in order of recursive calls
				if (leftChild != null)
				{
					nodeQueue.enqueue(leftChild);
				}

				if (rightChild != null)
				{
					nodeQueue.enqueue(rightChild);
				}
			}
			else
			{
				throw new NoSuchElementException();
			}
		
			return nextNode.getData();
		} // end next
	
		public void remove()
		{
			throw new UnsupportedOperationException();
		} // end remove
	} // end LevelOrderIterator
} // end CloneableBinaryTree