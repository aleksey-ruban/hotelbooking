function selectVisitInfo() {

    const selectedPeople = document.querySelector(".people-count-item-selected");
    const peopleCount = selectedPeople.getAttribute("data-people-count");

    var data = {
        startDate: window.finalStartDate,
        endDate: window.finalEndDate,
        peopleCount: peopleCount
    };

    fetch('/render-available-rooms', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Ошибка сервера');
        }
        return response.text();
    })
    .then(html => {
        document.getElementById("dynamic-block").innerHTML = html;
    })
    .catch(error => {
        console.error('Ошибка:', error);
    });
}

function selectRoom(event, artificial=false, room=-1) {
    var roomId = 0;
    if (!artificial) {
        const target = event.target;
        roomId = target.getAttribute("data-room-id");
    } else {
        roomId = room;
    }
    
    const selectedPeople = document.querySelector(".people-count-item-selected");
    const peopleCount = selectedPeople.getAttribute("data-people-count");

    var data = {
        startDate: window.finalStartDate,
        endDate: window.finalEndDate,
        peopleCount: peopleCount,
        roomId: roomId
    };

    fetch('/render-booking-details', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Ошибка сервера');
        }
        return response.text();
    })
    .then(html => {
        document.getElementById("dynamic-block").innerHTML = html;
    })
    .catch(error => {
        console.error('Ошибка:', error);
    });
}

function bookRoom(event) {
    const target = event.target;
    const roomId = target.getAttribute("data-room-id");

    if (!isAuthorized) {

        const selectedPeople = document.querySelector(".people-count-item-selected");
        const peopleCount = selectedPeople.getAttribute("data-people-count");

        var data = {
            startDate: window.finalStartDate,
            endDate: window.finalEndDate,
            peopleCount: peopleCount,
            roomId: roomId
        };

        localStorage.setItem('bookingChoice', JSON.stringify(data));

        window.location.href = '/signup';
    } else {
        // Create record
    }

}

function repairChoice(bookingChoice) {
    window.finalStartDate = new Date(bookingChoice.startDate);
    window.finalEndDate = new Date(bookingChoice.endDate);

    selectedDateStart = new Date(bookingChoice.startDate);
    selectedDateEnd = new Date(bookingChoice.endDate);
    selectedMounth = new Date(selectedDateStart);
    selectedMounth.setDate(15);

    var selectedCounts = document.querySelectorAll(".people-count-item-selected");
    for (var i = 0; i < selectedCounts.length; i++) {
        selectedCounts[i].classList.remove("people-count-item-selected");
    }

    const listPeople = document.getElementById("peopleModalListItems");
    listPeople.children[bookingChoice.peopleCount - 1].classList.add("people-count-item-selected");

    var months = [
        "января", "февраля", "марта",
        "апреля", "мая", "июня",
        "июля", "августа", "сентября",
        "октября", "ноября", "декабря"
    ];
    var button = document.querySelector('#date-button-value');
    button.innerHTML = '<span class="d-block text-body-secondary">Заезд – выезд</span>';
    button.innerHTML += window.finalStartDate.getDate() + " " + months[window.finalStartDate.getMonth()] + " – " + window.finalEndDate.getDate() + " " + months[window.finalEndDate.getMonth()] + ", " + window.finalStartDate.getFullYear();

    var target = document.querySelectorAll(".people-count-item-selected")[0];
    var button = document.querySelector('#people-button-value');
    button.innerHTML = '<span class="d-block text-body-secondary">Гости</span>';
    button.innerHTML += target.innerHTML;

    configureCalendar(true);

    selectRoom(new Event('click'), artificial=true, room=bookingChoice.roomId);
}

window.addEventListener("load", function() {
    if (loadBooking) {
        if (localStorage.getItem("bookingChoice")) {
            const value = localStorage.getItem("bookingChoice");
            try {
                const obj = JSON.parse(value);
                if (obj && typeof obj === "object" && Object.keys(obj).length > 0) {
                    repairChoice(obj);
                }
            } catch (e) {
                console.log("Ошибка при парсинге сохранённого объекта букинга:", e);
            }
        }
    }
});