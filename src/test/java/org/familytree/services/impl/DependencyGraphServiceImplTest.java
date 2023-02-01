package org.familytree.services.impl;

import org.familytree.constants.ExceptionMessage;
import org.familytree.exceptions.NodeException;
import org.familytree.models.DependencyGraph;
import org.familytree.models.Node;
import org.familytree.services.DependencyGraphService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DependencyGraphServiceImplTest {

  @InjectMocks
  DependencyGraphServiceImpl dependencyGraphService;
  @Mock
  DependencyGraph dependencyGraph;

  @Test
  void addNodeWhenIdPresent() {
    Node node = Node.builder().nodeId("node 1").build();
    Exception exception = assertThrows(NodeException.class,
        () -> dependencyGraphService.addNode(node));
    assertEquals(ExceptionMessage.NODE_PRESENT, exception.getMessage());
  }

  @Test
  void addNodeWhenIdAbsent() {
    try {
      Node node = Node.builder().nodeId("node 1").build();
      dependencyGraphService.addNode(node);
    } catch (Exception e) {
      fail("Exception not expected");
    }
  }

  @Test
  void isNodeIdPresentWhenTrue() {
    assertEquals(true, dependencyGraphService.isNodeIdPresent("node 1"));
  }

  @Test
  void isNodeIdPresentWhenFalse() {
    assertEquals(false, dependencyGraphService.isNodeIdPresent("node 2"));
  }

  @Test
  void deleteNodeWhenAbsent() {
    Exception exception = assertThrows(NodeException.class,
        () -> dependencyGraphService.deleteNode("node 1"));
    assertEquals(ExceptionMessage.NODE_ABSENT, exception.getMessage());
  }

  @Test
  void deleteNodeWhenPresent() {
    try {
      dependencyGraphService.deleteNode("node 1");
    } catch (Exception e) {
      fail("Exception not expected");
    }
  }

  @Test
  void getNodeByIdWhenAbsent() {
    Exception exception = assertThrows(NodeException.class,
        () -> dependencyGraphService.getNodeById("node 1"));
    assertEquals(ExceptionMessage.NODE_ABSENT, exception.getMessage());
  }

  @Test
  void getNodeByIdWhenPresent() {
    try {
      dependencyGraphService.getNodeById("node 1");
    } catch (Exception e) {
      fail("Exception not expected");
    }
  }

  @Test
  void deleteDependencyWhenPresent() {
    Exception exception = assertThrows(NodeException.class,
        () -> dependencyGraphService.deleteDependency("node 1", "node 2"));
    assertEquals(ExceptionMessage.NO_DEPENDENCY, exception.getMessage());
  }

  @Test
  void deleteDependencyWhenAbsent() {
    try {
      dependencyGraphService.deleteDependency("node 1", "node 2");
    } catch (Exception e) {
      fail("Exception not expected");
    }
  }
}