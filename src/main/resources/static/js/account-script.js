document.getElementById("account-logout").addEventListener("click", function(event) {
    window.location.href = "/logout";
});

document.getElementById("account-delete").addEventListener("click", function(event) {
    const userConfirmed = confirm("Вы уверены, что хотите удалить аккаунт? Это действие необратимо.");
    if (userConfirmed) {
        fetch("/account", {
            method: "DELETE",
        }).then(response => {
            console.log("DELETE запрос выполнен успешно");
            var url = "/logout";
            window.location.href = url;
        }).catch(error => {
            console.error('Ошибка при отправке запроса:', error);
        });
    } 
});

function deleteRecord(event) {
    const userConfirmed = confirm("Вы уверены, что хотите отменить бронь? Это действие необратимо.");
    if (userConfirmed) {
        const target = event.target;
        var recordId = target.getAttribute("data-record-id");
    
        var data = {
            recordId: recordId
        };
    
        fetch('/cancel-booking', {
            method: 'DELETE',
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
            document.getElementById("records").innerHTML = html;
        })
        .catch(error => {
            console.error('Ошибка:', error);
        });
    }
}