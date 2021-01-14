package structures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

public class BinarySearchTree<T extends Comparable<T>> implements BSTInterface<T> {
  protected BSTNode<T> root;
  public boolean isEmpty() {
    return root == null;
  }

  public int size() {
    return subtreeSize(root);
  }

  protected int subtreeSize(BSTNode<T> node) {
    if (node == null) {
      return 0;
    } else {
      return 1 + subtreeSize(node.getLeft()) + subtreeSize(node.getRight());
    }
  }

  public boolean contains(T t) {
    while(root != null)
    {
    	int check = root.getData().compareTo(t);
    	if(check == 0)
    	{
    		return true;
    	}
    	else if(check < 0)
    	{
    		root = root.getLeft();
    	}
    	else
    		root = root.getRight();
    }
    return false;
    }
    

  /**
   * remove the data from the tree.
   */
  public boolean remove(T t) {
    if (t == null) {
      throw new NullPointerException();
    }
    boolean result = contains(t);
    if (result) {
      root = removeFromSubtree(root, t);
    }
    return result;
  }

  protected BSTNode<T> removeFromSubtree(BSTNode<T> node, T t) {
    // node must not be null
    int result = t.compareTo(node.getData());
    if (result < 0) {
      node.setLeft(removeFromSubtree(node.getLeft(), t));
      return node;
    } else if (result > 0) {
      node.setRight(removeFromSubtree(node.getRight(), t));
      return node;
    } else { // result == 0
      if (node.getLeft() == null) {
        return node.getRight();
      } else if (node.getRight() == null) {
        return node.getLeft();
      } else { // neither child is null
        T predecessorValue = getHighestValue(node.getLeft());
        node.setLeft(removeRightmost(node.getLeft()));
        node.setData(predecessorValue);
        return node;
      }
    }
  }

  private T getHighestValue(BSTNode<T> node) {
    // node must not be null
    if (node.getRight() == null) {
      return node.getData();
    } else {
      return getHighestValue(node.getRight());
    }
  }

  private BSTNode<T> removeRightmost(BSTNode<T> node) {
    // node must not be null
    if (node.getRight() == null) {
      return node.getLeft();
    } else {
      node.setRight(removeRightmost(node.getRight()));
      return node;
    }
  }

  public T get(T t) {
	  int check = t.compareTo(root.getData());
	if( root == null)
    {
    	return null;
    }
	else if(check < 0)
    {	
    	root = root.getLeft();
    	return get(t);
    }
	else if(check > 0)
    {
    	root = root.getRight();
    	return get(t);
    }
	else
	{
		return root.getData();
	}
  }


  /**
   * add data into the tree.
   */
  public void add(T t) {
    if (t == null) {
      throw new NullPointerException();
    }
    root = addToSubtree(root, new BSTNode<T>(t, null, null));
  }

  protected BSTNode<T> addToSubtree(BSTNode<T> node, BSTNode<T> toAdd) {
    if (node == null) {
      return toAdd;
    }
    int result = toAdd.getData().compareTo(node.getData());
    if (result <= 0) {
      node.setLeft(addToSubtree(node.getLeft(), toAdd));
    } else {
      node.setRight(addToSubtree(node.getRight(), toAdd));
    }
    return node;
  }
  
  private BSTNode<T> findMin(BSTNode<T> node)
  	{
	  if(node == null)
	    {
	    	return null;
	    }
	    else if(node.getLeft() == null)
	    {
	    	return node;
	    }
	    else 
	    	return findMin(node.getLeft());
  	}
  
  
  @Override
  public T getMinimum() {
	  if(root == null)
	  {
		  return null;
	  }
	  return findMin(root).getData();
  }
        	
  private BSTNode<T> findMax(BSTNode<T> node)
	{
	  if(node == null)
	    {
	    	return null;
	    }
	    else if(node.getRight() == null)
	    {
	    	return node;
	    }
	    else 
	    	return findMax(node.getRight());
	}

  @Override
  public T getMaximum() {
    if(root == null)
    {
    	return null;
    }
    return findMax(root).getData();
  }

  private int height(BSTNode<T> node)
  {
	  if( node == null)
	  {
		  return 0;
	  }
	  else
	  {
		  int lftHght = height(node.getLeft());
		  int rghtHght = height(node.getRight());
		  if(lftHght > rghtHght)
		  {
			  return lftHght+1;
		  }
		  else
		  return rghtHght+1;
	  }
  }
  @Override
  public int height() {
	  if(this.isEmpty())
		  return -1;
	  if(size() == 1)
	  {
		  return 0;
	  }
    return height(root) -1;
  }


  public Iterator<T> preorderIterator() {
    Queue<T> queue = new LinkedList<T>();
    preorderTraverse(queue,root);
    return queue.iterator();
    }
  	
  public void preorderTraverse(Queue<T> queue, BSTNode<T> node)
  {
	  if(node != null)
	  {
		  queue.add(node.getData());
		  preorderTraverse(queue, node.getLeft());
		  preorderTraverse(queue,node.getRight());
	  }
  }


  /**
   * in-order traversal.
   */
  public Iterator<T> inorderIterator() {
    Queue<T> queue = new LinkedList<T>();
    inorderTraverse(queue, root);
    return queue.iterator();
  }


