package br.com.jobdev.msdigitalgym.service.impl;

import br.com.jobdev.msdigitalgym.entity.Evaluation;
import br.com.jobdev.msdigitalgym.entity.Signature;
import br.com.jobdev.msdigitalgym.repository.EvaluationRepository;
import br.com.jobdev.msdigitalgym.service.EvaluationInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class EvaluationService implements EvaluationInterface<Evaluation> {

    private final EvaluationRepository evaluationRepository;

    @Autowired
    public EvaluationService(EvaluationRepository evaluationRepository) {
        this.evaluationRepository = evaluationRepository;
    }


    @Override
    public Evaluation create(Evaluation evaluation) {
        return null;
    }

    @Override
    public List<Evaluation> findByCustomer(UUID id) {
        return null;
    }

    @Override
    public Evaluation update(UUID id) {
        return null;
    }

    @Override
    public void deleteById(UUID id) {

    }
}