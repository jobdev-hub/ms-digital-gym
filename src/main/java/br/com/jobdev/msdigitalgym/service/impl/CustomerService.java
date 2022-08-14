package br.com.jobdev.msdigitalgym.service.impl;

import br.com.jobdev.msdigitalgym.entity.Customer;
import br.com.jobdev.msdigitalgym.repository.CustomerRepository;
import br.com.jobdev.msdigitalgym.service.CustomerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
            customerRepository.save(customer);
            return new ResponseEntity<>(customer.getId(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<Customer> findById(UUID id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<List<Customer>> findAll() {
        List<Customer> customers = customerRepository.findAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Customer>> findAllBySignatureActive(boolean active) {
        List<Customer> customers = customerRepository.findAllBySignatureActive(active);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Customer> update(UUID id, Customer customerBodyRequest) {

        Optional<Customer> customerQuery = customerRepository.findById(id);

        if (customerQuery.isPresent()) {

            Customer customerToSave = customerQuery.get();

            customerToSave.setName(customerBodyRequest.getName());
            customerToSave.setCpf(customerBodyRequest.getCpf());
            customerToSave.setDistrict(customerBodyRequest.getDistrict());
            customerToSave.setBirthDate(customerBodyRequest.getBirthDate());

            // todo: update signature from customerBodyRequest

            customerRepository.save(customerToSave);
            return new ResponseEntity<>(customerToSave, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<UUID> deleteById(UUID id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            customerRepository.delete(customer.get());
            return new ResponseEntity<>(id, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}