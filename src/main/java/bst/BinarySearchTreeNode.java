package bst;/*
 * bst.BinarySearchTreeNode.java
 * Created on Aug 12, 2018, 12:18 PM
 */

import java.util.Objects;

/**
 * @author Marshall Hampson
 */
public class BinarySearchTreeNode<T extends Comparable<T>> {
  private T value;
  private BinarySearchTreeNode<T> right;
  private BinarySearchTreeNode<T> left;

  public BinarySearchTreeNode(T value) {
    this(value, null, null);
  }
  
  public BinarySearchTreeNode(T value, BinarySearchTreeNode<T> right, BinarySearchTreeNode<T> left) {
    Objects.requireNonNull(value);
    this.value = value;
    this.right = right;
    this.left = left;
  }

  public T getValue() {
    return value;
  }

  public BinarySearchTreeNode<T> getRight() {
    return right;
  }

  public BinarySearchTreeNode<T> getLeft() {
    return left;
  }

  public void setValue(T value) {
    this.value = value;
  }

  public void setRight(BinarySearchTreeNode<T> right) {
    this.right = right;
  }

  public void setLeft(BinarySearchTreeNode<T> left) {
    this.left = left;
  }
}
