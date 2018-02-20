import java.io.File;
import java.util.ArrayList;

/**
 * Staff sınıfı Userın member ve getter setterlarına ve 
 * kütüphane veritabanını yönetmek için gerekli methodlara sahiptir.
 * @author emre
 * @see User
 */
public class LibStaff extends User{
    /**
     * No parameter constructor
     */
    public LibStaff(){}
    
    /**
     * Base sınıfın constructorunu çağırıp ilklendirme yapan constructor
     * @param id id ilklendirir
     * @param name ismi ilklendirir
     * @param password şifreyi ilklendirir
     */
    public LibStaff(int id,String name,String password){
        super(id,name,password,"Staff");
    }
    
    /**
     * Kütüphaneye kitap eklemek için gerekli dosya ismini alıp o dosyaya
     * verilen kitabı ekler.
     * @param filename ekleneceği dosya adı
     * @param book eklenecek kitap
     */
    public void addBook(String filename,Book book){         
            LibFileSystem file = new LibFileSystem();
                       
            String record = "";
            ArrayList<String> records = new ArrayList<String>();
            
            if(new File(filename).length() == 0){
                record += ("ID");
                record += (",");
                record += ("NAME");
                record += (",");
                record += ("AUTHOR");
                record += (",");
                record += ("PUBLISHER");
                record += (",");
                record += ("NUMBER");
                
                records.add(record);

            }
            record = "";
            record += (String.valueOf(book.getId()));
            record += (",");
            record += (book.getName());
            record += (",");
            record += (book.getAuthor());
            record += (",");
            record += (book.getPublisher());
            record += (",");
            record += (String.valueOf(book.getNumber()));
      
            records.add(record);
            
            file.writeArrayIntoFile(filename, records, true);
            
    }
    /**
     * Kütüphaneden (verilen dosyadan) istenen kitabı kaldırır.
     * @param filename hangi dosyadan silineceği
     * @param book  silinecek kitap
     */
    public void removeBook(String filename,Book book){
        LibFileSystem file = new LibFileSystem();
        ArrayList<String> bookList = file.readFileIntoArray(filename);
        String wanted = "";
        
        for(String line : bookList)
            if(bookList.indexOf(line) != 0)
                if(line.split(",")[0].equals(String.valueOf(book.getId())))
                    wanted = line;
                
        if(wanted == ""){
            System.out.println("There is no book that has this id!");
        }
        else{
            bookList.remove(bookList.indexOf(wanted));
            file.writeArrayIntoFile(filename, bookList, false);
        }
        
    }
    
    /**
     * Kütüphane kullanıcılarına(verilen dosyaya) istenen kullanıcıyı
     * ekler.
     * @param filename ekleneceği dosya adı
     * @param user eklenecek user
     */
    public void addUser(String filename , User user){
        LibFileSystem file = new LibFileSystem();
                       
        String record = "";
        ArrayList<String> records = new ArrayList<String>();

            if(new File(filename).length() == 0){
                record += ("ID");
                record += (",");
                record += ("NAME");
                record += (",");
                record += ("PASSWORD");
                record += (",");
                record += ("BOOKID");
                record += (",");
                record += ("AUTHORITY");

                records.add(record);
            }
            record = "";
            
            record += (String.valueOf(user.getId()));
            record += (",");
            record += (user.getName());
            record += (",");
            record += (user.getPassword());
            record += (",");
            
            if(user.getAuthority() == "User"){
                LibUser libUser = (LibUser) user;
                record += (String.valueOf(libUser.getBookId()));
            }
            else
                record += (String.valueOf(-1));
            
            record += (",");
            record += (String.valueOf(user.getAuthority()));

            records.add(record);
            
            file.writeArrayIntoFile(filename, records, true);
    }
    
}
