package com.clone.metube.video.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VideoUploadResponse {
    private boolean isUploaded;
    private String message;
    private String videoUrl;
}
