package Utils;

import Exceptions.InvalidArgumentsException;

/**
 * Абстрактный класс валидатор аргументов командной строки.
 * Данный класс определяет базовые методы для проверки аргументов и вывода вспомогательной информации.
 * @see BaseArguments
 * @see InvalidArgumentsException
 */
public abstract class BaseArgumentsValidator {
    /**
     * @param args массив аргументов командной строки
     * @return объект {@link BaseArguments}, содержащий провалидированные параметры
     * @throws InvalidArgumentsException если аргументы не прошли валидацию.
     */
    public abstract BaseArguments validate(String[] args) throws InvalidArgumentsException;

    /**
     * Проверяет, число аргументов соответствует заданным ожиданиям.
     * @param argsCount количество переданных аргументов.
     * @throws InvalidArgumentsException если количество аргументов неверное.
     */
    protected abstract void checkArgsCount(int argsCount) throws InvalidArgumentsException;

    /**
     * Проверяет, что переданная команда допустимая.
     * @param command команда.
     * @throws InvalidArgumentsException если команда неверная.
     */
    protected abstract void checkCommand(String command) throws InvalidArgumentsException;

    /**
     * Выводит информацию о правильном использовании.
     */
    protected abstract void printUsage();
}
