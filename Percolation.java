import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean [] openstate;
    private final int sitesN;
    private final int top;
    private int sitesOpenNum;
    private final WeightedQuickUnionUF uf;
    public Percolation(int n)                // create n-by-n grid, with all sites blocked
    {
        if (n < 1)
        {
            throw new java.lang.IllegalArgumentException();
        }
        sitesN = n;
        int total = n * n;
        sitesOpenNum = 0;
        top = n * n;
        uf = new WeightedQuickUnionUF(total + 1);
        openstate = new boolean[total + 1];
        for (int i = 0; i < total + 1; i++)
        {
            openstate[i] = false;
        }
    }
    private static void validateIdx(int row, int col, int n)
    {
        if (row == n * n)
        {
            return;
        }
        if (row < 1 || col < 1 || row > n || col > n)
        {
            throw new java.lang.IllegalArgumentException();
        }
    }
    private int getIdx(int row, int col)
    {
        return (row - 1) * sitesN + col - 1;
    }
    public    void open(int row, int col)    // open site (row, col) if it is not open already
    {
        validateIdx(row, col, sitesN);
        if (isOpen(row, col))
        {
            return;
        }
        int ndIdx = getIdx(row, col);
        openstate[ndIdx] = true;
        sitesOpenNum += 1;

        if (row == 1)
        {
            uf.union(ndIdx, top);

        }
        else
        {
            if (isOpen(row - 1, col))
            {
                int topIdx = getIdx(row - 1, col);
                uf.union(ndIdx, topIdx);
            }
        }
        if (row == sitesN)
        {
            if (isOpen(sitesN, col))
            {
                int bottomIdx = getIdx(sitesN, col);
                uf.union(ndIdx, bottomIdx);
            }
        }
        else
        {
            if (isOpen(row + 1, col))
            {
                int bottomIdx = getIdx(row + 1, col);
                uf.union(ndIdx, bottomIdx);
            }
        }
        if (col == 1)
        {
            if (isOpen(row, 1))
            {
                int leftIdx = getIdx(row, 1);
                uf.union(ndIdx, leftIdx);
            }
        }
        else
        {
            if (isOpen(row, col - 1))
            {
                int leftIdx = getIdx(row, col - 1);
                uf.union(ndIdx, leftIdx);
            }
        }
        if (col == sitesN)
        {
            if (isOpen(row, sitesN))
            {
                int rightIdx = getIdx(row, sitesN);
                uf.union(ndIdx, rightIdx);
            }
        }
        else
        {
            if (isOpen(row, col + 1))
            {
                int rightIdx = getIdx(row, col + 1);
                uf.union(ndIdx, rightIdx);
            }
        }
    }
    public boolean isOpen(int row, int col)  // is site (row, col) open?
    {
        validateIdx(row, col, sitesN);
        int ndIdx = getIdx(row, col);
        return openstate[ndIdx];
    }
    public boolean isFull(int row, int col)  // is site (row, col) full?
    {
        validateIdx(row, col, sitesN);
        int ndIdx = getIdx(row, col);
        if (uf.connected(ndIdx, top))
        {
            return true;
        }
        return false;
    }
    public     int numberOfOpenSites()       // number of open sites
    {
        return sitesOpenNum;
    }
    public boolean percolates()              // does the system percolate?
    {
        for (int i = 1; i < sitesN + 1; i++)
        {
            if(!isOpen(sitesN, i))
            {
                continue;
            }
            if (isFull(sitesN, i))
            {
                return true;
            }
        }
        return false;
    }
}
