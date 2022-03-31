
package com.register.Traffic.Repository;

import com.register.Traffic.Model.Interes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InteresRepository extends JpaRepository<Interes, Long>{
    
}
