import java.util.Iterator;

/**
 * Stringleri linkedlistten tutan bir string builder.Herhangi bir tip eklenebilir.
 * @author emre
 */
public class myStringBuilder {
    
    /**
     * stringleri tutacak olan linkedlist
     */
    private MySingleLinkedList<String> linkedList;
    
    /**
     * no parameter constructor
     */
    public myStringBuilder(){
        linkedList = new MySingleLinkedList<String>();
    }
    /**
     * yeni string ekle(obje)
     * @param item int ,string , bool , double gibi tipler için
     * @return bu nesneyi dondur
     */
    public myStringBuilder append(Object item){
        linkedList.add(item.toString());
        return this;
    }
    /**
     * char array ekler
     * @param str eklenecek char array
     * @return bu nesne
     */
    public myStringBuilder append(char[] str){
        linkedList.add(String.valueOf(str));
        return this;
    }
    /**
     * 
     * @param str char array
     * @param offset konumu(başlayacağı yer)
     * @param size boyutu(kaç karakter alacağı)
     * @return bu nesne
     */
    public myStringBuilder append(char[] str , int offset , int size){
        linkedList.add(String.valueOf(str, offset, size));
        return this;
    }
    /**
     * 
     * @param str charsequence interface tipinde(implement eden)class objesi
     * @param start başlangıc indexi
     * @param end bitiş indexi
     * @return bu nesne
     */
    public myStringBuilder append(CharSequence str , int start , int end){
        String temp = String.valueOf(str);
        linkedList.add(temp.substring(start, end));
        return this;
    }
    /**
     * linkedlist in get methodunu kullanıp döngüyle stringi verir.
     * @return toplam string
     */
    public String toStringWithGetIndex(){
        String out = "";
        out += "[";
        for(int i = 0 ; i < linkedList.size() ; ++i){
            out += linkedList.get(i).toString();
            if(i != linkedList.size() - 1)
                out += ", ";
        }
        out += "]";
        return out;
    }
    /**
     * linkedlistin iteratorunu kullanıp stringi verir.
     * @return toplam string
     */
    public String toStringWithIterator(){
        String out = "";
        Iterator<String> itr = linkedList.iterator();
        
        out += "[";
        while(itr.hasNext()){
            out += itr.next();
            if(itr.hasNext())
                out += ", ";
        }
        
        out += "]";        
        return out;
    }
    /**
     * linkedlistin kendi tostringini verir.
     * @return linkedlistin toStringi
     */
    public String toStringLinkedList(){
        return linkedList.toString();
    }
    
    
}
