/**
 * Stack Interface'i
 * @author emre
 * @param <E> generic 
 */
public interface StackInterface<E> {
    /**
     * yeni item ekle
     * @param item eklenecek eleman
     */
    public void push(E item);
    /**
     * sondan siler
     * @return generic
     */
    public E pop();
    /**
     * eleman sayısı dondurur
     * @return super.size
     */
    public int size();
    /**
     * Bos olup olmadıgını verir.
     * @return boolean
     */
    public boolean isEmpty();
}
