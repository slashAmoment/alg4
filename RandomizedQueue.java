import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int num;
    private Item []items;
    public RandomizedQueue()                 // construct an empty randomized queue
    {
        num = 0;
        items = (Item []) new Object[1];
    }
    public boolean isEmpty()                 // is the randomized queue empty?
    {
        return num == 0;
    }
    public int size()                        // return the number of items on the randomized queue
    {
        return num;
    }
    private void resize(int nsize)
    {
        Item [] nitems = (Item[]) new Object[nsize];
        for(int i = 0; i < num; i++)
        {
            nitems[i] = items[i];
        }
        items = nitems;
    }
    public void enqueue(Item item)           // add the item
    {
        if(item == null)
        {
            throw new java.lang.IllegalArgumentException();
        }
        if(num == items.length)
        {
            resize(num * 2);
        }
        items[num++] = item;
    }
    public Item dequeue()                    // remove and return a random item
    {
        if(isEmpty())
        {
            throw new java.util.NoSuchElementException();
        }
        if(num == items.length / 4)
        {
            resize(items.length / 2);
        }
        Item tmp;
        int idx = StdRandom.uniform(num);
        tmp = items[--num];
        Item item = items[idx];
        items[idx] = tmp;
        return item;
    }
    public Item sample()                     // return a random item (but do not remove it)
    {
        if(isEmpty())
        {
            throw new java.util.NoSuchElementException();
        }
        int idx = StdRandom.uniform(num);
        return items[idx];
    }
    public Iterator<Item> iterator()         // return an independent iterator over items in random order
    {
        return new ListIterator();
    }
    private class ListIterator implements Iterator<Item>
    {
        private int n;
        private int []nidx;
        public ListIterator()
        {
            n = num;
            nidx = new int [num];
            for(int i = 0; i < num; i++)
            {
                nidx[i] = i;
            }
            for(int i = 0; i < num; i++)
            {
                int idx = StdRandom.uniform(i + 1);
                int tmp = nidx[i];
                nidx[i] = nidx[idx];
                nidx[idx] = tmp;
            }
        }
        public boolean hasNext()
        {
            return n > 0;
        }
        public void remove()
        {
            throw new java.lang.UnsupportedOperationException();
        }
        public Item next()
        {
            if(isEmpty())
            {
                throw new java.util.NoSuchElementException();
            }
            return items[nidx[--n]];
        }
    }
    public static void main(String[] args)   // unit testing (optional)
    {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer> ();
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
        rq.enqueue(5);
        rq.enqueue(6);
        StdOut.println("Size: " + rq.size());
        StdOut.println(rq.dequeue());
        StdOut.println("Size: " + rq.size());
        Iterator<Integer> it = rq.iterator();
        StdOut.println(it.next());
        StdOut.println(it.next());
        StdOut.println(it.next());
        StdOut.println(it.next());
        StdOut.println(it.next());
    }
}