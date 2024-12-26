package com.busanit501.travelproject.service.manager;

import com.busanit501.travelproject.domain.Location;
import com.busanit501.travelproject.domain.Product;
import com.busanit501.travelproject.dto.LocationValueJh1DTO;
import com.busanit501.travelproject.dto.ProductJh1DTO;
import com.busanit501.travelproject.dto.util.reservationPageDTO.HcbPageRequestDTO;
import com.busanit501.travelproject.dto.util.reservationPageDTO.HcbPageResponseDTO;
import com.busanit501.travelproject.repository.LocationJh1Repository;
import com.busanit501.travelproject.repository.ProductJh1Repository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ManagerJh1ServiceImpl implements ManagerJh1Service {

  private final LocationJh1Repository lrepo;
  private final ProductJh1Repository prepo;

  @Override
  public List<LocationValueJh1DTO> getLocationsOnly() {
    List<Location> locations = lrepo.findAll();
    List<LocationValueJh1DTO> dtoList = locations.stream().map(loc -> {
      LocationValueJh1DTO dto = LocationValueJh1DTO.builder()
        .locationNo(loc.getLocationNo())
        .country(loc.getCountry())
        .city(loc.getCity())
        .build();

      return dto;
    }).toList();
    return dtoList;
  }

  @Override
  public Long registerLocation(LocationValueJh1DTO dto) {
    Location loc = Location.builder().country(dto.getCountry()).city(dto.getCity()).build();
    Location result = lrepo.save(loc);
    return result.getLocationNo();
  }

  @Transactional
  @Override
  public Long registerProduct(ProductJh1DTO dto) {
    Location location = lrepo.findById(dto.getLocationNo()).orElseThrow();
    Product product = Product.builder()
      .name(dto.getName())
      .price(dto.getPrice())
      .location(location)
      .startDate(dto.getStartDate())
      .endDate(dto.getEndDate())
      .capacity(dto.getCapacity())
      .description(dto.getDescription())
      .imagePath(dto.getImagePath())
      .reservations(Set.of())
      .reviews(Set.of())
      .build();
    Product result = prepo.save(product);
    return result.getProductNo();
  }

  @Override
  public ProductJh1DTO getProductTmp(Long id) {
    Product product = prepo.findById(id).orElseThrow();
    return productEntityToDTO(product);
  }

  @Override
  public HcbPageResponseDTO<ProductJh1DTO> listProducts(HcbPageRequestDTO requestDTO) {
    Page<Product> products = prepo.findAll(PageRequest.of(requestDTO.getPage() - 1, requestDTO.getSize(), Sort.by("startDate")));
    return HcbPageResponseDTO.<ProductJh1DTO>builder()
      .dtoList(products.stream().map(this::productEntityToDTO).toList())
      .total((int) products.getTotalElements())
      .hcbPageRequestDTO(HcbPageRequestDTO.builder().page(requestDTO.getPage()).size(requestDTO.getSize()).pageSize(requestDTO.getPageSize()).build())
      .build();
  }

}
