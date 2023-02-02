package org.familytree.services.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.familytree.constants.ExceptionMessageConstants;
import org.familytree.exceptions.ValidationException;
import org.familytree.models.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
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
   * The Nodes.
   */
  List<Node> nodes;

  /**
   * Init.
   */
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
   * Validate node when name empty.
   */
//expected isn't works in junit5
  @Test
  void validateNodeWhenNameEmpty() {
    Exception exception = assertThrows(ValidationException.class, () ->
        nodeService.validateAndCreateNode("Id1", "", new HashMap<>()));
    assertEquals(ExceptionMessageConstants.EMPTY_NAME, exception.getMessage());
  }

  /**
   * Validate node when id empty.
   */
  @Test
  void validateNodeWhenIdEmpty() {
    Exception exception = assertThrows(ValidationException.class, () ->
        nodeService.validateAndCreateNode("", "name 1", new HashMap<>()));
    assertEquals(ExceptionMessageConstants.EMPTY_ID, exception.getMessage());
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

  /**
   * Gets parents.
   */
  @Test
  void getParents() {
    Node node3 = nodes.get(2);
    Set<Node> parents = new HashSet<>(Arrays.asList(nodes.get(1)));
    assertEquals(parents, nodeService.getParents(node3));
  }

  /**
   * Gets children.
   */
  @Test
  void getChildren() {
    Node node2 = nodes.get(1);
    Set<Node> children = new HashSet<>(Arrays.asList(nodes.get(2)));
    assertEquals(children, nodeService.getChildren(node2));
  }
}