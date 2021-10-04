package week1_week2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {

    private double[] probability;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
        if(n<=0 || trials<=0){throw new IllegalArgumentException();}

        probability = new double[trials];

        for(int i = 0;i<trials;i++){
            Percolation p = new Percolation(n);
            while (!p.percolates()){
                p.open((int)(StdRandom.random()*n+1),(int)(StdRandom.random()*n)+1);
            }
            probability[i] = (double)(p.numberOfOpenSites())/(n*n);
        }
    }

    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(probability);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(probability);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return  StdStats.mean(probability)-(StdStats.stddev(probability)*1.960/Math.sqrt(probability.length));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return  StdStats.mean(probability)+(StdStats.stddev(probability)*1.960/Math.sqrt(probability.length));
    }

    // test client (see below)
    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n,t);
        System.out.println("mean                    = "+ps.mean());
        System.out.println("stddev                  = "+ps.stddev());
        System.out.println("95% confidence interval = ["+ ps.confidenceLo()+", "+ ps.confidenceHi()+"]");
    }
}
