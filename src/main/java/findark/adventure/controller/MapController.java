package findark.adventure.controller;

import findark.adventure.domain.Map;
import findark.adventure.domain.Region;
import findark.adventure.service.AdventureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MapController {

    private final AdventureService mapService;

    /**
     * 1. "/" 처리 - 지역 선택 화면
     */
    @GetMapping("/")
    public String showRegionSelection(Model model) {
        // 모든 지역 가져오기
        List<Region> regions = mapService.findAllRegions();
        model.addAttribute("regions", regions);
        return "index";
    }

    /**
     * 2. "/{regionName}" 처리 - 지역별 맵 보기
     */
    @GetMapping("/{regionName}")
    public String showRegionMaps(@PathVariable String regionName, Model model) {
        List<Map> maps = mapService.getMapsByRegionName(regionName);

        if (maps.isEmpty()) {
            model.addAttribute("error", "No maps available for this region.");
            return "region-view";
        }

        // 기본적으로 첫 번째 맵을 표시
        Map currentMap = maps.get(0);
        Map nextMap = mapService.getNextMap(regionName, currentMap);
        Map prevMap = mapService.getPreviousMap(regionName, currentMap);

        model.addAttribute("regionName", regionName);
        model.addAttribute("maps", maps);
        model.addAttribute("currentMap", currentMap);
        model.addAttribute("nextMap", nextMap);
        model.addAttribute("prevMap", prevMap);

        return "region-view";
    }
}