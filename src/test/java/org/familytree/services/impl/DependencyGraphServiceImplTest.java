package org.familytree.services.impl;

import org.familytree.constants.ExceptionMessage;
import org.familytree.exceptions.NodeException;
import org.familytree.models.DependencyGraph;
import org.familytree.models.Node;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;


@ExtendWith(MockitoExtension.class)
class DependencyGraphServiceImplTest {

  @InjectMocks
  DependencyGraphServiceImpl dependencyGraphService;
  @Mock
  DependencyGraph dependencyGraph;

  @Test
  void addNodeWhenIdPresent() {
    Node node = Node.builder().nodeId("node 1").build();
    Mockito.when(dependencyGraph.isNodeIdPresent(Mockito.anyString())).thenReturn(true);
    Exception exception = assertThrows(NodeException.class,
        () -> dependencyGraphService.addNewNode(node));
    assertEquals(ExceptionMessage.NODE_PRESENT, exception.getMessage());
    Mockito.verify(dependencyGraph).isNodeIdPresent(Mockito.anyString());
  }

  @Test
  void addNodeWhenIdAbsent() {
    try {
      Node node = Node.builder().nodeId("node 1").build();
      Mockito.when(dependencyGraph.isNodeIdPresent(Mockito.anyString())).thenReturn(false);
      dependencyGraphService.addNewNode(node);
      Mockito.verify(dependencyGraph).isNodeIdPresent(Mockito.anyString());
    } catch (Exception e) {
      fail("Exception not expected");
    }
  }

  @Test
  void deleteNodeWhenAbsent() {
    Mockito.when(dependencyGraph.isNodeIdPresent(Mockito.anyString())).thenReturn(false);
    Exception exception = assertThrows(NodeException.class,
        () -> dependencyGraphService.deleteNode("node 1"));
    assertEquals(ExceptionMessage.NODE_ABSENT, exception.getMessage());
    Mockito.verify(dependencyGraph).isNodeIdPresent(Mockito.anyString());
  }

  @Test
  void deleteNodeWhenPresent() {
    try {
      Mockito.when(dependencyGraph.isNodeIdPresent(Mockito.anyString())).thenReturn(true);
      dependencyGraphService.deleteNode("node 1");
      Mockito.verify(dependencyGraph).isNodeIdPresent(Mockito.anyString());

    } catch (Exception e) {
      fail("Exception not expected");
    }
  }

  @Test
  void getNodeByIdWhenAbsent() {
    Mockito.when(dependencyGraph.isNodeIdPresent(Mockito.anyString())).thenReturn(false);
    Exception exception = assertThrows(NodeException.class,
        () -> dependencyGraphService.getNodeById("node 1"));
    assertEquals(ExceptionMessage.NODE_ABSENT, exception.getMessage());
    Mockito.verify(dependencyGraph).isNodeIdPresent(Mockito.anyString());
  }

  @Test
  void getNodeByIdWhenPresent() {
    try {
      Mockito.when(dependencyGraph.isNodeIdPresent(Mockito.anyString())).thenReturn(true);
      Mockito.when(dependencyGraph.getNodeById(Mockito.anyString()))
          .thenReturn(Node.builder().build());
      dependencyGraphService.getNodeById("node 1");
      Mockito.verify(dependencyGraph).isNodeIdPresent(Mockito.anyString());
      Mockito.verify(dependencyGraph).getNodeById(Mockito.anyString());
    } catch (Exception e) {
      fail("Exception not expected");
    }
  }
}