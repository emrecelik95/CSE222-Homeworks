import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args){

        try {
            Scanner sc = new Scanner(new File("freq.txt"));

            int i = 0;
            while(sc.hasNextLine()) {
                sc.nextLine();
                ++i;
            }
            HuffmanTree.HuffData[] data = new HuffmanTree.HuffData[i];
            sc = new Scanner(new File("freq.txt"));
            Character ch = null;
            Integer fre = 0;
            i = 0;
            while(sc.hasNext()){
                ch = sc.next().charAt(0);

                fre  = sc.nextInt();
                data[i++] = new HuffmanTree.HuffData(fre,ch);
            }

            HuffmanTree tree = new HuffmanTree();
            tree.buildTree(data);
            tree.printCode(System.out);

            System.out.println("decode : " + tree.decode("10010111010000"));
            System.out.println("encode : " + tree.encode("dec"));

        } catch (FileNotFoundException e) {
            System.out.println("File is not found");
        } catch(Exception e){
            System.out.println("Exception caught : " + e.toString());
        }

    }
}
