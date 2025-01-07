package findark.adventure.service;

import findark.adventure.domain.Region;
import findark.adventure.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RegionService {

    private final RegionRepository regionRepository;

    public Region findRegion(Long regionId) {
        return regionRepository.findById(regionId)
                .orElseThrow(() -> new IllegalArgumentException("Region not found"));
    }

    public List<Region> findRegions() {
        return regionRepository.findAll();
    }
}
