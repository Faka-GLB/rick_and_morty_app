package com.onboarding.data

import com.onboarding.data.database.entity.RoomCharacter
import com.onboarding.data.database.entity.RoomEpisode
import com.onboarding.data.database.entity.RoomLocationLink
import com.onboarding.data.mapper.transformToCharacterResult
import com.onboarding.data.mapper.transformToEpisodeResult
import com.onboarding.data.mapper.transformToRoomCharacter
import com.onboarding.data.mapper.transformToRoomEpisode
import com.onboarding.domain.entity.CharacterResult
import com.onboarding.domain.entity.EpisodeResult
import com.onboarding.domain.entity.LocationLink
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RoomMapperTest {

    @Test
    fun `location link transform to room`() {
        val locationTransformed = characterResultTest.transformToRoomCharacter().location
        assertEquals(roomLocationLinkTest, locationTransformed)
    }

    @Test
    fun `character result to room`() {
        val characterResultTransformed = characterResultTest.transformToRoomCharacter()
        assertEquals(roomCharacterResult, characterResultTransformed)
    }

    @Test
    fun `room locationLink transform to domain entity`() {
        val locationTransformed = roomCharacterResult.transformToCharacterResult().location
        assertEquals(locationLinkTest, locationTransformed)
    }

    @Test
    fun `room character result transform to domain entity`() {
        val characterResultTransformed = roomCharacterResult.transformToCharacterResult()
        assertEquals(characterResultTest, characterResultTransformed)
    }

    @Test
    fun `episode result transform to room`() {
        val episodeResultTransformed = episodeResultTest.transformToRoomEpisode()
        assertEquals(roomEpisodeResult, episodeResultTransformed)
    }

    @Test
    fun `room episode result transform to domain entity`() {
        val episodeResultTransformed = roomEpisodeResult.transformToEpisodeResult()
        assertEquals(episodeResultTest, episodeResultTransformed)
    }

    companion object {
        private const val STRING_VALUE = "value"
        private const val INT_VALUE = 3

        private val locationLinkTest = LocationLink(
            locationName = STRING_VALUE,
            locationUrl = STRING_VALUE
        )

        private val characterResultTest = CharacterResult(
            id = INT_VALUE,
            name = STRING_VALUE,
            status = STRING_VALUE,
            species = STRING_VALUE,
            type = STRING_VALUE,
            gender = STRING_VALUE,
            origin = locationLinkTest,
            location = locationLinkTest,
            image = STRING_VALUE,
            episodes = listOf(STRING_VALUE),
            characterUrl = STRING_VALUE,
            created = STRING_VALUE
        )

        private val roomLocationLinkTest = RoomLocationLink(
            locationName = STRING_VALUE,
            locationUrl = STRING_VALUE
        )

        private val roomCharacterResult = RoomCharacter(
            id = INT_VALUE,
            name = STRING_VALUE,
            status = STRING_VALUE,
            species = STRING_VALUE,
            type = STRING_VALUE,
            gender = STRING_VALUE,
            origin = roomLocationLinkTest,
            location = roomLocationLinkTest,
            image = STRING_VALUE,
            episodes = listOf(STRING_VALUE),
            characterUrl = STRING_VALUE,
            created = STRING_VALUE
        )

        private val episodeResultTest = EpisodeResult(
            id = INT_VALUE,
            episodeName = STRING_VALUE,
            airDate = STRING_VALUE,
            episodeCode = STRING_VALUE,
            characters = listOf(STRING_VALUE),
            episodeUrl = STRING_VALUE,
            created = STRING_VALUE
        )

        private val roomEpisodeResult = RoomEpisode(
            id = INT_VALUE,
            name = STRING_VALUE,
            airDate = STRING_VALUE,
            episodeCode = STRING_VALUE,
            characters = listOf(STRING_VALUE),
            episodeUrl = STRING_VALUE,
            created = STRING_VALUE
        )
    }
}
