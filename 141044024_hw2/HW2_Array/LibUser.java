/**
 * Normal kullanıcı sınıfı.
 * Kitap alıp geri verebilir.User sınıfından türemiştir.
 * @author emre
 * @see User
 */
public class LibUser extends User{

    /**
     * Kullanıcının ödünç aldığı kitabın ID'sidir.
     * Eğer default(-1) ise kitap almamış anlamına gelir.
     */
    private int bookId;
    
    /**
     * No parameter constructor
     */
    public LibUser(){}
    
    /**
     * Parametrelerle kullanıcıyı ilklendirir.
     * bookId ye default olarak -1 atar;
     * 
     * @param id    idyi ilklendirir
     * @param name  ismi ilklendirir
     * @param password şifreyi ilklendirir
     */
    public LibUser(int id,String name,String password){
        super(id,name,password,"User");
        this.bookId = -1;
    }
    
    /**
     * Parametrelerle kullanıcıyı ilklendirir.
     * @param id     idyi ilklendirir
     * @param name   ismi ilklendirir
     * @param password  şifreyi ilklendirir 
     * @param bookId  kitap id ilklendirir
     */
    public LibUser(int id,String name,String password,int bookId){
        super(id,name,password,"User");
        this.bookId = bookId;
    }
   
    /**
     * bookId getter
     * @return sahip olunan kitabın idsini dondurur
     */
    public int getBookId(){
        return bookId;
    }
    
    /**
     * bookId setter
     * @param bookId sahip olunan kitap id sini ilklendirir
     */
    public void setBookId(int bookId){
        this.bookId = bookId;
    }
    
    /**
     * Parametre olarak gelen kitabı ödünç alır.Kullanıcı ve kitap 
     * veritabanında güncellenir.
     * @param book  verilen kitap ödünc alınır
     */
    public void borrowBook(Book book){
       if(book.getNumber() > 0 && getBookId() == -1){
           LibManageData data = new LibManageData();
        
           setBookId(book.getId());
           book.setNumber(book.getNumber() - 1);
           
           data.updateBook(book);
           data.updateUser(this);
       }
    }
    /**
     * Parametre olarak gelen kitabı geri verir(Çoklu kitap sistemine açık
     * olması için parametre koyuldu).Kullanıcı ve kitap veritabanında güncellenir.
     * @param book  sahip olunan kitap geri verilir
     */
    public void returnBook(Book book){
        LibManageData data = new LibManageData();
        
        setBookId(-1);
        book.setNumber(book.getNumber() + 1);
        
        data.updateBook(book);
        data.updateUser(this);
    }
    
    
}
