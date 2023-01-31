package org.familytree.services.impl;

import org.familytree.services.GraphValidators;

public class GraphValidatorsImpl implements GraphValidators {
  @Override
  public void validateNodeId(String nodeId) {
    //validate whether Id present or absent
  }

  @Override
  public void validateCyclicDependency(String parentId, String childId) {
    //validate parentId childId
    //validate for cyclicDependency
  }

  @Override
  public void validateParentChildRelationship(String parentId, String childId) {
    //validate parentId childId
    //validate if relationship exists or not
  }

  @Override
  public void validateParentChildId(String parentId, String childId) {
    //validate parentId and childId
  }
}
