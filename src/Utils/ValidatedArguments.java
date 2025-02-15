package Utils;

public class ValidatedArguments extends BaseArguments {
    private String outputFile;

    public ValidatedArguments(String command, String inputFile, String outputFile) {
        super(command, inputFile);
        this.outputFile = outputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    public String getOutputFile() {
        return outputFile;
    }
}