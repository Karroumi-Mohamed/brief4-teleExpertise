<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GP Dashboard - TeleExpertise</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
            background: #fafafa;
            color: #111;
        }

        .header {
            padding: 1.5rem 2rem;
            border-bottom: 1px solid #e5e5e5;
            background: #fff;
        }

        .header-content {
            max-width: 1200px;
            margin: 0 auto;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .brand h1 {
            font-size: 1.25rem;
            font-weight: 600;
            letter-spacing: -0.02em;
        }

        .user-info {
            font-size: 0.875rem;
            color: #666;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 3rem 2rem;
        }

        .page-title {
            margin-bottom: 0.5rem;
        }

        .page-title h2 {
            font-size: 2.25rem;
            font-weight: 600;
            letter-spacing: -0.03em;
            line-height: 1.2;
        }

        .page-subtitle {
            font-size: 1rem;
            color: #666;
            margin-bottom: 3rem;
        }

        .actions {
            margin-top: 4rem;
        }

        .actions h3 {
            font-size: 1rem;
            font-weight: 500;
            text-transform: uppercase;
            letter-spacing: 0.05em;
            margin-bottom: 1.5rem;
            color: #666;
        }

        .action-list {
            display: grid;
            gap: 0;
        }

        .action-item {
            padding: 1.25rem 0;
            border-bottom: 1px solid #e5e5e5;
            display: flex;
            justify-content: space-between;
            align-items: center;
            text-decoration: none;
            color: #111;
            transition: padding-left 0.2s ease;
        }

        .action-item:hover {
            padding-left: 0.5rem;
        }

        .action-title {
            font-size: 1.125rem;
            font-weight: 500;
        }

        .action-arrow {
            font-size: 1.25rem;
            color: #999;
        }

        .logout-btn {
            display: inline-block;
            padding: 0.75rem 1.5rem;
            background: #111;
            color: #fff;
            text-decoration: none;
            font-size: 0.875rem;
            font-weight: 500;
            letter-spacing: 0.02em;
            margin-top: 3rem;
            transition: background 0.2s ease;
        }

        .logout-btn:hover {
            background: #000;
        }

        .success-message {
            background: rgba(34, 197, 94, 0.05);
            border-left: 2px solid #22c55e;
            color: #16a34a;
            padding: 0.875rem 1rem;
            margin-bottom: 2rem;
            font-size: 0.875rem;
        }
    </style>
</head>
<body>
    <div class="header">
        <div class="header-content">
            <div class="brand">
                <h1>TeleExpertise</h1>
            </div>
            <div class="user-info">
                <c:if test="${not empty user}">
                    Logged in as Dr. <c:out value="${user.fname}" /> <c:out value="${user.lname}" />
                </c:if>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="page-title">
            <h2>Dashboard</h2>
        </div>
        <p class="page-subtitle">Patient consultations and expertise requests</p>

        <c:if test="${not empty sessionScope.successMessage}">
            <div class="success-message">
                ${sessionScope.successMessage}
                <c:remove var="successMessage" scope="session"/>
            </div>
        </c:if>

        <div class="actions">
            <h3>Quick Actions</h3>
            <div class="action-list">
                <a href="${pageContext.request.contextPath}/gp/queue" class="action-item">
                    <span class="action-title">View Waiting Queue</span>
                    <span class="action-arrow">→</span>
                </a>
                <a href="${pageContext.request.contextPath}/gp/consultations" class="action-item">
                    <span class="action-title">My Consultations</span>
                    <span class="action-arrow">→</span>
                </a>
                <a href="${pageContext.request.contextPath}/gp/expertise-requests" class="action-item">
                    <span class="action-title">Expertise Requests</span>
                    <span class="action-arrow">→</span>
                </a>
            </div>

            <a href="${pageContext.request.contextPath}/logout" class="logout-btn">Sign Out</a>
        </div>
    </div>
</body>
</html>
