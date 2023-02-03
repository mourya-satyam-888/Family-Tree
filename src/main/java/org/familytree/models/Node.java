package org.familytree.models;

import jakarta.validation.constraints.NotEmpty;
import java.util.Map;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The type Node.
 */
@Getter
@Setter
@ToString
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
  @ToString.Exclude
  private Set<Node> parents;
  /**
   * The Children.
   */
  @ToString.Exclude
  private Set<Node> children;
}
