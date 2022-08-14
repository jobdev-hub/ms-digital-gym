package br.com.jobdev.msdigitalgym.service;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface CustomerInterface<T> {

    ResponseEntity<?> create(T t);

    ResponseEntity<T> findById(UUID id);

    ResponseEntity<T> findByCpf(String cpf);

    ResponseEntity<List<T>> findAll();

    ResponseEntity<List<T>> findAllBySignatureActive(boolean active);

    ResponseEntity<T> update(UUID id, T t);

    ResponseEntity<UUID> deleteById(UUID id);

}
