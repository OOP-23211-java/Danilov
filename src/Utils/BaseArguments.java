package Utils;

public abstract class BaseArguments {
    private String command;
    private String inputFile;

    public BaseArguments(String command, String inputFile) {
        this.command = command;
        this.inputFile = inputFile;
    }

    public BaseArguments() {}

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getInputFile() {
        return inputFile;
    }

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }
}
