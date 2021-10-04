package week1_week2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] grid;
    private int[] unionArray;
    private int openSitesCount;


    //Union Methods
    //========================================================================================
    //returns the unionArray index of a element in the grid
    private int gridIndex(int row, int col){
        return grid.length*col+row;
    }
    //returns the root of a union tree
    private int root(int index){
        while (unionArray[index]!=index){
            index = unionArray[index];
        }
        return index;
    }
    private void union(int a, int b){
        unionArray[root(a)] = root(b);
    }
    //========================================================================================



    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){
        if(n<=0)throw new IllegalArgumentException();
        grid = new boolean[n][n];
        unionArray = new int[n*n+2];
        for(int i = 0;i<unionArray.length;i++){
            unionArray[i] = i;
        }
        openSitesCount = 0;
    }


    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        row-=1;
        col-=1;
        if(row>=grid.length || row<0 || col>=grid.length || col<0){
            throw new IllegalArgumentException();
        }
        if(!isOpen(row+1,col+1)){
            grid[row][col] = true;
            openSitesCount++;



            //union stuff
            //connecting to the abstract top and buttom nodes
            if(row==0){
                union(gridIndex(row,col),unionArray.length-2);
            }
            if(row==grid.length-1){
                union(gridIndex(row,col),unionArray.length-1);
            }
            //connecting to side and bottom/top cells
            if(col>0 && grid[row][col-1]){
                union(gridIndex(row,col),gridIndex(row, col-1));
            }
            if(col<grid.length-1 && grid[row][col+1]){
                union(gridIndex(row,col),gridIndex(row, col+1));
            }
            if(row>0 && grid[row-1][col]){
                union(gridIndex(row,col),gridIndex(row-1, col));
            }
            if(row<grid.length-1 && grid[row+1][col]){
                union(gridIndex(row,col),gridIndex(row+1, col));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        row-=1;
        col-=1;
        if(row>=grid.length || row<0 || col>=grid.length || col<0){
            throw new IllegalArgumentException();
        }
        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){

        row-=1;
        col-=1;
        if(row>=grid.length || row<0 || col>=grid.length || col<0){
            throw new IllegalArgumentException();
        }
        if(grid[row][col]){
            return root(gridIndex(row,col))==root(unionArray[unionArray.length-2]);
        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return openSitesCount;
    }

    // does the system percolate?
    public boolean percolates(){
        return root(unionArray[unionArray.length-2])==root(unionArray.length-1);
    }

    // test client (optional)
    public static void main(String[] args){

    }
}
