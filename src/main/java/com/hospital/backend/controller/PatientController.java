package com.hospital.backend.controller;

import com.hospital.backend.model.Patient;
import com.hospital.backend.repository.PatientRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "*")
public class PatientController {

    private final PatientRepository repo;

    public PatientController(PatientRepository repo) {
        this.repo = repo;
    }

    // 🔥 GET ALL
    @GetMapping
    public List<Patient> getAll() {
        return repo.findAll();
    }

    // 🔥 ADD (DUPLICATE STOP)
    @PostMapping
    public Patient add(@RequestBody Patient p) {

        List<Patient> all = repo.findAll();

        for (Patient existing : all) {
            if (existing.getName().equalsIgnoreCase(p.getName()) &&
                existing.getSymptoms().equalsIgnoreCase(p.getSymptoms())) {

                // ❌ duplicate मिला → नया save नहीं करेगा
                return existing;
            }
        }

        return repo.save(p);
    }

    // 🔥 DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }

    // 🔥 UPDATE (Doctor panel use)
    @PutMapping("/{id}")
    public Patient update(@PathVariable Long id, @RequestBody Patient updated) {
        Patient p = repo.findById(id).orElseThrow();

        p.setDisease(updated.getDisease());
        p.setTreatment(updated.getTreatment());
        p.setBill(updated.getBill());

        return repo.save(p);
    }
}