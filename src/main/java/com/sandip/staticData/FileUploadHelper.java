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

    public final String UPLOAD_Dir = new ClassPathResource("static/images").getFile().getAbsolutePath();

    public FileUploadHelper() throws IOException {

    }

    public boolean uploadFile(MultipartFile file) {

        boolean f = false;

        try {

            Files.copy(file.getInputStream(), Paths.get(UPLOAD_Dir + File.separator + file.getOriginalFilename()),
                    StandardCopyOption.ATOMIC_MOVE);

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }

        return f;
    }

}
