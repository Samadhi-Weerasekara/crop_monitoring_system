package lk.ijse.green_shadow_backend.dao;

import lk.ijse.green_shadow_backend.entity.MonitoringLogDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitoringLogDetailsDao extends JpaRepository<MonitoringLogDetailsEntity,String> {
}
