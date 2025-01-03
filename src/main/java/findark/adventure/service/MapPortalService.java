package findark.adventure.service;

import findark.adventure.dto.PortalDto;

import java.util.List;

public interface MapPortalService {
    List<PortalDto> getPortalsByMap(Long mapId);
}
