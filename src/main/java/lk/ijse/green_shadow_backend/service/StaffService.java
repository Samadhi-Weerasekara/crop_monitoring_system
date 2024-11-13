package lk.ijse.green_shadow_backend.service;

import lk.ijse.green_shadow_backend.customeobj.StaffResponse;
import lk.ijse.green_shadow_backend.dto.StaffDto;

import java.util.List;

public interface StaffService {
    void saveStaff(StaffDto staffDto);
    void updateStaff(String staffId,StaffDto staffDto);
    void deleteStaff(String staffId);
    StaffResponse getSelectedStaff(String staffId);
    List<StaffDto> getAllStaff();
}
