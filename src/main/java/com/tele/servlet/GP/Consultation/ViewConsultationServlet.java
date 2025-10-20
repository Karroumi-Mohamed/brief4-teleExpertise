package com.tele.servlet.GP.Consultation;

import com.tele.dto.ConsultationDTO;
import com.tele.dto.ExpertiseRequestDTO;
import com.tele.dto.PatientDTO;
import com.tele.model.enums.ConsultationStatus;
import com.tele.service.ConsultationService;
import com.tele.service.ExpertiseRequestService;
import com.tele.service.PatientService;
import com.tele.service.impl.AuthServiceImpl;
import com.tele.service.impl.ConsultationServiceImpl;
import com.tele.service.impl.ExpertiseRequestServiceImpl;
import com.tele.service.impl.PatientServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/gp/consultations/view")
public class ViewConsultationServlet extends HttpServlet {
    private ConsultationService consultationService;
    private PatientService patientService;
    private ExpertiseRequestService expertiseRequestService;

    @Override
    public void init() {
        consultationService = new ConsultationServiceImpl();
        patientService = new PatientServiceImpl();
        expertiseRequestService = new ExpertiseRequestServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String consultationIdParam = request.getParameter("consultationId");
        if (consultationIdParam == null || consultationIdParam.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/gp/dashboard");
            return;
        }

        try {
            Long consultationId = Long.parseLong(consultationIdParam);
            Optional<ConsultationDTO> consultationOpt = consultationService.findById(consultationId);

            if (consultationOpt.isEmpty()) {
                request.getSession().setAttribute("error", "Consultation not found.");
                response.sendRedirect(request.getContextPath() + "/gp/dashboard");
                return;
            }

            ConsultationDTO consultation = consultationOpt.get();

            Optional<PatientDTO> patientOpt = patientService.findById(consultation.getPatientId());
            if (patientOpt.isEmpty()) {
                request.getSession().setAttribute("error", "Patient not found.");
                response.sendRedirect(request.getContextPath() + "/gp/dashboard");
                return;
            }

            PatientDTO patient = patientOpt.get();

            List<ConsultationDTO> consultations = consultationService.findByPatient(patient.getId())
                    .stream()
                    .filter(c -> !c.getId().equals(consultationId))
                    .toList();

            request.setAttribute("consultation", consultation);
            request.setAttribute("patient", patient);
            request.setAttribute("consultations", consultations);

            // Fetch expertise request if consultation is awaiting specialist advice
            if (consultation.getStatus() == ConsultationStatus.AWAITING_SPECIALIST_ADVICE) {
                Long gpId = (Long) request.getSession().getAttribute(AuthServiceImpl.USER_ID);
                List<ExpertiseRequestDTO> expertiseRequests = expertiseRequestService.findByGP(gpId);

                // Find the expertise request for this consultation
                Optional<ExpertiseRequestDTO> expertiseRequestOpt = expertiseRequests.stream()
                        .filter(er -> er.getConsultationId().equals(consultationId))
                        .findFirst();

                expertiseRequestOpt.ifPresent(er -> request.setAttribute("expertiseRequest", er));
            }

            request.getRequestDispatcher("/WEB-INF/views/gp/view_consultation.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.getSession().setAttribute("error", "Invalid consultation ID.");
            response.sendRedirect(request.getContextPath() + "/gp/dashboard");
        } catch (Exception e) {
            request.getSession().setAttribute("error", "An error occurred: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/gp/dashboard");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String consultationIdParam = request.getParameter("consultationId");

        System.out.println("DEBUG: doPost called with action=" + action + ", consultationId=" + consultationIdParam);

        if (consultationIdParam == null || consultationIdParam.isEmpty()) {
            System.out.println("DEBUG: consultationId is null or empty, redirecting to dashboard");
            response.sendRedirect(request.getContextPath() + "/gp/dashboard");
            return;
        }

        try {
            Long consultationId = Long.parseLong(consultationIdParam);
            Long gpId = (Long) request.getSession().getAttribute(AuthServiceImpl.USER_ID);

            System.out.println("DEBUG: Parsed consultationId=" + consultationId + ", action=" + action);

            if ("complete".equals(action)) {
                System.out.println("DEBUG: Handling complete consultation");
                handleCompleteConsultation(request, response, consultationId);
            } else if ("requestExpertise".equals(action)) {
                System.out.println("DEBUG: Handling request expertise");
                handleRequestExpertise(request, response, consultationId);
            } else {
                System.out.println("DEBUG: Unknown action, redirecting to view consultation");
                response.sendRedirect(request.getContextPath() + "/gp/consultations/view?consultationId=" + consultationId);
            }

        } catch (Exception e) {
            System.out.println("DEBUG: Exception in doPost: " + e.getMessage());
            e.printStackTrace();
            request.getSession().setAttribute("error", "An error occurred: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/gp/dashboard");
        }
    }

    private void handleCompleteConsultation(HttpServletRequest request, HttpServletResponse response, Long consultationId)
            throws IOException {

        String diagnosis = request.getParameter("diagnosis");
        String treatment = request.getParameter("treatment");

        // Validate required fields for completion
        if (diagnosis == null || diagnosis.trim().isEmpty() ||
            treatment == null || treatment.trim().isEmpty()) {
            request.getSession().setAttribute("error", "Diagnosis and treatment are required to complete consultation.");
            response.sendRedirect(request.getContextPath() + "/gp/consultations/view?consultationId=" + consultationId);
            return;
        }

        // Update consultation with diagnosis and treatment
        consultationService.updateConsultation(
                consultationId,
                null, // observations already set
                null, // symptoms already set
                diagnosis.trim(),
                treatment.trim()
        );

        // Mark consultation as completed
        consultationService.completeConsultation(consultationId);

        request.getSession().setAttribute("successMessage", "Consultation completed successfully.");
        response.sendRedirect(request.getContextPath() + "/gp/dashboard");
    }

    private void handleRequestExpertise(HttpServletRequest request, HttpServletResponse response, Long consultationId)
            throws IOException {

        System.out.println("DEBUG: handleRequestExpertise called with consultationId=" + consultationId);
        String redirectUrl = request.getContextPath() + "/gp/expertise/request?consultationId=" + consultationId;
        System.out.println("DEBUG: Redirecting to: " + redirectUrl);

        // Just redirect to expertise request form - don't change status yet
        // Status will be changed when the form is actually submitted
        response.sendRedirect(redirectUrl);
    }

    @Override
    public void destroy() {
        consultationService = null;
        patientService = null;
        expertiseRequestService = null;
    }
}
