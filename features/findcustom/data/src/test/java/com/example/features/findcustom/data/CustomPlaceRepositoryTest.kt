package com.example.features.findcustom.data

import com.example.features.findcustom.data.repositories.CustomPlaceRepositoryImpl
import com.example.features.findcustom.domain.models.AddressCustomPlaceDomainModel
import com.example.features.findcustom.domain.models.CoordinatesCustomPlaceDomainModel
import com.example.features.findcustom.domain.models.CustomPlaceInfoCustomPlaceDomainModel
import com.example.features.findcustom.domain.models.CustomPlaceMapDataCustomPlaceDomainModel
import com.example.features.findcustom.domain.models.PlaceCustomPlaceDomainModel
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class CustomPlaceRepositoryTest {


    @Test fun setCustomPlaceTest() = runTest {
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
            ), category = "test"
        )
        testCustomPlaceRepositoryImpl.setCustomPlace(testCustomPlace)

        val mapDataEmissions = testCustomPlaceRepositoryImpl.getCustomPlaceMapData().take(1).toList()
        val infoDataEmissions = testCustomPlaceRepositoryImpl.getCustomPlaceInfo().take(1).toList()

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
    }
}