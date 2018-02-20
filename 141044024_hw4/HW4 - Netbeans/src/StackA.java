
import java.util.ArrayList;

/**
 * StackInterface implement eder ve ArrayList'i extend eder.
 * @author emre
 * @param <E> generic 
 */
public class StackA<E> extends ArrayList<E> implements StackInterface<E>{
    
    public void push(E item){
        super.add(item);
    }
    
    public E pop(){
        return super.remove(size() - 1);
    }
    
    public int size(){
        return super.size();
    }

    public boolean isEmpty(){
        return (size() == 0);
    }
}
