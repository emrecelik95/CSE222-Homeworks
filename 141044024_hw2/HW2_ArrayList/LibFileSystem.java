
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Kütüphane dosyalarını yönetmek , okumak , yazmak için kullanılan sınıf.
 * @author emre
 */
public class LibFileSystem {
    
    /**
     * No parameter constructor
     */
    public LibFileSystem(){}
    
    /**
     * Verilen dosya ismini kullanıp o dosyayı okur ve her satır bir eleman
     * olacak şekilde ArrayList'e aktarıp bu ArrayList'i return eder.
     * @param filename okunacak dosya
     * @return arr dondurulecek arraylist
     */
    public ArrayList<String> readFileIntoArray(String filename){
       ArrayList<String> arr = new ArrayList<String>();
       try
       {          
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
                      
            while((line = bufferedReader.readLine()) != null){
                arr.add(line);
            }
       } catch(IOException ex) {
            System.out.println("Reading file failed!");
        }
       
       return arr;
    }
    /**
     * Verilen ArrayList i verilen dosyaya dosyaya yazar.
     * boolean append parametresi true ise dosyanın sonuna eklenir , false ise
     * dosya baştan yazılır.
     * @param filename yazılacak dosya
     * @param arr   eklenecek arraylist
     * @param append eklenmeli mi (true), bastan mı yazılmalı(true)
     */
    public void writeArrayIntoFile(String filename,ArrayList<String> arr,boolean append){

        try
        {
            FileWriter fileWriter = new FileWriter(filename,append);
            
            for(String record : arr){
                fileWriter.append(record);
                fileWriter.append("\n");
            }
            fileWriter.flush();
            fileWriter.close();
            
        } catch (IOException ex) {
            System.out.println("Writing file failed!");
        }
           
    }
}
