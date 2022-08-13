package br.com.jobdev.msdigitalgym.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface SignatureInterface<T> {

    List<Map<String, Integer>> findResume();

    T update(UUID id, boolean active);

    void deleteById(UUID id);

}
