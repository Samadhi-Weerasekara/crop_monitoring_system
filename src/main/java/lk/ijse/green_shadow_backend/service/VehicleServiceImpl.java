package lk.ijse.green_shadow_backend.service;

import jakarta.transaction.Transactional;
import lk.ijse.green_shadow_backend.customeobj.VehicleResponse;
import lk.ijse.green_shadow_backend.customeobj.VehicleErrorResponse;
import lk.ijse.green_shadow_backend.dao.VehicleDao;
import lk.ijse.green_shadow_backend.dto.VehicleDto;
import lk.ijse.green_shadow_backend.entity.VehicleEntity;
import lk.ijse.green_shadow_backend.exception.DataPersistFailedException;
import lk.ijse.green_shadow_backend.exception.VehicleNotFoundException;
import lk.ijse.green_shadow_backend.util.AppUtil;
import lk.ijse.green_shadow_backend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private final VehicleDao vehicleDao;

    @Autowired
    private final Mapping mapping;

    @Override
    public void saveVehicle(VehicleDto vehicleDto) {
        vehicleDto.setVehicleCode(AppUtil.createVehicleId());
        VehicleEntity savedVehicle = vehicleDao.save(mapping.converToEntity(vehicleDto));
        if (savedVehicle == null) {
            throw new DataPersistFailedException("Cannot save vehicle data");
        }
    }

    @Override
    public void updateVehicle(String vehicleId, VehicleDto vehicleDto)
    {Optional<VehicleEntity> tmpVehicleEntity= vehicleDao.findById(vehicleId);
        if(!tmpVehicleEntity.isPresent()){
            throw new VehicleNotFoundException("Vehicle not found");
        }else {
            tmpVehicleEntity.get().setLicensePlateNumber(vehicleDto.getLicensePlateNumber());
            tmpVehicleEntity.get().setVehicleCategory(vehicleDto.getVehicleCategory());
            tmpVehicleEntity.get().setFuelType(vehicleDto.getFuelType());
            tmpVehicleEntity.get().setStatus(vehicleDto.getStatus());
            tmpVehicleEntity.get().setRemarks(vehicleDto.getRemarks());

        }
    }



    @Override
    public void deleteVehicle(String vehicleId) {
        Optional<VehicleEntity> selectedVehicle = vehicleDao.findById(vehicleId);
        if (!selectedVehicle.isPresent()) {
            throw new VehicleNotFoundException("Vehicle not found");
        } else {
            vehicleDao.deleteById(vehicleId);
        }
    }

    @Override
    public VehicleResponse getSelectedVehicle(String vehicleId) {
        if (vehicleDao.existsById(vehicleId)) {
            VehicleEntity vehicleEntity = vehicleDao.getReferenceById(vehicleId);
            return mapping.convertToDto(vehicleEntity);
        } else {
            return new VehicleErrorResponse(0, "Vehicle not found");
        }
    }

    @Override
    public List<VehicleDto> getAllVehicles() {
        List<VehicleEntity> vehicleEntities = vehicleDao.findAll();
        return mapping.convertVehicleEntityToDTO(vehicleEntities);
    }
}
