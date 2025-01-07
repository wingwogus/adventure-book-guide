package findark.adventure.repository;

import findark.adventure.domain.Map;
import findark.adventure.domain.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MapRepository extends JpaRepository<Map, Long> {
    List<Map> findMapsByRegion(Region region);
}
