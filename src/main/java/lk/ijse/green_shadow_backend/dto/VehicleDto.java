package lk.ijse.green_shadow_backend.dto;
import lk.ijse.green_shadow_backend.customeobj.VehicleResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleDto  implements SuperDto , VehicleResponse {
    private String vehicleCode; // Unique code for each vehicle, with a proper prefix
    private String licensePlateNumber; // Unique license plate number for each vehicle
    private String vehicleCategory; // Type of vehicle (e.g., Van, Truck, etc.)
    private String fuelType; // Type of fuel used by the vehicle (e.g., Diesel, Petrol)
    private String status; // Status of the vehicle (e.g., Available, Out of service)
    private String remarks; // Any special remark or "N/A" if none
    private String staffId; // Include staffId if needed
}
