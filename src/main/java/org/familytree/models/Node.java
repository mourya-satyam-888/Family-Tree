package org.familytree.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Node {
  //there would be need of developing counter to auto increment Id for each new node
  @NotEmpty(message = "Id should not be empty")
  private String nodeId;
  @NotEmpty(message = "Name should not be empty")
  private String nodeName;
  private Map<String, String> nodeAdditionalInfo;
  private Set<Node> parents;
  private Set<Node> children;
}
