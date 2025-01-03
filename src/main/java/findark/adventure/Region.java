package findark.adventure;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Region {

    @Id @GeneratedValue
    @Column(name = "region_id")
    private Long id;

    private String name;

    public Region() {

    }

    public Region(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    private List<Map> maps = new ArrayList<>();
}
