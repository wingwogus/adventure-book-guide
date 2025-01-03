package findark.adventure.service;

import findark.adventure.Region;
import findark.adventure.dto.MapDto;
import findark.adventure.dto.RegionDto;
import findark.adventure.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;

    @Override
    public List<RegionDto> getAllRegions() {
        List<Region> regions = regionRepository.findAll();
        return regions.stream()
                .map(region -> new RegionDto(region.getId(), region.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<MapDto> getMapsByRegion(Long regionId) {
        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new RuntimeException("Region not found"));
        return region.getMaps().stream()
                .map(map -> new MapDto(map.getId(), map.getName(), map.getImageUrl()))
                .collect(Collectors.toList());
    }
}
