package com.busanit501.travelproject.service.admin;

import com.busanit501.travelproject.domain.Location;
import com.busanit501.travelproject.domain.Member;
import com.busanit501.travelproject.domain.Product;
import com.busanit501.travelproject.dto.LocationValueJh1DTO;
import com.busanit501.travelproject.dto.ProductJh1DTO;
import com.busanit501.travelproject.dto.member.MemberDTO;
import com.busanit501.travelproject.dto.member.MemberFullDTO;
import com.busanit501.travelproject.dto.util.reservationPageDTO.HcbPageRequestDTO;
import com.busanit501.travelproject.dto.util.reservationPageDTO.HcbPageResponseDTO;
import com.busanit501.travelproject.repository.LocationJh1Repository;
import com.busanit501.travelproject.repository.ProductJh1Repository;
import com.busanit501.travelproject.repository.member.MemberRepository;
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
public class AdminJh1ServiceImpl implements AdminJh1Service {

  private final LocationJh1Repository locationRepo;
  private final ProductJh1Repository productRepo;
  private final MemberRepository memberRepo;

  @Override
  public List<LocationValueJh1DTO> getLocationsOnly() {
    List<Location> locations = locationRepo.findAll();
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
    Location result = locationRepo.save(loc);
    return result.getLocationNo();
  }

  @Transactional
  @Override
  public Long registerProduct(ProductJh1DTO dto) {
    Location location = locationRepo.findById(dto.getLocationNo()).orElseThrow();
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
    Product result = productRepo.save(product);
    return result.getProductNo();
  }

  @Override
  public ProductJh1DTO getProductTmp(Long id) {
    Product product = productRepo.findById(id).orElseThrow();
    return productEntityToDTO(product);
  }

  @Override
  public HcbPageResponseDTO<ProductJh1DTO> listProducts(HcbPageRequestDTO requestDTO) {
    Page<Product> products = productRepo.findAll(PageRequest.of(requestDTO.getPage() - 1, requestDTO.getSize(), Sort.by("startDate")));
    return HcbPageResponseDTO.<ProductJh1DTO>builder()
      .dtoList(products.stream().map(this::productEntityToDTO).toList())
      .total((int) products.getTotalElements())
      .hcbPageRequestDTO(HcbPageRequestDTO.builder().page(requestDTO.getPage()).size(requestDTO.getSize()).pageSize(requestDTO.getPageSize()).build())
      .build();
  }


  @Override
  public HcbPageResponseDTO<MemberDTO> listMembers(HcbPageRequestDTO requestDTO) {
    Page<Member> members = memberRepo.findAll(PageRequest.of(requestDTO.getPage() - 1, requestDTO.getSize()));
    return HcbPageResponseDTO.<MemberDTO>builder()
      .dtoList(members.stream().map(this::memberEntityToDTO).toList())
      .total((int) members.getTotalElements())
      .hcbPageRequestDTO(HcbPageRequestDTO.builder().page(requestDTO.getPage()).size(requestDTO.getSize()).pageSize(requestDTO.getPageSize()).build())
      .build();
  }

  @Override
  public MemberFullDTO getMemberFullSupport(long memberNo) {
    Member member = memberRepo.findByMemberNo(memberNo);
    return memberEntityToFullDTO(member);
  }

}
