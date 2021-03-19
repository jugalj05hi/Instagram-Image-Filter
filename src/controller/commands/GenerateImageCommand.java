package controller.commands;

/**
 * The class GenerateImageCommand helps generate the image as mentioned by the user, it takes in
 * argument from the controller. The class also implements interface Command. When called from the
 * controller it executes the command in order to achieve the task required.
 */
public class GenerateImageCommand implements Command {

  private String generate;

  /**
   * A constructor that takes in the name of the flag/pattern to be generated.
   *
   * @param generate the name of the pattern to be generated.
   */
  public GenerateImageCommand(String generate) {
    this.generate = generate;
  }

  @Override
  public void execute() {

    switch (generate.toLowerCase()) {
      case "rainbow":
        //Using the RainbowGenerateCommand and  commandController to execute the commands.
        RainbowGenerateCommand generateRainbow = new RainbowGenerateCommand();
        CommandController commandController = new CommandController(generateRainbow);
        commandController.execute();
        break;
      case "checkerboard":
        //Using the CheckerboardGenerateCommand and  commandController to execute the commands.
        CheckerboardGenerateCommand generateCheckerboard = new CheckerboardGenerateCommand();

        commandController = new CommandController(generateCheckerboard);
        commandController.execute();


        break;
      case "france":

        FranceFlagGenerateCommand generateFranceFlag = new FranceFlagGenerateCommand();

        commandController = new CommandController(generateFranceFlag);
        commandController.execute();
        break;
      case "greece":

        GreeceFlagGenerateCommand generateGreeceFlag = new GreeceFlagGenerateCommand();
        commandController = new CommandController(generateGreeceFlag);
        commandController.execute();

        break;
      case "switzerland":

        SwitzerlandFlagGenerate generateSwissFlag = new SwitzerlandFlagGenerate();
        commandController = new CommandController(generateSwissFlag);
        commandController.execute();
        break;
      default:
        System.out.println("No such image exists.");
        break;
    }
  }
}
