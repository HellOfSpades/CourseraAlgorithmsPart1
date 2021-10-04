package week1_week2;

public class ConnectivityUnion{

    public static void main(String[] args){

    }

    public static void connectUnion(int[] array, int a, int b){
        if(array[a]!=array[b]) {
            int ia = array[a];

            for (int i = 0; i < array.length; i++) {
                if (array[i] == ia) array[i] = array[b];
            }
        }
    }

    public static boolean FindUnion(int[] array, int a, int b){
        return array[a]==array[b];
    }

    //"lazy aproach"
    public class QuickUnion{

        int[] array;

        //finds the root of an element
        public int root(int a){

            while(array[a]!=a){
                a = array[a];
            }
            return a;
        }

        //puts the root element of element 'a' under the root element of element 'b'
        public void union(int a, int b){
            int rootA = root(a);
            int rootB = root(b);
            array[rootA] = rootB;
        }

        //check if two elements are connected by seeing if they have the same root object
        public boolean connected( int a, int b){
            return root(a)==root(b);
        }

    }

}
