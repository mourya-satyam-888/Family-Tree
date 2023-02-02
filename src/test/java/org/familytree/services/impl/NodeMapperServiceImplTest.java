package org.familytree.services.impl;

import org.familytree.constants.ExceptionMessage;
import org.familytree.exceptions.DependencyGraphException;
import org.familytree.models.NodeMapper;
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
class NodeMapperServiceImplTest {

  @InjectMocks
  NodeMapperServiceImpl nodeMapperService;
  @Mock
  NodeMapper nodeMapper;

  @Test
  void addNodeWhenIdPresent() {
    Node node = Node.builder().nodeId("node 1").build();
    Mockito.when(nodeMapper.isNodeIdPresent(Mockito.anyString())).thenReturn(true);
    Exception exception = assertThrows(DependencyGraphException.class,
        () -> nodeMapperService.addNode(node));
    assertEquals(ExceptionMessage.NODE_PRESENT, exception.getMessage());
    Mockito.verify(nodeMapper).isNodeIdPresent(Mockito.anyString());
  }

  @Test
  void addNodeWhenIdAbsent() {
    try {
      Node node = Node.builder().nodeId("node 1").build();
      Mockito.when(nodeMapper.isNodeIdPresent(Mockito.anyString())).thenReturn(false);
      nodeMapperService.addNode(node);
      Mockito.verify(nodeMapper).isNodeIdPresent(Mockito.anyString());
    } catch (Exception e) {
      fail("Exception not expected");
    }
  }

  @Test
  void deleteNodeWhenAbsent() {
    Mockito.when(nodeMapper.isNodeIdPresent(Mockito.anyString())).thenReturn(false);
    Exception exception = assertThrows(DependencyGraphException.class,
        () -> nodeMapperService.deleteNode("node 1"));
    assertEquals(ExceptionMessage.NODE_ABSENT, exception.getMessage());
    Mockito.verify(nodeMapper).isNodeIdPresent(Mockito.anyString());
  }

  @Test
  void deleteNodeWhenPresent() {
    try {
      Mockito.when(nodeMapper.isNodeIdPresent(Mockito.anyString())).thenReturn(true);
      nodeMapperService.deleteNode("node 1");
      Mockito.verify(nodeMapper).isNodeIdPresent(Mockito.anyString());

    } catch (Exception e) {
      fail("Exception not expected");
    }
  }

  @Test
  void getNodeByIdWhenAbsent() {
    Mockito.when(nodeMapper.isNodeIdPresent(Mockito.anyString())).thenReturn(false);
    Exception exception = assertThrows(DependencyGraphException.class,
        () -> nodeMapperService.getNodeById("node 1"));
    assertEquals(ExceptionMessage.NODE_ABSENT, exception.getMessage());
    Mockito.verify(nodeMapper).isNodeIdPresent(Mockito.anyString());
  }

  @Test
  void getNodeByIdWhenPresent() {
    try {
      Mockito.when(nodeMapper.isNodeIdPresent(Mockito.anyString())).thenReturn(true);
      Mockito.when(nodeMapper.getNodeById(Mockito.anyString()))
          .thenReturn(Node.builder().build());
      nodeMapperService.getNodeById("node 1");
      Mockito.verify(nodeMapper).isNodeIdPresent(Mockito.anyString());
      Mockito.verify(nodeMapper).getNodeById(Mockito.anyString());
    } catch (Exception e) {
      fail("Exception not expected");
    }
  }
}