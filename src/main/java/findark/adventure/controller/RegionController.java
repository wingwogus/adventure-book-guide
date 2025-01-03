package findark.adventure.controller;


import findark.adventure.dto.MapDto;
import findark.adventure.dto.RegionDto;
import findark.adventure.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/regions")
public class RegionController {

    private final RegionService regionService;

    @GetMapping
    public List<RegionDto> getAllRegions() {
        return regionService.getAllRegions();
    }

    @GetMapping("/{regionId}/maps")
    public List<MapDto> getMapsByRegion(@PathVariable Long regionId) {
        return regionService.getMapsByRegion(regionId);
    }
}
