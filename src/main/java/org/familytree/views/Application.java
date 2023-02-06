package org.familytree.views;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import lombok.extern.log4j.Log4j2;
import org.familytree.controllers.ApplicationController;
import org.familytree.models.Node;
import org.familytree.models.NodeMapper;
import org.familytree.services.impl.DependencyGraphServiceImpl;
import org.familytree.services.impl.NodeMapperServiceImpl;
import org.familytree.services.impl.NodeServiceImpl;

/**
 * The type Application.
 */
@Log4j2
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
    log.info(
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
          log.info("select correct option");
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
    log.info("Please enter the id of node:");
    final String nodeId = scanner.nextLine().strip();
    log.info("Please enter the Name of node:");
    final String nodeName = scanner.nextLine().strip();
    log.info("Do you want to enter additional details?(y/n)");
    String choice = scanner.nextLine().strip();
    final HashMap<String, String> additionalInfo = new HashMap<>();
    while ("y".equals(choice)) {
      log.info("Enter key:");
      final String key = scanner.nextLine().strip();
      log.info("Enter value:");
      final String value = scanner.nextLine().strip();
      additionalInfo.put(key, value);
      log.info("Do you want to enter more additional details?(y/n)");
      choice = scanner.nextLine().strip();
    }
    applicationController.addNewNode(nodeId, nodeName, additionalInfo);
    log.info("Node Added Successfully.");
  }

  /**
   * Delete node.
   */
  private void deleteNode() {
    log.info("Enter Id of Node to be deleted:");
    final String nodeId = scanner.nextLine().strip();
    applicationController.deleteNode(nodeId);
    log.info("Node Deleted Successfully.");
  }

  /**
   * Add new dependency.
   */
  private void addDependency() {
    log.info("Enter Id of Parent:");
    final String parentId = scanner.nextLine().strip();
    log.info("Enter Id of Child:");
    final String childId = scanner.nextLine().strip();
    applicationController.addDependency(parentId, childId);
    log.info("Dependency Added Successfully.");
  }

  /**
   * Delete dependency.
   */
  private void deleteDependency() {
    log.info("Enter Id of Parent:");
    final String parentId = scanner.nextLine().strip();
    log.info("Enter Id of Child:");
    final String childId = scanner.nextLine().strip();
    applicationController.deleteDependency(parentId, childId);
    log.info("Dependency deleted Successfully.");
  }

  /**
   * Show immediate parents.
   */
  private void showImmediateParents() {
    log.info("Enter Id of Node to find parents: ");
    final String nodeId = scanner.nextLine().strip();
    final Set<Node> parents = applicationController.getParents(nodeId);
    log.info("The immediate parents are: ");
    log.info(parents);
  }

  /**
   * Show immediate children.
   */
  private void showImmediateChildren() {
    log.info("Enter Id of Node to find children: ");
    final String nodeId = scanner.nextLine().strip();
    final Set<Node> children = applicationController.getChildren(nodeId);
    log.info("The immediate children are: ");
    log.info(children);
  }

  /**
   * Show ancestors.
   */
  private void showAncestors() {
    log.info("Enter Id of Node to find ancestors: ");
    final String nodeId = scanner.nextLine().strip();
    final Set<Node> ancestors = applicationController.getAncestors(nodeId);
    log.info("The ancestors of Node are: ");
    log.info(ancestors);
  }

  /**
   * Show descendants.
   */
  private void showDescendants() {
    log.info("Enter Id of Node to find descendants: ");
    final String nodeId = scanner.nextLine().strip();
    final Set<Node> descendants = applicationController.getDescendants(nodeId);
    log.info("The descendant of Node are: ");
    log.info(descendants);
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
    log.error("The following error occurred: ");
    log.error(error);
  }
}
