package findark.adventure.controller;

import findark.adventure.dto.PortalDto;
import findark.adventure.service.MapPortalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/maps")
public class MapPortalController {

    private final MapPortalService mapPortalService;

    @GetMapping("/{mapId}/portals")
    public List<PortalDto> getPortalsByMap(@PathVariable Long mapId) {
        return mapPortalService.getPortalsByMap(mapId);
    }
}
