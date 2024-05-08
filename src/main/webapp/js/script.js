window.addEventListener("beforeunload", function(event) {
    fetch('/Logout', { method: 'POST' })
        .then(response => {
            if (!response.ok) {
                console.error("Logout failed with status:", response.status);
            }
        })
        .catch(error => console.error("Error during logout:", error));
});

// Assume this variable is set from the server-side. In your case, it comes from JSP.
var isLoggedIn = false;

function checkLoginAndNavigate(event, url, message) {
    if (!isLoggedIn) {
        event.preventDefault(); // Prevent navigation
        alert(message); // Display the custom alert message
    } else {
        window.location.href = url; // Navigate to the specified URL
    }
}

function confirmLogout() {
    alert("You have logged out.");
}

// Example of assigning the `isLoggedIn` variable from server-side data (optional step)
function setIsLoggedIn(value) {
    isLoggedIn = value === "true";
}
