package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;


/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Stack. This results in a
 * depth-first search.
 * 
 */
public class StackBasedDepthFirstSearcher<T> extends Searcher<T> {

  /**
   * StackBasedDepthFirstSearcher.
   * @param searchProblem : search problem
   */
  public StackBasedDepthFirstSearcher(SearchProblem<T> searchProblem) {
    super(searchProblem);
  }

  @Override
  public List<T> findSolution() {
	  Stack<T> stk = new Stack<T>();
	  visited.add(searchProblem.getInitialState());
	  stk.push(searchProblem.getInitialState());
	  while(!stk.isEmpty())
	  {
		  T current = stk.peek();
		  if(searchProblem.isGoal(current))
		  {
			  return stk;
		  }
		  List<T> nlst = searchProblem.getSuccessors(current);
		  for(T elem : nlst)
		  {
			  if(!visited.contains(elem))
			  {
				  stk.push(elem);
				  visited.add(elem);
				  
			  }
		  }
		  stk.pop();
	  }
    return new ArrayList<T>();
  }
}
/*ArrayList<T> lst = new ArrayList<T>();
lst.add(searchProblem.getInitialState());
Stack<Store<T>> stack = new Stack<Store<T>>();
stack.push(new Store<T>(searchProblem.getInitialState(), lst));
int i = 0;
while(!stack.isEmpty())
{
	  Store<T> tmp = stack.pop();
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
				  stack.push(new Store<T>(successors.get(i), nlst));
				  visited.add(successors.get(i));
			  }
		  }
	  }
}
return new ArrayList<T>();*/
