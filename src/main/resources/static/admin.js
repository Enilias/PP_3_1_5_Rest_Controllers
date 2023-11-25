const usersUrl = "http://localhost:8080/admin/users"
const creatUserUrl = "http://localhost:8080/admin/new"
const rolesUrl = "http://localhost:8080/admin/role"
let userList;
let rolesList;

async function getData() {
    let promiseUsers = await fetch(usersUrl);
    let promiseRoles = await fetch(rolesUrl)
    rolesList = await promiseRoles.json();
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
            "<td>" + user.roles.map(role => role.name).join(", ") + "</td>" +
            "<td><button class='btn btn-info' style='color: white; background: #16A3B8' type='button' data-bs-toggle='modal' data-bs-target='#modalEdit' onclick='fillModalEdit(" + user.id + ")'>Edit</button></td>" +
            "<td><button class='btn btn-danger' style='color: white;' type='button' data-bs-toggle='modal' data-bs-target='#modalDelete' onclick='fillModalDelete(" + user.id + ")'>Delete</button></td>"

    })
}

function fillModalEdit(id) {
    let user = userList.find(user => user.id === id);
    document.getElementById("editId").value = user.id;
    document.getElementById("editFirstName").value = user.firstName;
    document.getElementById("editLastName").value = user.lastName;
    document.getElementById("editAge").value = user.age;
    document.getElementById("editUsername").value = user.username;
    let roleSelect = document.getElementById("editRoles");
    roleSelect.options.length = 0;
    rolesList.forEach(role => {
        let option = new Option();
        option.text = role.name;
        option.selected = user.roles.some(r => r.id === role.id);
        roleSelect.add(option);
    });
}

function fillModalDelete(id) {
    let user = userList.find(user => user.id === id);
    document.getElementById("deleteId").value = user.id;
    document.getElementById("deleteFirstName").value = user.firstName;
    document.getElementById("deleteLastName").value = user.lastName;
    document.getElementById("deleteAge").value = user.age;
    document.getElementById("deleteUsername").value = user.username;
    let roleSelect = document.getElementById("deleteRoles");
    roleSelect.options.length = 0;
    rolesList.forEach(role => {
        let option = new Option();
        option.text = role.name;
        option.selected = user.roles.some(r => r.id === role.id);
        roleSelect.add(option);
    });
}


getData();


