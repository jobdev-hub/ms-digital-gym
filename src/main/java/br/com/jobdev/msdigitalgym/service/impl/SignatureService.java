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
        Map<String, Integer> resume = Map.of("active", signatureRepository.countByActive(true), "inactive", signatureRepository.countByActive(false));
        return ResponseEntity.ok(resume);
    }

    @Override
    public ResponseEntity<UUID> update(UUID id, boolean active) {

        Optional<Signature> signatureQuery = signatureRepository.findById(id);

        if (signatureQuery.isPresent()) {
            Signature signatureToSave = signatureQuery.get();
            signatureToSave.setActive(active);
            signatureRepository.save(signatureToSave);
            return ResponseEntity.ok(signatureToSave.getId());
        }

        return ResponseEntity.notFound().build();
    }

}