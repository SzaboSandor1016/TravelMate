package com.example.features.findcustom.data

import com.example.features.findcustom.data.repositories.CustomPlaceRepositoryImpl
import com.example.features.findcustom.domain.models.AddressCustomPlaceDomainModel
import com.example.features.findcustom.domain.models.CoordinatesCustomPlaceDomainModel
import com.example.features.findcustom.domain.models.CustomPlaceInfoCustomPlaceDomainModel
import com.example.features.findcustom.domain.models.CustomPlaceMapDataCustomPlaceDomainModel
import com.example.features.findcustom.domain.models.PlaceCustomPlaceDomainModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class CustomPlaceRepositoryTest {


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun setCustomPlaceTest() = runTest {

        val testCustomPlaceRepositoryImpl = CustomPlaceRepositoryImpl()

        val testCustomPlace = PlaceCustomPlaceDomainModel.CustomPlace(
            uUID = "gfh4566",
            name = "test",
            address = AddressCustomPlaceDomainModel(
                "testCity",
                "testStreet",
                "0"
            ),
            coordinates = CoordinatesCustomPlaceDomainModel(
                23.5,
                12.1
            ),
            category = "test"
        )

        val testCustomPlace1 = PlaceCustomPlaceDomainModel.CustomPlace(
            uUID = "43fdg332",
            name = "test1",
            address = AddressCustomPlaceDomainModel(
                "testCity1",
                "testStreet1",
                "0"
            ),
            coordinates = CoordinatesCustomPlaceDomainModel(
                22.4,
                11.1
            ),
            category = "test2"
        )

        val mapDataEmissions = mutableListOf<CustomPlaceMapDataCustomPlaceDomainModel>()
        val infoDataEmissions = mutableListOf<CustomPlaceInfoCustomPlaceDomainModel>()

        val mapJob = launch { testCustomPlaceRepositoryImpl.getCustomPlaceMapData().toList(mapDataEmissions) }
        val infoJob = launch { testCustomPlaceRepositoryImpl.getCustomPlaceInfo().toList(infoDataEmissions) }

        testCustomPlaceRepositoryImpl.setCustomPlace(testCustomPlace)
        testCustomPlaceRepositoryImpl.resetCustomPlace()
        testCustomPlaceRepositoryImpl.setCustomPlace(testCustomPlace1)

        advanceUntilIdle() // Waits for all coroutines to finish scheduled work

        mapJob.cancel()
        infoJob.cancel()

        val testInfoData = infoDataEmissions.first()
        val testMapData = mapDataEmissions.first()

        assertTrue(testInfoData is CustomPlaceInfoCustomPlaceDomainModel.CustomPlace)
        assertTrue(testMapData is CustomPlaceMapDataCustomPlaceDomainModel.CustomPlace)

        if (testInfoData is CustomPlaceInfoCustomPlaceDomainModel.CustomPlace) {

            assertTrue(testInfoData.uUID == "gfh4566")
            assertTrue(testInfoData.name == "test")
            assertTrue(testInfoData.address.city == "testCity")
        }
        if (testMapData is CustomPlaceMapDataCustomPlaceDomainModel.CustomPlace) {

            assertTrue(testMapData.uuid == "gfh4566")
            assertTrue(testMapData.name == "test")
            assertTrue(testMapData.coordinates.latitude == 23.5)
            assertTrue(testMapData.coordinates.longitude == 12.1)
        }

        val testInfoData1 = infoDataEmissions[1]
        val testMapData1 = mapDataEmissions[1]

        assertTrue(testInfoData1 is CustomPlaceInfoCustomPlaceDomainModel.Default)
        assertTrue(testMapData1 is CustomPlaceMapDataCustomPlaceDomainModel.Default)

        val testInfoData2 = infoDataEmissions.last()
        val testMapData2 = mapDataEmissions.last()

        assertTrue(testInfoData2 is CustomPlaceInfoCustomPlaceDomainModel.CustomPlace)
        assertTrue(testMapData2 is CustomPlaceMapDataCustomPlaceDomainModel.CustomPlace)

        if (testInfoData2 is CustomPlaceInfoCustomPlaceDomainModel.CustomPlace) {

            assertTrue(testInfoData2.uUID == "43fdg332")
            assertTrue(testInfoData2.name == "test1")
            assertTrue(testInfoData2.address.city == "testCity1")
        }
        if (testMapData2 is CustomPlaceMapDataCustomPlaceDomainModel.CustomPlace) {

            assertTrue(testMapData2.uuid == "43fdg332")
            assertTrue(testMapData2.name == "test1")
            assertTrue(testMapData2.coordinates.latitude == 22.4)
            assertTrue(testMapData2.coordinates.longitude == 11.1)
        }
    }
}