<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Expertise Request #${expertiseRequest.id} - TeleExpertise</title>
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
            display: flex;
            align-items: center;
            gap: 1rem;
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

        .error-message {
            background: rgba(239, 68, 68, 0.05);
            border-left: 2px solid #ef4444;
            color: #dc2626;
            padding: 0.875rem 1rem;
            margin-bottom: 2rem;
            font-size: 0.875rem;
        }

        .success-message {
            background: rgba(34, 197, 94, 0.05);
            border-left: 2px solid #22c55e;
            color: #16a34a;
            padding: 0.875rem 1rem;
            margin-bottom: 2rem;
            font-size: 0.875rem;
        }

        .info-card {
            background: #fff;
            border: 1px solid #e5e5e5;
            padding: 2rem;
            margin-bottom: 2rem;
        }

        .info-card h3 {
            font-size: 1rem;
            font-weight: 500;
            text-transform: uppercase;
            letter-spacing: 0.05em;
            margin-bottom: 1.5rem;
            color: #666;
        }

        .info-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 1.5rem;
        }

        .info-item {
            display: flex;
            flex-direction: column;
            gap: 0.5rem;
        }

        .info-label {
            font-size: 0.813rem;
            text-transform: uppercase;
            letter-spacing: 0.05em;
            color: #666;
            font-weight: 500;
        }

        .info-value {
            font-size: 0.938rem;
            color: #111;
        }

        .medical-history {
            margin-top: 1.5rem;
        }

        .history-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 1rem;
        }

        .history-table thead {
            border-bottom: 1px solid #e5e5e5;
        }

        .history-table th {
            padding: 0.75rem;
            text-align: left;
            font-size: 0.813rem;
            font-weight: 500;
            color: #666;
            text-transform: uppercase;
            letter-spacing: 0.05em;
        }

        .history-table tbody tr {
            border-bottom: 1px solid #e5e5e5;
        }

        .history-table tbody tr:last-child {
            border-bottom: none;
        }

        .history-table td {
            padding: 0.75rem;
            font-size: 0.875rem;
            color: #111;
        }

        .request-details {
            background: #fff;
            border: 1px solid #e5e5e5;
            padding: 2rem;
            margin-bottom: 2rem;
        }

        .request-details h3 {
            font-size: 1rem;
            font-weight: 500;
            text-transform: uppercase;
            letter-spacing: 0.05em;
            margin-bottom: 1.5rem;
            color: #666;
        }

        .detail-section {
            margin-bottom: 2rem;
        }

        .detail-section:last-child {
            margin-bottom: 0;
        }

        .detail-section label {
            display: block;
            font-size: 0.813rem;
            font-weight: 500;
            color: #444;
            margin-bottom: 0.5rem;
            text-transform: uppercase;
            letter-spacing: 0.05em;
        }

        .detail-content {
            font-size: 0.938rem;
            color: #111;
            line-height: 1.6;
            white-space: pre-wrap;
            padding: 1rem;
            background: #fafafa;
            border-left: 2px solid #e5e5e5;
        }

        .response-form {
            background: #fff;
            border: 1px solid #e5e5e5;
            padding: 2rem;
            margin-bottom: 2rem;
        }

        .response-form h3 {
            font-size: 1rem;
            font-weight: 500;
            text-transform: uppercase;
            letter-spacing: 0.05em;
            margin-bottom: 1.5rem;
            color: #666;
        }

        .form-field {
            margin-bottom: 2rem;
        }

        .form-field:last-child {
            margin-bottom: 0;
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

        .form-field textarea {
            width: 100%;
            padding: 0.875rem;
            border: 1px solid #e5e5e5;
            background: #fff;
            font-size: 1rem;
            color: #111;
            font-family: inherit;
            transition: border-color 0.2s ease;
            min-height: 150px;
            resize: vertical;
        }

        .form-field textarea:focus {
            outline: none;
            border-color: #111;
        }

        .form-actions {
            margin-top: 2rem;
            display: flex;
            gap: 1rem;
        }

        .submit-btn {
            padding: 1rem 2rem;
            background: #111;
            color: #fff;
            border: none;
            font-size: 0.938rem;
            font-weight: 500;
            cursor: pointer;
            letter-spacing: 0.02em;
            transition: background 0.2s ease;
        }

        .submit-btn:hover {
            background: #000;
        }

        .cancel-btn {
            padding: 1rem 2rem;
            background: transparent;
            color: #666;
            border: none;
            font-size: 0.938rem;
            font-weight: 500;
            cursor: pointer;
            letter-spacing: 0.02em;
            text-decoration: none;
            display: inline-block;
            transition: color 0.2s ease;
        }

        .cancel-btn:hover {
            color: #111;
        }

        @media (max-width: 768px) {
            .info-grid {
                grid-template-columns: 1fr;
            }

            .form-actions {
                flex-direction: column;
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
            <a href="${pageContext.request.contextPath}/specialist/expertise-requests" class="back-link">← Back to Requests</a>
        </div>
    </div>

    <div class="container">
        <div class="page-title">
            <h2>Expertise Request #${expertiseRequest.id}</h2>
        </div>
        <div class="page-subtitle">
            <span class="status-badge status-${expertiseRequest.status == 'PENDING' ? 'pending' : 'completed'}">
                ${expertiseRequest.status}
            </span>
            <span class="status-badge priority-${expertiseRequest.priority == 'URGENT' ? 'urgent' : (expertiseRequest.priority == 'NORMAL' ? 'normal' : 'low')}">
                ${expertiseRequest.priority} Priority
            </span>
        </div>

        <c:if test="${not empty sessionScope.error}">
            <div class="error-message">
                ${sessionScope.error}
                <c:remove var="error" scope="session"/>
            </div>
        </c:if>

        <c:if test="${not empty sessionScope.successMessage}">
            <div class="success-message">
                ${sessionScope.successMessage}
                <c:remove var="successMessage" scope="session"/>
            </div>
        </c:if>

        <!-- Patient Information -->
        <div class="info-card">
            <h3>Patient Information</h3>
            <div class="info-grid">
                <div class="info-item">
                    <div class="info-label">Patient Name</div>
                    <div class="info-value">${patient.fname} ${patient.lname}</div>
                </div>
                <div class="info-item">
                    <div class="info-label">Date of Birth</div>
                    <div class="info-value">
                        ${patient.birthDate.year}-${patient.birthDate.monthValue < 10 ? '0' : ''}${patient.birthDate.monthValue}-${patient.birthDate.dayOfMonth < 10 ? '0' : ''}${patient.birthDate.dayOfMonth}
                    </div>
                </div>
                <div class="info-item">
                    <div class="info-label">Social Security</div>
                    <div class="info-value">${patient.socialSecurityNumber}</div>
                </div>
                <div class="info-item">
                    <div class="info-label">Contact</div>
                    <div class="info-value">${patient.phone}</div>
                </div>
                <c:if test="${not empty patient.allergies}">
                    <div class="info-item">
                        <div class="info-label">Allergies</div>
                        <div class="info-value">${patient.allergies}</div>
                    </div>
                </c:if>
                <c:if test="${not empty patient.currentTreatments}">
                    <div class="info-item">
                        <div class="info-label">Current Treatments</div>
                        <div class="info-value">${patient.currentTreatments}</div>
                    </div>
                </c:if>
            </div>

            <!-- Medical History -->
            <c:if test="${not empty medicalHistory}">
                <div class="medical-history">
                    <h4 style="font-size: 0.875rem; font-weight: 500; color: #666; margin-bottom: 0.75rem; text-transform: uppercase; letter-spacing: 0.05em;">Medical History</h4>
                    <table class="history-table">
                        <thead>
                            <tr>
                                <th>Date</th>
                                <th>Reason</th>
                                <th>Diagnosis</th>
                                <th>Treatment</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="history" items="${medicalHistory}">
                                <tr>
                                    <td>${history.consultationDate.year}-${history.consultationDate.monthValue < 10 ? '0' : ''}${history.consultationDate.monthValue}-${history.consultationDate.dayOfMonth < 10 ? '0' : ''}${history.consultationDate.dayOfMonth}</td>
                                    <td>${history.reason}</td>
                                    <td>${not empty history.diagnosis ? history.diagnosis : '-'}</td>
                                    <td>${not empty history.treatment ? history.treatment : '-'}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
        </div>

        <!-- Current Consultation Details -->
        <div class="info-card">
            <h3>Current Consultation</h3>
            <div class="info-grid">
                <div class="info-item">
                    <div class="info-label">Requesting Doctor</div>
                    <div class="info-value">Dr. ${expertiseRequest.requestingDoctorName}</div>
                </div>
                <div class="info-item">
                    <div class="info-label">Consultation Date</div>
                    <div class="info-value">
                        ${consultation.consultationDate.year}-${consultation.consultationDate.monthValue < 10 ? '0' : ''}${consultation.consultationDate.monthValue}-${consultation.consultationDate.dayOfMonth < 10 ? '0' : ''}${consultation.consultationDate.dayOfMonth}
                    </div>
                </div>
                <div class="info-item">
                    <div class="info-label">Request Created</div>
                    <div class="info-value">
                        ${expertiseRequest.createdAt.year}-${expertiseRequest.createdAt.monthValue < 10 ? '0' : ''}${expertiseRequest.createdAt.monthValue}-${expertiseRequest.createdAt.dayOfMonth < 10 ? '0' : ''}${expertiseRequest.createdAt.dayOfMonth}
                    </div>
                </div>
            </div>

            <div style="margin-top: 1.5rem;">
                <div class="info-item">
                    <div class="info-label">Reason for Consultation</div>
                    <div class="info-value">${consultation.reason}</div>
                </div>
            </div>

            <c:if test="${not empty consultation.symptoms}">
                <div style="margin-top: 1.5rem;">
                    <div class="info-item">
                        <div class="info-label">Symptoms</div>
                        <div class="info-value">${consultation.symptoms}</div>
                    </div>
                </div>
            </c:if>

            <c:if test="${not empty consultation.observations}">
                <div style="margin-top: 1.5rem;">
                    <div class="info-item">
                        <div class="info-label">Observations</div>
                        <div class="info-value">${consultation.observations}</div>
                    </div>
                </div>
            </c:if>
        </div>

        <!-- Expertise Request Details -->
        <div class="request-details">
            <h3>Expertise Request Details</h3>

            <div class="detail-section">
                <label>Question from General Practitioner</label>
                <div class="detail-content">${expertiseRequest.question}</div>
            </div>

            <c:if test="${not empty expertiseRequest.medicalData}">
                <div class="detail-section">
                    <label>Additional Medical Data</label>
                    <div class="detail-content">${expertiseRequest.medicalData}</div>
                </div>
            </c:if>
        </div>

        <!-- Response Section -->
        <c:choose>
            <c:when test="${expertiseRequest.status == 'PENDING'}">
                <!-- Response Form -->
                <form action="${pageContext.request.contextPath}/specialist/expertise-requests/view" method="post" class="response-form">
                    <input type="hidden" name="requestId" value="${expertiseRequest.id}">
                    <h3>Submit Your Expertise Response</h3>

                    <div class="form-field">
                        <label for="advice">Medical Advice *</label>
                        <textarea id="advice" name="advice" required placeholder="Provide your medical advice based on the case presented..."></textarea>
                    </div>

                    <div class="form-field">
                        <label for="recommendations">Recommendations *</label>
                        <textarea id="recommendations" name="recommendations" required placeholder="Provide specific recommendations for treatment or further investigation..."></textarea>
                    </div>

                    <div class="form-actions">
                        <button type="submit" class="submit-btn">Submit Expertise Response</button>
                        <a href="${pageContext.request.contextPath}/specialist/expertise-requests" class="cancel-btn">Cancel</a>
                    </div>
                </form>
            </c:when>
            <c:otherwise>
                <!-- Display Submitted Response -->
                <div class="request-details">
                    <h3>Your Expertise Response</h3>

                    <div class="detail-section">
                        <label>Medical Advice</label>
                        <div class="detail-content">${expertiseRequest.advice}</div>
                    </div>

                    <div class="detail-section">
                        <label>Recommendations</label>
                        <div class="detail-content">${expertiseRequest.recommendations}</div>
                    </div>

                    <c:if test="${not empty expertiseRequest.respondedAt}">
                        <div style="margin-top: 1.5rem; font-size: 0.875rem; color: #666;">
                            <strong>Responded on:</strong> ${expertiseRequest.respondedAt.year}-${expertiseRequest.respondedAt.monthValue < 10 ? '0' : ''}${expertiseRequest.respondedAt.monthValue}-${expertiseRequest.respondedAt.dayOfMonth < 10 ? '0' : ''}${expertiseRequest.respondedAt.dayOfMonth}
                        </div>
                    </c:if>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
