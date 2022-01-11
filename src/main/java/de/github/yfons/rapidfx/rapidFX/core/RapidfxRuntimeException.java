/*
 * 
 */
package de.github.yfons.rapidfx.rapidFX.core;

/**
 * {@link Rapidfx} Domain specific Runtime Exceptions.
 */
public class RapidfxRuntimeException extends RuntimeException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -3752734849344552384L;

  /**
   * Instantiates a new rapid FX runtime exception.
   *
   * @param errorMessage the error message
   */
  public RapidfxRuntimeException(String errorMessage) {
    super(errorMessage);
  }
}
