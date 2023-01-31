package org.familytree.exceptions;

import org.familytree.enums.ExceptionCode;

/**
 * The type Node exception.
 */
public class NodeException extends BaseApplicationException {
  /**
   * Instantiates a new Node exception.
   *
   * @param message the message
   */
  public NodeException(final String message) {
    super(message, ExceptionCode.NODE_ERROR);
  }
}
