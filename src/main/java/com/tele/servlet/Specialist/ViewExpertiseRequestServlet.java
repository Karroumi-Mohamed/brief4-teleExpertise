package com.tele.servlet.Specialist;

import com.tele.dto.ConsultationDTO;
import com.tele.dto.ExpertiseRequestDTO;
import com.tele.dto.PatientDTO;
import com.tele.model.Specialist;
import com.tele.model.User;
import com.tele.service.ConsultationService;
import com.tele.service.ExpertiseRequestService;
import com.tele.service.PatientService;
import com.tele.service.UserService;
import com.tele.service.impl.AuthServiceImpl;
import com.tele.service.impl.ConsultationServiceImpl;
import com.tele.service.impl.ExpertiseRequestServiceImpl;
import com.tele.service.impl.PatientServiceImpl;
import com.tele.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/specialist/expertise-requests/view")
public class ViewExpertiseRequestServlet extends HttpServlet {
    private ExpertiseRequestService expertiseRequestService;
    private ConsultationService consultationService;
    private PatientService patientService;
    private UserService userService;

    @Override
    public void init() {
        expertiseRequestService = new ExpertiseRequestServiceImpl();
        consultationService = new ConsultationServiceImpl();
        patientService = new PatientServiceImpl();
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // AuthFilter already verified authentication and authorization
        // Just get the user ID from session
        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute(AuthServiceImpl.USER_ID);

        Optional<User> userOpt = userService.findById(userId);
        if (userOpt.isEmpty() || !(userOpt.get() instanceof Specialist)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Specialist specialist = (Specialist) userOpt.get();

        try {
            Long requestId = Long.parseLong(request.getParameter("id"));

            // Get expertise request
            Optional<ExpertiseRequestDTO> expertiseRequestOpt = expertiseRequestService.findById(requestId);
            if (!expertiseRequestOpt.isPresent()) {
                response.sendRedirect(request.getContextPath() + "/specialist/expertise-requests");
                return;
            }

            ExpertiseRequestDTO expertiseRequest = expertiseRequestOpt.get();

            // Verify this request belongs to this specialist
            if (!expertiseRequest.getSpecialistId().equals(specialist.getId())) {
                response.sendRedirect(request.getContextPath() + "/specialist/expertise-requests");
                return;
            }

            // Get consultation details
            Optional<ConsultationDTO> consultationOpt = consultationService.findById(expertiseRequest.getConsultationId());
            if (!consultationOpt.isPresent()) {
                response.sendRedirect(request.getContextPath() + "/specialist/expertise-requests");
                return;
            }
            ConsultationDTO consultation = consultationOpt.get();

            // Get patient details and medical history
            Optional<PatientDTO> patientOpt = patientService.findById(consultation.getPatientId());
            if (!patientOpt.isPresent()) {
                response.sendRedirect(request.getContextPath() + "/specialist/expertise-requests");
                return;
            }
            PatientDTO patient = patientOpt.get();

            // Get patient's medical history
            List<ConsultationDTO> medicalHistory = consultationService.findByPatient(patient.getId())
                    .stream()
                    .filter(c -> !c.getId().equals(consultation.getId()) && c.getStatus().toString().equals("COMPLETED"))
                    .toList();

            request.setAttribute("expertiseRequest", expertiseRequest);
            request.setAttribute("consultation", consultation);
            request.setAttribute("patient", patient);
            request.setAttribute("medicalHistory", medicalHistory);

            request.getRequestDispatcher("/WEB-INF/views/specialist/view_expertise_request.jsp")
                    .forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/specialist/expertise-requests");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // AuthFilter already verified authentication and authorization
        // Just get the user ID from session
        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute(AuthServiceImpl.USER_ID);

        Optional<User> userOpt = userService.findById(userId);
        if (userOpt.isEmpty() || !(userOpt.get() instanceof Specialist)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Specialist specialist = (Specialist) userOpt.get();

        try {
            Long requestId = Long.parseLong(request.getParameter("requestId"));
            String advice = request.getParameter("advice");
            String recommendations = request.getParameter("recommendations");

            // Validate required fields
            if (advice == null || advice.trim().isEmpty() ||
                recommendations == null || recommendations.trim().isEmpty()) {

                request.getSession().setAttribute("error", "Advice and recommendations are required.");
                response.sendRedirect(request.getContextPath() + "/specialist/expertise-requests/view?id=" + requestId);
                return;
            }

            // Verify request belongs to this specialist
            Optional<ExpertiseRequestDTO> expertiseRequestOpt = expertiseRequestService.findById(requestId);
            if (!expertiseRequestOpt.isPresent() ||
                !expertiseRequestOpt.get().getSpecialistId().equals(specialist.getId())) {

                response.sendRedirect(request.getContextPath() + "/specialist/expertise-requests");
                return;
            }

            // Submit expertise response
            expertiseRequestService.respondToRequest(requestId, advice.trim(), recommendations.trim());

            request.getSession().setAttribute("successMessage", "Expertise response submitted successfully.");
            response.sendRedirect(request.getContextPath() + "/specialist/expertise-requests");

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/specialist/expertise-requests");
        }
    }

    @Override
    public void destroy() {
        expertiseRequestService = null;
        consultationService = null;
        patientService = null;
        userService = null;
    }
}
