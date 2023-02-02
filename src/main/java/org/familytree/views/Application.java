package org.familytree.views;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import org.familytree.controllers.ApplicationController;
import org.familytree.models.Node;
import org.familytree.models.NodeMapper;
import org.familytree.services.impl.DependencyGraphServiceImpl;
import org.familytree.services.impl.NodeMapperServiceImpl;
import org.familytree.services.impl.NodeServiceImpl;

/**
 * The type Application.
 */
public class Application {
  /**
   * The Application controller.
   */
  private final ApplicationController applicationController;
  /**
   * The constant scanner.
   */
  private static Scanner scanner = new Scanner(System.in);

  /**
   * Instantiates a new Application.
   */
  public Application() {
    final NodeMapper nodeMapper = NodeMapper.getInstance();
    applicationController = new ApplicationController(
        new NodeMapperServiceImpl(nodeMapper),
        new NodeServiceImpl(), new DependencyGraphServiceImpl());
  }

  /**
   * Run.
   */
  public void run() {
    while (true) {
      showMenu();
    }
  }

  /**
   * Show menu.
   */
  private void showMenu() {
    System.out.println(
        "Select one of the 9 options available by entering number\n"
            + "1. Add new Node to graph\n"
            + "2. Delete Node from graph\n"
            + "3. Add new dependency\n"
            + "4. Delete dependency\n"
            + "5. Get Immediate Parent of Node\n"
            + "6. Get Immediate Children of Node\n"
            + "7. Get Ancestors of Node\n"
            + "8. Get Descendants of Node\n"
            + "9. Terminate");
    selectOption();
  }

  /**
   * Select option.
   */
  private void selectOption() {
    try {
      final String option = scanner.nextLine().strip();
      switch (option) {
        case "1":
          addNewNode();
          break;
        case "2":
          deleteNode();
          break;
        case "3":
          addDependency();
          break;
        case "4":
          deleteDependency();
          break;
        case "5":
          showImmediateParents();
          break;
        case "6":
          showImmediateChildren();
          break;
        case "7":
          showAncestors();
          break;
        case "8":
          showDescendants();
          break;
        case "9":
          terminate();
          break;
        default:
          System.out.println("select correct option");
          selectOption();
          break;
      }
    } catch (Exception e) {
      showErrors(e.getMessage());
    }
  }

  /**
   * Add new node.
   */
  private void addNewNode() {
    System.out.println("Please enter the id of node:");
    final String nodeId = scanner.nextLine().strip();
    System.out.println("Please enter the Name of node:");
    final String nodeName = scanner.nextLine().strip();
    System.out.println("Do you want to enter additional details?(y/n)");
    String choice = scanner.nextLine().strip();
    final HashMap<String, String> additionalInfo = new HashMap<>();
    while ("y".equals(choice)) {
      System.out.println("Enter key:");
      final String key = scanner.nextLine().strip();
      System.out.println("Enter value:");
      final String value = scanner.nextLine().strip();
      additionalInfo.put(key, value);
      System.out.println("Do you want to enter more additional details?(y/n)");
      choice = scanner.nextLine().strip();
    }
    applicationController.addNewNode(nodeId, nodeName, additionalInfo);
    System.out.println("Node Added Successfully.");
  }

  /**
   * Delete node.
   */
  private void deleteNode() {
    System.out.println("Enter Id of Node to be deleted:");
    final String nodeId = scanner.nextLine().strip();
    applicationController.deleteNode(nodeId);
    System.out.println("Node Deleted Successfully.");
  }

  /**
   * Add new dependency.
   */
  private void addDependency() {
    System.out.println("Enter Id of Parent:");
    final String parentId = scanner.nextLine().strip();
    System.out.println("Enter Id of Child:");
    final String childId = scanner.nextLine().strip();
    applicationController.addDependency(parentId, childId);
    System.out.println("Dependency Added Successfully.");
  }

  /**
   * Delete dependency.
   */
  private void deleteDependency() {
    System.out.println("Enter Id of Parent:");
    final String parentId = scanner.nextLine().strip();
    System.out.println("Enter Id of Child:");
    final String childId = scanner.nextLine().strip();
    applicationController.deleteDependency(parentId, childId);
    System.out.println("Dependency deleted Successfully.");
  }

  /**
   * Show immediate parents.
   */
  private void showImmediateParents() {
    System.out.println("Enter Id of Node to find parents: ");
    final String nodeId = scanner.nextLine().strip();
    final Set<Node> parents = applicationController.getParents(nodeId);
    System.out.println("The immediate parents are: ");
    System.out.println(parents);
  }

  /**
   * Show immediate children.
   */
  private void showImmediateChildren() {
    System.out.println("Enter Id of Node to find children: ");
    final String nodeId = scanner.nextLine().strip();
    final Set<Node> children = applicationController.getChildren(nodeId);
    System.out.println("The immediate children are: ");
    System.out.println(children);
  }

  /**
   * Show ancestors.
   */
  private void showAncestors() {
    System.out.println("Enter Id of Node to find ancestors: ");
    final String nodeId = scanner.nextLine().strip();
    final Set<Node> ancestors = applicationController.getAncestors(nodeId);
    System.out.println("The ancestors of Node are: ");
    System.out.println(ancestors);
  }

  /**
   * Show descendants.
   */
  private void showDescendants() {
    System.out.println("Enter Id of Node to find descendants: ");
    final String nodeId = scanner.nextLine().strip();
    final Set<Node> descendants = applicationController.getDescendants(nodeId);
    System.out.println("The descendant of Node are: ");
    System.out.println(descendants);
  }

  /**
   * Terminate.
   */
  private void terminate() {
    System.exit(0);
  }

  /**
   * Show errors.
   *
   * @param error the error
   */
  private void showErrors(final String error) {
    System.out.println("The following error occurred: ");
    System.out.println(error);
  }
}
