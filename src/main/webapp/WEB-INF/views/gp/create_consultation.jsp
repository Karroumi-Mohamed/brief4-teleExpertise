<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Consultation - TeleExpertise</title>
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
            max-width: 900px;
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
            max-width: 900px;
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

        .patient-info {
            background: #fff;
            border: 1px solid #e5e5e5;
            padding: 2rem;
            margin-bottom: 3rem;
        }

        .patient-info h3 {
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

        .vital-signs {
            margin-top: 1.5rem;
            padding-top: 1.5rem;
            border-top: 1px solid #e5e5e5;
        }

        .vital-signs-grid {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 1rem;
            margin-top: 1rem;
        }

        .vital-sign-item {
            display: flex;
            flex-direction: column;
        }

        .vital-label {
            font-size: 0.75rem;
            text-transform: uppercase;
            letter-spacing: 0.05em;
            color: #999;
            font-weight: 500;
            margin-bottom: 0.25rem;
        }

        .vital-value {
            color: #111;
            font-size: 0.938rem;
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

        .form-row {
            display: grid;
            grid-template-columns: 1fr 1fr;
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

        .cost-info {
            background: #fff;
            border: 1px solid #e5e5e5;
            padding: 1.5rem;
            margin-top: 2rem;
        }

        .cost-label {
            font-size: 0.813rem;
            text-transform: uppercase;
            letter-spacing: 0.05em;
            color: #666;
            font-weight: 500;
            margin-bottom: 0.5rem;
        }

        .cost-value {
            font-size: 1.5rem;
            font-weight: 600;
            letter-spacing: -0.02em;
            color: #111;
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
            .form-row,
            .info-grid,
            .vital-signs-grid {
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
            <a href="${pageContext.request.contextPath}/gp/queue" class="back-link">← Back to Queue</a>
        </div>
    </div>

    <div class="container">
        <div class="page-title">
            <h2>Create Consultation</h2>
        </div>
        <p class="page-subtitle">Start new consultation for patient</p>

        <c:if test="${not empty error}">
            <div class="error-message">${error}</div>
        </c:if>

        <!-- Patient Information -->
        <div class="patient-info">
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
                <c:if test="${not empty patient.mutuelle}">
                    <div class="info-item">
                        <span class="info-label">Insurance</span>
                        <span class="info-value">${patient.mutuelle}</span>
                    </div>
                </c:if>
                <c:if test="${not empty patient.allergies}">
                    <div class="info-item">
                        <span class="info-label">Allergies</span>
                        <span class="info-value">${patient.allergies}</span>
                    </div>
                </c:if>
            </div>

            <!-- Vital Signs -->
            <c:if test="${not empty patient.vitalSigns}">
                <div class="vital-signs">
                    <h4 class="info-label">Current Vital Signs</h4>
                    <div class="vital-signs-grid">
                        <c:if test="${not empty patient.vitalSigns.bloodPressure}">
                            <div class="vital-sign-item">
                                <span class="vital-label">Blood Pressure</span>
                                <span class="vital-value">${patient.vitalSigns.bloodPressure}</span>
                            </div>
                        </c:if>
                        <c:if test="${not empty patient.vitalSigns.heartRate}">
                            <div class="vital-sign-item">
                                <span class="vital-label">Heart Rate</span>
                                <span class="vital-value">${patient.vitalSigns.heartRate} bpm</span>
                            </div>
                        </c:if>
                        <c:if test="${not empty patient.vitalSigns.temperature}">
                            <div class="vital-sign-item">
                                <span class="vital-label">Temperature</span>
                                <span class="vital-value">${patient.vitalSigns.temperature} °C</span>
                            </div>
                        </c:if>
                        <c:if test="${not empty patient.vitalSigns.respiratoryRate}">
                            <div class="vital-sign-item">
                                <span class="vital-label">Respiratory Rate</span>
                                <span class="vital-value">${patient.vitalSigns.respiratoryRate} /min</span>
                            </div>
                        </c:if>
                        <c:if test="${not empty patient.vitalSigns.weight}">
                            <div class="vital-sign-item">
                                <span class="vital-label">Weight</span>
                                <span class="vital-value">${patient.vitalSigns.weight} kg</span>
                            </div>
                        </c:if>
                        <c:if test="${not empty patient.vitalSigns.height}">
                            <div class="vital-sign-item">
                                <span class="vital-label">Height</span>
                                <span class="vital-value">${patient.vitalSigns.height} cm</span>
                            </div>
                        </c:if>
                    </div>
                </div>
            </c:if>
        </div>

        <!-- Medical History -->
        <c:if test="${not empty consultations}">
            <div class="patient-info">
                <h3>Medical History</h3>
                <c:choose>
                    <c:when test="${consultations.size() == 0}">
                        <p style="color: #666; font-size: 0.938rem;">No previous consultations</p>
                    </c:when>
                    <c:otherwise>
                        <div class="table-container" style="margin-top: 1rem;">
                            <table style="width: 100%; border-collapse: collapse;">
                                <thead style="border-bottom: 1px solid #e5e5e5;">
                                    <tr>
                                        <th style="padding: 0.75rem; text-align: left; font-size: 0.75rem; font-weight: 500; color: #666; text-transform: uppercase; letter-spacing: 0.05em;">Date</th>
                                        <th style="padding: 0.75rem; text-align: left; font-size: 0.75rem; font-weight: 500; color: #666; text-transform: uppercase; letter-spacing: 0.05em;">Reason</th>
                                        <th style="padding: 0.75rem; text-align: left; font-size: 0.75rem; font-weight: 500; color: #666; text-transform: uppercase; letter-spacing: 0.05em;">Diagnosis</th>
                                        <th style="padding: 0.75rem; text-align: left; font-size: 0.75rem; font-weight: 500; color: #666; text-transform: uppercase; letter-spacing: 0.05em;">Treatment</th>
                                        <th style="padding: 0.75rem; text-align: left; font-size: 0.75rem; font-weight: 500; color: #666; text-transform: uppercase; letter-spacing: 0.05em;">Doctor</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="consultation" items="${consultations}">
                                        <tr style="border-bottom: 1px solid #e5e5e5;">
                                            <td style="padding: 0.75rem; font-size: 0.875rem; color: #111;">
                                                ${consultation.consultationDate.year}-${consultation.consultationDate.monthValue < 10 ? '0' : ''}${consultation.consultationDate.monthValue}-${consultation.consultationDate.dayOfMonth < 10 ? '0' : ''}${consultation.consultationDate.dayOfMonth}
                                            </td>
                                            <td style="padding: 0.75rem; font-size: 0.875rem; color: #111;">${consultation.reason}</td>
                                            <td style="padding: 0.75rem; font-size: 0.875rem; color: #666;">
                                                <c:choose>
                                                    <c:when test="${not empty consultation.diagnosis}">
                                                        ${consultation.diagnosis}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span style="color: #999;">-</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td style="padding: 0.75rem; font-size: 0.875rem; color: #666;">
                                                <c:choose>
                                                    <c:when test="${not empty consultation.treatment}">
                                                        ${consultation.treatment}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span style="color: #999;">-</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td style="padding: 0.75rem; font-size: 0.875rem; color: #666;">
                                                <c:choose>
                                                    <c:when test="${not empty consultation.generalPractitionerName}">
                                                        Dr. ${consultation.generalPractitionerName}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span style="color: #999;">-</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </c:if>

        <!-- Consultation Form -->
        <h3 class="section-title">Consultation Details</h3>

        <form action="${pageContext.request.contextPath}/gp/consultations/create" method="post">
            <input type="hidden" name="patientId" value="${patient.id}">

            <div class="form-grid">
                <div class="form-field full-width">
                    <label for="reason">Reason for Consultation *</label>
                    <input type="text" id="reason" name="reason" required
                           placeholder="e.g., Persistent cough and fever">
                </div>

                <div class="form-field full-width">
                    <label for="symptoms">Symptoms</label>
                    <textarea id="symptoms" name="symptoms"
                              placeholder="Describe patient's symptoms in detail"></textarea>
                </div>

                <div class="form-field full-width">
                    <label for="observations">Initial Observations</label>
                    <textarea id="observations" name="observations"
                              placeholder="Physical examination findings and observations"></textarea>
                </div>
            </div>

            <div class="cost-info">
                <div class="cost-label">Consultation Fee</div>
                <div class="cost-value">150.00 DH</div>
            </div>

            <div class="form-actions">
                <button type="submit" class="submit-btn">Create Consultation</button>
                <a href="${pageContext.request.contextPath}/gp/queue" class="cancel-btn">Cancel</a>
            </div>
        </form>
    </div>
</body>
</html>
