package com.example.tokobuku.exception;

import java.util.Set;

public class BookUnsupportedFieldPatchException extends RuntimeException{
    public BookUnsupportedFieldPatchException (Set<String> keys){
        super("Maaf Field " +keys.toString()+" tidak boleh diupdate");
    }
}
