package org.familytree.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import org.familytree.models.Node;
import org.familytree.services.DependencyGraphService;

public class DependencyGraphServiceImpl implements DependencyGraphService {
  @Override
  public void addNode(String nodeId, String name, HashMap<String, String> additionalInfo) {

  }

  @Override
  public void deleteNode(String nodeId) {

  }

  @Override
  public void addDependency(String parentId, String childId) {

  }

  @Override
  public void deleteDependency(String parentId, String childId) {

  }

  @Override
  public ArrayList<Node> getImmediateParents(String childId) {
    return null;
  }

  @Override
  public ArrayList<Node> getImmediateChildren(String parentId) {
    return null;
  }

  @Override
  public ArrayList<Node> getAncestors(String childId) {
    return null;
  }

  @Override
  public ArrayList<Node> getDescendants(String parentId) {
    return null;
  }

  @Override
  public ArrayList<Node> traverseChildDependency(Node node) {
    return null;
  }

  @Override
  public ArrayList<Node> traverseParentDependency(Node node) {
    return null;
  }
}
