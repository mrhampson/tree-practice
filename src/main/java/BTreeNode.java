/*
 * BTreeNode.java
 * Created on Aug 12, 2018, 12:18 PM
 */

import java.util.Objects;

/**
 * @author Marshall Hampson
 */
public class BTreeNode<T extends Comparable<T>> {
  private T value;
  private BTreeNode<T> right;
  private BTreeNode<T> left;

  public BTreeNode(T value) {
    this(value, null, null);
  }
  
  public BTreeNode(T value, BTreeNode<T> right, BTreeNode<T> left) {
    this.value = value;
    this.right = right;
    this.left = left;
  }

  public T getValue() {
    return value;
  }

  public BTreeNode<T> getRight() {
    return right;
  }

  public BTreeNode<T> getLeft() {
    return left;
  }

  public void setValue(T value) {
    this.value = value;
  }

  public void setRight(BTreeNode<T> right) {
    this.right = right;
  }

  public void setLeft(BTreeNode<T> left) {
    this.left = left;
  }

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    BTreeNode<?> bTreeNode = (BTreeNode<?>) o;
    return Objects.equals(value, bTreeNode.value) && Objects.equals(right, bTreeNode.right) && Objects
      .equals(left, bTreeNode.left);
  }

  @Override public int hashCode() {
    return Objects.hash(value, right, left);
  }
}
