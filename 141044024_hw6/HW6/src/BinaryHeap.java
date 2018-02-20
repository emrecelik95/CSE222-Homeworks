import java.util.*;

/**
 * BinarySearchTree extend edip queue interface'ini implement eden binary tree heap.
 * @param <E> generic
 */
public class BinaryHeap<E extends Comparable<E>>  extends BinarySearchTree<E> implements Queue{

    /**
     * eleman sayısı
     */
    protected int size = 0;

    /**
     * size getter
     * @return int
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * heap boş mu?
     * @return boolean
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Heap bu objeyi içeriyor mu diye kontrol eder.
     * @param o eleman
     * @return boolean
     */
    @Override
    public boolean contains(Object o){
        Iterator<E> itr = iterator();
        while(itr.hasNext()){
            if(itr.next().equals(o))
                return true;
        }
        return false;
    }

    /**
     * iterator
     * @return iterator
     */
    @Override
    public Iterator iterator() {
        return super.iterator();
    }

    /**
     * heapin arraye cevrilmesi
     * @return object array
     */
    @Override
    public Object[] toArray() {
        Iterator itr = iterator();
        Object[] arr = new Object[size()];

        for(int i = 0 ; itr.hasNext() ; ++i)
            arr[i] = itr.next();

        return arr;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    @Override
    public boolean remove(Object o) {
        return super.remove((E)o);
    }

    /**
     * veriken collection objesinin tum elemanlarının bu heap'e eklenmesi
     * @param c
     * @return boolean
     */
    @Override
    public boolean addAll(Collection c) {
        Iterator itr = c.iterator();
        while(itr.hasNext())
            if(!add(itr.next()))
                return false;

        return true;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    /**
     * Heap , bu objeyi kapsıyor mu?
     * @param c collection objesi
     * @return boolean
     */
    @Override
    public boolean containsAll(Collection c) {
        Iterator itr = c.iterator();
        while(itr.hasNext())
            if(!contains(itr.next()))
                return false;

        return true;
    }

    /**
     * eleman ekleme
     * @param o eklenecek eleman
     * @return boolean
     */
    @Override
    public boolean add(Object o) {
        if(o == null)
            throw new NullPointerException();
        return offer((E)o);
    }

    /**
     * add ile aynı , null dondurmek yerine exception fırlatır
     * @param o eklenecek eleman
     * @return boolean
     */
    @Override
    public boolean offer(Object o) {
        if(o == null)
            throw new NullPointerException();

        ++size;
        if(root == null) {
            root = new Node<E>((E)o);
            lastItemAdded = root;
            root.parent = null;
            return true;
        }
        insertLevelOrder(root,(E)o);

        return true;
    }

    /**
     * sondan eleman sil , poll'ı çağırır,exception fırlatır
     * @return silinen obje
     */
    @Override
    public Object remove() {
        if(isEmpty())
            return new NoSuchElementException();

        return poll();
    }

    /**
     * sondan eleman sil
     * @return silinen obje
     */
    @Override
    public Object poll() {
        if(isEmpty())
            return null;

        E data = root.data;
        root.data = lastItemAdded.data;

        if(lastItemAdded.parent.left == lastItemAdded)
            lastItemAdded.parent.left = null;
        else if(lastItemAdded.parent.right == lastItemAdded)
            lastItemAdded.parent.right = null;

        Node<E> node = root;

        while(!(node.left == null && node.right == null)){
            Node<E> tmp = null;
            if(node.left != null && node.right  != null)
            {

                if(node.left.data.compareTo(node.right.data) < 0)
                {
                    tmp = node.left;
                }
                else
                    tmp = node.right;

                if(tmp.data.compareTo(node.data) < 0) {
                    E temp = node.data;
                    node.data = tmp.data;
                    tmp.data = temp;

                    node = tmp;
                }

            }
            else
                break;

                /*
            if(node.left != null && node.left.data.compareTo(node.data) < 0) {
                E temp = node.data;
                node.data = node.left.data;
                node.left.data = temp;

                node = node.left;
            }
            else if(node.right != null && node.right.data.compareTo(node.data) < 0) {
                E temp = node.data;
                node.data = node.right.data;
                node.right.data = temp;

                node = node.right;
            }
            else
                break;*/

        }

        return data;
    }

    /**
     * queue'nun başındaki elemanı ver , exception fırlatır
     * @return object
     */
    @Override
    public Object element()
    {
        if(isEmpty())
            throw new NoSuchElementException();

        return root.data;
    }

    /**
     * element() ile aynı
     * @return object
     */
    @Override
    public Object peek() {
        if(size() == 0)
            return null;

        return root.data;

    }

    /**
     *
     * @param node baslanacak yer
     * @param item eklenecek item
     */
    private void insertLevelOrder(Node<E> node,E item){
        LinkedList<Node<E>> nodes = new LinkedList<>();

        nodes.add(root);
        while(!nodes.isEmpty()){
            Node<E> temp = nodes.removeFirst();

            if(temp.left != null)
                nodes.addLast(temp.left);
            else{
                temp.left = new Node<>(item);
                temp.left.parent = temp;
                lastItemAdded = temp.left;
                break;
            }

            if(temp.right != null)
                nodes.addLast(temp.right);
            else{
                temp.right = new Node<>(item);
                temp.right.parent = temp;
                lastItemAdded = temp.right;
                break;
            }

        }

        Node<E> last = lastItemAdded;
        while(last.parent != null && last.parent.data.compareTo(last.data) > 0) {
            E temp = last.parent.data;
            last.parent.data = last.data;
            last.data = temp;

            last = last.parent;

        }

    }

}
