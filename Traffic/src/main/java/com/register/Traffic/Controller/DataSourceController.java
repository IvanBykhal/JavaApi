package com.register.Traffic.Controller;

import com.register.Traffic.Model.DataSource;
import com.register.Traffic.Repository.DataSourceRepository;
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
@RequestMapping(path = "/traffic/datasource")
public class DataSourceController {

    @Autowired
    private DataSourceRepository datasourceRepository;

    @PostMapping(path = "/add")
    public ResponseEntity<?> addNewModel(@RequestBody DataSource n) {

        if (n.getSource().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        datasourceRepository.save(n);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "searchbyid")
    public ResponseEntity<Optional<DataSource>> searchDataSourceById(@RequestParam(value = "id") Long id) {

        try {
            if (id == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if (datasourceRepository.findById(id).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(datasourceRepository.findById(id), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/all")
    public Iterable<DataSource> getAllModel() {

        return datasourceRepository.findAll();
    }

}
