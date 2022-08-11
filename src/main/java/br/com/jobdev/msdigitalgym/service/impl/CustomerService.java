package br.com.jobdev.msdigitalgym.service.impl;

import br.com.jobdev.msdigitalgym.entity.Customer;
import br.com.jobdev.msdigitalgym.repository.CustomerRepository;
import br.com.jobdev.msdigitalgym.service.CustomerInterface;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer findById(UUID id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> findAllActive() {
        return customerRepository.findAllBySignatureActiveTrue();
    }

    @Override
    public List<Customer> findAllInactive() {
        return customerRepository.findAllBySignatureActiveFalse();
    }

    @Override
    public Customer update(UUID id, Customer customerBodyRequest) {

        Optional<Customer> customerQuery = customerRepository.findById(id);

        if (customerQuery.isPresent()) {

            Customer customerToSave = customerQuery.get();

            customerToSave.setName(customerBodyRequest.getName());
            customerToSave.setCpf(customerBodyRequest.getCpf());
            customerToSave.setDistrict(customerBodyRequest.getDistrict());
            customerToSave.setBirthDate(customerBodyRequest.getBirthDate());

            // todo: update signature from customerBodyRequest

            return customerRepository.save(customerToSave);
        }

        return null;
    }

    @Override
    public void deleteById(UUID id) {
        customerRepository.deleteById(id);
    }
}