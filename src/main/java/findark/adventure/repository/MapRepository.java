package findark.adventure.repository;

import findark.adventure.domain.Map;
import findark.adventure.domain.Region;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MapRepository {

    private final EntityManager em;

    List<Map> findMapsByRegion(Long regionId);
}
