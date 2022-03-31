
package com.register.Traffic.Controller;

import com.register.Traffic.Model.Interes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.register.Traffic.Repository.InteresRepository;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(path = "/traffic/interes")
public class InteresController {
    
    @Autowired
    private InteresRepository interesRepository;

    @PostMapping(path = "/add")
    public ResponseEntity<?> addNewModel(@RequestBody Interes interes) {

        if (interes.getInterest().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        interesRepository.save(interes);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "searchbyid")
    public ResponseEntity<Optional<Interes>> searchInteresById(@RequestParam(value = "id") Long id) {

        try {
            if (id == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if (interesRepository.findById(id).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(interesRepository.findById(id), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping(path = "/all")
    public Iterable<Interes> getAllModel() {

        return interesRepository.findAll();
    }
}
