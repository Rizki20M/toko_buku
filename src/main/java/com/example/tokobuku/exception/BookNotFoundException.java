package com.example.tokobuku.exception;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(Long id){
        super("Maaf Buku Dengan Kode : " +id+",itu hanya ada di khayalan anda..");
    }
}
