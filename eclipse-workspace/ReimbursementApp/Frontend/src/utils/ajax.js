
function getAccounts() {
    let url = 'http://localhost:3000/reimbursements/employee'
    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let accounts = JSON.parse(xhr.response);

            console.log(accounts);
        }
    }

    xhr.open('GET', url);

    xhr.send();
}

window.onload = function () {
    this.getAccounts();
}