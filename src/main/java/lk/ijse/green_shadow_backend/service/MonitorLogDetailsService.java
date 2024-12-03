package lk.ijse.green_shadow_backend.service;

import lk.ijse.green_shadow_backend.customeobj.MonitorLogResponse;
import lk.ijse.green_shadow_backend.customeobj.StaffResponse;
import lk.ijse.green_shadow_backend.dto.MonitoringLogDetailsDto;
import lk.ijse.green_shadow_backend.dto.StaffDto;

import java.util.List;

public interface MonitorLogDetailsService {
    void saveLog(MonitoringLogDetailsDto monitoringLogDetailsDto);
    void updateLog(MonitoringLogDetailsDto monitoringLogDetailsDto);
    void deleteLog(String logCode);
    MonitorLogResponse getSelectedLog(String logCode);
    List<MonitoringLogDetailsDto> getAllLogs();
}
