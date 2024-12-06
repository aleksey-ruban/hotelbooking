package com.github.aleksey_ruban.hotelbooking.controllers;

import com.github.aleksey_ruban.hotelbooking.entity.*;
import com.github.aleksey_ruban.hotelbooking.helpers.ListHelper;
import com.github.aleksey_ruban.hotelbooking.helpers.PhoneNumberHelper;
import com.github.aleksey_ruban.hotelbooking.security.CustomUserDetails;
import com.github.aleksey_ruban.hotelbooking.service.BookingRecordService;
import com.github.aleksey_ruban.hotelbooking.service.ClientService;
import com.github.aleksey_ruban.hotelbooking.service.ExtendedRoomConfigurationService;
import com.github.aleksey_ruban.hotelbooking.service.RoomService;
import jakarta.servlet.ServletContext;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Controller
@AllArgsConstructor
public class BookingPagesController {

    private final ExtendedRoomConfigurationService extendedRoomConfigurationService;
    private final RoomService roomService;
    private final BookingRecordService bookingRecordService;
    private final ClientService clientService;

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

        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        LocalDate dayAfterTomorrow = today.plusDays(2);

        List<ExtendedRoomConfiguration> availableRooms = this.availableRoomConfigs(tomorrow, dayAfterTomorrow, 2);

        Integer nights = (int) ChronoUnit.DAYS.between(tomorrow, dayAfterTomorrow);
        List<BaseRoomConfiguration> modifiedConfigs = new ArrayList<>();
        for (ExtendedRoomConfiguration erc : availableRooms) {
            BaseRoomConfiguration brc = erc.getBaseRoomConfiguration();
            if (modifiedConfigs.contains(brc)) {
                continue;
            }
            brc.setBaseCoast(brc.getBaseCoast() * nights);
            erc.setBaseRoomConfiguration(brc);
            modifiedConfigs.add(brc);
        }

        if (loadBooking) {
            model.addAttribute("loadBooking", true);
        }

        model.addAttribute("extendedRoomConfigurations", availableRooms);
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

        List<ExtendedRoomConfiguration> availableRooms = this.availableRoomConfigs(startDate, endDate, peopleCount);
        Integer nights = (int) ChronoUnit.DAYS.between(startDate, endDate);
        List<BaseRoomConfiguration> modifiedConfigs = new ArrayList<>();
        for (ExtendedRoomConfiguration erc : availableRooms) {
            BaseRoomConfiguration brc = erc.getBaseRoomConfiguration();
            if (modifiedConfigs.contains(brc)) {
                continue;
            }
            brc.setBaseCoast(brc.getBaseCoast() * nights);
            erc.setBaseRoomConfiguration(brc);
            modifiedConfigs.add(brc);
        }

        context.setVariable("extendedRoomConfigurations", availableRooms);

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
        Integer nights = (int) ChronoUnit.DAYS.between(startDate, endDate);
        ExtendedRoomConfiguration nonOptionalConfiguration = configuration.get();

        formatter = DateTimeFormatter.ofPattern("d MMMM");
        String bookingDates = startDate.format(formatter) + " – " +
                endDate.format(formatter) + ", " + startDate.getYear();

        context.setVariable("roomConfig", nonOptionalConfiguration);
        context.setVariable("peopleCount", peopleCount);
        context.setVariable("bookingDates", bookingDates);
        context.setVariable("totalCoast", nonOptionalConfiguration.getBaseRoomConfiguration().getBaseCoast() * nights);

        String renderedHtml = templateEngine.process("includes/booking-confirm-panel", context);

