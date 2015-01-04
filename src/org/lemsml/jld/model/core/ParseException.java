package org.lemsml.jld.model.core;

import java.io.Serializable;

import org.lemsml.jld.exception.LEMSException;

public class ParseException extends LEMSException implements Serializable {

    private static final long serialVersionUID = 1L;

    String message;

    public ParseException(String msg) {
        super(msg);
        message = msg;
    }

    public String toString() {
        return "ParseException: " + message;
    }

}
