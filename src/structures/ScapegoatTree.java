package structures;

import java.util.Iterator;

public class ScapegoatTree<T extends Comparable<T>> extends BinarySearchTree<T> {
	  private int upperBound;


	  @Override
	  public void add(T t) {
		  upperBound++;
		  BSTNode<T> nNode = new BSTNode<T>(t,null,null);
		  root = addToSubtree(root,nNode);
		  if (height() > Math.log(upperBound) / Math.log((double)3/2)) {
			  BSTNode<T> tempNode = nNode.parent;
			  BSTNode<T> childNode = nNode;
				
				while ((double)subtreeSize(childNode)/ subtreeSize(tempNode) <= (double)2/3)
				{
					childNode = childNode.parent;
					tempNode = tempNode.parent;
				}
				ScapegoatTree<T> sbtree = new ScapegoatTree<T>();
				sbtree.root = tempNode;
				BSTNode<T> original = tempNode.parent;
				sbtree.balance();
				if (original.getLeft() == tempNode) 
					original.setLeft(sbtree.root);
				else 
					original.setRight(sbtree.root);
	  }
	  }
	  @Override
	  public boolean remove(T element) {
		upperBound--;
		
		boolean result = contains(element);
		if (result) {
			root = removeFromSubtree(root, element);
		}
		
		if (this.height() > Math.log(upperBound) / Math.log(3.0/2.0)) {
			this.balance();
		}
		
		return result;
	  }
}
  