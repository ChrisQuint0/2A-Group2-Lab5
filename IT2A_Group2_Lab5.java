
/*
 * A program implemenation of Binary Search Tree that stores integer values.
 * 
 * Group # 2
 * Authors: Goto, Ipei B.(Leader)
 * Quinto, Christopher A.
 * Talato, Joshua P.
 * Laboratory Exercise # 5
 * Date: 11/26/2024
 */

import java.util.InputMismatchException;
import java.util.*;

class TreeNode {
  int value;
  TreeNode left, right;

  TreeNode(int value) {
    this.value = value;
    this.left = null;
    this.right = null;
  }
}

class BinaryTree {
  private TreeNode root;

  public boolean isEmpty() {
    return root == null;
  }

  public static void cls() {
    System.out.print("\u000C");
    System.out.flush();
  }

  public static void pause() {
    Scanner sc = new Scanner(System.in);

    System.out.println("Press any key to continue...");
    sc.nextLine();
  }

  // Insert a value into the binary tree
  public void insert() {
    cls();
    Scanner sc = new Scanner(System.in);

    String again;

    while (true) {
      System.out.println("INSERT");
      System.out.println("------------------------------------\n");

      System.out.print("Enter Value to Insert: ");

      int value = 0;

      try {
        value = sc.nextInt();
      } catch (InputMismatchException e) {
        sc.next();
        cls();

        System.out.println("ERROR!");
        System.out.println("------------------------------------\n");
        System.out.println("Invalid Input. Enter an Integer.\n");

        pause();
        cls();
        continue;
      }

      if (root == null) {
        root = new TreeNode(value); // First value becomes the root
      } else {
        insertRecursive(root, value);
      }

      while (true) {
        System.out.print("Try Again (Y/N)?: ");

        again = sc.next();

        again = again.toUpperCase();

        if (again.length() > 1 || again.length() < 1) {
          System.out.println("ERROR!");
          System.out.println("------------------------------------\n");
          System.out.println("Invalid Input. Enter Y/N\n");

          pause();
          cls();
          System.out.println("INSERT");
          System.out.println("------------------------------------\n");

          continue;
        }

        if (again.charAt(0) != 'Y' && again.charAt(0) != 'N') {
          System.out.println("ERROR!");
          System.out.println("------------------------------------\n");
          System.out.println("Invalid Input. Enter Y/N\n");

          pause();
          cls();
          System.out.println("INSERT");
          System.out.println("------------------------------------\n");
          continue;
        } else {
          break;
        }

      }

      if (again.charAt(0) == 'Y') {
        cls();
        continue;
      } else if (again.charAt(0) == 'N') {
        cls();
        break;
      }
    }

  }

  // Recursive function to insert a value into the tree
  private void insertRecursive(TreeNode node, int value) {
    if (value < node.value) {
      if (node.left == null) {
        node.left = new TreeNode(value); // Insert as left child
      } else {
        insertRecursive(node.left, value); // Continue to the left subtree
      }
    } else if (value > node.value) {
      if (node.right == null) {
        node.right = new TreeNode(value); // Insert as right child
      } else {
        insertRecursive(node.right, value); // Continue to the right subtree
      }
    } else {
      System.out.println("\nValue " + value + " already exists in the tree. Duplicate insertion is not allowed.\n");
    }
  }

  public void preOrderTraversal() {
    if (root == null) {
      System.out.println("------------------------");
      System.out.println("THE TREE IS EMPTY");
      System.out.println("------------------------\n");

      return;
    }

    printTree();
    System.out.print("\nPreOrder Traversal: ");
    preorderRecursive(root);
    System.out.println();
    System.out.println();
  }

  public void preorderRecursive(TreeNode node) {
    if (node == null) {
      return;
    }

    System.out.print(node.value + " ");
    preorderRecursive(node.left);
    preorderRecursive(node.right);
  }

  // In-order traversal to display the tree
  public void inorderTraversal() {
    if (root == null) {
      System.out.println("------------------------");
      System.out.println("THE TREE IS EMPTY");
      System.out.println("------------------------\n");

      return;
    }

    printTree();

    System.out.print("\nInOrder Traversal: ");
    inorderRecursive(root);
    System.out.println();
    System.out.println();
  }

  private void inorderRecursive(TreeNode node) {
    if (node != null) {

      inorderRecursive(node.left);
      System.out.print(node.value + " ");
      inorderRecursive(node.right);
    }
  }

  public void postOrderTraversal() {

    if (root == null) {
      System.out.println("------------------------");
      System.out.println("THE TREE IS EMPTY");
      System.out.println("------------------------\n");

      return;
    }

    printTree();
    System.out.print("\nPostOrder Traversal: ");

    postorderRecursive(root);
    System.out.println();
    System.out.println();
  }

