package lk.ijse.green_shadow_backend.controller;

import lk.ijse.green_shadow_backend.customeobj.MonitorLogResponse;
import lk.ijse.green_shadow_backend.dto.MonitoringLogDetailsDto;
import lk.ijse.green_shadow_backend.exception.DataPersistFailedException;
import lk.ijse.green_shadow_backend.exception.MonitorLogNotFoundException;
import lk.ijse.green_shadow_backend.service.MonitorLogDetailsService;
import lk.ijse.green_shadow_backend.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/logs")
@RequiredArgsConstructor

public class MonitoringLogController {

    @Autowired
    private final MonitorLogDetailsService monitorLogDetailsService;

    // Save Log
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveLog(
            @RequestParam("logDate") String logDate,
            @RequestParam("logDetails") String logDetails,
            @RequestParam("observedImage") MultipartFile observedImage) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(logDate);
            String base64Image = AppUtil.toBase64ProfilePic(observedImage);

            MonitoringLogDetailsDto logDto = new MonitoringLogDetailsDto();
            logDto.setLogDate(parsedDate);
            logDto.setLogDetails(logDetails);
            logDto.setObservedImage(base64Image);

            monitorLogDetailsService.saveLog(logDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete Log
    @DeleteMapping("/{logCode}")
    public ResponseEntity<Void> deleteLog(@PathVariable("logCode") String logCode) {
        try {
            monitorLogDetailsService.deleteLog(logCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (MonitorLogNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get Selected Log
    @GetMapping(value = "/{logCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MonitorLogResponse getSelectedLog(@PathVariable("logCode") String logCode) {
        return monitorLogDetailsService.getSelectedLog(logCode);
    }

    // Get All Logs
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MonitoringLogDetailsDto> getAllLogs() {
        return monitorLogDetailsService.getAllLogs();
    }

    // Update Log
    @PatchMapping(value = "/{logCode}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateLog(
            @PathVariable("logCode") String logCode,
            @RequestParam("updateLogDate") String updateLogDate,
            @RequestParam("updateLogDetails") String updateLogDetails,
            @RequestParam("updateObservedImage") MultipartFile updateObservedImage) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date updatedParsedDate = dateFormat.parse(updateLogDate);
            String updateBase64Image = AppUtil.toBase64ProfilePic(updateObservedImage);

            MonitoringLogDetailsDto updatedLog = new MonitoringLogDetailsDto();
            updatedLog.setLogCode(logCode);
            updatedLog.setLogDate(updatedParsedDate);
            updatedLog.setLogDetails(updateLogDetails);
            updatedLog.setObservedImage(updateBase64Image);

            monitorLogDetailsService.updateLog(updatedLog);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (MonitorLogNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
