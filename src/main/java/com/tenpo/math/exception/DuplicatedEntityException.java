package com.tenpo.math.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DuplicatedEntityException extends RuntimeException{

    private String entity;

    public  DuplicatedEntityException (String entity){
        super(String.format("Duplicated entity not allowed: %s ", entity));
    }

    @Override
    public String toString() {
        return "DuplicatedEntityException{" + "Entity='" + entity + '}';
    }
}
