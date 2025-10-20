package com.tele.servlet.Nurse;

import com.tele.dto.PatientDTO;
import com.tele.dto.VitalSignsDTO;
import com.tele.service.PatientService;
import com.tele.service.QueueService;
import com.tele.service.impl.PatientServiceImpl;
import com.tele.service.impl.QueueServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@WebServlet("/nurse/patients/register")
public class NurseRegisterPatientServlet extends HttpServlet {

    private PatientService patientService;
    private QueueService queueService;

    @Override
    public void init() {
        patientService = new PatientServiceImpl();
        queueService = new QueueServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/nurse/register_patient.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("search".equals(action)) {
            handleSearch(request, response);
        } else if ("update".equals(action)) {
            handleUpdateVitalSigns(request, response);
        } else if ("create".equals(action)) {
            handleCreatePatient(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/nurse/patients/register");
        }
    }

    private void handleSearch(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String ssn = request.getParameter("ssn");

        if (ssn == null || ssn.trim().isEmpty()) {
            request.setAttribute("error", "Social Security Number is required");
            request.getRequestDispatcher("/WEB-INF/views/nurse/register_patient.jsp").forward(request, response);
            return;
        }

        Optional<PatientDTO> patientOpt = patientService.findBySocialSecurityNumber(ssn.trim());

        request.setAttribute("searchPerformed", true);
        request.setAttribute("searchedSSN", ssn.trim());

        if (patientOpt.isPresent()) {
            request.setAttribute("patient", patientOpt.get());
            request.setAttribute("patientFound", true);
        } else {
            request.setAttribute("patientNotFound", true);
        }

        request.getRequestDispatcher("/WEB-INF/views/nurse/register_patient.jsp").forward(request, response);
    }

    private void handleUpdateVitalSigns(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String patientIdStr = request.getParameter("patientId");
            Long patientId = Long.parseLong(patientIdStr);

            VitalSignsDTO vitalSigns = extractVitalSigns(request);

            patientService.updateVitalSigns(patientId, vitalSigns);

            queueService.addPatientToQueue(patientId);

            request.getSession().setAttribute("successMessage", "Patient vital signs updated and added to waiting queue");
            response.sendRedirect(request.getContextPath() + "/nurse/dashboard");

        } catch (Exception e) {
            request.setAttribute("error", "Error updating patient: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/nurse/register_patient.jsp").forward(request, response);
        }
    }

    private void handleCreatePatient(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String fname = request.getParameter("firstName");
            String lname = request.getParameter("lastName");
            String birthDateStr = request.getParameter("birthDate");
            String ssn = request.getParameter("ssn");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String mutuelle = request.getParameter("mutuelle");
            String allergies = request.getParameter("allergies");
            String currentTreatments = request.getParameter("currentTreatments");

            if (fname == null || lname == null || birthDateStr == null || ssn == null || phone == null ||
                    fname.trim().isEmpty() || lname.trim().isEmpty() || birthDateStr.trim().isEmpty() ||
                    ssn.trim().isEmpty() || phone.trim().isEmpty()) {
                request.setAttribute("error", "Required fields: First Name, Last Name, Birth Date, SSN, Phone");
                request.setAttribute("patientNotFound", true);
                request.setAttribute("searchPerformed", true);
                request.getRequestDispatcher("/WEB-INF/views/nurse/register_patient.jsp").forward(request, response);
                return;
            }

            LocalDate birthDate = LocalDate.parse(birthDateStr);
            VitalSignsDTO vitalSigns = extractVitalSigns(request);

            PatientDTO createdPatient = patientService.createPatient(
                    fname.trim(),
                    lname.trim(),
                    birthDate,
                    ssn.trim(),
                    phone.trim(),
                    address != null ? address.trim() : "",
                    mutuelle != null ? mutuelle.trim() : "",
                    allergies != null ? allergies.trim() : "",
                    currentTreatments != null ? currentTreatments.trim() : "",
                    vitalSigns
            );

            queueService.addPatientToQueue(createdPatient.getId());

            request.getSession().setAttribute("successMessage", "Patient registered successfully and added to waiting queue");
            response.sendRedirect(request.getContextPath() + "/nurse/dashboard");

        } catch (Exception e) {
            request.setAttribute("error", "Error creating patient: " + e.getMessage());
            request.setAttribute("patientNotFound", true);
            request.setAttribute("searchPerformed", true);
            request.getRequestDispatcher("/WEB-INF/views/nurse/register_patient.jsp").forward(request, response);
        }
    }

    private VitalSignsDTO extractVitalSigns(HttpServletRequest request) {
        String bloodPressure = request.getParameter("bloodPressure");
        String heartRateStr = request.getParameter("heartRate");
        String temperatureStr = request.getParameter("temperature");
        String respiratoryRateStr = request.getParameter("respiratoryRate");
        String weightStr = request.getParameter("weight");
        String heightStr = request.getParameter("height");

        Integer heartRate = (heartRateStr != null && !heartRateStr.trim().isEmpty())
                ? Integer.parseInt(heartRateStr.trim()) : null;
        Double temperature = (temperatureStr != null && !temperatureStr.trim().isEmpty())
                ? Double.parseDouble(temperatureStr.trim()) : null;
        Integer respiratoryRate = (respiratoryRateStr != null && !respiratoryRateStr.trim().isEmpty())
                ? Integer.parseInt(respiratoryRateStr.trim()) : null;
        Double weight = (weightStr != null && !weightStr.trim().isEmpty())
                ? Double.parseDouble(weightStr.trim()) : null;
        Double height = (heightStr != null && !heightStr.trim().isEmpty())
                ? Double.parseDouble(heightStr.trim()) : null;

        VitalSignsDTO vitalSigns = new VitalSignsDTO(
                bloodPressure,
                heartRate,
                temperature,
                respiratoryRate,
                weight,
                height,
                LocalDateTime.now()
        );

        return vitalSigns;
    }

    @Override
    public void destroy() {
        patientService = null;
        queueService = null;
    }
}
