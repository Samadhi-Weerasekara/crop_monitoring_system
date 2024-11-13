package lk.ijse.green_shadow_backend.service;

import lk.ijse.green_shadow_backend.customeobj.CropResponse;
import lk.ijse.green_shadow_backend.dto.CropDto;

import java.util.List;

public interface CropService {
    void saveCrop(CropDto cropDto);
    void updateCrop(CropDto cropDto);
    void deleteCrop(String cropCode);
    CropResponse getSelectedCrop(String cropCode);
    List<CropDto> getAllCrops();
}
