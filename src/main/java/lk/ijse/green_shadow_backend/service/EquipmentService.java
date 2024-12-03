package lk.ijse.green_shadow_backend.service;

import lk.ijse.green_shadow_backend.customeobj.EquipmentResponse;
import lk.ijse.green_shadow_backend.dto.EquipmentDto;

import java.util.List;

public interface EquipmentService {
    void saveEquipment(EquipmentDto equipmentDto);
    void updateEquipment(String equipmentId,EquipmentDto equipmentDto);
    void deleteEquipment(String equipmentId);
    EquipmentResponse getSelectedEquipment(String equipmentId);
    List<EquipmentDto> getAllEquipments();
}
