import CITS2200.IllegalValue;
import CITS2200.Iterator;
import CITS2200.OutOfBounds;
import CITS2200.PriorityQueue;
import CITS2200.Underflow;

public class PriorityQueueLinked implements PriorityQueue<Object> {
  private Link front;

  public PriorityQueueLinked() {
    front = null;
  }

  private class Link {
    public Object element;
    public int priority;
    public Link next;

    public Link(Object element, int priority, Link next) {
      this.element = element;
      this.priority = priority;
      this.next = next;
    }
  }

  public boolean isEmpty() {
    return front == null;
  }

  public Object examine() throws Underflow {
    if (isEmpty()) {
      throw new Underflow("Empty priority queue");
    }
    return front.element;
  }

  public Object dequeue() throws Underflow {
    if (isEmpty()) {
      throw new Underflow("Empty priority queue");
    }

    Object temp = front.element;
    front = front.next;
    return temp;
  }

  public void enqueue(Object element, int priority) {
    if (isEmpty() || priority > front.priority) {
      front = new Link(element, priority, front);
      return;
    }

    Link curr = front;
    while (curr.next != null && curr.next.priority >= priority) {
      curr = curr.next;
    }
    curr.next = new Link(element, priority, curr.next);
  }

  private class PriorityQueueIterator implements Iterator<Object> {
    private Link current;

    /**
     * * Constructor for PriorityQueueIterator
     */
    public PriorityQueueIterator() {
      current = front;
    }

    /**
     * * Tests if there is a next item to return
     *
     * @return true if and only if there is a next item
     */
    public boolean hasNext() {
      return current != null;
    }

    /**
     * * Returns the next element and moves the iterator to the next position.
     *
     * @return the next element in the collection
     * @exception OutOfBounds - if there is no next element
     */
    public Object next() throws OutOfBounds {
      if (!hasNext()) {
        throw new OutOfBounds("No more elements");
      }

      Object temp = current.element;

      current = current.next;

      return temp;
    }
  }

  /**
   * * Returns an iterator that will traverse through every element in the queue,
   * * exactly once. If the queue is empty, it will return an iterator for which
   * * hasNext() is false.
   *
   * @return an iterator to traverse every element once
   */
  public Iterator<Object> iterator() {
    return new PriorityQueueIterator();
  }
}
