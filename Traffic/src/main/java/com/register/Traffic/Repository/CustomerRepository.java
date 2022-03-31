
package com.register.Traffic.Repository;

import com.register.Traffic.Model.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
     
    @Query("From Customer Where pib Like %?1%")
    public List<Customer> findByPib(String pib);
    
}
