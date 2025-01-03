package findark.adventure.service;

import findark.adventure.domain.Map;
import findark.adventure.domain.Region;
import findark.adventure.repository.MapRepository;
import findark.adventure.repository.RegionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class MapServiceTest {

    @Autowired MapService mapService;
    @Autowired MapRepository mapRepository;
    @Autowired RegionRepository regionRepository;

    private Region region;
    private Map map1;
    private Map map2;

    @BeforeEach
    public void setUp() throws Exception {
        region = new Region();
        region.setName("Test Region");

        map1 = new Map();
        map1.setRegion(region);

        map2 = new Map();
        map2.setRegion(region);

        regionRepository.save(region);
        }

    @Test
    public void testGetPreviousMap_withValidPrevious() throws Exception {
        // 두 번째 맵에서 이전 맵이 있는지 확인
        Map result = mapService.getPreviousMap(mapRepository.findByRegionOrderById(region.getId()), map2.getId());
        Assertions.assertThat(result).isEqualTo(map1);
    }

    @Test
    public void testGetNextMap_withValidNext() throws Exception{
        // 첫 번째 맵에서 다음 맵이 있는지 확인
        Map result = mapService.getNextMap(mapRepository.findByRegionOrderById(region.getId()), map1.getId());
        Assertions.assertThat(result).isEqualTo(map2);

    }

//    @Test
//    public void testGetMapsByRegion() throws Exception{
//        // 특정 지역에 대한 맵 리스트 가져오기
//        List<Map> maps = mapService.getMapsByRegion(region.getId());
//        ass
//        assertEquals(2, maps.size());  // 두 개의 맵이 반환되어야 함
//        assertTrue(maps.contains(map1));
//        assertTrue(maps.contains(map2));
//    }
}