package org.familytree.models;

import jakarta.validation.constraints.NotEmpty;
import java.util.Map;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Node.
 */
@Getter
@Setter
@Builder
public class Node {
  /**
   * The Node id.
   */
  @NotEmpty(message = "Id should not be empty")
  private String nodeId;
  /**
   * The Node name.
   */
  @NotEmpty(message = "Name should not be empty")
  private String nodeName;
  /**
   * The Node additional info.
   */
  private Map<String, String> nodeAdditionalInfo;
  /**
   * The Parents.
   */
  private Set<Node> parents;
  /**
   * The Children.
   */
  private Set<Node> children;
}