        return ResponseEntity.ok(renderedHtml);
    }

    @PostMapping("/book-room")
    public ResponseEntity<String> bookRoom(@RequestBody Map<String, Object> requestData) {
        Long userId = null;
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof CustomUserDetails userDetails) {
                userId = userDetails.getId();
            }
        }
        if (userId == null) {
            return ResponseEntity.badRequest().body("При бронировании возникла ошибка, попробуйте заново");
        }
        Optional<Client> client = clientService.getById(userId);
        if (client.isEmpty()) {
            return ResponseEntity.badRequest().body("При бронировании возникла ошибка, попробуйте заново");
        }

        Long extendedConfigurationId = Long.parseLong((String) requestData.get("roomId"));
        Optional<ExtendedRoomConfiguration> erc = extendedRoomConfigurationService.findById(extendedConfigurationId);
        if (erc.isEmpty()) {
            return ResponseEntity.badRequest().body("При бронировании возникла ошибка, попробуйте заново");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        String startDateString = (String) requestData.get("startDate");
        String endDateString = (String) requestData.get("endDate");
        Integer peopleCount = Integer.parseInt((String) requestData.get("peopleCount"));

        LocalDate startDate = LocalDate.parse(startDateString, formatter);
        LocalDate endDate = LocalDate.parse(endDateString, formatter);

        List<Room> availableRooms = this.availableRooms(startDate, endDate, peopleCount);

        Optional<Room> roomOptional = availableRooms.stream()
                .filter(room -> room.getExtendedRoomConfiguration().getId().equals(extendedConfigurationId))
                .findFirst();

        if (roomOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("При бронировании возникла ошибка, попробуйте заново");
        }

        Integer nights = (int) ChronoUnit.DAYS.between(startDate, endDate);
        Integer totalCoast = erc.get().getBaseRoomConfiguration().getBaseCoast() * nights;

        BookingRecord record = BookingRecord.builder()
                .startDate(startDate)
                .endDate(endDate)
                .guestsCount(peopleCount)
                .client(client.get())
                .room(roomOptional.get())
                .totalCoast(totalCoast)
                .build();

        bookingRecordService.save(record);

        return ResponseEntity.ok("Запись создана");
    }

    @RequestMapping(value = {"/cancel-booking", "/cancel-booking/"}, method = RequestMethod.DELETE)
    public ResponseEntity<String> cancelBooking(@RequestBody Map<String, Object> requestData) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof CustomUserDetails userDetails) {
                Long userId = userDetails.getId();
                Optional<Client> client = clientService.getById(userId);
                if (client.isEmpty()) {
                    return new ResponseEntity<>("Account not found", HttpStatus.NOT_FOUND);
                }

                Long recordId = Long.parseLong((String) requestData.get("recordId"));
                Optional<BookingRecord> bookingRecord = bookingRecordService.findById(recordId);
                if (bookingRecord.isEmpty()) {
                    return new ResponseEntity<>("Record not found", HttpStatus.NOT_FOUND);
                }
                bookingRecordService.delete(recordId);

                Context modelContext = new Context();

                List<BookingRecord> bookingRecords = bookingRecordService.findByClientId(userId);
                modelContext.setVariable("bookingRecords", bookingRecords);

                String renderedHtml = templateEngine.process("includes/account-includes", modelContext);

                return ResponseEntity.ok(renderedHtml);
            }
        }
        return new ResponseEntity<>("Account deleted", HttpStatus.OK);
    }

    private List<ExtendedRoomConfiguration> availableRoomConfigs(LocalDate startDate, LocalDate endDate, Integer guestsCount) {

        List<ExtendedRoomConfiguration> extendedRoomConfigurations = new ArrayList<>();

        List<Room> allRooms = roomService.findRoomsByGuestsCount(guestsCount);
        List<BookingRecord> bookingRecords = bookingRecordService.findByDateRange(startDate, endDate);

        for (Room room : allRooms) {
            boolean isBooked = bookingRecords.stream()
                    .anyMatch(record -> record.getRoom().getId().equals(room.getId()));
            if (!isBooked) {
                ExtendedRoomConfiguration configuration = room.getExtendedRoomConfiguration();
                boolean configInList = extendedRoomConfigurations.stream()
                        .anyMatch(config -> config.getId().equals(configuration.getId()));
                if (!configInList) {
                    extendedRoomConfigurations.add(configuration);
                }
            }
        }

        return extendedRoomConfigurations.stream()
                .sorted(Comparator.comparing(e -> e.getBaseRoomConfiguration().getBaseCoast()))
                .toList();
    }

    private List<Room> availableRooms(LocalDate startDate, LocalDate endDate, Integer guestsCount) {

        List<Room> rooms = new ArrayList<>();

        List<Room> allRooms = roomService.findRoomsByGuestsCount(guestsCount);
        List<BookingRecord> bookingRecords = bookingRecordService.findByDateRange(startDate, endDate);

        for (Room room : allRooms) {
            boolean isBooked = bookingRecords.stream()
                    .anyMatch(record -> record.getRoom().getId().equals(room.getId()));
            if (!isBooked) {
                rooms.add(room);
            }
        }

        return rooms;
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
