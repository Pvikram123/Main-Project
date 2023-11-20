package com.mileStone.MainProject.userDataTest;

import com.mileStone.MainProject.config.JwtConfigurationForUser;
import com.mileStone.MainProject.dtos.userdtos.UserNameDTOs;
import com.mileStone.MainProject.models.usermodel.User;
import com.mileStone.MainProject.models.usermodel.UserRequest;
import com.mileStone.MainProject.repository.userrepository.UserRepository;
import com.mileStone.MainProject.repository.userrepository.UserRequestRepository;
import com.mileStone.MainProject.service.userservice.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
 class UserDataTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    JwtConfigurationForUser jwtConfigurationForUser;
    @Mock
    UserRequestRepository userRequestRepository;
    @InjectMocks
    private UserService userService;

    @Test
     void testUserDataRegistrationSuccess() {
        User user = new User();
        user.setUserNameData("newUser");
        user.setEmailId("newuser@example.com");
        user.setPassword("password");
        user.setRole("ROLE_USER");
        when(userRepository.findByUserNameDataIgnoreCase("newUser")).thenReturn(null);
        Object result = userService.data(user);
        assertEquals("its done", result);
    }
    @Test
     void testUserDataRegistrationUsernameTaken() {
        User user = new User();
        user.setUserNameData("existingUser");
        user.setEmailId("existinguser@example.com");
        user.setPassword("password");
        user.setRole("ROLE_USER");
        when(userRepository.findByUserNameDataIgnoreCase("existingUser")).thenReturn(user);
        Object result = userService.data(user);
        verify(userRepository, times(1)).findByUserNameDataIgnoreCase("existingUser");
        verify(userRepository, never()).save(any(User.class));
        assertEquals("username is already there", result);
    }
    @Test
     void testUserData(){
        System.out.println(userRepository.findAll());
        System.out.println(userRepository.findByUserNameData("existingUser"));
    }
    @Test
