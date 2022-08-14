package br.com.jobdev.msdigitalgym.service;

import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface EvaluationInterface<T> {

    ResponseEntity<?> createByCustomerId(T t, UUID custumeId);

    ResponseEntity<?> findByCustomerId(UUID id);

    ResponseEntity<?> deleteById(UUID id);

}