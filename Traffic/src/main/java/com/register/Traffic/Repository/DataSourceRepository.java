
package com.register.Traffic.Repository;

import com.register.Traffic.Model.DataSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataSourceRepository extends JpaRepository<DataSource, Long>{
   
    
}
