package lk.ijse.green_shadow_backend.dto;
import lk.ijse.green_shadow_backend.customeobj.UserResponse;
import lk.ijse.green_shadow_backend.enums.Enums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto  implements SuperDto, UserResponse {
    private String email; // Email to be used as the username
    private String password; // Password for the user (ensure encryption on storage)
    private Enums.Role role; // Role of the user as ENUM (MANAGER, ADMINISTRATIVE, SCIENTIST, OTHER)
}
