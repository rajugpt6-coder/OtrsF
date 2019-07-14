package com.techment.OtrsSystem.service;

import com.techment.OtrsSystem.domain.Department;
import com.techment.OtrsSystem.repository.DepartmentRepository;
import com.techment.OtrsSystem.repository.UserRepository;
import com.techment.OtrsSystem.security.JwtProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private UserService userService;

    public Department createDepartment(String departmentName, long id, String token){
        LOGGER.info("Admin attempting create department");
        Department department=null;
        Optional<Department> departmentOptional=departmentRepository.findByDepartmentName(departmentName);
        if(userRepository.existsById(id) &&
                userRepository.findById(id).get().getEmail().equalsIgnoreCase(jwtProvider.getUsername(userService.filterToken(token)))&&
                userRepository.findById(id).get().isFlag()&& !departmentOptional.isPresent()
        ) {
            department = departmentRepository.save(new Department(departmentName));
        }
        return department;

    }


    public String deleteDepartment(String departmentName, long id, String token){
        LOGGER.info("Admin attempting delete department");
        Optional<Department> departmentOptional=departmentRepository.findByDepartmentName(departmentName);
        String rtn="";
        if(userRepository.existsById(id) &&
                userRepository.findById(id).get().getEmail().equalsIgnoreCase(jwtProvider.getUsername(userService.filterToken(token)))&&
                userRepository.findById(id).get().isFlag()&& departmentOptional.isPresent()
        ) {
             departmentRepository.delete(departmentOptional.get());
             rtn="{\"status\":\"success\",\"msg\":\"department has been deleted successfully\"}";
        }
        else{
            rtn="{\"status\":\"failure\",\"msg\":\"Something went wrong !!\"}";
        }
        return rtn;

    }
}

