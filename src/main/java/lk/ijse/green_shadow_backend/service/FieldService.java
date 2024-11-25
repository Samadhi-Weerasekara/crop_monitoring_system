package lk.ijse.green_shadow_backend.service;

import lk.ijse.green_shadow_backend.customeobj.FieldResponse;
import lk.ijse.green_shadow_backend.dto.FieldDto;

import java.util.List;

public interface FieldService {
    void saveField(FieldDto fieldDto);
    void updateField(FieldDto fieldDto);
    void deleteField(String fieldId);
    FieldResponse getSelectedField(String fieldId);
    List<FieldDto> getAllFields();
}
