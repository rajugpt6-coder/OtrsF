package com.techment.OtrsSystem.service;

import com.techment.OtrsSystem.domain.Designation;
import com.techment.OtrsSystem.domain.Features;
import com.techment.OtrsSystem.domain.Gender;
import com.techment.OtrsSystem.domain.User;
import com.techment.OtrsSystem.repository.*;
import com.techment.OtrsSystem.security.JwtProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private static final boolean FLAG = true;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GenderRepository genderRepository;

    @Autowired
    private FeaturesRepository featuresRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DesignationRepository designationRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender javaMailSender;


    public Optional<User> signup(String email, String firstName, String middleName, String lastName,
                                 String employeeId, String phoneNumber, String workingNumber, String extensionLandline,
                                 String landlineNumber, String genderName, String designation, String department){
        LOGGER.info("New user attempting to sign up");
        Optional<User> user = Optional.empty();
        String password = GenerateSecurePassword.generatePassword(10);
        LOGGER.info(password);


        if (!userRepository.findByEmail(email).isPresent()) {
            try {
//            Optional<Features> features =
                Optional<Gender> gender = genderRepository.findByGenderName(genderName);
                sendEmail(email, password);
                user = Optional.of(userRepository.save(new User(email,
                        passwordEncoder.encode(password),
                        firstName,
                        middleName,
                        lastName,
                        employeeId,
                        phoneNumber,
                        workingNumber,
                        extensionLandline,
                        landlineNumber,
                        genderRepository.findByGenderName(genderName).get(),
                        new Designation(designation),
                        departmentRepository.findByDepartmentName(department).get(),
                        FLAG,
                        Timestamp.valueOf(LocalDateTime.now()),
                        firstName + " " + lastName,
                        Arrays.asList(featuresRepository.findByFeatureName("F_LOGIN").get())

                )));
            } catch (MailException mailException) {
                return null;
            }
        }
        return user;
    }

    public  Optional<String> signin(String username, String password) {
        LOGGER.info("New user attempting to sign in");
        String token = "";
        Optional<User> user = userRepository.findByEmail(username);
        String rtn="";

        if (user.isPresent()) {
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
                token = jwtProvider.createToken(username, user.get().getFeatures());
                rtn= "{\"status\":\"success\"," +
                        "\"id\":" +"\""+user.get().getId()+"\""+
                        ",\"email\":" +"\""+user.get().getEmail()+"\""+
                        ",\"phoneNo\":" +"\""+user.get().getPhoneNumber()+"\""+
                        ",\"feature\":" +"\""+user.get().getFeatures().get(0).getFeatureName()+"\""+
                        ",\"token\":" +"\""+token+"\""+
                        '}';

            } catch (AuthenticationException e){
                rtn= "{\"status\":\"failure\",\"msg\":\"Incorrect user name or password !!\"}";
            }
        }
        else{
            rtn= "{\"status\":\"failure\",\"msg\":\"please sign up !!\"}";
        }
        return Optional.of(rtn);
    }

    public String forgotPassword(String email) {
        LOGGER.info("User forgot Password");
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()) {

            String password = GenerateSecurePassword.generatePassword(10);
            //send password to mail
            try {
                sendEmail(user.get().getEmail(), password);
                user.get().setPassword(passwordEncoder.encode(password));
                return "{\"status\":\"success\",\"msg\":\"New password sent to your email. Please check !!\"}";
            }catch (MailException mailException) {
                return "{\"status\":\"failure\",\"msg\":\"Unable to send mail !!\"}";
            }
        }
        return "{\"status\":\"failure\",\"msg\":\"Incorrect email !!\"}";
    }

    public String updatePassword(long id, String oldPassword, String newPassword, String token) {
        if (userRepository.existsById(id) &&
                userRepository.findById(id).get().getEmail().equalsIgnoreCase(jwtProvider.getUsername(filterToken(token))) &&
               userRepository.findById(id).get().getPassword().equals(passwordEncoder.encode(oldPassword))) {
            User user = userRepository.findById(id).get();
            user.setPassword(passwordEncoder.encode(newPassword));
            return "{\"status\":\"success\",\"msg\":\"Password updates successfully !!\"}";
        }
        return "{\"status\":\"failure\",\"msg\":\"Something went wrong !!\"}";
    }

    public String filterToken(String token) { return token.replace("Bearer", "").trim(); }


    public void sendEmail(String email, String password) throws MailException {

        /*
         * This JavaMailSender Interface is used to send Mail in Spring Boot. This
         * JavaMailSender extends the MailSender Interface which contains send()
         * function. SimpleMailMessage Object is required because send() function uses
         * object of SimpleMailMessage as a Parameter
         */

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
//        mail.setCc(user.getCcMailAddress());

        mail.setSubject("Password");
        mail.setText("YOU HAVE REGISTERED SUCCESSFULLY " +
                "YOUR PASSWORD IS :" +
                password);


        /*
         * This send() contains an Object of SimpleMailMessage as an Parameter
         */
        javaMailSender.send(mail);
    }

}
