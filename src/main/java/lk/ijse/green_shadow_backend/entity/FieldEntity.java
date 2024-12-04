package lk.ijse.green_shadow_backend.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "field")
public class FieldEntity implements SuperEntity{
    @Id
    @Column(name = "field_code",  length = 255)
    private String fieldCode;

    @Column(name = "field_name", length = 255)
    private String fieldName;


    private String fieldLocation;

    @Column(name = "extent_size")
    private Double extentSize; // Size in square meters

    @Lob
    @Column(name = "field_image1", columnDefinition = "LONGTEXT")
    private String fieldImage1;

    @Lob
    @Column(name = "field_image2", columnDefinition = "LONGTEXT")
    private String fieldImage2;

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    private List<CropEntity> crops;

    @OneToMany(mappedBy = "field")
    private List<StaffEntity> staffAssignments;

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    private List<MonitoringLogDetailsEntity> logs;





}
