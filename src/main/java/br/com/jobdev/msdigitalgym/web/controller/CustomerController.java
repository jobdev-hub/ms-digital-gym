package br.com.jobdev.msdigitalgym.web.controller;

import br.com.jobdev.msdigitalgym.entity.Customer;
import br.com.jobdev.msdigitalgym.service.impl.CustomerService;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public Customer create(@RequestBody Customer customer) {
        return customerService.create(customer);
    }

    @GetMapping("/findById/{id}")
    public Customer findById(@PathVariable UUID id) {
        return customerService.findById(id);
    }

    @GetMapping("/findAll")
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/findAllBySignatureActive")
    public List<Customer> findAllBySignatureActive(@PathParam("active") boolean active) {
        return customerService.findAllBySignatureActive(active);
    }

    @PutMapping("/update/{id}")
    public Customer update(@PathVariable UUID id, @RequestBody Customer customer) {
        return customerService.update(id, customer);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable UUID id) {
        customerService.deleteById(id);
    }

}
