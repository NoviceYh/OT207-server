package com.alkemy.ong.dto;

import java.util.ArrayList;
import java.util.List;

public class Base64ImageDTO {
    private byte[] imageBytes;
    private String fileName;
    private String fileType;
    private boolean hasErrors;
    private List<String> errorMessages;
    private static final List<String> VALID_FILE_TYPES = new ArrayList<String>(3);

    static {
        VALID_FILE_TYPES.add("jpg");
        VALID_FILE_TYPES.add("jpeg");
        VALID_FILE_TYPES.add("png");
    }

    public Base64ImageDTO(String b64ImageData, String fileName) {
        this.fileName = fileName;
        this.errorMessages = new ArrayList<String>(2);
        String[] base64Components = b64ImageData.split(",");

        if (base64Components.length != 2) {
            this.hasErrors = true;
            this.errorMessages.add("Invalid base64 data: " + b64ImageData);
        }

        if (!this.hasErrors) {
            String base64Data = base64Components[0];
            this.fileType = base64Data.substring(base64Data.indexOf('/') + 1, base64Data.indexOf(';'));

            if (!VALID_FILE_TYPES.contains(fileType)) {
                this.hasErrors = true;
                this.errorMessages.add("Invalid file type: " + fileType);
            }

            if (!this.hasErrors) {
                String base64Image = base64Components[1];
                this.imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
            }
        }
    }

    public byte[] getImageBytes() {
        return imageBytes;
    }

    public void setImageBytes(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }

    public boolean isHasErrors() {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
