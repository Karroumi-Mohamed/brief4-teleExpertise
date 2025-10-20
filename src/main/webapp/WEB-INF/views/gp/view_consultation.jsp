<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Consultation Details - TeleExpertise</title>
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

        .error-message {
            background: rgba(239, 68, 68, 0.05);
            border-left: 2px solid #ef4444;
            color: #dc2626;
            padding: 0.875rem 1rem;
            margin-bottom: 2rem;
            font-size: 0.875rem;
        }

        .status-badge {
            display: inline-block;
            padding: 0.375rem 0.75rem;
            font-size: 0.75rem;
            font-weight: 500;
            text-transform: uppercase;
            letter-spacing: 0.05em;
            border-radius: 2px;
            margin-left: 1rem;
        }

        .status-in-progress {
            background: rgba(59, 130, 246, 0.1);
            color: #2563eb;
        }

        .status-awaiting {
            background: rgba(245, 158, 11, 0.1);
            color: #d97706;
        }

        .status-completed {
            background: rgba(34, 197, 94, 0.1);
            color: #16a34a;
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
            grid-template-columns: repeat(2, 1fr);
            gap: 1.5rem;
        }

        .info-item {
            display: flex;
            flex-direction: column;
        }

        .info-label {
            font-size: 0.813rem;
            text-transform: uppercase;
            letter-spacing: 0.05em;
            color: #666;
            font-weight: 500;
            margin-bottom: 0.5rem;
        }

        .info-value {
            color: #111;
            font-size: 1rem;
        }

        .section-title {
            font-size: 1rem;
            font-weight: 500;
            text-transform: uppercase;
            letter-spacing: 0.05em;
            margin: 3rem 0 2rem 0;
            color: #666;
        }

        .form-grid {
            display: grid;
            gap: 2rem;
        }

        .form-field {
            margin-bottom: 0;
        }

        .form-field.full-width {
            grid-column: 1 / -1;
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

        .form-field input,
        .form-field textarea {
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

        .form-field input:focus,
        .form-field textarea:focus {
            outline: none;
            border-bottom-color: #111;
        }

        .form-field input::placeholder,
        .form-field textarea::placeholder {
            color: #999;
        }

        .form-field textarea {
            min-height: 100px;
            resize: vertical;
        }

        .form-field input[readonly],
        .form-field textarea[readonly] {
            color: #666;
            background: #fafafa;
        }

        .form-actions {
            margin-top: 3rem;
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

        .secondary-btn {
            padding: 1rem 2rem;
            background: transparent;
            color: #666;
            border: 1px solid #e5e5e5;
            font-size: 0.938rem;
            font-weight: 500;
            cursor: pointer;
            letter-spacing: 0.02em;
            text-decoration: none;
            display: inline-block;
            transition: all 0.2s ease;
        }

        .secondary-btn:hover {
            border-color: #111;
            color: #111;
        }

        .expertise-info {
            background: rgba(245, 158, 11, 0.05);
            border-left: 2px solid #f59e0b;
            padding: 1rem;
            margin-bottom: 2rem;
            font-size: 0.875rem;
            color: #92400e;
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
            <a href="${pageContext.request.contextPath}/gp/dashboard" class="back-link">← Back to Dashboard</a>
        </div>
    </div>

    <div class="container">
        <div class="page-title">
            <h2>
                Consultation Details
                <c:choose>
                    <c:when test="${consultation.status == 'IN_PROGRESS'}">
                        <span class="status-badge status-in-progress">In Progress</span>
                    </c:when>
                    <c:when test="${consultation.status == 'AWAITING_SPECIALIST_ADVICE'}">
                        <span class="status-badge status-awaiting">Awaiting Specialist</span>
                    </c:when>
                    <c:when test="${consultation.status == 'COMPLETED'}">
                        <span class="status-badge status-completed">Completed</span>
                    </c:when>
                </c:choose>
            </h2>
        </div>
        <p class="page-subtitle">Consultation #${consultation.id} - ${consultation.consultationDate.year}-${consultation.consultationDate.monthValue < 10 ? '0' : ''}${consultation.consultationDate.monthValue}-${consultation.consultationDate.dayOfMonth < 10 ? '0' : ''}${consultation.consultationDate.dayOfMonth}</p>

        <c:if test="${not empty error}">
            <div class="error-message">${error}</div>
        </c:if>

        <!-- Patient Information -->
        <div class="info-card">
            <h3>Patient Information</h3>
            <div class="info-grid">
                <div class="info-item">
                    <span class="info-label">Name</span>
                    <span class="info-value">${patient.fname} ${patient.lname}</span>
                </div>
                <div class="info-item">
                    <span class="info-label">Birth Date</span>
                    <span class="info-value">${patient.birthDate}</span>
                </div>
                <div class="info-item">
                    <span class="info-label">SSN</span>
                    <span class="info-value">${patient.socialSecurityNumber}</span>
                </div>
                <div class="info-item">
                    <span class="info-label">Phone</span>
                    <span class="info-value">${patient.phone}</span>
                </div>
                <c:if test="${not empty patient.allergies}">
                    <div class="info-item">
                        <span class="info-label">Allergies</span>
                        <span class="info-value">${patient.allergies}</span>
                    </div>
                </c:if>
                <c:if test="${not empty patient.currentTreatments}">
                    <div class="info-item">
                        <span class="info-label">Current Treatments</span>
                        <span class="info-value">${patient.currentTreatments}</span>
                    </div>
                </c:if>
            </div>
        </div>

        <!-- Medical History -->
        <c:if test="${not empty consultations and consultations.size() > 0}">
            <div class="info-card">
                <h3>Medical History</h3>
                <div class="table-container" style="margin-top: 1rem;">
                    <table style="width: 100%; border-collapse: collapse;">
                        <thead style="border-bottom: 1px solid #e5e5e5;">
                            <tr>
                                <th style="padding: 0.75rem; text-align: left; font-size: 0.75rem; font-weight: 500; color: #666; text-transform: uppercase; letter-spacing: 0.05em;">Date</th>
                                <th style="padding: 0.75rem; text-align: left; font-size: 0.75rem; font-weight: 500; color: #666; text-transform: uppercase; letter-spacing: 0.05em;">Reason</th>
                                <th style="padding: 0.75rem; text-align: left; font-size: 0.75rem; font-weight: 500; color: #666; text-transform: uppercase; letter-spacing: 0.05em;">Diagnosis</th>
                                <th style="padding: 0.75rem; text-align: left; font-size: 0.75rem; font-weight: 500; color: #666; text-transform: uppercase; letter-spacing: 0.05em;">Treatment</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="pastConsultation" items="${consultations}">
                                <tr style="border-bottom: 1px solid #e5e5e5;">
                                    <td style="padding: 0.75rem; font-size: 0.875rem; color: #111;">
                                        ${pastConsultation.consultationDate.year}-${pastConsultation.consultationDate.monthValue < 10 ? '0' : ''}${pastConsultation.consultationDate.monthValue}-${pastConsultation.consultationDate.dayOfMonth < 10 ? '0' : ''}${pastConsultation.consultationDate.dayOfMonth}
                                    </td>
                                    <td style="padding: 0.75rem; font-size: 0.875rem; color: #111;">${pastConsultation.reason}</td>
                                    <td style="padding: 0.75rem; font-size: 0.875rem; color: #666;">
                                        ${not empty pastConsultation.diagnosis ? pastConsultation.diagnosis : '-'}
                                    </td>
                                    <td style="padding: 0.75rem; font-size: 0.875rem; color: #666;">
                                        ${not empty pastConsultation.treatment ? pastConsultation.treatment : '-'}
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </c:if>

        <!-- Current Consultation Details -->
        <div class="info-card">
            <h3>Current Consultation</h3>
            <div class="info-grid">
                <div class="info-item">
                    <span class="info-label">Reason</span>
                    <span class="info-value">${consultation.reason}</span>
                </div>
                <div class="info-item">
                    <span class="info-label">Cost</span>
                    <span class="info-value">${consultation.cost} DH</span>
                </div>
                <c:if test="${not empty consultation.symptoms}">
                    <div class="info-item" style="grid-column: 1 / -1;">
                        <span class="info-label">Symptoms</span>
                        <span class="info-value">${consultation.symptoms}</span>
                    </div>
                </c:if>
                <c:if test="${not empty consultation.observations}">
                    <div class="info-item" style="grid-column: 1 / -1;">
                        <span class="info-label">Observations</span>
                        <span class="info-value">${consultation.observations}</span>
                    </div>
                </c:if>
            </div>
        </div>

        <!-- Show expertise status if waiting for specialist -->
        <c:if test="${consultation.status == 'AWAITING_SPECIALIST_ADVICE'}">
            <c:choose>
                <c:when test="${not empty expertiseRequest and expertiseRequest.status == 'COMPLETED'}">
                    <!-- Display Specialist Response -->
                    <div class="info-card" style="background: rgba(34, 197, 94, 0.05); border-color: #22c55e;">
                        <h3 style="color: #16a34a;">Specialist Response Received</h3>
                        <div class="info-grid">
                            <div class="info-item" style="grid-column: 1 / -1;">
                                <span class="info-label">Specialist</span>
                                <span class="info-value">Dr. ${expertiseRequest.specialistName} (${expertiseRequest.specialistSpecialty})</span>
                            </div>
                            <div class="info-item" style="grid-column: 1 / -1;">
                                <span class="info-label">Medical Advice</span>
                                <div style="margin-top: 0.5rem; padding: 1rem; background: #fff; border-left: 2px solid #22c55e; white-space: pre-wrap;">
                                    ${expertiseRequest.advice}
                                </div>
                            </div>
                            <div class="info-item" style="grid-column: 1 / -1;">
                                <span class="info-label">Recommendations</span>
                                <div style="margin-top: 0.5rem; padding: 1rem; background: #fff; border-left: 2px solid #22c55e; white-space: pre-wrap;">
                                    ${expertiseRequest.recommendations}
                                </div>
                            </div>
                            <div class="info-item">
                                <span class="info-label">Response Date</span>
                                <span class="info-value">
                                    ${expertiseRequest.respondedAt.year}-${expertiseRequest.respondedAt.monthValue < 10 ? '0' : ''}${expertiseRequest.respondedAt.monthValue}-${expertiseRequest.respondedAt.dayOfMonth < 10 ? '0' : ''}${expertiseRequest.respondedAt.dayOfMonth}
                                </span>
                            </div>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="expertise-info">
                        <strong>Awaiting Specialist Response:</strong> This consultation is pending expert advice from a specialist.
                        You can still complete the consultation or wait for the specialist's recommendations.
                    </div>
                </c:otherwise>
            </c:choose>
        </c:if>

        <!-- Diagnosis and Treatment Form (Only if not completed) -->
        <c:if test="${consultation.status != 'COMPLETED'}">
            <h3 class="section-title">Diagnosis & Treatment</h3>

            <form action="${pageContext.request.contextPath}/gp/consultations/view" method="post" id="completeForm">
                <input type="hidden" name="consultationId" value="${consultation.id}">
                <input type="hidden" name="action" value="complete">

                <div class="form-grid">
                    <div class="form-field full-width">
                        <label for="diagnosis">Diagnosis *</label>
                        <textarea id="diagnosis" name="diagnosis" required
                                  placeholder="Enter your diagnosis">${consultation.diagnosis}</textarea>
                    </div>

                    <div class="form-field full-width">
                        <label for="treatment">Treatment *</label>
                        <textarea id="treatment" name="treatment" required
                                  placeholder="Enter prescribed treatment and recommendations">${consultation.treatment}</textarea>
                    </div>
                </div>

                <div class="form-actions">
                    <button type="submit" class="submit-btn">Complete Consultation</button>

                    <c:if test="${consultation.status != 'AWAITING_SPECIALIST_ADVICE'}">
                        <button type="button" class="secondary-btn"
                                onclick="document.getElementById('expertiseForm').submit();">
                            Request Specialist Advice
                        </button>
                    </c:if>

                    <a href="${pageContext.request.contextPath}/gp/dashboard" class="secondary-btn">Cancel</a>
                </div>
            </form>

            <!-- Hidden form for requesting expertise -->
            <c:if test="${consultation.status != 'AWAITING_SPECIALIST_ADVICE'}">
                <form action="${pageContext.request.contextPath}/gp/consultations/view" method="post" id="expertiseForm" style="display: none;">
                    <input type="hidden" name="consultationId" value="${consultation.id}">
                    <input type="hidden" name="action" value="requestExpertise">
                </form>
            </c:if>
        </c:if>

        <!-- Show diagnosis and treatment if completed -->
        <c:if test="${consultation.status == 'COMPLETED'}">
            <div class="info-card">
                <h3>Diagnosis & Treatment</h3>
                <div class="info-grid">
                    <div class="info-item" style="grid-column: 1 / -1;">
                        <span class="info-label">Diagnosis</span>
                        <span class="info-value">${consultation.diagnosis}</span>
                    </div>
                    <div class="info-item" style="grid-column: 1 / -1;">
                        <span class="info-label">Treatment</span>
                        <span class="info-value">${consultation.treatment}</span>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
</body>
</html>
