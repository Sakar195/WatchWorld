/**
 * 
 */
window.addEventListener("beforeunload", function (event) {
    fetch('/Logout', { method: 'POST' }); // Sends a POST request to log out
});