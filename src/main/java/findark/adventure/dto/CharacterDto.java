package findark.adventure.dto;

import findark.adventure.domain.ArkPassivePoint;
import findark.adventure.domain.Town;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class CharacterDto {

    private String serverName;

    private String characterName;

    private Integer characterLevel;

    private String characterClassName;

    private String itemMaxLevel;

    private String characterImage;

    private Integer expeditionLevel;

    private String title;

    private String guildName;

    @Embedded
    private Town town;

    @Embedded
    private ArkPassivePoint arkPassivePoint;
}
