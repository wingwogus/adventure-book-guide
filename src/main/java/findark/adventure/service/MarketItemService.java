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

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MarketItemService {

    private final ApiService apiService;
    private final MarketItemRepository marketItemRepository;
    private final RegionService regionService;

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

                    switch (item.getId() / 100) {
                        case 9101: marketItem.setRegion(regionService.findRegion(1L));break;
                        case 9117: marketItem.setRegion(regionService.findRegion(2L));break;
                        case 9114: marketItem.setRegion(regionService.findRegion(3L));break;
                        case 9115: marketItem.setRegion(regionService.findRegion(4L));break;
                        case 9102: marketItem.setRegion(regionService.findRegion(5L));break;
                        case 9103: marketItem.setRegion(regionService.findRegion(6L));break;
                        case 9104: marketItem.setRegion(regionService.findRegion(7L));break;
                        case 9111: marketItem.setRegion(regionService.findRegion(8L));break;
                        case 9106: marketItem.setRegion(regionService.findRegion(9L));break;
                        case 9091: marketItem.setRegion(regionService.findRegion(10L));break;
                        case 9071: marketItem.setRegion(regionService.findRegion(11L));break;
                        case 9110: marketItem.setRegion(regionService.findRegion(12L));break;
                        case 9108: marketItem.setRegion(regionService.findRegion(13L));break;
                        case 9119: marketItem.setRegion(regionService.findRegion(14L));break;
                        case 9124: marketItem.setRegion(regionService.findRegion(15L));break;
                        case 9112: marketItem.setRegion(regionService.findRegion(16L));break;
                        case 9125: marketItem.setRegion(regionService.findRegion(18L));break;
                        case 9113: marketItem.setRegion(regionService.findRegion(19L));break;
                        case 9126: marketItem.setRegion(regionService.findRegion(20L));break;
                        case 9121: marketItem.setRegion(regionService.findRegion(21L));break;
                    }

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
        return marketItemRepository.findMarketItemsByRegion(region);
    }
}
