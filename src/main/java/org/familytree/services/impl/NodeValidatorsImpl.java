package org.familytree.services.impl;

import java.util.HashMap;
import org.familytree.services.NodeValidators;

public class NodeValidatorsImpl implements NodeValidators {
  @Override
  public void validateNode(String nodeId, String nodeName, HashMap<String, String> additionalInfo) {
    //check for bean validation violations
  }
}
