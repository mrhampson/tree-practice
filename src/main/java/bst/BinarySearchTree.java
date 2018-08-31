package bst;/*
 * bst.BinarySearchTree.java
 * Created on Aug 12, 2018, 12:25 PM
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Marshall Hampson
 */
public class BinarySearchTree<T extends Comparable<T>> {
  private BinarySearchTreeNode<T> root = null;
  private int size = 0;

  /**
   * Adds a value into the tree
   * @param value the value
   */
  public void insert(T value) {
    Objects.requireNonNull(value);
    BinarySearchTreeNode<T> newNode = new BinarySearchTreeNode<>(value);
    if (root == null) {
      root = newNode;
      size++;
    }
    else {
      insertHelper(root, newNode);
    }
  }

  /**
   * Recursive function to insert a new node
   * @param currentNode the current node
   * @param newNode the new node to insert
   */
  private void insertHelper(BinarySearchTreeNode<T> currentNode, BinarySearchTreeNode<T> newNode) {
    boolean isLessThanOrEqualToCurrent = newNode.getValue().compareTo(currentNode.getValue()) <= 0;
    if (isLessThanOrEqualToCurrent) {
      if (currentNode.getLeft() != null) {
        insertHelper(currentNode.getLeft(), newNode);
      }
      else {
        currentNode.setLeft(newNode);
        size++;
      }
    }
    else {
      if (currentNode.getRight() != null) {
        insertHelper(currentNode.getRight(), newNode);
      }
      else {
        currentNode.setRight(newNode);
        size++;
      }
    }
  }

  /**
   * Gets the value if it exists in the tree 
   * @param value the value to find
   * @return the value if it exists or null
   */
  public T getValue(T value) {
    return getValueHelper(root, value);
  }

  /**
   * Recursive function to get the value from the tree
   * @param currentNode the current node 
   * @param value the value to find
   * @return the value if it exists or null
   */
  private T getValueHelper(BinarySearchTreeNode<T> currentNode, T value) {
    boolean isLessThanOrEqualToCurrent = value.compareTo(currentNode.getValue()) <= 0;
    if (isLessThanOrEqualToCurrent) {
      if (currentNode.getLeft() != null) {
        return getValueHelper(currentNode.getLeft(), value);
      }
      else {
        return currentNode.getValue().equals(value) ? currentNode.getValue() : null;
      }
    }
    else {
      if (currentNode.getRight() != null) {
        return getValueHelper(currentNode.getRight(), value);
      }
      else {
        return currentNode.getValue().equals(value) ? currentNode.getValue() : null;
      }
    }
  }

  /**
   * Rebalances the treee
   */
  public void rebalance() {
    List<T> orderedValues = toListOrdered();
    root = rebalanceHelper(orderedValues, 0, orderedValues.size());
  }

  /**
   * Recursive function that rebalances the tree by reading all its values to an array,
   * sorting it and then recursively building the tree 
   * @param orderedValues the ordered values of the existing tree
   * @param startIndex the starting index in the ordered values array
   * @param endIndex the ending index in the ordered values array
   * @return
   */
  private BinarySearchTreeNode<T> rebalanceHelper(List<T> orderedValues, int startIndex, int endIndex) {
    if (endIndex <= startIndex) {
      return null;
    }
    int middle = (endIndex - startIndex) / 2 + startIndex;
    BinarySearchTreeNode<T> newNode = new BinarySearchTreeNode<>(orderedValues.get(middle));
    newNode.setLeft(rebalanceHelper(orderedValues, startIndex, middle));
    newNode.setRight(rebalanceHelper(orderedValues, middle + 1, endIndex));
    return newNode;
  }

  /**
   * Gets all values in an unordered list
   * @return a list of all values in the tree
   */
  public List<T> toListUnordered() {
    List<T> allValues = new ArrayList<>(size);
    toListUnordered(root,allValues);
    return allValues;
  }

  /**
   * Gets all values in an ordered list
   * @return a list of all values in the tree sorted
   */
  public List<T> toListOrdered() {
    List<T> allValues = toListUnordered();
    Collections.sort(allValues);
    return allValues;
  }

  /**
   * Recursive function to read all the values into a list
   * @param currentNode the current node to add to the list 
   * @param list the list to write the values into 
   */
  private void toListUnordered(BinarySearchTreeNode<T> currentNode, List<T> list) {
    if (currentNode != null && currentNode.getValue() != null) {
      list.add(currentNode.getValue());
      toListUnordered(currentNode.getRight(), list);
      toListUnordered(currentNode.getLeft(), list);
    }
  }

  /**
   * The number of nodes in this tree
   * @return the number of nodes in this tree
   */
  public int getSize() {
    return size;
  }
  
  /** {@inheritDoc} */
  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    toString(root, stringBuilder, 1);
    return Arrays.stream(stringBuilder.toString().split(System.lineSeparator()))
      .sorted()
      .collect(Collectors.joining(System.lineSeparator()));
  }

  /**
   * Recursive function to write the values of the tree as a string
   * @param node the current node to print
   * @param stringBuilder the string builder to print into
   * @param level the current level of the node
   */
  private void toString(BinarySearchTreeNode<T> node, StringBuilder stringBuilder, int level) {
    stringBuilder.append("level: ").append(level);
    stringBuilder.append(" value: ").append(node.getValue()).append(" ");
    stringBuilder.append(", leftVal: ").append(node.getLeft() != null ? node.getLeft().getValue() : "null");
    stringBuilder.append(", rightVal: ").append(node.getRight() != null ? node.getRight().getValue() : "null");
    stringBuilder.append(System.lineSeparator());
    if (node.getLeft() != null) {
      toString(node.getLeft(), stringBuilder, ++level);
      --level;
    }
    if (node.getRight() != null) {
      toString(node.getRight(), stringBuilder, ++level);
      --level;
    }
  }
}
