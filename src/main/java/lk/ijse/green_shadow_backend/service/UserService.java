package lk.ijse.green_shadow_backend.service;

import lk.ijse.green_shadow_backend.customeobj.UserResponse;
import lk.ijse.green_shadow_backend.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    public void saveUser(UserDto userDTO);
    public UserResponse getUserById(String id);
    public void deleteUser(String id);
    public void updateUser(String id, UserDto updatedUser);
    public List<UserDto> getAllUsers();
    UserDetailsService userDetailsService();
}
