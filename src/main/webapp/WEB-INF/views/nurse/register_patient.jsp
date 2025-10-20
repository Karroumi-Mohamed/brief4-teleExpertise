<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register Patient - TeleExpertise</title>
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

        .success-message {
            background: rgba(34, 197, 94, 0.05);
            border-left: 2px solid #22c55e;
            color: #16a34a;
            padding: 0.875rem 1rem;
            margin-bottom: 2rem;
            font-size: 0.875rem;
        }

        .info-message {
            background: rgba(59, 130, 246, 0.05);
            border-left: 2px solid #3b82f6;
            color: #2563eb;
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
            min-height: 80px;
            resize: vertical;
        }

        .form-field input[readonly] {
            color: #666;
            cursor: not-allowed;
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

        .search-form {
            max-width: 500px;
            margin: 0 auto;
        }

        @media (max-width: 768px) {
            .form-row,
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
            <a href="${pageContext.request.contextPath}/nurse/dashboard" class="back-link">← Back to Dashboard</a>
        </div>
    </div>

    <div class="container">
        <div class="page-title">
            <h2>Register Patient</h2>
        </div>
        <p class="page-subtitle">Search for existing patient or register new</p>

        <c:if test="${not empty error}">
            <div class="error-message">${error}</div>
        </c:if>

        <c:if test="${not empty successMessage}">
            <div class="success-message">${successMessage}</div>
        </c:if>

        <!-- INITIAL STATE: Search Form -->
        <c:if test="${not searchPerformed}">
            <form action="${pageContext.request.contextPath}/nurse/patients/register" method="post" class="search-form">
                <input type="hidden" name="action" value="search">

                <div class="form-grid">
                    <div class="form-field full-width">
                        <label for="ssn">Social Security Number</label>
                        <input type="text" id="ssn" name="ssn" required
                               placeholder="Enter patient's SSN">
                    </div>
                </div>

                <div class="form-actions">
                    <button type="submit" class="submit-btn">Search Patient</button>
                </div>
            </form>
        </c:if>

        <!-- PATIENT FOUND: Update Vital Signs -->
        <c:if test="${searchPerformed and patientFound}">
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
            </div>

            <h3 class="section-title">Update Vital Signs</h3>

            <form action="${pageContext.request.contextPath}/nurse/patients/register" method="post">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="patientId" value="${patient.id}">

                <div class="form-grid">
                    <div class="form-row">
                        <div class="form-field">
                            <label for="bloodPressure">Blood Pressure</label>
                            <input type="text" id="bloodPressure" name="bloodPressure"
                                   placeholder="e.g., 120/80">
                        </div>

                        <div class="form-field">
                            <label for="heartRate">Heart Rate (bpm)</label>
                            <input type="number" id="heartRate" name="heartRate"
                                   placeholder="e.g., 75">
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-field">
                            <label for="temperature">Temperature (°C)</label>
                            <input type="number" id="temperature" name="temperature"
                                   step="0.1" placeholder="e.g., 37.0">
                        </div>

                        <div class="form-field">
                            <label for="respiratoryRate">Respiratory Rate (breaths/min)</label>
                            <input type="number" id="respiratoryRate" name="respiratoryRate"
                                   placeholder="e.g., 16">
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-field">
                            <label for="weight">Weight (kg)</label>
                            <input type="number" id="weight" name="weight"
                                   step="0.1" placeholder="e.g., 70.5">
                        </div>

                        <div class="form-field">
                            <label for="height">Height (cm)</label>
                            <input type="number" id="height" name="height"
                                   step="0.1" placeholder="e.g., 175.0">
                        </div>
                    </div>
                </div>

                <div class="form-actions">
                    <button type="submit" class="submit-btn">Update & Add to Queue</button>
                    <a href="${pageContext.request.contextPath}/nurse/patients/register" class="cancel-btn">Cancel</a>
                </div>
            </form>
        </c:if>

        <!-- PATIENT NOT FOUND: Create New Patient -->
        <c:if test="${searchPerformed and patientNotFound}">
            <div class="info-message">
                Patient with SSN "${searchedSSN}" not found. Please register as new patient.
            </div>

            <h3 class="section-title">New Patient Registration</h3>

            <form action="${pageContext.request.contextPath}/nurse/patients/register" method="post">
                <input type="hidden" name="action" value="create">

                <div class="form-grid">
                    <div class="form-row">
                        <div class="form-field">
                            <label for="firstName">First Name *</label>
                            <input type="text" id="firstName" name="firstName" required>
                        </div>

                        <div class="form-field">
                            <label for="lastName">Last Name *</label>
                            <input type="text" id="lastName" name="lastName" required>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-field">
                            <label for="birthDate">Birth Date *</label>
                            <input type="date" id="birthDate" name="birthDate" required>
                        </div>

                        <div class="form-field">
                            <label for="ssn2">Social Security Number *</label>
                            <input type="text" id="ssn2" name="ssn" required
                                   value="${searchedSSN}" readonly>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-field">
                            <label for="phone">Phone *</label>
                            <input type="tel" id="phone" name="phone" required
                                   placeholder="e.g., 0612345678">
                        </div>

                        <div class="form-field">
                            <label for="mutuelle">Insurance (Mutuelle)</label>
                            <input type="text" id="mutuelle" name="mutuelle"
                                   placeholder="Insurance provider name">
                        </div>
                    </div>

                    <div class="form-field full-width">
                        <label for="address">Address</label>
                        <input type="text" id="address" name="address"
                               placeholder="Full address">
                    </div>

                    <div class="form-field full-width">
                        <label for="allergies">Allergies</label>
                        <textarea id="allergies" name="allergies"
                                  placeholder="List any known allergies"></textarea>
                    </div>

                    <div class="form-field full-width">
                        <label for="currentTreatments">Current Treatments</label>
                        <textarea id="currentTreatments" name="currentTreatments"
                                  placeholder="List current medications or treatments"></textarea>
                    </div>
                </div>

                <h3 class="section-title">Vital Signs</h3>

                <div class="form-grid">
                    <div class="form-row">
                        <div class="form-field">
                            <label for="bloodPressure2">Blood Pressure</label>
                            <input type="text" id="bloodPressure2" name="bloodPressure"
                                   placeholder="e.g., 120/80">
                        </div>

                        <div class="form-field">
                            <label for="heartRate2">Heart Rate (bpm)</label>
                            <input type="number" id="heartRate2" name="heartRate"
                                   placeholder="e.g., 75">
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-field">
                            <label for="temperature2">Temperature (°C)</label>
                            <input type="number" id="temperature2" name="temperature"
                                   step="0.1" placeholder="e.g., 37.0">
                        </div>

                        <div class="form-field">
                            <label for="respiratoryRate2">Respiratory Rate (breaths/min)</label>
                            <input type="number" id="respiratoryRate2" name="respiratoryRate"
                                   placeholder="e.g., 16">
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-field">
                            <label for="weight2">Weight (kg)</label>
                            <input type="number" id="weight2" name="weight"
                                   step="0.1" placeholder="e.g., 70.5">
                        </div>

                        <div class="form-field">
                            <label for="height2">Height (cm)</label>
                            <input type="number" id="height2" name="height"
                                   step="0.1" placeholder="e.g., 175.0">
                        </div>
                    </div>
                </div>

                <div class="form-actions">
                    <button type="submit" class="submit-btn">Register & Add to Queue</button>
                    <a href="${pageContext.request.contextPath}/nurse/patients/register" class="cancel-btn">Cancel</a>
                </div>
            </form>
        </c:if>
    </div>
</body>
</html>
