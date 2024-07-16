package com.api.forohub.infra.errores;

public class Validacion extends RuntimeException{
    public Validacion(String s){
        super(s);
    }
}
