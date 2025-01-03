package findark.adventure.service;

import findark.adventure.MapPortal;
import findark.adventure.dto.PortalDto;
import findark.adventure.repository.MapPortalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MapPortalServiceImpl implements MapPortalService {

    private final MapPortalRepository mapPortalRepository;

    @Override
    public List<PortalDto> getPortalsByMap(Long mapId) {
        List<MapPortal> mapPortals = mapPortalRepository.findByMapId(mapId);
        return mapPortals.stream()
                .map(mp -> new PortalDto(mp.getPortal().getId(),
                        mp.getPortal().getName(),
                        mp.getX(),
                        mp.getY()))
                .collect(Collectors.toList());
    }
}
