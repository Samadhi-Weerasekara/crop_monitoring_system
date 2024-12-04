package lk.ijse.green_shadow_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "vehicle")
public class VehicleEntity implements SuperEntity {
    @Id
    private String vehicleCode;

    private String licensePlateNumber;

    private String vehicleCategory;

    private String fuelType;
    
    private String status;

    private String remarks;

    @ManyToOne
    @JoinColumn(name = "staff_id") // or your foreign key column name
    private StaffEntity staff;

}
