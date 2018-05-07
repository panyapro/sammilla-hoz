package kz.pavershin.services;

import kz.pavershin.exceptions.InputValidationException;

import java.util.List;

public interface GlobalService<T> {

    List<T> findAll();

    void save(T t) throws InputValidationException;

    T getById(Long id);
}
