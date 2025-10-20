<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Expertise Requests - TeleExpertise</title>
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

        .filters {
            background: #fff;
            border: 1px solid #e5e5e5;
            padding: 1.5rem;
            margin-bottom: 2rem;
            display: flex;
            gap: 1rem;
            align-items: end;
        }

        .filter-field {
            flex: 1;
        }

        .filter-field label {
            display: block;
            font-size: 0.813rem;
            font-weight: 500;
            color: #444;
            margin-bottom: 0.5rem;
            text-transform: uppercase;
            letter-spacing: 0.05em;
        }

        .filter-field select {
            width: 100%;
            padding: 0.875rem 0;
            border: none;
            border-bottom: 1px solid #e5e5e5;
            background: transparent;
            font-size: 1rem;
            color: #111;
            font-family: inherit;
            transition: border-color 0.2s ease;
        }

        .filter-field select:focus {
            outline: none;
            border-bottom-color: #111;
        }

        .filter-btn {
            padding: 0.875rem 1.5rem;
            background: #111;
            color: #fff;
            border: none;
            font-size: 0.875rem;
            font-weight: 500;
            cursor: pointer;
            letter-spacing: 0.02em;
            transition: background 0.2s ease;
        }

        .filter-btn:hover {
            background: #000;
        }

        .clear-btn {
            padding: 0.875rem 1.5rem;
            background: transparent;
            color: #666;
            border: 1px solid #e5e5e5;
            font-size: 0.875rem;
            font-weight: 500;
            cursor: pointer;
            letter-spacing: 0.02em;
            text-decoration: none;
            display: inline-block;
            transition: all 0.2s ease;
        }

        .clear-btn:hover {
            border-color: #111;
            color: #111;
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
            .filters {
                flex-direction: column;
                align-items: stretch;
            }

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
            <a href="${pageContext.request.contextPath}/specialist/dashboard" class="back-link">← Back to Dashboard</a>
        </div>
    </div>

    <div class="container">
        <div class="page-title">
            <h2>Expertise Requests</h2>
        </div>
        <p class="page-subtitle">Manage and respond to consultation requests</p>

        <!-- Filters (Stream API) -->
        <form action="${pageContext.request.contextPath}/specialist/expertise-requests" method="get" class="filters">
            <div class="filter-field">
                <label for="status">Filter by Status</label>
                <select id="status" name="status">
                    <option value="">All Statuses</option>
                    <c:forEach var="status" items="${statuses}">
                        <option value="${status}" ${selectedStatus == status.toString() ? 'selected' : ''}>${status}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="filter-field">
                <label for="priority">Filter by Priority</label>
                <select id="priority" name="priority">
                    <option value="">All Priorities</option>
                    <c:forEach var="priority" items="${priorities}">
                        <option value="${priority}" ${selectedPriority == priority.toString() ? 'selected' : ''}>${priority}</option>
                    </c:forEach>
                </select>
            </div>

            <button type="submit" class="filter-btn">Apply Filters</button>
            <a href="${pageContext.request.contextPath}/specialist/expertise-requests" class="clear-btn">Clear</a>
        </form>

        <!-- Requests Table -->
        <c:choose>
            <c:when test="${empty requests}">
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
                                <th>Patient</th>
                                <th>Requesting Doctor</th>
                                <th>Priority</th>
                                <th>Status</th>
                                <th>Created</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="request" items="${requests}">
                                <tr>
                                    <td>#${request.id}</td>
                                    <td>${request.patientName}</td>
                                    <td>Dr. ${request.requestingDoctorName}</td>
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
                                        <a href="${pageContext.request.contextPath}/specialist/expertise-requests/view?id=${request.id}" class="action-btn">
                                            ${request.status == 'PENDING' ? 'Respond' : 'View'}
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
