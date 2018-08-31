package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author Marshall Hampson
 */
public class BFSShortestPath {
  private static final int EMPTY = 0;
  private static final int WALL = 1;
  private static final int X_MAX = 5;
  private static final int Y_MAX = 6;
  
  public static void main(String[] args) {
    int[][] graph = {
      {0, 0, 0, 0, 0, 1},
      {0, 0, 0, 0, 0, 1},
      {1, 1, 1, 1, 0, 1},
      {0, 0, 0, 0, 0, 1},
      {0, 0, 0, 0, 0, 1},
    };
    
    Point predecessor[][] = new Point[X_MAX][Y_MAX];
    
    Point employeeStart = new Point(1,2);
    Point bike = new Point(3, 1);
    
    boolean found = false;
    List<Point> currentPoints = new ArrayList<>();
    List<Point> nextPoints = new ArrayList<>();
    Set<Point> visitedPoints = new HashSet<>();
    nextPoints.add(employeeStart);
    while (!nextPoints.isEmpty() && !found) {
      currentPoints.clear();
      currentPoints.addAll(nextPoints);
      nextPoints.clear();
      for (Point point : currentPoints) {
        //System.out.println("Visiting " + point);
        if (bike.equals(point)) {
          System.out.println("Bike found !");
          found = true;
          break;
        }
        for (int x = point.x - 1; x <= point.x + 1; x++) {
          for (int y = point.y - 1; y <= point.y + 1; y++) {
            if (x >= 0 && x < X_MAX && y >= 0 && y < Y_MAX 
              && graph[x][y] == EMPTY) {
              Point nextPoint = new Point(x, y);
              if (!visitedPoints.contains(nextPoint)) {
                visitedPoints.add(nextPoint);
                predecessor[x][y] = point;
                nextPoints.add(new Point(x, y));
              }
            }
          }
        }
      }
    }
    
    Point next = bike;
    while (!next.equals(employeeStart)) {
      System.out.println(next);
      next = predecessor[next.x][next.y];
    }
  }
  
  public static final class Point {
    int x; 
    int y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override public boolean equals(Object o) {
      if (this == o)
        return true;
      if (o == null || getClass() != o.getClass())
        return false;
      Point point = (Point) o;
      return x == point.x && y == point.y;
    }

    @Override public int hashCode() {
      return Objects.hash(x, y);
    }

    @Override public String toString() {
      return "(" + x + ", " + y + ")";
    }
  }
}
