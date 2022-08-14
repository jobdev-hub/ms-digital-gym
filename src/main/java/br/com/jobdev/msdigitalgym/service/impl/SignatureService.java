package br.com.jobdev.msdigitalgym.service.impl;

import br.com.jobdev.msdigitalgym.entity.Signature;
import br.com.jobdev.msdigitalgym.repository.SignatureRepository;
import br.com.jobdev.msdigitalgym.service.SignatureInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class SignatureService implements SignatureInterface<Signature> {

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

    @Override
    public ResponseEntity<UUID> upkeepDeleteInactive(UUID id) {

        Optional<Signature> signatureQuery = signatureRepository.findById(id);

        if (signatureQuery.isPresent()) {
            UUID idFound = signatureQuery.get().getId();
            signatureRepository.deleteById(idFound);
            return ResponseEntity.ok(idFound);
        }

        return ResponseEntity.notFound().build();
    }

}