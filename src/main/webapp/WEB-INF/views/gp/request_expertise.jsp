<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Request Specialist Advice - TeleExpertise</title>
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

        .section-card {
            background: #fff;
            border: 1px solid #e5e5e5;
            padding: 2rem;
            margin-bottom: 2rem;
        }

        .section-card h3 {
            font-size: 1rem;
            font-weight: 500;
            text-transform: uppercase;
            letter-spacing: 0.05em;
            margin-bottom: 1.5rem;
            color: #666;
        }

        .filter-row {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 2rem;
            margin-bottom: 2rem;
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

        .form-field select,
        .form-field input,
        .form-field textarea {
            width: 100%;
            padding: 0.875rem;
            border: 1px solid #e5e5e5;
            background: #fff;
            font-size: 1rem;
            color: #111;
            font-family: inherit;
            transition: border-color 0.2s ease;
        }

        .form-field select:focus,
        .form-field input:focus,
        .form-field textarea:focus {
            outline: none;
            border-color: #111;
        }

        .form-field textarea {
            min-height: 100px;
            resize: vertical;
        }

        .specialist-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
            gap: 1rem;
            margin-bottom: 2rem;
        }

        .specialist-card {
            border: 2px solid #e5e5e5;
            padding: 1.5rem;
            cursor: pointer;
            transition: all 0.2s;
            background: #fff;
        }

        .specialist-card:hover {
            border-color: #999;
        }

        .specialist-card.selected {
            border-color: #111;
            background: #fafafa;
        }

        .specialist-card.hidden {
            display: none;
        }

        .specialist-name {
            font-weight: 600;
            font-size: 1.125rem;
            margin-bottom: 0.5rem;
        }

        .specialist-specialty {
            font-size: 0.813rem;
            color: #666;
            text-transform: uppercase;
            letter-spacing: 0.05em;
            margin-bottom: 0.75rem;
        }

        .specialist-fee {
            font-size: 1rem;
            color: #111;
            font-weight: 500;
        }

        .no-specialists {
            text-align: center;
            padding: 3rem;
            color: #999;
            font-size: 1rem;
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

        .submit-btn:hover:not(:disabled) {
            background: #000;
        }

        .submit-btn:disabled {
            background: #e5e5e5;
            color: #999;
            cursor: not-allowed;
        }

        .cancel-btn {
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

        .cancel-btn:hover {
            border-color: #111;
            color: #111;
        }

        @media (max-width: 768px) {
            .filter-row {
                grid-template-columns: 1fr;
            }

            .specialist-grid {
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
            <a href="${pageContext.request.contextPath}/gp/consultations/view?consultationId=${consultation.id}" class="back-link">← Back to Consultation</a>
        </div>
    </div>

    <div class="container">
        <div class="page-title">
            <h2>Request Specialist Advice</h2>
        </div>
        <p class="page-subtitle">Consultation #${consultation.id} - ${consultation.reason}</p>

        <c:if test="${not empty sessionScope.error}">
            <div class="error-message">
                ${sessionScope.error}
                <c:remove var="error" scope="session"/>
            </div>
        </c:if>

        <form action="${pageContext.request.contextPath}/gp/expertise/request" method="post" id="expertiseForm">
            <input type="hidden" name="consultationId" value="${consultation.id}">
            <input type="hidden" name="specialistId" id="selectedSpecialistId">

            <!-- Filter Section -->
            <div class="section-card">
                <h3>Select Specialty & Specialist</h3>

                <div class="filter-row">
                    <div class="form-field">
                        <label for="specialtyFilter">Filter by Specialty *</label>
                        <select id="specialtyFilter" required onchange="filterSpecialists()">
                            <option value="">-- Select Specialty --</option>
                            <c:forEach var="specialty" items="${specialties}">
                                <option value="${specialty}">${specialty}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-field">
                        <label for="sortBy">Sort By</label>
                        <select id="sortBy" onchange="sortSpecialists()">
                            <option value="fee-asc">Fee: Low to High</option>
                            <option value="fee-desc">Fee: High to Low</option>
                            <option value="name">Name: A-Z</option>
                        </select>
                    </div>
                </div>

                <!-- Specialists Grid -->
                <div class="specialist-grid" id="specialistGrid">
                    <c:forEach var="specialist" items="${specialists}">
                        <div class="specialist-card"
                             data-id="${specialist.id}"
                             data-specialty="${specialist.specialty}"
                             data-fee="${specialist.expertiseFee}"
                             data-name="${specialist.fname} ${specialist.lname}"
                             onclick="selectSpecialist(this, ${specialist.id})">
                            <div class="specialist-name">Dr. ${specialist.fname} ${specialist.lname}</div>
                            <div class="specialist-specialty">${specialist.specialty}</div>
                            <div class="specialist-fee">${specialist.expertiseFee} DH</div>
                        </div>
                    </c:forEach>
                </div>

                <div class="no-specialists" id="noSpecialists" style="display: none;">
                    No specialists found for the selected criteria.
                </div>
            </div>

            <!-- Request Details Section -->
            <div class="section-card">
                <h3>Expertise Request Details</h3>

                <div class="form-field" style="margin-bottom: 2rem;">
                    <label for="question">Question for Specialist *</label>
                    <textarea id="question" name="question" required
                              placeholder="Describe your question or concern for the specialist"></textarea>
                </div>

                <div class="form-field" style="margin-bottom: 2rem;">
                    <label for="medicalData">Additional Medical Data</label>
                    <textarea id="medicalData" name="medicalData"
                              placeholder="Include any test results, imaging reports, or other relevant medical information"></textarea>
                </div>

                <div class="form-field">
                    <label for="priority">Priority Level *</label>
                    <select id="priority" name="priority" required>
                        <option value="">-- Select Priority --</option>
                        <c:forEach var="p" items="${priorities}">
                            <option value="${p}">${p}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-actions">
                <button type="submit" class="submit-btn" id="submitBtn" disabled>Send Expertise Request</button>
                <a href="${pageContext.request.contextPath}/gp/consultations/view?consultationId=${consultation.id}" class="cancel-btn">Cancel</a>
            </div>
        </form>
    </div>

    <script>
        let selectedSpecialistId = null;
        const specialistCards = document.querySelectorAll('.specialist-card');

        function selectSpecialist(card, specialistId) {
            // Remove previous selection
            specialistCards.forEach(c => c.classList.remove('selected'));

            // Add new selection
            card.classList.add('selected');
            selectedSpecialistId = specialistId;

            // Update hidden field
            document.getElementById('selectedSpecialistId').value = specialistId;

            // Enable submit button
            validateForm();
        }

        function filterSpecialists() {
            const selectedSpecialty = document.getElementById('specialtyFilter').value;
            const grid = document.getElementById('specialistGrid');
            const noSpecialistsMsg = document.getElementById('noSpecialists');

            let visibleCount = 0;

            specialistCards.forEach(card => {
                const cardSpecialty = card.getAttribute('data-specialty');

                if (selectedSpecialty === '' || cardSpecialty === selectedSpecialty) {
                    card.classList.remove('hidden');
                    visibleCount++;
                } else {
                    card.classList.add('hidden');
                    card.classList.remove('selected');
                }
            });

            // Show/hide no specialists message
            if (visibleCount === 0) {
                noSpecialistsMsg.style.display = 'block';
                grid.style.display = 'none';
            } else {
                noSpecialistsMsg.style.display = 'none';
                grid.style.display = 'grid';
            }

            // Clear selection if hidden
            if (selectedSpecialistId) {
                const selectedCard = document.querySelector(`[data-id="${selectedSpecialistId}"]`);
                if (selectedCard && selectedCard.classList.contains('hidden')) {
                    selectedSpecialistId = null;
                    document.getElementById('selectedSpecialistId').value = '';
                    validateForm();
                }
            }

            sortSpecialists();
        }

        function sortSpecialists() {
            const sortBy = document.getElementById('sortBy').value;
            const grid = document.getElementById('specialistGrid');
            const cards = Array.from(specialistCards);

            cards.sort((a, b) => {
                if (sortBy === 'fee-asc') {
                    return parseFloat(a.getAttribute('data-fee')) - parseFloat(b.getAttribute('data-fee'));
                } else if (sortBy === 'fee-desc') {
                    return parseFloat(b.getAttribute('data-fee')) - parseFloat(a.getAttribute('data-fee'));
                } else if (sortBy === 'name') {
                    return a.getAttribute('data-name').localeCompare(b.getAttribute('data-name'));
                }
                return 0;
            });

            // Re-append cards in sorted order
            cards.forEach(card => grid.appendChild(card));
        }

        function validateForm() {
            const specialty = document.getElementById('specialtyFilter').value;
            const question = document.getElementById('question').value.trim();
            const priority = document.getElementById('priority').value;
            const submitBtn = document.getElementById('submitBtn');

            const isValid = specialty && selectedSpecialistId && question && priority;
            submitBtn.disabled = !isValid;
        }

        // Add validation listeners
        document.getElementById('specialtyFilter').addEventListener('change', validateForm);
        document.getElementById('question').addEventListener('input', validateForm);
        document.getElementById('priority').addEventListener('change', validateForm);

        // Sort by fee (low to high) by default on page load
        window.addEventListener('load', () => {
            sortSpecialists();
        });

        // Form submission handler
        document.getElementById('expertiseForm').addEventListener('submit', function(e) {
            if (!selectedSpecialistId) {
                e.preventDefault();
                alert('Please select a specialist before submitting.');
                return false;
            }
        });
    </script>
</body>
</html>
