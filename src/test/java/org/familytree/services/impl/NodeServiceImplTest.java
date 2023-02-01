package org.familytree.services.impl;

import java.util.HashMap;
import java.util.HashSet;
import org.familytree.constants.ExceptionMessage;
import org.familytree.exceptions.NodeException;
import org.familytree.exceptions.ValidationException;
import org.familytree.models.Node;
import org.familytree.services.NodeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * The type Node validators impl test.
 */
@ExtendWith(MockitoExtension.class)
class NodeServiceImplTest {
  /**
   * The Node validators.
   */
  @InjectMocks
  NodeServiceImpl nodeService;

  /**
   * Validate node when name empty.
   */
//expected isn't works in junit5
  @Test
  void validateNodeWhenNameEmpty() {
    Exception exception = assertThrows(ValidationException.class, () ->
        nodeService.validateAndCreateNode("Id1", "", new HashMap<>()));
    assertEquals(ExceptionMessage.EMPTY_NAME, exception.getMessage());
  }

  /**
   * Validate node when id empty.
   */
  @Test
  void validateNodeWhenIdEmpty() {
    Exception exception = assertThrows(ValidationException.class, () ->
        nodeService.validateAndCreateNode("", "name 1", new HashMap<>()));
    assertEquals(ExceptionMessage.EMPTY_ID, exception.getMessage());
  }

  /**
   * Validate node success.
   */
  @Test
  void validateNodeSuccess() {
    try {
      nodeService.validateAndCreateNode("id 1", "name 1", new HashMap<>());
    } catch (ValidationException ex) {
      fail("No exception expected");
    }
  }

  @Test
  void addDependencyWhenCyclic() {
    Node parent = Node.builder().nodeId("node 1").build();
    Node child = Node.builder().nodeId("node 2").build();
    Exception exception = assertThrows(NodeException.class, () ->
        nodeService.addDependency(parent, child));
    assertEquals(ExceptionMessage.CYCLIC_DEPENDENCY, exception.getMessage());
  }

  @Test
  void addDependencySuccess() {
    try {
      Node parent = Node.builder().nodeId("node 1").children(new HashSet<>()).build();
      Node child = Node.builder().nodeId("node 2").parents(new HashSet<>()).build();
      int childParentSizeExpected = child.getParents().size() + 1;
      int parentChildrenSizeExpected = parent.getChildren().size() + 1;
      nodeService.addDependency(parent, child);
      assertEquals(childParentSizeExpected, child.getParents().size());
      assertEquals(parentChildrenSizeExpected, parent.getChildren().size());
    } catch (Exception e) {
      fail("Exception not expected");
    }
  }

  @Test
  void isCyclicDependencyWhenPresent() {
    Boolean expected = true;
    Node parent = Node.builder().nodeId("node 1").build();
    Node child = Node.builder().nodeId("node 2").build();
    Boolean actual = nodeService.isCyclicDependency(parent, child);
    assertEquals(expected, actual);
  }

  @Test
  void isCyclicDependencyWhenAbsent() {
    Boolean expected = false;
    Node parent = Node.builder().nodeId("node 1").build();
    Node child = Node.builder().nodeId("node 2").build();
    Boolean actual = nodeService.isCyclicDependency(parent, child);
    assertEquals(expected, actual);
  }

}