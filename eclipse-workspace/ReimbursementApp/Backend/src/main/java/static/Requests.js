let base_url = 'http://localhost:3000/reimbursements/'
let account_id = 0
let login_div = document.getElementById('login-div')
let logout_div = document.getElementById('logout-div')
let request_div = document.getElementById('request-div')
let login_button = document.getElementById('login-button')
let logout_button = document.getElementById('logout-button')
let new_request_button = document.getElementById('new-requests-button')
let update_requests_button = document.getElementById('update-requests-button')
let statistics_button = document.getElementById('statistics-button')
let submit_request_button = document.getElementById('submit-new-request')
let submit_update_button = document.getElementById('submit-update-button')
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
    if (!sb.hidden)
        getStats()
})

submit_request_button.addEventListener('click', (event) => {
    if (event.cancelable) {
        event.preventDefault()
    }
    addNewRequest()
})

submit_update_button.addEventListener('click', (event) => {
    if (event.cancelable) {
        event.preventDefault()
    }
    updateRequest()
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
    let url = new URL('login', base_url)

    let xhr = new XMLHttpRequest()
    xhr.open('POST', url.href)

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
    let input_boxes = document.getElementsByClassName("login-class")

    let data = new FormData(document.getElementById('login-form'))
    data.append('username', input_boxes[0].value)
    data.append('password', input_boxes[1].value)

    xhr.send(data)
}

function logout() {
    let url = new URL('login', base_url)

    let xhr = new XMLHttpRequest()
    xhr.open('GET', url.href)

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
    let url = new URL('employee/' + account_id + '/requests', base_url)

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

function addNewRequest() {
    let url = new URL('employee/' + account_id + '/requests', base_url)

    let xhr = new XMLHttpRequest()

    xhr.open('POST', url.href)

    let input_boxes = document.getElementsByClassName('new-request-class')

    let data = new FormData(document.getElementById('request-form'))
    data.append('amount', input_boxes[0].value)
    data.append('reason', input_boxes[1].value)

    xhr.send(data)

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let new_request = JSON.parse(xhr.response)
            let table = document.getElementById('request-table')

            let rowCount = table.rows.length
            let row = table.insertRow(rowCount)

            let cell1 = row.insertCell(0)
            let element1 = document.createElement('p')
            element1.innerText = new_request.id
            cell1.appendChild(element1)

            let cell2 = row.insertCell(1)
            let element2 = document.createElement('p')
            element2.innerText = formatter.format(new_request.amount)
            cell2.appendChild(element2)

            let cell3 = row.insertCell(2)
            let element3 = document.createElement('p')
            element3.innerText = new_request.reason
            cell3.appendChild(element3)

            let cell4 = row.insertCell(3)
            let element4 = document.createElement('p')
            element4.innerText = new_request.status
            cell4.appendChild(element4)

            let cell5 = row.insertCell(4)
            let element5 = document.createElement('p')
            element5.innerText = new_request.message
            cell5.appendChild(element5)

            console.log('New request created')
        }
    }
    
}

function updateRequest() {
    let input_boxes = document.getElementsByClassName('update-request-input')
    let ele = document.getElementsByName('new-status')
    let new_status
    for (let i = 0; i < ele.length; i++) {
        if (ele[i].checked)
            new_status = ele[i].value
    }

    let url = new URL('employee/' + account_id + '/requests/' + input_boxes[0].value, base_url)
    let xhr = new XMLHttpRequest()
    xhr.open('POST', url.href)

    let data = new FormData()
    data.append('new_status', new_status)
    let message = input_boxes[1].value
    if (message == null)
        data.append('message', 'None')
    else
        data.append('message', message)

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            console.log(JSON.stringify(xhr.response))
        }
    }
    xhr.send(data)
}

function getStats() {
    let url = new URL('employee/' + account_id + '/requests/stats', base_url)
    let xhr = new XMLHttpRequest()

    xhr.open('GET', url.href)

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            console.log(xhr.response)
        }
    }

    xhr.send()
}