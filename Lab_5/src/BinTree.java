import CITS2200.BinaryTree;
import CITS2200.Iterator;
import CITS2200.OutOfBounds;
import java.util.ArrayDeque;

/**
This class represents a binary tree data structure that extends the CITS2200.BinaryTree class.
It provides constructors to create a binary tree object and implements the equals() method to check if
two binary trees are equal. It also implements the iterator() method to iterate through the binary tree
using a breadth-first traversal algorithm.
*/

public class BinTree<E> extends BinaryTree<E> {

  /**
  Constructs an empty binary tree.
  */
  public BinTree() {
    super();
  }


  /**
  Constructs a binary tree with a given root item and left and right subtrees.
  @param item the item at the root of the binary tree.
  @param ltree the left subtree of the binary tree.
  @param rtree the right subtree of the binary tree.
  */
  public BinTree(E item, BinaryTree<E> ltree, BinaryTree<E> rtree) {
    super(item, ltree, rtree);
  }

  /**
  Compares this binary tree with the specified object for equality.
  @param o the object to be compared for equality with this binary tree.
  @return true if the specified object is equal to this binary tree, false otherwise.
  */
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || !(o instanceof BinaryTree<?>)) return false;

    @SuppressWarnings("unchecked")
    BinaryTree<E> that = (BinaryTree<E>) o;
    
    if (!this.isEmpty() && !that.isEmpty()){
        return (this.getItem().equals(that.getItem())) && (this.getLeft().equals(that.getLeft())) && (this.getRight().equals(that.getRight()));
    }
    if (this.isEmpty() && that.isEmpty()) return true;
    return false;
  }

  /**
  Returns an iterator to traverse the binary tree in level order.
  @return an iterator to traverse the binary tree in level order.
  */
  public Iterator<E> iterator() {
    return new BinTreeIterator<>(this);
  }


  /**
  This inner class represents an iterator to traverse the binary tree in level order.
  It implements the Iterator interface and provides two methods, hasNext() and next().
  */
  public class BinTreeIterator<E> implements Iterator<E> {

    private ArrayDeque<BinaryTree<E>> queue = new ArrayDeque<>();
    private ArrayDeque<BinaryTree<E>> dataList = new ArrayDeque<>();

    /**
    * Constructs an iterator with the given binary tree as the root node.
    * @param root the root node of the binary tree.
    */
    public BinTreeIterator(BinaryTree<E> root) {
      buildQueue(root);
      
    }


    /**
    * Builds a queue to traverse the binary tree in level order.
    * @param root the root node of the binary tree.
    */
    private void buildQueue(BinaryTree<E> root) {
      if (root.isEmpty()) {
        return;
      }
      queue.offer(root);
      while (!queue.isEmpty()) {
        BinaryTree<E> node = queue.peek();
        if (!node.getLeft().isEmpty() ) {
          queue.offer(node.getLeft());
        }
        if (!node.getRight().isEmpty()) {
          queue.offer(node.getRight());
        }
        dataList.add(node);
        queue.pop();
      }
    }


    /**
    * Returns true if there are more nodes to traverse, false otherwise.
    * @return true if there are more nodes to traverse, false otherwise.
    */
    public boolean hasNext() {
      return !dataList.isEmpty();
    }

    
    /**
    * Returns the next item in the traversal of the binary tree.
    * @return the next item in the traversal of the binary tree.
    * @throws OutOfBounds if there are no more nodes to traverse.
    */
    public E next() {
      if (!hasNext()) {
        throw new OutOfBounds("No more nodes to traverse");
      }
      BinaryTree<E> node = dataList.poll();
      return node.getItem();
    }
  }
}