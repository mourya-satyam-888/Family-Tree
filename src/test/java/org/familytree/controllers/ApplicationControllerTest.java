package org.familytree.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import org.familytree.constants.ExceptionMessage;
import org.familytree.exceptions.NodeException;
import org.familytree.exceptions.ValidationException;
import org.familytree.models.Node;
import org.familytree.services.DependencyGraphService;
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
  DependencyGraphService dependencyGraphService;
  List<Node> nodes;
  HashMap<String, Node> nodeMapping = new HashMap<>();

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
    nodes.forEach(t -> nodeMapping.put(t.getNodeId(), t));
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
    Mockito.doThrow(new NodeException(ExceptionMessage.NODE_PRESENT))
        .when(dependencyGraphService).addNewNode(Mockito.any());
    Exception exception = assertThrows(NodeException.class, () ->
        applicationController.addNewNode("node 1", "", new HashMap<>()));
    assertEquals(ExceptionMessage.NODE_PRESENT, exception.getMessage());
    Mockito.verify(nodeService).validateAndCreateNode(Mockito.anyString(),
        Mockito.anyString(), Mockito.anyMap());
    Mockito.verify(dependencyGraphService).addNewNode(Mockito.any());
  }

  @Test
  void addNewNodeWhenAbsent() {
    Mockito.when(nodeService.validateAndCreateNode(Mockito.anyString(),
        Mockito.anyString(), Mockito.anyMap())).thenReturn(Node.builder().build());
    Mockito.doNothing()
        .when(dependencyGraphService).addNewNode(Mockito.any());
    applicationController.addNewNode("node 1", "", new HashMap<>());
    Mockito.verify(nodeService).validateAndCreateNode(Mockito.anyString(),
        Mockito.anyString(), Mockito.anyMap());
    Mockito.verify(dependencyGraphService).addNewNode(Mockito.any());
  }

  @Test
  void deleteNode() {
  }

  @Test
  void addNewDependency() {
  }

  @Test
  void deleteDependency() {
  }

  @Test
  void getParents() {
  }

  @Test
  void getChildren() {
  }

  @Test
  void getAncestors() {
  }

  @Test
  void getDescendants() {
  }
}