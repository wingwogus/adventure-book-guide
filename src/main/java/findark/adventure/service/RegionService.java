package findark.adventure.service;

import findark.adventure.dto.MapDto;
import findark.adventure.dto.RegionDto;

import java.util.List;

public interface RegionService {
    List<RegionDto> getAllRegions();
    List<MapDto> getMapsByRegion(Long regionId);
}