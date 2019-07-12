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
    private PasswordEncoder passwordEncoder;

    public Optional<User> signup(String email, String password, String firstName, String middleName, String lastName,
                                 String employeeId, String phoneNumber, String workingNumber, String extensionLandline,
                                 String landlineNumber, String genderName, String designation, String department){
        LOGGER.info("New user attempting to sign up");
        Optional<User> user = Optional.empty();


        if (!userRepository.findByEmail(email).isPresent()) {
//            Optional<Features> features =
            Optional<Gender> gender = genderRepository.findByGenderName(genderName);
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
                    firstName+" "+lastName,
                    Arrays.asList(featuresRepository.findByFeatureName("F_LOGIN").get())

            )));
        }
        return user;
    }

}
