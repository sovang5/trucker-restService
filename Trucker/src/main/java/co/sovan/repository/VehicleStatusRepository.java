package co.sovan.repository;

import co.sovan.entity.VehicleStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
        import org.springframework.data.repository.query.Param;
        import org.springframework.stereotype.Repository;

        import java.util.List;
        import java.util.Optional;

@Repository
public interface VehicleStatusRepository extends CrudRepository<VehicleStatus,String> {
    //@Query("select veh from VehicleStatus veh where veh.timestamp<SQL(\"(sysdate - interval '30' minute)\" and veh.vin=:vin")
    List<VehicleStatus> findAllByVin(@Param("vin") String vin);
}
