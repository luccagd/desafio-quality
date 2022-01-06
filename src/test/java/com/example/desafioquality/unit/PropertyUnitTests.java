package com.example.desafioquality.unit;

import com.example.desafioquality.entity.Property;
import com.example.desafioquality.entity.Room;
import com.example.desafioquality.exception.BusinessException;
import com.example.desafioquality.repository.DistrictRepository;
import com.example.desafioquality.repository.PropertyRepository;
import com.example.desafioquality.service.DistrictService;
import com.example.desafioquality.service.PropertyService;
import com.example.desafioquality.unit.mocks.FakeDistrictObject;
import com.example.desafioquality.unit.mocks.FakePropertyObject;
import com.example.desafioquality.unit.mocks.FakeRoomObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;
import java.util.NoSuchElementException;

public class PropertyUnitTests {
    private PropertyService propertyService;

    @Mock
    private PropertyRepository propertyRepository;

    private DistrictService districtService;

    @Mock
    private DistrictRepository districtRepository;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
        districtService = new DistrictService(districtRepository);
        propertyService = new PropertyService(propertyRepository, districtService);
    }

    @Test
    public void shouldReturnExceptionIfIdIsLessThanOne() {
        Long invalidId = 0L;

        BusinessException exception = Assertions.assertThrows(BusinessException.class,
                () -> propertyService.findById(invalidId));

        Assertions.assertEquals(new BusinessException().getClass(), exception.getClass());
        Assertions.assertEquals("Id should be greater than zero", exception.getMessage());
    }

    @Test
    public void shouldReturnExceptionIfPropertyNotFound() {
        Long validId = 1L;

        Mockito.when(propertyRepository.findById(validId)).thenReturn(null);

        NoSuchElementException exception = Assertions.assertThrows(NoSuchElementException.class,
                () -> propertyService.findById(validId));

        Assertions.assertEquals(new NoSuchElementException().getClass(), exception.getClass());
        Assertions.assertEquals("District not found for the id passed as parameter", exception.getMessage());
    }

    @Test
    public void shouldCallFindByIdFromRepositoryWithCorrectValues() {
        Long validId = 1L;

        Mockito.when(propertyRepository.findById(validId)).thenReturn(FakePropertyObject.makeFakeProperty());

        propertyService.findById(validId);
        Mockito.verify(propertyRepository, Mockito.times(1)).findById(Mockito.eq(validId));
    }

    @Test
    public void shouldReturnCorrectAreaOfTheProperty() {
        Long validId = 1L;

        Mockito.when(propertyRepository.findById(validId)).thenReturn(FakePropertyObject.makeFakeProperty());

        Double propertyArea = propertyService.getArea(validId);
        Assertions.assertEquals(14.0, propertyArea);
    }

    @Test
    public void shouldReturnTheBiggestRoom() {
        Long validId = 1L;
        Room fakeRoom = FakeRoomObject.makeFakeRoom("C", 3.0, 3.0);

        Mockito.when(propertyRepository.findById(validId)).thenReturn(FakePropertyObject.makeFakeProperty());

        Room roomReturned = propertyService.getBiggestRoom(validId);
        Assertions.assertEquals(fakeRoom.calculateRoomArea(), roomReturned.calculateRoomArea());
    }

    @Test
    public void shouldReturnTheCorrectAreaOfEachRoom() {
        Long validId = 1L;
        Property fakeProperty = FakePropertyObject.makeFakeProperty();

        Mockito.when(propertyRepository.findById(validId)).thenReturn(fakeProperty);

        List<Room> roomsReturned = propertyService.getRoomsArea(validId);
        for (int i = 0; i < roomsReturned.size(); i++) {
            Assertions.assertEquals(fakeProperty.getRooms().get(i).calculateRoomArea(),
                    roomsReturned.get(i).calculateRoomArea());
        }
    }

    @Test
    public void shouldSavePropertySucessfully() {
        Long validId = 1L;

        Mockito.when(districtRepository.findById(validId)).thenReturn(FakeDistrictObject.makeFakeDistrict());

        assertDoesNotThrow(() -> propertyService.save(FakePropertyObject.makeFakeProperty()));
    }
}
