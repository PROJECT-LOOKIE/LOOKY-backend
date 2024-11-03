package com.likelion.lookie.controller.file;

import lombok.RequiredArgsConstructor;
import com.likelion.lookie.common.dto.PresignedUrlRequest;
import com.likelion.lookie.common.dto.PresignedUrlResponse;
import com.likelion.lookie.common.exception.ApplicationResponse;
import com.likelion.lookie.common.util.FileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/file")
public class FileController implements FileControllerDocs{

    private final FileService fileService;

    @PostMapping
    public ApplicationResponse<PresignedUrlResponse> uploadPhoto(@RequestBody PresignedUrlRequest presignedUrlRequest) {
        PresignedUrlResponse response = fileService.getUploadPresignedUrl(presignedUrlRequest.getPrefix(), presignedUrlRequest.getFileName());
        return ApplicationResponse.ok(response);
    }

}
