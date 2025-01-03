package findark.adventure.controller;

import findark.adventure.domain.Map;
import findark.adventure.repository.RegionRepository;
import findark.adventure.service.MapService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MapController {

    private MapService mapService;

    private RegionRepository regionRepository;

    // 지역을 클릭했을 때 첫 번째 맵을 반환
    @GetMapping("/region/first-map/{regionId}")
    public String getFirstMap(@PathVariable Long regionId, Model model) {
        List<Map> maps = mapService.getMapsByRegion(regionId);
        if (!maps.isEmpty()) {
            Map firstMap = maps.get(0);  // 첫 번째 맵
            model.addAttribute("map", firstMap);
            model.addAttribute("maps", maps);  // 전체 맵 목록을 모델에 추가
        }
        return "mapDetails";  // 맵 세부 정보 반환
    }

    // 맵의 이전 맵을 반환
    @GetMapping("/map/previous/{mapId}")
    public String getPreviousMap(@PathVariable Long mapId, @RequestParam Long regionId, Model model) {
        List<Map> maps = mapService.getMapsByRegion(regionId);
        Map previousMap = mapService.getPreviousMap(maps, mapId);
        if (previousMap != null) {
            model.addAttribute("map", previousMap);
        }
        model.addAttribute("maps", maps);  // 전체 맵 목록을 모델에 추가
        return "mapDetails";  // 맵 세부 정보 반환
    }

    // 맵의 다음 맵을 반환
    @GetMapping("/map/next/{mapId}")
    public String getNextMap(@PathVariable Long mapId, @RequestParam Long regionId, Model model) {
        List<Map> maps = mapService.getMapsByRegion(regionId);
        Map nextMap = mapService.getNextMap(maps, mapId);
        if (nextMap != null) {
            model.addAttribute("map", nextMap);
        }
        model.addAttribute("maps", maps);  // 전체 맵 목록을 모델에 추가
        return "mapDetails";  // 맵 세부 정보 반환
    }
}
