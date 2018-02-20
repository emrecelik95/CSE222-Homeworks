import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Single Linked List : 1 node ve 1 silinen node tutar.
 * Iterable interfaceini implement eder.
 * @author emre
 * @param <E> generic
 * 
 * 
 */
public class MySingleLinkedList <E> implements Iterable<E>{
    /**
     * en bastaki nodu gosteren nod
     */
    private Node<E> head = null;
    /**
     * silinenlerin en basındaki nodu gosteren nod
     */
    private Node<E> deleted = null;
    /**
     * eleman sayisi
     */
    private int size = 0;
    /**
     * silinen eleman sayisi
     */
    private int delSize = 0;

    /**
     * Bastan ekler.
     * @param item eklenecek generic eleman
     */
    public void addFirst(E item){
        if(getDeletedIndex(item) != -1){
            Node <E> temp = head;
            this.head = getDeletedNode(getDeletedIndex(item));
            head.next = temp;
            return;
        }
        this.head = new Node<E>(item,head);
        ++size;
    }
    /**
     * Verilen nodun sonrasına ekler.
     * @param node elemanın eklenecegi noddan onceki nod
     * @param item eklenecek generic eleman
     */
    private void addAfter(Node<E> node , E item){
        
        node.next = new Node<E>(item,node.next);
        ++size;
    }
    /**
     * Verilen indexe ekleme yapar.
     * @param index eklenecek konum
     * @param item eklnecek eleman
     */
    public void add(int index , E item){
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException(Integer.toString(index));
        
        if(index == 0)
            addFirst(item);
        else{
            if(getDeletedIndex(item) != -1){
                Node <E> node = getNode(index - 1);
                Node <E> nodeDel = getDeletedNode(getDeletedIndex(item));
                Node <E> temp = node.next;
                node.next = nodeDel;
                nodeDel.next = temp;
                return;
            }
            Node<E> node = getNode(index - 1);
            addAfter(node,item);
        }
    }
    /**
     * Sona eleman ekler.
     * @param item eklenecek data
     * @return true dondurur
     */
    public boolean add(E item){
        if(getDeletedIndex(item) != -1){
            addOld(getDeletedIndex(item));
            return true;
        }
            
        add(size,item);
        return true;
    }
    /**
     * Verilen noddam sonraki nodu siler.
     * @param node silinecek noddan oncesindeki nod
     * @return silinenin data'sı
     */
    private E removeAfter(Node<E> node){
        Node<E> temp = node.next;
        if(temp!= null){
            node.next = temp.next;
            --size;
            addDeleted(temp);
            return temp.data;
        }
        else
            return null;
    }       
    /**
     * Bastaki elemanı siler.
     * @return silinen datayı return eder
     */
    public E removeFirst(){
        Node<E> temp = head;
        if(head != null){
            head = head.next;
            --size;
            addDeleted(temp);
            return temp.data;
        }
        else
            return null;
    } 
    /**
     * Verilen index'teki elemanı siler.
     * @param index silinecek eleman indexi
     */
    public void remove(int index){
        Node<E> node = null;
        if(index == 0){
            removeFirst();
            return;
        }
     
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException(Integer.toString(index));
        
        removeAfter(getNode(index - 1));
    }
    /**
     * Silinen nodu deleted noduna ekler.
     * @param node silinenlere eklenecek nod
     */
    private void addDeleted(Node<E> node){
        if(deleted == null)
            deleted = new Node<E>(node.data);
        else
            deleted.next = new Node<E>(node.data);
        
        ++delSize;
        
    }
    /**
     * Verilen Indexe sahip olan silineni dondurur.
     * @param index getirilecek olan silinenin indexi
     * @return data dondurur
     */
    public E getDeleted(int index){
        if(index < 0 || index >= delSize)
            throw new IndexOutOfBoundsException(Integer.toString(index));
                
        return getDeletedNode(index).data;
    } 
    /**
     * Silineni node olarak dondurur.
     * @param index silinenin indexi
     * @return node dondurur
     */
    public Node<E> getDeletedNode(int index){
        if(index < 0 || index >= delSize)
            throw new IndexOutOfBoundsException(Integer.toString(index));
        
        Node<E> node = deleted;
        for(int i = 0 ; i < index && node != null ; ++i)
            node = node.next;
                
        return node;
    } 
    /**
     * Verinin silinenler arasında olup olmadigina gore eger varsa index'i verir.
     * @param data - kontrol edilecek veri
     * @return index veya -1
     */
    private int getDeletedIndex(E data){
        Node<E> node = deleted;
        for(int i = 0 ; i < delSize && node != null ; ++i){
            if(node.data.equals(data))
                return i;
            node = node.next;
        }
        return -1;
    }
    /**
     * Sona ekleme(onceden silineni gosterir).
     * @param delIndex silinenin indexi
     */
    private void addOld(int delIndex){
        Node<E> node = getNode(size - 1);
        node.next = getDeletedNode(delIndex);
        node.next.next = null;
        ++size;
    }
    /**
     * Silinenlerin toStringi.
     * @return string 
     */
    public String deletedToString(){
        String out = "";
        out += "[";
        
        for(int i = 0 ; i < delSize ; ++i){
            out += getDeleted(i);
            if(i != delSize - 1)
                out += ", ";
        }
        
        out += "]";
        return out;
    }
    /**
     * Verilen Indexe sahip olan nodu dondurur
     * @param index nodun indexi
     * @return node
     */
    private Node<E> getNode(int index){
        Node<E> node = head;
        for(int i = 0 ; i < index && node != null ; ++i)
            node = node.next;
                
        return node;
    }
    
   
   /**
    * Nodun datasını dondurur.
    * @param index nodun indexi
    * @return data
    */ 
    public E get(int index){
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException(Integer.toString(index));
        
        Node<E> node = getNode(index);
        return node.data;
    } 
    /**
     * Verilen Indexe sahip olan nodun datasını degistirir.
     * @param index node index
     * @param value yeni data
     * @return eski data
     */
    public E set(int index , E value){
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException(Integer.toString(index));
        
        Node<E> node = getNode(index);
        E oldValue = node.data;
        node.data = value;
        
        return oldValue;
    }
    /**
     * eleman sayisi
     * @return size
     */
    public int size(){
        return this.size;
    }
    /**
     * LinkedList toStringi
     * @return string
     */
    public String toString(){
        String out = "";
        out += "[";

        Iterator<E> itr = new Iter();
        while(itr.hasNext()){
            out += itr.next().toString();
            if(itr.hasNext())
                out += ", ";
        }
        
        out += "]";
        return out;
    }
    /**
     * LinkedList terst toStringi
     * @return string
     */
    public String reverseToString(){
        String out = "";
        Node<E> node = head;
        out = "[";
        out += reverseNodesToString(node);
        out += "]";
        
        return out;
    }
    /**
     * LinkedList elemanlarını aralarında virgülle tersten toplayip string olarak
     * donduren fonksiyon.
     * @param node baslanilacak node(head gelir)
     * @return string
     */
    private String reverseNodesToString(Node<E> node){    
        if(node.next == null){
            return node.data.toString();
        }
        
        return  reverseNodesToString(node.next) + " ," + node.data.toString();
    }
    /**
     * iterator
     * @return new Iter
     */
    @Override
    public Iterator<E> iterator() {
        return new Iter(0);
    }
    /**
     * Node Inner Class
     * @param <E> generic
     */
    private static class Node<E>{
        /**
         * tutacağı veri
         */
        private E data;
        /**
         * sonraki nodun referansı
         */
        private Node<E> next;
        /**
         * Generic data parametresi alan constructor
         * @param data 
         */
        private Node(E data){
            this.data = data;
            this.next = null;
        }
        /**
         * Verisi ve nexti belli olan nod için gerekli constructor
         * @param item data
         * @param nodeRef node referansı
         */
        private Node(E item,Node<E> nodeRef){
            this.data = item;
            this.next = nodeRef;
        }
        
    }
    /**
     * Iterator Inner Class
     */
    private class Iter implements Iterator<E>{
        /**
         * Sonraki eleman
         */
        private Node<E> nextItem;
        /**
         * Dondurulen son eleman
         */
        private Node<E> lastItemReturned;
        /**
         * Index
         */
        private int index = 0;
        /**
         * No parameter constructor
         */
        private Iter(){
            this(0);
        }
        /**
         * Belirli indexten baslamak icin gerekli constructor
         * @param index 
         */
        private Iter(int index){
            if(index < 0 || index > size)
                throw new IndexOutOfBoundsException("Invalid Index : " + index);
            lastItemReturned = null;
            nextItem = head;
            for(int i = 0 ; i < index ; ++i)
                nextItem = nextItem.next;
            this.index = index;
        }
        /**
         * Bir sonraki eleman var mı check eder
         * @return bool
         */
        @Override
        public boolean hasNext() {
            return nextItem != null;
        }
        /**
         * Bir sonraki eleman(eger varsa) dondurulur
         * @return generic data
         */
        @Override
        public E next() {
            if(!hasNext())
                throw new NoSuchElementException();
            lastItemReturned = nextItem;
            nextItem = nextItem.next;
            ++index;
            return lastItemReturned.data;
        }
        
        @Override
        public void remove(){
             throw new UnsupportedOperationException("remove");
        }
        
    }
}
