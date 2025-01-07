package com.busanit501.travelproject.controller.admin;

import com.busanit501.travelproject.dto.UploadProductImageFileDTO;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@Log4j2
@RestController
public class AdminProductImageController {

  @Value("${com.busanit501.travelproject.upload.path}")
  private String uploadPath;

  private String getFileExtension(String originalFilename) {
    if (originalFilename == null || originalFilename.isEmpty()) {
      return ""; // No extension if filename is null or empty
    }

    int dotIndex = originalFilename.lastIndexOf('.');
    if (dotIndex > 0 && dotIndex < originalFilename.length() - 1) {
      return originalFilename.substring(dotIndex);
    }

    return ""; // No extension if no '.' or ends with '.'
  }

  private void ensureDir(String country) throws IOException {
    Path path = Paths.get(uploadPath, country);
    if (!Files.exists(path)) Files.createDirectory(path);
  }

  private String handleTargetingFileUpload(MultipartFile file, String country, String city) {
    String originalFilename = file.getOriginalFilename();

    String saveStr = city + getFileExtension(originalFilename);
    Path savePath = Paths.get(uploadPath, country, saveStr);

    try {
      ensureDir(country);
      file.transferTo(savePath);
      return country + "/" + saveStr;
    } catch (IOException ioe) {
      log.error(ioe);
      throw new RuntimeException(ioe);
    }
  }


  @PostMapping(value = "/api/admin/productImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<Map<String, Object>> uploadProductImage(
    @Valid UploadProductImageFileDTO uploadDTO,
    BindingResult br
  ) {
    if (br.hasErrors()) {
      return ResponseEntity.badRequest().body(
        Map.of("success", false, "message", "Invalid Request", "model", br.getModel())
      );
    }
    if (uploadDTO.getFile() != null) {
      String imagePath = handleTargetingFileUpload(uploadDTO.getFile(), uploadDTO.getCountryDst(), uploadDTO.getCityDst());
      return ResponseEntity.ok().body(Map.of("success", true, "imagePath", imagePath));
    } else {
      return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Invalid Request"));
    }
  }



}
