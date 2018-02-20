/**
 * Kitap sınıfı , kitaba ait bilgileri tutar , 
 * bu bilgilerin getter setter'larına sahiptir.
 * @author emre
 */
public class Book {
    
    /**
     * Kitap id si(eşsiz)
     */
    private int id;
    /**
     * Kitabın ismi
     */
    private String name;
    /**
     * Kitabın yazarı
     */
    private String author;
    /**
     * Kitabın yayınevi
     */
    private String publisher;
    /**
     * Kitabın adedi
     */
    private int number;
    
    /**
     * No parameter constructor
     */
    public Book(){}
    
    /**
     * Parametrelerle kitabı ilklendirir.
     * @param id   kitap id si
     * @param name kitap ismi
     * @param author yazar
     * @param publisher yayınevi
     * @param number kitap adedi
     */
    public Book(int id,String name,String author,String publisher,int number){
        this.id = id;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.number = number;
    }
    /**
     * id getter
     * @return id - numara
     */
    public int getId(){
        return id;
    }
    /**
     * id setter
     * @param id numara(eşsiz)
     * 
     */
    public void setId(int id){
        this.id = id;
    }
    /**
     * kitap ismi getter
     * @return name - isim
     */
    public String getName(){
        return name;
    }
    /**
     * kitap ismi setter
     * @param name isim
     */
    public void setName(String name){
        this.name = name;
    }
    /**
     * kitabın yazarı getter
     * @return author - yazar
     */
    public String getAuthor(){
        return author;
    }
    /**
     * kitabın yazarı setter
     * @param author yazar
     */
    public void setAuthor(String author){
        this.author = author;
    }
    /**
     * kitabın yayınevi getter
     * @return publisher - yayınevi
     */
    public String getPublisher(){
        return publisher;
    }
    /**
     * kitabın yayınevi setter 
     * @param publisher  yayınevi
     */
    public void setPublisher(String publisher){
        this.publisher = publisher;
    }
    /**
     * kitabın adedi getter
     * @return number - kitap adedi
     */
    public int getNumber(){
        return number;
    }
    /**
     * kitabın adedi setter
     * @param number kitap adedi
     */
    public void setNumber(int number){
        this.number = number;
    }
}
