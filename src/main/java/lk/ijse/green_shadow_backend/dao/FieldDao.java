package lk.ijse.green_shadow_backend.dao;

import lk.ijse.green_shadow_backend.entity.FieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldDao extends JpaRepository<FieldEntity,String> {
}
