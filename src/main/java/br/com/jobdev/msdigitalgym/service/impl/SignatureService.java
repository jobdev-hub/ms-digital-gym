package br.com.jobdev.msdigitalgym.service.impl;

import br.com.jobdev.msdigitalgym.entity.Customer;
import br.com.jobdev.msdigitalgym.entity.Signature;
import br.com.jobdev.msdigitalgym.repository.CustomerRepository;
import br.com.jobdev.msdigitalgym.repository.SignatureRepository;
import br.com.jobdev.msdigitalgym.service.CustomerInterface;
import br.com.jobdev.msdigitalgym.service.SignatureInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<Map<String, Integer>> findResume() {
        return null;
    }

    @Override
    public Signature update(UUID id, boolean active) {
        return null;
    }

    @Override
    public void deleteById(UUID id) {

    }
}