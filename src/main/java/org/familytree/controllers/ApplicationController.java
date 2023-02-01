package org.familytree.controllers;

import java.util.Map;
import java.util.Set;
import org.familytree.models.Node;
import org.familytree.services.DependencyGraphService;
import org.familytree.services.NodeService;

/**
 * The type Application controller.
 */
public class ApplicationController {

  /**
   * The Dependency graph service.
   */
  private final DependencyGraphService dependencyGraphService;
  /**
   * The Node service.
   */
  private final NodeService nodeService;

  /**
   * Instantiates a new Application controller.
   *
   * @param dependencyGraphService the dependency graph service
   * @param nodeService            the node service
   */
  public ApplicationController(final DependencyGraphService dependencyGraphService,
                               final NodeService nodeService) {
    this.dependencyGraphService = dependencyGraphService;
    this.nodeService = nodeService;
  }

  /**
   * Add new node.
   *
   * @param nodeId         the node id
   * @param nodeName       the node name
   * @param additionalInfo the additional info
   */
  public void addNewNode(final String nodeId, final String nodeName,
                         final Map<String, String> additionalInfo) {
    dependencyGraphService.addNewNode(nodeService.validateAndCreateNode(
        nodeId, nodeName, additionalInfo));
  }

  /**
   * Delete node.
   *
   * @param nodeId the node id
   */
  public void deleteNode(final String nodeId) {
    nodeService.deleteNodeAndAllDependency(dependencyGraphService.getNodeById(nodeId));
    dependencyGraphService.deleteNode(nodeId);
  }

  /**
   * Add new dependency.
   *
   * @param parentId the parent id
   * @param childId  the child id
   */
  public void addNewDependency(final String parentId, final String childId) {
    final Node parent = dependencyGraphService.getNodeById(parentId);
    final Node child = dependencyGraphService.getNodeById(childId);
    nodeService.addDependency(parent, child);
  }

  /**
   * Delete dependency.
   *
   * @param parentId the parent id
   * @param childId  the child id
   */
  public void deleteDependency(final String parentId, final String childId) {
    final Node parent = dependencyGraphService.getNodeById(parentId);
    final Node child = dependencyGraphService.getNodeById(childId);
    nodeService.deleteDependency(parent, child);
  }

  /**
   * Gets parents.
   *
   * @param childId the child id
   * @return the parents
   */
  public Set<Node> getParents(final String childId) {
    return nodeService.getParents(dependencyGraphService.getNodeById(childId));
  }

  /**
   * Gets children.
   *
   * @param parentId the parent id
   * @return the children
   */
  public Set<Node> getChildren(final String parentId) {
    return nodeService.getChildren(dependencyGraphService.getNodeById(parentId));
  }

  /**
   * Gets ancestors.
   *
   * @param childId the child id
   * @return the ancestors
   */
  public Set<Node> getAncestors(final String childId) {
    return nodeService.getAncestors(dependencyGraphService.getNodeById(childId));
  }

  /**
   * Gets descendants.
   *
   * @param parentId the parent id
   * @return the descendants
   */
  public Set<Node> getDescendants(final String parentId) {
    return nodeService.getDescendants(dependencyGraphService.getNodeById(parentId));
  }
}
