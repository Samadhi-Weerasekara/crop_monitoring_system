package lk.ijse.green_shadow_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MonitoringLogDetailsDto  implements SuperDto {
    private String logCode; // Unique code for each log, with a proper prefix
    private Date logDate; // Date of the log
    private String logDetails; // Observation details of the log
    private String observedImage; // Image of the observed status (base64 string or path)
    private List<FieldDto> fields = new ArrayList<>(); // List of relevant fields for log monitoring
    private List<CropDto> crops = new ArrayList<>(); // List of relevant crops for log monitoring
    private List<StaffDto> staff = new ArrayList<>(); // List of relevant staff members for log monitoring
}
