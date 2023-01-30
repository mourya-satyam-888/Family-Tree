package org.familytree.services;

public interface GraphValidators {
  void validateNodeId(String nodeId);

  void validateCyclicDependency(String parentId, String childId);

  void validateParentChildRelationship(String parentId, String childId);

  void validateParentChildId(String parentId, String childId);
}
