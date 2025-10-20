<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Expertise Requests - TeleExpertise</title>
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

        .empty-state {
            text-align: center;
            padding: 4rem 2rem;
            color: #666;
        }

        .empty-state-icon {
            font-size: 3rem;
            margin-bottom: 1rem;
            opacity: 0.3;
        }

        .empty-state-text {
            font-size: 1.125rem;
        }

        .table-container {
            background: #fff;
            border: 1px solid #e5e5e5;
            overflow-x: auto;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        thead {
            border-bottom: 1px solid #e5e5e5;
        }

        th {
            padding: 1rem;
            text-align: left;
            font-size: 0.813rem;
            font-weight: 500;
            color: #666;
            text-transform: uppercase;
            letter-spacing: 0.05em;
        }

        tbody tr {
            border-bottom: 1px solid #e5e5e5;
            transition: background-color 0.2s;
        }

        tbody tr:hover {
            background-color: #fafafa;
        }

        tbody tr:last-child {
            border-bottom: none;
        }

        td {
            padding: 1rem;
            font-size: 0.938rem;
            color: #111;
        }

        .status-badge {
            display: inline-block;
            padding: 0.25rem 0.75rem;
            font-size: 0.75rem;
            font-weight: 500;
            text-transform: uppercase;
            letter-spacing: 0.05em;
            border-radius: 2px;
        }

        .status-pending {
            background: rgba(245, 158, 11, 0.1);
            color: #d97706;
        }

        .status-completed {
            background: rgba(34, 197, 94, 0.1);
            color: #16a34a;
        }

        .priority-urgent {
            background: rgba(239, 68, 68, 0.1);
            color: #dc2626;
        }

        .priority-normal {
            background: rgba(59, 130, 246, 0.1);
            color: #2563eb;
        }

        .priority-low {
            background: rgba(107, 114, 128, 0.1);
            color: #4b5563;
        }

        .action-btn {
            padding: 0.5rem 1rem;
            background: #111;
            color: #fff;
            text-decoration: none;
            font-size: 0.813rem;
            font-weight: 500;
            letter-spacing: 0.02em;
            transition: background 0.2s ease;
            display: inline-block;
        }

        .action-btn:hover {
            background: #000;
        }

        @media (max-width: 768px) {
            table {
                font-size: 0.875rem;
            }

            th, td {
                padding: 0.75rem;
            }
        }
    </style>
</head>
<body>
    <div class="header">
        <div class="header-content">
            <div class="brand">
                <h1>TeleExpertise</h1>
            </div>
            <a href="${pageContext.request.contextPath}/gp/dashboard" class="back-link">← Back to Dashboard</a>
        </div>
    </div>

    <div class="container">
        <div class="page-title">
            <h2>My Expertise Requests</h2>
        </div>
        <p class="page-subtitle">Track specialist consultations and responses</p>

        <c:choose>
            <c:when test="${empty expertiseRequests}">
                <div class="empty-state">
                    <div class="empty-state-icon">📋</div>
                    <div class="empty-state-text">No expertise requests found</div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="table-container">
                    <table>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Specialist</th>
                                <th>Specialty</th>
                                <th>Priority</th>
                                <th>Status</th>
                                <th>Created</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="request" items="${expertiseRequests}">
                                <tr>
                                    <td>#${request.id}</td>
                                    <td>Dr. ${request.specialistName}</td>
                                    <td>${request.specialty}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${request.priority == 'URGENT'}">
                                                <span class="status-badge priority-urgent">Urgent</span>
                                            </c:when>
                                            <c:when test="${request.priority == 'NORMAL'}">
                                                <span class="status-badge priority-normal">Normal</span>
                                            </c:when>
                                            <c:when test="${request.priority == 'LOW'}">
                                                <span class="status-badge priority-low">Low</span>
                                            </c:when>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${request.status == 'PENDING'}">
                                                <span class="status-badge status-pending">Pending</span>
                                            </c:when>
                                            <c:when test="${request.status == 'COMPLETED'}">
                                                <span class="status-badge status-completed">Completed</span>
                                            </c:when>
                                        </c:choose>
                                    </td>
                                    <td>
                                        ${request.createdAt.year}-${request.createdAt.monthValue < 10 ? '0' : ''}${request.createdAt.monthValue}-${request.createdAt.dayOfMonth < 10 ? '0' : ''}${request.createdAt.dayOfMonth}
                                    </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/gp/expertise-requests/view?id=${request.id}" class="action-btn">
                                            View
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
