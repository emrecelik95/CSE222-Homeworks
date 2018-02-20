
import java.util.LinkedList;


public class priorityQueueB {
    LinkedList list = new LinkedList();
    
    public int size(){
        return list.size();
    }
    
    public boolean isEmpty(){
        return size() == 0;
    }
    
    public void insert(Object item){
        list.add(item);
    }
}
