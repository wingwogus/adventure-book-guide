package findark.adventure.service;

import findark.adventure.domain.Character;
import findark.adventure.dto.CharacterInfo;
import findark.adventure.dto.CharacterReq;
import findark.adventure.dto.CharacterRes;
import findark.adventure.dto.MarketSearchRes;
import findark.adventure.repository.CharacterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        ResponseEntity<CharacterRes> response = apiService.characterSearch(characterReq);

        CharacterRes body = response.getBody();

        if (body != null && body.getCharacterInfo() != null) {
            for (CharacterInfo characterInfo : body.getCharacterInfo()) {
                Character character = new Character();

                character.setServerName(characterInfo.getServerName());
                character.setCharacterName(characterInfo.getCharacterName());
                character.setCharacterLevel(characterInfo.getCharacterLevel());
                character.setCharacterClassName(characterInfo.getCharacterClassName());
                character.setItemAvgLevel(characterInfo.getItemAvgLevel());
                character.setItemMaxLevel(characterInfo.getItemMaxLevel());
                CharacterInfo.ArmoryProfile armoryProfile = characterInfo.getArmoryProfile();
                if(armoryProfile != null) {
                    character.setCharacterImage(armoryProfile.getCharacterImage());
                    character.setTownName(armoryProfile.getTownName());
                    character.setTownLevel(armoryProfile.getTownLevel());
                    character.setExpeditionLevel(armoryProfile.getExpeditionLevel());
                    character.setTotalSkillPoint(armoryProfile.getTotalSkillPoint());
                    character.setTitle(armoryProfile.getTitle());
                    character.setGuildName(armoryProfile.getGuildName());
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
