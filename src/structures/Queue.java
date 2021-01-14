package structures;

import java.util.NoSuchElementException;
import stack.LinkedStack;
/**************************************************************************************
 * NOTE: before starting to code, check support/structures/UnboundedQueueInterface.java
 * for detailed explanation of each interface method, including the parameters, return
 * values, assumptions, and requirements
 ***************************************************************************************/
public class Queue<T> implements UnboundedQueueInterface<T> {
  @SuppressWarnings("hiding")
class Node<T> {
    public T data;
    public Node<T> next;
       public Node(T data) { 
      this.data = data;
    }
    
    public Node(T data, Node<T> next) {
      this.data = data; 
      this.next = next;
    }
    }
 public Node<T> front;
 public Node<T> rear;
 int cnt;
 public Node<T> str;
  
public Queue() {
	front = null;
	rear = null;
	cnt = 0;
	
  }
  public Queue(Queue<T> other) {
   if(other.front == null)
	   this.front = null;
   else
	   {
	   str = other.front;
	   while(str != null)
	   {
		   this.enqueue(str.data);
		   str = str.next;
	   }
	   }
   		
   		
  }

  @Override
  public boolean isEmpty() {
    if(front == null)
    {
    	return true;
    }
    return false;
  }

  @Override
  public int size() {
    return cnt;
  }

  @Override
  public void enqueue(T element) {
	  Node<T> node = new Node<T>(element);
	  if(element == null)
	  {
		 return ;
	  }
	  if(isEmpty())
	  {	  
		  rear = node;
		  front = rear;
	  }
	  else
	  {
		  rear.next = node;
		  rear = node;
	  }
	  this.cnt++;
  }

  @Override
  public T dequeue() throws NoSuchElementException {
    if(isEmpty()== true)
    {
    	throw new NoSuchElementException("Nothing to dequeue");
    }
    else
    {
    T ret = front.data;
    if(rear == front)
    {
    	rear =null;
    }
    front = front.next;
    this.cnt--;
    return ret;
    }
    
    }

  @Override
  public T peek() throws NoSuchElementException {
    if(isEmpty()== true)
    {
    	throw new NoSuchElementException("Nothing to peek");
    }
    else
    	return front.data;    
  }
  
  	  


  @Override
  public UnboundedQueueInterface<T> reversed() {
	  Queue<T> rvrse = new Queue<T>();
		LinkedStack<T> stack = new LinkedStack<T>();
		Node<T> curr = front;
		while(curr != null)
		{
			rvrse.enqueue(curr.data);
			curr = curr.next;
		}
		while (!rvrse.isEmpty()) {
			stack.push(rvrse.peek());
			rvrse.dequeue();
		}
		while(!stack.isEmpty())
		{
			rvrse.enqueue(stack.top());
			stack.pop();
		}
		return rvrse;

  }
}
	  

/* Queue<T> rvrse = new Queue<T>(this);   
Node<T> node1 = new Node<T>(rvrse.front.data);
node1 = rvrse.front;
 Node<T> nxtNode = new Node<T>(null);
 Node<T> prvNode = new Node<T>(null);
 while(node1 != null)
 {
	  nxtNode = node1.next;
	  node1.next = prvNode;
	  prvNode = node1;
	  node1 = nxtNode;
 }
 rvrse.front = prvNode;
 return rvrse;*/