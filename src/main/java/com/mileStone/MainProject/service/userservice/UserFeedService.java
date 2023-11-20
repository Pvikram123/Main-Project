package com.mileStone.MainProject.service.userservice;


import com.mileStone.MainProject.models.usermodel.User;
import com.mileStone.MainProject.models.usermodel.UserFeed;
import com.mileStone.MainProject.repository.userrepository.UserFeedRepository;
import com.mileStone.MainProject.repository.userrepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@Service
public class UserFeedService {
    @Autowired
    UserFeedRepository userFeedRepository;
    @Autowired
    UserRepository userRepository;

    public String saveData(MultipartFile file, String title, String description, String category, String visibility, String tags, String token) throws IOException {
        UserFeed saveAll = new UserFeed();
        saveAll.setImageData(file.getBytes());
        User userId = userRepository.findByTokenUpdate(token);
        saveAll.setDescription(description);
        saveAll.setCategory(category);
        saveAll.setUserId(userId.getId());
        saveAll.setTags(tags);
        saveAll.setTitle(title);
        saveAll.setVisibility(visibility);
        userFeedRepository.save(saveAll);
        return ("image is up UpLoaded");
    }

    public List<UserFeed> gettingAllData() {
        return userFeedRepository.findAll();

    }
}

