package org.familytree.exceptions;

import org.familytree.enums.ExceptionCode;

/**
 * This Exception should be thrown for Validation Error.
 */
@SuppressWarnings("serial")
public class ValidationException extends BaseApplicationException {
  /**
   * Instantiates a new Validation exception.
   *
   * @param message the message
   */
  public ValidationException(final String message) {
    super(message, ExceptionCode.VALIDATION_ERROR);
  }
}
