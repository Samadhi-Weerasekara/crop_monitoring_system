package lk.ijse.green_shadow_backend.service;

import jakarta.transaction.Transactional;
import lk.ijse.green_shadow_backend.customeobj.EquipmentErrorResponse;
import lk.ijse.green_shadow_backend.customeobj.EquipmentResponse;
import lk.ijse.green_shadow_backend.customeobj.VehicleErrorResponse;
import lk.ijse.green_shadow_backend.dao.EquipmentDao;
import lk.ijse.green_shadow_backend.dto.EquipmentDto;
import lk.ijse.green_shadow_backend.entity.EquipmentEntity;
import lk.ijse.green_shadow_backend.entity.VehicleEntity;
import lk.ijse.green_shadow_backend.exception.DataPersistFailedException;
import lk.ijse.green_shadow_backend.exception.EquipmentNotFoundException;
import lk.ijse.green_shadow_backend.util.AppUtil;
import lk.ijse.green_shadow_backend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    private EquipmentDao equipmentDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveEquipment(EquipmentDto equipmentDto) {
        equipmentDto.setEquipmentId(AppUtil.createEquipmentId());
        EquipmentEntity equipmentEntity = mapping.convertEquipDtoToEntity(equipmentDto);
        EquipmentEntity savedEquipment = equipmentDao.save(equipmentEntity);
        if (savedEquipment == null) {
            throw new DataPersistFailedException("Failed to save equipment");
        }
    }

    @Override
    public void updateEquipment(String equipmentId, EquipmentDto equipmentDto) {
        Optional<EquipmentEntity> existingEquipment = equipmentDao.findById(equipmentId);
        if (!existingEquipment.isPresent()) {
            throw new EquipmentNotFoundException("Equipment not found");
        } else {
            existingEquipment.get().setName(equipmentDto.getName());
            existingEquipment.get().setType(equipmentDto.getType());
            existingEquipment.get().setStatus(equipmentDto.getStatus());
        }
    }

    @Override
    public void deleteEquipment(String equipmentId) {
        Optional<EquipmentEntity> existingEquipment = equipmentDao.findById(equipmentId);
        if (!existingEquipment.isPresent()) {
            throw new EquipmentNotFoundException("Equipment not found");
        } else {
            equipmentDao.deleteById(equipmentId);
        }
    }

    @Override
    public EquipmentResponse getSelectedEquipment(String equipmentId) {
        if (equipmentDao.existsById(equipmentId)) {
            EquipmentEntity equipmentEntity = equipmentDao.getReferenceById(equipmentId);
            return mapping.convertEquipmentToDto(equipmentEntity);
        } else {
            return new EquipmentErrorResponse(0, "Equipment not found");
        }
    }

    @Override
    public List<EquipmentDto> getAllEquipments() {

        return mapping.convertEquipmentEntityToDTO(equipmentDao.findAll());
    }
}
