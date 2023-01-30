package org.familytree.services;

import java.util.ArrayList;
import java.util.HashMap;
import org.familytree.models.Node;

public interface DependencyGraphService {
  void addNode(String nodeId, String name, HashMap<String, String> additionalInfo);

  void deleteNode(String nodeId);

  void addDependency(String parentId, String childId);

  void deleteDependency(String parentId, String childId);

  ArrayList<Node> getImmediateParents(String childId);

  ArrayList<Node> getImmediateChildren(String parentId);

  ArrayList<Node> getAncestors(String childId);

  ArrayList<Node> getDescendants(String parentId);

  ArrayList<Node> traverseChildDependency(Node node);

  ArrayList<Node> traverseParentDependency(Node node);
}
