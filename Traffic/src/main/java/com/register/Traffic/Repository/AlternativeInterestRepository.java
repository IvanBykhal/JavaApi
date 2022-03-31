
package com.register.Traffic.Repository;

import com.register.Traffic.Model.AlternativeInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlternativeInterestRepository extends JpaRepository<AlternativeInterest, Long>{
    
}
