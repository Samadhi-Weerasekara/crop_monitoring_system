package lk.ijse.green_shadow_backend.entity;
import jakarta.persistence.*;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CropEntity  implements SuperEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String cropCode;  // Unique identifier for the crop

    @Column(nullable = false)
    private String commonName;  // Common name of the crop

    @Column(nullable = false)
    private String scientificName;  // Scientific name of the crop

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String cropImage;  // Image or description of the crop (could be a URL or base64 string)

    @Column(nullable = false)
    private String category;  // Category of the crop (e.g., Cereal)

    @Column(nullable = false)
    private String cropSeason;  // Season of the crop (e.g., Summer, Winter)


}
