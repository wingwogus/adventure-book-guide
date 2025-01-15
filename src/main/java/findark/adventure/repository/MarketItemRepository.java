package findark.adventure.repository;

import findark.adventure.domain.MarketItem;
import findark.adventure.domain.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarketItemRepository extends JpaRepository<MarketItem, Integer> {
    List<MarketItem> findMarketItemsByRegion(Region region);
}
