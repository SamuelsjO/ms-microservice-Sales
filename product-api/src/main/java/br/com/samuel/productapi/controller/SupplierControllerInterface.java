package br.com.samuel.productapi.controller;

import br.com.samuel.productapi.config.SuccessResponse;
import br.com.samuel.productapi.dtos.supplier.SupplierRequest;
import br.com.samuel.productapi.dtos.supplier.SupplierResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.Authorization;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(tags = "Endpoint supplier", authorizations = @Authorization("Authorization"))
@RequestMapping(value = "/api/supplier",
        consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
        produces = APPLICATION_JSON_VALUE)
public interface SupplierControllerInterface {


    @PostMapping(
            consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
            produces = APPLICATION_JSON_VALUE)
    SupplierResponse saveSupplier(@RequestBody SupplierRequest request);


    @GetMapping(value = "IdSupplier",
            consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
            produces = APPLICATION_JSON_VALUE)
    SupplierResponse findByIdSupplier(@RequestParam Integer id);

    @GetMapping( value = "AllSupplier",
            consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
            produces = APPLICATION_JSON_VALUE)
    List<SupplierResponse> findaAllSupplier();

    @GetMapping(value = "name",
            consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
            produces = APPLICATION_JSON_VALUE)
    List<SupplierResponse> findByNameIgnoreCaseContaining(
            @RequestParam String name);

    @PutMapping(
            consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
            produces = APPLICATION_JSON_VALUE)
    SupplierResponse updateSupplier(@RequestBody SupplierRequest request,
                                    @RequestParam Integer id);

    @DeleteMapping(
            consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
            produces = APPLICATION_JSON_VALUE)
    SuccessResponse deleteSupplier(@RequestParam Integer id);
}
