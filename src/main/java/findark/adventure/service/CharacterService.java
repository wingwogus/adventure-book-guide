package findark.adventure.service;

import findark.adventure.domain.ArkPassivePoint;
import findark.adventure.domain.Character;
import findark.adventure.domain.Town;
import findark.adventure.dto.CharacterInfo;
import findark.adventure.dto.CharacterReq;
import findark.adventure.dto.CharacterRes;
import findark.adventure.repository.CharacterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CharacterService {

    private final ApiService apiService;
    private final CharacterRepository characterRepository;

    @Transactional
    public void searchCharacterAndSave(String name) {
        CharacterReq characterReq = new CharacterReq();
        characterReq.setCharacterName(name);

        CharacterRes body = apiService.characterSearch(characterReq).getBody();

        if (body != null && body.getCharacterInfo() != null) {
            for (CharacterInfo characterInfo : body.getCharacterInfo()) {
                Character character = new Character();

                character.setServerName(characterInfo.getServerName());
                character.setCharacterName(characterInfo.getCharacterName());
                character.setCharacterLevel(characterInfo.getCharacterLevel());
                character.setCharacterClassName(characterInfo.getCharacterClassName());
                character.setItemAvgLevel(characterInfo.getItemAvgLevel());
                character.setItemMaxLevel(characterInfo.getItemMaxLevel());
                character.setLastModifiedAt(LocalDateTime.now());

                CharacterInfo.ArmoryProfile armoryProfile = characterInfo.getArmoryProfile();
                if(armoryProfile != null) {
                    character.setCharacterImage(armoryProfile.getCharacterImage());
                    character.setExpeditionLevel(armoryProfile.getExpeditionLevel());
                    character.setTotalSkillPoint(armoryProfile.getTotalSkillPoint());
                    character.setTitle(armoryProfile.getTitle());
                    character.setGuildName(armoryProfile.getGuildName());

                    Town town = new Town(armoryProfile.getTownName(), armoryProfile.getTownLevel());
                    character.setTown(town);
                }

                CharacterInfo.CharacterArkPassive arkPassive = characterInfo.getArkPassive();
                if(arkPassive != null) {
                    List<CharacterInfo.CharacterArkPassive.Point> points = arkPassive.getPoints();
                    ArkPassivePoint arkPassivePoint = new ArkPassivePoint(
                            points.get(0).getValue(),
                            points.get(1).getValue(),
                            points.get(2).getValue());
                    character.setArkPassivePoint(arkPassivePoint);
                }

                characterRepository.save(character);
            }
        }
    }

    public Character getCharacterByName(String name) {
        return characterRepository.findByCharacterName(name);
    }

    public List<Character> getCharacters() {
        return characterRepository.findAll();
    }
}
