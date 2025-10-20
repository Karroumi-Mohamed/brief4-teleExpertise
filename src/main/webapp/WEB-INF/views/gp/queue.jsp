<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Waiting Queue - TeleExpertise</title>
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

        .queue-stats {
            display: flex;
            gap: 2rem;
            margin-bottom: 3rem;
            padding-bottom: 2rem;
            border-bottom: 1px solid #e5e5e5;
        }

        .stat-item {
            display: flex;
            flex-direction: column;
        }

        .stat-label {
            font-size: 0.813rem;
            text-transform: uppercase;
            letter-spacing: 0.05em;
            color: #666;
            font-weight: 500;
            margin-bottom: 0.5rem;
        }

        .stat-value {
            font-size: 2rem;
            font-weight: 600;
            letter-spacing: -0.03em;
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
            margin-bottom: 2rem;
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

        .patient-name {
            font-weight: 500;
        }

        .time-info {
            color: #666;
            font-size: 0.875rem;
        }

        .queue-position {
            display: inline-flex;
            align-items: center;
            justify-content: center;
            width: 2rem;
            height: 2rem;
            background: #111;
            color: #fff;
            border-radius: 50%;
            font-weight: 600;
            font-size: 0.875rem;
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

        .status-waiting {
            background: rgba(59, 130, 246, 0.1);
            color: #2563eb;
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
            .queue-stats {
                flex-direction: column;
                gap: 1.5rem;
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
        <a href="${pageContext.request.contextPath}/gp/dashboard" class="back-link">← Back to Dashboard</a>
    </div>
</div>

<div class="container">
    <div class="page-title">
        <h2>Waiting Queue</h2>
    </div>
    <p class="page-subtitle">Patients waiting for consultation</p>

    <div class="queue-stats">
        <div class="stat-item">
            <span class="stat-label">Patients Waiting</span>
            <span class="stat-value">${waitingQueue.size()}</span>
        </div>
    </div>
    <c:choose>
        <c:when test="${not empty error}">
            <div class="error-message" style="color: red; margin-bottom: 1rem;">
                    ${error}
            </div>
        </c:when>
    </c:choose>
    <c:choose>
        <c:when test="${empty waitingQueue}">
            <div class="empty-state">
                <div class="empty-state-icon">⏱</div>
                <div class="empty-state-text">No patients in the waiting queue</div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="table-container">
                <table>
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Patient Name</th>
                        <th>Patient ID</th>
                        <th>Arrival Time</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="entry" items="${waitingQueue}" varStatus="status">
                        <tr>
                            <td>
                                <span class="queue-position">${status.index + 1}</span>
                            </td>
                            <td>
                                <span class="patient-name">${entry.patientName}</span>
                            </td>
                            <td>
                                <span class="time-info">#${entry.patientId}</span>
                            </td>
                            <td>
                                        <span class="time-info">
                                            ${entry.arrivalTime.hour < 10 ? '0' : ''}${entry.arrivalTime.hour}:${entry.arrivalTime.minute < 10 ? '0' : ''}${entry.arrivalTime.minute}
                                        </span>
                            </td>
                            <td>
                                <span class="status-badge status-waiting">Waiting</span>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/gp/consultations/create?patientId=${entry.patientId}"
                                   class="action-btn">
                                    Start Consultation
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
