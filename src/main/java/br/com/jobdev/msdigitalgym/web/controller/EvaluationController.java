package br.com.jobdev.msdigitalgym.web.controller;

import br.com.jobdev.msdigitalgym.entity.Evaluation;
import br.com.jobdev.msdigitalgym.service.impl.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/evaluation")
public class EvaluationController {

    private final EvaluationService evaluationService;

    @Autowired
    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping("/create/{customerId}")
    public ResponseEntity<UUID> create(@PathVariable UUID customerId, @RequestBody Evaluation evaluation) {
        return evaluationService.createByCustomerId(evaluation, customerId);
    }

    @GetMapping("/findByCustomerId")
    public ResponseEntity<List<Evaluation>> findByCustomerId(@RequestParam UUID customerId) {
        return evaluationService.findByCustomerId(customerId);
    }

    @PutMapping("/update/{customerId}")
    public ResponseEntity<UUID> update(@PathVariable UUID customerId, @RequestBody Evaluation evaluation) {
        return evaluationService.update(customerId, evaluation);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UUID> deleteById(@PathVariable UUID id) {
        return evaluationService.deleteById(id);
    }

}