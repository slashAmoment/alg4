/* *****************************************************************************
 *  Name:    Alan Turing
 *  NetID:   aturing
 *  Precept: P00
 *
 *  Description:  Prints 'Hello, World' to the terminal window.
 *                By tradition, this is everyone's first program.
 *                Prof. Brian Kernighan initiated this tradition in 1974.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.Stack;

public class twoStackQueue<Item> {
    private Stack<Item> in = new Stack<Item>();
    private Stack<Item> out = new Stack<Item>();

    public void enqueue(Item item){
        in.push(item);
    }
    public Item dequeue(){
        if(out.isEmpty())
        {
            while(!in.isEmpty())
            {
                Item i = in.pop();
                out.push(i);
            }
        }
        return out.pop();
    }
    public static void main(String[] args) {

    }
}
