package findark.adventure.service;

import findark.adventure.domain.Map;
import findark.adventure.domain.Region;
import findark.adventure.repository.MapRepository;
import findark.adventure.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MapService {

    private final RegionRepository regionRepository;
    private final MapRepository mapRepository;

    // 지역에 속한 모든 맵 가져오기
    public List<Map> getMapsByRegion(Long regionId) {
        Optional<Region> region = regionRepository.findById(regionId);
        if (region.isPresent()) {
            return mapRepository.findByRegionOrderById(region.get().getId());
        } else {
            return Collections.emptyList();
        }
    }

    // 현재 맵을 기준으로 이전 맵을 반환
    public Map getPreviousMap(List<Map> maps, Long currentMapId) {
        int index = findMapIndex(maps, currentMapId);
        if (index > 0) {
            return maps.get(index - 1);
        }
        return null;
    }

    // 현재 맵을 기준으로 다음 맵을 반환
    public Map getNextMap(List<Map> maps, Long currentMapId) {
        int index = findMapIndex(maps, currentMapId);
        if (index < maps.size() - 1) {
            return maps.get(index + 1);
        }
        return null;
    }

    // 맵의 인덱스를 찾아주는 메서드
    private int findMapIndex(List<Map> maps, Long currentMapId) {
        for (int i = 0; i < maps.size(); i++) {
            if (maps.get(i).getId().equals(currentMapId)) {
                return i;
            }
        }

        return -1;
    }
}
