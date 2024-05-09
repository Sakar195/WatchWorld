<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/aboutus.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<title>About Us</title>

</head>
<body>
	<%@ include file="includes/header.jsp"%>
	
	<!-- Spacer div to create space below the header -->
	<div style="height: 80px;"></div>
	<!-----content--->
	<div class="section">
        <div class="container">
            <div class="title">
                <h1>About Us</h1>
            </div>
            <div class="content-section">
                <div class="content">
                    <h2>Aayush Gharti</h2>
                    <p>Highly-motivated, responsible and ambitious student with excellent time management skill and eagerness to learn something new. I have a clear, logical mind with a practical approach to problem-solving and a drive to see things through to completion.</p>
                    <div class="social">
                        <a href=""><i class="fab fa-facebook"></i></a>
                        <a href=""><i class="fab fa-instagram"></i></a>
                    </div>
                </div>
                <div class="image-section">
                    <img src="${pageContext.request.contextPath}/images/pfp1.jpg">
                </div>
            </div>
            <div class="content-section">
                <div class="content">
                    <h2>Sakar Paudel</h2>
                    <p>Hey guys! Thanks for visiting our website. I'm Sakar Paudel,
                 a student of computing studying at Islington College. My goal is
                to be the P.M. I like browsing the web for new
                technologies that come out everyday as it helps me with getting great
                knowledge and resources. Additionally, i am an automobile enthusiast.</p>
                    <div class="social">
                        <a href=""><i class="fab fa-facebook"></i></a>
                        <a href=""><i class="fab fa-instagram"></i></a>
                    </div>
                </div>
                <div class="image-section">
                    <img src="${pageContext.request.contextPath}/images/pfp2.jpg">
                </div>
            </div>
            <div class="content-section">
                <div class="content">
                    <h2>Ritesh Maharjan</h2>
                    <p>Hello everyone! Thank you for visiting the website. I'm Ritesh Maharjan. Hope you guys enjoy browsing through our website........</p>
                    <div class="social">
                        <a href=""><i class="fab fa-facebook"></i></a>
                        <a href=""><i class="fab fa-instagram"></i></a>
                    </div>
                </div>
                <div class="image-section">
                    <img src="${pageContext.request.contextPath}/images/pfp3.jpg">
                </div>
            </div>
            <div class="content-section">
                <div class="content">
                    <h2>Navaras Shrestha</h2>
                    <p>Greetings! Im Navaras Shrestha, an avid tech and music enjoyer. Enjoy shopping here in this site, and have a great day!</p>
                    <div class="social">
                        <a href=""><i class="fab fa-facebook"></i></a>
                        <a href=""><i class="fab fa-instagram"></i></a>
                    </div>
                </div>
                <div class="image-section">
                    <img src="${pageContext.request.contextPath}/images/pfp4.jpg">
                </div>
            </div>
            <div class="content-section">
                <div class="content">
                    <h2>Madhu Magar</h2>
                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequatur,
                    vel id dolore, perferendis quisquam, non nam perspiciatis debitis
                    vitae omnis sint in dolor nesciunt? Quas a consectetur fugiat
                    repudiandae sequi.
                    Aspernatur unde vero expedita facilis fuga inventore
                    corrupti soluta omnis harum est adipisci hic, a provident.
                    Velit quibusdam corrupti qui minima accusamus!</p>
                    <div class="social">
                        <a href=""><i class="fab fa-facebook"></i></a>
                        <a href=""><i class="fab fa-instagram"></i></a>
                    </div>
                </div>
                <div class="image-section">
                    <img src="${pageContext.request.contextPath}/images/pfp5.jpg">
                </div>
            </div>
        </div>
    </div>
	<%@ include file="includes/footer.jsp"%>
	<!-----script for form validation---->

</body>
</html>