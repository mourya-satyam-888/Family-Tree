package org.familytree.services.impl;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.familytree.exceptions.ValidationException;
import org.familytree.models.Node;
import org.familytree.services.NodeService;

/**
 * The type Node service.
 */
public class NodeServiceImpl implements NodeService {
  /**
   * The constant FACTORY.
   */
  private static final ValidatorFactory FACTORY = Validation.buildDefaultValidatorFactory();
  /**
   * The constant VALIDATOR.
   */
  private static final Validator VALIDATOR = FACTORY.getValidator();

  @Override
  public Node validateAndCreateNode(final String nodeId, final String nodeName,
                                    final Map<String, String> additionalInfo) {
    final Node node = Node.builder().nodeId(nodeId)
        .nodeName(nodeName).nodeAdditionalInfo(additionalInfo)
        .parents(new HashSet<>()).children(new HashSet<>()).build();
    validateNode(node);
    return node;
  }

  @Override
  public void validateNode(final Node node) {
    final Set<ConstraintViolation<Node>> violations = VALIDATOR.validate(node);
    violations.forEach(t -> {
      throw new ValidationException(t.getMessage());
    });
  }


  @Override
  public Set<Node> getParents(final Node child) {
    return new HashSet<>(child.getParents());
  }

  @Override
  public Set<Node> getChildren(final Node parent) {
    return new HashSet<>(parent.getChildren());
  }

}
