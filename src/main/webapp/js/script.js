window.addEventListener("beforeunload", function(event) {
	fetch('/Logout', { method: 'POST' })
		.then(response => {
			if (!response.ok) {
				console.error("Logout failed with status:", response.status);
			}
		})
		.catch(error => console.error("Error during logout:", error));
});

// assigning the `isLoggedIn` variable from server-side 
function setIsLoggedIn(value) {
	isLoggedIn = value === "true";
}



// checking if user has logged in and displaying appropiate message.
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



// profile.js

$(document).ready(function() {
	// Initialize all forms' visibility
	$(".change-password-form").hide();
	$(".account").show();

	// Click event for the "Account" menu item
	$(".account-button").click(function(e) {
		e.preventDefault();
		$(".account").show();
		$(".change-password-form").hide();

		// Handle active class
		$(".menu-link").removeClass("active");
		$(this).addClass("active");
	});

	// Click event for the "Change Password" menu item
	$(".change-password-button").click(function(e) {
		e.preventDefault();
		$(".account").hide();
		$(".change-password-form").show();

		// Handle active class
		$(".menu-link").removeClass("active");
		$(this).addClass("active");
	});

	// Click event for other menu items
	$(".menu-link").click(function(e) {
		// Handle active class for other menu links
		$(".menu-link").removeClass("active");
		$(this).addClass("active");
	});
});
