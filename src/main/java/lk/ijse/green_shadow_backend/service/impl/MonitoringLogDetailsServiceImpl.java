package lk.ijse.green_shadow_backend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.green_shadow_backend.customeobj.MonitorLogErrorResponse;
import lk.ijse.green_shadow_backend.customeobj.MonitorLogResponse;
import lk.ijse.green_shadow_backend.dao.MonitoringLogDetailsDao;
import lk.ijse.green_shadow_backend.dto.MonitoringLogDetailsDto;
import lk.ijse.green_shadow_backend.entity.MonitoringLogDetailsEntity;
import lk.ijse.green_shadow_backend.exception.DataPersistFailedException;
import lk.ijse.green_shadow_backend.exception.MonitorLogNotFoundException;
import lk.ijse.green_shadow_backend.service.MonitorLogDetailsService;
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
public class MonitoringLogDetailsServiceImpl implements MonitorLogDetailsService {

    @Autowired
    private final MonitoringLogDetailsDao monitoringLogDetailsDao;
    @Autowired
    private final Mapping mapping;

    @Override
    public void saveLog(MonitoringLogDetailsDto monitoringLogDetailsDto) {
        monitoringLogDetailsDto.setLogCode(AppUtil.createLogId());
        MonitoringLogDetailsEntity savedLog =
                monitoringLogDetailsDao.save(mapping.convertLogDtoToEntity(monitoringLogDetailsDto));
        if (savedLog == null) {
            throw new DataPersistFailedException("Log saving failed");
        }
    }

    @Override
    public void updateLog(MonitoringLogDetailsDto monitoringLogDetailsDto) {
        Optional<MonitoringLogDetailsEntity> tmpLog =
                monitoringLogDetailsDao.findById(monitoringLogDetailsDto.getLogCode());
        if (!tmpLog.isPresent()) {
            throw new MonitorLogNotFoundException("Log not found");
        } else {
            tmpLog.get().setLogDate(monitoringLogDetailsDto.getLogDate());
            tmpLog.get().setLogDetails(monitoringLogDetailsDto.getLogDetails());
            tmpLog.get().setObservedImage(monitoringLogDetailsDto.getObservedImage());
        }
    }

    @Override
    public void deleteLog(String logCode) {
        Optional<MonitoringLogDetailsEntity> selectedLog = monitoringLogDetailsDao.findById(logCode);
        if (!selectedLog.isPresent()) {
            throw new MonitorLogNotFoundException("Log not found");
        } else {
            monitoringLogDetailsDao.deleteById(logCode);
        }
    }

    @Override
    public MonitorLogResponse getSelectedLog(String logCode) {
        if (monitoringLogDetailsDao.existsById(logCode)) {
            MonitoringLogDetailsEntity logEntity =
                    monitoringLogDetailsDao.getReferenceById(logCode);
            return mapping.convertLogsToDto(logEntity);
        } else {
            return new MonitorLogErrorResponse(0, "Log not found");
        }
    }

    @Override
    public List<MonitoringLogDetailsDto> getAllLogs() {
        List<MonitoringLogDetailsEntity> allLogs = monitoringLogDetailsDao.findAll();
        return mapping.convertLogsEntityToDTO(allLogs);
    }
}
