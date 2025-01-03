package findark.adventure.service;

import findark.adventure.domain.Map;
import findark.adventure.domain.Region;
import findark.adventure.repository.MapRepository;
import findark.adventure.repository.RegionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MapServiceTest {

    @Autowired
    private MapService mapService;

    @Autowired
    private MapRepository mapRepository;

    @Autowired
    private RegionRepository regionRepository;

    private Region region;
    private Map map1;
    private Map map2;

    @BeforeEach
    public void setUp() {
        region = new Region();
        region.setId(1L);
        region.setName("Test Region");

        map1 = new Map();
        map1.setId(1L);
        map1.setName("Map 1");
        map1.setImageUrl("/images/map1.jpg");
        map1.setRegion(region);

        map2 = new Map();
        map2.setId(2L);
        map2.setName("Map 2");
        map2.setImageUrl("/images/map2.jpg");
        map2.setRegion(region);

        region.setMaps(Arrays.asList(map1, map2));
        regionRepository.save(region);
        }

    @Test
    public void testGetPreviousMap_withValidPrevious() {
        // 두 번째 맵에서 이전 맵이 있는지 확인
        Map result = mapService.getPreviousMap(mapRepository.findByRegionOrderById(region), map2.getId());
        assertEquals(map1.getName(), result.getName());  // 이전 맵은 "Map 1"
    }

    @Test
    public void testGetNextMap_withValidNext() {
        // 첫 번째 맵에서 다음 맵이 있는지 확인
        Map result = mapService.getNextMap(mapRepository.findByRegionOrderById(region), map1.getId());
        assertEquals(map2.getName(), result.getName());  // 다음 맵은 "Map 2"
    }

    @Test
    public void testGetMapsByRegion() {
        // 특정 지역에 대한 맵 리스트 가져오기
        List<Map> maps = mapService.getMapsByRegion(region.getId());
        assertEquals(2, maps.size());  // 두 개의 맵이 반환되어야 함
        assertTrue(maps.contains(map1));
        assertTrue(maps.contains(map2));
    }
}