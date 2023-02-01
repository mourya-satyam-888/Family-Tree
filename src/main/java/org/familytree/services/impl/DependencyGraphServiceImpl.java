package org.familytree.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import org.familytree.models.DependencyGraph;
import org.familytree.models.Node;
import org.familytree.services.DependencyGraphService;

public class DependencyGraphServiceImpl implements DependencyGraphService {
  DependencyGraph dependencyGraph;

  public DependencyGraphServiceImpl(DependencyGraph dependencyGraph) {
    this.dependencyGraph = dependencyGraph;
  }

  @Override
  public void addNode(Node node) {
    //validate node id by calling isNodePresent
    //if no error add node
  }

  @Override
  public Boolean isNodeIdPresent(String nodeId) {
    return null;
  }

  @Override
  public void deleteNode(String nodeId) {
    //validate and delete node
  }

  @Override
  public Node getNodeById(String nodeId) {
    //validate nodeId by is node present
    //call getNodeById
    return null;
  }

  @Override
  public void deleteDependency(String parentId, String childId) {

  }
}
