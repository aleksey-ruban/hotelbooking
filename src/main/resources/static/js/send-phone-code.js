function sendPhoneCode(page) {
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
        phoneInput.disabled = true;
    })
    .catch(error => {
        alert(error.message);
    });
}
