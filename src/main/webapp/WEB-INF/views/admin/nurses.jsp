<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nurses - TeleExpertise</title>
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

        .back-link {
            font-size: 0.875rem;
            color: #666;
            text-decoration: none;
            transition: color 0.2s;
        }

        .back-link:hover {
            color: #111;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 3rem 2rem;
        }

        .page-header {
            display: flex;
            justify-content: space-between;
            align-items: flex-start;
            margin-bottom: 3rem;
        }

        .page-title h2 {
            font-size: 2.25rem;
            font-weight: 600;
            letter-spacing: -0.03em;
            line-height: 1.2;
            margin-bottom: 0.5rem;
        }

        .page-subtitle {
            font-size: 1rem;
            color: #666;
        }

        .add-btn {
            padding: 0.75rem 1.5rem;
            background: #111;
            color: #fff;
            text-decoration: none;
            font-size: 0.875rem;
            font-weight: 500;
            letter-spacing: 0.02em;
            transition: background 0.2s ease;
        }

        .add-btn:hover {
            background: #000;
        }

        .nurses-list {
            margin-top: 2rem;
        }

        .nurse-item {
            padding: 1.5rem 0;
            border-bottom: 1px solid #e5e5e5;
            display: grid;
            grid-template-columns: 1fr auto;
            gap: 2rem;
            align-items: center;
        }

        .nurse-info h3 {
            font-size: 1.125rem;
            font-weight: 500;
            margin-bottom: 0.5rem;
        }

        .nurse-details {
            font-size: 0.875rem;
            color: #666;
            display: flex;
            gap: 1.5rem;
        }

        .nurse-actions {
            display: flex;
            gap: 1rem;
        }

        .action-link {
            font-size: 0.813rem;
            color: #666;
            text-decoration: none;
            text-transform: uppercase;
            letter-spacing: 0.05em;
            transition: color 0.2s;
        }

        .action-link:hover {
            color: #111;
        }

        .empty-state {
            text-align: center;
            padding: 4rem 2rem;
            color: #666;
        }

        .empty-state h3 {
            font-size: 1.25rem;
            margin-bottom: 0.5rem;
            color: #111;
        }
    </style>
</head>
<body>
    <div class="header">
        <div class="header-content">
            <div class="brand">
                <h1>TeleExpertise</h1>
            </div>
            <a href="${pageContext.request.contextPath}/admin/dashboard" class="back-link">← Back to Dashboard</a>
        </div>
    </div>

    <div class="container">
        <div class="page-header">
            <div class="page-title">
                <h2>Nurses</h2>
                <p class="page-subtitle">Manage nursing staff</p>
            </div>
            <a href="${pageContext.request.contextPath}/admin/nurses/add" class="add-btn">Add Nurse</a>
        </div>

        <c:choose>
            <c:when test="${empty nurses}">
                <div class="empty-state">
                    <h3>No nurses yet</h3>
                    <p>Add your first nurse to get started</p>
                </div>
            </c:when>
            <c:otherwise>
                <div class="nurses-list">
                    <c:forEach var="nurse" items="${nurses}">
                        <div class="nurse-item">
                            <div class="nurse-info">
                                <h3><c:out value="${nurse.fname}" /> <c:out value="${nurse.lname}" /></h3>
                                <div class="nurse-details">
                                    <span><c:out value="${nurse.email}" /></span>
                                    <span><c:out value="${nurse.phone}" /></span>
                                </div>
                            </div>
                            <div class="nurse-actions">
                                <a href="${pageContext.request.contextPath}/admin/nurses/edit?id=${nurse.id}" class="action-link">Edit</a>
                                <a href="${pageContext.request.contextPath}/admin/nurses/delete?id=${nurse.id}" class="action-link">Delete</a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
