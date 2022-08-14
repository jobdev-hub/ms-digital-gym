package br.com.jobdev.msdigitalgym.web.controller;

import br.com.jobdev.msdigitalgym.service.impl.SignatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/signature")
public class SignatureController {

    private final SignatureService signatureService;

    @Autowired
    public SignatureController(SignatureService signatureService) {
        this.signatureService = signatureService;
    }

    @GetMapping("/findResume")
    public ResponseEntity<Map<String, Integer>> findResume() {
        return signatureService.findResume();
    }

    @PutMapping("/upkeep/delete-inactive/{id}")
    public ResponseEntity<UUID> upkeepDeleteInactive(@PathVariable UUID id) {
        return signatureService.upkeepDeleteInactive(id);
    }
}