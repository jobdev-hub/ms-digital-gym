package br.com.jobdev.msdigitalgym.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.UUID;

public interface SignatureInterface<T> {

    ResponseEntity<Map<String, Integer>> findResume();

    ResponseEntity<UUID> update(UUID id, boolean active);

}