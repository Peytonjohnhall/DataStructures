package TreePackage;
import java.util.Iterator;
import java.util.NoSuchElementException;
import StackAndQueuePackage.*; 

/** A class that implements the ADT binary tree.
   @author Peyton J. Hall
*/
public class BinaryTree<T extends Comparable<? super T>>
                          implements BinaryTreeInterface<T>
{
   private BinaryNode<T> root;
   
   public static void printLevelOrder(BinaryTree<String> binaryTree) 
   {
      Iterator<String> levelOrder = binaryTree.getLevelOrderIterator();

      // Add your logic to print the nodes in level order
      while (levelOrder.hasNext()) 
      {
         System.out.print(levelOrder.next() + " ");
      }
      System.out.println();
   }

   /** Adds a new entry to the binary tree.
      @param newEntry The object to be added as a new entry.
      Precondition:   The binary tree may or may not be empty.
      Postcondition:  The binary tree contains the new entry.
      @return         The object that was added, or null if addition failed
   */
   public T add(T newEntry)
   {
      T result = null;

      if (isEmpty())
      {
      setRootNode(new BinaryNode<>(newEntry));
      } // end if
      else
      {
      result = addEntry(getRootNode(), newEntry);
      } // end else

      return result;
   } // end add 

   /**
      Adds a new entry to the binary tree, rooted at the given node.
      @param rootNode The root node of the binary tree.
      @param newEntry The object to be added as a new entry.
      @return         The object that was added, or null, if addition failed.
   */
   private T addEntry(BinaryNode<T> rootNode, T newEntry) 
   {
      T result = null;

      // logic to add the new entry to the binary tree
      if (newEntry.compareTo(rootNode.getData()) < 0) 
      {
         // Add to the left subtree
         if (rootNode.hasLeftChild()) 
         {
            result = addEntry(rootNode.getLeftChild(), newEntry);
         } // end if 
         else 
         {
            rootNode.setLeftChild(new BinaryNode<>(newEntry));
            result = newEntry;
         } // end else
      } // end if 
      else if (newEntry.compareTo(rootNode.getData()) > 0) 
      {
         // Add to the right subtree
         if (rootNode.hasRightChild()) 
         {
            result = addEntry(rootNode.getRightChild(), newEntry);
         } // end if
         else 
         {
            rootNode.setRightChild(new BinaryNode<>(newEntry));
            result = newEntry;
         } // end else
      } // end else if 

      return result;
   } // end addEntry

   /** Converts an array to a binary tree.
      @param end      The ending index of the portion of the array.
      @param begin    The starting index of the portion of the array.
      @param theArray The array to be converted to a binary tree.
      Precondition:   TheArray is not null.
      Postcondition:  The binary tree is constructed,
                      from the elements in theArray.
   */
   public void arrayToTree(T[] theArray, int begin, int end)
   {
      if (begin > end)
      {
      return;
      } // end if

      int midpoint = (begin + end + 1) / 2;
      add(theArray[midpoint]);
      arrayToTree(theArray, begin, midpoint - 1);
      arrayToTree(theArray, midpoint + 1, end);
   } // end arrayToTree
   

   public BinaryTree()
   {
      root = null;
   } // end default constructor

   public BinaryTree(T rootData)
   {
      root = new BinaryNode<>(rootData);
   } // end constructor

   public BinaryTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree)
   {
      initializeTree(rootData, (BinaryTree<T>)leftTree, (BinaryTree<T>)rightTree);
   } // end constructor

   public void setTree(T rootData, BinaryTreeInterface<T> leftTree,
                                   BinaryTreeInterface<T> rightTree)
   {
      initializeTree(rootData, (BinaryTree<T>)leftTree,
                               (BinaryTree<T>)rightTree);
   } // end setTree

   public void setRootData(T rootData)
   {
      root.setData(rootData);
   } // end setRootData
   
   public T getRootData()
   {
      if (isEmpty())
      {
         throw new EmptyTreeException();
      }
      else
      {
         return root.getData();
      }
   } // end getRootData
   
   public boolean isEmpty()
   {
      return root == null;
   } // end isEmpty
   
   public void clear()
   {
      root = null;
   } // end clear
   
   public int getHeight()
   {
      int height = 0;
      if (root != null)
      {
         height = root.getHeight();
      }
      return height;
   } // end getHeight
   
   public int getNumberOfNodes()
   {
      int numberOfNodes = 0;
      if (root != null)
      {
         numberOfNodes = root.getNumberOfNodes();
      }
      return numberOfNodes;
   } // end getNumberOfNodes
   
   protected void setRootNode(BinaryNode<T> rootNode)
   {
      root = rootNode;
   } // end setRootNode
   
   public BinaryNode<T> getRootNode()
   {
      return root;
   } // end getRootNode
   
   private void initializeTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree)
   {
      root = new BinaryNode<>(rootData);
     
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
            root.setRightChild(rightTree.root.copy());
         }
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
   
   public Iterator<T> getPreorderIterator()
   {
      return new PreorderIterator();
   } // end getPreorderIterator
   
   public Iterator<T> getPostorderIterator()
   {
      return new PostorderIterator();
   } // end getPostorderIterator
   
   public Iterator<T> getInorderIterator()
   {
      return new InorderIterator();
   } // end getInorderIterator
   
   public Iterator<T> getLevelOrderIterator()
   {
      return new LevelOrderIterator();
   } // end getLevelOrderIterator

   public void iterativePreorderTraverse()
   {
      StackInterface<BinaryNode<T>> nodeStack = new LinkedStack<>();
      if (root != null)
      {
         nodeStack.push(root);
      }
      BinaryNode<T> nextNode;
      while (!nodeStack.isEmpty())
      {
         nextNode = nodeStack.pop();
         BinaryNode<T> leftChild = nextNode.getLeftChild();
         BinaryNode<T> rightChild = nextNode.getRightChild();
         
         // Push into stack in reverse order of recursive calls
         if (rightChild != null)
         {
            nodeStack.push(rightChild);
         }
         
         if (leftChild != null)
         {
            nodeStack.push(leftChild);
         }
         
         System.out.print(nextNode.getData() + " ");
      } // end while
   } // end iterativePreorderTraverse

   public void iterativeInorderTraverse()
   {
      StackInterface<BinaryNode<T>> nodeStack = new LinkedStack<>();
      BinaryNode<T> currentNode = root;
     
      while (!nodeStack.isEmpty() || (currentNode != null))
      {
         // Find leftmost node with no left child
         while (currentNode != null)
         {
            nodeStack.push(currentNode);
            currentNode = currentNode.getLeftChild();
         } // end while
         
         // Visit leftmost node, then traverse its right subtree
         if (!nodeStack.isEmpty())
         {
            BinaryNode<T> nextNode = nodeStack.pop();
            // Assertion: nextNode != null, since nodeStack was not empty
            // before the pop
            System.out.print(nextNode.getData() + " ");
            currentNode = nextNode.getRightChild();
         } // end if
      } // end while
   } // end iterativeInorderTraverse

   private class PreorderIterator implements Iterator<T>
   {
      private StackInterface<BinaryNode<T>> nodeStack;
     
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
         BinaryNode<T> nextNode;
         
         if (hasNext())
         {
            nextNode = nodeStack.pop();
            BinaryNode<T> leftChild = nextNode.getLeftChild();
            BinaryNode<T> rightChild = nextNode.getRightChild();
           
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
   
   private class PostorderIterator implements Iterator<T>
   {
      private StackInterface<BinaryNode<T>> nodeStack;
      private BinaryNode<T> currentNode;
     
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
         BinaryNode<T> leftChild, rightChild, nextNode = null;
         
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
         // it wasn't empty initially since hasNext() is true.
         // But Iterator specifies an exception for next() in case
         // hasNext() is false.
         
         if (!nodeStack.isEmpty())
         {
            nextNode = nodeStack.pop();
            // nextNode != null since stack was not empty before pop
           
            BinaryNode<T> parent = null;
            if (!nodeStack.isEmpty())
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
            } // end if
            else
            {
               currentNode = null;
            } // end else
         } // end if
         else
         {
            throw new NoSuchElementException();
         } // end else
         
         return nextNode.getData();
      } // end next
 
      public void remove()
      {
         throw new UnsupportedOperationException();
      } // end remove
   } // end PostorderIterator
   
   private class InorderIterator implements Iterator<T>
   {
      private StackInterface<BinaryNode<T>> nodeStack;
      private BinaryNode<T> currentNode;
     
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
         BinaryNode<T> nextNode = null;
         
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
            // Assertion: nextNode != null, since nodeStack was not empty
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
   
   private class LevelOrderIterator implements Iterator<T>
   {
      private QueueInterface<BinaryNode<T>> nodeQueue;
     
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
         BinaryNode<T> nextNode;
         
         if (hasNext())
         {
            nextNode = nodeQueue.dequeue();
            BinaryNode<T> leftChild = nextNode.getLeftChild();
            BinaryNode<T> rightChild = nextNode.getRightChild();
           
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
} // end BinaryTree
