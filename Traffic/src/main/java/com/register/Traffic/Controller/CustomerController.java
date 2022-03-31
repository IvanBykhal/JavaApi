
package com.register.Traffic.Controller;

import com.register.Traffic.Model.Customer;
import com.register.Traffic.Repository.CustomerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/traffic/customer")
public class CustomerController {
 
    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping(path = "/add")
    public ResponseEntity<?> addNewCustomer(@RequestBody Customer n) {

        if (n.getPib().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        customerRepository.save(n);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/searchbyname")
    public ResponseEntity<List<Customer>> searchCustomer(@RequestParam(value = "pib") String pib) {

        try {
            if (pib.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if (customerRepository.findByPib(pib).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(customerRepository.findByPib(pib), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/searchbyid")
    public ResponseEntity<Optional<Customer>> searchCustomerById(@RequestParam(value = "id") Long id) {

        try {
            if (id == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if (customerRepository.findById(id).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(customerRepository.findById(id), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/all")
    public Iterable<Customer> getAllCustomer() {

        return customerRepository.findAll();
    }
    
}
