package com.devcompany.crud1.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class CloudinaryServiceImpl implements CloudinaryService{
    private final Cloudinary cloudinary;

    public CloudinaryServiceImpl(){
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("cloud_name", "");
        valuesMap.put("cloud_key", "");
        valuesMap.put("cloud_secret", "");
        cloudinary = new Cloudinary(valuesMap);
    }

    @Override
    public Map<String, Object> upload(MultipartFile multipartFile) throws IOException{
        File file = convert(multipartFile);
        Map<String, Object> result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        if (!Files.deleteIfExists(file.toPath())){
            throw new IOException("Failed to delete temporary file: " + file.getAbsolutePath());
        }
        return result;
    }

    private File convert(MultipartFile multipartFile) throws IOException{
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
    }
}
