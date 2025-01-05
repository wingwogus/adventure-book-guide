package findark.adventure.service;

import findark.adventure.domain.Map;
import findark.adventure.domain.Region;
import findark.adventure.repository.MapRepository;
import findark.adventure.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdventureService {

    private final MapRepository mapRepository;
    private final RegionRepository regionRepository;

    public List<Map> getMapsByRegionName(String regionName) {
        Region region = regionRepository.findByName(regionName);
        return mapRepository.findByRegion(region);
    }

    public List<Region> findAllRegions() {
        return regionRepository.findAll();
    }

    public Map getMapById(Long id) {
        return mapRepository.findById(id).orElse(null);
    }

    public Map getNextMap(String regionName, Map currentMap) {
        Region region = regionRepository.findByName(regionName);
        List<Map> maps = mapRepository.findByRegion(region);

        // 다음 맵이 존재하는지 확인
        return maps.get((currentMap.getMapOrder() + 1) % maps.size());

    }

    public Map getPreviousMap(String regionName, Map currentMap) {
        Region region = regionRepository.findByName(regionName);
        List<Map> maps = mapRepository.findByRegion(region);

        return maps.get((currentMap.getMapOrder() - 1 + maps.size()) % maps.size());
    }
}
