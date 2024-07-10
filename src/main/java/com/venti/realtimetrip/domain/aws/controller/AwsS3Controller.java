package com.venti.realtimetrip.domain.aws.controller;

import com.venti.realtimetrip.domain.aws.service.AwsS3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/aws/s3")
public class AwsS3Controller {

    private final AwsS3Service awsS3Service;

    /**
     * 이미지 업로드 API
     * [POST] /aws/s3/upload-image
     * @return
     */
    @PostMapping("/upload-image")
    public String uploadImage(@RequestPart("image") MultipartFile imageFile) throws IOException {

        String uploadedImageUrl = awsS3Service.uploadImage(imageFile);

        return "업로드된 이미지의 URL은 " + uploadedImageUrl + " 입니다.";
    }

}