  public void postorderRecursive(TreeNode node) {
    if (node == null) {
      return;
    }

    postorderRecursive(node.left);
    postorderRecursive(node.right);
    System.out.print(node.value + " ");
  }

  // Delete a node using in-order successor
  public boolean delete(int value) {
    if (root == null) {
      System.out.println("------------------------");
      System.out.println("THE TREE IS EMPTY");
      System.out.println("------------------------\n");

      pause();
      return false;
    }

    TreeNode[] result = findNodeAndParent(root, null, value);
    TreeNode node = result[0];
    TreeNode parent = result[1];

    if (node == null) {
      cls();

      System.out.println("ERROR!");
      System.out.println("------------------------------------\n");
      System.out.println("Value not found\n");

      pause();
      cls();

      System.out.println("DELETE");
      System.out.println("------------------------------------\n");

      return false; // Value not found
    }

    // Case 1: Node has two children
    if (node.left != null && node.right != null) {
      TreeNode successor = getInOrderSuccessor(node);
      int successorValue = successor.value;
      delete(successorValue); // Delete the successor node
      node.value = successorValue; // Replace node's value with the successor's value

    }
    // Case 2: Node has one child or no child
    else {
      TreeNode child = (node.left != null) ? node.left : node.right;

      if (node == root) {
        root = child; // Update root if deleting the root node
      } else if (node == parent.left) {
        parent.left = child; // Update parent's left child
      } else {
        parent.right = child; // Update parent's right child
      }
    }
    return true;
  }

  private TreeNode[] findNodeAndParent(TreeNode current, TreeNode parent, int value) {
    if (current == null) {
      return new TreeNode[] { null, null }; // Node not found
    }

    if (current.value == value) {
      return new TreeNode[] { current, parent };
    }

    if (value < current.value) {
      return findNodeAndParent(current.left, current, value);
    } else {
      return findNodeAndParent(current.right, current, value);
    }
  }

  private TreeNode getInOrderSuccessor(TreeNode node) {
    TreeNode current = node.right;
    while (current != null && current.left != null) {
      current = current.left;
    }
    return current;
  }

  // Print the tree in a structured format
  public void printTree() {
    cls();
    if (root == null) {
      System.out.println("------------------------");
      System.out.println("THE TREE IS EMPTY");
      System.out.println("------------------------");
      return;
    }

    System.out.println("---------------------------------");
    System.out.println("        BINARY SEARCH TREE");
    System.out.println("---------------------------------");

    int height = getHeight(root);
    int width = (int) Math.pow(2, height + 2) - 1; // Maximum width of the tree
    List<List<String>> levels = new ArrayList<>();

    // Initialize levels with empty strings
    for (int i = 0; i < height; i++) {
      List<String> level = new ArrayList<>(Collections.nCopies(width, " "));
      levels.add(level);
    }

    fillLevels(root, levels, 0, 0, width - 1);

    // Print each level
    for (List<String> level : levels) {
      for (String val : level) {
        System.out.print(val);
      }
      System.out.println();
    }

    System.out.println();
  }

  private void fillLevels(TreeNode node, List<List<String>> levels, int depth, int left, int right) {
    if (node == null)
      return;

    int mid = (left + right) / 2; // Find the middle position for the current node
    levels.get(depth).set(mid, String.valueOf(node.value));

    fillLevels(node.left, levels, depth + 1, left, mid - 1); // Recur for left subtree
    fillLevels(node.right, levels, depth + 1, mid + 1, right); // Recur for right subtree
  }

  // Helper to get the height of the tree
  private int getHeight(TreeNode node) {
    if (node == null)
      return 0;
    return 1 + Math.max(getHeight(node.left), getHeight(node.right));
  }

}

