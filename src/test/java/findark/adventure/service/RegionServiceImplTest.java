package findark.adventure.service;

import findark.adventure.Region;
import findark.adventure.dto.RegionDto;
import findark.adventure.repository.RegionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Arrays;
import java.util.List;


@SpringBootTest
class RegionServiceImplTest {

    @MockitoBean
    private RegionRepository regionRepository;

    @Autowired
    private RegionService regionService;

    @Test
    public void testGetAllRegions() {
        // Given: Region 엔티티와 그에 포함된 Map 객체들 생성
        // Region 엔티티 생성 (maps 필드 포함)
        Region region1 = new Region(1L, "Region 1");
        Region region2 = new Region(2L, "Region 2");

        // 이를 List로 만들어서 반환할 데이터 준비
        List<Region> regions = Arrays.asList(region1, region2);

        // Mockito로 mock 객체 설정 (findAll() 호출 시 regions를 반환하도록 설정)
        Mockito.when(regionRepository.findAll()).thenReturn(regions);

        // When: 서비스 메서드 호출
        List<RegionDto> result = regionService.getAllRegions();

        // Then: 반환된 DTO 목록 검증
        System.out.println(result);
        Assertions.assertThat(result.size()).isEqualTo(2);

        Assertions.assertThat(result.get(0).getName()).isEqualTo(region1.getName());
        Assertions.assertThat(result.get(1).getName()).isEqualTo(region2.getName());

        Assertions.assertThat(result.get(0).getId()).isEqualTo(region1.getId());
        Assertions.assertThat(result.get(1).getId()).isEqualTo(region2.getId());
    }
}