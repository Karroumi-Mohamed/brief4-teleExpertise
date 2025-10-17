<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard - TeleExpertise</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
        }
        .header {
            background-color: #2c3e50;
            color: white;
            padding: 1rem;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .header h1 {
            margin: 0;
        }
        .logout-btn {
            background-color: #e74c3c;
            color: white;
            padding: 0.5rem 1rem;
            text-decoration: none;
            border-radius: 4px;
        }
        .logout-btn:hover {
            background-color: #c0392b;
        }
        .container {
            max-width: 1200px;
            margin: 2rem auto;
            padding: 0 1rem;
        }
        .dashboard-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
            gap: 2rem;
            margin-top: 2rem;
        }
        .card {
            background: white;
            padding: 2rem;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            text-align: center;
        }
        .card h3 {
            color: #2c3e50;
            margin-bottom: 1rem;
        }
        .card p {
            color: #7f8c8d;
            margin-bottom: 1.5rem;
        }
        .btn {
            display: inline-block;
            padding: 0.75rem 1.5rem;
            background-color: #3498db;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            transition: background-color 0.3s;
        }
        .btn:hover {
            background-color: #2980b9;
        }
        .btn-success {
            background-color: #27ae60;
        }
        .btn-success:hover {
            background-color: #229954;
        }
        .welcome {
            background: white;
            padding: 2rem;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            text-align: center;
        }
        .welcome h2 {
            color: #2c3e50;
            margin-bottom: 1rem;
        }
        .welcome p {
            color: #7f8c8d;
        }
    </style>
</head>
<body>
    <div class="header">
        <h1>TeleExpertise - Admin Dashboard</h1>
        <a href="${pageContext.request.contextPath}/logout" class="logout-btn">Logout</a>
    </div>

    <div class="container">
        <div class="welcome">
            <h2>Welcome, Administrator</h2>
            <p>Manage staff members and oversee the medical consultation platform</p>
        </div>

        <div class="dashboard-grid">
            <div class="card">
                <h3>Create Staff</h3>
                <p>Add new nurses, general practitioners, and specialists to the system</p>
                <a href="${pageContext.request.contextPath}/admin/create-staff" class="btn btn-success">Create New Staff</a>
            </div>

            <div class="card">
                <h3>View Staff</h3>
                <p>View and manage all registered staff members in the system</p>
                <a href="${pageContext.request.contextPath}/admin/staff-list" class="btn">View Staff List</a>
            </div>

            <div class="card">
                <h3>System Overview</h3>
                <p>Monitor system activity and generate reports</p>
                <a href="#" class="btn">View Reports</a>
            </div>
        </div>
    </div>
</body>
</html>