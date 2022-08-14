package br.com.jobdev.msdigitalgym.service;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface EvaluationInterface<T> {

    ResponseEntity<UUID> createByCustomerId(T t, UUID custumeId);

    ResponseEntity<List<T>> findByCustomerId(UUID id);

    ResponseEntity<UUID> update(UUID id, T t);

    ResponseEntity<UUID> deleteById(UUID id);

}