  private void inorderTraverse(Queue<T> queue, BSTNode<T> node) {
    if (node != null) {
      inorderTraverse(queue, node.getLeft());
      queue.add(node.getData());
      inorderTraverse(queue, node.getRight());
    }
  }

  public Iterator<T> postorderIterator() {
    Queue<T> queue = new LinkedList<T>();
    postorderTraverse(queue,root);
    return queue.iterator();
  }
  private void postorderTraverse(Queue<T> queue, BSTNode<T> node)
  {
	  if(node !=null)
	  {
		  postorderTraverse(queue, node.getLeft());
		  postorderTraverse(queue, node.getRight());
		  queue.add(node.getData());
	  }
	  
  }

  @Override
  public boolean equals(BSTInterface<T> other) {
    return equals(this.root, other.getRoot());
  }
  private boolean equals(BSTNode<T> nodeA, BSTNode<T> nodeB)
  {
	  if(nodeA == nodeB)
		  return true;
	  else {
			if (nodeA == null || nodeB == null) 
				return false;
			 else 
			 {
				boolean left = equals(nodeA.getLeft(), nodeB.getLeft());
				boolean right = equals(nodeA.getRight(), nodeB.getRight());
				return (nodeA.getData().equals(nodeB.getData()) && left && right);
			}
  }
  }


  @Override
  public boolean sameValues(BSTInterface<T> other)
  {
    Iterator<T> iterThis = this.inorderIterator();
    Iterator<T> iterOther = other.inorderIterator();
    while(iterThis.hasNext())
    {
    	if(!iterThis.next().equals(iterOther.next()))
    	{
    		return false;
    	}
    }
    	if(iterThis.hasNext() || iterOther.hasNext())
    	{
    		return false;
    	}
    return true;
  }
  

  @Override
  public boolean isBalanced() {
    return isBalanced(root);
  }
  private boolean isBalanced(BSTNode<T> node)
  {
	  if(node == null)
	  {
		  return true;
	  }
	  else if((height(node.getLeft()) > height(node.getRight()) + 1 || height(node.getLeft()) < height(node.getRight()) - 1) )
				return false;
				else if (!isBalanced(node.getLeft()) || !isBalanced(node.getRight())) 
				return false;
				else 
				return true;
  }	
  
  @Override
  @SuppressWarnings("unchecked")

  public void balance() {
	  int cnt = 0;
	  T[] array = (T[]) new Comparable[size()];
	  Iterator<T> itertr = this.inorderIterator();
	  while(itertr.hasNext())
	  {
		  array[cnt] = itertr.next();
		  cnt++;
	  }
	  root = sortArray(array.length-1,array,0);
  }
  public BSTNode<T> sortArray(int b, T[] array,  int a) {
		if (a > b)
			{
			return null;
			}
		int mid = (a + b)/2;
		BSTNode<T> nNode = new BSTNode<T>(array[mid], sortArray(mid-1,array,a ), sortArray(b,array, mid+1));
		return nNode;
	}


  

  @Override
  public BSTNode<T> getRoot() {
    // DO NOT MODIFY
    return root;
  }

  /**
   * toDotFormat.
   * @param root root of tree.
   * @return type T.
   */
  public static <T extends Comparable<T>> String toDotFormat(BSTNode<T> root) {
    // header
    int count = 0;
    String dot = "digraph G { \n";
    dot += "graph [ordering=\"out\"]; \n";
    // iterative traversal
    Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
    queue.add(root);
    BSTNode<T> cursor;
    while (!queue.isEmpty()) {
      cursor = queue.remove();
      if (cursor.getLeft() != null) {
        // add edge from cursor to left child
        dot += cursor.getData().toString() + " -> "
            + cursor.getLeft().getData().toString() + ";\n";
        queue.add(cursor.getLeft());
      } else {
        // add dummy node
        dot += "node" + count + " [shape=point];\n";
        dot += cursor.getData().toString() + " -> " + "node" + count
            + ";\n";
        count++;
      }
      if (cursor.getRight() != null) {
        // add edge from cursor to right child
        dot += cursor.getData().toString() + " -> "
            + cursor.getRight().getData().toString() + ";\n";
        queue.add(cursor.getRight());
      } else {
        // add dummy node
        dot += "node" + count + " [shape=point];\n";
        dot += cursor.getData().toString() + " -> " + "node" + count
            + ";\n";
        count++;
      }

    }
    dot += "};";
    return dot;
  }

  /**
   * main method.
   * @param args arguments.
   */
  public static void main(String[] args) {
    for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
      BSTInterface<String> tree = new BinarySearchTree<String>();
      for (String s : new String[] { "d", "b", "a", "c", "f", "e", "g" }) {
        tree.add(s);
      }
      Iterator<String> iterator = tree.inorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();
      iterator = tree.preorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();
      iterator = tree.postorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();

      System.out.println(tree.remove(r));

      iterator = tree.inorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();
    }

    BSTInterface<String> tree = new BinarySearchTree<String>();
    for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
      tree.add(r);
    }
    System.out.println(tree.size());
    System.out.println(tree.height());
    System.out.println(tree.isBalanced());
    tree.balance();
    System.out.println(tree.size());
    System.out.println(tree.height());
    System.out.println(tree.isBalanced());
  }
}