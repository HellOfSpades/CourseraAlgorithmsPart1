package week1_week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Element first = new Element();
    private Element last = new Element();
    private int size = 0;

    // construct an empty deque
    public Deque(){
        first.previus = last;
        last.next = first;
    }

    // is the deque empty?
    public boolean isEmpty(){
        return size==0;
    }

    // return the number of items on the deque
    public int size(){
        return size;
    }

    // add the item to the front
    public void addFirst(Item item){
        if(item==null)throw new IllegalArgumentException();
        size++;
        Element element = new Element(item);

        first.previus.next = element;
        element.previus = first.previus;
        element.next = first;
        first.previus = element;
    }

    // add the item to the back
    public void addLast(Item item){
        if(item==null)throw new IllegalArgumentException();
        size++;
        Element element = new Element(item);

        last.next.previus = element;
        element.next = last.next;
        element.previus = last;
        last.next = element;
    }

    // remove and return the item from the front
    public Item removeFirst(){
        if(!isEmpty()){
            size--;
            Item item = first.previus.value;
            first.previus.previus.next = first;
            first.previus = first.previus.previus;
            return item;
        }else{
            throw new NoSuchElementException();
        }

    }

    // remove and return the item from the back
    public Item removeLast(){
        if(!isEmpty()){
            size--;
            Item item = last.next.value;
            last.next.next.previus = last;
            last.next = last.next.next;
            return item;
        }else{
            throw new NoSuchElementException();
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        return new Iterator<Item>() {
            Element element = first;
            @Override
            public boolean hasNext() {
                return element.previus!=last;
            }

            @Override
            public void remove(){
                throw new UnsupportedOperationException();
            }

            @Override
            public Item next(){
                if(!hasNext())throw new NoSuchElementException();
                this.element = element.previus;
                return this.element.value;
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args){


        Deque<String> deque = new Deque<>();
//        for (int i = 0; i < 10; i++) {
//            deque.addLast(i+"");
//        }
//        Iterator<String> iterator = deque.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//        }

        deque.addFirst("a");
        deque.addFirst("b");
        deque.addFirst("c");
        deque.addFirst("d");
        deque.addLast("z");
        System.out.println(deque.size());
        System.out.println(deque.isEmpty());
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeLast());
    }

    private class Element{
        Item value;
        Element next = null;
        Element previus = null;
        Element(){
            value = null;
        }
        Element(Item value){
            this.value = value;
        }

    }


}
