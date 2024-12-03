package lk.ijse.green_shadow_backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "vehicle")
public class VehicleEntity implements SuperEntity{
    @Id
    private String vehicleCode;

    @Column(nullable = false)
    private String licensePlateNumber;

    @Column(nullable = false)
    private String vehicleCategory;

    @Column(nullable = false)
    private String fuelType;

    @Column(nullable = false)
    private String status;

    private String remarks;
}
