package lk.ijse.green_shadow_backend.util;

import lk.ijse.green_shadow_backend.dto.*;
import lk.ijse.green_shadow_backend.entity.*;
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

    //matters of vehicleEntity and DTO
    public VehicleDto convertToDto(VehicleEntity vehicle){
        return modelMapper.map(vehicle,VehicleDto.class);
    }

    public VehicleEntity converToEntity(VehicleDto vehicleDto){
        return modelMapper.map(vehicleDto,VehicleEntity.class);
    }

    public List<VehicleDto> convertVehicleEntityToDTO(List<VehicleEntity> vehicleEntities) {
        return modelMapper.map(vehicleEntities, new TypeToken<List<VehicleDto>>() {}.getType());
    }

    //matters of equipEntity and DTO
    public EquipmentDto convertEquipmentToDto(EquipmentEntity equipment){
        return modelMapper.map(equipment,EquipmentDto.class);
    }

    public EquipmentEntity convertEquipDtoToEntity(EquipmentDto equipmentDto){
        return modelMapper.map(equipmentDto,EquipmentEntity.class);
    }

    public List<EquipmentDto> convertEquipmentEntityToDTO(List<EquipmentEntity> equipmentEntities) {
        return modelMapper.map(equipmentEntities, new TypeToken<List<EquipmentDto>>() {}.getType());
    }


    //matters of monitorlogEntity and DTO
    public MonitoringLogDetailsDto convertLogsToDto(MonitoringLogDetailsEntity monitoringLogDetailsEntity){
        return modelMapper.map(monitoringLogDetailsEntity,MonitoringLogDetailsDto.class);
    }

    public MonitoringLogDetailsEntity convertLogDtoToEntity(MonitoringLogDetailsDto monitoringLogDetailsDto){
        return modelMapper.map(monitoringLogDetailsDto,MonitoringLogDetailsEntity.class);
    }

    public List<MonitoringLogDetailsDto> convertLogsEntityToDTO(List<MonitoringLogDetailsEntity> monitoringLogDetailsEntityList) {
        return modelMapper.map(monitoringLogDetailsEntityList, new TypeToken<List<MonitoringLogDetailsDto>>() {}.getType());
    }
}
