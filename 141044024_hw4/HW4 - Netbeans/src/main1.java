
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class main1 {
    public static void main(String[] args){
        String inputFile = "test.csv";
        String outputFile = "testResult_1.csv";
        long sTime,eTime;
        
        StackA stackA = new StackA<>();
        StackB stackB = new StackB<>();
        StackC stackC = new StackC<>();
        StackD stackD = new StackD<>();
        
        try {
            FileReader fileReader = new FileReader(inputFile);
            FileWriter fileWriter = new FileWriter(outputFile);

            Scanner sc = new Scanner(fileReader);

                
            while(sc.hasNextLine()){
                String[] line = sc.nextLine().split(",");
                
                int dataType = 0;
                Scanner sc2 = new Scanner(line[0]);
                if(sc2.hasNextInt()){
                    dataType = 1;                    
                    System.out.println("int...\n");
                }
                else if(sc2.hasNextFloat()){
                    dataType = 2;                    
                    System.out.println("float...\n");
                }
                else if(line[0].length() == 1){
                    dataType = 3;                    
                    System.out.println("char...\n");
                }
                else{//else if(sc2.hasNext())
                    dataType = 4;                    
                    System.out.println("string...\n");
                }
            
                sTime = System.nanoTime();
                for(String item : line){
                    Object newItem = null;
                    if(dataType == 1)
                        newItem = Integer.parseInt(item);
                    else if(dataType == 2)
                        newItem = Float.parseFloat(item);
                    else if(dataType == 3)
                        newItem = (char)item.charAt(0);
                    else if(dataType == 4)
                        newItem = item;
                    stackA.push(newItem);
                }
                eTime = System.nanoTime();
                System.out.println("stackA push time : " + (double)(eTime-sTime)/1000000000 + " sn");
                
                sTime = System.nanoTime();
                for(String item : line){
                    Object newItem = null;
                    if(dataType == 1)
                        newItem = Integer.parseInt(item);
                    else if(dataType == 2)
                        newItem = Float.parseFloat(item);
                    else if(dataType == 3)
                        newItem = (char)item.charAt(0);
                    else if(dataType ==4)
                        newItem = item;
                    stackB.push(newItem);         
                }
                eTime = System.nanoTime();
                System.out.println("stackB push time : " + (double)(eTime-sTime)/1000000000 + " sn");
                
                sTime = System.nanoTime();
                for(String item : line){
                    Object newItem = null;
                    if(dataType == 1)
                        newItem = Integer.parseInt(item);
                    else if(dataType == 2)
                        newItem = Float.parseFloat(item);
                    else if(dataType == 3)
                        newItem = (char)item.charAt(0);
                    else if(dataType == 4)
                        newItem = item;
                    stackC.push(newItem);
                }
                eTime = System.nanoTime();
                System.out.println("stackC push time : " + (double)(eTime-sTime)/1000000000 + " sn");
                
                sTime = System.nanoTime();
                for(String item : line){
                    Object newItem = null;
                    if(dataType == 1)
                        newItem = Integer.parseInt(item);
                    else if(dataType == 2)
                        newItem = Float.parseFloat(item);
                    else if(dataType == 3)
                        newItem = (char)item.charAt(0);
                    else if(dataType == 4)
                        newItem = item;
                    
                    stackD.push(newItem);   
                }
                eTime = System.nanoTime();
                System.out.println("stackD push time : " + (double)(eTime-sTime)/1000000000 + " sn\n");
                
                sTime = System.nanoTime();
                fileWriter.write(stackA.size() + ",");
                while(!stackA.isEmpty())
                    fileWriter.write(stackA.pop() + ",");
                
                fileWriter.write("\n");
                eTime = System.nanoTime();
                System.out.println("stackA pop time : " + (double)(eTime-sTime)/1000000000 + " sn");
                
                sTime = System.nanoTime();
                fileWriter.write(stackB.size() + ",");
                while(!stackB.isEmpty())
                    fileWriter.write(stackB.pop() + ",");
                
                fileWriter.write("\n");
                eTime = System.nanoTime();
                System.out.println("stackB pop time : " + (double)(eTime-sTime)/1000000000 + " sn");
                
                sTime = System.nanoTime();
                fileWriter.write(stackC.size() + ",");
                while(!stackC.isEmpty())
                    fileWriter.write(stackC.pop() + ",");
                
                fileWriter.write("\n");
                eTime = System.nanoTime();
                System.out.println("stackC pop time : " + (double)(eTime-sTime)/1000000000 + " sn");
                
                sTime = System.nanoTime();
                fileWriter.write(stackD.size() + ",");
                while(!stackD.isEmpty())
                    fileWriter.write(stackD.pop() + ",");
                
                fileWriter.write("\n");
                eTime = System.nanoTime();
                System.out.println("stackD pop time : " + (double)(eTime-sTime)/1000000000 + " sn\n");
            }
    
            
            
            fileReader.close();
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(main1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
            System.err.println("Exception caught!");
        }
        
        
    }
}
