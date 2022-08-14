package br.com.jobdev.msdigitalgym.repository;

import br.com.jobdev.msdigitalgym.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    List<Customer> findAllBySignatureActive(Boolean active);
    Optional<Customer> findByCpf(String cpf);
}