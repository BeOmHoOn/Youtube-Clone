package com.clone.metube.video.controllers;

import com.clone.metube.global.annotations.RequireAccessToken;
import com.clone.metube.video.dtos.VideoUploadRequest;
import com.clone.metube.video.services.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
public class VideoController {
    private final VideoService videoService;

    @PostMapping("/upload")
    @RequireAccessToken
    public ResponseEntity<?> uploadVideo(@RequestPart("file") MultipartFile file,
                                         @RequestPart("data") VideoUploadRequest videoUploadRequest) throws Exception {
        return ResponseEntity.ok().body(videoService.uploadVideo(file, videoUploadRequest));
    }
}