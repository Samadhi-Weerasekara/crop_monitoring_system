package lk.ijse.green_shadow_backend.customeobj;

import java.io.Serializable;

public class UserErrorReponse implements UserResponse, Serializable {
    private int errorCode;
    private String errorMessage;
}
