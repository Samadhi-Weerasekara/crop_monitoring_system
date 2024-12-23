package lk.ijse.green_shadow_backend.controller;

import lk.ijse.green_shadow_backend.dto.UserDto;
import lk.ijse.green_shadow_backend.exception.DataPersistFailedException;
import lk.ijse.green_shadow_backend.jwtmodels.JwtAuthResponse;
import lk.ijse.green_shadow_backend.jwtmodels.SignIn;
import lk.ijse.green_shadow_backend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(value = "/signup")
    public ResponseEntity<JwtAuthResponse> signUp(@RequestBody UserDto userDTO) {
        try {
         userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            //send to the service layer
            return ResponseEntity.ok(authenticationService.signUp(userDTO));
        }catch (DataPersistFailedException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(value = "/signin")
    public ResponseEntity<JwtAuthResponse> signIn(@RequestBody SignIn signIn) {
        return ResponseEntity.ok(authenticationService.signIn(signIn));
    }
    @PostMapping("refresh")
    public ResponseEntity<JwtAuthResponse> refreshToken (@RequestParam("refreshToken") String refreshToken) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshToken));
    }

}
