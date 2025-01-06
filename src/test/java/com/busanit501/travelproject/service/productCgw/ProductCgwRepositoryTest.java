package com.busanit501.travelproject.service.productCgw;

import com.busanit501.travelproject.domain.Location;
import com.busanit501.travelproject.domain.Product;
import com.busanit501.travelproject.repository.LocationJh1Repository;
import com.busanit501.travelproject.repository.ProductCgw.ProductCgwRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest
@Log4j2
@Transactional
@Commit
public class ProductCgwRepositoryTest {
    @Autowired
    private ProductCgwRepository productCgwRepository;
    @Autowired
    private LocationJh1Repository locationJh1Repository;

    private final ArrayList<String> productNames = new ArrayList<>(Arrays.asList("베이징", "광저우", "상하이", "시안",
                "베를린", "런던", "파리", "로마", "빈",
                "후쿠오카", "쿄토", "오사카", "삿포로", "도쿄",
                "부산", "경주", "제주", "서울", "수원"));
    private final ArrayList<String> productPaths = new ArrayList<>(Arrays.asList(
                "/City/China/Beijing.jpg",    // 베이징
                "/City/China/Guangzhou.jpeg",  // 광저우
                "/City/China/Shanghai.webp",   // 상하이
                "/City/China/XiAn.jpg",       // 시안
                "/City/Europe/Berlin.jpg",   // 베를린
                "/City/Europe/London.jpg",        // 런던
                "/City/Europe/Paris.jpg",     // 파리
                "/City/Europe/Rome.jpg",       // 로마
                "/City/Europe/Vienna.jpg",   // 빈
                "/City/Japan/Fukuoka.jpg",    // 후쿠오카
                "/City/Japan/Kyoto.jpg",      // 쿄토
                "/City/Japan/Osaka.jpg",      // 오사카
                "/City/Japan/Sapporo.jpg",    // 삿포로
                "/City/Japan/Tokyo.jpg",      // 도쿄
                "/City/Korea/Busan.jpg",      // 부산
                "/City/Korea/Gyeongju.jpg",   // 경주
                "/City/Korea/Jeju.jpg",       // 제주
                "/City/Korea/Seoul.jpg",      // 서울
                "/City/Korea/Suwon.jpg"));    // 수원
    private final ArrayList<String> productLocations = new ArrayList<>(Arrays.asList(
                "중국", "유럽", "일본", "한국"
        ));
    private final ArrayList<Integer> locationNextCount = new ArrayList<>(Arrays.asList(3, 8, 13, 18));

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

//    @Test
//    public void searchProductTest() {
//        Location location = locationJh1Repository.findById(1L).orElseThrow();
//        Page<Product> products = productCgwRepository.searchProduct(null, null, null, PageRequest.of(0, 10));
//        log.info("ProductCgwRepositoryTest searchProductTest products getElements, {}", products.getContent());
//    }
}
