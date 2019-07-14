//package com.techment.OtrsSystem.controller;
//
//import com.techment.OtrsSystem.domain.Features;
//import com.techment.OtrsSystem.domain.User;
//import com.techment.OtrsSystem.repository.GenderRepository;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
//import org.springframework.hateoas.Link;
//import org.springframework.hateoas.mvc.ControllerLinkBuilder;
//import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
//
//@Component
//public class UserAssembler extends ResourceAssemblerSupport<User, UserDto> {
//
//    //Helper to fetch Spring Data Rest Repository links.
//    private RepositoryEntityLinks entityLinks;
//
//    public UserAssembler(RepositoryEntityLinks repositoryEntityLinks) {
//        super(UserController.class, UserDto.class);
//        this.entityLinks = repositoryEntityLinks;
//    }
//
//
//    @Override
//    public UserDto toResource(User user) {
//        ArrayList<String> features = new ArrayList<>();
//
//        for(Features feature : user.getFeatures()){
//            features.add(feature.getFeatureName());
//        }
//
//        UserDto userDto = new UserDto(user.getEmail(), user.getFirstName(), user.getMiddleName(), user.getLastName(),
//                user.getEmployeeId(), user.getPhoneNumber(), user.getWorkingNumber(), user.getExtensionLandline(),
//                user.getLandlineNumber(), features,
//                user.getGender().getGenderName(), user.getDesignation().getDesignationName(),
//                user.getDepartment().getDepartmentName(), user.isFlag());
//
//        // "self" : ".../ratings/{ratingId}"
//        ControllerLinkBuilder ratingLink = linkTo(methodOn(UserController.class).getUserDetails(user.getId()));
//        userDto.add(ratingLink.withSelfRel());
//
//        //"tour" : ".../tours/{tourId}"
//        Link tourLink = entityLinks.linkToSingleResource(GenderRepository.class, user.getGender().getId());
//        userDto.add(tourLink.withRel("gender"));
//
//        return userDto;
//    }
//
//    @Override
//    public List<UserDto> toResources(Iterable<? extends User> entities) {
//        return super.toResources(entities);
//    }
//}
