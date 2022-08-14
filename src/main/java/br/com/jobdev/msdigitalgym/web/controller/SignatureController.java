package br.com.jobdev.msdigitalgym.web.controller;

import br.com.jobdev.msdigitalgym.service.impl.SignatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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

}