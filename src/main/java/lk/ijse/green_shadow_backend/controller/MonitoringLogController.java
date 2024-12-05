package lk.ijse.green_shadow_backend.controller;

import lk.ijse.green_shadow_backend.customeobj.MonitorLogResponse;
import lk.ijse.green_shadow_backend.dto.MonitoringLogDetailsDto;
import lk.ijse.green_shadow_backend.exception.DataPersistFailedException;
import lk.ijse.green_shadow_backend.exception.MonitorLogNotFoundException;
import lk.ijse.green_shadow_backend.service.MonitorLogDetailsService;
import lk.ijse.green_shadow_backend.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/logs")

public class MonitoringLogController {

    @Autowired
     MonitorLogDetailsService monitorLogDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(MonitoringLogController.class);

    // Save Log
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveLog(
            @RequestParam("logDate") String logDate,
            @RequestParam("logDetails") String logDetails,
            @RequestParam(value = "fieldIds", required = false) String fieldIds,
            @RequestParam(value = "cropIds", required = false) String cropIds,
            @RequestParam("staffId") String staffId,
            @RequestParam("observedImage") MultipartFile observedImage) {
        try {
            // Validate and parse the date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(logDate);

            // Convert the image to a Base64 string
            String base64Image = AppUtil.toBase64ProfilePic(observedImage);

            // Create and populate the DTO
            MonitoringLogDetailsDto logDto = new MonitoringLogDetailsDto();
            logDto.setLogDate(parsedDate);
            logDto.setLogDetails(logDetails);
            logDto.setObservedImage(base64Image);
            logDto.setFieldId(fieldIds); // Assuming you want to set these as well
            logDto.setCropId(cropIds);   // Assuming you want to set these as well
            logDto.setStaffId(staffId);    // Assuming you want to set the staff ID as well

            // Save the log using the service
            monitorLogDetailsService.saveLog(logDto);

            // Return 201 Created
            logger.info("Log saved successfully");
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (ParseException e) {
            // Handle date parsing errors
            logger.error("Date parsing failed", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (DataPersistFailedException e) {
            // Handle persistence failures
            logger.error("Failed to save log", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Handle unexpected errors
            logger.error("An unexpected error occurred", e);
            e.printStackTrace(); // Consider logging the exception
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Delete Log
    @DeleteMapping("/{logCode}")
    public ResponseEntity<Void> deleteLog(@PathVariable("logCode") String logCode) {
        try {
            monitorLogDetailsService.deleteLog(logCode);
            logger.info("Log deleted successfully");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (MonitorLogNotFoundException e) {
            logger.error("Log not found", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("An unexpected error occurred", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get Selected Log
    @GetMapping(value = "/{logCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MonitorLogResponse getSelectedLog(@PathVariable("logCode") String logCode) {
        logger.info("Getting log with code: " + logCode);
        return monitorLogDetailsService.getSelectedLog(logCode);
    }

    // Get All Logs
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MonitoringLogDetailsDto> getAllLogs() {
        logger.info("Getting all logs");
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
            logger.info("Log updated successfully");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (MonitorLogNotFoundException e) {
            logger.error("Log not found", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("An unexpected error occurred", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
