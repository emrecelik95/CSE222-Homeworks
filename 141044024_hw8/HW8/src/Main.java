/**
 * Created by root on 11.05.2017.
 */
public class Main {
    public static void main(String[] args){
        AVLTree<String> avl = new AVLTree<>();
        avl.add("Nush");
        avl.add("ile");
        avl.add("uslanmayani");
        avl.add("etmeli");
        avl.add("tekdir");
        avl.add("uslanmayanin");
        avl.add("hakki");
        avl.add("kotektir");

        TreePrinter.print((TreePrinter.PrintableNode)avl.root);
        System.out.println();
        avl.add("edille");
        avl.add("dakik");
        avl.add("ferc");

        TreePrinter.print((TreePrinter.PrintableNode)avl.root);
        System.out.println();

        avl.delete(avl.root.data);
        TreePrinter.print((TreePrinter.PrintableNode)avl.root);
        System.out.println();

        avl.delete(avl.root.data);
        TreePrinter.print((TreePrinter.PrintableNode)avl.root);
        System.out.println();


        avl.delete(avl.root.data);
        TreePrinter.print((TreePrinter.PrintableNode)avl.root);


    }
}
