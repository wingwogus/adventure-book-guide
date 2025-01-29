package findark.adventure.service;

import findark.adventure.domain.Map;
import findark.adventure.domain.Region;
import findark.adventure.repository.MapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MapService {

    private final MapRepository mapRepository;

    public List<Map> findMapsByRegion(Region region) {

        return mapRepository.findMapsByRegion(region);
    }

    public Map findMap(Long id) {
        return mapRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Map not found"));
    }

    public Map getNextMap(Map map) {
        Region region = map.getRegion();
        List<Map> maps = mapRepository.findMapsByRegion(region);
        return maps.get((map.getMapOrder()) % maps.size());
    }

    public Map getPrevMap(Map map) {
        Region region = map.getRegion();
        List<Map> maps = mapRepository.findMapsByRegion(region);
        return maps.get((map.getMapOrder() + maps.size() - 2) % maps.size());
    }
}
