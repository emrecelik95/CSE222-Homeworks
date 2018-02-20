
import java.io.File;
import java.util.Scanner;

/**
 * Yönetici paneli veritabanını yönetmek için kullanılan sınıftır.
 * Library Staff objesi barındırır.
 * @author emre
 * @see  LibStaff
 */

public class StaffPanel implements IPanel{

    /**
     * Kütüphaneyi yönetecek staff
     */
    private LibStaff staff;
    
    /**
     * No parameter constructor
     */
    public StaffPanel(){
        this.staff = new LibStaff();
    }
    
    /**LibStaff parametesi alan constructor. 
     * Parametreyle staffı ilklendirir
     * @param staff Staff parametresi alır
     */
    public StaffPanel(LibStaff staff){
        this.staff = staff;
    }
    
    public void startSystem(){
        System.out.printf("Welcome '%s'.\n"
                        + "You can manage the system.\n",staff.getName());
        mainMenu();
    }
    
    public void mainMenu(){
        Scanner scanner = new Scanner(System.in);
        boolean wait = true;
        String option = "";
        LibManageData data = new LibManageData();
        
        System.out.println("----- MAIN PANEL -----  (q to exit)\n");
        
        while(wait){
            System.out.println("ENTER (1) TO ADD A NEW USER\n"
                             + "ENTER (2) TO ADD A NEW BOOK\n"
                             + "ENTER (3) TO REMOVE A BOOK\n"
                             + "ENTER (4) TO VIEW USERS\n"
                             + "ENTER (5) TO VIEW BOOKS");
            
            option = scanner.nextLine();
            if(option.equals("1"))
                addUserMenu();
            else if(option.equals("2"))
                addBookMenu();
            else if(option.equals("3"))
                removeBookMenu();
            else if(option.equals("4")){
                if(new File(data.getUsersFilename()).length() == 0)
                    System.out.println("There is no User!");
                else{
                    // TIMER STARTS
                    long startTime = System.nanoTime();
                    data.viewRecords(data.getUsersFilename());
                    // TIMER ENDS
                    long endTime = System.nanoTime();
                    System.out.printf("-->Method completed in '%s' nanoseconds<--\n",(endTime - startTime));
                }
            }
            else if(option.equals("5")){
                if(new File(data.getBooksFilename()).length() == 0)
                    System.out.println("There is no Book!");
                else{
                    long startTime = System.nanoTime();
                    // TIMER STARTS
                    data.viewRecords(data.getBooksFilename());
                    long endTime = System.nanoTime();
                    // TIMER ENDS
                    System.out.printf("-->Method completed in '%s' nanoseconds<--\n",(endTime - startTime));
                }
            }
            else if(option.toUpperCase().equals("Q"))
                return;
            else 
                System.out.println("WRONG CHOICE! PLEASE ENTER AGAIN");
        }
    }
    
