package com.vojtoshik.problems.services;

/**
 * @author Anton Voitovych <vojtoshik@gmail.com>
 */
public class BadDataFileFormat extends RuntimeException {
    public BadDataFileFormat(String errorMessage) {
        super(errorMessage);
    }
}
