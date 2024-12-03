"use strict";

var selectedMounth = new Date();
var selectedDateStart = null;
var selectedDateEnd = null;

window.finalStartDate = null;
window.finalEndDate = null;

function configureCalendar(changeMounth=false) {
    var calendarGrid = document.getElementsByClassName("calendar-view")[0]

    var today = new Date();
    
    var currentYear = today.getFullYear();
    var currentMounth = today.getMonth();

    if (changeMounth) {
        currentMounth = selectedMounth.getMonth();
    }
    var firstDayCurrentMounth = new Date(currentYear, currentMounth, 1);
    if (changeMounth) {
        var firstDayCurrentMounth = new Date(selectedMounth.getFullYear(), currentMounth, 1);
    }
    
    var firstDayWeekdayNumber = firstDayCurrentMounth.getDay();
    if (firstDayWeekdayNumber == 0) {
        firstDayWeekdayNumber = 7;
    }
    
    var firstCalendarDay = new Date(firstDayCurrentMounth);
    firstCalendarDay.setHours(9, 0, 0, 0)
    firstCalendarDay.setDate(firstDayCurrentMounth.getDate() - firstDayWeekdayNumber + (firstDayWeekdayNumber === 0 ? -6 : 1));
    
    var i = 0;
    var someCalendarDay = new Date(firstCalendarDay);
    var daysHtml = '';
    var days_rendered = 0;
    while (i < 35) {
        var onclick = ' onclick="selectDate(event)"';
        
        var q = "";
        
        var someCalendarDayString = "" + someCalendarDay.getFullYear() + "-" + (someCalendarDay.getMonth() + 1 < 10 ? ("0" + (someCalendarDay.getMonth() + 1)) : (someCalendarDay.getMonth() + 1)) + "-" + (someCalendarDay.getDate() < 10 ? ("0" + someCalendarDay.getDate()) : (someCalendarDay.getDate()));

        let copy1 = new Date(selectedDateStart);
        let copy2 = new Date(selectedDateEnd);
        let copy3 = new Date(someCalendarDay);

        copy1.setHours(9, 0, 0, 0);
        copy2.setHours(9, 0, 0, 0);
        copy3.setHours(9, 0, 0, 0);
        
        if ((copy1 <= copy3 && copy3 <= copy2) || copy1.getTime() === copy3.getTime()) {
            q = " calendar-view-day-selected";
        }
        
        if (days_rendered === 0) {
            daysHtml += '<div class="row">';
        }
        daysHtml += '<div class="calendar-view-day col py-2 m-1 rounded-2'+ q + '"' + 'data-date="' + someCalendarDayString + '"' + onclick + '>' + someCalendarDay.getDate() + "</div>";
        days_rendered += 1;
        if (days_rendered === 7) {
            daysHtml += '</div>';
            days_rendered = 0;
        }
        i += 1;
        someCalendarDay.setDate(someCalendarDay.getDate() + 1);
    }
    
    calendarGrid.innerHTML = daysHtml;

    var months = [
        "Январь", "Февраль", "Март",
        "Апрель", "Май", "Июнь",
        "Июль", "Август", "Сентябрь",
        "Октябрь", "Ноябрь", "Декабрь"
    ];
    var currentMounthLabel = document.querySelector(".current-mounth-value");
    if (changeMounth) {
        currentMounthLabel.innerHTML = months[currentMounth] + " " + selectedMounth.getFullYear();
    } else {
        currentMounthLabel.innerHTML = months[currentMounth] + " " + today.getFullYear();
    }
    

}

function loadPreviousMounth() {
    var today = new Date();
    var currentMounth = today;
    if (getMonthDifference(currentMounth, selectedMounth) >= 1) {
        selectedMounth = shiftDateBackOneMonth(selectedMounth);
        configureCalendar(true);
    }
}

function loadNextMounth() {
    var today = new Date();
    var currentMounth = today;
    console.log(getMonthDifference(selectedMounth, currentMounth));
    if (getMonthDifference(currentMounth, selectedMounth) <= 12) {
        selectedMounth = shiftDateForwardOneMonth(selectedMounth);
        configureCalendar(true);
    }
}

