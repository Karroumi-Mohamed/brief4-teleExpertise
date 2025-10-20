<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TeleExpertise - Login</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
            background: #fafafa;
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 2rem;
        }

        .login-wrapper {
            width: 100%;
            max-width: 420px;
        }

        .brand {
            margin-bottom: 3.5rem;
        }

        .brand h1 {
            font-size: 2rem;
            font-weight: 600;
            color: #111;
            letter-spacing: -0.03em;
            margin-bottom: 0.5rem;
        }

        .brand p {
            font-size: 0.938rem;
            color: #666;
            font-weight: 400;
        }

        .error-message {
            background: rgba(239, 68, 68, 0.05);
            border-left: 2px solid #ef4444;
            color: #dc2626;
            padding: 0.875rem 1rem;
            margin-bottom: 2rem;
            font-size: 0.875rem;
        }

        .form-field {
            margin-bottom: 1.75rem;
        }

        .form-field label {
            display: block;
            font-size: 0.813rem;
            font-weight: 500;
            color: #444;
            margin-bottom: 0.5rem;
            text-transform: uppercase;
            letter-spacing: 0.05em;
        }

        .form-field input {
            width: 100%;
            padding: 0.875rem 0;
            border: none;
            border-bottom: 1px solid #e5e5e5;
            background: transparent;
            font-size: 1rem;
            color: #111;
            transition: border-color 0.2s ease;
        }

        .form-field input:focus {
            outline: none;
            border-bottom-color: #111;
        }

        .form-field input::placeholder {
            color: #999;
        }

        .submit-btn {
            width: 100%;
            padding: 1rem;
            background: #111;
            color: #fff;
            border: none;
            font-size: 0.938rem;
            font-weight: 500;
            cursor: pointer;
            margin-top: 2.5rem;
            letter-spacing: 0.02em;
            transition: background 0.2s ease;
        }

        .submit-btn:hover {
            background: #000;
        }

        .submit-btn:active {
            transform: translateY(1px);
        }

        @media (max-width: 480px) {
            .brand h1 {
                font-size: 1.75rem;
            }
        }
    </style>
</head>
<body>
    <div class="login-wrapper">
        <div class="brand">
            <h1>TeleExpertise</h1>
            <p>Medical Consultation Platform</p>
        </div>

        <c:if test="${not empty sessionScope.error}">
            <div class="error-message">
                <c:out value="${sessionScope.error}" />
            </div>
        </c:if>

        <form method="post" action="${pageContext.request.contextPath}/login">
            <div class="form-field">
                <label for="email">Email</label>
                <input
                    type="email"
                    id="email"
                    name="email"
                    required
                    value="${param.email}"
                    placeholder="your@email.com"
                    autocomplete="email">
            </div>

            <div class="form-field">
                <label for="password">Password</label>
                <input
                    type="password"
                    id="password"
                    name="password"
                    required
                    placeholder="Enter your password"
                    autocomplete="current-password">
            </div>

            <button type="submit" class="submit-btn">Sign In</button>
        </form>
    </div>
</body>
</html>
