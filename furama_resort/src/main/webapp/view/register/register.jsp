<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="view/register/register.css">
</head>
<body>
<div class="wrapper">
    <div class="form-header">
        <div class="titles">
            <div class="title-login">Login</div>
            <div class="title-register">Register</div>
        </div>
    </div>

    <form action="" method="post" class="register-form" autocomplete="off">
        <div class="form-grid">
            <div class="input-box">
                <input type="text" class="input-field" name="username" required placeholder=" ">
                <label class="label">Username</label>
                <i class='bx bx-user icon'></i>
            </div>

            <div class="input-box">
                <input type="text" class="input-field" name="full_name" required placeholder=" ">
                <label class="label">Full Name</label>
                <i class='bx bx-user icon'></i>
            </div>

            <div class="input-box">
                <input type="date" class="input-field" name="birthday" required placeholder=" ">
                <label class="label">Birthday</label>
            </div>

            <div class="input-box">
                <select class="select-field" name="gender" required>
                    <option value="" disabled selected hidden></option>
                    <option value="male">Male</option>
                    <option value="female">Female</option>
                </select>
                <label class="label">Gender</label>
                <i class='bx bx-user icon'></i>
            </div>

            <div class="input-box">
                <input type="text" class="input-field" name="id_number" required placeholder=" ">
                <label class="label">Citizen Identification Number</label>
                <i class='bx bx-id-card icon'></i>
            </div>

            <div class="input-box">
                <input type="tel" class="input-field" name="phone" required placeholder=" ">
                <label class="label">Phone Number</label>
                <i class='bx bx-phone icon'></i>
            </div>

            <div class="input-box">
                <input type="email" class="input-field" name="email" required placeholder=" ">
                <label class="label">Email</label>
                <i class='bx bx-envelope icon'></i>
            </div>

            <div class="input-box">
                <input type="text" class="input-field" name="address" required placeholder=" ">
                <label class="label">Address</label>
                <i class='bx bx-home icon'></i>
            </div>

            <div class="input-box">
                <input type="password" class="input-field" name="password" required placeholder=" ">
                <label class="label">Password</label>
                <i class='bx bx-lock-alt icon'></i>
            </div>

            <div class="input-box">
                <input type="password" class="input-field" name="confirm_password" required placeholder=" ">
                <label class="label">Confirm Password</label>
                <i class='bx bx-lock icon'></i>
            </div>
        </div>

        <div class="input-box">
            <button type="submit" class="btn-submit">Sign Up</button>
        </div>

        <div class="switch-form">
            <span>Already have an account? <a href="login">Login</a></span>
        </div>
    </form>
</div>
</body>
</html>
