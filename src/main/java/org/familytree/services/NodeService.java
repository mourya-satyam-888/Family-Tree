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
   * Add dependency.
   *
   * @param parent the parent
   * @param child  the child
   */
  void addDependency(Node parent, Node child);

  /**
   * Delete dependency.
   *
   * @param parent the parent
   * @param child  the child
   */
  void deleteDependency(Node parent, Node child);

  /**
   * Delete node and all dependency.
   *
   * @param node the node
   */
  void deleteNodeAndAllDependency(Node node);

  /**
   * Is parent child relation ship boolean.
   *
   * @param parent the parent
   * @param child  the child
   * @return the boolean
   */
  Boolean isParentChildRelationShip(Node parent, Node child);

  /**
   * Is cyclic dependency boolean.
   *
   * @param parent the parent
   * @param child  the child
   * @return the boolean
   */
  Boolean isCyclicDependency(Node parent, Node child);

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

  /**
   * Gets ancestors.
   *
   * @param child the child
   * @return the ancestors
   */
  Set<Node> getAncestors(Node child);

  /**
   * Gets descendants.
   *
   * @param parent the parent
   * @return the descendants
   */
  Set<Node> getDescendants(Node parent);

  /**
   * Gets ancestors util.
   *
   * @param child the child
   * @return the ancestors util
   */
  Set<Node> getAncestorsUtil(Node child, Set<Node> visited);

  /**
   * Gets descendants util.
   *
   * @param parent the parent
   * @return the descendants util
   */
  Set<Node> getDescendantsUtil(Node parent, Set<Node> visited);
}
