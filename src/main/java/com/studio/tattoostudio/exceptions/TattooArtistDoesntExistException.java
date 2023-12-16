package com.studio.tattoostudio.exceptions;

import java.io.Serial;

public class TattooArtistDoesntExistException extends Exception{
    @Serial
    private static final long serialVersionUID = 4694251783169306832L;

    public TattooArtistDoesntExistException(){
        super();
    }

    public TattooArtistDoesntExistException(String message){
        super(message);
    }
}
