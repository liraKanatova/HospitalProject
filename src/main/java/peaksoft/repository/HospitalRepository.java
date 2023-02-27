package peaksoft.repository;

import peaksoft.model.Hospital;

import java.util.List;

public interface HospitalRepository {
    Hospital save(Hospital hospital);



    List<Hospital> getAllHospitals();



    Hospital getHospitalById(Long id);



    void deleteHospital(Long id);



    void updateHospital( Long id,Hospital updatedHospital);

    List<Hospital> search(String search);

}
