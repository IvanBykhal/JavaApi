package com.register.Traffic.Controller;

import com.register.Traffic.Repository.ManagerRepository;
import com.register.Traffic.Model.Manager;
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
@RequestMapping(path = "/traffic/manager")
public class ManagerController {

    @Autowired
    private ManagerRepository managerRepository;

    @PostMapping(path = "/add")
    public ResponseEntity<?> addNewManager(@RequestBody Manager n) {

        if (n.getPib().isEmpty() || n.getStatus().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        managerRepository.save(n);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/searchbyname")
    public ResponseEntity<List<Manager>> searchManager(@RequestParam(value = "pib") String pib) {

        try {
            if (pib.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if (managerRepository.findByPib(pib).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(managerRepository.findByPib(pib), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "searchbyid")
    public ResponseEntity<Optional<Manager>> searchManagerById(@RequestParam(value = "id") Long id) {

        try {
            if (id == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if (managerRepository.findById(id).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(managerRepository.findById(id), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/all")
    public Iterable<Manager> getAllManagers() {

        return managerRepository.findAll();
    }
}
