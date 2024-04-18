package org.rrd.inventoryservice.controller;

import lombok.RequiredArgsConstructor;
import org.rrd.inventoryservice.dto.InventoryResponse;
import org.rrd.inventoryservice.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam() List<String> skuCode) {
        return this.inventoryService.isInStock(skuCode);
    }
}
