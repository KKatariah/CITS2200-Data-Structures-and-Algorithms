import CITS2200.List;
import CITS2200.OutOfBounds;
import CITS2200.WindowLinked;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestListLinked {

    
    @Test
public void testInsertAfter() throws OutOfBounds {
    ListLinked list = new ListLinked();
    WindowLinked w = new WindowLinked();
    list.beforeFirst(w);
    list.insertAfter("A", w);
    list.next(w); // move the window to the newly inserted element
    assertEquals("A", list.examine(w)); // examine the element
    list.insertAfter("B", w);
    list.next(w); // move the window to the newly inserted element
    assertEquals("B", list.examine(w)); // examine the element
}

    

    @Test
    public void testInsertBefore() {
        ListLinked list = new ListLinked();
        WindowLinked w = new WindowLinked();
        Object o1 = new Object();
        Object o2 = new Object();
        Object o3 = new Object();

        // Test inserting before first with no elements
        list.beforeFirst(w);
        try {
            list.insertBefore(o1, w);
        } catch (OutOfBounds e) {
            assertTrue(true);
        }

        // Test inserting before first with one element
        list.afterLast(w);
        list.insertAfter(o1, w);
        list.beforeFirst(w);
        list.insertBefore(o2, w);
        assertEquals(o2, list.examine(w));
        list.next(w);
        assertEquals(o1, list.examine(w));

        // Test inserting before middle
        list.afterLast(w);
        list.insertAfter(o1, w);
        list.insertAfter(o3, w);
        list.beforeFirst(w);
        list.next(w);
        list.insertBefore(o2, w);
        list.beforeFirst(w);
        list.next(w);
        assertEquals(o2, list.examine(w));
        list.next(w);
        assertEquals(o1, list.examine(w));
        list.next(w);
        assertEquals(o3, list.examine(w));
    }

    @Test
    public void testDelete() throws OutOfBounds {
        ListLinked list = new ListLinked();
        WindowLinked window = new WindowLinked();
    
        list.insertBefore("A", window); // Insert "A"
        list.insertAfter("B", window);  // Insert "B" after "A"
    
        assertEquals("A", list.examine(window)); // Check that we're examining "A"
    
        Object deleted = list.delete(window); // Delete "A"
        assertEquals("A", deleted); // Check that the deleted object is "A"
    
        assertEquals("B", list.examine(window)); // Check that we're now examining "B"
    
        list.beforeFirst(window); // Move window before the start of the list
    
        // Try to delete an element when the window is before the start of the list
        assertThrows(OutOfBounds.class, () -> {
            list.delete(window);
        });
    }
    

    @Test(expected = OutOfBounds.class)
    public void testOutOfBounds() throws OutOfBounds {
        List list = new ListLinked();
        WindowLinked window = new WindowLinked();
        list.examine(window); // should throw OutOfBounds
    }
}
