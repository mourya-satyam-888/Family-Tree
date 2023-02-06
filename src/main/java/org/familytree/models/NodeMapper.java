package org.familytree.models;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Dependency graph.
 */
public final class NodeMapper {
  /**
   * The Nodes.
   */
  private final Map<String, Node> nodes = new HashMap<>();
  /**
   * The constant INSTANCE.
   */
  private static final NodeMapper INSTANCE = new NodeMapper();

  /**
   * Instantiates a new Dependency graph.
   */
  private NodeMapper() {
    //singleton pattern
  }

  /**
   * Gets instance.
   *
   * @return the instance
   */
  public static NodeMapper getInstance() {
    return INSTANCE;
  }

  /**
   * Add node.
   *
   * @param node the node
   */
  public void addNode(final Node node) {
    nodes.put(node.getNodeId(), node);
  }

  /**
   * Delete node.
   *
   * @param nodeId the node id
   */
  public void deleteNode(final String nodeId) {
    nodes.remove(nodeId);
  }

  /**
   * Gets node by id.
   *
   * @param nodeId the node id
   * @return the node by id
   */
  public Node getNodeById(final String nodeId) {
    return nodes.get(nodeId);
  }

  /**
   * Is node id present boolean.
   *
   * @param nodeId the node id
   * @return the boolean
   */
  public Boolean isNodeIdPresent(final String nodeId) {
    return nodes.containsKey(nodeId);
  }
}
