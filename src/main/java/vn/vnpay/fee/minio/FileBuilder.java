package vn.vnpay.fee.minio;

import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Component
public class FileBuilder {

    @Value(value = "${spring.minio.url}")
    private String url;
    @Value(value = "${spring.minio.access-key}")
    private String accessKey;
    @Value(value = "${spring.minio.secret-key}")
    private String secretKey;
    @Value(value = "${spring.minio.time-share-file}")
    private int timeShareFile;

    public String uploadFile(String bucket, String filename, InputStream inputStream) {
        try {
            MinioClient minioClient = new MinioClient(url, accessKey, secretKey);
            log.info("Begin upload: {}, to bucket: {}", filename, bucket);
            makeBucket(minioClient, bucket);
            PutObjectArgs putObjectArgs = new PutObjectArgs.Builder()
                    .contentType("application/octet-stream")
                    .bucket(bucket)
                    .object(filename)
                    .stream(inputStream, -1, 10485760)
                    .build();
            minioClient.putObject(putObjectArgs);
            String url = downloadFile(minioClient, bucket, filename);
            log.info("Upload Success with url public: {}", url);
            return url;
        } catch (Exception e) {
            log.error("Push file to server have EX.", e);
            return null;
        }
    }

    public String downloadFile(String bucket, String filename) {
        return downloadFile(new MinioClient(url, accessKey, secretKey), bucket, filename);
    }

    public String downloadFile(MinioClient minioClient, String bucket, String filename) {
        try {
            log.info("Begin download: {}, in bucket: {}", filename, bucket);
            String url = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucket)
                            .object(filename)
                            .expiry(timeShareFile)
                            .build());
            log.info("create session success with url public: {}", url);
            return url;
        } catch (Exception e) {
            log.error("Get file to server have EX.", e);
            return null;
        }
    }

    private void makeBucket(MinioClient minioClient, String bucket) throws IOException,
            InvalidKeyException,
            NoSuchAlgorithmException,
            InsufficientDataException,
            InternalException,
            InvalidBucketNameException,
            ErrorResponseException,
            RegionConflictException,
            XmlParserException,
            ServerException,
            InvalidResponseException {
        if (minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build()))
            return;
        minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
    }
}
