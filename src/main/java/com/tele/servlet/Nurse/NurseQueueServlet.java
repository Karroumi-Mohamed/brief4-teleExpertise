package com.tele.servlet.Nurse;

import com.tele.dto.QueueEntryDTO;
import com.tele.model.enums.QueueStatus;
import com.tele.service.QueueService;
import com.tele.service.impl.QueueServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/nurse/queue")
public class NurseQueueServlet extends HttpServlet {

    private QueueService queueService;

    @Override
    public void init() {
        queueService = new QueueServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get all queue entries with WAITING status, sorted by arrival time
        List<QueueEntryDTO> waitingQueue = queueService.getQueueByStatus(QueueStatus.WAITING);

        request.setAttribute("waitingQueue", waitingQueue);
        request.getRequestDispatcher("/WEB-INF/views/nurse/queue.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        queueService = null;
    }
}
