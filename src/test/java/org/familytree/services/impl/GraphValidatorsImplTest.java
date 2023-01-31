package org.familytree.services.impl;

import org.familytree.constants.ExceptionMessage;
import org.familytree.exceptions.NodeException;
import org.familytree.services.GraphValidators;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Graph validators impl test.
 */
@ExtendWith(MockitoExtension.class)
class GraphValidatorsImplTest {
  /**
   * The Graph validators.
   */
  @Mock
  GraphValidators graphValidators;

  /**
   * Validate node id when absent.
   */
  @Test
  void validateNodeIdWhenAbsent() {
    Exception exception = assertThrows(NodeException.class,
        () -> graphValidators.validateNodeId("node 1"));
    assertEquals(ExceptionMessage.NODE_ABSENT, exception.getMessage());
  }

  /**
   * Validate node id when present.
   */
  @Test
  void validateNodeIdWhenPresent() {
    try {
      graphValidators.validateNodeId("node 2");
    } catch (Exception e) {
      fail("Exception not expected");
    }
  }

  /**
   * Validate cyclic dependency when present.
   */
  @Test
  void validateCyclicDependencyWhenPresent() {
    Exception exception = assertThrows(NodeException.class, () ->
        graphValidators.validateCyclicDependency("node 1", "node 2"));
    assertEquals(ExceptionMessage.CYCLIC_DEPENDENCY, exception.getMessage());
  }

  /**
   * Validate cyclic dependency when absent.
   */
  @Test
  void validateCyclicDependencyWhenAbsent() {
    try {
      graphValidators.validateCyclicDependency("node 2", "node 1");
    } catch (Exception e) {
      fail("Exception not expected");
    }
  }

  /**
   * Validate parent child relationship failure.
   */
  @Test
  void validateParentChildRelationshipFailure() {
    Exception exception = assertThrows(NodeException.class, () ->
        graphValidators.validateParentChildRelationship("node 1", "node 2"));
    assertEquals(ExceptionMessage.NO_DEPENDENCY, exception.getMessage());
  }

  /**
   * Validate parent child relationship success.
   */
  @Test
  void validateParentChildRelationshipSuccess() {
    try {
      graphValidators.validateParentChildRelationship("node 1", "node 2");
    } catch (Exception e) {
      fail("Exception not expected");
    }
  }
}