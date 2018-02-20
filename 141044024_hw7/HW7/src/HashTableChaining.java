import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by syucer on 4/24/2017.
 */
public class HashTableChaining<K, V> implements HashMap<K, V> {
    /** The table */
    //private HashTableOpen<K,V>[] table2;
    private HashTableOpen<K, V>[] table;
    private int numKeys;
    private static final int CAPACITY = 101;
    private static final int LOAD_THRESHOLD = 3;
    //Do not forget you can use more class and methods to do this homework,
    // this project gives you an initial classes an methods to see easily
    //....
    //.... other class members

    /**
     * no param constructor
     */
    public HashTableChaining() {
        table = new HashTableOpen[CAPACITY];
    }

    /**
     * getter
     * @param key verilecek key
     * @return value
     */
    @Override
    public V get(Object key) {
        int index = key.hashCode() % table.length;
        if(index < 0)
            index += table.length;
        if(table[index] == null)
            return null;

        for(Map.Entry<K, V> next : table[index])
            if(next.getKey().equals(key))
                return next.getValue();

        return null;
    }

    /**
     * yeni eleman ekleme
     * @param key eklenecek key
     * @param value eklenecek value
     * @return eski value
     */
    @Override
    public V put(K key, V value) {
        int index = key.hashCode() % table.length;
        if(index < 0)
            index += table.length;
        if(table[index] == null)
            table[index] = new HashTableOpen<>();

        for(Map.Entry<K,V> next : table[index])
            if(next.getKey().equals(key)){
                V oldValue = next.getValue();
                next.setValue(value);
                return oldValue;
            }

        table[index].put(key,value);
        numKeys++;
        if(numKeys > (LOAD_THRESHOLD * table.length))
            rehash();

        return null;
    }

    /**
     * yeniden yer alır
     */
    private void rehash() {
        HashTableOpen<K, V>[] oldTable = table;
        table = new HashTableOpen[2 * oldTable.length + 1];

        numKeys = 0;
        for(int i = 0 ; i < oldTable.length ; i++)
            for(Map.Entry<K,V> next : oldTable[i])
                if(next != null)
                    put(next.getKey(),next.getValue());
    }

    /**
     * silme methodu
     * @param key silinecek key
     * @return eski value dondurur
     */
    @Override
    public V remove(Object key) {
        int index = key.hashCode() % table.length;
        if(index < 0)
            index += table.length;
        if(table[index] == null)
            return null;

        for(Map.Entry<K,V> next : table[index])
            if (next.getKey().equals(key)) {
                V oldValue = next.getValue();
                table[index].remove(next);
                numKeys--;
                if (table[index].isEmpty())
                    table[index] = null;
                return oldValue;
            }


        return null;
    }

    /**
     * keylerin sayisi
     * @return int
     */
    @Override
    public int size() {
        return numKeys;
    }

    /**
     * bols olup olmadıgını verir
     * @return booelan
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * Entry sinifi
     * @param <K> generic
     * @param <V> generic
     */
    private static class Entry<K,V> implements Map.Entry<K,V>{

        private final K key;
        private V value;

        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V val) {
            V oldVal = value;
            value = val;
            return oldVal;
        }
    }

}
