package com.kaikeventura.awss3.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

@Service
@AllArgsConstructor
public class S3Service {

    private final AmazonS3 amazonS3;

    private static final String BUCKET_NAME = "mybucket";

    public void uploadFile(final String fileName, final MultipartFile multipartFile) throws IOException {
        var file = this.buildFile(multipartFile);
        this.amazonS3.putObject(new PutObjectRequest(BUCKET_NAME, fileName, file));
    }

    public File download(final String fileName) {
        File file = new File(fileName);
        try {
            IOUtils.copy(this.amazonS3.getObject(BUCKET_NAME, fileName).getObjectContent(), new FileOutputStream(file));
        } catch (IOException e) {
            throw new RuntimeException();
        }

        return file;
    }

    private File buildFile(final MultipartFile multipartFile) throws IOException {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));

        try (OutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        }

        return file;
    }
}
