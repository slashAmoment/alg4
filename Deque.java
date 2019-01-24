import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
    private Node first = new Node();
    private Node last = new Node();
    private int num;
    private class Node
    {
        private Item item;
        private Node prev;
        private Node next;
    }
    public Deque()                           // construct an empty deque
    {
        num = 0;
    }
    public boolean isEmpty()                 // is the deque empty?
    {
        return num == 0;
    }
    public int size()                        // return the number of items on the deque
    {
        return num;
    }
    public void addFirst(Item item)          // add the item to the front
    {
        if(item == null)
        {
            throw new java.lang.IllegalArgumentException();
        }
        if(first.next == null)
        {
            Node tmp = new Node();
            first.next = last.prev = tmp;
            tmp.item = item;
            tmp.next = tmp.prev = null;
            num += 1;
        }
        else
        {
            Node tmp = new Node();
            tmp.item = item;
            tmp.next = first.next;
            first.next.prev = tmp;
            first.next = tmp;
            num += 1;
        }
    }
    public void addLast(Item item)           // add the item to the end
    {
        if(item == null)
        {
            throw new java.lang.IllegalArgumentException();
        }
        if(last.prev == null)
        {
            Node tmp = new Node();
            first.next = last.prev = tmp;
            tmp.item = item;
            tmp.next = tmp.prev = null;
            num += 1;
        }
        else
        {
            Node tmp = new Node();
            tmp.item = item;
            tmp.prev = last.prev;
            last.prev.next = tmp;
            last.prev = tmp;
            num += 1;
        }
    }
    public Item removeFirst()                // remove and return the item from the front
    {
        if(isEmpty())
        {
            throw new java.util.NoSuchElementException();
        }
        Item item = first.next.item;
        if(first.next.next == null)
        {
            last.prev = null;
        }
        else
        {
            first.next.next.prev = null;
        }
        first.next = first.next.next;
        num -= 1;
        return item;
    }
    public Item removeLast()                 // remove and return the item from the end
    {
        if(isEmpty())
        {
            throw new java.util.NoSuchElementException();
        }
        Item item = last.prev.item;
        if(last.prev.prev == null)
        {
            first.next = null;
        }
        else
        {
            last.prev.prev.next = null;
        }
        last.prev = last.prev.prev;
        num -= 1;
        return item;
    }
    public Iterator<Item> iterator()         // return an iterator over items in order from front to end
    {
        return new ListIterator();
    }
    private class ListIterator implements Iterator<Item>
    {
        private Node cur = first;
        public Item next()
        {
            if(isEmpty())
            {
                throw new java.util.NoSuchElementException();
            }
            Item item = cur.item;
            cur = cur.next;
            return item;
        }
        public boolean hasNext()
        {
            return cur.next == null;
        }
        public void remove()
        {
            throw new java.lang.UnsupportedOperationException();
        }

    }
    public static void main(String[] args)   // unit testing (optional)
    {
        Deque<Integer> deque = new Deque<Integer>();

        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);
        deque.addFirst(0);
        deque.addFirst(-1);
        deque.addFirst(-2);
        StdOut.println(deque.removeLast());
        StdOut.println(deque.removeLast());
        StdOut.println(deque.removeFirst());
        StdOut.println(deque.removeFirst());
        StdOut.println(deque.removeFirst());
        StdOut.println(deque.removeFirst());
        StdOut.println(deque.removeFirst());
        StdOut.println(deque.removeFirst());

    }
}
