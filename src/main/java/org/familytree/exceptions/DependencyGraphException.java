package org.familytree.exceptions;

import org.familytree.enums.ExceptionCode;

/**
 * The type Node exception.
 */
public class DependencyGraphException extends BaseApplicationException {
  /**
   * Instantiates a new Node exception.
   *
   * @param message the message
   */
  public DependencyGraphException(final String message) {
    super(message, ExceptionCode.NODE_ERROR);
  }
}
