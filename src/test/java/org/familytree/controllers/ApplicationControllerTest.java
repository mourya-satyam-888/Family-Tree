package org.familytree.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.familytree.constants.ExceptionMessage;
import org.familytree.exceptions.DependencyGraphException;
import org.familytree.exceptions.ValidationException;
import org.familytree.models.Node;
import org.familytree.services.DependencyGraphService;
import org.familytree.services.NodeMapperService;
import org.familytree.services.NodeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ApplicationControllerTest {
  @InjectMocks
  ApplicationController applicationController;
  @Mock
  NodeService nodeService;
  @Mock
  NodeMapperService nodeMapperService;
  @Mock
  DependencyGraphService dependencyGraphService;
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

  @Test
  void addNewNodeWhenNameEmpty() {
    Mockito.when(nodeService.validateAndCreateNode(Mockito.anyString(),
            Mockito.anyString(), Mockito.anyMap()))
        .thenThrow(new ValidationException(ExceptionMessage.EMPTY_NAME));
    Exception exception = assertThrows(ValidationException.class, () ->
        applicationController.addNewNode("node 1", "", new HashMap<>()));
    assertEquals(ExceptionMessage.EMPTY_NAME, exception.getMessage());
    Mockito.verify(nodeService).validateAndCreateNode(Mockito.anyString(),
        Mockito.anyString(), Mockito.anyMap());
  }

  @Test
  void addNewNodeWhenIdEmpty() {
    Mockito.when(nodeService.validateAndCreateNode(Mockito.anyString(),
            Mockito.anyString(), Mockito.anyMap()))
        .thenThrow(new ValidationException(ExceptionMessage.EMPTY_ID));
    Exception exception = assertThrows(ValidationException.class, () ->
        applicationController.addNewNode("", "name", new HashMap<>()));
    assertEquals(ExceptionMessage.EMPTY_ID, exception.getMessage());
    Mockito.verify(nodeService).validateAndCreateNode(Mockito.anyString(),
        Mockito.anyString(), Mockito.anyMap());
  }

  @Test
  void addNewNodeWhenPresent() {
    Mockito.when(nodeService.validateAndCreateNode(Mockito.anyString(),
        Mockito.anyString(), Mockito.anyMap())).thenReturn(Node.builder().build());
    Mockito.doThrow(new DependencyGraphException(ExceptionMessage.NODE_PRESENT))
        .when(nodeMapperService).addNode(Mockito.any());
    Exception exception = assertThrows(DependencyGraphException.class, () ->
        applicationController.addNewNode("node 1", "", new HashMap<>()));
    assertEquals(ExceptionMessage.NODE_PRESENT, exception.getMessage());
    Mockito.verify(nodeService).validateAndCreateNode(Mockito.anyString(),
        Mockito.anyString(), Mockito.anyMap());
    Mockito.verify(nodeMapperService).addNode(Mockito.any());
  }

  @Test
  void addNewNodeWhenAbsent() {
    Mockito.when(nodeService.validateAndCreateNode(Mockito.anyString(),
        Mockito.anyString(), Mockito.anyMap())).thenReturn(Node.builder().build());
    Mockito.doNothing()
        .when(nodeMapperService).addNode(Mockito.any());
    applicationController.addNewNode("node 1", "", new HashMap<>());
    Mockito.verify(nodeService).validateAndCreateNode(Mockito.anyString(),
        Mockito.anyString(), Mockito.anyMap());
    Mockito.verify(nodeMapperService).addNode(Mockito.any());
  }

  @Test
  void deleteNodeWhenAbsent() {
    Mockito.when(nodeMapperService.getNodeById(Mockito.anyString())).thenThrow(
        new DependencyGraphException(ExceptionMessage.NODE_ABSENT));
    Exception exception = assertThrows(DependencyGraphException.class,
        () -> applicationController.deleteNode("node 1"));
    assertEquals(ExceptionMessage.NODE_ABSENT, exception.getMessage());
  }

  @Test
  void deleteNodeWhenPresent() {
    Mockito.when(nodeMapperService.getNodeById(Mockito.anyString()))
        .thenReturn(Node.builder().build());
    Mockito.doNothing().when(dependencyGraphService).deleteAllDependency(Mockito.any());
    Mockito.doNothing().when(nodeMapperService).deleteNode(Mockito.anyString());
    try {
      applicationController.deleteNode("node 1");
    } catch (Exception e) {
      fail("Exception not expected");
    }
    Mockito.verify(nodeMapperService).getNodeById(Mockito.anyString());
    Mockito.verify(dependencyGraphService).deleteAllDependency(Mockito.any());
    Mockito.verify(nodeMapperService).deleteNode(Mockito.anyString());
  }

  @Test
  void addNewDependencyWhenCyclic() {
    Mockito.when(nodeMapperService.getNodeById(Mockito.anyString()))
        .thenReturn(Node.builder().build());
    Mockito.doThrow(new DependencyGraphException(ExceptionMessage.CYCLIC_DEPENDENCY))
        .when(dependencyGraphService).addDependency(Mockito.any(), Mockito.any());
    Exception exception = assertThrows(DependencyGraphException.class, () ->
        applicationController.addDependency("node 1", "node 2"));
    assertEquals(ExceptionMessage.CYCLIC_DEPENDENCY, exception.getMessage());
    Mockito.verify(nodeMapperService, Mockito.times(2)).getNodeById(Mockito.anyString());
    Mockito.verify(dependencyGraphService).addDependency(Mockito.any(), Mockito.any());
  }

  @Test
  void addNewDependencySuccess() {
    Mockito.when(nodeMapperService.getNodeById(Mockito.anyString()))
        .thenReturn(Node.builder().build());
    Mockito.doNothing().when(dependencyGraphService).addDependency(Mockito.any(), Mockito.any());
    try {
      applicationController.addDependency("node 1", "node 2");
    } catch (Exception e) {
      fail("Exception not expected");
    }
    Mockito.verify(nodeMapperService, Mockito.times(2)).getNodeById(Mockito.anyString());
    Mockito.verify(dependencyGraphService).addDependency(Mockito.any(), Mockito.any());
  }

  @Test
  void deleteDependencyWhenAbsent() {
    Mockito.when(nodeMapperService.getNodeById(Mockito.anyString()))
        .thenReturn(Node.builder().build());
    Mockito.doThrow(new DependencyGraphException(ExceptionMessage.NO_DEPENDENCY)).when(dependencyGraphService)
        .deleteDependency(Mockito.any(), Mockito.any());
    Exception exception = assertThrows(DependencyGraphException.class, () ->
        applicationController.deleteDependency("node 1", "node 2"));
    assertEquals(ExceptionMessage.NO_DEPENDENCY, exception.getMessage());
    Mockito.verify(nodeMapperService, Mockito.times(2))
        .getNodeById(Mockito.anyString());
    Mockito.verify(dependencyGraphService).deleteDependency(Mockito.any(), Mockito.any());
  }

  @Test
  void deleteDependencyWhenPresent() {
    Mockito.when(nodeMapperService.getNodeById(Mockito.anyString()))
        .thenReturn(Node.builder().build());
    Mockito.doNothing().when(dependencyGraphService)
        .deleteDependency(Mockito.any(), Mockito.any());
    try {
      applicationController.deleteDependency("node 1", "node 2");
    } catch (Exception e) {
      fail("Exception not expected");
    }
    Mockito.verify(nodeMapperService, Mockito.times(2))
        .getNodeById(Mockito.anyString());
    Mockito.verify(dependencyGraphService).deleteDependency(Mockito.any(), Mockito.any());
  }

  @Test
  void getParents() {
    Set<Node> parents = new HashSet<>(Arrays.asList(nodes.get(1)));
    Mockito.when(nodeMapperService.getNodeById(Mockito.anyString()))
        .thenReturn(nodes.get(2));
    Mockito.when(nodeService.getParents(Mockito.any())).thenReturn(parents);
    assertEquals(parents, applicationController.getParents("node 3"));
    Mockito.verify(nodeMapperService).getNodeById(Mockito.anyString());
    Mockito.verify(nodeService).getParents(Mockito.any());
  }

  @Test
  void getChildren() {
    Set<Node> children = new HashSet<>(Arrays.asList(nodes.get(2)));
    Mockito.when(nodeMapperService.getNodeById(Mockito.anyString()))
        .thenReturn(nodes.get(1));
    Mockito.when(nodeService.getChildren(Mockito.any())).thenReturn(children);
    assertEquals(children, applicationController.getChildren("node 2"));
    Mockito.verify(nodeMapperService).getNodeById(Mockito.anyString());
    Mockito.verify(nodeService).getChildren(Mockito.any());
  }

  @Test
  void getAncestors() {
    Set<Node> ancestors = new HashSet<>(Arrays.asList(nodes.get(1), nodes.get(0)));
    Mockito.when(nodeMapperService.getNodeById(Mockito.anyString()))
        .thenReturn(nodes.get(2));
    Mockito.when(dependencyGraphService.getAncestors(Mockito.any())).thenReturn(ancestors);
    assertEquals(ancestors, applicationController.getAncestors("node 3"));
    Mockito.verify(nodeMapperService).getNodeById(Mockito.anyString());
    Mockito.verify(dependencyGraphService).getAncestors(Mockito.any());
  }

  @Test
  void getDescendants() {
    Set<Node> descendants = new HashSet<>(Arrays.asList(nodes.get(1), nodes.get(2)));
    Mockito.when(nodeMapperService.getNodeById(Mockito.anyString()))
        .thenReturn(nodes.get(0));
    Mockito.when(dependencyGraphService.getDescendants(Mockito.any())).thenReturn(descendants);
    assertEquals(descendants, applicationController.getDescendants("node 3"));
    Mockito.verify(nodeMapperService).getNodeById(Mockito.anyString());
    Mockito.verify(dependencyGraphService).getDescendants(Mockito.any());
  }
}