package org.familytree.services;

import java.util.Set;
import org.familytree.models.Node;

/**
 * The interface Dependency graph service.
 */
public interface DependencyGraphService {
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
   * Delete all dependency.
   *
   * @param node the node
   */
  void deleteAllDependency(Node node);

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
   * @param child   the child
   * @param visited the visited
   * @return the ancestors util
   */
  Set<Node> getAncestorsUtil(Node child, Set<Node> visited);

  /**
   * Gets descendants util.
   *
   * @param parent  the parent
   * @param visited the visited
   * @return the descendants util
   */
  Set<Node> getDescendantsUtil(Node parent, Set<Node> visited);
}
