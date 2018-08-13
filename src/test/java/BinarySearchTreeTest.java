import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author Marshall Hampson
 */
public class BinarySearchTreeTest {

  @Test public void insert() {
    BinarySearchTree<Integer> btree = new BinarySearchTree<>();
    btree.insert(5);
    btree.insert(4);
    btree.insert(6);
    btree.insert(3);
    btree.insert(7);
    assertEquals(5, btree.getSize());
  }
  
  @Test public void getValue() {
    BinarySearchTree<Integer> btree = new BinarySearchTree<>();
    btree.insert(5);
    btree.insert(4);
    btree.insert(6);
    btree.insert(3);
    btree.insert(7);
    
    assertNotNull(btree.getValue(7));
  }
  
  @Test public void toListOrdered() {
    BinarySearchTree<Integer> btree = new BinarySearchTree<>();
    btree.insert(5);
    btree.insert(4);
    btree.insert(6);
    btree.insert(3);
    btree.insert(7);

    assertEquals(Arrays.asList(3, 4, 5, 6, 7), btree.toListOrdered());
  }
  
  @Test public void rebalance() {
    BinarySearchTree<Integer> btree = make1to7BTree();
    btree.rebalance();
    System.out.println(btree);
  }
  
  @Test public void toStringMethod() {
    BinarySearchTree<Integer> btree = make1to7BTree();
    System.out.println(btree);
  }
  
  private BinarySearchTree<Integer> make1to7BTree() {
    BinarySearchTree<Integer> btree = new BinarySearchTree<>();
    for (int i = 1; i <= 7; i++) {
      btree.insert(i);
    }
    return btree;
  }
}