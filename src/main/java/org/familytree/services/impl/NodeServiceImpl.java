package org.familytree.services.impl;

import java.util.HashMap;
import java.util.Set;
import org.familytree.models.Node;
import org.familytree.services.NodeService;

public class NodeServiceImpl implements NodeService {
  public void addDependency(Node parent, Node child) {
    //check for cyclic dependency
    //parent.addChild
    //child.addParent
  }

  @Override
  public Boolean isCyclicDependency(Node parent, Node child) {
    //whole logic for cyclic dependency
    return null;
  }

  @Override
  public void deleteDependency(Node parent, Node child) {

  }

  @Override
  public Boolean isParentChildRelationShip(Node parent, Node child) {
    //check for parent child relationship
    return null;
  }

  @Override
  public Node validateAndCreateNode(String nodeId, String nodeName, HashMap<String, String> additionalInfo) {
    //create node
    //validateNode
    //call addNode
    return null;
  }

  @Override
  public void validateNode(Node node) {
    //validate violations
  }

  @Override
  public Set<Node> getParents(Node child) {
    //get from child.getParents
    return null;
  }

  @Override
  public Set<Node> getChildren(Node parent) {
    //get from parent.getChildren
    return null;
  }

  @Override
  public Set<Node> getAncestors(Node child) {
    //get ancestors
    //need to write logic here
    return null;
  }

  @Override
  public Set<Node> getDescendants(Node parent) {
    //get descendants
    //need to write logic
    return null;
  }
}
