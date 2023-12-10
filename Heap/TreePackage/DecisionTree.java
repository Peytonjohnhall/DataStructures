package TreePackage;

/**
   A class that implements a decision tree by extending BinaryTree.
*/
public class DecisionTree<T> extends BinaryTree<T> implements DecisionTreeInterface<T>
{
	private BinaryNode<T> currentNode; // Tracks where we are in the tree
	
   public DecisionTree()
   {
      super();
      resetCurrentNode();
   } // end default constructor
   
   public DecisionTree(T rootData)
   {
      super(rootData);
      resetCurrentNode();
   } // end constructor
   
   public DecisionTree(T rootData, DecisionTree<T> leftTree,
                                   DecisionTree<T> rightTree)
   {
      setTree(rootData, leftTree, rightTree);
      resetCurrentNode();
   } // end constructor
   
   public DecisionTree(T rootData, T responseForNo, T responseForYes)
   {
      DecisionTree<T> leftTree = new DecisionTree<>(responseForNo);
      DecisionTree<T> rightTree = new DecisionTree<>(responseForYes);
      setTree(rootData, leftTree, rightTree);
      resetCurrentNode();
   } // end constructor
   
	public T getCurrentData()
	{
		if (currentNode != null)
		{
		 	return currentNode.getData();
		}
		else
		{
		 	return null;
		}
	} // end getCurrentData
	
	public void setCurrentData(T newData)
	{
		if (currentNode != null)
		{
         currentNode.setData(newData);
		}
      else
      {
         throw new NullPointerException();
      }
	} // end setCurrentData
	
   public void setResponses(T responseForNo, T responseForYes)
	{
      if (currentNode == null)
      {
         throw new NullPointerException();
      }
		else if (currentNode.hasLeftChild())
		{
			BinaryNode<T> leftChild = currentNode.getLeftChild();
			leftChild.setData(responseForNo);
		}
		else
		{
			BinaryNode<T> newLeftChild = new BinaryNode<>(responseForNo);
			currentNode.setLeftChild(newLeftChild);
		} // end if

		if (currentNode.hasRightChild())
		{
			BinaryNode<T> rightChild = currentNode.getRightChild();
			rightChild.setData(responseForYes);
		}
		else
		{
			BinaryNode<T> newRightChild = new BinaryNode<>(responseForYes);
			currentNode.setRightChild(newRightChild);
		} // end if
	} // end setResponses

	public boolean isAnswer()
	{
		if (currentNode != null)
		{
			return currentNode.isLeaf();
		}
		else
		{
		 	return false;
		}
	} // end isAnswer

	public void advanceToNo()
	{
      if (currentNode == null)
      {
         throw new NullPointerException();
      }
      else
      {
		   currentNode = currentNode.getLeftChild();
      }
	} // end advanceToNo
	
	public void advanceToYes()
	{
      if (currentNode == null)
      {
         throw new NullPointerException();
      }
      else
      {
		   currentNode = currentNode.getRightChild();
      }
	} // end advanceToYes
	
	public void resetCurrentNode()
	{
		currentNode = getRootNode();
	} // end resetCurrentNode
	
	protected BinaryNode<T> getCurrentNode()
	{
		return currentNode;
	} // end getCurrentNode
} // end DecisionTree