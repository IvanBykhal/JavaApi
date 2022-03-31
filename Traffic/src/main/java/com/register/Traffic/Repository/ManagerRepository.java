
package com.register.Traffic.Repository;

import com.register.Traffic.Model.Manager;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {

    @Query("From Manager Where pib Like %?1%")
    public List<Manager> findByPib(String pib);
}
