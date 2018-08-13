import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Marshall Hampson
 */
public class BTreeTest {

  @Test public void insert() {
    BTree<Integer> btree = new BTree<>();
    btree.insert(5);
    btree.insert(4);
    btree.insert(6);
    btree.insert(3);
    btree.insert(7);
    assertEquals(5, btree.getSize());
  }
  
  @Test public void getValue() {
    BTree<Integer> btree = new BTree<>();
    btree.insert(5);
    btree.insert(4);
    btree.insert(6);
    btree.insert(3);
    btree.insert(7);
    
    assertNotNull(btree.getValue(7));
  }
  
  @Test public void toListOrdered() {
    BTree<Integer> btree = new BTree<>();
    btree.insert(5);
    btree.insert(4);
    btree.insert(6);
    btree.insert(3);
    btree.insert(7);

    assertEquals(Arrays.asList(3, 4, 5, 6, 7), btree.toListOrdered());
  }
  
  @Test public void rebalance() {
    BTree<Integer> btree = make1to7BTree();
    btree.rebalance();
    System.out.println(btree);
  }
  
  @Test public void toStringMethod() {
    BTree<Integer> btree = make1to7BTree();
    System.out.println(btree);
  }
  
  private BTree<Integer> make1to7BTree() {
    BTree<Integer> btree = new BTree<>();
    for (int i = 1; i <= 7; i++) {
      btree.insert(i);
    }
    return btree;
  }
}