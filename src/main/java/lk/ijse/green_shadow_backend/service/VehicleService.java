package lk.ijse.green_shadow_backend.service;

import lk.ijse.green_shadow_backend.customeobj.VehicleResponse;
import lk.ijse.green_shadow_backend.dto.VehicleDto;

import java.util.List;

public interface VehicleService {
    void saveVehicle(VehicleDto vehicleDto);
    void updateVehicle(String vehicleId, VehicleDto vehicleDto);
    void deleteVehicle(String vehicleId);
    VehicleResponse getSelectedVehicle(String vehicleId);
    List<VehicleDto> getAllVehicles();
}
