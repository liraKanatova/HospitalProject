package peaksoft.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.model.Hospital;
import peaksoft.repository.HospitalRepository;
import peaksoft.service.HospitalService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService {
    private final HospitalRepository hospitalRepository;
    @Override
    public void save(Hospital hospital) {
        hospitalRepository.save(hospital);

    }

    @Override
    public List<Hospital> getAllHospitals() {
        return hospitalRepository.getAllHospitals();
    }

    @Override
    public Hospital getHospitalById(Long id) {
        return hospitalRepository.getHospitalById(id);
    }

    @Override
    public void deleteHospital(Long id) {
hospitalRepository.deleteHospital(id);
    }

    @Override
    public void updateHospital(Long id, Hospital updatedHospital) {
hospitalRepository.updateHospital(id, updatedHospital);
    }
}
