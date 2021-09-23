let base_url = 'http://localhost:3000/reimbursements/'
let account_id = 0
let login_button = document.getElementById('login-button')

login_button.addEventListener('click', (event) => {
    if (event.cancelable) {
        event.preventDefault()
    }
    login()
})

function login() {
    let url = new URL('login', base_url)

    let xhr = new XMLHttpRequest()
    xhr.open('POST', url.href)

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            account_id = JSON.parse(xhr.response)
            sessionStorage.setItem('id', account_id)
            window.location = '../Pages/requests.html'
        }
    }
    let input_boxes = document.getElementsByClassName("login-class")

    let data = new FormData(document.getElementById('login-form'))
    data.append('username', input_boxes[0].value)
    data.append('password', input_boxes[1].value)

    xhr.send(data)
}