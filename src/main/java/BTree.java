/*
 * BTree.java
 * Created on Aug 12, 2018, 12:25 PM
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Marshall Hampson
 */
public class BTree<T extends Comparable<T>> {
  private BTreeNode<T> root = null;
  private int size = 0;
  
  public void insert(T value) {
    BTreeNode<T> newNode = new BTreeNode<>(value);
    if (root == null) {
      root = newNode;
      size++;
    }
    else {
      insertHelper(root, newNode);
    }
  }

  private void insertHelper(BTreeNode<T> currentNode, BTreeNode<T> newNode) {
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
  
  public T getValue(T value) {
    return getValueHelper(root, value);
  }
  
  private T getValueHelper(BTreeNode<T> currentNode, T value) {
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
  
  @SuppressWarnings("unchecked")
  public void rebalance() {
    List<T> orderedValues = toListOrdered();
    root = rebalanceHelper(orderedValues, 0, orderedValues.size());
  }
  
  private BTreeNode<T> rebalanceHelper(List<T> orderedValues, int startIndex, int endIndex) {
    if (endIndex <= startIndex) {
      return null;
    }
    int middle = (endIndex - startIndex) / 2 + startIndex;
    BTreeNode<T> newNode = new BTreeNode<>(orderedValues.get(middle));
    newNode.setLeft(rebalanceHelper(orderedValues, startIndex, middle));
    newNode.setRight(rebalanceHelper(orderedValues, middle + 1, endIndex));
    return newNode;
  }
  
  public List<T> toListUnordered() {
    List<T> allValues = new ArrayList<>(size);
    toListUnordered(root,allValues);
    return allValues;
  }
  
  public List<T> toListOrdered() {
    List<T> allValues = toListUnordered();
    Collections.sort(allValues);
    return allValues;
  }
  private void toListUnordered(BTreeNode<T> currentNode, List<T> list) {
    if (currentNode != null && currentNode.getValue() != null) {
      list.add(currentNode.getValue());
      toListUnordered(currentNode.getRight(), list);
      toListUnordered(currentNode.getLeft(), list);
    }
  }

  public int getSize() {
    return size;
  }
  
  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    toString(root, stringBuilder, 1);
    return Arrays.stream(stringBuilder.toString().split(System.lineSeparator()))
      .sorted()
      .collect(Collectors.joining(System.lineSeparator()));
  }
  
  private void toString(BTreeNode<T> node, StringBuilder stringBuilder, int level) {
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
