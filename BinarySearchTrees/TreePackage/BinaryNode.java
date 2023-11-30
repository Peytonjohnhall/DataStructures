
package TreePackage;

/**
 * An implementation of the ADT Binary Node.
 * @author Peyton J. Hall
*/
class BinaryNode<T> 
{

    private T data;
    private BinaryNode<T> leftChild;
    private BinaryNode<T> rightChild;
    private BinaryNode<T> parent;
    private BinaryNode<T> thread;

    public BinaryNode() 
    {
        this(null); // Call next constructor
    } // end default constructor

    public BinaryNode(T dataPortion) 
    {
        this(dataPortion, null, null); // Call next constructor
    } // end constructor

    public BinaryNode(T dataPortion, BinaryNode<T> newLeftChild,
                      BinaryNode<T> newRightChild) 
    {
        // call next constructor
        this(dataPortion, newLeftChild, newRightChild, null); 
    } // end constructor

    public BinaryNode(T dataPortion, BinaryNode<T> newLeftChild,
                      BinaryNode<T> newRightChild, BinaryNode<T> newParent) 
    {
        data = dataPortion;
        leftChild = newLeftChild;
        rightChild = newRightChild;
        parent = newParent;
    } // end constructor

    public BinaryNode(T dataPortion, BinaryNode<T> left, BinaryNode<T> right,
                        BinaryNode<T> parent, BinaryNode<T> thread) 
    {
        // store the value of the parameter to the variable
        data = dataPortion;
        leftChild = left;
        rightChild = right;
        parent = parent;
        thread = thread;
    }

    public BinaryNode<T> getThread() 
    {
        return thread;
    } // end getThread

    public void setThread(BinaryNode<T> target)
    {
        thread = target;
    }

    public boolean hasThread()
    {
        return thread != null;
    }
    
    /**
     * Retrieves the data portion of this node.
     *
     * @return The object in the data portion of the node.
     */
    public T getData() 
    {
        return data;
    } // end getData

    /**
     * Sets the data portion of this node.
     *
     * @param newData The data object.
     */
    public void setData(T newData) 
    {
        data = newData;
    } // end setData

    /**
     * Retrieves the left child of this node.
     *
     * @return The node that is this node's left child.
     */
    public BinaryNode<T> getLeftChild() 
    {
        return leftChild;
    } // end getLeftChild

    /**
     * Sets this node's left child to a given node.
     *
     * @param newLeftChild A node that will be the left child.
     */
    public void setLeftChild(BinaryNode<T> newLeftChild) 
    {
        leftChild = newLeftChild;
    } // end setLeftChild

    /**
     * Detects whether this node has a left child.
     *
     * @return True if the node has a left child.
     */
    public boolean hasLeftChild() 
    {
        return leftChild != null;
    } // end hasLeftChild

    /**
     * Retrieves the right child of this node.
     *
     * @return The node that is this node's right child.
     */
    public BinaryNode<T> getRightChild() 
    {
        return rightChild;
    } // end getRightChild

    /**
     * Sets this nodes's right child to a given node.
     *
     * @param newRightChild A node that will be the right child.
     */
    public void setRightChild(BinaryNode<T> newRightChild) 
    {
        rightChild = newRightChild;
    } // end setRightChild

    /**
     * Detects whether this node has a right child.
     *
     * @return True if the node has a right child.
     */
    public boolean hasRightChild() 
    {
        return rightChild != null;
    } // end hasRightChild

    /**
     * Detects whether this node is a leaf.
     *
     * @return True if the node is a leaf.
     */
    public boolean isLeaf() 
    {
        return (leftChild == null) && (rightChild == null);
    } // end isLeaf

    /**
     * Computes the height of the subtree rooted at this node.
     *
     * @return The height of the subtree rooted at this node.
     */
    public int getHeight() 
    {
        return getHeight(this); // Call private getHeight
    } // end getHeight

    private int getHeight(BinaryNode<T> node) 
    {
        int height = 0;
        if (node != null) 
        {
            height = 1 + Math.max(getHeight(node.getLeftChild()),
                    getHeight(node.getRightChild()));
        }
        return height;
    } // end getHeight

    /**
     * Counts the nodes in the subtree rooted at this node.
     *
     * @return The number of nodes in the subtree rooted at this node.
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

    /**
     * Copies the subtree rooted at this node.
     *
     * @return The root of a copy of the subtree rooted at this node.
     */
    public BinaryNode<T> copy() 
    {
        BinaryNode<T> newRoot = new BinaryNode<T>(data);
        if (leftChild != null) 
        {
            newRoot.setLeftChild(leftChild.copy(newRoot));
            newRoot.leftChild.linkSubtreeThreadOut(newRoot);
        }
        if (rightChild != null) 
        {
            newRoot.setRightChild(rightChild.copy(newRoot));
            newRoot.setThread(newRoot.getLeftmostInSubtree());
        }
        // Since we didn't get a parent
        // our parent should be null
        newRoot.parent = null;
        
        return newRoot;
    } // end copy
    
    /**
     * Copies the subtree starting at this node.
     *
     * @param p The copied parent for the subtree copy
     * @return Node of a copy of the subtree at this node.
     */
    public BinaryNode<T> copy( BinaryNode<T> p) 
    {
        BinaryNode<T> newRoot = new BinaryNode<T>(data);
        if (leftChild != null) 
        {
            newRoot.setLeftChild(leftChild.copy(newRoot));
            newRoot.leftChild.linkSubtreeThreadOut(newRoot);

        }
        if (rightChild != null) 
        {
            newRoot.setRightChild(rightChild.copy(newRoot));
            newRoot.setThread(newRoot.getLeftmostInSubtree());

        }
        // Our parent should be the reference we received
        newRoot.parent = p;
        linkSubtreeThreadOut(newRoot);
        linkSubtreeThreadOut(newRoot.getRightChild());

        newRoot.parent = p;
        return newRoot;
    } // end copy

    
    // ADD IN ACCESSORS FOR THE PARENT REFERENCE
    public BinaryNode<T> getParent()
    {
        return parent;
    } // end getParent

    public void setParent(BinaryNode<T> p)
    {
        parent =  p;
    } // end setParent

    public boolean hasParent()
    {
        return parent != null;
    } // end hasParent

    public void linkSubtreeThreadOut(BinaryNode<T> linkTo)
    {
        BinaryNode<T> linkFrom = getRightmostInSubtree();
        linkFrom.setThread(linkTo);
    }

    public BinaryNode<T> getLeftmostInSubtree()

    {
        BinaryNode<T> toReturn = this;
        while(toReturn.hasLeftChild())
            toReturn = toReturn.getLeftChild();
        return toReturn;
    }
    public BinaryNode<T> getRightmostInSubtree()

    {
        BinaryNode<T> toReturn = this;
        while(toReturn.hasRightChild())
            toReturn = toReturn.getRightChild();
        return toReturn;
    }
} // end BinaryNode
