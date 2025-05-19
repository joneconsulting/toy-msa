package com.example.catalogservice.controller;

import com.example.catalogservice.dto.CatalogDto;
import com.example.catalogservice.jpa.CatalogEntity;
import com.example.catalogservice.service.CatalogService;
import com.example.catalogservice.vo.ResponseCatalog;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/catalog-service")
@Slf4j
public class CatalogController {
    Environment env;
    CatalogService catalogService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    public CatalogController(Environment env, CatalogService catalogService) {
        this.env = env;
        this.catalogService = catalogService;
    }

    @PostMapping("/catalogs")
    public ResponseEntity<String> createCatalog(@RequestBody ResponseCatalog catalogDetails) {
        log.info("Before add catalog data");
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        CatalogEntity catalogEntity = mapper.map(catalogDetails, CatalogEntity.class);
        /* jpa */
        CatalogDto createdCatalog = catalogService.createCatalog(catalogEntity);
        ResponseCatalog responseCatalog = mapper.map(createdCatalog, ResponseCatalog.class);

        log.info("After added catalog data");
        return ResponseEntity.status(HttpStatus.CREATED).body("Product is created successfully");
    }

    @GetMapping("/catalogs")
    public ResponseEntity<List<ResponseCatalog>> getCatalogs() {
        Iterable<CatalogEntity> catalogList = catalogService.getAllCatalogs();

        List<ResponseCatalog> result = new ArrayList<>();
        catalogList.forEach(v -> {
            result.add(new ModelMapper().map(v, ResponseCatalog.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    private List<ServiceInstance> getApplications() {

        List<String> services = this.discoveryClient.getServices();
        List<ServiceInstance> instances = new ArrayList<ServiceInstance>();
        services.forEach(serviceName -> {
            this.discoveryClient.getInstances(serviceName).forEach(instance ->{
                instances.add(instance);
            });
        });
        return instances;
    }
}
