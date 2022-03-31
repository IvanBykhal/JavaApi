
package com.register.Traffic.Repository;

import com.register.Traffic.Model.Traffic;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TrafficRepository extends JpaRepository<Traffic, Long>{
   
    @Query("From Traffic Where registration_date >= ?1 AND registration_date <= ?2")
    public List<Traffic> findByPeriod(LocalDate firstDate, LocalDate secondDate);
    
}
