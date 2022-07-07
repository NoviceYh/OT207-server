package com.alkemy.ong.auth.service;

import com.alkemy.ong.auth.model.vm.Asset;
import com.alkemy.ong.dto.Base64ImageDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface IS3Service {

    String putObject(MultipartFile multipartFile);

    String uploadBase64Image(Base64ImageDTO base64ImageDto);

    List<String> getObjectsFromS3();

    Asset getObject(String key);

    InputStream downloadFile(String key);

    void deleteObject(String key);

    String getObjectUrl(String key);

}
