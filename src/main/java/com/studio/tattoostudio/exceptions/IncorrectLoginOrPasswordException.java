package com.studio.tattoostudio.Exceptions;

import java.io.Serial;

public class IncorrectLoginOrPasswordException extends Exception{

    @Serial
    private static final long serialVersionUID = 7240647401644064522L;

    public IncorrectLoginOrPasswordException(){
        super();
    }

    public IncorrectLoginOrPasswordException(String message){
        super(message);
    }
}
