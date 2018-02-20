
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;



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

        list.add("aaa");
        list.add("bbb");
        list.add("cee");
        list.add("dee");
        list.add("5");
        list.add("7.5");
        
        System.out.println(list.toString());
        
        System.out.println();
        
        System.out.println(list.reverseToString());
        
        System.out.println();
    }
    
}
