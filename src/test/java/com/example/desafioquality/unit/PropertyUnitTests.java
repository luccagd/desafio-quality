package com.example.desafioquality.unit;

import com.example.desafioquality.entity.Property;
import com.example.desafioquality.entity.Room;
import com.example.desafioquality.exception.BusinessException;
import com.example.desafioquality.repository.PropertyRepository;
import com.example.desafioquality.service.PropertyService;
import com.example.desafioquality.unit.mocks.FakePropertyObject;
import com.example.desafioquality.unit.mocks.FakeRoomObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.NoSuchElementException;

public class PropertyUnitTests {

    @Test
    public void shouldReturnExceptionIfIdIsLessThanOne() {
        Long invalidId = 0L;

        PropertyService propertyService = new PropertyService(new PropertyRepository());

        BusinessException exception = Assertions.assertThrows(BusinessException.class, () -> propertyService.findById(invalidId));

        Assertions.assertEquals(new BusinessException().getClass(), exception.getClass());
        Assertions.assertEquals("Id should be greater than zero", exception.getMessage());
    }

    @Test
    public void shouldReturnExceptionIfPropertyNotFound() {
        Long validId = 1L;

        PropertyRepository mockRepository = Mockito.mock(PropertyRepository.class);
        Mockito.when(mockRepository.findById(validId)).thenReturn(null);
        PropertyService propertyService = new PropertyService(mockRepository);

        NoSuchElementException exception = Assertions.assertThrows(NoSuchElementException.class, () -> propertyService.findById(validId));

        Assertions.assertEquals(new NoSuchElementException().getClass(), exception.getClass());
        Assertions.assertEquals("District not found for the id passed as parameter", exception.getMessage());
    }

    @Test
    public void shouldCallFindByIdFromRepositoryWithCorrectValues() {
        Long validId = 1L;

        PropertyRepository mockRepository = Mockito.mock(PropertyRepository.class);
        Mockito.when(mockRepository.findById(validId)).thenReturn(FakePropertyObject.makeFakeProperty());
        PropertyService fakePropertyService = new PropertyService(mockRepository);

        fakePropertyService.findById(validId);
        Mockito.verify(mockRepository, Mockito.times(1)).findById(Mockito.eq(validId));
    }

    @Test
    public void shouldReturnCorrectAreaOfTheProperty() {
        Long validId = 1L;

        PropertyRepository mockRepository = Mockito.mock(PropertyRepository.class);
        Mockito.when(mockRepository.findById(validId)).thenReturn(FakePropertyObject.makeFakeProperty());
        PropertyService propertyService = new PropertyService(mockRepository);

        Double propertyArea = propertyService.getArea(validId);
        Assertions.assertEquals(14.0, propertyArea);
    }

    @Test
    public void shouldReturnTheBiggestRoom() {
        Long validId = 1L;
        Room fakeRoom = FakeRoomObject.makeFakeRoom("C", 3.0, 3.0);

        PropertyRepository mockRepository = Mockito.mock(PropertyRepository.class);
        Mockito.when(mockRepository.findById(validId)).thenReturn(FakePropertyObject.makeFakeProperty());
        PropertyService propertyService = new PropertyService(mockRepository);

        Room roomReturned = propertyService.getBiggestRoom(validId);
        Assertions.assertEquals(fakeRoom.getArea(), roomReturned.getArea());
    }

    @Test
    public void shouldReturnTheCorrectAreaOfEachRoom() {
        Long validId = 1L;
        Property fakeProperty = FakePropertyObject.makeFakeProperty();
        
        PropertyRepository mockRepository = Mockito.mock(PropertyRepository.class);
        Mockito.when(mockRepository.findById(validId)).thenReturn(fakeProperty);
        PropertyService propertyService = new PropertyService(mockRepository);

        List<Room> roomsReturned = propertyService.getRoomsArea(validId);

        for (int i = 0; i < roomsReturned.size(); i++) {
            Assertions.assertEquals(fakeProperty.getRooms().get(i).getArea(), roomsReturned.get(i).getArea());
        }
    }

}
