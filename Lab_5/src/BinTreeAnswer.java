import CITS2200.BinaryTree;
import CITS2200.Iterator;
import CITS2200.OutOfBounds;
import java.util.ArrayList;

public class BinTree<E> extends BinaryTree<E> {

    /**
     * * Constructor for BinTree with no arguments that calls its superclass
     * * constructor
     */
    public BinTree() {
        super();
    }

    /**
     * * Constructor for BinTree with arguments that calls its superclass'
     * * constructor
     * *
     * * @param item the data to include in the tree's node
     * * @param ltree the left child node
     * * @param rtree the right child node
     */
    public BinTree(E item, BinaryTree<E> ltree, BinaryTree<E> rtree) {
        super(item, ltree, rtree);
    }

    /**
     * * Tests whether the tree is equal to an Object, where two trees are equal
     * * if either both trees are empty, or both trees contain equal items at the
     * * root (tested using the equals method of E), and have equal left subtrees
     * * and equal right subtrees (recursively calling the equals method of
     * * BinaryTree.
     * *
     * * @param o the tree to compare with
     * * @return true if both trees are equal
     * *
     */
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {

        if (!(this instanceof BinaryTree && o instanceof BinaryTree)) {
            return false;
        }

        BinTree<E> tree = (BinTree<E>) o;

        if (this.isEmpty() && tree.isEmpty())
            return true;

        if (this != null && tree != null) {
            return (this.getItem() == tree.getItem()
                    && this.getLeft().equals(tree.getLeft())
                    && this.getRight().equals(tree.getRight()));
        }

        return false;
    }

    /**
     * * @author Benny Ha
     * *
     * * Inner class that is the iterator for the BinTree class
     * *
     */
    private class BinTreeIterator implements Iterator<E> {

        ArrayList<E> l; // ArrayList ADT
        int index; // Index used as a window over the ArrayList l

        /**
         * * Constructor for BinTreeIterator Stores all the data in the BinTree
         * * into the ArrayList l
         */
        private BinTreeIterator() {
            l = new ArrayList<E>();
            index = 0;
            this.inOrder(BinTree.this);
        }

        /**
         * * Tests if there is a next item to return
         * *
         * * @return true if and only if there is a next item
         */
        public boolean hasNext() {
            if (index == l.size()) {
                return false;
            } else {
                return true;
            }
        }

        /**
         * * Returns the next element and moves the iterator to the next position.
         * *
         * * @return: the next element in the collection
         * * @exception OutOfBounds - if there is no next element
         * *
         */
        @SuppressWarnings("unchecked")
        public E next() throws OutOfBounds {
            if (!hasNext()) {
                throw new OutOfBounds("No more elements");
            }

            else {
                Object temp = l.get(index);

                index = index + 1;

                return (E) temp;
            }
        }

        /**
         * * Receives a BinaryTree and stores its data into the ArrayList l
         * *
         * * @param b the BinaryTree to get the data from
         */
        public void inOrder(BinaryTree<E> b) {
            if (!b.isEmpty()) {
                inOrder(b.getLeft());
                l.add(b.getItem());
                inOrder(b.getRight());
            }
        }

    }

    /**
     * * Returns an iterator that will traverse through every element in the tree,
     * * exactly once. The order isn't important. If the tree is empty, it will
     * * return an iterator for which hasNext() is false.
     * *
     * * @return an iterator to traverse every element once
     * *
     * *
     */
    public Iterator<E> iterator() {
        return new BinTreeIterator();
    }

}