void notEnablingToken() throws Exception {
        User user = new User();
        user.setUserNameData("existingUser");
        user.setEmailId("existinguser@example.com");
        user.setPassword("password");
        user.setRole("ROLE_USER");
        when(userRepository.findByTokenUpdate("invalidToken")).thenReturn(null);
        List<Object> data=userService.getUsernames("invalidToken");
        assertEquals(Collections.singletonList("the token is not valid"),data);
    }
    @Test
     void enablingDemo() throws Exception {
        String validToken = "validToken";
        User user = new User();user.setId(0);user.setUserNameData("null34");user.setFirstName("firstName");user.setLastName("lastName");user.setEmailId("emailId=null");user.setPhoneNumber("phoneNumber=null");user.setPassword("password=null");
        User user1 = new User();user1.setId(1);user1.setUserNameData("null344");user1.setFirstName("firstName");user1.setLastName("lastName");user1.setEmailId("emailId=null");user1.setPhoneNumber("phoneNumber=null");user1.setPassword("password=null");
        when(userRepository.findByTokenUpdate(validToken)).thenReturn(user);
        when(userRepository.findAll()).thenReturn(Arrays.asList(user, user1));
        List<Object> result = userService.getUsernames(validToken);
        assertEquals(Arrays.asList("null34","null344"), result);
    }
    @Test
    void  login_NotEnableUserName(){
        User first=new User();
        String token="tokenIsAvailable";
        first.setUserNameData("joker");
        when(userRepository.findByUserNameData(first.getUserNameData())).thenReturn(null);
        Object result=userService.login(first);
        assertEquals("username is not valid",result);
    }
    @Test
    void  login_Enable(){
        User first=new User();
        first.setUserNameData("joker");
        first.setPassword("dummy");
        User existData=new User();
        existData.setUserNameData("joker");
        existData.setPassword("dummy");
       when(userRepository.findByUserNameData(first.getUserNameData())).thenReturn(existData);
       when(jwtConfigurationForUser.creatingJWT(first)).thenReturn("jwtAuth");
        HttpHeaders result= (HttpHeaders) userService.login(first);
        assertEquals("jwtAuth",result.getFirst("Authorization"));
    }
    @Test
    void  login_PasswordIsWrong(){
        User first=new User();
        String token="tokenIsAvailable";
        first.setUserNameData("joker");
        User existData=new User();
        existData.setUserNameData("joker");
        existData.setPassword("Dai");
        when(userRepository.findByUserNameData(first.getUserNameData())).thenReturn(existData);
        Object result=userService.login(first);
        assertEquals("password is wrong",result);
    }
    @Test
    void friend_request(){
        String token="itsAvailable";
    UserNameDTOs data=new UserNameDTOs();
    data.setId(4L);
    data.setUserName("demo1");
    data.setPassword("exist");
    User existData = new User();
    existData.setId(3L);
    existData.setUserNameData("existingUser");
    existData.setEmailId("existinguser@example.com");
    existData.setPassword("password");
    existData.setRole("ROLE_USER");
    when(userRepository.findByTokenUpdate(token)).thenReturn(existData);
    Object result=userService.friendRequest(token,data);
    assertEquals("Request is Send",result);
    }
    @Test
    void accepting_request(){
        String token="itsAvailable";
        UserNameDTOs data=new UserNameDTOs();
        data.setId(4L);
        data.setUserName("test1");
        data.setPassword("exist");
        User user1 = new User();user1.setId(1);user1.setUserNameData("demo1");user1.setFirstName("firstName");user1.setLastName("lastName");user1.setEmailId("emailId=null");user1.setPhoneNumber("phoneNumber=null");user1.setPassword("password=null");
        UserRequest data1=new UserRequest();data1.setRequestCount(1L);data1.setGivenByName("demo1");data1.setFollow("test1");data1.setEnable(false);
        UserRequest data2=new UserRequest();data2.setRequestCount(2L);data2.setGivenByName("test1");data2.setFollow("demo1");data2.setEnable(false);
        UserRequest data3=new UserRequest();data3.setRequestCount(3L);data3.setGivenByName("test1");data3.setFollow("demo2");data3.setEnable(false);
        when(userRepository.findByTokenUpdate(token)).thenReturn(user1);
        when(userRequestRepository.findAllByFollow(user1.getUserNameData())).thenReturn(Collections.singletonList(data2));
        Object result=userService.acceptFriendRequest(data,token);
        assertTrue(result instanceof List);
        List<UserRequest> resultList = (List<UserRequest>) result;
        assertFalse(resultList.isEmpty());
        assertTrue(resultList.stream().anyMatch(UserRequest::isEnable));
    }
    @Test
    void accepting_request_when_token_null(){
        String token="itsAvailable";
        UserNameDTOs data=new UserNameDTOs();
        data.setId(4L);
        data.setUserName("test1");
        data.setPassword("exist");
        when(userRepository.findByTokenUpdate(token)).thenReturn(null);
        Object result=userService.acceptFriendRequest(data,token);
        assertEquals("go and login first(or)user name is valid",result);
    }
    @Test
    void accepting_request_when_request_null(){
        String token="itsAvailable";
        UserNameDTOs data=new UserNameDTOs();
        data.setId(4L);
        data.setUserName("test1");
        data.setPassword("exist");
        User user1 = new User();user1.setId(1);user1.setUserNameData("demo1");user1.setFirstName("firstName");user1.setLastName("lastName");user1.setEmailId("emailId=null");user1.setPhoneNumber("phoneNumber=null");user1.setPassword("password=null");
        when(userRepository.findByTokenUpdate(token)).thenReturn(user1);
        when(userRequestRepository.findAllByFollow(user1.getUserNameData())).thenReturn(null);
        Object result=userService.acceptFriendRequest(data,token);
        assertEquals("There is no friend request",result);
    }
    @Test
    void  rejecting_friendRequest_withValid_token(){
        String token="itsAvailable";
        UserNameDTOs data=new UserNameDTOs();
        data.setId(4L);
        data.setUserName("demo1");
        data.setPassword("exist");
        User user1 = new User();user1.setId(1);user1.setUserNameData("demo1");user1.setFirstName("firstName");user1.setLastName("lastName");user1.setEmailId("emailId=null");user1.setPhoneNumber("phoneNumber=null");user1.setPassword("password=null");
        UserRequest data1=new UserRequest();data1.setRequestCount(1L);data1.setGivenByName("demo1");data1.setFollow("test1");data1.setEnable(false);
        UserRequest data2=new UserRequest();data2.setRequestCount(2L);data2.setGivenByName("test1");data2.setFollow("demo1");data2.setEnable(false);
        UserRequest data3=new UserRequest();data3.setRequestCount(3L);data3.setGivenByName("test1");data3.setFollow("demo2");data3.setEnable(false);

        when(userRepository.findByTokenUpdate(token)).thenReturn(user1);
        when(userRequestRepository.findAllByFollow(user1.getUserNameData())).thenReturn(Arrays.asList(data2,data3));
        Object result=userService.rejectFriendRequest(data,token);
        assertEquals("its deleted",result);
    }
    @Test
    void  rejecting_friendRequest_withNotValid_Username(){
        String token="itsAvailable";
        UserNameDTOs data=new UserNameDTOs();
        data.setId(4L);
        data.setUserName("demo3");
        data.setPassword("exist");
        User user1 = new User();user1.setId(1);user1.setUserNameData("demo1");user1.setFirstName("firstName");user1.setLastName("lastName");user1.setEmailId("emailId=null");user1.setPhoneNumber("phoneNumber=null");user1.setPassword("password=null");
        UserRequest data1=new UserRequest();data1.setRequestCount(1L);data1.setGivenByName("demo1");data1.setFollow("test1");data1.setEnable(false);
        UserRequest data2=new UserRequest();data2.setRequestCount(2L);data2.setGivenByName("test1");data2.setFollow("demo1");data2.setEnable(false);
        UserRequest data3=new UserRequest();data3.setRequestCount(3L);data3.setGivenByName("test1");data3.setFollow("demo2");data3.setEnable(false);

        when(userRepository.findByTokenUpdate(token)).thenReturn(null);
        Object result=userService.rejectFriendRequest(data,token);
        assertEquals("friend request is not there",result);

        }
}
