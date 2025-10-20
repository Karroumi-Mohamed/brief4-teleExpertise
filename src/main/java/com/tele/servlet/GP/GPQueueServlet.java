package com.tele.servlet.GP;

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

@WebServlet("/gp/queue")
public class GPQueueServlet extends HttpServlet {

    private QueueService queueService;

    @Override
    public void init() {
        queueService = new QueueServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<QueueEntryDTO> waitingQueue = queueService.getQueueByStatus(QueueStatus.WAITING);

        request.setAttribute("waitingQueue", waitingQueue);
        request.getRequestDispatcher("/WEB-INF/views/gp/queue.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        queueService = null;
    }
}
