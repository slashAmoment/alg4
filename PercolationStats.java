import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;
public class PercolationStats {
    private final double[] finished;
    private final int ntrials;

    public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
    {
        finished = new double[trials];
        ntrials = trials;
        if (n < 1 || trials < 1)
        {
            throw new java.lang.IllegalArgumentException();
        }
        for (int i = 0; i < trials; i++)
        {
            Percolation pn = new Percolation(n);
            double openedSites = 0;
            while (!pn.percolates())
            {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1,n + 1);
                if (!pn.isOpen(row, col)) {
                    pn.open(row, col);
                    openedSites++;
                }
            }
            finished[i] = openedSites * 1.0 / (n * n);
        }
        /*
        for (double i:finished) {
            StdOut.println("finished                  = " + i);
        }
        */
    }
    public double mean()                          // sample mean of percolation threshold
    {
        return StdStats.mean(finished);
    }
    public double stddev()                        // sample standard deviation of percolation threshold
    {
        return StdStats.stddev(finished);
    }
    public double confidenceLo()                  // low  endpoint of 95% confidence interval
    {
        final double confidence = 1.96;
        return mean() - ((confidence * stddev()) / Math.sqrt(ntrials));
    }
    public double confidenceHi()                  // high endpoint of 95% confidence interval
    {
        return mean() + ((1.96 * stddev()) / Math.sqrt(ntrials));
    }
    public static void main(String[] args)        // test client (described below)
    {

        int num = Integer.parseInt(args[0]);
        int trial = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(num, trial);

        String confidence = ps.confidenceLo() + ", " + ps.confidenceHi();
        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = " + confidence);
    }
}

