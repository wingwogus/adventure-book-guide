package findark.adventure;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Map {

    @Id @GeneratedValue
    @Column(name = "map_id")
    private Long id;

    private String name;

    private String imageUrl;

    public Map() {
    }

    public Map(Long id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @OneToMany(mappedBy = "map", cascade = CascadeType.ALL)
    private List<MapPortal> mapPortals = new ArrayList<>();
}
