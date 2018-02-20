
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args){

        BinaryTree<Integer> tree = new BinaryTree<>();
        BinarySearchTree<Integer> bsTree = new BinarySearchTree<>();


        try {
            FileReader reader = new FileReader("test.txt");
            Scanner sc = new Scanner(reader);

            while(sc.hasNextInt()){
                Integer item = sc.nextInt();
                tree.add(item);
                bsTree.add(item);
            }

            reader.close();

            System.out.println(tree);

            traversePreOrder(tree);
            System.out.println();
            traverseLevelOrder(bsTree);



        } catch (FileNotFoundException e) {
            System.out.print("File not found!");
        } catch (Exception e){
            System.out.print("Exception caught : " + e.toString());

        }

    }


    public static void traversePreOrder(BinaryTree tree){

        System.out.println("Traverse Pre Order");
        Iterator<Integer> itr = tree.iterator();

        while(itr.hasNext())
            System.out.println("current node data : " + itr.next().toString());
    }

    public static void traverseLevelOrder(BinarySearchTree bsTree){

        System.out.println("Traverse Level Order");
        Iterator<Integer> itr = bsTree.iterator();

        while(itr.hasNext())
            System.out.println("current node data : " + itr.next().toString());
    }
}
