package week1_week2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args){
        String s = "";
        float i = 1;
        while(!StdIn.isEmpty()){
            String temp = StdIn.readString();
            if(StdRandom.bernoulli(1/i)){
                s = temp;
            }
            i++;
        }
        StdOut.println(s);
    }
}
