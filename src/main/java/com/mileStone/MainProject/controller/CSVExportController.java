package com.mileStone.MainProject.controller;

import com.itextpdf.text.DocumentException;
import com.mileStone.MainProject.exporter.CSVExport;
import com.mileStone.MainProject.exporter.CSVImport;
import com.mileStone.MainProject.exporter.PdfExport;
import com.mileStone.MainProject.repository.userrepository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/CSV")
public class CSVExportController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CSVImport csvImport;
    @GetMapping("/export/csv")
     public void exportCsv(HttpServletResponse response) throws IOException {
        List<?> data = new ArrayList<>(userRepository.findAll());
        CSVExport.export((List<User>) data, response);
    }
    @GetMapping("/export/pdf")
    public void exportPdf(HttpServletResponse response) throws IOException, DocumentException {
        List<?> feeds = userRepository.findAll();
        PdfExport.export((List<com.mileStone.MainProject.models.usermodel.User>) feeds, response);
    }
    @GetMapping
    public void importCsv(HttpServletResponse response) throws IOException {
        List<?> data = new ArrayList<>(userRepository.findAll());
        CSVExport.export((List<User>) data, response);
    }
    @PostMapping
    public Object demo2(@RequestBody String path){
       return   csvImport.demo(path);
    }
}

