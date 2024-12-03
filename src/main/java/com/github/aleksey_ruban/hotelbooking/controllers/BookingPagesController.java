package com.github.aleksey_ruban.hotelbooking.controllers;

import com.github.aleksey_ruban.hotelbooking.entity.ExtendedRoomConfiguration;
import com.github.aleksey_ruban.hotelbooking.helpers.ListHelper;
import com.github.aleksey_ruban.hotelbooking.service.ExtendedRoomConfigurationService;
import jakarta.servlet.ServletContext;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class BookingPagesController {

    private final ExtendedRoomConfigurationService extendedRoomConfigurationService;

    private final SpringTemplateEngine templateEngine;

    private ServletContext servletContext;

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
    public String booking(@RequestParam(name = "loadBooking", required = false, defaultValue = "false") boolean loadBooking, Model model) {
        List<ExtendedRoomConfiguration> allRoomCards = extendedRoomConfigurationService.finsAllOrderByCoast();

        if (loadBooking) {
            model.addAttribute("loadBooking", true);
        }

        model.addAttribute("extendedRoomConfigurations", allRoomCards);
        model.addAttribute("isAuthorized", this.isClientAuthorized());

        return "booking/booking";
    }

    @PostMapping("/render-available-rooms")
    public ResponseEntity<String> renderAvailableRooms(@RequestBody Map<String, Object> requestData) {
        Context context = new Context();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        String startDateString = (String) requestData.get("startDate");
        String endDateString = (String) requestData.get("endDate");
        Integer peopleCount = Integer.parseInt((String) requestData.get("peopleCount"));

        LocalDate startDate = LocalDate.parse(startDateString, formatter);
        LocalDate endDate = LocalDate.parse(endDateString, formatter);

        List<ExtendedRoomConfiguration> allRoomCards = extendedRoomConfigurationService.finsAllOrderByCoast();

        context.setVariable("extendedRoomConfigurations", allRoomCards);

        String renderedHtml = templateEngine.process("includes/booking-select-room-panel", context);

        return ResponseEntity.ok(renderedHtml);
    }

    @PostMapping("/render-booking-details")
    public ResponseEntity<String> renderBookingDetails(@RequestBody Map<String, Object> requestData) {
        Context context = new Context();

        Long roomId = Long.parseLong((String) requestData.get("roomId"));

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        String startDateString = (String) requestData.get("startDate");
        String endDateString = (String) requestData.get("endDate");
        Integer peopleCount = Integer.parseInt((String) requestData.get("peopleCount"));

        LocalDate startDate = LocalDate.parse(startDateString, formatter);
        LocalDate endDate = LocalDate.parse(endDateString, formatter);

        Optional<ExtendedRoomConfiguration> configuration = extendedRoomConfigurationService.findById(roomId);
        if (configuration.isEmpty()) {
            return ResponseEntity.badRequest().body("Указанного номера не существует");
        }

        formatter = DateTimeFormatter.ofPattern("d MMMM");
        String bookingDates = startDate.format(formatter) + " – " +
                endDate.format(formatter) + ", " + startDate.getYear();

        context.setVariable("roomConfig", configuration.get());
        context.setVariable("peopleCount", peopleCount);
        context.setVariable("bookingDates", bookingDates);
        context.setVariable("totalCoast", 15800);

        String renderedHtml = templateEngine.process("includes/booking-confirm-panel", context);

        return ResponseEntity.ok(renderedHtml);
    }

    private boolean isClientAuthorized() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            for (GrantedAuthority auth : authentication.getAuthorities()) {
                if (auth.getAuthority().equals("CLIENT") || auth.getAuthority().equals("ADMIN")) {
                    return true;
                }
            }
        }
        return false;
    }
}