public class IT2A_Group2_Lab5 {
  static BinaryTree tree = new BinaryTree();

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    while (true) {
      cls();
      displayMenu();

      System.out.print("\nEnter the First Letter of your Choice: ");

      String choice;

      choice = sc.nextLine();

      if (choice.length() > 1 || choice.length() == 0 || Character.isDigit(choice.charAt(0))) {
        cls();
        System.out.println("ERROR!");
        border();

        System.out.println("Invalid Input! Please Enter the First Letter of your Choice\n");
        pause();
        cls();

        continue;
      }

      if (!choice.equalsIgnoreCase("S") &&
          !choice.equalsIgnoreCase("I") &&
          !choice.equalsIgnoreCase("D") &&
          !choice.equalsIgnoreCase("T") &&
          !choice.equalsIgnoreCase("Q")) {
        cls();
        System.out.println("ERROR!");
        border();

        System.out.println("Invalid Input! Please Enter the First Letter of your Choice\n");
        pause();
        cls();

        continue;
      }

      if (choice.equalsIgnoreCase("Q")) {
        System.out.println("\nProgram Terminated Gracefully\n");
        break;
      }

      executeChoice(choice);
    }

  }

  public static void border() {
    System.out.println("--------------------------------------\n");
  }

  public static void displayMenu() {
    System.out.println("       MENU");
    System.out.println("BST Tree Operations");
    System.out.println();
    System.out.println("    [S] Show");
    System.out.println();
    System.out.println("    [I] Insert");
    System.out.println();
    System.out.println("    [D] Delete");
    System.out.println();
    System.out.println("    [T] Traverse");
    System.out.println();
    System.out.println("    [Q] Quit");
  }

  public static void executeChoice(String choice) {
    Scanner sc = new Scanner(System.in);

    if (choice.equalsIgnoreCase("S")) {
      tree.printTree();

      pause();
    } else if (choice.equalsIgnoreCase("I")) {
      tree.insert();
    } else if (choice.equalsIgnoreCase("D")) {
      if (tree.isEmpty()) {

        cls();
        System.out.println("--------------------------------");
        System.out.println("TREE IS EMPTY");
        System.out.println("--------------------------------\n");

        pause();
        return;
      }
      cls();

      while (true) {
        tree.printTree();

        System.out.println("DELETE");
        System.out.println("------------------------------------\n");

        System.out.print("Enter the value you want to delete: ");
        int value = 0;

        try {
          value = sc.nextInt();
        } catch (InputMismatchException e) {
          sc.next();

          cls();

          System.out.println("ERROR!");
          System.out.println("------------------------------------\n");
          System.out.println("Invalid Input. Enter an Integer.\n");

          pause();
          cls();
          continue;

        }

        if (tree.delete(value)) {
          System.out.println();
          System.out.print(value);
          System.out.println(" Successfully Deleted!\n");
        }

        String again;

        if (tree.isEmpty()) {
          System.out.println("--------------------------------");
          System.out.println("TREE IS NOW EMPTY");
          System.out.println("--------------------------------\n");

          pause();
          break;
        }

        while (true) {
          System.out.print("Try Again (Y/N)?: ");

          again = sc.next();

          again = again.toUpperCase();

          if (again.length() > 1 || again.length() < 1) {
            System.out.println("ERROR!");
            System.out.println("------------------------------------\n");
            System.out.println("Invalid Input. Enter Y/N\n");

            pause();
            cls();
            System.out.println("DELETE");
            System.out.println("------------------------------------\n");

            continue;
          }

          if (again.charAt(0) != 'Y' && again.charAt(0) != 'N') {
            System.out.println("ERROR!");
            System.out.println("------------------------------------\n");
            System.out.println("Invalid Input. Enter Y/N\n");

            pause();
            cls();
            System.out.println("DELETE");
            System.out.println("------------------------------------\n");
            continue;
          } else {
            break;
          }

        }

        if (again.charAt(0) == 'Y') {
          cls();

          if (tree.isEmpty()) {
            break;
          } else {
            continue;
          }

        } else if (again.charAt(0) == 'N') {
          cls();
          break;
        }

        pause();
      }

    } else if (choice.equalsIgnoreCase("T")) {
      if (tree.isEmpty()) {

        cls();
        System.out.println("--------------------------------");
        System.out.println("TREE IS EMPTY");
        System.out.println("--------------------------------\n");

        pause();
        return;
      }

      int traversalChoice;

      while (true) {
        displayTraversalMenu();

        try {
          System.out.print("Enter Your Choice: ");
          traversalChoice = sc.nextInt();

        } catch (InputMismatchException e) {
          sc.next();
          cls();
          System.out.println("ERROR!");
          border();

          System.out.println("Invalid Input! Please Enter from the Given Choices\n");
          pause();
          cls();

          continue;
        }

        if (traversalChoice == 4) {
          break;
        }

        executeTraversal(traversalChoice);

        pause();
        break;
      }

    }
  }

  public static void displayTraversalMenu() {
    cls();
    Scanner sc = new Scanner(System.in);

    System.out.println("   TREE TRAVERSAL");
    System.out.println("        Menu");
    System.out.println();
    System.out.println("    1 - InOrder");
    System.out.println();
    System.out.println("    2 - PreOrder");
    System.out.println();
    System.out.println("    3 - PostOrder");
    System.out.println();
    System.out.println("    4 - Exit");
    System.out.println();

  }

  public static void executeTraversal(int choice) {
    switch (choice) {
      case 1:
        tree.inorderTraversal();
        break;
      case 2:
        tree.preOrderTraversal();
        break;
      case 3:
        tree.postOrderTraversal();
      default:
        break;
    }
  }

  public static void cls() {
    System.out.print("\u000C");
    System.out.flush();
  }

  public static void pause() {
    Scanner sc = new Scanner(System.in);

    System.out.println("Press any key to continue...");
    sc.nextLine();
  }
}