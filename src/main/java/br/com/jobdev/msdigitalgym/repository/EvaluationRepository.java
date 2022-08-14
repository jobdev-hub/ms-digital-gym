package br.com.jobdev.msdigitalgym.repository;

import br.com.jobdev.msdigitalgym.entity.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EvaluationRepository extends JpaRepository<Evaluation, UUID> {
    List<Evaluation> findByCustomerId(UUID id);
}