function selectDate(event) {
    var target = event.target;
    var newDate = new Date(target.getAttribute("data-date"));
    if (!selectedDateStart || (selectedDateStart && selectedDateEnd)) {
        selectedDateStart = new Date(newDate);
        var today = new Date();
        if (selectedDateStart < today) {
            selectedDateStart = null;
            selectedDateEnd = null;
            refreshDates();
            configureCalendar(true);
            return
        }
        selectedDateEnd = null;
        var selectedDays = document.querySelectorAll(".calendar-view-day-selected");
        for (var i = 0; i < selectedDays.length; i++) {
            selectedDays[i].classList.remove("calendar-view-day-selected");
        }
        target.classList.add("calendar-view-day-selected");
    } else {
        selectedDateEnd = new Date(newDate);
        if (selectedDateStart >= selectedDateEnd) {
            selectedDateStart = null;
            selectedDateEnd = null;
            configureCalendar(true);
            return;
        }
        window.finalStartDate = new Date(selectedDateStart);
        window.finalEndDate = new Date(selectedDateEnd);
        var months = [
            "января", "февраля", "марта",
            "апреля", "мая", "июня",
            "июля", "августа", "сентября",
            "октября", "ноября", "декабря"
        ];
        var button = document.querySelector('#date-button-value');
        button.innerHTML = '<span class="d-block text-body-secondary">Заезд – выезд</span>';
        button.innerHTML += window.finalStartDate.getDate() + " " + months[window.finalStartDate.getMonth()] + " – " + window.finalEndDate.getDate() + " " + months[window.finalEndDate.getMonth()] + ", " + window.finalStartDate.getFullYear();
        configureCalendar(true);
        const myModalEl = document.getElementById("dateModal");
        myModalEl.querySelector(".btn-close").click();
        selectVisitInfo();
    }
}

function getMonthDifference(startDate, endDate) {
    // Получаем годы и месяцы из обеих дат
    const startYear = startDate.getFullYear();
    const startMonth = startDate.getMonth(); // Январь = 0
    const endYear = endDate.getFullYear();
    const endMonth = endDate.getMonth();
    
    // Вычисляем разницу в месяцах
    return (endYear - startYear) * 12 + (endMonth - startMonth);
}

function shiftDateBackOneMonth(date) {
    // Создаём копию, чтобы не изменять оригинальную дату
    const newDate = new Date(date);
    // Сдвигаем месяц назад
    newDate.setMonth(newDate.getMonth() - 1);
    return newDate;
}

function shiftDateForwardOneMonth(date) {
    // Создаём копию, чтобы не изменять оригинальную дату
    const newDate = new Date(date);
    // Сдвигаем месяц вперёд
    newDate.setMonth(newDate.getMonth() + 1);
    return newDate;
}

function refreshDates() {
    selectedDateStart = new Date(window.finalStartDate);
    selectedDateEnd = new Date(window.finalEndDate);
    configureCalendar(true);
}

function selectPeopleCount(event) {
    var target = event.target;
    var selectedCounts = document.querySelectorAll(".people-count-item-selected");
        for (var i = 0; i < selectedCounts.length; i++) {
            selectedCounts[i].classList.remove("people-count-item-selected");
        }
    target.classList.add("people-count-item-selected")

    var button = document.querySelector('#people-button-value');
    button.innerHTML = '<span class="d-block text-body-secondary">Гости</span>';
    button.innerHTML += target.innerHTML;

    const myModalEl = document.getElementById("peopleModal");
    myModalEl.querySelector(".btn-close").click();
    selectVisitInfo();
}

function initDates() {
    var today = new Date();
    var tomorrow = new Date(today);
    today.setDate(today.getDate() + 1);
    tomorrow.setDate(tomorrow.getDate() + 2);

    today.setHours(9, 0, 0, 0);
    tomorrow.setHours(9, 0, 0, 0);

    window.finalStartDate = new Date(today);
    window.finalEndDate = new Date(tomorrow);

    selectedDateStart = new Date(today);
    selectedDateEnd = new Date(tomorrow);

    var months = [
        "января", "февраля", "марта",
        "апреля", "мая", "июня",
        "июля", "августа", "сентября",
        "октября", "ноября", "декабря"
    ];
    var button = document.querySelector('#date-button-value');
    button.innerHTML = '<span class="d-block text-body-secondary">Заезд – выезд</span>';
    button.innerHTML += window.finalStartDate.getDate() + " " + months[window.finalStartDate.getMonth()] + " – " + window.finalEndDate.getDate() + " " + months[window.finalEndDate.getMonth()] + ", " + window.finalStartDate.getFullYear();

    var button = document.querySelector('#people-button-value');
    button.innerHTML = '<span class="d-block text-body-secondary">Гости</span>';
    button.innerHTML += "2 человека";

    var peopleTarget = document.querySelector("#peopleModalListItems > :nth-child(2)");
    peopleTarget.classList.add("people-count-item-selected");
}

window.addEventListener("load", function() {
    configureCalendar();
    initDates();
});