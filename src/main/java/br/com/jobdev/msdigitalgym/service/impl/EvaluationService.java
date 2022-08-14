package br.com.jobdev.msdigitalgym.service.impl;

import br.com.jobdev.msdigitalgym.entity.Evaluation;
import br.com.jobdev.msdigitalgym.repository.EvaluationRepository;
import br.com.jobdev.msdigitalgym.service.EvaluationInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EvaluationService implements EvaluationInterface<Evaluation> {

    private final EvaluationRepository evaluationRepository;
    private final CustomerService customerService;

    @Autowired
    public EvaluationService(EvaluationRepository evaluationRepository, CustomerService customerService) {
        this.evaluationRepository = evaluationRepository;
        this.customerService = customerService;
    }

    @Override
    public ResponseEntity<UUID> createByCustomerId(Evaluation evaluation, UUID customerId) {
        evaluation.setCustomer(customerService.findById(evaluation.getCustomer().getId()).getBody());
        try {
            evaluationRepository.save(evaluation);
            return ResponseEntity.ok(evaluation.getId());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<List<Evaluation>> findByCustomerId(UUID customerId) {
        return ResponseEntity.ok(evaluationRepository.findByCustomerId(customerId));
    }

    @Override
    public ResponseEntity<UUID> update(UUID id, Evaluation evaluationBodyRequest) {
        Optional<Evaluation> evaluationQuery = evaluationRepository.findById(id);

        if (evaluationQuery.isPresent()) {
            Evaluation evaluationToSave = evaluationQuery.get();
            evaluationToSave.setHeight(evaluationBodyRequest.getHeight());
            evaluationToSave.setWeight(evaluationBodyRequest.getWeight());
            evaluationToSave.setDateTime(evaluationBodyRequest.getDateTime());
            evaluationRepository.save(evaluationToSave);
            return ResponseEntity.ok(evaluationToSave.getId());
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<UUID> deleteById(UUID id) {
        Optional<Evaluation> evaluationQuery = evaluationRepository.findById(id);

        if (evaluationQuery.isPresent()) {
            evaluationRepository.deleteById(id);
            return ResponseEntity.ok(id);
        }
        return ResponseEntity.notFound().build();
    }
}