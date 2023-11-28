const AUTHENTICATION_INFO_URL = "http://localhost:8080/user/authentication";
const USER_URL = "http://localhost:8080/admin/users"
const ROLES_URL = "http://localhost:8080/admin/role"
const CREATE_USER_URL = "http://localhost:8080/admin/create"
const UPDATE_USER_URL = "http://localhost:8080/admin/update"
const DELETE_USER_URL = "http://localhost:8080/admin/delete";
let authenticationInfoAdmin;
let userList;
let rolesList;

async function getData() {
    let promiseUsers = await fetch(USER_URL);
    let promiseRoles = await fetch(ROLES_URL);
    let promiseAuthenticationInfo = await fetch(AUTHENTICATION_INFO_URL)
    authenticationInfoAdmin = await promiseAuthenticationInfo.json();
    rolesList = await promiseRoles.json();
    userList = await promiseUsers.json();
    navbarAuthenticationInfo("navbarAuthenticationInfoAdmin", authenticationInfoAdmin);
    accessingTheNavigationBar("navigationBarAdmin", authenticationInfoAdmin);

    fillTable();
}

function fillTable() {
    let userTable = document.getElementById("usersTable");
    $("#usersTable").empty();
    userList.forEach(user => {
        let newRow = userTable.insertRow()
        newRow.innerHTML =
            "<td>" + user.id + "</td>" +
            "<td>" + user.firstName + "</td>" +
            "<td>" + user.lastName + "</td>" +
            "<td>" + user.age + "</td>" +
            "<td>" + user.username + "</td>" +
            "<td>" + user.roles.map(role => role.name.replace("ROLE_", "")).join(" ") + "</td>" +
            "<td><button class='btn btn-info' style='color: white; background: #16A3B8' type='button' data-bs-toggle='modal' data-bs-target='#modalEdit' onclick='fillModalEdit(" + user.id + ")'>Edit</button></td>" +
            "<td><button class='btn btn-danger' style='color: white;' type='button' data-bs-toggle='modal' data-bs-target='#modalDelete' onclick='fillModalDelete(" + user.id + ")'>Delete</button></td>"

    })
    fillModalRolesCreate();
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

async function fillModalRolesCreate() {
    let roleSelect = document.getElementById("createRoles");
    roleSelect.options.length = 0;
    rolesList.forEach(role => {
        let option = new Option();
        option.text = role.name;
        roleSelect.add(option);
    });
}

function addEventListeners() {

    document.getElementById("NewUser").addEventListener("submit", async (e) => {
        e.preventDefault();
        await fetch(CREATE_USER_URL, {
            method: "POST",
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            body: $("#creatForm").serialize()
        });
        let createUser = {
            id: 0,
            firstName: null,
            lastName: null,
            age: null,
            username: null,
            password: null,
            roles: null
        }
        createUser.id = userList.length + 1;
        createUser.firstName = document.getElementById("firstName").value;
        createUser.lastName = document.getElementById("lastName").value;
        createUser.age = document.getElementById("age").value;
        createUser.username = document.getElementById("username").value;
        createUser.password = document.getElementById("password").value;

        let selectedRoles = Array.from(document.getElementById("createRoles").selectedOptions).map(option => option.value);

        createUser.roles = rolesList.filter(role => selectedRoles.includes(role.name));

        userList[userList.length + 1] = createUser;
        fillTable();
    });


    document.getElementById("editForm").addEventListener("submit", async (e) => {
        e.preventDefault();
        let userId = Number(new FormData(e.target).get("id"));
        await fetch(UPDATE_USER_URL, {
            method: "PATCH",
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            body: $("#editForm").serialize()
        });
        let updateUser = userList[userId - 1];
        updateUser.firstName = document.getElementById("editFirstName").value;
        updateUser.lastName = document.getElementById("editLastName").value;
        updateUser.age = document.getElementById("editAge").value;
        updateUser.username = document.getElementById("editUsername").value;
        updateUser.password = document.getElementById("editPassword").value;

        let selectedRoles = Array.from(document.getElementById("editRoles").selectedOptions).map(option => option.value);

        updateUser.roles = rolesList.filter(role => selectedRoles.includes(role.name));

        userList[userId - 1] = updateUser;
        fillTable();
    });
    document.getElementById("deleteForm").addEventListener("submit", async (e) => {
        e.preventDefault()
        let formData = new FormData(e.target);
        await fetch(DELETE_USER_URL, {
            method: "DELETE",
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            body: $("#deleteForm").serialize()
        });
        userList.splice(userList.findIndex(user => user.id === formData.get("id")), 1);
        fillTable();
    });
}

getData();
addEventListeners();

