<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Create Room Reservation</h2>
	 <form action="/api/v1/reservation/save" method="post">
        <label for="room">Room:</label>
        <input type="text" id="room" name="room" /><br/>

        <label for="floor">Floor:</label>
        <input type="text" id="floor" name="floor" /><br/>

        <label for="roomName">Room Name:</label>
        <input type="text" id="roomName" name="roomName" /><br/>

        <label for="selectedDate">Selected Date:</label>
        <input type="date" id="selectedDate" name="selectedDate" /><br/>

        <label for="startTime">Start Time:</label>
        <input type="time" id="startTime" name="startTime" /><br/>

        <label for="endTime">End Time:</label>
        <input type="time" id="endTime" name="endTime" /><br/>

        <label for="capacity">Capacity:</label>
        <input type="number" id="capacity" name="capacity" /><br/>

        <button type="submit">Create Reservation</button>
    </form>
</body>
</html>