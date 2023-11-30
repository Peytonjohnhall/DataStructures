package TreePackage;



/**
 * An implementation of the ADT Binary Tree.
 */
import java.util.*;
       
public class BinaryTree<T> implements BinaryWithParentsTreeAccessInterface<T> 
{
    private BinaryNode<T> root;

    public BinaryTree() 
    {
        root = null;      
        resetAccess();
    } // end default constructor

    public BinaryTree(T rootData) 
    {
        root = new BinaryNode<T>(rootData);       
        resetAccess();
    } // end constructor

    public BinaryTree(T rootData, BinaryTree<T> leftTree,
                      BinaryTree<T> rightTree) 
    {
        privateSetTree(rootData, leftTree, rightTree);
    } // end constructor

    public void setTree(T rootData) 
    {
        root = new BinaryNode<T>(rootData);        
        resetAccess();
    } // end setTree

    public void setTree(T rootData, BinaryTreeInterface<T> leftTree,
                        BinaryTreeInterface<T> rightTree) 
    {
        privateSetTree(rootData, (BinaryTree<T>) leftTree, 
                       (BinaryTree<T>) rightTree);
    } // end setTree

    private void privateSetTree(T rootData, BinaryTree<T> leftTree, 
                                BinaryTree<T> rightTree) 
    {
        root = new BinaryNode<T>(rootData);

        if ((leftTree != null) && !leftTree.isEmpty()) 
        {
            root.setLeftChild(leftTree.root);
            root.getLeftChild().linkSubtreeThreadOut(root);
            // set the parent of the left child
            root.getLeftChild().setParent(root);
        }

        if ((rightTree != null) && !rightTree.isEmpty()) 
        {
            if (rightTree != leftTree) 
            {
                root.setRightChild(rightTree.root);
                root.setThread(root.getRightChild().getLeftmostInSubtree());
            } 
            else 
            {
                root.setRightChild(rightTree.root.copy());
                root.setThread(root.getRightChild().getLeftmostInSubtree());
            }
            // set the parent of the right child
            root.getRightChild().setParent(root);
        } // end if

        if ((leftTree != null) && (this != leftTree)) 
        {
            leftTree.clear();
        }

        if ((rightTree != null) && (this != rightTree)) 
        {
            rightTree.clear();
        }        
        resetAccess();
    } // end privateSetTree

