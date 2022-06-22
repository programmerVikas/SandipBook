package com.sandip.staticData;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {

    public final String UPLOAD_Dir = new ClassPathResource("static/uploadedImages").getFile().getAbsolutePath();

    public FileUploadHelper() throws IOException {

    }

    public boolean uploadFile(MultipartFile file, String uniqueName) {

        boolean f = false;

        try {

            //for uniqye name for file 
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
