package com.tharanga.event_app.services.impl;

import com.tharanga.event_app.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadFile(String path, MultipartFile file) throws IOException {
        // Get the name of the file
        String fileName = file.getOriginalFilename();

        // Create the file path
        String filePath = path + File.separator + fileName;

        // Create a File object
        File f = new File(path);

        // If the directory doesn't exist, create it
        if (!f.exists()) {
            f.mkdir();
        }

        // Copy the file to the specified path
        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

        // Return the name of the uploaded file
        return fileName;
    }

    @Override
    public InputStream getResourceFile(String path, String filename) throws FileNotFoundException {
        String filePath = path + File.separator + filename;
        return new FileInputStream(filePath);
    }
}
