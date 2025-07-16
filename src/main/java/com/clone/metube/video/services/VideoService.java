package com.clone.metube.video.services;

import com.clone.metube.authentication.normal.repositories.AccountRepository;
import com.clone.metube.video.dtos.VideoUploadRequest;
import com.clone.metube.video.dtos.VideoUploadResponse;
import com.clone.metube.video.entities.Video;
import com.clone.metube.video.repositories.VideoRepository;
import com.clone.metube.video.utils.FileSignatureChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VideoService {
    private final AccountRepository accountRepository;
    private final VideoRepository videoRepository;
    private final Path uploadDirectory = Paths.get("uploads/videos");

    public VideoUploadResponse uploadVideo(MultipartFile file, VideoUploadRequest videoUploadRequest) throws Exception {
        String fileName = "";

        if (file.isEmpty()) {
            //throw new Exception(""); // 나중에 FileIsEmptyException 만들어서 BadRequest로 보내기
        }

        try {
            //파일점검 및 저장
            if (FileSignatureChecker.checkSignature(file)) {
                return new VideoUploadResponse(false, "MP4형식의 동영상만 업로드할 수 있습니다.", null);
            }

            if (!Files.exists(uploadDirectory)) {
                Files.createDirectories(uploadDirectory);
            }

            String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
            fileName = UUID.randomUUID() + "_" + (int) (Math.random() * 1000000) + 1;

            Path targetPath = uploadDirectory.resolve(fileName).normalize();
            System.out.println(targetPath);
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            // db저장
            String email = videoUploadRequest.getEmail();
            var requestAccount = accountRepository.findByEmail(email)
                    .orElseThrow(() -> new Exception(""));

            String title = videoUploadRequest.getTitle();
            String description = videoUploadRequest.getDescription();

            var newVideo = Video.builder()
                    .localAuthorId(email)
                    .title(title)
                    .filePath(targetPath.toString())
                    .description(description)
                    .build();

            videoRepository.save(newVideo);
        } catch (IOException e) {

        }

        return new VideoUploadResponse(true, "동영상이 업로드 되었습니다.", "/videos/" + fileName);
    }
}
