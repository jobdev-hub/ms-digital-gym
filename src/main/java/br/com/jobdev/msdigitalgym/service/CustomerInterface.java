package br.com.jobdev.msdigitalgym.service;

import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface CustomerInterface<T> {

    ResponseEntity<?> create(T t);

    ResponseEntity<?> findById(UUID id);

    ResponseEntity<?> findByCpf(String cpf);

    ResponseEntity<?> findAll();

    ResponseEntity<?> findAllBySignatureActive(boolean active);

    ResponseEntity<?> update(UUID id, T t);

    ResponseEntity<?> deleteById(UUID id);

}
