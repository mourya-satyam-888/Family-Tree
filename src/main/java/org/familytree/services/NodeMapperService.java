package org.familytree.services;

import org.familytree.models.Node;

/**
 * The interface Node mapper service.
 */
public interface NodeMapperService {
  /**
   * Add new node.
   *
   * @param node the node
   */
  void addNode(Node node);

  /**
   * Is node id present boolean.
   *
   * @param nodeId the node id
   * @return the boolean
   */
  Boolean isNodeIdPresent(String nodeId);

  /**
   * Delete node.
   *
   * @param nodeId the node id
   */
  void deleteNode(String nodeId);

  /**
   * Gets node by id.
   *
   * @param nodeId the node id
   * @return the node by id
   */
  Node getNodeById(String nodeId);
}
