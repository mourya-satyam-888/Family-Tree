package org.familytree.services;

import java.util.HashMap;

public interface NodeValidators {
  void validateNode(String nodeId, String nodeName, HashMap<String, String> additionalInfo);
}
