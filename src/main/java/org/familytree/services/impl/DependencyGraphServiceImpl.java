package org.familytree.services.impl;

import org.familytree.constants.ExceptionMessage;
import org.familytree.exceptions.NodeException;
import org.familytree.models.DependencyGraph;
import org.familytree.models.Node;
import org.familytree.services.DependencyGraphService;

/**
 * The type Dependency graph service.
 */
public class DependencyGraphServiceImpl implements DependencyGraphService {
  /**
   * The Dependency graph.
   */
  private final DependencyGraph dependencyGraph;

  /**
   * Instantiates a new Dependency graph service.
   *
   * @param dependencyGraph the dependency graph
   */
  public DependencyGraphServiceImpl(final DependencyGraph dependencyGraph) {
    this.dependencyGraph = dependencyGraph;
  }

  @Override
  public void addNewNode(final Node node) {
    if (isNodeIdPresent(node.getNodeId())) {
      throw new NodeException(ExceptionMessage.NODE_PRESENT);
    }
    dependencyGraph.addNode(node);
  }

  @Override
  public Boolean isNodeIdPresent(final String nodeId) {
    return dependencyGraph.isNodeIdPresent(nodeId);
  }

  @Override
  public void deleteNode(final String nodeId) {
    if (!isNodeIdPresent(nodeId)) {
      throw new NodeException(ExceptionMessage.NODE_ABSENT);
    }
    dependencyGraph.deleteNode(nodeId);
  }

  @Override
  public Node getNodeById(final String nodeId) {
    if (!isNodeIdPresent(nodeId)) {
      throw new NodeException(ExceptionMessage.NODE_ABSENT);
    }
    return dependencyGraph.getNodeById(nodeId);
  }
}
