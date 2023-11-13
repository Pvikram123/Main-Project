package com.mileStone.MainProject.exporter;
import com.mileStone.MainProject.models.usermodel.User;
import com.mileStone.MainProject.repository.userrepository.UserRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
@Service
public class CSVImport {

    @Autowired
    UserRepository userRepository;

    public Object demo(String path) {
        String csvFilePath =path ;

        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            String[] header = reader.readNext();

            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                User user = createUserFromCSV(header, nextLine);
                userRepository.save(user);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return ("data is uploaded");
    }

    private User createUserFromCSV(String[] header, String[] values) {
        User user = new User();
        for (int i = 0; i < header.length; i++) {
            switch (header[i].toLowerCase()) {
                case "firstname":
                    user.setFirstName(values[i]);
                    break;
                case "lastname":
                    user.setLastName(values[i]);
                    break;
                case "username":
                    user.setUserNameData(values[i]);
                    break;
                case "id":
                    try {
                        user.setId(Long.parseLong(values[i]));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    break;
                case "role":
                    user.setRole(values[i]);
                    break;
                case "tokenupdata":
                    user.setTokenUpdate(values[i]);
                    break;
                case "emailid":
                    user.setEmailId(values[i]);
                    break;
                case "phonenumber":
                    user.setPhoneNumber(values[i]);
                    break;
                case "password":
                    user.setPassword(values[i]);
                    break;
                case "reentrypassword":
                    user.setReEntryPassword(values[i]);
                    break;
            }
        }
        return user;
    }
}


