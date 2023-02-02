package org.familytree.services.impl;

import org.familytree.constants.ExceptionMessageConstants;
import org.familytree.exceptions.DependencyGraphException;
import org.familytree.models.Node;
import org.familytree.models.NodeMapper;
import org.familytree.services.NodeMapperService;

/**
 * The type Node mapper service.
 */
public class NodeMapperServiceImpl implements NodeMapperService {
  /**
   * The Node mapper.
   */
  private final NodeMapper nodeMapper;

  /**
   * Instantiates a new Node mapper service.
   *
   * @param nodeMapper the node mapper
   */
  public NodeMapperServiceImpl(final NodeMapper nodeMapper) {
    this.nodeMapper = nodeMapper;
  }

  @Override
  public void addNode(final Node node) {
    if (isNodeIdPresent(node.getNodeId())) {
      throw new DependencyGraphException(ExceptionMessageConstants.NODE_PRESENT);
    }
    nodeMapper.addNode(node);
  }

  @Override
  public Boolean isNodeIdPresent(final String nodeId) {
    return nodeMapper.isNodeIdPresent(nodeId);
  }

  @Override
  public void deleteNode(final String nodeId) {
    if (!isNodeIdPresent(nodeId)) {
      throw new DependencyGraphException(ExceptionMessageConstants.NODE_ABSENT);
    }
    nodeMapper.deleteNode(nodeId);
  }

  @Override
  public Node getNodeById(final String nodeId) {
    if (!isNodeIdPresent(nodeId)) {
      throw new DependencyGraphException(ExceptionMessageConstants.NODE_ABSENT);
    }
    return nodeMapper.getNodeById(nodeId);
  }
}
