package com.example.catalogservice.service;

import com.example.catalogservice.dto.CatalogDto;
import com.example.catalogservice.jpa.CatalogEntity;

public interface CatalogService {
    Iterable<CatalogEntity> getAllCatalogs();
    CatalogDto createCatalog(CatalogEntity catalogEntity);
}
