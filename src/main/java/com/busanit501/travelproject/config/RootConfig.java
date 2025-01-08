package com.busanit501.travelproject.config;

import com.busanit501.travelproject.domain.Product;
import com.busanit501.travelproject.domain.Reply;
import com.busanit501.travelproject.dto.freeboard.ReplyDTO;
import com.busanit501.travelproject.dto.productCgw.ProductDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RootConfig {

    @Bean
    public ModelMapper getMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel
                        (org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.addMappings(new PropertyMap<Product, ProductDTO>() {
            @Override
            protected void configure() {
                map().setLocationNo(source.getLocation().getLocationNo());
            }
        });
        modelMapper.addMappings(new PropertyMap<Reply, ReplyDTO>() {

            @Override
            protected void configure() {
                map().setReplier(source.getMember().getMemberName());
                map().setFreeBoardNo(source.getFreeBoard().getFreeBoardNo());
            }
        });
        modelMapper.addMappings(new PropertyMap<ReplyDTO, Reply>() {

            @Override
            protected void configure() {
                map().setContent(source.getReplyText());
            }
        });
        return modelMapper;
    }
}