    public T getRootData() 
    {
        if (isEmpty()) 
        {
            throw new EmptyTreeException("Empty tree for operation getRootData");
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
        resetAccess();
    } // end clear

    protected void setRootData(T rootData) 
    {
        root.setData(rootData);
    } // end setRootData

    protected void setRootNode(BinaryNode<T> rootNode) 
    {
        root = rootNode;
    } // end setRootNode

    protected BinaryNode<T> getRootNode() 
    {
        return root;
    } // end getRootNode

    public int getHeight() 
    {
        if (root == null) 
        {
            return 0;
        } 
        else 
        {
            return root.getHeight();
        }
    } // end getHeight

    public int getNumberOfNodes() 
    {
        if (root == null) 
        {
            return 0;
        } 
        else 
        {
            return root.getNumberOfNodes();
        }
    } // end getNumberOfNodes

    public void inorderTraverse() 
    {
        inorderTraverse(root);
    } // end inorderTraverse

    private void inorderTraverse(BinaryNode<T> node) 
    {
        if (node != null) 
        {
            inorderTraverse(node.getLeftChild());
            System.out.println(node.getData());
            inorderTraverse(node.getRightChild());
        } // end if
    } // end inorderTraverse
    
    private class InorderIterator implements Iterator<T> 
    {
        private BinaryNode<T> currentNode;

        public InorderIterator() 
        {
            currentNode = root;
            if(currentNode != null)
            {
                currentNode = currentNode.getLeftmostInSubtree();
            }
        } // end default constructor

        public boolean hasNext() 
        {
            return (currentNode != null);
        } // end hasNext

        public T next() 
        {
            BinaryNode<T> gotNode = currentNode;

            // Find leftmost node with no left child
            // Get leftmost node, then move to its right subtree
            if (currentNode != null) 
            {
                // before the pop
                currentNode = currentNode.getThread();
            } 
            else 
            {
                throw new NoSuchElementException();
            } 

            return gotNode.getData();
        } // end next

        public void remove() 
        {
            throw new UnsupportedOperationException();
        } // end remove

    } // end InorderIterator

    /* 
        Create an inorder iterator.
        @return The iterator.
    */
    public Iterator<T> getInorderIterator() 
    {
        return new InorderIterator();
    }

       
    private class PostorderIterator implements Iterator<T> 
    {

        Stack<BinaryNode<T>> nodeStack;
        private BinaryNode<T> currentNode;

        public PostorderIterator() 
        {
            nodeStack = new Stack<BinaryNode<T>>();
            currentNode =  root;
            if (root != null) 
            {
                goDeep();
            }
        } // end default constructor

        public boolean hasNext() 
        {
            return (currentNode != null);
        } // end hasNext

        public T next() 
        {

            BinaryNode<T> thisNode = currentNode;

            if (currentNode != null) 
            {
                if (!currentNode.hasParent()) 
                {
                    currentNode = null; // at the root, so we are done
                } 
                else 
                {
                    currentNode =  currentNode.getParent();
                    if (cameFromLeft(currentNode, thisNode)) 
                    {
                        // go right and deep if we can
                        if (currentNode.hasRightChild()) 
                        {
                            currentNode = currentNode.getRightChild();
                            goDeep();
                        }
                    }
                }
            } 
            else 
            {
                throw new NoSuchElementException();
            }

            return thisNode.getData();

        } // end next

        private void goDeep() 
        {
            while (currentNode.hasLeftChild() || currentNode.hasRightChild()) 
            {
                if (currentNode.hasLeftChild()) 
                {
                    currentNode = currentNode.getLeftChild();
                } 
                else 
                {
                    currentNode = currentNode.getRightChild();
                }
            }
        }

        /**
            find the relation between the parent and the child
        */
        private boolean cameFromLeft(BinaryNode<T> parent, BinaryNode<T> child) 
        {                
            return (parent.hasLeftChild() && child == parent.getLeftChild());
        }

        /**
            find the relation between the parent and the child
        */
        private boolean cameFromRight(BinaryNode<T> parent, BinaryNode<T> child) 
        {
            return (parent.hasRightChild() && child == parent.getRightChild());
        }

        public void remove() 
        {
            throw new UnsupportedOperationException();
        } // end remove

    } // end PostorderIterator

    
              
    /**
        Only inorder and postorder iterators are supported by this code
    */
    public Iterator<T> getPreorderIterator() 
    {
        throw new RuntimeException(
                        "Pre order iterators not yet supported by this class");
    }

    public Iterator<T> getPostorderIterator() 
    {      
        return new PostorderIterator();
    }

    public Iterator<T> getLevelOrderIterator() 
    {
        throw new RuntimeException(
                        "Level order iterators not yet supported by this class");
    }
          
    BinaryNode<T> current = null;

    /**
     * Gets the data in the current node.
     *
     * @return The data object in the current node
     */
    public T getCurrentData() 
    {
        T result = null;

        if (current != null) 
        {
            result = current.getData();
        }
        return result;
    }

    /**
     * Determines if the current node has a left child.
     *
     * @return True if the left child is non-null.
     *
     */
    public boolean canAdvanceToLeft() 
    {
        return (current != null && current.hasLeftChild());
    }

    /**
     * Moves the current node to the left child of the current node.
     */
    public void advanceToLeft() 
    {
        if (current != null) 
        {
            current = current.getLeftChild();
        }
    }

    /**
     * Determines if the current node has a right child.
     *
     * @return True if the right child is non-null.
     *
     */
    public boolean canAdvanceToRight() 
    {
        return (current != null && current.hasRightChild());
    }

    /**
     * Moves the current node to the right child of the current node.
     */
    public void advanceToRight() 
    {
        if (current != null) 
        {
            current = current.getRightChild();
        }
    }

    /**
     * Sets the current node to the root of the tree.
     */
    public final void resetAccess() 
    {
        current = root;
    }

    /**
     * Task: Determines if the current node has a parent.
     *
     * @return True if the parent is non-null.
     *
     */
    public boolean canGoBackToParent() 
    {
        return (current != null && current.hasParent());
    }

    /**
     * Task: Moves the current node to the parent of the current node.
     */
    public void goBackToParent() 
    {
        if (current != null) 
        {
            current = current.getParent();
        }
    }
}
