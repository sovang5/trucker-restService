package co.sovan.repository;


import co.sovan.entity.Alert;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepsoitory extends CrudRepository<Alert,String>{
    List<Alert> findAllByVin(String id);
    @Query("select alt from Alert alt  where alt.priority=:type order by alt.timeStamp ASC")
    List<Alert> findAllByVmake(@Param("type") String type);

}
