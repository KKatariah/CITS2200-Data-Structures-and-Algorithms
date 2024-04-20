import CITS2200.Stack;
import CITS2200.Link;
import CITS2200.Overflow;
import CITS2200.Underflow;

public class StackBlock implements Stack {
  private Object[] myStack;
  private int last;

  /*
   * create an empty stack of size s
   */
  public StackBlock(int size) {
    myStack = new Object[size];
    last = -1;
    
  }

  /**
   * @return true if stack is empty, false otherwise
   */
  public boolean isEmpty() {
    return last == -1 ;
  }

  /*
   * @return true if stack is full, false otherwise
   */
  public boolean isFull() {
    return last == myStack.length - 1;
  }

  /*
   * push Object o onto the top of the stack, or throw an Overflow exception if the stack is full
   */
  public void push(Object item) throws Overflow {
    if (!isFull()){
      myStack[last + 1] = item;
      last++;
    }
    else throw new Overflow("Stack is full.");
  }

  /* 
   * return the Object on top of the stack, or throw an Underflow exception if the stack is empty
   */
  public Object examine() throws Underflow {
    if (!isEmpty()){
      return myStack[last];
    }
    else throw new Underflow("Stack is empty.");
  }
 /*
   * remove and return the Object on the top of the stack, or throw an Underflow exception if the stack is empty
   */
  public Object pop() throws Underflow {
    if (!isEmpty()){
      Object x = myStack[last];
      last--;
      return x;
    }
    else throw new Underflow("Stack is empty.");
  }
}