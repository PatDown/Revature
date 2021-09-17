

function login() {
    let url = 'http://localhost:3000/reimbursements/login'

    let xhr = new XMLHttpRequest()
    xhr.open('POST', url)

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let credintials = JSON.parse(xhr.response)
            xhr.
            console.log(credintials)
        }
    }
    let input_boxes = document.getElementsByTagName("input")

    let data = new FormData(document.getElementById('login-form'))
    data.append('username', input_boxes[0].value)
    data.append('password', input_boxes[1].value)
    
    xhr.send(data)
    
}

let submit_button = document.querySelector('[type=submit]')

submit_button.addEventListener('click', (event) => {
    if (event.cancelable) {
        event.preventDefault()
    }
    login();
})