package org.familytree.controllers;

import java.util.Map;
import java.util.Set;
import org.familytree.models.Node;
import org.familytree.services.DependencyGraphService;
import org.familytree.services.NodeMapperService;
import org.familytree.services.NodeService;

/**
 * The type Application controller.
 */
public class ApplicationController {

  /**
   * The Node mapper service.
   */
  private final NodeMapperService nodeMapperService;
  /**
   * The Node service.
   */
  private final NodeService nodeService;
  /**
   * The Dependency graph service.
   */
  private final DependencyGraphService dependencyGraphService;

  /**
   * Instantiates a new Application controller.
   *
   * @param nodeMapperService the node mapper service
   * @param nodeService       the node service
   */
  public ApplicationController(final NodeMapperService nodeMapperService,
                               final NodeService nodeService,
                               final DependencyGraphService dependencyGraphService) {
    this.nodeMapperService = nodeMapperService;
    this.nodeService = nodeService;
    this.dependencyGraphService = dependencyGraphService;
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
    nodeMapperService.addNode(nodeService.validateAndCreateNode(
        nodeId, nodeName, additionalInfo));
  }

  /**
   * Delete node.
   *
   * @param nodeId the node id
   */
  public void deleteNode(final String nodeId) {
    dependencyGraphService.deleteAllDependency(nodeMapperService.getNodeById(nodeId));
    nodeMapperService.deleteNode(nodeId);
  }

  /**
   * Add new dependency.
   *
   * @param parentId the parent id
   * @param childId  the child id
   */
  public void addDependency(final String parentId, final String childId) {
    final Node parent = nodeMapperService.getNodeById(parentId);
    final Node child = nodeMapperService.getNodeById(childId);
    dependencyGraphService.addDependency(parent, child);
  }

  /**
   * Delete dependency.
   *
   * @param parentId the parent id
   * @param childId  the child id
   */
  public void deleteDependency(final String parentId, final String childId) {
    final Node parent = nodeMapperService.getNodeById(parentId);
    final Node child = nodeMapperService.getNodeById(childId);
    dependencyGraphService.deleteDependency(parent, child);
  }

  /**
   * Gets parents.
   *
   * @param childId the child id
   * @return the parents
   */
  public Set<Node> getParents(final String childId) {
    return nodeService.getParents(nodeMapperService.getNodeById(childId));
  }

  /**
   * Gets children.
   *
   * @param parentId the parent id
   * @return the children
   */
  public Set<Node> getChildren(final String parentId) {
    return nodeService.getChildren(nodeMapperService.getNodeById(parentId));
  }

  /**
   * Gets ancestors.
   *
   * @param childId the child id
   * @return the ancestors
   */
  public Set<Node> getAncestors(final String childId) {
    return dependencyGraphService.getAncestors(nodeMapperService.getNodeById(childId));
  }

  /**
   * Gets descendants.
   *
   * @param parentId the parent id
   * @return the descendants
   */
  public Set<Node> getDescendants(final String parentId) {
    return dependencyGraphService.getDescendants(nodeMapperService.getNodeById(parentId));
  }
}
