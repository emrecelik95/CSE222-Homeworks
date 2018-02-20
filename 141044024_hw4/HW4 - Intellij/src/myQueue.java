import java.util.Queue;

/**
 * KWLinkedList sınıfını extend eder.Bir queue sınıfıdır. 
 * @author emre
 * @param <E> generic
 */
public class myQueue<E> extends KWLinkedList<E> {
   /**
    * Sona eleman ekle
    * @param item eklenecek eleman
    */
    public void add(E item){
        super.addLast(item);
    }
    /**
     * Bu objeyi ters cevir.
     */
    public void reverse() {

        int indexL = 0; // soldan saga git
        int indexR = size() - 1; // sagdan sola git

        while(indexL < indexR) // ortada dur
        {
            E temp = get(indexL); 

            set(indexL,get(indexR));
            set(indexR,temp);

            ++indexL;
            --indexR;
        }

    }
    /**
     * Verilen Queue objesini ters cevir.
     * @param queue Queue objesi
     */
    public static void reverseQueue(Queue queue){

        if(queue.isEmpty()) // base case eleman kalmama durumu 
            return;

        Object temp = queue.poll(); // elemanı cıkar ve bunu temp te tut
        reverseQueue(queue);        // recursice cagır

        queue.add(temp);            // queue boşalınca sildiklerini ekle(tersten)
                                    // son silinen ilk eklenmiş oluyor.
    }


}
