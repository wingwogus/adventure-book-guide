package findark.adventure.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter@Setter
public class MarketItem {

    @Id
    @Column(name = "item_id")
    private int id;
    private String name;
    private String grade;
    private String icon;
    private int bundleCount;
    private Integer tradeRemainCount;
    private double yDayAvgPrice;
    private int recentPrice;
    private int currentMinPrice;
}