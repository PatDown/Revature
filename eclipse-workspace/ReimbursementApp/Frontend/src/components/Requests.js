
let account_id = 0
let login_div = document.getElementById('login-div')
let logout_div = document.getElementById('logout-div')
let request_div = document.getElementById('request-div')
let login_button = document.getElementById('login-button')
let logout_button = document.getElementById('logout-button')
let new_request_button = document.getElementById('new-requests-button')
let update_requests_button = document.getElementById('update-requests-button')
let statistics_button = document.getElementById('statistics-button')
let formatter = new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'USD'
})

login_button.addEventListener('click', (event) => {
    if (event.cancelable) {
        event.preventDefault()
    }
    login()
})

logout_button.addEventListener('click', (event) => {
    if (event.cancelable) {
        event.preventDefault()
    }
    logout()
})

new_request_button.addEventListener('click', (event) => {
    if (event.cancelable) {
        event.preventDefault()
    }
    let nrb = document.getElementById('new-request')
    toggleView(nrb)
})

update_requests_button.addEventListener('click', (event) => {
    if (event.cancelable) {
        event.preventDefault()
    }
    let urb = document.getElementById('update-request')
    toggleView(urb)
})

statistics_button.addEventListener('click', (event) => {
    if (event.cancelable) {
        event.preventDefault()
    }
    let sb = document.getElementById('stats')
    toggleView(sb)
})

function toggleView(obj) {
    obj.hidden = !(obj.hidden)
}

function resetViews() {
    let urb = document.getElementById('update-request') 
    let nrb = document.getElementById('new-request')
    let sb = document.getElementById('stats')

    urb.hidden = true
    nrb.hidden = true
    sb.hidden = true
}

function login() {
    let url = 'http://localhost:3000/reimbursements/login'

    let xhr = new XMLHttpRequest()
    xhr.open('POST', url)

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            login_div.hidden = true
            logout_div.hidden = false
            request_div.hidden = false
            resetViews()
            account_id = JSON.parse(xhr.response)
            getRequests()
        }
    }
    let input_boxes = document.getElementsByTagName("input")

    let data = new FormData(document.getElementById('login-form'))
    data.append('username', input_boxes[0].value)
    data.append('password', input_boxes[1].value)

    xhr.send(data)
}

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

function getRequests() {
    let url = new URL(account_id + '/requests', 'http://localhost:3000/reimbursements/employee/')
    let xhr = new XMLHttpRequest()

    xhr.open('GET', url.href)
    xhr.send()

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let requests = JSON.parse(xhr.response)
            if (requests.length === 0) {
                let request_container = document.getElementById('request-container')
                let no_requests = document.getElementById('no-requests')
                request_container.hidden = true
                no_requests.hidden = false
            } else {

                let table = document.getElementById('request-table')
                for (let i = 0; i < requests.length; i++) {
                    let myObj = requests[i]
                    let rowCount = table.rows.length
                    let row = table.insertRow(rowCount)

                    let cell1 = row.insertCell(0)
                    let element1 = document.createElement('p')
                    element1.innerText = myObj.id
                    cell1.appendChild(element1)

                    let cell2 = row.insertCell(1)
                    let element2 = document.createElement('p')
                    element2.innerText = formatter.format(myObj.amount)
                    cell2.appendChild(element2)

                    let cell3 = row.insertCell(2)
                    let element3 = document.createElement('p')
                    element3.innerText = myObj.reason
                    cell3.appendChild(element3)

                    let cell4 = row.insertCell(3)
                    let element4 = document.createElement('p')
                    element4.innerText = myObj.status
                    cell4.appendChild(element4)

                    let cell5 = row.insertCell(4)
                    let element5 = document.createElement('p')
                    element5.innerText = myObj.message
                    cell5.appendChild(element5)
                }
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

function getStats() {
    let url = new URL(account_id + '/stats', 'http://localhost:3000/reimbursements/employee/')
    let xhr = new XMLHttpRequest()

    xhr.open('GET', url)

    xhr.send()

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            console.log(JSON.parse(xhr.response))
        }
    }
}