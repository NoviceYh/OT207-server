package com.alkemy.ong.auth.controller;

import com.alkemy.ong.auth.model.vm.Asset;
import com.alkemy.ong.auth.service.IS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    @Autowired
    private IS3Service s3ServiceImpl;

    @PostMapping(value = "/upload")
    Map<String, String> upload(@RequestParam MultipartFile file) {
        String key = s3ServiceImpl.putObject(file);

        Map<String, String> result = new HashMap<>();
        result.put("key", key);
        result.put("url", s3ServiceImpl.getObjectUrl(key));

        return result;
    }

    @GetMapping(value = "/get-object", params = "key")
    public ResponseEntity<ByteArrayResource> getObject(@RequestParam String key) {
        Asset asset = s3ServiceImpl.getObject(key);
        ByteArrayResource resource = new ByteArrayResource(asset.getContent());
        return ResponseEntity
                .ok()
                .header("Content-Type", asset.getContentType())
                .contentLength(asset.getContent().length)
                .body(resource);
    }

    @DeleteMapping(value = "/delete-object", params = "key")
    public void deleteObject(@RequestParam String key) {
        s3ServiceImpl.deleteObject(key);
    }

}
