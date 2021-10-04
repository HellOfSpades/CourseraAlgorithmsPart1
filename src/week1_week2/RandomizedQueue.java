package week1_week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] array;
    private int lastValue = -1;

    private void refillArray(int newsize){
        Item[] newarray = (Item[]) new Object[newsize];
        for (int i = 0; i < array.length; i++) {
            newarray[i] = array[i];
        }
        array = newarray;
    }

    // construct an empty randomized queue
    public RandomizedQueue(){
        array = (Item[])new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        return lastValue<0;
    }

    // return the number of items on the randomized queue
    public int size(){
        return lastValue+1;
    }

    // add the item
    public void enqueue(Item item){
        if(item==null)throw new IllegalArgumentException();
        if(lastValue>=array.length-1){
            refillArray(array.length*2);
        }
        lastValue++;
        array[lastValue] = item;

    }

    // remove and return a random item
    public Item dequeue(){
        if(isEmpty())throw new NoSuchElementException();

        //picking a random element
        int index = (int)Math.round(Math.random()*lastValue);
        Item item = array[index];
        System.out.println("index chosen: "+index);
        //removing the element from the array
        for (int i = index+1; i <= lastValue; i++) {
            array[i-1] = array[i];
        }
        lastValue--;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if(isEmpty())throw new NoSuchElementException();

        //picking a random element
        int index = (int)Math.round(Math.random()*lastValue);
        Item item = array[index];

        return item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return new Iterator<Item>() {

            private Item[] innerArray = array.clone();
            private int innerLastValue = lastValue;


            // remove and return a random item
            private Item innerDequeue(){
                if(innerLastValue<0)throw new NoSuchElementException();

                //picking a random element
                int index = (int)Math.round(Math.random()*innerLastValue);
                Item item = innerArray[index];

                //removing the element from the array
                for (int i = index+1; i <= innerLastValue; i++) {
                    innerArray[i-1] = innerArray[i];
                }
                innerLastValue--;
                return item;
            }


            @Override
            public boolean hasNext() {
                return innerLastValue>=0;
            }
            @Override
            public void remove(){
                throw new UnsupportedOperationException();
            }
            @Override
            public Item next(){
                return innerDequeue();
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args){
        int num = 0;
        for (int n = 0; n < 50; n++) {


            RandomizedQueue<Integer> rq = new RandomizedQueue<>();

            for (int i = 0; i < 12; i++) {
                rq.enqueue(i );
            }
            int s = rq.dequeue();
            if(s==11)num++;
        }
        System.out.println("11s: " + num);
//        week1_week2.RandomizedQueue<String> rq = new week1_week2.RandomizedQueue<>();
//        rq.enqueue("a");
//        rq.enqueue("b");
//        rq.enqueue("c");
//        rq.enqueue("d");
//        rq.enqueue("e");
//        System.out.println(rq.isEmpty());
//        System.out.println(rq.size());
//        System.out.println(rq.sample());
//        System.out.println(rq.dequeue());
    }

}