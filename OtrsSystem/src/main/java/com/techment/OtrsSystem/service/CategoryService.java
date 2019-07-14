package com.techment.OtrsSystem.service;

import com.techment.OtrsSystem.domain.Category;
import com.techment.OtrsSystem.repository.CategoryRepository;
import com.techment.OtrsSystem.repository.UserRepository;
import com.techment.OtrsSystem.security.JwtProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserService userService;

    public Category createCategory(String category, long id, String token){
        LOGGER.info("Admin attempting create category");
       Category cat=null;
       Optional<Category> categoryOptional=categoryRepository.findByCategoryName(category);
        if(userRepository.existsById(id) &&
                userRepository.findById(id).get().getEmail().equalsIgnoreCase(jwtProvider.getUsername(userService.filterToken(token)))&&
                userRepository.findById(id).get().isFlag()&&!categoryOptional.isPresent()
        ) {
            cat = categoryRepository.save(new Category(category));
        }
        return cat;
    }

    public String deleteCategory(String categoryName, long id, String token){
        LOGGER.info("Admin attempting delete department");
        Optional<Category> category=categoryRepository.findByCategoryName(categoryName);
        String rtn="";
        if(userRepository.existsById(id) &&
                userRepository.findById(id).get().getEmail().equalsIgnoreCase(jwtProvider.getUsername(userService.filterToken(token)))&&
                userRepository.findById(id).get().isFlag()&& category.isPresent()
        ) {
            categoryRepository.delete(category.get());
            rtn="{\"status\":\"success\",\"msg\":\"Category has been deleted successfully\"}";
        }
        else{
            rtn="{\"status\":\"failure\",\"msg\":\"Something went wrong !!\"}";
        }
        return rtn;

    }
}
