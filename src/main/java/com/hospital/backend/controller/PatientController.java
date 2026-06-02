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

    @GetMapping
    public List<Patient> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Patient add(@RequestBody Patient p) {
        return repo.save(p);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }

    @PutMapping("/{id}")
    public Patient update(@PathVariable Long id, @RequestBody Patient updated) {
        Patient p = repo.findById(id).orElseThrow();

        p.setDisease(updated.getDisease());
        p.setTreatment(updated.getTreatment());
        p.setBill(updated.getBill());

        return repo.save(p);
    }
}