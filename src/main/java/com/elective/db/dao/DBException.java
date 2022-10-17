package com.elective.db.dao;

public class DBException extends Exception {
    public DBException(String message, Exception ex){
        super(message, ex);
    }

    public DBException(String message){
        super(message);
    }
}
