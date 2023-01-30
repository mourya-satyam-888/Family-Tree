package org.familytree.models;

import java.util.HashMap;
import java.util.Map;

public class DependencyGraph {
  private final Map<String, Node> nodes = new HashMap<>();
  private final DependencyGraph INSTANCE = new DependencyGraph();

  private DependencyGraph() {
    //singleton pattern
  }

  public DependencyGraph getInstance() {
    return INSTANCE;
  }

  public void addNode(Node node) {
  }

  public void deleteNode(String nodeId) {
  }

  public Node getNodeById(String nodeId) {
    //this should return the node mapped to nodeId
    return null;
  }
}
