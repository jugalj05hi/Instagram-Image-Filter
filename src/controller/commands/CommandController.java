package controller.commands;


/**
 * The controller.commands.CommandController class is the controller of the
 * controller.commands.Command interface i.e the classes that needs their objects to be
 * called should be passed in the constructor of this class and later the execute() method can
 * be called from this class. This would significantly make the code look cleaner in the
 * controller and make the controller less cluttered.
 */

public class CommandController {

  Command commandOf;
  Command command;

  /**
   * A constructor that takes in argument of the Object of the class whose execute() method is to be
   * called.
   *
   * @param command the object of the class whose execute() method is to be called.
   */
  public CommandController(Command command) {
    this.commandOf = command;
  }

  /**
   * The execute method will call the object's execute() method passed in the constructor of the
   * controller.commands.CommandController class.
   */
  public void execute() {
    commandOf.execute();
  }


}
