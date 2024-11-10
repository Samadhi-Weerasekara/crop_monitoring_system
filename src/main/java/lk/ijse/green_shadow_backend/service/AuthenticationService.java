package lk.ijse.green_shadow_backend.service;


import lk.ijse.green_shadow_backend.dto.UserDto;
import lk.ijse.green_shadow_backend.jwtmodels.JwtAuthResponse;
import lk.ijse.green_shadow_backend.jwtmodels.SignIn;

public interface AuthenticationService {
    JwtAuthResponse signIn(SignIn signIn);
    JwtAuthResponse signUp(UserDto signUp);
    JwtAuthResponse refreshToken(String accessToken);
}
