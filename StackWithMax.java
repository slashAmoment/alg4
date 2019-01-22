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

public class StackWithMax extends Stack<Integer> {
    private Stack<Integer> maxStack;

    public StackWithMax()
    {
        maxStack = new Stack<Integer>();
    }

    public int max()
    {
        if(!maxStack.isEmpty())
        {
            return maxStack.peek();
        }
        else
        {
            return Integer.MIN_VALUE;
        }
    }

    public void push(Integer value)
    {
        super.push(value);
        if(value >= max())
        {
            maxStack.push(value);
        }
    }
    public Integer pop()
    {
        Integer value = super.pop();
        if(value == max())
        {
            maxStack.pop();
        }
        return value;
    }
    public static void main(String[] args) {

    }
}
