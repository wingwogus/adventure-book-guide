package findark.adventure.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MapDto {
    private Long id;
    private String name;
    private String imageUrl;

    public MapDto(Long id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }
}
