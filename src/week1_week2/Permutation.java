package week1_week2;

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args){
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> input = new RandomizedQueue<>();
        for (int i = 0; i < k; i++) {
             input.enqueue(StdIn.readString());
        }
        for (int i = 0; i < k; i++) {
            System.out.println(input.dequeue());
        }
    }
}
