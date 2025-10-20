package com.tele.servlet.GP.Consultation;

import com.tele.dto.ConsultationDTO;
import com.tele.dto.GeneralPractitionerDTO;
import com.tele.dto.PatientDTO;
import com.tele.dto.QueueEntryDTO;
import com.tele.model.enums.QueueStatus;
import com.tele.service.*;
import com.tele.service.impl.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/gp/consultations/create")
public class CreateServlet extends HttpServlet {
    private ConsultationService consultationService;
    private PatientService patientService;
    private QueueService queueService;
    private GeneralPractitionerService generalPractitionerService;

    @Override
    public void init() {
        consultationService = new ConsultationServiceImpl();
        patientService = new PatientServiceImpl();
        queueService = new QueueServiceImpl();
        generalPractitionerService = new GeneralPractitionerServiceImpl();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String patientIdParam = request.getParameter("patientId");
        if (patientIdParam == null || patientIdParam.isEmpty()) {
            request.setAttribute("error", "Patient ID is required.");
            request.getRequestDispatcher("/WEB-INF/views/gp/queue.jsp").forward(request, response);
            return;
        }

        try {
            Long patientId = Long.parseLong(patientIdParam);
            Optional<PatientDTO> patientOpt = patientService.findById(patientId);

            if (patientOpt.isEmpty()) {
                request.setAttribute("error", "Patient not found.");
                request.getRequestDispatcher("/WEB-INF/views/gp/queue.jsp").forward(request, response);
                return;
            }

            PatientDTO patient = patientOpt.get();
            List<ConsultationDTO> consultations = consultationService.findByPatient(patientId);
            request.setAttribute("patient", patient);
            request.setAttribute("consultations", consultations);
            request.getRequestDispatcher("/WEB-INF/views/gp/create_consultation.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/gp/queue.jsp").forward(request, response);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long patientId = Long.parseLong(request.getParameter("patientId"));
            String reason = request.getParameter("reason");
            String symptoms = request.getParameter("symptoms");
            String observations = request.getParameter("observations");
            if (reason == null || reason.trim().isEmpty()) {
                Optional<PatientDTO> patientOpt = patientService.findById(patientId);
                if (patientOpt.isPresent()) {
                    List<ConsultationDTO> consultations = consultationService.findByPatient(patientId);
                    request.setAttribute("patient", patientOpt.get());
                    request.setAttribute("consultations", consultations);
                    request.setAttribute("error", "Reason for consultation is required.");
                    request.getRequestDispatcher("/WEB-INF/views/gp/create_consultation.jsp").forward(request,
                            response);
                    return;
                }
            }

            Long gpId = (Long) request.getSession().getAttribute(AuthServiceImpl.USER_ID);
            ConsultationDTO consultation = consultationService.createConsultation(patientId, gpId, reason.trim());
            if ((observations != null && !observations.trim().isEmpty()) ||
                    (symptoms != null && !symptoms.trim().isEmpty())) {
                consultationService.updateConsultation(
                        consultation.getId(),
                        observations != null ? observations.trim() : null,
                        symptoms != null ? symptoms.trim() : null,
                        null,
                        null);
            }

            Optional<QueueEntryDTO> queueEntry = queueService.getActiveQueueEntryByPatientId(patientId);

            if (queueEntry.isPresent()) {
                queueService.updateQueueEntryStatus(queueEntry.get().getId(), QueueStatus.COMPLETED);
            }

            response.sendRedirect(
                    request.getContextPath() + "/gp/consultations/view?consultationId=" + consultation.getId());
        } catch (Exception e) {
            request.setAttribute("error", "An error occurred: " + e.getMessage());
            e.printStackTrace();
            request.getRequestDispatcher("/WEB-INF/views/gp/create_consultation.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        consultationService = null;
        patientService = null;
        queueService = null;
    }
}
