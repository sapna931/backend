package com.hospital.backend.controller;

import com.hospital.backend.model.Patient;
import com.hospital.backend.repository.PatientRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "*") // 🔥 allow all (render + localhost)
public class PatientController {

    private final PatientRepository repo;

    public PatientController(PatientRepository repo) {
        this.repo = repo;
    }

    // ✅ ADD PATIENT
    @PostMapping
    public Patient addPatient(@RequestBody Patient patient) {
        return repo.save(patient);
    }

    // ✅ GET ALL PATIENTS
    @GetMapping
    public List<Patient> getPatients() {
        return repo.findAll();
    }

    // ✅ DELETE PATIENT
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {
        repo.deleteById(id);
    }
}