package com.mileStone.MainProject.exporter;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.mileStone.MainProject.models.usermodel.User;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Service
public class PdfExport {
        public static void export(List<User> users, HttpServletResponse response) throws IOException, DocumentException {
            response.setContentType("application/pdf");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=data.pdf");

            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());

            document.open();

            for (User user : users) {
                document.add(new Paragraph("Name: " + user.getUsername()));
                document.add(new Paragraph("Email: " + user.getEmailId()));
                document.add(new Paragraph("Role: "+user.getRole()));
                document.add(new Paragraph("PhoneNumber: "+user.getPhoneNumber()));
                document.add(new Paragraph("Password: "+user.getPassword()));
                document.add(new Paragraph("\n"));
            }

            document.close();
        }
    }

