package lk.ijse.green_shadow_backend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.green_shadow_backend.customeobj.StaffErrorResponse;
import lk.ijse.green_shadow_backend.customeobj.StaffResponse;
import lk.ijse.green_shadow_backend.dao.StaffDao;
import lk.ijse.green_shadow_backend.dto.StaffDto;
import lk.ijse.green_shadow_backend.entity.StaffEntity;
import lk.ijse.green_shadow_backend.exception.DataPersistFailedException;
import lk.ijse.green_shadow_backend.exception.StaffNotFoundException;
import lk.ijse.green_shadow_backend.service.StaffService;
import lk.ijse.green_shadow_backend.util.AppUtil;
import lk.ijse.green_shadow_backend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffDao staffDao;

    @Autowired
    private Mapping mapping;
    @Override
    public void saveStaff(StaffDto staffDto) {
         staffDto.setId(AppUtil.createStaffId());
        var staffEntity = mapping.converToEntity(staffDto);
        StaffEntity savedStaff = staffDao.save(staffEntity);
        if(savedStaff == null){
            throw new DataPersistFailedException("Cannot save staff member");
        }
    }

    @Override
    public void updateStaff(String staffId, StaffDto staffDto) {
        Optional<StaffEntity> tmpStaffEntity = staffDao.findById(staffId);
        if(!tmpStaffEntity.isPresent()){
            throw new StaffNotFoundException("Staff member not found");
        }else {
            tmpStaffEntity.get().setFirstName(staffDto.getFirstName());
            tmpStaffEntity.get().setLastName(staffDto.getLastName());
            tmpStaffEntity.get().setDesignation(staffDto.getDesignation());
            tmpStaffEntity.get().setGender(staffDto.getGender());
            tmpStaffEntity.get().setJoinedDate(staffDto.getJoinedDate());
            tmpStaffEntity.get().setDob(staffDto.getDob());
            tmpStaffEntity.get().setAddress(staffDto.getAddress());
            tmpStaffEntity.get().setContactNo(staffDto.getContactNo());
            tmpStaffEntity.get().setEmail(staffDto.getEmail());
            tmpStaffEntity.get().setRole(staffDto.getRole());
        }
    }

    @Override
    public void deleteStaff(String staffId) {
        Optional<StaffEntity> findById = staffDao.findById(staffId);
        if(!findById.isPresent()){
            throw new StaffNotFoundException("Staff member not found");
        }else {
            staffDao.deleteById(staffId);
        }
    }

    @Override
    public StaffResponse getSelectedStaff(String staffId) {
        if(staffDao.existsById(staffId)){
            return mapping.convertToDto(staffDao.getReferenceById(staffId));
        }else {
            return new StaffErrorResponse(0,"Staff member not Found");
        }
    }

    @Override
    public List<StaffDto> getAllStaff() {
        return mapping.convertToDTO(staffDao.findAll());
    }
}
