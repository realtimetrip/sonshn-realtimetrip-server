package com.venti.realtimetrip.domain.aws.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class AwsS3Service {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    /**
     * 이미지 업로드
     */
    public String uploadImage(MultipartFile imageFile) throws IOException {

        String originalFileName = imageFile.getOriginalFilename();
        String fileName = createFileName(originalFileName);
        String filePath = "img/" + fileName;

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(imageFile.getSize());
        metadata.setContentType(imageFile.getContentType());

        // TODO: S3에 저장될 이미지 파일의 저장 구조와 URL 분리
        amazonS3Client.putObject(bucket, filePath, imageFile.getInputStream(), metadata);

        return amazonS3Client.getUrl(bucket, filePath).toString();
    }

    /**
     * 이미지 삭제
     */
    public void deleteImage(String filename)  {
        amazonS3Client.deleteObject(bucket, filename);
    }

    /**
     * S3 버킷에 업로드할 파일의 새로운 이름 생성
     */
    private String createFileName(String fileName) {
        return UUID.randomUUID().toString().concat(extractFileExtension(fileName));
    }

    /**
     * 업로드할 파일의 확장자 분리
     */
    private String extractFileExtension(String originalFileName) {
        try {
            return originalFileName.substring(originalFileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일입니다.");
        }
    }

}
