package br.com.jobdev.msdigitalgym.service.impl;

import br.com.jobdev.msdigitalgym.entity.Customer;
import br.com.jobdev.msdigitalgym.entity.Evaluation;
import br.com.jobdev.msdigitalgym.repository.CustomerRepository;
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
    private final CustomerRepository customerRepository;

    @Autowired
    public EvaluationService(EvaluationRepository evaluationRepository, CustomerRepository customerRepository) {
        this.evaluationRepository = evaluationRepository;

        this.customerRepository = customerRepository;
    }

    @Override
    public ResponseEntity<UUID> createByCustomerId(Evaluation evaluation, UUID customerId) {
        try {
            Optional<Customer> customerQuery = customerRepository.findById(customerId);
            if (customerQuery.isPresent()) {
                evaluation.setCustomer(customerQuery.get());
                evaluationRepository.save(evaluation);
                return ResponseEntity.ok(evaluation.getId());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<List<Evaluation>> findByCustomerId(UUID customerId) {
        try {
            return ResponseEntity.ok(evaluationRepository.findByCustomerId(customerId));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<?> deleteById(UUID id) {
        try {
            Optional<Evaluation> evaluationQuery = evaluationRepository.findById(id);
            if (evaluationQuery.isPresent()) {
                evaluationRepository.deleteById(id);
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}