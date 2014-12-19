package org.lemsml.api;

import java.io.Serializable;

import org.lemsml.io.jldreader.LEMSException;

public class ContentError extends LEMSException implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public ContentError(String msg) {
        super(msg);
    }

    public ContentError(String msg, Throwable t) {
        super(msg, t);
    }

}
