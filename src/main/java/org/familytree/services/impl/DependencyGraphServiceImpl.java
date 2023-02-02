package org.familytree.services.impl;

import java.util.HashSet;
import java.util.Set;
import org.familytree.constants.ExceptionMessage;
import org.familytree.exceptions.DependencyGraphException;
import org.familytree.models.Node;
import org.familytree.services.DependencyGraphService;

/**
 * The type Dependency graph service.
 */
public class DependencyGraphServiceImpl implements DependencyGraphService {
  @Override
  public void addDependency(final Node parent, final Node child) {
    if (isCyclicDependency(parent, child)) {
      throw new DependencyGraphException(ExceptionMessage.CYCLIC_DEPENDENCY);
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
      throw new DependencyGraphException(ExceptionMessage.NO_DEPENDENCY);
    }
    parent.getChildren().remove(child);
    child.getParents().remove(parent);
  }

  @Override
  public void deleteAllDependency(final Node node) {
    node.getChildren().forEach(t -> t.getParents().remove(node));
    node.getParents().forEach(t -> t.getChildren().remove(node));
  }

  @Override
  public Boolean isParentChildRelationShip(final Node parent, final Node child) {
    return parent.getChildren().contains(child);
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
