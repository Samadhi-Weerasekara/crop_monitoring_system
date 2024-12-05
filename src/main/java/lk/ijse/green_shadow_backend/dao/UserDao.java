package lk.ijse.green_shadow_backend.dao;

import lk.ijse.green_shadow_backend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<UserEntity, String> {
    UserEntity getUserEntityById(String id);
    Optional<UserEntity> findByEmail(String email);
}
