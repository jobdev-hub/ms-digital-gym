package br.com.jobdev.msdigitalgym.service.impl;

import br.com.jobdev.msdigitalgym.entity.Customer;
import br.com.jobdev.msdigitalgym.entity.Signature;
import br.com.jobdev.msdigitalgym.repository.CustomerRepository;
import br.com.jobdev.msdigitalgym.service.CustomerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService implements CustomerInterface<Customer> {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public ResponseEntity<UUID> create(Customer customer) {
        try {
            return ResponseEntity.ok(customerRepository.save(customer).getId());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<Customer> findById(UUID id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<Customer>> findAll() {
        try {
            return ResponseEntity.ok(customerRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<List<Customer>> findAllBySignatureActive(boolean active) {
        try {
            return ResponseEntity.ok(customerRepository.findAllBySignatureActive(active));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<Customer> update(UUID id, Customer customerBodyRequest) {

        Optional<Customer> customerQuery = customerRepository.findById(id);

        if (customerQuery.isPresent()) {

            Customer customerToSave = customerQuery.get();
            Signature signatureToSave = customerToSave.getSignature();

            signatureToSave.setActive(customerBodyRequest.getSignature().getActive());
            signatureToSave.setUpdateAt(LocalDateTime.now());

            customerToSave.setSignature(signatureToSave);
            customerToSave.setName(customerBodyRequest.getName());
            customerToSave.setCpf(customerBodyRequest.getCpf());
            customerToSave.setDistrict(customerBodyRequest.getDistrict());
            customerToSave.setBirthDate(customerBodyRequest.getBirthDate());

            return ResponseEntity.ok(customerRepository.save(customerToSave));
        }

        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<UUID> deleteById(UUID id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            customerRepository.delete(customer.get());
            return ResponseEntity.ok(id);
        }
        return ResponseEntity.notFound().build();
    }
}