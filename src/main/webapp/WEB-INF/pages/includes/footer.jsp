<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Watch World Footer</title>
    <style>
      body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
      }

      /* Footer styling */
      footer {
        background-color: #222;
        color: #fff;
        padding: 30px 0;
        text-align: center;
        width: 100%;
        position:relative;
        bottom: 0;
      }

      .footer-content {
        display: flex;
        flex-wrap: wrap;
        justify-content: space-around;
      }

      .footer-section {
        margin: 0 20px;
      }

      .footer-section h3 {
        margin-bottom: 20px;
        font-size: 20px;
      }

      .footer-section ul {
        list-style: none;
        padding: 0;
        margin: 0;
      }

      .footer-section ul li {
        margin-bottom: 10px;
      }

      .footer-section ul li a {
        color: #ccc;
        text-decoration: none;
        font-size: 14px;
        transition: color 0.3s ease;
      }

      .footer-section ul li a:hover {
        color: #fff;
      }

      /* Copyright section */
      .copyright {
        margin-top: 20px;
        font-size: 14px;
      }
    </style>
  </head>
  <body>
    <footer>
      <div class="footer-content">
        <!-- Customer Service Section -->
        <div class="footer-section">
          <h3>Customer Service</h3>
          <ul>
            <li><a href="#">Contact Us</a></li>
            <li><a href="#">FAQs</a></li>
            <li><a href="#">Returns & Refunds</a></li>
          </ul>
        </div>

        <!-- Connect with Us Section -->
        <div class="footer-section">
          <h3>Connect with Us</h3>
          <ul>
            <li><a href="#">Facebook</a></li>
            <li><a href="#">Twitter</a></li>
            <li><a href="#">Instagram</a></li>
          </ul>
        </div>
      </div>

      <!-- Copyright Section -->
      <div class="copyright">&copy; 2024 Watch World. All Rights Reserved.</div>
    </footer>
  </body>
</html>
