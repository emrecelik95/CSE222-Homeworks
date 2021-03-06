import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")          // warningleri ignore et                      

public class DynamicArray<T> implements Iterable<T>{
   
    private T[] arr;            // dynamic array
    private int capacity;       // arrayin kapasitesi
    private int used;           // arrayin kullanılan kısmı(size)
    
    DynamicArray()         // tek parametre(veritipi alan)constructor
    {                           // ilklendirmele,arraye yer alma
        capacity = 10;
        used = 0;
        arr = (T[])new Object[capacity];
    }
    
   
    public int size()
    {
        return used;
    }

    public void add(T value)
    {
        if(used >= capacity)
        {
            capacity = 2 * capacity + 1;
            T[] tempArr = (T[])new Object[capacity];
            for(int k = 0 ; k < used ; ++k)
                tempArr[k] = arr[k];
                 
            arr = tempArr;
                   
        }
        arr[used] = value;
        ++used;
    }

    public void remove(int index){
        T[] temp = (T[])new Object[capacity];
        for(int i = 0 ; i < index ; ++i){
            temp[i] = arr[i];
        }
        for(int i = index + 1 ; i < size() ; ++i){
            temp[i - 1] = arr[i];
        }
        
        arr = temp;
        --used;
    }
    
    public int indexOf(T obj){
        for(int i = 0 ; i < size() ; ++i)
            if(arr[i] == obj)
                return i;
        
        return -1;

    }    
    
    public T set(int index , T obj){
        if(index > used){
            System.out.println("IndexOutOfBounds");
            return null;
        }
        arr[index] = obj;
        return obj;
    }
    
    public T get(int index){
        if(index > used){
            System.out.println("IndexOutOfBounds");
            return null;
        }
        return arr[index];
    }
    
    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator<T>(arr,0,size());
    }
    
    private static final class ArrayIterator<T> implements Iterator<T>{
        private T[] array;
        private int cursor;
        private final int end;
        
        ArrayIterator(T[] array,int start,int end){
            this.array = array;
            this.cursor = start;
            this.end = end;
        }
        
        @Override
        public boolean hasNext() {
            return this.cursor < end;
        }

        @Override
        public T next() {
            if(this.hasNext()){
                T current = array[cursor];
                ++cursor;
                return current;
            }
            else{
                throw new NoSuchElementException();
            }
        }
        
        @Override
        public void remove(){
            throw new UnsupportedOperationException("remove");
        }
    }
    
    
}
