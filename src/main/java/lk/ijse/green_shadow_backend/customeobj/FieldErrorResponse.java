package lk.ijse.green_shadow_backend.customeobj;

import java.io.Serializable;

public class FieldErrorResponse implements FieldResponse, Serializable {
    private int errorCode;
    private String errorMessage;
}
