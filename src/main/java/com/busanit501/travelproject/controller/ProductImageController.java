package com.busanit501.travelproject.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * <p>
 *   고객 및 관리자가 모두 사용할 수 있는 이미지 컨트롤러
 * </p>
 * <p>
 *   조회하는 기능만 포함되어 있음
 * </p>
 *
 * @author 원종호
 */
@Log4j2
@RestController
public class ProductImageController {

  @Value("${com.busanit501.travelproject.upload.path}")
  private String uploadPath;

  @Value("${com.busanit501.travelproject.thumbnail.path}")
  private String thumbnailPath;


  private String getImagePath(HttpServletRequest request, String basePath) {
    String requestURI = request.getRequestURI();
    return URLDecoder.decode(requestURI.replaceFirst(basePath, ""), StandardCharsets.UTF_8);
  }

  private ResponseEntity<Resource> response(String dir, String imagePath) throws IOException {
    Resource resource = new FileSystemResource(dir + File.separator + String.join(File.separator, imagePath.split("/")));
    HttpHeaders headers = new HttpHeaders();
    String mimeType = Files.probeContentType(resource.getFile().toPath());
    headers.add("Content-Type", mimeType);
    return ResponseEntity.ok().headers(headers).body(resource);
  }

  @GetMapping("/productImage/**")
  public ResponseEntity<Resource> getProductImage(
      HttpServletRequest request
  ) {
    try {
      String imagePath = getImagePath(request, "/productImage/");
      return response(uploadPath, imagePath);
    } catch (IOException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/thumbnail/**")
  public ResponseEntity<Resource> getProductThumbnailImage(
    HttpServletRequest request
  ) {
    try {
      String imagePath = getImagePath(request, "/thumbnail/");
      return response(thumbnailPath, imagePath);
    } catch (IOException e) {
      return ResponseEntity.notFound().build();
    }
  }
}
