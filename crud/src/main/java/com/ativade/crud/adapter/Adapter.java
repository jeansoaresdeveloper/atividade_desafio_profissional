package com.ativade.crud.adapter;

public interface Adapter<T, R>{

    T toEntity(R DTO);

}
