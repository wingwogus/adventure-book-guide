package findark.adventure.dto;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class MarketSearchReq {
    private String sort;
    private int categoryCode;
    private String itemName;
    private int pageNo;
    private int pageSize;
    private String sortCondition;
}
