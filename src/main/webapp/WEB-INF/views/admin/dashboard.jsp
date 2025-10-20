<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard - TeleExpertise</title>
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

        .stats-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(240px, 1fr));
            gap: 1.5rem;
            margin-bottom: 3rem;
        }

        .stat-card {
            padding: 2rem 0;
            border-bottom: 1px solid #e5e5e5;
        }

        .stat-label {
            font-size: 0.813rem;
            text-transform: uppercase;
            letter-spacing: 0.05em;
            color: #666;
            font-weight: 500;
            margin-bottom: 0.75rem;
        }

        .stat-value {
            font-size: 2.5rem;
            font-weight: 600;
            letter-spacing: -0.03em;
            color: #111;
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
                    Logged in as <c:out value="${user.fname}" /> <c:out value="${user.lname}" />
                </c:if>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="page-title">
            <h2>Dashboard</h2>
        </div>
        <p class="page-subtitle">System overview and quick actions</p>

        <div class="stats-grid">
            <div class="stat-card">
                <div class="stat-label">Total Users</div>
                <div class="stat-value">${totalUsers}</div>
            </div>

            <div class="stat-card">
                <div class="stat-label">Total Patients</div>
                <div class="stat-value">${requestScope.totalPatients}</div>
            </div>

            <div class="stat-card">
                <div class="stat-label">Consultations</div>
                <div class="stat-value">${totalConsultations}</div>
            </div>
        </div>

        <div class="actions">
            <h3>Quick Actions</h3>
            <div class="action-list">
                <a href="${pageContext.request.contextPath}/admin/nurses" class="action-item">
                    <span class="action-title">Manage Nurses</span>
                    <span class="action-arrow">→</span>
                </a>
                <a href="${pageContext.request.contextPath}/admin/generalists" class="action-item">
                    <span class="action-title">Manage Generalists</span>
                    <span class="action-arrow">→</span>
                </a>
                <a href="${pageContext.request.contextPath}/admin/specialists" class="action-item">
                    <span class="action-title">Manage Specialists</span>
                    <span class="action-arrow">→</span>
                </a>
                <a href="${pageContext.request.contextPath}/admin/consultations" class="action-item">
                    <span class="action-title">View Consultations</span>
                    <span class="action-arrow">→</span>
                </a>
            </div>

            <a href="${pageContext.request.contextPath}/logout" class="logout-btn">Sign Out</a>
        </div>
    </div>
</body>
</html>
