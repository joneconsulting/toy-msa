package com.example.catalogservice.service;

import com.example.catalogservice.jpa.CatalogEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
class CatalogServiceImplTest {
    @Autowired
    private CatalogService catalogService;

    @Test
    void getAllCatalogs() {
        Iterable<CatalogEntity> catalogEntities = catalogService.getAllCatalogs();

        assertNotNull(catalogEntities);

        catalogEntities.forEach(v -> {
            assertTrue(v.getProductId().startsWith("CATALOG-"));
        });
    }
}