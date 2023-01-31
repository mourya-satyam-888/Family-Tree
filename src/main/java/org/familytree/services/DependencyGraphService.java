package org.familytree.services;

import org.familytree.models.Node;

public interface DependencyGraphService {
  void addNode(Node node);

  Boolean isNodeIdPresent(String nodeId);

  void deleteNode(String nodeId);

  void deleteDependency(String parentId, String childId);

  Node getNodeById(String nodeId);
}
