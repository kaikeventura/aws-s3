package com.kaikeventura.awss3.controller;

import com.kaikeventura.awss3.service.S3Service;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/v1/s3")
@AllArgsConstructor
public class S3Controller {

    private final S3Service s3Service;

    @PostMapping("upload/{name}")
    public ResponseEntity<Void> upload(
            @PathVariable("name") final String fileName,
            @RequestParam("file") MultipartFile multipartFile
    ) throws IOException {
        this.s3Service.uploadFile(fileName, multipartFile);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("download/{name}")
    public ResponseEntity<InputStreamResource> download(@PathVariable("name") final String fileName) throws FileNotFoundException {
        var file = this.s3Service.download(fileName);

        var resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }
}
