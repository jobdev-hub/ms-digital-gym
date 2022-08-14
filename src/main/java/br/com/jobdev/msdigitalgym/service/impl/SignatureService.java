package br.com.jobdev.msdigitalgym.service.impl;

import br.com.jobdev.msdigitalgym.repository.SignatureRepository;
import br.com.jobdev.msdigitalgym.service.SignatureInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SignatureService implements SignatureInterface {

    private final SignatureRepository signatureRepository;

    @Autowired
    public SignatureService(SignatureRepository signatureRepository) {
        this.signatureRepository = signatureRepository;
    }


    @Override
    public ResponseEntity<Map<String, Integer>> findResume() {
        try {
            return ResponseEntity.ok(Map.of(
                    "active", signatureRepository.countByActive(true),
                    "inactive", signatureRepository.countByActive(false))
            );

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}