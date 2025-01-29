package findark.adventure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CharacterInfo {
    @JsonProperty("ServerName")
    private String serverName;

    @JsonProperty("CharacterName")
    private String characterName;

    @JsonProperty("CharacterLevel")
    private Integer characterLevel;

    @JsonProperty("CharacterClassName")
    private String characterClassName;

    @JsonProperty("ItemAvgLevel")
    private String itemAvgLevel;

    @JsonProperty("ItemMaxLevel")
    private String itemMaxLevel;

    private ArmoryProfile armoryProfile;

    private CharacterArkPassive arkPassive;

    @Getter
    @Setter
    public static class CharacterArkPassive {
        @JsonProperty("Points")
        private List<Point> points;

        @Getter
        @Setter
        public static class Point {
            @JsonProperty("Name")
            private String name;

            @JsonProperty("Value")
            private int value;

            @JsonProperty("ToolTip")
            private String toolTip;
        }
    }

    @Getter
    @Setter
    public static class ArmoryProfile {
        @JsonProperty("CharacterImage")
        private String characterImage;

        @JsonProperty("ExpeditionLevel")
        private Integer expeditionLevel;

        @JsonProperty("TownLevel")
        private Integer townLevel;

        @JsonProperty("TownName")
        private String townName;

        @JsonProperty("TotalSkillPoint")
        private Integer totalSkillPoint;

        @JsonProperty("Title")
        private String title;

        @JsonProperty("GuildName")
        private String guildName;
    }
}
