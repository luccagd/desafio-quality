package com.example.desafioquality.unit;

public class FakeDistrictService {

    private FakeDistrictRepository fakeDistrictRepository;

    public FakeDistrictService(FakeDistrictRepository fakeDistrictRepository) {
        this.fakeDistrictRepository = fakeDistrictRepository;
    }

    public FakeDistrict findById(Long id) {
        if (id < 1) {
            throw new ArrayIndexOutOfBoundsException();
        }

        FakeDistrict fakeDistrict = fakeDistrictRepository.findById(id);
        if (fakeDistrict == null) {
            throw new ArithmeticException();
        }

        return fakeDistrict;
    }
}
