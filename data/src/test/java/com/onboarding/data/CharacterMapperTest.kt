package com.onboarding.data

import com.onboarding.data.mapper.transform
import com.onboarding.data.service.response.CharacterBaseResponse
import com.onboarding.data.service.response.CharactersResultResponse
import com.onboarding.data.service.response.InfoResponse
import com.onboarding.data.service.response.LocationLinkResponse
import com.onboarding.domain.entity.CharacterResult
import com.onboarding.domain.entity.LocationLink
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CharacterMapperTest {

    @Test
    fun `location link response transform`() {
        val locationTransformed = characterBaseTest.transform().results.first().location
        assertEquals(expectedLocation, locationTransformed)
    }

    @Test
    fun `character result response transform`() {
        val characterResultTransformed = characterBaseTest.transform().results.first()
        assertEquals(expectedCharacterResult, characterResultTransformed)
    }

    @Test
    fun `info response transform`() {
        val infoTransformed = characterBaseTest.transform().info
        assertEquals(INT_VALUE, infoTransformed.count)
        assertEquals(INT_VALUE, infoTransformed.pages)
        assertEquals(STRING_VALUE, infoTransformed.nextPage)
        assertEquals(STRING_VALUE, infoTransformed.previousPage)
    }

    companion object {

        private const val STRING_VALUE = "value"
        private const val INT_VALUE = 3

        private val locationTest = LocationLinkResponse(
            locationName = STRING_VALUE,
            locationUrl = STRING_VALUE
        )

        private val characterResultTest = CharactersResultResponse(
            id = INT_VALUE,
            name = STRING_VALUE,
            status = STRING_VALUE,
            species = STRING_VALUE,
            type = STRING_VALUE,
            gender = STRING_VALUE,
            origin = locationTest,
            location = locationTest,
            image = STRING_VALUE,
            episodes = listOf(STRING_VALUE),
            characterUrl = STRING_VALUE,
            created = STRING_VALUE
        )

        private val infoTest = InfoResponse(
            count = INT_VALUE,
            pages = INT_VALUE,
            nextPage = STRING_VALUE,
            previousPage = STRING_VALUE
        )

        private val characterBaseTest = CharacterBaseResponse(
            info = infoTest,
            results = listOf(characterResultTest)
        )

        private val expectedLocation = LocationLink(
            locationName = STRING_VALUE,
            locationUrl = STRING_VALUE
        )

        private val expectedCharacterResult = CharacterResult(
            id = INT_VALUE,
            name = STRING_VALUE,
            status = STRING_VALUE,
            species = STRING_VALUE,
            type = STRING_VALUE,
            gender = STRING_VALUE,
            origin = expectedLocation,
            location = expectedLocation,
            image = STRING_VALUE,
            episodes = listOf(STRING_VALUE),
            characterUrl = STRING_VALUE,
            created = STRING_VALUE
        )
    }
}
