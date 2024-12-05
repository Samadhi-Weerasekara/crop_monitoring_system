package lk.ijse.green_shadow_backend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.green_shadow_backend.customeobj.UserErrorReponse;
import lk.ijse.green_shadow_backend.customeobj.UserResponse;
import lk.ijse.green_shadow_backend.dao.UserDao;
import lk.ijse.green_shadow_backend.dto.UserDto;
import lk.ijse.green_shadow_backend.entity.UserEntity;
import lk.ijse.green_shadow_backend.exception.DataPersistFailedException;
import lk.ijse.green_shadow_backend.exception.NotFoundException;
import lk.ijse.green_shadow_backend.exception.StaffNotFoundException;
import lk.ijse.green_shadow_backend.service.UserService;
import lk.ijse.green_shadow_backend.util.Mapping;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserDao userDAO;
    private final Mapping mapping;


    public UserServiceImpl(Mapping mapping, UserDao userDAO){
        this.userDAO=userDAO;
        this.mapping=mapping;

    }
    @Override
    public void saveUser(UserDto userDTO) {
        if (userDTO.getEmail() == null) {
            throw new StaffNotFoundException(" Email are required.");
        }
        UserEntity savedUser = userDAO.save(mapping.convertToUserEntity(userDTO));
        if(savedUser == null){
            throw new DataPersistFailedException("Cannot save user");
        }
    }

    @Override
    public UserResponse getUserById(String id) {
        if(userDAO.existsById(id)){
           UserEntity  userEntityById = userDAO.getUserEntityById(id);
            return mapping.convertTouserDTO(userEntityById);
        }else {
            return new UserErrorReponse(0, "User not found");
        }
    }

    @Override
    public void deleteUser(String id) {
        if (!userDAO.existsById(id)) {
            throw new NotFoundException("Cannot delete: User with ID " + id + " not found.");
        }
        userDAO.deleteById(id);
    }

    @Override
    public void updateUser(String id, UserDto updatedUser) {
        Optional<UserEntity> tmpUser = userDAO.findById(id);

        if (tmpUser.isEmpty()) {
            throw new StaffNotFoundException("User not found");
        } else {
           UserEntity userEntity = mapping.convertToUserEntity(updatedUser);
            userEntity.setId(id);
           userDAO.save(userEntity);
        }
    }

    @Override
    public List<UserDto> getAllUsers() {
        return mapping.convertUserToDTOList(userDAO.findAll());
    }


    @Override
    public UserDetailsService userDetailsService() {
        return email ->
                userDAO.findByEmail(email)
                        .orElseThrow(()-> new NotFoundException("User Not found"));
    }
}
