package findark.adventure.service;

import findark.adventure.domain.Character;

import findark.adventure.repository.CharacterRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
@Transactional
class CharacterServiceTest {

    @Autowired CharacterService characterService;
    @Autowired
    CharacterRepository characterRepository;
    @Autowired ApiService apiService;

    @Transactional
    @Test
    public void searchCharacter() throws Exception {
        //given
        String name = "사신소녀2";
        //when
        characterService.searchCharacterAndSave(name);

        //then
        List<Character> characters = characterService.getCharacters();
        for (Character character : characters) {
            System.out.println(character.getCharacterName());
            System.out.println(character.getCharacterImage());
        }
    }
}