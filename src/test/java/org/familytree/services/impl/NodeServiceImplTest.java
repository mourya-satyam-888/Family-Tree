package org.familytree.services.impl;

import java.util.HashMap;
import org.familytree.constants.ExceptionMessage;
import org.familytree.exceptions.ValidationException;
import org.familytree.services.NodeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * The type Node validators impl test.
 */
@ExtendWith(MockitoExtension.class)
class NodeServiceImplTest {
  /**
   * The Node validators.
   */
  @Mock
  NodeService nodeService;

  /**
   * Validate node when name empty.
   */
//expected isn't works in junit5
  @Test
  void validateNodeWhenNameEmpty() {
    Exception exception = assertThrows(ValidationException.class, () ->
        nodeService.validateAndCreateNode("Id1", "", new HashMap<>()));
    assertEquals(ExceptionMessage.EMPTY_NAME, exception.getMessage());
  }

  /**
   * Validate node when id empty.
   */
  @Test
  void validateNodeWhenIdEmpty() {
    Exception exception = assertThrows(ValidationException.class, () ->
        nodeService.validateAndCreateNode("", "name 1", new HashMap<>()));
    assertEquals(ExceptionMessage.EMPTY_ID, exception.getMessage());
  }

  /**
   * Validate node success.
   */
  @Test
  void validateNodeSuccess() {
    try {
      nodeService.validateAndCreateNode("id 1", "name 1", new HashMap<>());
    } catch (ValidationException ex) {
      fail("No exception expected");
    }
  }
}