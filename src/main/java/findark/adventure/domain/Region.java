package findark.adventure.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Region {

    @Id @GeneratedValue
    @Column(name = "region_id")
    private Long id;

    private String name;

    private int startItemId;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    private List<Map> maps = new ArrayList<>();
}
