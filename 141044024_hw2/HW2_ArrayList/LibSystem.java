import java.util.Scanner;

/**
 * Kutuphanenin ana sistemi
 * @see IPanel
 * @author emre
 */
public class LibSystem implements IPanel{
    
    /**
     * No parameter constructor
     */
    public LibSystem(){}

    public void startSystem(){
        System.out.println("----- WELCOME TO LIBRARY SYSTEM -----\n"
                + "TESTING USER STAFF NAME : 'staff' , PASSWORD : 'staff'");
        mainMenu();
    }
    
    
    public void mainMenu(){
        Scanner scanner = new Scanner(System.in);//veri okumak icin gerekli
        String option = "";                      // input
        boolean wait = true;                     // dongu kosulu
        
        // menu secenekleri
        System.out.println("----- MAIN MENU -----\n"
                + "CHOICES ARE GIVEN BELOW , ENTER YOUR CHOICE TO\n"
                + "ENTER (1) TO LOG IN AS LIBRARY USER\n"
                + "ENTER (2) TO LOG IN AS LIBRARY STAFF\n"
                + "ENTER (q) TO QUIT\n");
        
        // inputlara göre gerekli tipte kullanıcı icin method çağrılır
        while(wait){
            option = scanner.next();
            if(option.equals("1")){
                loginMenu("User");
                wait = false;
            }
            else if(option.equals("2")){
                loginMenu("Staff");
                wait = false;
            }
            else if(option.toUpperCase().equals("Q")) // çıkış inputu
                return;
            else                                      // hatalı input
                System.out.println("Wrong Choice!Enter Again");
        }
        
    }
    
    /**
     * Kullanıcıdan adını ve şifresini input olarak alıp , yetkiye göre
     * downcasting yapıp gerekli kullanıcı panelini çağırır.
     * 
     * @param authority kullanıcının yetkisi 
     */
    
    private void loginMenu(String authority){
        Scanner scanner = new Scanner(System.in);
        boolean wait = true , successed = false;
        String name = "" , password = "";
        
        System.out.println("----- SIGN IN -----   (q to return to main menu)");
        
        LibManageData data = new LibManageData();//veritabanı yönetimi sağlar
        User user = null;   //daha sonra downcasting yapılacak
                            // iki tip kullanıcı olduğu için base kullanıldı
        
        while(wait){
            System.out.println("Enter your name : ");
            name = scanner.nextLine();
            if(name.toUpperCase().equals("Q"))
                wait = false;
            else{
                System.out.println("Enter your password : ");
                password = scanner.nextLine();
                if(password.toUpperCase().equals("Q")){
                    wait = false;
                }
                else if(data.getUser(name,password,authority) != null){
                    user = data.getUser(name,password,authority);
                    wait = false;
                    successed = true;
                }
                else
                    System.out.println("Name or Password is wrong!");
            }
        }       
        
        if(successed){  // giriş yapıldıysa yetkiye göre paneli başlat
           System.out.printf("Signed in as '%s'!\n",authority);
           if(authority == "User"){
               LibUser libUser = (LibUser) user;
               UserPanel panel = new UserPanel(libUser);
               panel.startSystem();
           }
           else if(authority == "Staff"){
               LibStaff libStaff = (LibStaff) user;
               StaffPanel panel = new StaffPanel(libStaff);
               panel.startSystem();
           }
        }
         // çıkış durumunda ana menüye dön
        else if(name.toUpperCase().equals("Q") || password.toUpperCase().equals("Q"))
            mainMenu();
        
        
    }

    
}
