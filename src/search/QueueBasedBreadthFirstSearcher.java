package search;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
//import sun.misc.Queue;
import java.util.Queue;
import java.util.HashMap;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Queue. This results in a
 * breadth-first search.
 * 
 */
public class QueueBasedBreadthFirstSearcher<T> extends Searcher<T> {

  /**
   * QueueBasedBreadthFirstSearcher.
   * @param searchProblem : search problem
   */
  public QueueBasedBreadthFirstSearcher(SearchProblem<T> searchProblem) {
    super(searchProblem);
  }
  
  public static <T> List<T> Helper(T goal, HashMap<T, T> prev)
  {
	  T current = goal;
	  List<T> lst = new ArrayList<T>();
	  lst.add(current);
	  while(prev.containsKey(current))
	  {
		  current = prev.get(current);
		  lst.add(0, current);
	  }
	  return lst;
  }
  @Override
  public List<T> findSolution()
  {	
	  HashMap<T, T> prev = new HashMap<T, T>();   
	  Queue<T> q = new ArrayDeque<T>();
	  q.add(searchProblem.getInitialState());
	  T current = q.poll();
	  visited.add(searchProblem.getInitialState());
	  while(!q.isEmpty())
	  {
		  if(searchProblem.isGoal(current))
		  {
			  return Helper(current, prev);
		  }
		   
		  List<T>  nlst = searchProblem.getSuccessors(current);
		  for(T elem: nlst)
		  {
			  if(!visited.contains(elem))
			  {
				  prev.put(elem, current);
				  visited.add(elem);
				  q.add(elem);
			  }
		  }
	  }
	     
    return new ArrayList<T>();
  }
}
/* ArrayList<T> lst = new ArrayList<T>();
lst.add(searchProblem.getInitialState());
Queue<Store<T>> queue = new Queue<Store<T>>();
queue.enqueue(new Store<T>(searchProblem.getInitialState(), lst));
int i = 0;
while(!queue.isEmpty())
{
	  Store<T> tmp = null;
	  try
	  {
		tmp = queue.dequeue();
	  }
	  catch (InterruptedException e)
	  {
		e.printStackTrace();
	  }
	  if(searchProblem.isGoal(tmp.getData()))
	  return tmp.getList();
	  else
	  {
		  List<T> successors = searchProblem.getSuccessors(tmp.getData());
		  List<T> nlst = new ArrayList<T>(tmp.getList());
		  while(i < successors.size())
		  {
			  if(!visited.contains(successors.get(i)))
			  {
				  nlst.add(successors.get(i));
				  queue.enqueue(new Store<T>(successors.get(i), nlst));
				  visited.add(successors.get(i));
			  }
		  }
	  }
}
return new ArrayList<T>();
*/