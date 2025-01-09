//package com.busanit501.travelproject.service.productCgw;
//
//import com.busanit501.travelproject.domain.Location;
//import com.busanit501.travelproject.domain.Product;
//import com.busanit501.travelproject.repository.LocationJh1Repository;
//import com.busanit501.travelproject.repository.ProductCgw.ProductCgwRepository;
//import lombok.extern.log4j.Log4j2;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Commit;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Arrays;
//
//@SpringBootTest
//@Log4j2
//@Transactional
//@Commit
//public class ProductCgwRepositoryTest {
//    @Autowired
//    private ProductCgwRepository productCgwRepository;
//    @Autowired
//    private LocationJh1Repository locationJh1Repository;
//
//    private final ArrayList<String> productNames = new ArrayList<>(Arrays.asList("베이징", "광저우", "상하이", "시안",
//                "베를린", "런던", "파리", "로마", "빈",
//                "후쿠오카", "쿄토", "오사카", "삿포로", "도쿄",
//                "부산", "경주", "제주", "서울", "수원",
//            "LA","뉴욕","샌프란시스코","워싱턴D.C"
//    ));
//    private final ArrayList<String> productPaths = new ArrayList<>(Arrays.asList(
//            "/China/Beijing.jpg",    // 베이징
//            "/China/Guangzhou.jpeg",  // 광저우
//            "/China/Shanghai.jpg",   // 상하이
//            "/China/XiAn.jpg",       // 시안
//            "/Europe/Berlin.jpg",   // 베를린
//            "/Europe/London.jpg",        // 런던
//            "/Europe/Paris.jpg",     // 파리
//            "/Europe/Rome.jpg",       // 로마
//            "/Europe/Vienna.jpg",   // 빈
//            "/Japan/Fukuoka.jpg",    // 후쿠오카
//            "/Japan/Kyoto.jpg",      // 쿄토
//            "/Japan/Osaka.jpg",      // 오사카
//            "/Japan/Sapporo.jpg",    // 삿포로
//            "/Japan/Tokyo.jpg",      // 도쿄
//            "/Korea/Busan.jpg",      // 부산
//            "/Korea/Gyeongju.jpg",   // 경주
//            "/Korea/Jeju.jpg",       // 제주
//            "/Korea/Seoul.jpg",      // 서울
//            "/Korea/Suwon.jpg",
//            "/USA/LosAngeles.jpeg",
//            "/USA/Newyork.jpeg",
//            "/USA/SanFrancisco.jpeg",
//            "/USA/WashingtonD.C.jpg"
//            ));    // 수원
//
//    private final ArrayList<String> productLocations = new ArrayList<>(Arrays.asList(
//                "중국", "유럽", "일본", "한국","미국"
//        ));
//    private final ArrayList<Integer> locationNextCount = new ArrayList<>(Arrays.asList(3, 8, 13, 18, 22));
//
//    @Test
//    public void setProduct() {
//        int countNum = 0;
//        for (int i = 0; i < productNames.size(); i ++) {
//            Location location = Location.builder()
//                    .country(productLocations.get(countNum))
//                    .city(productNames.get(i))
//                    .build();
//
//            locationJh1Repository.save(location);
//
//            productCgwRepository.save(Product.builder()
//                            .name(productNames.get(i))
//                            .description(productNames.get(i))
//                            .price(0L)
//                            .location(location)
//                            .startDate(LocalDate.now())
//                            .endDate(LocalDate.now().plusDays(1))
//                            .capacity(5)
//                            .imagePath(productPaths.get(i))
//                    .build());
//
//            if (i == locationNextCount.get(countNum)) {countNum ++;}
//        }
//    }
//
////    @Test
////    public void searchProductTest() {
////        Location location = locationJh1Repository.findById(1L).orElseThrow();
////        Page<Product> products = productCgwRepository.searchProduct(null, null, null, PageRequest.of(0, 10));
////        log.info("ProductCgwRepositoryTest searchProductTest products getElements, {}", products.getContent());
////    }
//}
