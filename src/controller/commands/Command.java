package controller.commands;

/**
 * The command interface is the part of the controller.commands.Command Design Pattern,
 * whenever we want to execute a set of commands, the class executing it should implement
 * this interface.
 */
public interface Command {
  /**
   * The method executes the list of commands in the classes. The classes that need to execute a
   * list of commands should put them in this method in the sequence they want them to be executed.
   * The execute method will later execute them whe it is called. This way it makes it easier and
   * makes the code look clean.
   */
  void execute();


}
