import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/** Hash table implementation using open addressing.
 *  @author Koffman and Wolfgang
 */

public class HashTableOpen < K,V >
    implements HashMap < K, V > , Iterable<Map.Entry<K,V>> {
  // Data Fields
  private ArrayList<Map.Entry < K,V >>  table;
  private static final int START_CAPACITY = 101;
  private double LOAD_THRESHOLD = 0.75;
  private int numKeys;
  private int numDeletes;
  private final Entry < K, V > DELETED =
      new Entry < K, V > (null, null);

  // Constructor
  public HashTableOpen() {
    table = new ArrayList<>();
  }

  @Override
  public Iterator<Map.Entry<K, V>> iterator() {
    return table.iterator();
  }



  /** Contains key-value pairs for a hash table. */
  private static class Entry < K, V > implements Map.Entry<K,V>{

    /** The key */
    private K key;

    /** The value */
    private V value;

    /** Creates a new key-value pair.
        @param key The key
        @param value The value
     */
    public Entry(K key, V value) {
      this.key = key;
      this.value = value;
    }

    /** Retrieves the key.
        @return The key
     */
    public K getKey() {
      return key;
    }

    /** Retrieves the value.
        @return The value
     */
    public V getValue() {
      return value;
    }

    /** Sets the value.
        @param val The new value
        @return The old value
     */
    public V setValue(V val) {
      V oldVal = value;
      value = val;
      return oldVal;
    }
  }

  /** Returns the number of entries in the map */
  public int size() {
    return numKeys;
  }

  /** Returns true if empty */
  public boolean isEmpty() {
    return numKeys == 0;
  }

  /** Finds either the target key or the first empty slot in the
      search chain using linear probing.
      pre: The table is not full.
      @param key The key of the target object
      @return The position of the target or the first empty slot if
              the target is not in the table.
   */
  private int find(Object key) {
    // Calculate the starting index.

/*
    int index = key.hashCode() % table.size();
    if (index < 0)
      index += table.size(); // Make it positive.

      // Increment index until an empty slot is reached
      // or the key is found.
    while ( (table.get(index) != null)
           && (!key.equals(table.get(index).getKey()))) {
      index++;
      // Check for wraparound.
      if (index >= table.size())
        index = 0; // Wrap around.
    }
    */
  int i = 0;
  for(;i< table.size() && !(key.equals(table.get(i))) ;++i);

    return i;
  }

  /** Method get for class HashtableOpen.
      @param key The key being sought
      @return the value associated with this key if found;
              otherwise, null
   */
  public V get(Object key) {
    // Find the first table element that is empty
    // or the table element that contains the key.
    int index = find(key);

    // If the search is successful, return the value.
    if (table.get(index) != null)
      return table.get(index).getValue();
    else
      return null; // key not found.
  }

  /** Method put for class HashtableOpen.
      post: This key-value pair is inserted in the
            table and numKeys is incremented. If the key is already
            in the table, its value is changed to the argument
            value and numKeys is not changed. If the LOAD_THRESHOLD
            is exceeded, the table is expanded.
      @param key The key of item being inserted
      @param value The value for this key
      @return Old value associated with this key if found;
              otherwise, null
   */
  public V put(K key, V value) {
    // Find the first table element that is empty
    // or the table element that contains the key.
  /*  int index = find(key);

    // If an empty element was found, insert new entry.
    if (table.get(index) == null) {
      table.set(index,new Entry < K, V > (key, value));
      numKeys++;
      // Check whether rehash is needed.
      double loadFactor =
          (double) (numKeys + numDeletes) / table.size();
      /*if (loadFactor > LOAD_THRESHOLD)
        rehash();
     } */
      table.add(0,new Entry < K, V > (key, value));

      return value;

    // assert: table element that contains the key was found.
    // Replace value for this key.
    /*V oldVal = table.get(index).getValue();
    table.get(index).setValue(value);
    return oldVal;
    */
  }

  /** Expands table size when loadFactor exceeds LOAD_THRESHOLD
      post: The size of table is doubled and is an odd integer.
            Each nondeleted entry from the original table is
            reinserted into the expanded table.
            The value of numKeys is reset to the number of items
            actually inserted; numDeletes is reset to 0.
   */
  /*
  private void rehash() {
    // Save a reference to oldTable.
    ArrayList<Entry < K, V >>  oldTable = table;
    // Double capacity of this table.
    table = new Entry[2 * oldTable.size() + 1];

    // Reinsert all items in oldTable into expanded table.
    numKeys = 0;
    numDeletes = 0;
    for (int i = 0; i < oldTable.length; i++) {
      if ( (oldTable[i] != null) && (oldTable[i] != DELETED)) {
        // Insert entry in expanded table
        put(oldTable[i].key, oldTable[i].value);
      }
    }
  }
*/
  /** Remove the item with a given key value
   *  @param key The key to be removed
   *  @return The value associated with this key, or null
   *  if the key is not in the table.
   */
  public V remove(Object key) {
    int index = find(key);
    if (table.get(index) == null)
      return null;
    V oldValue = table.get(index).getValue();
    table.set(index,DELETED);
    numKeys--;
    return oldValue;
  }


}
