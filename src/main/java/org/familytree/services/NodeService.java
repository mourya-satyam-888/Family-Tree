package org.familytree.services;

import java.util.Map;
import java.util.Set;
import org.familytree.models.Node;

/**
 * The interface Node service.
 */
public interface NodeService {
  /**
   * Validate and create node node.
   *
   * @param nodeId         the node id
   * @param nodeName       the node name
   * @param additionalInfo the additional info
   * @return the node
   */
  Node validateAndCreateNode(String nodeId, String nodeName,
                             Map<String, String> additionalInfo);

  /**
   * Validate node.
   *
   * @param node the node
   */
  void validateNode(Node node);


  /**
   * Gets parents.
   *
   * @param child the child
   * @return the parents
   */
  Set<Node> getParents(Node child);

  /**
   * Gets children.
   *
   * @param parent the parent
   * @return the children
   */
  Set<Node> getChildren(Node parent);

}
