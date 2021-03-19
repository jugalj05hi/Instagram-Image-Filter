package controller;

/**
 * The interface IController provides a single method for the outside classes or objects to use the
 * controller class in a limited way.
 */
public interface IController {
  /**
   * The method jumpStart helps the controller to start executing the controller based on the
   * command line arguments.
   */
  public void jumpStart();
}
