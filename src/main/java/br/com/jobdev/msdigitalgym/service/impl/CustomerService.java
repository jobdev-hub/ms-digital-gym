package br.com.jobdev.msdigitalgym.service.impl;

import br.com.jobdev.msdigitalgym.entity.Customer;
import br.com.jobdev.msdigitalgym.entity.Signature;
import br.com.jobdev.msdigitalgym.repository.CustomerRepository;
import br.com.jobdev.msdigitalgym.service.CustomerInterface;
import br.com.jobdev.msdigitalgym.util.StringUtils;
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
    public ResponseEntity<?> create(Customer customer) {
        try {
            customer.setCpf(customer.getCpf());
            checkIfCpfAlreadyExists(customer);
            return ResponseEntity.ok(customerRepository.save(customer));
        } catch (Exception e) {
            return responseByExceptionAndRules(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Customer> findById(UUID id) {
        try {
            Optional<Customer> customer = customerRepository.findById(id);
            return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<Customer> findByCpf(String cpf) {
        try {
            Optional<Customer> customer = customerRepository.findByCpf(StringUtils.formatCpf(cpf));
            return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
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
    public ResponseEntity<?> update(UUID id, Customer customerBodyRequest) {
        try {
            Optional<Customer> customerQuery = customerRepository.findById(id);
            if (customerQuery.isPresent()) {

                //Query
                Customer customerToSave = customerQuery.get();
                Signature signatureToSave = customerToSave.getSignature();

                //Rule Unique Cpf
                if (!customerToSave.getCpf().equals(customerBodyRequest.getCpf())) {
                    checkIfCpfAlreadyExists(customerBodyRequest);
                }

                //Update
                signatureToSave.setActive(customerBodyRequest.getSignature().getActive());
                signatureToSave.setUpdateAt(LocalDateTime.now());
                customerToSave.setSignature(signatureToSave);
                customerToSave.setName(customerBodyRequest.getName());
                customerToSave.setCpf(customerBodyRequest.getCpf());
                customerToSave.setDistrict(customerBodyRequest.getDistrict());
                customerToSave.setBirthDate(customerBodyRequest.getBirthDate());

                //Save
                return ResponseEntity.ok(customerRepository.save(customerToSave));

            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return responseByExceptionAndRules(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> deleteById(UUID id) {
        try {
            Optional<Customer> customer = customerRepository.findById(id);
            if (customer.isPresent()) {
                customerRepository.delete(customer.get());
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    private ResponseEntity<?> responseByExceptionAndRules(String exMessage) {

        // Rule 1: CPF already exists
        if (exMessage.contains("CPF already exists")) {
            return ResponseEntity.badRequest().body(exMessage);
        }

        // Default
        return ResponseEntity.internalServerError().build();
    }

    private void checkIfCpfAlreadyExists(Customer customerBodyRequest) {
        customerRepository.findByCpf(customerBodyRequest.getCpf()).ifPresent(c -> {
            throw new IllegalArgumentException("CPF already exists");
        });
    }
}