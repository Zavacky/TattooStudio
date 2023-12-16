package com.studio.tattoostudio.exceptions;

import java.io.Serial;

public class ClientDoesntExistException extends Exception{
    @Serial
    private static final long serialVersionUID = -9014458853098525435L;

    public ClientDoesntExistException(){
        super();
    }

    public ClientDoesntExistException(String message){
        super(message);
    }
}
