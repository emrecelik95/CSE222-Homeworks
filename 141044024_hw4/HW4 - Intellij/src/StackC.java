/**
 * StackInterface implement eder ve Node barındırır,linkedlist gorevi gorerek
 * stack islevi gorur.
 * @author emre
 * @param <E> generic
 */
public class StackC<E> implements StackInterface<E>{
    /**
     * node objesi , stackın bası
     */
    private Node<E> head = null;
    /**
     * stack'taki eleman sayısı
     */
    private int size = 0;


    public void push(E item){
        Node<E> temp = head;
        head = new Node<E>(item);
        head.next = temp;
        ++size;
    }

    public E pop(){
        if(head == null){
            System.out.println("Stack is empty!\n");
            return null;
        }
        E data = head.data;
        
        head = head.next;
        --size;
        
        return data;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return (size() == 0);
    }

    /**
     * Inner class Node
     * @param <E> generic
     */
    private static class Node < E > {
        /** data */
        private E data;

        /** bir sonraki datanın node referansı */
        private Node < E > next = null;

        /** Data ile ilklendiren constructor
         @param dataItem data
         */
        private Node(E dataItem) {
            data = dataItem;
        }
    } 
}
