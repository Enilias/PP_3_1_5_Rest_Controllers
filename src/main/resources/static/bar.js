
function navbarAuthenticationInfo(id, authenticationInfo) {
    let userAuthentication = document.getElementById(id);
    userAuthentication.innerHTML = authenticationInfo.principal.username + " with roles: " + authenticationInfo.authorities.map(role => role.name.replace("ROLE_", "")).join(" ");
}

function accessingTheNavigationBar(id, authenticationInfo) {
    let navigationBar = document.getElementById(id);
    if (authenticationInfo.authorities.some(role => role.name === "ROLE_ADMIN")) {
        if (window.location.href === "http://localhost:8080/admin") {
            navigationBar.innerHTML =
                "<li class='nav-item'> <div> " +
                "<a href='admin' href='#' class='nav-link active' aria-current='page'>Admin</a> " +
                "</div> </li> <li class='nav-item'> <a href='user' href='#' class='nav-link'>User</a> </li>"
        } else {
            navigationBar.innerHTML =
                "<li class='nav-item'> <div> " +
                "<a href='admin' href='#' class='nav-link' aria-current='page'>Admin</a> " +
                "</div> </li> <li class='nav-item'> <a href='user' href='#' class='nav-link active'>User</a> </li>"
        }
    } else {
        navigationBar.innerHTML =
            "<li class='nav-item'> <div> " +
            "<a href='user' href='#' class='nav-link active' aria-current='page'>User</a></div> </li>"
    }
}