    /**
     * Yeni kullanıcı eklemek için gerekli inputları alıp hata durumlarında
     * tekrar input alır , normal kulanıcı veya staff olarak veritabanına
     * işler.
     */
    private void addUserMenu(){
        Scanner scanner = new Scanner(System.in);
        String name = "" , password = "" , option = "";
        boolean wait = true;
      
        LibManageData data = new LibManageData();
        
        System.out.println("----- ADD USER ----- (q to return main panel)\n");

        
        while(wait){
            System.out.println("ENTER A NAME : ");
            name = scanner.nextLine();
            if(name.toUpperCase().equals("Q"))
                return;

            System.out.println("ENTER A PASSWORD : ");
            password = scanner.nextLine();
            if(password.toUpperCase().equals("Q"))
                return;
           
            System.out.println("ENTER AUTHORITY (1 for User , 2 for Staff) : ");
            option = scanner.nextLine();
            if(option.toUpperCase().equals("Q"))
                return;
            
            if(!data.checkName(name,data.getUsersFilename())){
                if(option.equals("1") || option.equals("2"))                   
                    wait = false;
                else
                    System.out.println("Please enter authority as 1 or 2");
            }
            else
                System.out.println("This name was taken,"
                                 + "please enter a different name");
            
        }
        
        int id = data.generateID(data.getUsersFilename());
        User user = null;
                    
        if(option.equals("1"))
            user = new LibUser(id,name,password);
        else
            user = new LibStaff(id,name,password);
        
        // TIMER STARTS
        long startTime = System.nanoTime();
        staff.addUser(data.getUsersFilename() , user); 
        long endTime = System.nanoTime();
        // TIMER ENDS
        System.out.println("User added successfully!");
        System.out.printf("-->Method completed in '%s' nanoseconds<--\n",(endTime - startTime));

    }
    /**
     * Yeni kitap eklemek için gerekli inputları alıp bunu veritabanına işler.
     */
    private void addBookMenu(){
        Scanner scanner = new Scanner(System.in);
        String name = "" , author = "" , publisher = "" , number = "";
        boolean wait = true;
       
        LibManageData data = new LibManageData();
        
        System.out.println("----- ADD BOOK ----- (q to return main panel)\n");
        
        
        while(wait){
            System.out.println("ENTER BOOK NAME : ");
            name = scanner.nextLine();
            if(name.toUpperCase().equals("Q"))
                return;
            
            System.out.println("ENTER AUTHOR : ");
            author = scanner.nextLine();
            if(author.toUpperCase().equals("Q"))
                return;
            
            System.out.println("ENTER PUBLISHER : ");
            publisher = scanner.nextLine();
            if(publisher.toUpperCase().equals("Q"))
                return;
            
            System.out.println("ENTER NUMBER : ");
            number = scanner.nextLine();
            if(number.toUpperCase().equals("Q"))
                return;
            
            while(!(new Scanner(number).hasNextInt())){
                System.out.println("Number is invalid.Enter again!");
                number = scanner.nextLine();
                
                if(number.toUpperCase().equals("Q"))
                    return;
            }
            
            if(data.checkName(name,data.getBooksFilename())){
                System.out.println("This book exists in the library."
                                 + "Please enter a new one!");
            }
            else
                wait = false;
        }
        
        // TIMER STARTS
        long startTime = System.nanoTime();
        int id = data.generateID(data.getBooksFilename());
        Book book = new Book(id,name,author,publisher,Integer.parseInt(number));
        staff.addBook(data.getBooksFilename(),book);
        long endTime = System.nanoTime();
        // TIMER ENDS
        System.out.println("Book added successfully!");
        System.out.printf("-->Method completed in '%s' nanoseconds<--\n",(endTime - startTime));
    }
    /**
     * Veritabanından eğer kaldırılması uygunsa istenen kitabı kaldırmak 
     * için gerekli inputları alır , kitabı kaldırır.
     */
    private void removeBookMenu(){
        String id = "";
        Scanner scanner = new Scanner(System.in);
        LibFileSystem file = new LibFileSystem();
        LibManageData data = new LibManageData();

        if(new File(data.getBooksFilename()).length() == 0){
            System.out.println("THERE IS NO BOOK TO REMOVE!");
            return;
        }
        DynamicArray<String> list = file.readFileIntoArray(data.getBooksFilename());

        if(list.size() == 1){
            System.out.println("THERE IS NO BOOK TO REMOVE!");
            return;
        }
            
        System.out.println("----- REMOVE BOOK ----- (q to return main panel)\n");
        
        System.out.println("ENTER BOOK ID : ");
        id = scanner.nextLine();
        if(id.toUpperCase().equals("Q"))
            return;
            
        while(!(new Scanner(id).hasNextInt()) || 
              !data.checkID(id,data.getBooksFilename()))
        {
                System.out.println("ID is invalid or doesn't exist."
                                 + "Enter a different ID!");
                id = scanner.nextLine();
                if(id.toUpperCase().equals("Q"))
                    return;
        }
        while(data.checkBorrowing(id)){
            System.out.println("This book can't be removed ,\n "
                    + "a user has borrowed this book!");
            id = scanner.nextLine();
                if(id.toUpperCase().equals("Q"))
                    return;
        }
        // TIMER STARTS
        long startTime = System.nanoTime();
        Book book = data.getBook(Integer.parseInt(id));
        staff.removeBook(data.getBooksFilename(),book);
        long endTime = System.nanoTime();
        // TIMER ENDS
        System.out.println("Book removed successfully!");
        System.out.printf("-->Method completed in '%s' nanoseconds<--\n",(endTime - startTime));
    }
}
