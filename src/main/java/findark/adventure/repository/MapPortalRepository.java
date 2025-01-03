package findark.adventure.repository;

import findark.adventure.MapPortal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MapPortalRepository extends JpaRepository<MapPortal, Long> {
    List<MapPortal> findByMapId(Long id);
}
