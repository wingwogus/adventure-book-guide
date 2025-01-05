package findark.adventure.repository;

import findark.adventure.domain.Map;
import findark.adventure.domain.Region;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MapRepository extends JpaRepository<Map, Long> {
    List<Map> findByRegion(Region region);
}
