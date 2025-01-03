package findark.adventure.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegionDto {
    private Long id;
    private String name;

    public RegionDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}