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
    @Column(name = "field_code", nullable = false, length = 255)
    private String fieldCode;

    @Column(name = "field_name", nullable = false, length = 255)
    private String fieldName;

    @Column(name = "field_location", nullable = false)
    private String fieldLocation; // GPS coordinate as a string (e.g., "latitude,longitude")

    @Column(name = "extent_size", nullable = false)
    private Double extentSize; // Size in square meters

    @Lob
    @Column(name = "field_image1", columnDefinition = "LONGTEXT")
    private String fieldImage1;

    @Lob
    @Column(name = "field_image2", columnDefinition = "LONGTEXT")
    private String fieldImage2;

    @OneToMany
    @JoinColumn(name = "field_id")
    private List<CropEntity> crops; // List of crops in the field

    @OneToMany
    @JoinColumn(name = "field_id")
    private List<StaffEntity> staff; // List of staff allocated to the field
}
