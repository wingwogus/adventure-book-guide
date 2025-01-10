package findark.adventure.service;

import findark.adventure.domain.MarketItem;
import findark.adventure.domain.Region;
import findark.adventure.repository.MarketItemRepository;
import findark.adventure.repository.RegionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MarketItemServiceTest {

    @Autowired RegionService regionService;
    @Autowired MarketItemRepository marketItemRepository;
    @Autowired MarketItemService marketItemService;

    @Transactional
    @Test
    void findItemByRegion() {
        Region region = regionService.findRegion(1L);
        marketItemService.fetchAndSaveMarketData();

        List<MarketItem> items = marketItemService.getItemsByRegion(region);
        for (MarketItem item : items) {
            System.out.print("item.getId() = " + item.getId());
            System.out.println("item.getName() = " + item.getName());
        }
    }

}