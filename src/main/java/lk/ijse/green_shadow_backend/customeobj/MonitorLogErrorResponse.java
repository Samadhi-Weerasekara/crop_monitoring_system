package lk.ijse.green_shadow_backend.customeobj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MonitorLogErrorResponse implements MonitorLogResponse, Serializable {
    private int errorCode;
    private String errorMessage;
}
