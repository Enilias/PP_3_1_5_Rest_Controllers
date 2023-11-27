const AUTHENTICATION_INFO_URL = "http://localhost:8080/user/authentication";

let authenticationInfo;

async function getData() {
    let promiseUsers = await fetch(AUTHENTICATION_INFO_URL);
    authenticationInfo = await promiseUsers.json();
    navbarAuthenticationInfo("navbarAuthenticationInfoUser", authenticationInfo);
    accessingTheNavigationBar("navigationBarUser", authenticationInfo);
    fillTable(authenticationInfo);
}


function fillTable(authenticationInfo) {
    let userTable = document.getElementById("userTable");
    let newRow = userTable.insertRow()
    newRow.innerHTML =
        "<td>" + authenticationInfo.principal.id + "</td>" +
        "<td>" + authenticationInfo.principal.firstName + "</td>" +
        "<td>" + authenticationInfo.principal.lastName + "</td>" +
        "<td>" + authenticationInfo.principal.age + "</td>" +
        "<td>" + authenticationInfo.principal.username + "</td>" +
        "<td>" + authenticationInfo.authorities.map(role => role.name.replace("ROLE_", "")).join(" ") + "</td>";

}

getData();