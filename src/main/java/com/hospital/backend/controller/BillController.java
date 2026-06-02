package com.hospital.backend.controller;

import com.hospital.backend.model.Bill;
import com.hospital.backend.repository.BillRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
@CrossOrigin
public class BillController {

    private final BillRepository repo;

    public BillController(BillRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Bill createBill(@RequestBody Bill bill) {
        return repo.save(bill);
    }

    @GetMapping
    public List<Bill> getBills() {
        return repo.findAll();
    }
}