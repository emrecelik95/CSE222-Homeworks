
import java.util.Iterator;
import java.util.LinkedList;


public class priorityQueueA extends LinkedList{
    
    public int size(){
        return super.size();
    }
    
    public boolean isEmpty(){
        return size() == 0;
    }
    
    public void insert(Object item){
        super.add(item);
    }
    
}
