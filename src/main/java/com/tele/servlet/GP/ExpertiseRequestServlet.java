package com.tele.servlet.GP;

import com.tele.dto.ConsultationDTO;
import com.tele.dto.SpecialistDTO;
import com.tele.dto.TimeSlotDTO;
import com.tele.model.enums.DaySlot;
import com.tele.model.enums.Priority;
import com.tele.model.enums.Specialty;
import com.tele.service.ConsultationService;
import com.tele.service.ExpertiseRequestService;
import com.tele.service.SpecialistService;
import com.tele.service.TimeSlotService;
import com.tele.service.impl.AuthServiceImpl;
import com.tele.service.impl.ConsultationServiceImpl;
import com.tele.service.impl.ExpertiseRequestServiceImpl;
import com.tele.service.impl.SpecialistServiceImpl;
import com.tele.service.impl.TimeSlotServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@WebServlet("/gp/expertise/request")
public class ExpertiseRequestServlet extends HttpServlet {
    private ExpertiseRequestService expertiseRequestService;
    private SpecialistService specialistService;
    private TimeSlotService timeSlotService;
    private ConsultationService consultationService;

    @Override
    public void init() {
        expertiseRequestService = new ExpertiseRequestServiceImpl();
        specialistService = new SpecialistServiceImpl();
        timeSlotService = new TimeSlotServiceImpl();
        consultationService = new ConsultationServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("DEBUG ExpertiseRequestServlet: doGet called");

        String consultationIdParam = request.getParameter("consultationId");
        System.out.println("DEBUG ExpertiseRequestServlet: consultationId=" + consultationIdParam);

        if (consultationIdParam == null || consultationIdParam.isEmpty()) {
            System.out.println("DEBUG ExpertiseRequestServlet: consultationId is null/empty, redirecting to dashboard");
            response.sendRedirect(request.getContextPath() + "/gp/dashboard");
            return;
        }

        try {
            Long consultationId = Long.parseLong(consultationIdParam);
            System.out.println("DEBUG ExpertiseRequestServlet: Finding consultation " + consultationId);

            Optional<ConsultationDTO> consultationOpt = consultationService.findById(consultationId);

            if (consultationOpt.isEmpty()) {
                System.out.println("DEBUG ExpertiseRequestServlet: Consultation not found");
                request.getSession().setAttribute("error", "Consultation not found.");
                response.sendRedirect(request.getContextPath() + "/gp/dashboard");
                return;
            }

            ConsultationDTO consultation = consultationOpt.get();
            request.setAttribute("consultation", consultation);

            System.out.println("DEBUG ExpertiseRequestServlet: Loading specialists");
            List<SpecialistDTO> allSpecialists = specialistService.findAll();
            System.out.println("DEBUG ExpertiseRequestServlet: Found " + allSpecialists.size() + " specialists");
            request.setAttribute("specialists", allSpecialists);

            request.setAttribute("specialties", Specialty.values());
            request.setAttribute("priorities", Priority.values());

            System.out.println("DEBUG ExpertiseRequestServlet: Forwarding to request_expertise.jsp");
            request.getRequestDispatcher("/WEB-INF/views/gp/request_expertise.jsp").forward(request, response);

        } catch (Exception e) {
            System.out.println("DEBUG ExpertiseRequestServlet: Exception occurred: " + e.getMessage());
            e.printStackTrace();
            request.getSession().setAttribute("error", "An error occurred: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/gp/dashboard");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Long consultationId = Long.parseLong(request.getParameter("consultationId"));
            Long specialistId = Long.parseLong(request.getParameter("specialistId"));
            String question = request.getParameter("question");
            String medicalData = request.getParameter("medicalData");
            Priority priority = Priority.valueOf(request.getParameter("priority"));

            // Validate required fields
            if (question == null || question.trim().isEmpty()) {
                request.getSession().setAttribute("error", "Question for specialist is required.");
                response.sendRedirect(request.getContextPath() + "/gp/expertise/request?consultationId=" + consultationId);
                return;
            }

            if (specialistId == null) {
                request.getSession().setAttribute("error", "Please select a specialist.");
                response.sendRedirect(request.getContextPath() + "/gp/expertise/request?consultationId=" + consultationId);
                return;
            }

            Long gpId = (Long) request.getSession().getAttribute(AuthServiceImpl.USER_ID);

            consultationService.requestExpertise(consultationId);

            expertiseRequestService.createExpertiseRequest(
                    consultationId,
                    gpId,
                    specialistId,
                    question.trim(),
                    medicalData != null ? medicalData.trim() : null,
                    priority
            );

            request.getSession().setAttribute("successMessage",
                    "Expertise request sent successfully. Specialist will respond within 24-48h.");
            response.sendRedirect(request.getContextPath() + "/gp/consultations/view?consultationId=" + consultationId);

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Error creating expertise request: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/gp/dashboard");
        }
    }

    @Override
    public void destroy() {
        expertiseRequestService = null;
        specialistService = null;
        timeSlotService = null;
        consultationService = null;
    }
}
