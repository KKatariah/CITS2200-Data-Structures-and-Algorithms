import CITS2200.Deque;
import CITS2200.Overflow;
import CITS2200.Underflow;

public class DequeCyclic<E> implements Deque<E> {
    E[] list;
    int right;
    int left;
    int size;

 /**
 * 
 * @param size the capacity of the queue.
 */
@SuppressWarnings("unchecked")
  public DequeCyclic(int size) {
    if (size <= 0) {
        throw new IllegalArgumentException("Cannot have a dequeue of less than size 1.");
    }
    list = (E[]) new Object[size];
    right = 0;
    left = 1;
    size = 0;
    
  }

  /**
   * @return true if deque is empty, false otherwise.
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * @return true if deque is full, false otherwise.
   */
  public boolean isFull() {
    return size == list.length;
  }

  /**
   * 
   * @param item  add item as the leftmost object in the deque
   * @throws Overflow if the deque is full.
   */
  public void pushLeft(E item) throws Overflow {
    if (isFull()){
        throw new Overflow("Deque is full");
    }
    left = ((left + list.length) - 1) % list.length;
    list[left] = item;
    size++;

  }

  /**
   * 
   * @param item add item as the rightmost object in the deque
   * @throws Overflow if the deque is full.
   */
  public void pushRight(E item) throws Overflow {
    if (isFull()){
        throw new Overflow("Deque is full");
    }
    right = ((right + list.length) + 1) % list.length;
    list[right] = item;
    size++;
  }

  /**
   * 
   * @return the leftmost element in the deque.
   * @throws Underflow if deque is empty.
   */
  public E peekLeft() throws Underflow {

    if (isEmpty()) {
        throw new Underflow("Dequeue is empty.");
    }
    return list[left];
  }

  /**
   * 
   * @return the rightmost element in the deque.
   * @throws Underflow id deque is empty.
   */
  public E peekRight() throws Underflow {
    if (isEmpty()) {
        throw new Underflow("Dequeue is empty.");
    }
    return list[right];
  }

  /**
   * 
   * @return the leftmost element in the deque.
   * @throws Underflow if deque is empty.
   */
  public E popLeft() throws Underflow {
    if (isEmpty()) {
      throw new Underflow("Cannot pop from empty deque");
    }
    E data = list[left];
    list[left] = null;
    left = ((left + list.length) + 1) % list.length;
    size--;
    return data;
    }
    

  /**
   * 
   * @return the rightmost element in the deque.
   * @throws Underflow if the deque is empty.
   */
  public E popRight() throws Underflow {
    if (isEmpty()) {
        throw new Underflow("Cannot pop from empty deque");
    }
    E data = list[right];
    list[right] = null;
    right = ((right + list.length) - 1) % list.length;
    size--;
    return data;
    }
  }
