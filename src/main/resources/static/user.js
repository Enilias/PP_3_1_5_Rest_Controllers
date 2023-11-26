const userUrl = "http://localhost:8080/user/authentication";

let userList;

async function getData() {
    let promiseUsers = await fetch(userUrl);
    userList = await promiseUsers.json();
    fillTable();
}

function fillTable() {
    let userTable = document.getElementById("userTable");
    userList.forEach(user => {
        let newRow = userTable.insertRow()
        newRow.innerHTML =
            "<td>" + user.id + "</td>" +
            "<td>" + user.firstName + "</td>" +
            "<td>" + user.lastName + "</td>" +
            "<td>" + user.age + "</td>" +
            "<td>" + user.username + "</td>" +
            "<td>" + user.roles.map(role => role.name).join(", ") + "</td>"
    })
}

getData();