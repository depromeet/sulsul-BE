package com.depromeet.sulsul.common.external;

import static com.depromeet.sulsul.util.ImageUtil.append;
import static com.depromeet.sulsul.util.ImageUtil.extractExt;
import static com.depromeet.sulsul.util.ImageUtil.getNameByUUID;
import static com.depromeet.sulsul.util.ImageUtil.isValidExtension;
import static com.depromeet.sulsul.util.ImageUtil.resize;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.depromeet.sulsul.common.entity.ImageType;
import com.depromeet.sulsul.common.error.exception.custom.MediaFileProcessException;
import com.depromeet.sulsul.util.ImageUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Slf4j
@RequiredArgsConstructor
public class AwsS3ImageClient {

  private static final String BUCKET_URL = "https://sulsul-media-bucket.s3.ap-northeast-2.amazonaws.com";

  @Value("${cloud.aws.s3.bucket}")
  private String bucket;

  private final AmazonS3Client amazonS3Client;

  public String upload(MultipartFile multipartFile, ImageType imageType) {

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    String ext = extractExt(multipartFile.getOriginalFilename());
    String fileName = multipartFile.getOriginalFilename();
    String bucketObjectName = getNameByUUID(fileName);

    isValidExtension(ext);

    writeImage(multipartFile, ext, outputStream);

    InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

    putObject(imageType, bucketObjectName, inputStream, outputStream, ext);

    return append(BUCKET_URL, imageType.name(), bucketObjectName);
  }

  private void putObject(ImageType imageType, String bucketObjectName, InputStream inputStream,
      ByteArrayOutputStream outputStream, String ext) {

    try {
      amazonS3Client.putObject(new PutObjectRequest(makePathBy(imageType),
          bucketObjectName,
          inputStream,
          ImageUtil.getObjectMetadata(outputStream, ext)));
    } catch (AmazonS3Exception e) {
      log.error("[ERROR] Fail to upload to S3: {}", e.getMessage());
      e.printStackTrace();
    }

  }

  private void writeImage(MultipartFile multipartFile, String ext,
      ByteArrayOutputStream outputStream) {
    try {
      ImageIO.write(resize(multipartFile), ext, outputStream);
    } catch (IOException e) {
      log.error(e.getMessage(), e);
      throw new MediaFileProcessException();
    }
  }

  public String makePathBy(ImageType imageType) {
    return bucket + ImageUtil.SLASH + imageType.name();
  }
}
