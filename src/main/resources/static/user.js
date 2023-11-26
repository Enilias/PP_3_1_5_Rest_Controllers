const userUrl = "http://localhost:8080/user/authentication";

let user;

async function getData() {
    let promiseUsers = await fetch(userUrl);
    user = await promiseUsers.json();
    navbarAuthenticationInfo();
    accessingTheNavigationBar();
    fillTable();
}

function fillTable() {
    let userTable = document.getElementById("userTable");

    let newRow = userTable.insertRow()
    newRow.innerHTML =
        "<td>" + user.principal.id + "</td>" +
        "<td>" + user.principal.firstName + "</td>" +
        "<td>" + user.principal.lastName + "</td>" +
        "<td>" + user.principal.age + "</td>" +
        "<td>" + user.principal.username + "</td>" +
        "<td>" + user.authorities.map(role => role.name.replace("ROLE_", "")).join(", ") + "</td>";

}

function navbarAuthenticationInfo() {
    let userAuthentication = document.getElementById("navbarAuthenticationInfo");
    userAuthentication.innerHTML = user.principal.username + " with roles: " + user.authorities.map(role => role.name.replace("ROLE_", "")).join(" ");
}

function accessingTheNavigationBar() {
    let navigationBar = document.getElementById("navigationBar");
    let boolean = user.authorities.some(role => role.name === "ROLE_ADMIN");
    if (boolean) {
        navigationBar.innerHTML =
            "<li class='nav-item'> <div> " +
            "<a href='admin' href='#' class='nav-link' aria-current='page'>Admin</a> " +
            "</div> </li> <li class='nav-item'> <a href='user' href='#' class='nav-link active'>User</a> </li>"
    } else {
        navigationBar.innerHTML =
            "<li class='nav-item'> <div> " +
            "<a href='user' href='#' class='nav-link active' aria-current='page'>User</a></div> </li>"
    }
}

getData();