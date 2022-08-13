package br.com.jobdev.msdigitalgym.service;

import java.util.List;
import java.util.UUID;

public interface CustomerInterface<T> {

    T create(T t);

    T findById(UUID id);

    List<T> findAll();

    List<T> findAllBySignatureActive(boolean active);

    T update(UUID id, T t);

    void deleteById(UUID id);

}
