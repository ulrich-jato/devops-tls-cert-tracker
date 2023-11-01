package com.devops.certtracker.controller;

import com.devops.certtracker.entity.Certificate;
import com.devops.certtracker.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/certificates")
public class CertificateController {
    @Autowired
    private CertificateService certificateService;

    @PostMapping("/info")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Object> getCertificateInfo(@RequestBody Map<String, String> requestBody) {
        String url = requestBody.get("url");
        Certificate certificate = certificateService.getCertificateInfo(url);
        return ResponseEntity.ok(certificate);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Object> addCertificate(@RequestBody Map<String, String> requestBody) {
        String url = requestBody.get("url");
        Certificate certificate = certificateService.retrieveAndSaveCertificate(url);
        return ResponseEntity.ok(certificate);
    }

    @DeleteMapping("delete/{certificateId}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCertificateById(@PathVariable Long certificateId){
        certificateService.deleteCertificateById(certificateId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    ResponseEntity<List<Certificate>> getALLCertificates(){
        List<Certificate> certificates = certificateService.getAllCertificates();
        return ResponseEntity.ok(certificates);
    }

    @GetMapping("get/{certificateId}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    ResponseEntity<Object> getCertificateById(@PathVariable Long certificateId){
        Certificate certificate = certificateService.getCertificateById(certificateId);
        return ResponseEntity.ok(certificate);
    }

}
