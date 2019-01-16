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

import edu.princeton.cs.algs4.StdOut;
public class bitonic {
    public static int findMax(int[] a, int lo, int hi)
    {
        int mid = lo + (lo + hi) / 2;
        if (lo + 1 == hi || lo == hi)
        {
            return hi;
        }
        else if (mid - 1 >= 0 && mid + 1 <= hi &&
                a[mid - 1] <= a[mid] && a[mid] >= a[mid + 1])
        {
            return mid;
        }
        else if (mid - 1 >= 0 && mid + 1 <= hi &&
                a[mid - 1] >= a[mid] && a[mid] >= a[mid + 1])
        {
            return findMax(a, lo, mid - 1);
        }
        else if (mid - 1 >= 0 && mid + 1 <= hi &&
                a[mid - 1] <= a[mid] && a[mid] <= a[mid + 1])
        {
            return findMax(a, mid + 1, hi);
        }
        return -1;
    }
    public static int binarySearch(int []a, int key, int lo, int hi)
    {
        while(lo <= hi)
        {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] < key) lo = mid + 1;
            else if (a[mid] > key) hi = mid - 1;
            else return mid;
        }
        return -1;
    }
    public static int find(int[] a, int key)
    {
        int x = findMax(a, 0, a.length);
        int ret = binarySearch(a, key, 0, x);
        if (ret != -1)
        {
            return ret;
        }
        ret = binarySearch(a, key, x, a.length);
        if (ret != -1)
        {
            return ret;
        }

        return -1;
    }
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 0, -1, -2, -3};
        int x = findMax(a, 0, a.length);
        StdOut.println(a[x]);
        x = find(a, 3);
        StdOut.println(x);


    }
}
