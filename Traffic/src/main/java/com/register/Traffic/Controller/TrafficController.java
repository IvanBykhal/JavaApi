package com.register.Traffic.Controller;

import com.register.Traffic.Model.AlternativeInterest;
import com.register.Traffic.Model.Customer;
import com.register.Traffic.Model.Traffic;
import com.register.Traffic.Repository.AlternativeInterestRepository;
import com.register.Traffic.Repository.CustomerRepository;
import com.register.Traffic.Repository.DataSourceRepository;
import com.register.Traffic.Repository.ManagerRepository;
import com.register.Traffic.Repository.TrafficRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.register.Traffic.Repository.InteresRepository;

@RestController
@RequestMapping(path = "/traffic")
public class TrafficController {

    @Autowired
    private TrafficRepository trafficRepository;
    @Autowired
    private AlternativeInterestRepository alternativeInterestRepository;
    @Autowired
    private InteresRepository interesRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private DataSourceRepository datasourceRepository;
    @Autowired
    private ManagerRepository managerRepository;

    @GetMapping(path = "/all")
    public Iterable<Traffic> getAllTraffic() {

        return trafficRepository.findAll();
    }

    @GetMapping(path = "/period")
    public ResponseEntity<List<Traffic>> getAllTrafficFromPeriod(@RequestParam(value = "firstDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate firstDate, @RequestParam(value = "secondDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate secondDate) {

        try {
            if (firstDate == null || secondDate == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if (trafficRepository.findByPeriod(firstDate, secondDate).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(trafficRepository.findByPeriod(firstDate, secondDate), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/search")
    public ResponseEntity<Optional<Traffic>> searchTrafficById(@RequestParam(value = "id") Long id) {

        try {
            if (id == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if (trafficRepository.findById(id).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(trafficRepository.findById(id), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/sortmanager")
    public ResponseEntity<Iterable<Traffic>> sortedByManager(@RequestParam(value = "type") String typeSorted) {

        try {
            switch (typeSorted.toUpperCase()) {
                case "DESC":
                    return new ResponseEntity<>(trafficRepository.findAll(Sort.by(Direction.DESC, "manager.pib")), HttpStatus.OK);
                case "ASC":
                    return new ResponseEntity<>(trafficRepository.findAll(Sort.by(Direction.ASC, "manager.pib")), HttpStatus.OK);
                default:
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
     @GetMapping(path = "/sortcustomer")
    public ResponseEntity<Iterable<Traffic>> sortedByCustomer(@RequestParam(value = "type") String typeSorted) {

        try {
            switch (typeSorted.toUpperCase()) {
                case "DESC":
                    return new ResponseEntity<>(trafficRepository.findAll(Sort.by(Direction.DESC, "customer.pib")), HttpStatus.OK);
                case "ASC":
                    return new ResponseEntity<>(trafficRepository.findAll(Sort.by(Direction.ASC, "customer.pib")), HttpStatus.OK);
                default:
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/sortdate")
    public ResponseEntity<Iterable<Traffic>> sortedByDate(@RequestParam(value = "type") String typeSorted) {

        try {
            switch (typeSorted.toUpperCase()) {
                case "DESC":
                    return new ResponseEntity<>(trafficRepository.findAll(Sort.by(Direction.DESC, "registrationDate")), HttpStatus.OK);
                case "ASC":
                    return new ResponseEntity<>(trafficRepository.findAll(Sort.by(Direction.ASC, "registrationDate")), HttpStatus.OK);
                default:
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/add")
    public ResponseEntity<?> addNewTraffic(@RequestBody Traffic traffic) {
      
        try {
            Customer updateCustomer = customerRepository.getById(traffic.getCustomer().getId());
            if (!updateCustomer.getPhone().equals(traffic.getCustomer().getPhone()) || !updateCustomer.getPhone().equals(traffic.getCustomer().getEmail())) {
                updateCustomer.setEmail(traffic.getCustomer().getEmail());
                updateCustomer.setPhone(traffic.getCustomer().getPhone());
                customerRepository.save(updateCustomer);
            }

            trafficRepository.save(traffic);

            for (AlternativeInterest interes : traffic.getAlternativeInterest()) {
                alternativeInterestRepository.save(new AlternativeInterest(interesRepository.getById(interes.getModel().getId()), traffic));
            }

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
              return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
