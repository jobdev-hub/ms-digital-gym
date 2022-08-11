package br.com.jobdev.msdigitalgym.repository;

import br.com.jobdev.msdigitalgym.entity.Signature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SignatureRepository extends JpaRepository<Signature, UUID> {

}