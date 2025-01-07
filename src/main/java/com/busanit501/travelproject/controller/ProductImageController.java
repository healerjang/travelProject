package com.busanit501.travelproject.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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

  @GetMapping(value = "/productImage/{countryDir}/{cityNameWithExt}")
  public ResponseEntity<Resource> getProductImage(
    @PathVariable String countryDir,
    @PathVariable String cityNameWithExt
  ) {
    try {
      Resource resource = new FileSystemResource(uploadPath + File.separator + countryDir + File.separator + cityNameWithExt);
      HttpHeaders headers = new HttpHeaders();
      String mimeType = Files.probeContentType(resource.getFile().toPath());
      headers.add("Content-Type", mimeType);
      return ResponseEntity.ok().headers(headers).body(resource);
    } catch (FileNotFoundException fnoex) {
      return ResponseEntity.notFound().build();
    } catch (IOException e) {
      return ResponseEntity.notFound().build();
    }
  }
}
