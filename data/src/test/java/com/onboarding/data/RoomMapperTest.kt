package com.onboarding.data

import com.onboarding.data.database.entity.RoomCharacter
import com.onboarding.data.database.entity.RoomLocationLink
import com.onboarding.data.mapper.transform
import com.onboarding.data.mapper.transformToRoom
import com.onboarding.domain.entity.CharacterResult
import com.onboarding.domain.entity.LocationLink
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RoomMapperTest {

    @Test
    fun `location link transform to room`() {
        val locationTransformed = characterResultTest.transformToRoom().location
        assertEquals(roomLocationLinkTest, locationTransformed)
    }

    @Test
    fun `character result to room`() {
        val characterResultTransformed = characterResultTest.transformToRoom()
        assertEquals(roomCharacterResult, characterResultTransformed)
    }

    @Test
    fun `room locationLink transform to domain entity`() {
        val locationTransformed = roomCharacterResult.transform().location
        assertEquals(locationLinkTest, locationTransformed)
    }

    @Test
    fun `room character result transform to domain entity`() {
        val characterResultTransformed = roomCharacterResult.transform()
        assertEquals(characterResultTest, characterResultTransformed)
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
    }
}
