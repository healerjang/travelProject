package com.busanit501.travelproject.controller.admin;

import com.busanit501.travelproject.annotation.member.Member;
import com.busanit501.travelproject.dto.UploadProductImageFileDTO;
import com.busanit501.travelproject.dto.member.MemberDTO;
import com.busanit501.travelproject.exception.member.UnauthorizedRestException;
import com.busanit501.travelproject.service.member.ResponseLogin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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

  @Value("${com.busanit501.travelproject.thumbnail.path}")
  private String thumbnailPath;

  @SneakyThrows
  private void throwIfUnauthorized(MemberDTO memberDTO) {
    if (memberDTO == null)
      throw new UnauthorizedRestException("access denied");
    boolean admin = memberDTO.getResponseLogin() == ResponseLogin.ADMIN;
    if (!admin)
      throw new UnauthorizedRestException("access denied");
  }

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
    Path thumbPath = Paths.get(thumbnailPath, country);
    if (!Files.exists(path)) Files.createDirectory(path);
    if (!Files.exists(thumbPath)) Files.createDirectory(thumbPath);
  }

  private String handleTargetingFileUpload(MultipartFile file, String country, String city) {
    String originalFilename = file.getOriginalFilename();
    String saveStr = city + getFileExtension(originalFilename);
    Path savePath = Paths.get(uploadPath, country, saveStr);
    Path thumbSavePath = Paths.get(thumbnailPath, country, saveStr);
    try {
      ensureDir(country);
      file.transferTo(savePath);
      File thumbFile = thumbSavePath.toFile();
      boolean isImage = Files.probeContentType(thumbFile.toPath()).startsWith("image/");
      if (isImage) Thumbnailator.createThumbnail(savePath.toFile(), thumbFile, 300, 300);
      return country + "/" + saveStr;
    } catch (IOException ioe) {
      log.error(ioe);
      throw new RuntimeException(ioe);
    }
  }

  @Member
  @PostMapping(value = "/api/admin/productImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<Map<String, Object>> uploadProductImage(
    HttpServletRequest request,
    MemberDTO memberDTO,
    @Valid UploadProductImageFileDTO uploadDTO,
    BindingResult br
  ) {
    throwIfUnauthorized(memberDTO);
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
