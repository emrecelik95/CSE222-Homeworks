
import java.io.File;
import java.util.Scanner;

/**
 * Kullanıcı paneli kullanıcının kitap bilgisini yönetmek için kullanılan
 * sınıftır.
 * Library User objesi barındırır.
 * @author emre
 * @see  LibUser
 */
public class UserPanel implements IPanel{
    /**
     * Kitap bilgisini yönetmek için kullanılan Normal Kullanıcı
     */
    private LibUser user;
    /**
     * No parameter constructor
     */
    public UserPanel(){
        this.user = new LibUser();
    }
    /**
     * LibUser parametresi alan constructor.
     * Parametreyle kullanıcıyı ilklendir.
     * @param user User objesi alır
     */
    public UserPanel(LibUser user){
        this.user = user;
    }
    
    @Override
    public void startSystem() {
        System.out.printf("Welcome '%s'.\n"
                        + "You can manage your system.\n",user.getName());
        mainMenu();
    }

    @Override
    public void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean wait = true;
        String option = "";
        LibManageData data = new LibManageData();
        
        System.out.println("----- MAIN PANEL -----  (q to exit)\n");
        
        while(wait){
            System.out.println("ENTER (1) TO BORROW A BOOK\n"
                             + "ENTER (2) TO RETURN BOOK\n"
                             + "ENTER (3) TO VIEW BOOKS\n"
                             + "ENTER (4) TO VIEW YOUR BOOK");
            
            option = scanner.nextLine();
            if(option.equals("1"))
                borrowBookMenu();
            else if(option.equals("2")){
                if(user.getBookId() == -1)
                    System.out.println("YOU HAVE NO BOOK!");
                else
                    returnBookMenu();
            }
                
            else if(option.equals("3")){
                if(new File(data.getBooksFilename()).length() == 0)
                    System.out.println("There is no Book!");
                    else
                        data.viewRecords(data.getBooksFilename());
            }
            else if(option.equals("4")){
                if(user.getBookId() == -1)
                    System.out.println("YOU HAVE NO BOOK");
                    else
                        viewMyBook();
            }
            else if(option.toUpperCase().equals("Q"))
                return;
            else 
                System.out.println("WRONG CHOICE! PLEASE ENTER AGAIN");
        }
    }
    /**
     * Kullanıcının kitap ödünç alması için gereklş method.
     * Kitabın id sini girmesi yeterlidir.
     * Kullanıcı kitap aldığında hem kullanıcı hem de kitap veritabanında
     * güncellenir.
     */
    private void borrowBookMenu(){
        Scanner scanner = new Scanner(System.in);
        String id = "";
       
        LibManageData data = new LibManageData();
        
        if(user.getBookId() != -1){
           System.out.println("YOU CANNOT BORROW A BOOK AT THE MOMENT , "
                            + "YOU HAVE ONE BOOK , PLEASE RETURN IT THEN BORROW!");
           return;
        }
           
        System.out.println("----- BORROW BOOK ----- (q to return main panel)\n");
        
        System.out.println("ENTER BOOK ID : ");
        id = scanner.nextLine();
        if(id.equalsIgnoreCase("Q"))
            return;

        while(!(new Scanner(id).hasNextInt()) || 
              !data.checkID(id,data.getBooksFilename()))
        {
            System.out.println("ID is invalid or doesn't exist."
                             + "Enter a different ID!");
            id = scanner.nextLine();
            if(id.equalsIgnoreCase("Q"))
                return;
        }
        while(data.checkBorrowing(id)){
            System.out.println("This book can't be borrowed ,\n "
                    + "a user has borrowed this book!");
            id = scanner.nextLine();
            if(id.equalsIgnoreCase("Q"))
                return;
        }
        Book book = data.getBook(Integer.parseInt(id));
        if(book.getNumber()== 0) {
            System.out.println("THERE IS NOT EVEN ONE FROM THIS BOOK!");
            return;
        }
        
        user.borrowBook(book);
        System.out.println("BOOK BORROWED SUCCESSFULLY!");
    }
    
    /**
     * Kullanıcının kitabı geri vermesi için gerekli method.
     * Kitap geri verildiğinde kullanıcı ve kitap veritabanında güncellenir.
     */
    private void returnBookMenu(){
        String option = "";
        boolean wait = true;
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("----- RETURN BOOK ----- (q to return main panel)\n");
        
        System.out.println("Do you want to return book? (1) yes , (0) no");
        
        
        while(wait){
            option = scanner.nextLine();
            if(option.equalsIgnoreCase("Q") || option.equalsIgnoreCase("0"))
                return;
            else if(option.equals("1"))
                wait = false;
            else
                System.out.println("Please enter (1) or (0) or (q) to exit!");
        }
        LibManageData data = new LibManageData();
        Book book = data.getBook(user.getBookId());
        user.returnBook(book);
        
        System.out.println("RETURNED BOOK SUCCESSFULLY!");
    }
    /**
     * Kullanıcının sahip olduğu kitabı görüntüler.
     */
    private void viewMyBook(){
        LibManageData data = new LibManageData();
        
        System.out.println("----- MY BOOK ----- (q to return main panel)\n");
        
        data.viewRecord(data.getBooksFilename(),user.getBookId());
        
    }
    
    
}
