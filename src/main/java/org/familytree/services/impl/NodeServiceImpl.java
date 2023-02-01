package org.familytree.services.impl;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.familytree.constants.ExceptionMessage;
import org.familytree.exceptions.NodeException;
import org.familytree.exceptions.ValidationException;
import org.familytree.models.Node;
import org.familytree.services.NodeService;

/**
 * The type Node service.
 */
public class NodeServiceImpl implements NodeService {
  /**
   * The constant FACTORY.
   */
  private static final ValidatorFactory FACTORY = Validation.buildDefaultValidatorFactory();
  /**
   * The constant VALIDATOR.
   */
  private static final Validator VALIDATOR = FACTORY.getValidator();

  @Override
  public Node validateAndCreateNode(final String nodeId, final String nodeName,
                                    final Map<String, String> additionalInfo) {
    final Node node = Node.builder().nodeId(nodeId)
        .nodeName(nodeName).nodeAdditionalInfo(additionalInfo)
        .parents(new HashSet<>()).children(new HashSet<>()).build();
    validateNode(node);
    return node;
  }

  @Override
  public void validateNode(final Node node) {
    final Set<ConstraintViolation<Node>> violations = VALIDATOR.validate(node);
    violations.forEach(t -> {
      throw new ValidationException(t.getMessage());
    });
  }

  @Override
  public void addDependency(final Node parent, final Node child) {
    if (isCyclicDependency(parent, child)) {
      throw new NodeException(ExceptionMessage.CYCLIC_DEPENDENCY);
    }
    parent.getChildren().add(child);
    child.getParents().add(parent);
  }

  @Override
  public Boolean isCyclicDependency(final Node parent, final Node child) {
    return parent.equals(child) || getDescendants(child).contains(parent);
  }

  @Override
  public void deleteDependency(final Node parent, final Node child) {
    if (!isParentChildRelationShip(parent, child)) {
      throw new NodeException(ExceptionMessage.NO_DEPENDENCY);
    }
    parent.getChildren().remove(child);
    child.getParents().remove(parent);
  }

  @Override
  public void deleteNodeAndAllDependency(final Node node) {
    node.getChildren().forEach(t -> t.getParents().remove(node));
    node.getParents().forEach(t -> t.getChildren().remove(node));
  }

  @Override
  public Boolean isParentChildRelationShip(final Node parent, final Node child) {
    return parent.getChildren().contains(child);
  }

  @Override
  public Set<Node> getParents(final Node child) {
    return new HashSet<>(child.getParents());
  }

  @Override
  public Set<Node> getChildren(final Node parent) {
    return new HashSet<>(parent.getChildren());
  }

  @Override
  public Set<Node> getAncestors(final Node child) {
    return getAncestorsUtil(child, new HashSet<>());
  }

  @Override
  public Set<Node> getAncestorsUtil(final Node child, final Set<Node> visited) {
    final Set<Node> parents = new HashSet<>(child.getParents());
    visited.add(child);
    for (final Node node : child.getParents()) {
      if (!visited.contains(node)) {
        parents.addAll(getAncestorsUtil(node, visited));
      }
    }
    return parents;
  }

  @Override
  public Set<Node> getDescendants(final Node parent) {
    return getDescendantsUtil(parent, new HashSet<>());
  }

  @Override
  public Set<Node> getDescendantsUtil(final Node parent, final Set<Node> visited) {
    final Set<Node> children = new HashSet<>(parent.getChildren());
    visited.add(parent);
    for (final Node node : parent.getChildren()) {
      if (!visited.contains(node)) {
        children.addAll(getDescendantsUtil(node, visited));
      }
    }
    return children;
  }
}
