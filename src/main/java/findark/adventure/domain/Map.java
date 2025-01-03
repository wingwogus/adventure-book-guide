package findark.adventure.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Map {
    @Id
    @GeneratedValue
    @Column(name = "map_id")
    private Long id;

    private String name;
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    //연관관계 메서드
    public void setRegion(Region region) {
        this.region = region;
        region.getMaps().add(this);
    }
}
