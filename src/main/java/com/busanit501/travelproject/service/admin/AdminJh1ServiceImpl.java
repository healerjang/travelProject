package com.busanit501.travelproject.service.admin;

import com.busanit501.travelproject.domain.FreeBoard;
import com.busanit501.travelproject.domain.Location;
import com.busanit501.travelproject.domain.Member;
import com.busanit501.travelproject.domain.Product;
import com.busanit501.travelproject.dto.*;
import com.busanit501.travelproject.dto.freeboard.FreeBoardJh1DTO;
import com.busanit501.travelproject.dto.member.MemberDTO;
import com.busanit501.travelproject.dto.member.MemberFullDTO;
import com.busanit501.travelproject.dto.util.PageRequestJh1DTO;
import com.busanit501.travelproject.dto.util.PageResponseJh1DTO;
import com.busanit501.travelproject.repository.LocationJh1Repository;
import com.busanit501.travelproject.repository.ProductJh1Repository;
import com.busanit501.travelproject.repository.freeboard.FreeBoardRepository;
import com.busanit501.travelproject.repository.freeboard.ReplyRepository;
import com.busanit501.travelproject.repository.member.MemberJh1Repository;
import com.busanit501.travelproject.repository.member.MemberRepository;
import com.busanit501.travelproject.service.CustomMapperJh1;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class AdminJh1ServiceImpl implements AdminJh1Service {

  private static final Logger log = LogManager.getLogger(AdminJh1ServiceImpl.class);
  private final CustomMapperJh1 customMapper;

  private final LocationJh1Repository locationRepo;
  private final ProductJh1Repository productRepo;
  private final MemberRepository memberRepo;
  private final MemberJh1Repository memberSearchRepo;
  private final FreeBoardRepository freeBoardRepo;
  private final ReplyRepository replyRepo;

  @Override
  public List<LocationValueJh1DTO> getLocationsOnly() {
    List<Location> locations = locationRepo.findAll();
    return locations.stream().map(customMapper::locationToDTO).toList();
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
  public void updateProduct(ProductUpdateJh1DTO dto) {
    Product product = productRepo.findById(dto.getProductNo()).orElseThrow();
    product.updateProductSafe(dto.getName(), dto.getDescription(), dto.getCapacity(), dto.getImagePath());
    productRepo.save(product);
  }


  @Deprecated
  @Override
  public ProductJh1DTO getProductTmp(Long id) {
    Product product = productRepo.findById(id).orElseThrow();
    return customMapper.productToCompactDTO(product);
  }

  @Override
  public ProductJh1DTO getProductCompact(Long id) {
    Product product = productRepo.findById(id).orElseThrow();
    return customMapper.productToCompactDTO(product);
  }


  @Override
  @Transactional
  public ProductJh1DTO getProductFull(Long id) {
    Product product = productRepo.findById(id).orElseThrow();
    return customMapper.productToFullDTO(product);
  }

  @Override
  public PageResponseJh1DTO<ProductJh1DTO> listProducts(PageRequestJh1DTO requestDTO) {
    Page<Product> products = productRepo.searchProducts(requestDTO.getPageable("startDate"), requestDTO.getSearchFor(), requestDTO.getSearch());
    return PageResponseJh1DTO.<ProductJh1DTO>builder()
      .dtoList(products.stream().map(customMapper::productToCompactDTO).toList())
      .total((int) products.getTotalElements())
      .pageRequestDTO(requestDTO)
      .build();
  }

  @Override
  @Transactional
  public PageResponseJh1DTO<MemberDTO> listMembers(PageRequestJh1DTO requestDTO) {
    Pageable pageable = requestDTO.getPageable();
    String search = requestDTO.getSearch();
    Page<Member> members;
    if (search != null) {
      if (requestDTO.isSearchingFor("id")) members = memberSearchRepo.idOf(pageable, search);
      else if (requestDTO.isSearchingFor("name")) members = memberSearchRepo.nameOf(pageable, search);
      else if (requestDTO.isSearchingFor("email")) members = memberSearchRepo.emailOf(pageable, search);
      else if (requestDTO.isSearchingFor("phone")) members = memberSearchRepo.phoneOf(pageable, search);
      else members = memberSearchRepo.findAll(pageable);
    } else members = memberSearchRepo.findAll(pageable);
    return PageResponseJh1DTO.<MemberDTO>builder()
      .dtoList(members.stream().map(customMapper::memberToDTO).toList())
      .total((int) members.getTotalElements())
      .pageRequestDTO(requestDTO)
      .build();
  }

  @Override
  public MemberFullDTO getMemberFullSupport(long memberNo) {
    Member member = memberRepo.findByMemberNo(memberNo);
    return customMapper.memberToFullDTO(member);
  }

  @Override
  public void givePointTo(long memberNo, int amount) {
    Member member = memberRepo.findByMemberNo(memberNo);
    member.addPoint(amount);
    memberRepo.save(member);
  }

  @Transactional
  @Override
  public PageResponseJh1DTO<FreeBoardJh1DTO> getFreeBoardList(PageRequestJh1DTO requestDTO) {
    Page<FreeBoard> boards = freeBoardRepo.findAll(PageRequest.of(requestDTO.getPage() - 1, requestDTO.getSize()));
    return PageResponseJh1DTO.<FreeBoardJh1DTO>builder()
      .dtoList(boards.stream().map(customMapper::boardToCompactDTO).toList())
      .total((int) boards.getTotalElements())
      .pageRequestDTO(requestDTO)
      .build();
  }

  @Transactional
  @Override
  public FreeBoardJh1DTO getFreeBoard(Long freeBoardNo) {
    FreeBoard freeBoard = freeBoardRepo.findById(freeBoardNo).orElseThrow();
    return customMapper.boardToFullDTO(freeBoard);
  }

  @Transactional
  @Override
  public void deleteFreeBoard(Long freeBoardNo) {
    freeBoardRepo.deleteById(freeBoardNo);
  }

  @Override
  public void deleteReply(Long replyNo) {
    replyRepo.deleteById(replyNo);
  }

  @Override
  public List<ProductImageAdminDTO> getProductImages() {
    List<Tuple> products = productRepo.getProductsWithImages();
    return products.stream().map(
      tuple -> ProductImageAdminDTO.builder()
        .productNo(tuple.get("productNo", Long.class))
        .productName(tuple.get("productName", String.class))
        .imagePath(tuple.get("imagePath", String.class))
        .build()
    ).toList();
  }

}
