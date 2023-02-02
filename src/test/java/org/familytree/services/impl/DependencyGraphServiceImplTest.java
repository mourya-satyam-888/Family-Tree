package org.familytree.services.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.familytree.constants.ExceptionMessageConstants;
import org.familytree.exceptions.DependencyGraphException;
import org.familytree.models.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(MockitoExtension.class)
public class DependencyGraphServiceImplTest {
  @InjectMocks
  DependencyGraphServiceImpl dependencyGraphService;
  /**
   * The Nodes.
   */
  List<Node> nodes;

  @BeforeEach
  void init() {
    //graph be like 1->2->3 and 4 alone
    Node node4 = Node.builder().nodeId("node4")
        .parents(new HashSet<>()).children(new HashSet<>()).build();
    Node node3 = Node.builder().nodeId("node3")
        .parents(new HashSet<>()).children(new HashSet<>()).build();
    Node node2 = Node.builder().nodeId("node2")
        .parents(new HashSet<>()).children(new HashSet<>(Arrays.asList(node3))).build();
    Node node1 = Node.builder().nodeId("node1")
        .parents(new HashSet<>()).children(new HashSet<>(Arrays.asList(node2))).build();
    node3.getParents().add(node2);
    node2.getParents().add(node1);
    nodes = Arrays.asList(node1, node2, node3, node4);
  }

  /**
   * Add dependency when cyclic.
   */
  @Test
  void addDependencyWhenCyclic() {
    Node parent = nodes.get(2);
    Node child = nodes.get(0);
    Exception exception = assertThrows(DependencyGraphException.class, () ->
        dependencyGraphService.addDependency(parent, child));
    assertEquals(ExceptionMessageConstants.CYCLIC_DEPENDENCY, exception.getMessage());
  }


  /**
   * Add dependency success.
   */
  @Test
  void addDependencySuccess() {
    try {
      Node parent = nodes.get(2);
      Node child = nodes.get(3);
      int childParentSizeExpected = child.getParents().size() + 1;
      int parentChildrenSizeExpected = parent.getChildren().size() + 1;
      dependencyGraphService.addDependency(parent, child);
      assertEquals(childParentSizeExpected, child.getParents().size());
      assertEquals(parentChildrenSizeExpected, parent.getChildren().size());
    } catch (Exception e) {
      fail("Exception not expected");
    }
  }

  /**
   * Delete dependency when present.
   */
  @Test
  void deleteDependencyWhenAbsent() {
    Node parent = nodes.get(2);
    Node child = nodes.get(3);
    Exception exception = assertThrows(DependencyGraphException.class,
        () -> dependencyGraphService.deleteDependency(parent, child));
    assertEquals(ExceptionMessageConstants.NO_DEPENDENCY, exception.getMessage());
  }

  /**
   * Delete dependency when absent.
   */
  @Test
  void deleteDependencyWhenPresent() {
    Node parent = nodes.get(1);
    Node child = nodes.get(2);
    try {
      dependencyGraphService.deleteDependency(parent, child);
    } catch (Exception e) {
      fail("Exception not expected");
    }
  }

  /**
   * Gets ancestors.
   */
  @Test
  void getAncestors() {
    Set<Node> ancestors = new HashSet<>(Arrays.asList(nodes.get(0), nodes.get(1)));
    Node node3 = nodes.get(2);
    assertEquals(ancestors, dependencyGraphService.getAncestors(node3));
  }

  /**
   * Gets descendants.
   */
  @Test
  void getDescendants() {
    Set<Node> descendants = new HashSet<>(Arrays.asList(nodes.get(1), nodes.get(2)));
    Node node1 = nodes.get(0);
    assertEquals(descendants, dependencyGraphService.getDescendants(node1));
  }

  /**
   * Delete node and all dependency.
   */
  @Test
  void deleteNodeAndAllDependency() {
    Node node2 = nodes.get(1);
    dependencyGraphService.deleteAllDependency(node2);
    assertEquals(0, nodes.get(0).getChildren().size());
    assertEquals(0, nodes.get(2).getParents().size());
  }

}
