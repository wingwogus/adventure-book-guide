package findark.adventure.repository;

import findark.adventure.domain.MarketItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketItemRepository extends JpaRepository<MarketItem, Integer> {
}
