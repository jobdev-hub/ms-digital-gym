package br.com.jobdev.msdigitalgym.service;

import java.util.List;
import java.util.UUID;

public interface EvaluationInterface<T> {

    T create(T t);

    List<T> findByCustomer(UUID id);

    T update(UUID id);

    void deleteById(UUID id);

}
