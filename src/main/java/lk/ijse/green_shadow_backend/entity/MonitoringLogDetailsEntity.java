package lk.ijse.green_shadow_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "logs")
public class MonitoringLogDetailsEntity implements SuperEntity {

    @Id
    private String logCode;  // Unique code for each log (e.g., a prefix can be added)


    @Temporal(TemporalType.DATE)
    private Date logDate;  // Date of the log


    private String logDetails;  // Observation or details of the log

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String observedImage;  // Image or description of the observed status (could be a URL or base64 string)

    @ManyToOne
    @JoinColumn(name = "field_id")
    private FieldEntity field;

    @ManyToOne
    @JoinColumn(name = "crop_id")
    private CropEntity crop;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private StaffEntity staff;

}
