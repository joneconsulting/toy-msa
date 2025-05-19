package com.example.catalogservice.service;

import com.example.catalogservice.dto.CatalogDto;
import com.example.catalogservice.jpa.CatalogEntity;
import com.example.catalogservice.jpa.CatalogRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Data
@Slf4j
@Service
public class CatalogServiceImpl implements CatalogService{
    CatalogRepository catalogRepository;

    @Autowired
    public CatalogServiceImpl(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @Override
    public Iterable<CatalogEntity> getAllCatalogs() {
        return catalogRepository.findAll();
    }

    @Override
    public CatalogDto createCatalog(CatalogEntity catalogEntity) {
        catalogEntity.setProductId(UUID.randomUUID().toString());

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        catalogRepository.save(catalogEntity);

        CatalogDto returnValue = mapper.map(catalogEntity, CatalogDto.class);

        return returnValue;
    }
}
