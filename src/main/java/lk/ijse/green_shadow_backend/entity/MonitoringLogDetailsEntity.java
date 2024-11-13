package lk.ijse.green_shadow_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "logs")
public class MonitoringLogDetailsEntity implements SuperEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Unique identifier for the log entry

    @Column(nullable = false, unique = true)
    private String logCode;  // Unique code for each log (e.g., a prefix can be added)

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date logDate;  // Date of the log

    @Column(nullable = false)
    private String logDetails;  // Observation or details of the log

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String observedImage;  // Image or description of the observed status (could be a URL or base64 string)


}
