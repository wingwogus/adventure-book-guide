package findark.adventure;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
public class MapPortal {

    @Id @GeneratedValue
    @Column(name = "map_portal_id")
    private Long id;

    private int x; // 포탈의 X 좌표
    private int y; // 포탈의 Y 좌표

    public MapPortal() {
    }

    public MapPortal(Long id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    @ManyToOne
    @JoinColumn(name = "map_id")
    private Map map;

    @ManyToOne
    @JoinColumn(name = "portal_id")
    private Portal portal;
}
