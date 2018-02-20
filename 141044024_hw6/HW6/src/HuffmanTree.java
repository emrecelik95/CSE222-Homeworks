import java.util.*;
import java.io.*;

/**
 * Kitaptan alınıp gerekli methodlar(encode) eklenmiştir.
* */

public class HuffmanTree
    implements Serializable {

  // Nested Classes
  /** A datum in the Huffman tree. */
  public static class HuffData
      implements Serializable, Comparable<HuffData> {
    // Data Fields
    /** The weight or probability assigned to this HuffData. */
    private double weight;

    /** The alphabet symbol if this is a leaf. */
    private Character symbol;

    public HuffData(double weight, Character symbol) {
      this.weight = weight;
      this.symbol = symbol;
    }

    @Override
    public int compareTo(HuffData o) {
      return 0;
    }
  }

  // Data Fields
  /** A reference to the completed Huffman tree. */
  private BinaryTree < HuffData > huffTree;

  /** A Comparator for Huffman trees; nested class. */
  private static class CompareHuffmanTrees
      implements Comparator < BinaryTree < HuffData >> {
    /** Compare two objects.
        @param treeLeft The left-hand object
        @param treeRight The right-hand object
        @return -1 if left less than right,
                0 if left equals right,
                and +1 if left greater than right
     */
    public int compare(BinaryTree < HuffData > treeLeft,
                       BinaryTree < HuffData > treeRight) {
      double wLeft = treeLeft.getData().weight;
      double wRight = treeRight.getData().weight;
      return Double.compare(wLeft, wRight);
    }
  }


  /** Builds the Huffman tree using the given alphabet and weights.
      post:  huffTree contains a reference to the Huffman tree.
      @param symbols An array of HuffData objects
   */
  public void buildTree(HuffData[] symbols) {
    Queue < BinaryTree < HuffData >> theQueue
        = new PriorityQueue < BinaryTree < HuffData >>
        (symbols.length, new CompareHuffmanTrees());
    // Load the queue with the leaves.
    for (HuffData nextSymbol : symbols) {
      BinaryTree < HuffData > aBinaryTree =
          new BinaryTree < HuffData > (nextSymbol, null, null);
      theQueue.offer(aBinaryTree);
    }

    // Build the tree.
    while (theQueue.size() > 1) {
      BinaryTree < HuffData > left = theQueue.poll();
      BinaryTree < HuffData > right = theQueue.poll();
      double wl = left.getData().weight;
      double wr = right.getData().weight;
      HuffData sum = new HuffData(wl + wr, null);
      BinaryTree < HuffData > newTree =
          new BinaryTree < HuffData > (sum, left, right);
      theQueue.offer(newTree);
    }

    // The queue should now contain only one item.
    huffTree = theQueue.poll();
  }

  public void printCode(PrintStream out){
    printCode(out,"",huffTree);
  }


  /** Outputs the resulting code.
      @param out A PrintStream to write the output to
      @param code The code up to this node
      @param tree The current node in the tree
   */
  private void printCode(PrintStream out, String code,
                         BinaryTree < HuffData > tree) {
    HuffData theData = tree.getData();

    if (theData.symbol != null) {
      if (theData.symbol.equals(" ")) {
        out.println("space: " + code);
      }
      else {
        out.println(theData.symbol + ": " + code);
      }
    }
    else {
      printCode(out, code + "0", tree.getLeftSubtree());
      printCode(out, code + "1", tree.getRightSubtree());
    }
  }

  /** Method to decode a message that is input as a string of
      digit characters '0' and '1'.
      @param codedMessage The input message as a String of
                          zeros and ones.
      @return The decoded message as a String
   */
  public String decode(String codedMessage) {
    StringBuilder result = new StringBuilder();
    BinaryTree < HuffData > currentTree = huffTree;
    for (int i = 0; i < codedMessage.length(); i++) {
      if (codedMessage.charAt(i) == '1') {
        currentTree = currentTree.getRightSubtree();
      }
      else {
        currentTree = currentTree.getLeftSubtree();
      }
      if (currentTree.isLeaf()) {
        HuffData theData = currentTree.getData();
        result.append(theData.symbol);
        currentTree = huffTree;
      }
    }
    return result.toString();
  }

  /**
   * Verilen stringin binary kodlanmıs seklini dondurur.
   * @param message kodlanacak string
   * @return coded string
   */
  public String encode(String message) throws Exception {
    StringBuilder coded = new StringBuilder("");
    for(int i = 0 ; i < message.length() ; ++i) {
      if(message.charAt(i) == ' ')
          throw new Exception("Space cannot be accepted!");

      encode(huffTree, message.charAt(i), "", coded).toString();
      coded.append(" ");
    }
    return coded.toString();
  }

  /**
   * Tek karakteri kodlayan recursive method.
   * @param tree islemin uzerinden yapıldıgı tree
   * @param ch   kodlanacak karakter
   * @param code  recursive icin gerekl,
   * @param coded en son kodlanmıs hali
   * @return kodlanmıs stringbuilder
   */
  private StringBuilder encode(BinaryTree < HuffData > tree, char ch , String code,StringBuilder coded) {

    HuffData theData = tree.getData();

    if (theData.symbol != null) {
          if (theData.symbol.equals(ch)){
            coded.append(code);
            return coded;
          }
    } else {
      if(tree.getLeftSubtree() != null)
        encode(tree.getLeftSubtree(),ch,code + "0",coded);
      if(tree.getRightSubtree() != null)
        encode(tree.getRightSubtree(),ch,code + "1",coded);
    }

    return coded;
  }


}
