package com.shibro.travel.data.dto;

import lombok.Data;

import java.util.List;

@Data
public class FileUploadResponse {
    private List<FileUploadResponseItem> uploadResults;
}
