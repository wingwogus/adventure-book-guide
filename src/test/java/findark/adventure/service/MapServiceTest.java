package findark.adventure.service;

import findark.adventure.domain.Map;
import findark.adventure.domain.Region;
import findark.adventure.repository.MapRepository;
import findark.adventure.repository.RegionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class MapServiceTest {

    @Autowired
    AdventureService mapService;

    @Autowired MapRepository mapRepository;

    @Autowired RegionRepository regionRepository;



    @Test
    @Transactional()
    public void testGetMapsByRegionName() {
        // Given
        Region regionA = new Region();
        regionA.setName("Region A");

        Map map1 = new Map();
        map1.setName("Map 1");
        map1.setMapOrder(0);
        mapRepository.save(map1);

        regionA.addMap(mapRepository.findById(map1.getId()).orElseThrow());

        regionRepository.save(regionA);

        String regionName = "Region A";

        // When
        List<Map> result = mapService.getMapsByRegionName(regionName);

        // Then
        assertThat(result.get(0)).isEqualTo(mapRepository.findById(map1.getId()).orElseThrow());
    }

    @Test
    public void 이전_맵_확인() throws Exception {
        //given
        Region regionA = new Region();
        regionA.setName("Region A");

        Map map1 = new Map();
        map1.setName("Map 1");
        map1.setMapOrder(0);
        mapRepository.save(map1);

        Map map2 = new Map();
        map2.setName("Map 2");
        map2.setMapOrder(1);
        mapRepository.save(map2);

        Map map3 = new Map();
        map3.setName("Map 3");
        map3.setMapOrder(2);
        mapRepository.save(map3);

        Map map4 = new Map();
        map4.setName("Map 4");
        map4.setMapOrder(3);
        mapRepository.save(map4);

        regionA.addMap(mapRepository.findById(map1.getId()).orElseThrow());
        regionA.addMap(mapRepository.findById(map2.getId()).orElseThrow());
        regionA.addMap(mapRepository.findById(map3.getId()).orElseThrow());
        regionA.addMap(mapRepository.findById(map4.getId()).orElseThrow());

        regionRepository.save(regionA);

        //when
        Map nextMap = mapService.getNextMap("Region A", map1);
        Map nextMap2 = mapService.getNextMap("Region A", nextMap);
        Map previousMap = mapService.getPreviousMap("Region A", nextMap);

        System.out.println("map1 nextMap.getName() = " + nextMap.getName());
        System.out.println("previousMap.getName() = " + previousMap.getName());

        //then
        assertThat(nextMap).isEqualTo(mapRepository.findById(map2.getId()).orElseThrow());
        assertThat(nextMap2).isEqualTo(mapRepository.findById(map3.getId()).orElseThrow());
        assertThat(previousMap).isEqualTo(mapRepository.findById(map1.getId()).orElseThrow());
     }

     @Test
     public void 마지막_지역() throws Exception {
         //given
         Region regionA = new Region();
         regionA.setName("Region A");

         Map map1 = new Map();
         map1.setName("Map 1");
         map1.setMapOrder(0);
         mapRepository.save(map1);

         Map map2 = new Map();
         map2.setName("Map 2");
         map2.setMapOrder(1);
         mapRepository.save(map2);

         Map map3 = new Map();
         map3.setName("Map 3");
         map3.setMapOrder(2);
         mapRepository.save(map3);

         Map map4 = new Map();
         map4.setName("Map 4");
         map4.setMapOrder(3);
         mapRepository.save(map4);

         regionA.addMap(mapRepository.findById(map1.getId()).orElseThrow());
         regionA.addMap(mapRepository.findById(map2.getId()).orElseThrow());
         regionA.addMap(mapRepository.findById(map3.getId()).orElseThrow());
         regionA.addMap(mapRepository.findById(map4.getId()).orElseThrow());

         regionRepository.save(regionA);
         //when
         Map nextMap = mapService.getNextMap("Region A", map4);
         Map previousMap = mapService.getPreviousMap("Region A", map1);

         //then
         assertThat(nextMap).isEqualTo(mapRepository.findById(map1.getId()).orElseThrow());
         assertThat(previousMap).isEqualTo(mapRepository.findById(map4.getId()).orElseThrow());
     }
}