
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
       String filename = "numbers.txt";
       myStringBuilder stb = new myStringBuilder();
       long startTime , endTime;
       String analysis;
       try
       {          
            FileWriter fileWriter = null;     
            
            if(!new File(filename).exists()){
                System.err.println("File doesn't exist!");
                return;
            }
            FileReader fileReader = new FileReader(filename);
            Scanner sc = new Scanner(fileReader);
            
            while(sc.hasNextInt())
                stb.append(sc.nextInt());
            fileReader.close();
            
            fileWriter = new FileWriter("result1.txt");
            startTime = System.nanoTime();/////////////////////////////////
            fileWriter.write(stb.toStringWithGetIndex());
            endTime = System.nanoTime();///////////////////////////////////
            analysis = "\n    toStringWithGetIndex() çalışma süresi : " + 
                    (double)(endTime - startTime) / 1000000000.0 + " sn";
            System.out.println(analysis);
            fileWriter.flush();
            ////////////////////////////////////////////////////////////////////
            fileWriter = new FileWriter("result2.txt");
            startTime = System.nanoTime();
            fileWriter.write(stb.toStringWithIterator());
            endTime = System.nanoTime();///////////////////////////////////
            analysis = "\n    toStringWithIterator() çalışma süresi : " + 
                    (double)(endTime - startTime) / 1000000000.0 + " sn";
            fileWriter.flush();
            System.out.println(analysis);
            ////////////////////////////////////////////////////////////////////
            fileWriter = new FileWriter("result3.txt");
            startTime = System.nanoTime();
            fileWriter.write(stb.toStringLinkedList());
            endTime = System.nanoTime();///////////////////////////////////
            analysis = "\n    toStringLinkedList() çalışma süresi : " + 
                    (double)(endTime - startTime) / 1000000000.0 + " sn";
            System.out.println(analysis);
            
            fileWriter.close();
            
       } catch(IOException ex) {
            System.out.println("Reading file failed!");
       }
       
        
    }
    
}
