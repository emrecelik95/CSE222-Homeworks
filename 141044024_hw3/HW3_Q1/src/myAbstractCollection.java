
import java.util.AbstractCollection;
import java.util.Iterator;
/**
 *
 * @author emre
 * @param <E>  generic
 */
public abstract class myAbstractCollection<E> extends AbstractCollection<E>{
    /**
     * 
     * ilk objeye ikinci objeyi ekler
     * abstractcolleciton dan gelen add fonksiyonunu kullanır
     * @param obj eklenecek nesne
     */
    public void appendAnything(myAbstractCollection<E> obj){
        Iterator<E> itr = obj.iterator();
        while(itr.hasNext())
            this.add(itr.next());
    }
    
}