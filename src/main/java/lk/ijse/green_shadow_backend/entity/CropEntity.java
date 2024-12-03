package lk.ijse.green_shadow_backend.entity;

import jakarta.persistence.*;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "crops")
public class CropEntity implements SuperEntity {
    @Id
    @Column(name = "crop_code", unique = true)
    private String cropCode;  // Unique identifier for the crop


    private String commonName;  // Common name of the crop


    private String scientificName;  // Scientific name of the crop

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String cropImage;  // Image or description of the crop (could be a URL or base64 string)


    private String category;  // Category of the crop (e.g., Cereal)

    private String cropSeason;  // Season of the crop (e.g., Summer, Winter)

    @ManyToOne
    @JoinColumn(name = "field_id")
    private FieldEntity field;

    @OneToMany(mappedBy = "crop", cascade = CascadeType.ALL)
    private List<MonitoringLogDetailsEntity> logs;

}
