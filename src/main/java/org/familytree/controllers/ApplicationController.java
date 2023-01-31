package org.familytree.controllers;

import java.util.HashMap;
import java.util.Set;
import org.familytree.models.Node;
import org.familytree.services.DependencyGraphService;
import org.familytree.services.NodeService;

public class ApplicationController {

  DependencyGraphService dependencyGraphService;
  NodeService nodeService;

  public void addNewNode(String nodeId, String nodeName, HashMap<String, String> additionalInfo) {
    //nodeService createAndValidateNode
    //dependencyGraphService addNode
  }

  public void deleteNode(String nodeId) {
    //dependencyGraphService deleteNode
  }

  public void addNewDependency(String parentId, String childId) {
    //dependencyGraphService getNodeById for parentId and childId
    //Node service add Dependency
  }

  public void deleteDependency(String parentId, String childId) {
    //dependencyGraphService getNodeById for parentId and childId
    //delete dependency
  }

  public Set<Node> getParents(String childId) {
    //validate and get node
    //just redirect to Node Services
    return null;
  }

  public Set<Node> getChildren(String parentId) {
    //validate and get node
    //just redirect
    return null;
  }

  public Set<Node> getAncestors(String childId) {
    //validate and getNode
    //just redirect
    return null;
  }

  public Set<Node> getDescendants(String parentId) {
    //validate and getNode
    //just redirect
    return null;
  }

}
