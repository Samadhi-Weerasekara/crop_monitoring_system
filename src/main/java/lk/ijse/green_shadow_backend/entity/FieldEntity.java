package lk.ijse.green_shadow_backend.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



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

    @Column( nullable = false)
    private String fieldLocation;

    @Column(name = "extent_size", nullable = false)
    private Double extentSize; // Size in square meters

    @Lob
    @Column(name = "field_image1", columnDefinition = "LONGTEXT")
    private String fieldImage1;

    @Lob
    @Column(name = "field_image2", columnDefinition = "LONGTEXT")
    private String fieldImage2;

}
