
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
import java.util.Scanner;

import javax.swing.plaf.TreeUI;

public class IT2A_Group2_Lab5 {

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
        System.out.println("\nProgram Terminated\n");
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
    if (choice.equalsIgnoreCase("S")) {
      System.out.println("Show");

      pause();
    } else if (choice.equalsIgnoreCase("I")) {
      System.out.println("I");

      pause();
    } else if (choice.equalsIgnoreCase("D")) {
      System.out.println("Delete");
      pause();
    } else if (choice.equalsIgnoreCase("T")) {
      System.out.println("Traverse");
      pause();
    }
  }

  public static void cls() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public static void pause() {
    Scanner sc = new Scanner(System.in);

    System.out.println("Press any key to continue...");
    sc.nextLine();
  }
}