package org.familytree.services.impl;

import java.util.HashMap;
import org.familytree.constants.ExceptionMessage;
import org.familytree.exceptions.ValidationException;
import org.familytree.services.NodeValidators;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * The type Node validators impl test.
 */
@ExtendWith(MockitoExtension.class)
class NodeValidatorsImplTest {
  /**
   * The Node validators.
   */
  @Mock
  NodeValidators nodeValidators;

  /**
   * Validate node when name empty.
   */
//expected isn't works in junit5
  @Test
  void validateNodeWhenNameEmpty() {
    Exception exception = assertThrows(ValidationException.class, () ->
        nodeValidators.validateNode("Id1", "", new HashMap<>()));
    assertEquals(ExceptionMessage.EMPTY_NAME, exception.getMessage());
  }

  /**
   * Validate node when id empty.
   */
  @Test
  void validateNodeWhenIdEmpty() {
    Exception exception = assertThrows(ValidationException.class, () ->
        nodeValidators.validateNode("", "name 1", new HashMap<>()));
    assertEquals(ExceptionMessage.EMPTY_ID, exception.getMessage());
  }

  /**
   * Validate node success.
   */
  @Test
  void validateNodeSuccess() {
    try {
      nodeValidators.validateNode("id 1", "name 1", new HashMap<>());
    } catch (ValidationException ex) {
      fail("No exception expected");
    }
  }
}