package com.devcompany.crud1.services;

import com.cloudinary.Cloudinary;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
}
