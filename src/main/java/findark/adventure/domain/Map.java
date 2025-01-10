package findark.adventure.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Map {
    @Id @GeneratedValue
    @Column(name = "map_id")
    private Long id;

    private String name;

    private String imageUrl;

    private int mapOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;
}
