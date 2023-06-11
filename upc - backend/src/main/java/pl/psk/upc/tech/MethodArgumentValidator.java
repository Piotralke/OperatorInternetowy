package pl.psk.upc.tech;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

public final class MethodArgumentValidator {

    private MethodArgumentValidator() {
    }

    public static void requiredNotNullOrEmptyCollection(Collection<?> argument, String argumentName) {
        if (argument == null) {
            throw new IllegalArgumentException(prepareErrorMessage(argumentName, "is null"));
        }
        if (argument.isEmpty()) {
            throw new IllegalArgumentException(prepareErrorMessage(argumentName, "is empty collection"));
        }
    }

    public static void requiredNotNullOrBlankString(String argument, String argumentName) {
        if (StringUtils.isBlank(argument)) {
            throw new IllegalArgumentException(prepareErrorMessage(argumentName, "is null or empty Sting"));
        }
    }

    public static <T> void requiredNotNull(T argument, String argumentName) {
        if (argument == null) {
            throw new IllegalArgumentException(prepareErrorMessage(argumentName, "is null"));
        }
    }
    public static void requiredNotNullEnum(Object argument, String argumentName) {
        if (argument == null) {
            throw new IllegalArgumentException(prepareErrorMessage(argumentName, "is null"));
        }
        if (!argument.getClass().isEnum()) {
            throw new IllegalArgumentException(prepareErrorMessage(argumentName, "must be en enum"));
        }
    }

    private static String prepareErrorMessage(String argumentName, String s) {
        String message = null;
        if (StringUtils.isBlank(argumentName)) {
            message = "Argument";
        } else {
            message = argumentName;
        }
        message += " " + s;
        return message;
    }

}