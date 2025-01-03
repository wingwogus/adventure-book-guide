package findark.adventure;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Portal {

    @Id @GeneratedValue
    @Column(name = "portal_id")
    private Long id;

    private String name;

    public Portal() {
    }

    public Portal(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @OneToMany(mappedBy = "portal", cascade = CascadeType.ALL)
    private List<MapPortal> mapPortals = new ArrayList<>();
}
