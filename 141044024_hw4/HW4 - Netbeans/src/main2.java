
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author emre
 */
public class main2 {
    public static void main(String[] args) throws FileNotFoundException{
        String inputFile = "test.csv";
        String outputFile = "testResult_2.csv";
        long sTime,eTime;
        
        myQueue<Object> queueForRows = new myQueue<>();       
        Queue<Object> queueForRows2 = new LinkedList<>();
        
        
        try {
            FileReader fileReader = new FileReader(inputFile);
            FileWriter fileWriter = new FileWriter(outputFile);

            Scanner sc = new Scanner(fileReader);
            
            sTime = System.nanoTime();

            while(sc.hasNextLine()){
                String line = sc.nextLine();
                queueForRows.add(line);
            }
            
            queueForRows.reverse();
            
            Iterator<Object> itr1 = queueForRows.iterator();
            while(itr1.hasNext()){
                String[] line = itr1.next().toString().split(",");
                
                myQueue<Object> queueForCols = new myQueue<>();

                int dataType = 0;
                Scanner sc2 = new Scanner(line[0]);
                if(sc2.hasNextInt()){
                    dataType = 1;
                    
                    System.out.println("int...");
                }
                else if(sc2.hasNextFloat()){
                    dataType = 2;
                    System.out.println("float...");
                }
                else if(line[0].length() == 1){
                    dataType = 3;
                    
                    System.out.println("char...");
                }
                else{//else if(sc2.hasNext())
                    dataType = 4;
                    System.out.println("string...");
                }
                
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
                    
                    queueForCols.addLast(newItem);
                }
                
                queueForCols.reverse();
            
                Iterator<Object> itr2 = queueForCols.iterator();
                while(itr2.hasNext())
                    fileWriter.write(itr2.next().toString() + ",");
                
                fileWriter.write("\n");
            }
            
            eTime = System.nanoTime();
            System.out.println("myQueue - reverse() - running time : " + (double)(eTime-sTime)/1000000000 + " sn");
            
////////////////// 2. TEST - QUEUE reverseQueue/ ///////////////////////////////
            fileWriter.write("\n");
            
            fileReader = new FileReader(inputFile);
            sc = new Scanner(fileReader);
            
            sTime = System.nanoTime();

            while(sc.hasNextLine()){
                String line = sc.nextLine();
                queueForRows2.add(line);
            }
            
            myQueue.reverseQueue(queueForRows2);
            
            itr1 = queueForRows2.iterator();
            while(itr1.hasNext()){
                String[] line = itr1.next().toString().split(",");
                
                Queue<Object> queueForCols2 = new LinkedList<>();

                int dataType = 0;
                Scanner sc2 = new Scanner(line[0]);
                if(sc2.hasNextInt()){
                    dataType = 1;
                    
                    System.out.println("int...");
                }
                else if(sc2.hasNextFloat()){
                    dataType = 2;
                    System.out.println("float...");
                }
                else if(line[0].length() == 1){
                    dataType = 3;
                    
                    System.out.println("char...");
                }
                else{//else if(sc2.hasNext())
                    dataType = 4;
                    System.out.println("string...");
                }
                
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
                    
                    queueForCols2.add(newItem);
                }
                
                myQueue.reverseQueue(queueForCols2);
            
                Iterator<Object> itr2 = queueForCols2.iterator();
                while(itr2.hasNext())
                    fileWriter.write(itr2.next().toString() + ",");
                
                fileWriter.write("\n");
            }
            
            eTime = System.nanoTime();
            System.out.println("Queue - myQueue.reverseQueue() - running time : " + (double)(eTime-sTime)/1000000000 + " sn");
            
            fileReader.close();
            fileWriter.close();
        } 
       catch (IOException ex) {
            Logger.getLogger(main2.class.getName()).log(Level.SEVERE, null, ex);
        }catch (Exception ex){
            System.err.println("Exception caught!");
        }
        
    }

}