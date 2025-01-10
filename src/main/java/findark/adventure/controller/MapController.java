package findark.adventure.controller;

import findark.adventure.domain.Map;
import findark.adventure.domain.MarketItem;
import findark.adventure.domain.Region;
import findark.adventure.service.MapService;
import findark.adventure.service.MarketItemService;
import findark.adventure.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/regions")
public class MapController {

    private final MapService mapService;
    private final RegionService regionService;
    private final MarketItemService marketItemService;

    /**
     * 1. "/" 처리 - 지역 선택 화면
     */
    @GetMapping("")
    public String home(Model model) {
        // 모든 지역 가져오기
        List<Region> regions = regionService.getRegions();
        marketItemService.fetchAndSaveMarketData();
        model.addAttribute("regions", regions);
        return "redirect:/regions/" + regions.getLast().getId();
    }

    /**
     * 2. "/{regionId}" 처리 - 지역별 맵 보기
     */
    @GetMapping("/{regionId}")
    public String showRegionMaps(@PathVariable Long regionId,
                                 @RequestParam(value = "mapId", required = false) Long mapId,
                                 @RequestParam(value = "direct", required = false) String direct,
                                 Model model) {
        List<Region> regions = regionService.getRegions();
        Region region = regionService.findRegion(regionId);
        Collections.reverse(regions);

        Map map;
        //mapId 값 존재 시
        if (mapId != null) map = mapService.findMap(mapId);
        //없으면 첫번째 반환
        else map = mapService.findMapsByRegion(region).get(0);

        //direct 값 존재 시
        if (direct != null) {
            if (direct.equals("next")) mapId = mapService.getNextMap(map).getId();
            else mapId = mapService.getPrevMap(map).getId();

            return "redirect:/regions/" + regionId + "?mapId=" + mapId;
        }

        List<MarketItem> items = marketItemService.getItemsByRegion(region);

        model.addAttribute("regions", regions);
        model.addAttribute("region", region);
        model.addAttribute("map", map);
        model.addAttribute("items", items);

        return "main-view";
    }
}