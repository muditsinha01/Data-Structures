package puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import search.SearchProblem;
import search.Solver;

/**
 * A class to represent an instance of the eight-puzzle.
 * The spaces in an 8-puzzle are indexed as follows:
 * 0 | 1 | 2
 * --+---+---
 * 3 | 4 | 5
 * --+---+---
 * 6 | 7 | 8
 * The puzzle contains the eight numbers 1-8, and an empty space.
 * If we represent the empty space as 0, then the puzzle is solved
 * when the values in the puzzle are as follows:
 * 1 | 2 | 3
 * --+---+---
 * 4 | 5 | 6
 * --+---+---
 * 7 | 8 | 0
 * That is, when the space at index 0 contains value 1, the space 
 * at index 1 contains value 2, and so on.
 * From any given state, you can swap the empty space with a space 
 * adjacent to it (that is, above, below, left, or right of it,
 * without wrapping around).
 * For example, if the empty space is at index 2, you may swap
 * it with the value at index 1 or 5, but not any other index.
 * Only half of all possible puzzle states are solvable! See:
 * https://en.wikipedia.org/wiki/15_puzzle
 * for details.
 * 

 * @author liberato
 *
 */
public class EightPuzzle implements SearchProblem<List<Integer>> {
	List<Integer> strt;
	List<Integer> values;
  /**
   * Creates a new instance of the 8 puzzle with the given starting values.
   * The values are indexed as described above, and should contain exactly the
   * nine integers from 0 to 8.
   * 
   * @param startingValues
   *            the starting values, 0 -- 8
   * @throws IllegalArgumentException
   *             if startingValues is invalid
   */
  public EightPuzzle(List<Integer> startingValues) {
	  if(startingValues.isEmpty())
	  throw new IllegalArgumentException();
	  for(int i =0; i< 8;i++)
	  {
		  if(!startingValues.contains(i))
			  throw new IllegalArgumentException();
	  }
	  if(startingValues.size() > 9)
		  throw new IllegalArgumentException();
	  values = startingValues;
	  strt = new ArrayList<Integer>(startingValues);
	  }
  
  @Override
  public List<Integer> getInitialState() {
    return strt;
  }

  @Override
  public List<List<Integer>> getSuccessors(List<Integer> currentState) {
    List<Integer> lst = new ArrayList<Integer>();
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    int index = currentState.indexOf(0);
    int temp=0;
   
    if(index == 0)
    {
    	lst.add(1);
    	lst.add(3);
    }
    if(index == 1)
    {
    	lst.add(0);
    	lst.add(2);
    	lst.add(4);
    }
    if(index == 2)
    {
    	lst.add(1);
    	lst.add(5);
    }
    if(index == 3)
    {
    	lst.add(0);
    	lst.add(4);
    	lst.add(6);
    }
    if(index == 4)
    {
    	lst.add(1);
    	lst.add(3);
    	lst.add(5);
    	lst.add(7);
    }
    if(index == 5)
    {
    	lst.add(2);
    	lst.add(4);
    	lst.add(8);
    }
    if(index == 6)
    {
    	lst.add(3);
    	lst.add(7);
    }
    if(index == 7)
    {
    	lst.add(4);
    	lst.add(6);
    	lst.add(8);
    }
    if(index == 8)
    {
    	lst.add(5);
    	lst.add(7);
    }
    for(int i =0;i < lst.size();i++)
    {	
    	ArrayList<Integer> tmpList = new ArrayList<Integer>(currentState);
    	temp = tmpList.get(index);
    	tmpList.set(index, tmpList.get(lst.get(i)));
    	tmpList.set(lst.get(i), temp);
    	result.add(tmpList);
    }
    return result;
  }


  @Override
  public boolean isGoal(List<Integer> state) {
		for (int i = 0; i < state.size() - 1; i++)
		{
			if (state.get(i) != i + 1)
				return false;
		}
		return state.get(state.size() - 1) == 0;
  }
  /**
   * supporting man method.
   */
  public static void main(String[] args) {
    EightPuzzle e = new EightPuzzle(Arrays.asList(new Integer[] { 1, 2, 3,
        4, 0, 6, 7, 5, 8 }));

    List<List<Integer>> r = new Solver<List<Integer>>(e).solveWithBFS();
    for (List<Integer> l : r) {
      System.out.println(l);
    }
  }
}
