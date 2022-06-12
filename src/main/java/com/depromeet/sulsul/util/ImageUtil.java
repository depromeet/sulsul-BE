package com.depromeet.sulsul.util;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.depromeet.sulsul.common.entity.ImageExt;
import com.mortennobel.imagescaling.AdvancedResizeOp;
import com.mortennobel.imagescaling.MultiStepRescaleOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;
import javax.imageio.ImageIO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageUtil {

  public static final String SLASH = "/";
  public static final int STANDARD_SIZE = 500;

  public static BufferedImage resize(MultipartFile multipartFile) throws IOException {

    BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());

    MultiStepRescaleOp rescale = getRescale(bufferedImage.getWidth(), bufferedImage.getHeight());

    rescale.setUnsharpenMask(AdvancedResizeOp.UnsharpenMask.Soft);

    return rescale.filter(bufferedImage, null);
  }

  private static MultiStepRescaleOp getRescale(int width, int height) {
    if (width > height) {
      height *= (float) STANDARD_SIZE / width;
      return new MultiStepRescaleOp(STANDARD_SIZE, height);
    }

    width *= (float) STANDARD_SIZE / height;
    return new MultiStepRescaleOp(width, STANDARD_SIZE);
  }

  public static ObjectMetadata getObjectMetadata(ByteArrayOutputStream outputStream, String ext) {
    ObjectMetadata objectMetadata = new ObjectMetadata();
    objectMetadata.setContentLength(outputStream.size());
    objectMetadata.setContentType(ext);

    return objectMetadata;
  }

  public static boolean isValidExtension(String fileName) {
    if (EnumUtils.isValidEnum(ImageExt.class, extractExt(fileName).toUpperCase())) {
      return true;
    }
    return false;
  }

  public static String getNameByUUID(String orginName) {
    return UUID.randomUUID().toString() + "." + extractExt(orginName);
  }

  public static String extractExt(String orginName) {
    return orginName.substring(orginName.lastIndexOf(".") + 1);
  }

  public static String append(String... paths) {
    return String.join("/", paths);
  }
}
