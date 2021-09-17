let url = 'http://localhost:3000/reimbursements/employee/:id/requests'

function getRequests() {

}

function addNewRequest(tableID) {
    let input_boxes = document.getElementsByTagName('input')

    let table = document.getElementById(tableID)

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