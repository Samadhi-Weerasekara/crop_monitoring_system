package lk.ijse.green_shadow_backend.dto;

import lk.ijse.green_shadow_backend.customeobj.MonitorLogResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MonitoringLogDetailsDto  implements SuperDto, MonitorLogResponse {
    private String logCode; // Unique code for each log, with a proper prefix
    private Date logDate; // Date of the log
    private String logDetails; // Observation details of the log
    private String observedImage; // Image of the observed status (base64 string or path)

    // Relationship fields
    private Long fieldId; // ID of the field the log is related to
    private Long cropId;  // ID of the crop the log is related to
    private Long staffId; // ID of the staff who logged this
}
