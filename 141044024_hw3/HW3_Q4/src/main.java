
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author emre
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MySingleLinkedList<String> list = new MySingleLinkedList<String>();

        try {
            FileWriter file = new FileWriter("test.txt");
            for(int i = 0 ; i < 100 ; ++i)
                file.write(new Integer(i).toString() + " ");
            file.close();
            
            FileReader reader = new FileReader("test.txt");
            Scanner sc = new Scanner(reader);
            while(sc.hasNextInt())
                list.add(new Integer(sc.nextInt()).toString());
            reader.close();
            
            System.out.println(list);
            
            for(int i = 0 ; i < 50 ; ++i)
                list.remove(0);
            
            System.out.println(list);
            
            
            for(int i = 0 ; i < 100 ; ++i)
                 list.add(new Integer(i).toString());
             
            
            System.out.println(list);
            
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
