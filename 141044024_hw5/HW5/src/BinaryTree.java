import java.io.*;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** BinaryTree ()
  * Bazı kısımlar kitaptan yardım alınmıştır.
  * */

public class BinaryTree < E
    extends Comparable < E >>
    implements Serializable , Iterable{

  /**
   * pre order iterator
   * @return Iterator
   */
  @Override
  public Iterator iterator() {
    return new preOrderIterator();
  }

  /**
   * Eleman ekler.
   * @param item
   * @return boolean
   */
  public boolean add(E item) {
    root = add(root, item);
    return true;
  }

  /**
   * Verilen noddan baslayıp eleman ekler.Bu eleman kucukse sola , buyukse saga ekler.
   * @param localRoot
   * @param item
   * @return node
   */
  private Node < E > add(Node < E > localRoot, E item) {
    if (localRoot == null) {
      return new Node<E>(item);
    }
    else if (item.compareTo(localRoot.data) == 0) {
      return localRoot;
    }
    else if (item.compareTo(localRoot.data) < 0) {
      localRoot.left = add(localRoot.left, item);
      return localRoot;
    }
    else {
      localRoot.right = add(localRoot.right, item);
      return localRoot;
    }
  }

  /**
   * Inner class : preorder iterator
   */
  protected class preOrderIterator implements Iterator<E>{
    LinkedList<Node<E>> list;

    /**
     * no parameter constructor
     * preorder traverse edilen treeyi liste aktarır.
     */
    public preOrderIterator(){
      list = new LinkedList<Node<E>>();
      list = traversePreOrder(root);
    }

    /**
     * traversepreorder edilen treeyi list olarak dondurur.
     * @return linkedlist
     */
  public LinkedList<Node<E>> traversePreOrder(){
      return traversePreOrder(root);
  }

    /**
     * tree yi preorder traverse edip elemanları linkedliste aktarır.
     * @param node
     * @return linkedlist
     */
  private LinkedList<Node<E>> traversePreOrder(Node<E> node){
    if(node == null)
      return null;

    list.addLast(node);

    traversePreOrder(node.left);
    traversePreOrder(node.right);
    return list;
  }

    /**
     * list doluysa true,bossa false dondurur.
     * @return boolean
     */
    @Override
    public boolean hasNext() {
      return !list.isEmpty();
    }

    /**
     * listenin bastaki elemanı silerek ver (preorder traverse gore)
     * @return E generic
     */
    @Override
    public E next() {
      if(!hasNext())
        throw new NoSuchElementException();

      E data = list.getFirst().data;
      list.removeFirst();

      return data;
    }

    /**
     * remove desteklenmiyor.
     */
    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  /** Class to encapsulate a tree node. */
  protected static class Node < E >
      implements Serializable {
    // Data Fields
    /** The information stored in this node. */
    protected E data;

    /** Reference to the left child. */
    protected Node < E > left;

    /** Reference to the right child. */
    protected Node < E > right;

    // Constructors
    /** Construct a node with given data and no children.
        @param data The data to store in this node
     */
    public Node(E data) {
      this.data = data;
      left = null;
      right = null;
    }

    // Methods
    /** Return a string representation of the node.
        @return A string representation of the data fields
     */
    public String toString() {
      return data.toString();
    }
  }

  // Data Field
  /** The root of the binary tree */
  protected Node<E> root;

  public BinaryTree() {
    root = null;
  }

  protected BinaryTree(Node < E > root) {
    this.root = root;
  }

  /** Constructs a new binary tree with data in its root,leftTree
      as its left subtree and rightTree as its right subtree.
   */
  public BinaryTree(E data, BinaryTree < E > leftTree,
                    BinaryTree < E > rightTree) {
    root = new Node < E > (data);
    if (leftTree != null) {
      root.left = leftTree.root;
    }
    else {
      root.left = null;
    }
    if (rightTree != null) {
      root.right = rightTree.root;
    }
    else {
      root.right = null;
    }
  }

  /** Return the left subtree.
      @return The left subtree or null if either the root or
      the left subtree is null
   */
  public BinaryTree < E > getLeftSubtree() {
    if (root != null && root.left != null) {
      return new BinaryTree < E > (root.left);
    }
    else {
      return null;
    }
  }

  /** Return the right sub-tree
        @return the right sub-tree or
        null if either the root or the
        right subtree is null.
    */
    public BinaryTree<E> getRightSubtree() {
        if (root != null && root.right != null) {
            return new BinaryTree<E>(root.right);
        } else {
            return null;
        }
    }


  /**** BEGIN EXERCISE ****/
  /** Return the data field of the root
        @return the data field of the root
        or null if the root is null
    */
    public E getData() {
        if (root != null) {
            return root.data;
        } else {
            return null;
        }
    }
  /**** END EXERCISE ****/

  /** Determine whether this tree is a leaf.
      @return true if the root has no children
   */
  public boolean isLeaf() {
    return (root.left == null && root.right == null);
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    preOrderTraverse(root, 1, sb);
    return sb.toString();
  }

  /** Perform a preorder traversal.
      @param node The local root
      @param depth The depth
      @param sb The string buffer to save the output
   */
  protected void preOrderTraverse(Node < E > node, int depth,
                                StringBuilder sb) {
    for (int i = 1; i < depth; i++) {
      sb.append("  ");
    }
    if (node == null) {
      sb.append("null\n");
    }
    else {
      sb.append(node.toString());
      sb.append("\n");
      preOrderTraverse(node.left, depth + 1, sb);
      preOrderTraverse(node.right, depth + 1, sb);
    }
  }

  /** Method to read a binary tree.
      pre: The input consists of a preorder traversal
           of the binary tree. The line "null" indicates a null tree.
      @param bR The input file
      @return The binary tree
      @throws IOException If there is an input error
   */

  public static BinaryTree < String >
  readBinaryTree(BufferedReader bR) throws IOException {
    // Read a line and trim leading and trailing spaces.
    String data = bR.readLine().trim();
    BinaryTree<String> leftTree;
    BinaryTree<String> rightTree;
    if (data.equals("null")) {
      return null;
    } else if (bR != null) {

      leftTree = readBinaryTree(bR);
      rightTree = readBinaryTree(bR);
      return new BinaryTree<String>(data, leftTree, rightTree);

    }

    return null;
  }


}