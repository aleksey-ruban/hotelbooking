document.getElementById("account-logout").addEventListener("click", function(event) {
    window.location.href = "/logout";
});

document.getElementById("account-delete").addEventListener("click", function(event) {
    // fetch("/authorization/account", {
    //     method: "DELETE",
    // }).then(response => {
    //     console.log("DELETE запрос выполнен успешно");
    //     var url = "/logout";
    //     window.location.href = url;
    // }).catch(error => {
    //     console.error('Ошибка при отправке запроса:', error);
    // });
});