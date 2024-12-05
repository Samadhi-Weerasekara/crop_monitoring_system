package lk.ijse.green_shadow_backend.dto;
import jakarta.validation.constraints.NotBlank;
import lk.ijse.green_shadow_backend.customeobj.UserResponse;
import lk.ijse.green_shadow_backend.enums.Enums;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto  implements SuperDto, UserResponse {
    private String id;
    private String email;
    private String role;
    private String password;
}
