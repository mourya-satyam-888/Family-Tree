package org.familytree.services;

import java.util.HashMap;
import java.util.Set;
import org.familytree.models.Node;

public interface NodeService {
  Node validateAndCreateNode(String nodeId, String nodeName,
                             HashMap<String, String> additionalInfo);

  void validateNode(Node node);

  void addDependency(Node parent, Node child);

  void deleteDependency(Node parent, Node child);

  Boolean isParentChildRelationShip(Node parent, Node child);

  Boolean isCyclicDependency(Node parent, Node child);

  Set<Node> getParents(Node child);

  Set<Node> getChildren(Node parent);

  Set<Node> getAncestors(Node child);

  Set<Node> getDescendants(Node parent);
}
