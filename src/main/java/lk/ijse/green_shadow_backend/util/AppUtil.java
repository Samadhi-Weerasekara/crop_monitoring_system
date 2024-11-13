package lk.ijse.green_shadow_backend.util;

import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {
    public static String createStaffId(){return "STAFF-"+ UUID.randomUUID();}
    public static String createCropId(){return "CROP-"+ UUID.randomUUID();}

    public static String toBase64ProfilePic(MultipartFile picture){
        String proPicBase64 = null;
        try {
            byte [] proPicBytes = picture.getBytes();
            proPicBase64 =  Base64.getEncoder().encodeToString(proPicBytes);
        }catch (Exception e){
            e.printStackTrace();
        }
        return proPicBase64;
    }
}
