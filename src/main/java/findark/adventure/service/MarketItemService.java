package findark.adventure.service;

import findark.adventure.domain.MarketItem;
import findark.adventure.domain.Region;
import findark.adventure.dto.MarketSearchReq;
import findark.adventure.dto.MarketSearchRes;
import findark.adventure.repository.MarketItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MarketItemService {

    private final ApiService apiService;
    private final MarketItemRepository marketItemRepository;

    @Transactional
    public void fetchAndSaveMarketData() {
        int pageNo = 1;
        int pageSize = 10;
        boolean hasMoreData;

        do {
            MarketSearchReq params = new MarketSearchReq();
            params.setSort("GRADE");
            params.setCategoryCode(100000);
            params.setPageNo(pageNo);
            params.setPageSize(pageSize);
            params.setSortCondition("ASC");

            ResponseEntity<MarketSearchRes> response = apiService.marketSearch(params);
            MarketSearchRes body = response.getBody();

            if (body != null && body.getItems() != null) {
                for (MarketSearchRes.MarketResultVO item : body.getItems()) {
                    MarketItem marketItem = new MarketItem();
                    marketItem.setId(item.getId());
                    marketItem.setName(item.getName());
                    marketItem.setGrade(item.getGrade());
                    marketItem.setIcon(item.getIcon());
                    marketItem.setBundleCount(item.getBundleCount());
                    marketItem.setTradeRemainCount(item.getTradeRemainCount());
                    marketItem.setYDayAvgPrice(item.getYDayAvgPrice());
                    marketItem.setRecentPrice(item.getRecentPrice());
                    marketItem.setCurrentMinPrice(item.getCurrentMinPrice());

                    marketItemRepository.save(marketItem);
                }
                hasMoreData = body.getItems().size() == pageSize; // 페이지가 꽉 찼으면 더 있음
            } else {
                hasMoreData = false;
            }
            pageNo++;
        } while (hasMoreData);
    }

    public List<MarketItem> getItemsByRegion(Region region) {
        int itemId = region.getStartItemId();
        List<MarketItem> marketItems = new ArrayList<>();
        for (MarketItem item : marketItemRepository.findAll()) {
            if (item.getId() >= itemId && item.getId() <= itemId + 6) {
                marketItems.add(item);
            }
        }

        return marketItems;
    }
}
