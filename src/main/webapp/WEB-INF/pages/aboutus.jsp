<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0"0>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/aboutus.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<title>About Us</title>

</head>
<body>
	<%@ include file="includes/header.jsp"%>
	
	<!-- Spacer div to create space below the header -->
	<div style="height: 80px;"></div>
	<!-----content--->
	<div class="row">
		<div class="details">
			<div class="img">
				<img src="../img/srijan.jpg">
			</div>
			<div class="desc">
				<h2>Sakar Paudel</h2>
				<div class="sociallogo">
					<a href="https://www.facebook.com/sakar.poudel.988"><img
						src="../img/fb.png"></a> <a
						href="https://www.instagram.com/sakar.paudel/"><img
						src="../img/insta.png"></a> <a
						href="https://github.com/Sakar195"><img
						src="../img/github.png"></a>
				</div>
				<p>Hey guys! Thanks for visiting our website. I'm Sakar Paudel,
					a student of computing studying at Islington College. My goal is to
					Become the PM. I like browsing the web for new
					technologies that come out everyday as it helps me with getting
					great knowledge and resources. Additionally, i am an automobile
					enthusiast</p>
			</div>
		</div>
		<div class="details">
			<div class="desc">
				<h2>Ritesh Maharjan</h2>
				<div class="sociallogo">
					<a href="https://www.facebook.com/sakar.poudel.988"><img
						src="../img/fb.png"></a> <a
						href="https://www.instagram.com/sakar.paudel/"><img
						src="../img/insta.png"></a> <a
						href="https://github.com/Sakar195"><img
						src="../img/github.png"></a>
				</div>
				<p>Hey guys! Thanks for visiting our website. I'm Sakar Paudel,
					a student of computing studying at Islington College. My goal is to
					work in a fortune 500 company. I like browsing the web for new
					technologies that come out everyday as it helps me with getting
					great knowledge and resources. Additionally, i am an automobile
					enthusiast.</p>
			</div>
			<div class="img">
				<img src="../img/sakar.jpg">
			</div>
		</div>
		<div class="details">
			<div class="img">
				<img src="../img/default.png">
			</div>
			<div class="desc">
				<h2>Shreeyush Dhungana</h2>
				<div class="sociallogo">
					<a href="https://www.facebook.com/srijan.mahrzn"><img
						src="../img/fb.png"></a> <a
						href="https://www.instagram.com/srijanmahrzn"><img
						src="../img/insta.png"></a> <a href="https://www.github.com"><img
						src="../img/github.png"></a>
				</div>
				<p>Hey everyone! Thanks for visiting our website. I'm Shreeyush
					Dhungana, currently a student studying Computing at Islington
					College. I would like to become a Software Engineer after I
					graduate. I love travelling and I also love researching online as
					it helps me with getting great knowledge and resources. I love
					playing games</p>
			</div>
		</div>
		<div class="details">
			<div class="desc">
				<h2>Bikalpa Kunwar</h2>
				<div class="sociallogo">
					<a href="https://www.facebook.com/bikalpa.kunwar.5"><img
						src="../img/fb.png"></a> <a
						href="https://www.instagram.com/bikalpaa_/"><img
						src="../img/insta.png"></a> <a
						href="https://github.com/Bikalpa07"><img
						src="../img/github.png"></a>
				</div>
				<p>Hey everyone! Thanks for visiting our website. I’m Bikalpa
					Kunwar, currently a student studying Computing at Islington
					College. I would like to become a Business Tycoon after I graduate.
					I love to travel, explore and experience new things.</p>
			</div>
			<div class="img">
				<img src="../img/default.png">
			</div>
		</div>
		
	</div>
	<div class="form">
		<form name="feedbackform">
			<p>
				<label for="email">Email</label>
			</p>
			<input type="email" name="email" required>
			<p>
				<label for="phoneNumber">Phone</label>
			</p>
			<input type="text" name="phoneNumber" required>
			<p>
				<label for="textarea">Feedback </label>
			</p>
			<textarea style="width: 400px; height: 100px;" name="feedback" required></textarea>
			<br>
			<button class="button" onclick="return feed()">Submit</button>
		</form>
	</div>
	<%@ include file="includes/footer.jsp"%>
	<!-----script for form validation---->

</body>
</html>