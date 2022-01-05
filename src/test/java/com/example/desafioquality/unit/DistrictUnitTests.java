package com.example.desafioquality.unit;

import com.example.desafioquality.unit.mocks.FakeDistrictObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class DistrictUnitTests {

    @Test
    public void shouldReturnExceptionIfIdIsLessThanOne() {
        Long invalidId = 0L;

        FakeDistrictService fakeDistrictService = new FakeDistrictService(new FakeDistrictRepository());

        ArrayIndexOutOfBoundsException exception = Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> fakeDistrictService.findById(invalidId));
        Assertions.assertEquals(new ArrayIndexOutOfBoundsException().getClass(), exception.getClass());
    }

    @Test
    public void shouldReturnExceptionIfPropertyNotFound() {
        Long validId = 1L;

        FakeDistrictRepository mockRepository = Mockito.mock(FakeDistrictRepository.class);
        Mockito.when(mockRepository.findById(validId)).thenReturn(null);
        FakeDistrictService fakeDistrictService = new FakeDistrictService(mockRepository);

        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> fakeDistrictService.findById(validId));
        Assertions.assertEquals(new ArithmeticException().getClass(), exception.getClass());
    }

    @Test
    public void shouldCallFindByIdFromRepositoryWithCorrectValues() {
        Long validId = 1L;

        FakeDistrictRepository mockRepository = Mockito.mock(FakeDistrictRepository.class);
        Mockito.when(mockRepository.findById(validId)).thenCallRealMethod();
        FakeDistrictService fakeDistrictService = new FakeDistrictService(mockRepository);

        fakeDistrictService.findById(validId);
        Mockito.verify(mockRepository, Mockito.times(1)).findById(Mockito.eq(validId));
    }

    @Test
    public void shouldReturnTheExistentDistrict() {
        Long validId = 1L;

        FakeDistrictService fakeDistrictService = new FakeDistrictService(new FakeDistrictRepository());
        FakeDistrict fakeDistrict = FakeDistrictObject.makeFakeDistrict();

        Assertions.assertEquals(fakeDistrict, fakeDistrictService.findById(validId));
    }
}
