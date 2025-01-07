package findark.adventure.controller;

import findark.adventure.domain.Map;
import findark.adventure.domain.Region;
import findark.adventure.service.MapService;
import findark.adventure.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MapController {

    private final MapService mapService;
    private final RegionService regionService;

    /**
     * 1. "/" 처리 - 지역 선택 화면
     */
    @GetMapping("/")
    public String showRegionSelection(Model model) {
        // 모든 지역 가져오기
        List<Region> regions = regionService.findRegions();
        model.addAttribute("regions", regions);
        return "index";
    }

    /**
     * 2. "/{regionId}" 처리 - 지역별 맵 보기
     */
    @GetMapping("/regions/{regionId}")
    public String showRegionMaps(@PathVariable Long regionId,
                                 @RequestParam("mapId") Long mapId,
                                 Model model) {


        Region region = regionService.findRegion(regionId);
        List<Region> regions = regionService.findRegions();

        Map map;
        // 기본적으로 첫 번째 맵을 표시
        if (mapId == 0) {
            List<Map> maps = mapService.findMapsByRegion(region);
            map = maps.get(0);
        } else {
            map = mapService.findMap(mapId);
        }

        model.addAttribute("regions", regions);
        model.addAttribute("region", region);
        model.addAttribute("map", map);

        return "region-view";
    }

    @GetMapping("/regions/{regionId}/{mapId}/{direct}")
    public String showNextMaps(@PathVariable Long regionId,
                               @PathVariable Long mapId,
                               @PathVariable String direct) {
        Map map = mapService.findMap(mapId);
        if (direct.equals("next")) {
            map = mapService.getNextMap(map);
        } else if (direct.equals("prev")) {
            map = mapService.getPreviousMap(map);
        }

        return "redirect:/regions/" + regionId + "?mapId=" + map.getId();
    }
}