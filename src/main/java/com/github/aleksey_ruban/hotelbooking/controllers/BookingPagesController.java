package com.github.aleksey_ruban.hotelbooking.controllers;

import com.github.aleksey_ruban.hotelbooking.entity.ExtendedRoomConfiguration;
import com.github.aleksey_ruban.hotelbooking.helpers.ListHelper;
import com.github.aleksey_ruban.hotelbooking.service.ExtendedRoomConfigurationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class BookingPagesController {

    private final ExtendedRoomConfigurationService extendedRoomConfigurationService;

    @GetMapping({"/", "home", "/home/"})
    public String index(Model model) {
        List<ExtendedRoomConfiguration> onMainPageCards = extendedRoomConfigurationService.findAllMainPage();
        List<List<ExtendedRoomConfiguration>> groupedConfigurations = ListHelper.partitionList(onMainPageCards, 3);
        model.addAttribute("extendedRoomConfigurations", groupedConfigurations);
        return "booking/index";
    }

    @GetMapping({"/rooms", "/rooms/"})
    public String rooms(Model model) {
        List<ExtendedRoomConfiguration> onMainPageCards = extendedRoomConfigurationService.findAllMainPage();
        model.addAttribute("extendedRoomConfigurations", onMainPageCards);
        return "booking/rooms";
    }

    @GetMapping({"/room-details", "/room-details/", "/room-details/{id}", "/room-details/{id}/"})
    public String roomDetails(@PathVariable(required = false) Long id, Model model) {
        if (id == null) {
            return "redirect:/rooms";
        }

        Optional<ExtendedRoomConfiguration> optionalExtendedRoomConfiguration = extendedRoomConfigurationService.findById(id);
        if (optionalExtendedRoomConfiguration.isEmpty()) {
            return "redirect:/rooms";
        }

        ExtendedRoomConfiguration exitstingConfiguration = optionalExtendedRoomConfiguration.get();
        model.addAttribute("detailedRoomConfiguration", exitstingConfiguration);

        List<ExtendedRoomConfiguration> onMainPageCards = extendedRoomConfigurationService.findAllMainPage();
        List<List<ExtendedRoomConfiguration>> groupedConfigurations = ListHelper.partitionList(onMainPageCards, 3);
        model.addAttribute("extendedRoomConfigurations", groupedConfigurations);
        return "booking/room-details";
    }

    @GetMapping({"/booking", "/booking/"})
    public String booking(Model model) {
        List<ExtendedRoomConfiguration> onMainPageCards = extendedRoomConfigurationService.finsAllOrderByCoast();
        model.addAttribute("extendedRoomConfigurations", onMainPageCards);
        return "booking/booking";
    }
}
