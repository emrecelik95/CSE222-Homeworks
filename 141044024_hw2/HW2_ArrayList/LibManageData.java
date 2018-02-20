import java.io.File;
import java.util.ArrayList;


public class LibManageData implements LibData{
    /**
     * Kütüphanenin kitaplarını tutacak olan dosyanın ismi.
     */
    private final String booksFilename;
    /**
     * Kütüphanenin kullanıcılarını tutacak olan dosyanın ismi , 
     */
    private final String usersFilename;
    
    public LibManageData(){
        this.booksFilename = "books.csv";
        this.usersFilename = "users.csv";
    }
    
    public LibManageData(String booksFilename,String usersFilename){
        this.booksFilename = booksFilename;
        this.usersFilename = usersFilename;
    }
    
    public final String getBooksFilename(){
        return this.booksFilename;
    }
    
    public final String getUsersFilename(){
        return this.usersFilename;
    }
    
    @Override
    public User getUser(String name, String password, String authority) {
        LibFileSystem file = new LibFileSystem();
        ArrayList<String> userList = file.readFileIntoArray(getUsersFilename());
        
        for(String line : userList){
            String[] rec = line.split(",");

            if(rec[1].equals(name) && rec[2].equals(password) &&
                                      rec[rec.length - 1].equals(authority))
            {
                if(authority == "User"){
                    LibUser libUser = new LibUser(Integer.parseInt(rec[0]),
                    rec[1],rec[2],Integer.parseInt(rec[3]));

                    return libUser;
                }
                else if(authority == "Staff"){                    
                    LibStaff libStaff = new LibStaff(Integer.parseInt(rec[0]),
                    rec[1],rec[2]);

                    return libStaff;
                }
                
            }
                 
        }
        
        return null;
    }

    @Override
    public Book getBook(int id) {
        LibFileSystem file = new LibFileSystem();
        ArrayList<String> bookList = file.readFileIntoArray(getBooksFilename());
        
        for(String line : bookList){
            String[] rec = line.split(",");
            if(rec[0].equals(String.valueOf(id)))
            {
                Book book = new Book(Integer.parseInt(rec[0]),rec[1],rec[2],
                                     rec[3],Integer.parseInt(rec[4]));
                System.out.println(book.getId());
                return book;
            }
        }
        return null;
    }
    
    @Override
    public int generateID(String filename){
        LibFileSystem file = new LibFileSystem();            
        
        if(new File(filename).length() == 0)
            return 1;
        
        
        ArrayList<String> list = file.readFileIntoArray(filename);
        if(list.size() == 1)
            return 1;
        
        String id = list.get(list.size() - 1).split(",")[0];
        return Integer.parseInt(id) + 1;
    }
    
    @Override
    public boolean checkName(String name,String filename){
        if(new File(filename).length() == 0)
            return false;
        
        LibFileSystem file = new LibFileSystem();         
        ArrayList<String> list = file.readFileIntoArray(filename);
        
        for(String rec : list)
            if(rec.split(",")[1].equalsIgnoreCase(name))
                return true;
            
        return false;
    }
    
    @Override
    public boolean checkID(String id,String filename){
        LibFileSystem file = new LibFileSystem(); 
        ArrayList<String> list = file.readFileIntoArray(filename);
        for(String rec : list)
            if(rec.split(",")[0].equalsIgnoreCase(id))
                return true;
            
        return false;
    }
    
    @Override
    public boolean checkBorrowing(String bookId){
        LibFileSystem file = new LibFileSystem(); 
        ArrayList<String> list = file.readFileIntoArray(getUsersFilename());
        
        for(String rec : list)
            if(rec.split(",")[3].equalsIgnoreCase(bookId))
                return true;
        
        return false;
    }

    @Override
    public void viewRecords(String filename) {
        LibFileSystem file = new LibFileSystem(); 
        ArrayList<String> list = file.readFileIntoArray(filename);
        if(new File(filename).length() == 0 || list.size() == 1){
            System.out.println("There is no record!");
            return;
        }
        
        for(String rec : list){
            String[] cell = rec.split(",");
            int i = 0;
            for(String c : cell){
                if(i == 0)
                    System.out.printf("|\t%-15s|\t",c);
                else
                    System.out.printf("%-15s|\t",c);
                
                ++i;
            }
            System.out.println();
        }
    }
    
    @Override
    public void viewRecord(String filename,int id){
        LibFileSystem file = new LibFileSystem(); 
        ArrayList<String> list = file.readFileIntoArray(filename);
        if(new File(filename).length() == 0 || list.size() == 1){
            System.out.println("There is no record!");
            return;
        }
        
        for(String rec : list){
            String[] cells = rec.split(",");
            int i = 0;
            if(cells[0].equals(String.valueOf(id)) || list.indexOf(rec) == 0)
            {
                for(String c : cells){
                    if(i == 0)
                        System.out.printf("|\t%-15s|\t",c);
                    else
                        System.out.printf("%-15s|\t",c);
                    ++i;
                }
                
                System.out.println();
            }
        }
    }
    
    @Override
    public void updateUser(User user){
        LibFileSystem file = new LibFileSystem(); 
        ArrayList<String> list = file.readFileIntoArray(getUsersFilename());
        
        for(String rec : list)
            if(rec.split(",")[0].equalsIgnoreCase(String.valueOf(user.getId()))){
                String newUser = user.getId() + "," + user.getName() + "," +
                                 user.getPassword();
                if(user.getAuthority() == "User")
                    newUser += "," + ((LibUser)user).getBookId();
                else
                    newUser += "," + "-1";
                
                newUser += "," + user.getAuthority();
                list.set(list.indexOf(rec), newUser);
                file.writeArrayIntoFile(getUsersFilename(), list, false);
                
                return;
            }
    }

    @Override
    public void updateBook(Book book) {
        LibFileSystem file = new LibFileSystem(); 
        ArrayList<String> list = file.readFileIntoArray(getBooksFilename());
        
        for(String rec : list)
            if(rec.split(",")[0].equalsIgnoreCase(String.valueOf(book.getId()))){
            String newBook = book.getId() + "," + book.getName() + "," +
                             book.getAuthor() + "," + book.getPublisher() +
                             "," + book.getNumber();
            
            list.set(list.indexOf(rec), newBook);
            file.writeArrayIntoFile(getBooksFilename(), list, false);
            
            return;
        }
    }
    
    
}
