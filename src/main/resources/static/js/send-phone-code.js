function sendPhoneCode(page) {
    if (localStorage.getItem("bookingChoice")) {
        const value = localStorage.getItem("bookingChoice");
        try {
            const obj = JSON.parse(value);
            if (obj && typeof obj === "object" && Object.keys(obj).length > 0) {
                const inp = document.getElementById("bookingChoice");
                inp.value = true;
            }
        } catch (e) {
            console.log("Ошибка при парсинге сохранённого объекта букинга:", e);
        }
    }

    const phoneInput = document.getElementById("phone");
    const phoneNumber = phoneInput.value;
    fetch('/send-code', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({"phoneNumber": phoneNumber, "page": page})
    })
    .then(response => {
        if (!response.ok) {
            return response.text().then(err => { throw new Error(err); });
        }
        return response.text();
    })
    .then(message => {
        alert(message);
        phoneInput.setAttribute("readonly", true);
        const smsInput = document.getElementById("sms-code");
        smsInput.removeAttribute("disabled");
        const submitButton = document.getElementById("submit-btn");
        submitButton.removeAttribute("disabled");
    })
    .catch(error => {
        alert(error.message);
    });
}
