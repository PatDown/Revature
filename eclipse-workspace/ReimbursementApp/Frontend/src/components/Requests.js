let login_div = document.getElementById('login-div')
let logout_div = document.getElementById('logout-div')
let request_div = document.getElementById('request-div')
function login() {
    let url = 'http://localhost:3000/reimbursements/login'

    let xhr = new XMLHttpRequest()
    xhr.open('POST', url)

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let id = JSON.parse(xhr.response)
            console.log(id)
            login_div.hidden = true
            logout_div.hidden = false
            request_div.hidden = false
            getRequests(id)
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
    login()
})

function logout() {
    let url = 'http://localhost:3000/reimbursements/login'

    let xhr = new XMLHttpRequest()
    xhr.open('GET', url)

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            console.log(xhr.response)
            login_div.hidden = false
            logout_div.hidden = true
            request_div.hidden = true
        }
    }

    xhr.send()
}

let logout_button = document.getElementById('logout-button')

logout_button.addEventListener('click', (event) => {
    if (event.cancelable) {
        event.preventDefault()
    }
    logout()
})

function getRequests(id) {
    let url = 'http://localhost:3000/reimbursements/employee/' + id + '/requests'

    let xhr = new XMLHttpRequest()
    xhr.open('GET', url)
    xhr.send()

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let requests = xhr.response
            if (requests.length === 0) {
                let request_container = document.getElementById('request-container')
                let no_requests = document.getElementById('no-requests')
                request_container.hidden = true
                no_requests.hidden = false
            }

            for (let i in requests) {
                addNewRequest(i)
            }
        }
    }
    
}

function addNewRequest(request) {
    let input_boxes = document.getElementsByTagName('input')

    let table = document.getElementById('request-table')

    let rowCount = table.rows.length
    let row = table.insertRow(rowCount)

    let cell1 = row.insertCell(0)
    let element1 = document.createElement('p')
    element1.innerText = input_boxes[0].value
    cell1.appendChild(element1)

    let cell2 = row.insertCell(1)

    let cell3 = row.insertCell(2)

    let cell4 = row.insertCell(3)
}

window.onload = function () {

   
}