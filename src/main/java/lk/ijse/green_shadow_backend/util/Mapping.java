package lk.ijse.green_shadow_backend.util;

import lk.ijse.green_shadow_backend.dto.CropDto;
import lk.ijse.green_shadow_backend.dto.FieldDto;
import lk.ijse.green_shadow_backend.dto.StaffDto;
import lk.ijse.green_shadow_backend.entity.CropEntity;
import lk.ijse.green_shadow_backend.entity.FieldEntity;
import lk.ijse.green_shadow_backend.entity.StaffEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    //matters of StaffEntity and DTO
    public StaffDto convertToDto(StaffEntity staff){
        return modelMapper.map(staff,StaffDto.class);
    }

    public StaffEntity converToEntity(StaffDto staffDto){
        return modelMapper.map(staffDto,StaffEntity.class);
    }

    public List<StaffDto> convertToDTO(List<StaffEntity> staffEntities) {
        return modelMapper.map(staffEntities, new TypeToken<List<StaffDto>>() {}.getType());
    }

    //matters of cropEntity and DTO
    public CropDto convertToDto(CropEntity crop){
        return modelMapper.map(crop,CropDto.class);
    }

    public CropEntity converToEntity(CropDto cropDto){
        return modelMapper.map(cropDto,CropEntity.class);
    }

    public List<CropDto> convertCropEntityToDTO(List<CropEntity> cropEntities) {
        return modelMapper.map(cropEntities, new TypeToken<List<CropDto>>() {}.getType());
    }

    //matters of fieldEntity and DTO
    public FieldDto convertToDto(FieldEntity field){
        return modelMapper.map(field,FieldDto.class);
    }

    public FieldEntity converToEntity(FieldDto fieldDto){
        return modelMapper.map(fieldDto,FieldEntity.class);
    }

    public List<FieldDto> convertFieldEntityToDTO(List<FieldEntity> fieldEntities) {
        return modelMapper.map(fieldEntities, new TypeToken<List<FieldDto>>() {}.getType());
    }
}
