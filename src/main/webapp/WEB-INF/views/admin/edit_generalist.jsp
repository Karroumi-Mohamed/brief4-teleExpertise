<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit General Practitioner - TeleExpertise</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif; background: #fafafa; color: #111; }
        .header { padding: 1.5rem 2rem; border-bottom: 1px solid #e5e5e5; background: #fff; }
        .header-content { max-width: 800px; margin: 0 auto; display: flex; justify-content: space-between; align-items: center; }
        .brand h1 { font-size: 1.25rem; font-weight: 600; letter-spacing: -0.02em; }
        .back-link { font-size: 0.875rem; color: #666; text-decoration: none; transition: color 0.2s; }
        .back-link:hover { color: #111; }
        .container { max-width: 800px; margin: 0 auto; padding: 3rem 2rem; }
        .page-title { margin-bottom: 0.5rem; }
        .page-title h2 { font-size: 2.25rem; font-weight: 600; letter-spacing: -0.03em; line-height: 1.2; }
        .page-subtitle { font-size: 1rem; color: #666; margin-bottom: 3rem; }
        .error-message { background: rgba(239, 68, 68, 0.05); border-left: 2px solid #ef4444; color: #dc2626; padding: 0.875rem 1rem; margin-bottom: 2rem; font-size: 0.875rem; }
        .form-grid { display: grid; gap: 2rem; }
        .form-row { display: grid; grid-template-columns: 1fr 1fr; gap: 2rem; }
        .form-field { margin-bottom: 0; }
        .form-field label { display: block; font-size: 0.813rem; font-weight: 500; color: #444; margin-bottom: 0.5rem; text-transform: uppercase; letter-spacing: 0.05em; }
        .form-field input { width: 100%; padding: 0.875rem 0; border: none; border-bottom: 1px solid #e5e5e5; background: transparent; font-size: 1rem; color: #111; font-family: inherit; transition: border-color 0.2s ease; }
        .form-field input:focus { outline: none; border-bottom-color: #111; }
        .form-field input::placeholder { color: #999; }
        .form-actions { margin-top: 3rem; display: flex; gap: 1rem; }
        .submit-btn { padding: 1rem 2rem; background: #111; color: #fff; border: none; font-size: 0.938rem; font-weight: 500; cursor: pointer; letter-spacing: 0.02em; transition: background 0.2s ease; }
        .submit-btn:hover { background: #000; }
        .cancel-btn { padding: 1rem 2rem; background: transparent; color: #666; border: none; font-size: 0.938rem; font-weight: 500; cursor: pointer; letter-spacing: 0.02em; text-decoration: none; transition: color 0.2s ease; }
        .cancel-btn:hover { color: #111; }
        @media (max-width: 768px) { .form-row { grid-template-columns: 1fr; } }
    </style>
</head>
<body>
    <div class="header">
        <div class="header-content">
            <div class="brand"><h1>TeleExpertise</h1></div>
            <a href="${pageContext.request.contextPath}/admin/generalists" class="back-link">← Back to Generalists</a>
        </div>
    </div>
    <div class="container">
        <div class="page-title"><h2>Edit General Practitioner</h2></div>
        <p class="page-subtitle">Update general practitioner information</p>
        <c:if test="${not empty error}">
            <div class="error-message"><c:out value="${error}" /></div>
        </c:if>
        <form method="post" action="${pageContext.request.contextPath}/admin/generalists/edit">
            <input type="hidden" name="id" value="${generalist.id}">
            <div class="form-grid">
                <div class="form-row">
                    <div class="form-field">
                        <label for="firstName">First Name</label>
                        <input type="text" id="firstName" name="firstName" required value="${generalist.fname}" placeholder="John">
                    </div>
                    <div class="form-field">
                        <label for="lastName">Last Name</label>
                        <input type="text" id="lastName" name="lastName" required value="${generalist.lname}" placeholder="Doe">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-field">
                        <label for="email">Email</label>
                        <input type="email" id="email" name="email" required value="${generalist.email}" placeholder="doctor@hospital.com">
                    </div>
                    <div class="form-field">
                        <label for="phone">Phone</label>
                        <input type="tel" id="phone" name="phone" required value="${generalist.phone}" placeholder="+1234567890">
                    </div>
                </div>
            </div>
            <div class="form-actions">
                <button type="submit" class="submit-btn">Update Generalist</button>
                <a href="${pageContext.request.contextPath}/admin/generalists" class="cancel-btn">Cancel</a>
            </div>
        </form>
    </div>
</body>
</html>
