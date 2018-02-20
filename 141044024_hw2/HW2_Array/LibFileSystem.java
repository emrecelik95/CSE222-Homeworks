
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
     * olacak şekilde DynamicArray'e aktarıp bu DynamicArray'i return eder.
     * @param filename okunacak dosya
     * @return arr dondurulecek arraylist
     */
    public DynamicArray<String> readFileIntoArray(String filename){
       DynamicArray<String> arr = new DynamicArray<String>();
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
     * Verilen DynamicArray i verilen dosyaya dosyaya yazar.
     * boolean append parametresi true ise dosyanın sonuna eklenir , false ise
     * dosya baştan yazılır.
     * @param filename yazılacak dosya
     * @param arr   eklenecek arraylist
     * @param append eklenmeli mi (true), bastan mı yazılmalı(true)
     */
    public void writeArrayIntoFile(String filename,DynamicArray<String> arr,boolean append){

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
