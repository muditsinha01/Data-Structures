package mazes;

import java.util.List;

import search.Solver;

public class MazeDriver {
  
  /**
   * Supproting main method.
   */
  public static void main(String[] args) {
    MazeGenerator mg = new MazeGenerator(24, 8, 0);
    Maze m = mg.generateDFS();
    System.out.println(m.toString());
    Solver<Cell> s = new Solver<Cell>(m);
    List<Cell> r = s.solveWithRecursiveDFS();
    for (Cell cell : r) {
      System.out.println(cell);
    }
    System.out.println(r.size());
    //  System.out.println("--------");
    //  List<Cell> d = s.solveWithIterativeDFS();
    //  for (Cell cell : d) {
    //    System.out.println(cell);
    //  }
    //  System.out.println(d.size());
    //  System.out.println("--------");
    //  List<Cell> q = s.solveWithBFS();
    //  for (Cell cell : q) {
    //    System.out.println(cell);
    //  }
    //  System.out.println(q.size());
  }
}
