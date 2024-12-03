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

function selectRoom(event) {
    const target = event.target;
    const roomId = target.getAttribute("data-room-id");
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