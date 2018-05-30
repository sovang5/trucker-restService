package co.sovan.repository;


import co.sovan.entity.Alert;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepsoitory extends CrudRepository<Alert,String>{
    // Method to return all Historical Alerts of a specific vehicle
    @Query("select alt from Alert alt  where alt.vin=:id order by alt.timeStamp DESC")
    List<Alert> findAllByVin(@Param("id")String id);
    // Method to return all the historical alerts sort by time
    @Query("select alt from Alert alt  where alt.priority=:type order by alt.timeStamp DESC")
    List<Alert> findAllByType(@Param("type") String type);

}
