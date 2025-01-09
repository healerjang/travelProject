package com.busanit501.travelproject.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

/**
 * 업로드 폴더에 있는 이미지를 모두 가져오는 컴포넌트
 * @author 원종호
 */
@Log4j2
@Component
public class UploadedImagesComponent {

  @Value("${com.busanit501.travelproject.upload.path}")
  private String uploadPath;

  public List<String> getList() {
    Path startPath = Paths.get(uploadPath);
    try (Stream<Path> walking = Files.walk(startPath)) {
      return walking.filter(path -> {
        try {
          String mime = Files.probeContentType(path);
          return mime != null && mime.startsWith("image/");
        } catch (IOException e) {
          return false;
        }
      }).map(
        path -> startPath.relativize(path).toString().replace("\\", "/")
      ).toList();
    } catch (IOException e) {
      log.error(e);
      return List.of();
    }
  }
}
