package lk.ijse.green_shadow_backend.customeobj;

import java.io.Serializable;

public class StaffErrorResponse implements StaffResponse, Serializable {
    private int errorCode;
    private String errorMessage;
}
