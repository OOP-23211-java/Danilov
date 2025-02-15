package Utils;

import Exceptions.InvalidArgumentsException;

public abstract class BaseArgumentsValidator {
    public abstract BaseArguments validate(String[] args) throws InvalidArgumentsException;

    protected abstract void checkArgsCount(int argsCount) throws InvalidArgumentsException;
    protected abstract void checkCommand(String command) throws InvalidArgumentsException;

    protected abstract void printUsage();
}
