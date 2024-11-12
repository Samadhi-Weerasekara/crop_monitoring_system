package lk.ijse.green_shadow_backend.customeobj;

import java.io.Serializable;

public class CropErrorResponse implements CropResponse, Serializable {
    private int errorCode;
    private String errorMessage;
}
