package com.studio.tattoostudio.exceptions;

import java.io.Serial;

public class EntityNotFoundException extends Exception{
    @Serial
    private static final long serialVersionUID = 2887209731935595450L;

    public EntityNotFoundException(){
        super();
    }

    public EntityNotFoundException(String message){
        super(message);
    }
}
