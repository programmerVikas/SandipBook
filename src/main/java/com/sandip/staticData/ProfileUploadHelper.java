package com.sandip.staticData;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

// import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ProfileUploadHelper {

    // it will not work when you will final deploy on server, for that comment this
    // line and uncomment second line

    public final String UPLOAD_Dir = "G:/Sandip-Book/SandipBook/src/main/resources/static/profilePictures";

    // for final deploying on server otherwise your files will delete all time when
    // you will redeploy your project while developing project
    // public final String UPLOAD_Dir = new
    // ClassPathResource("static/uploadedImages").getFile().getAbsolutePath();

    public ProfileUploadHelper() throws IOException {

    }

    public boolean uploadFile(MultipartFile file, String uniqueName) {

        File directory = new File(UPLOAD_Dir);

        if (!directory.exists()) {
            directory.mkdir();
        }

        boolean f = false;

        try {

            // for uniqye name for file
            String uniquefileName = uniqueName;

            Files.copy(file.getInputStream(), Paths.get(UPLOAD_Dir + File.separator + uniquefileName.concat(
                    file.getOriginalFilename())), StandardCopyOption.REPLACE_EXISTING);

            f = true;
        } catch (Exception e) {
            System.out.println(e);
        }

        return f;
    }

}
