<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <meta charset="utf-8">
    <title>勤怠</title>
</head>
<body>
    <p>今月の勤務時間:${shukeiKinmuJikanHH}時間${shukeiKinmuJikanMM}分</p>
    <p>今月の残業時間:${shukeiZangyoJikanHH}時間${shukeiZangyoJikanMM}分</p>
    <form action="kintais" method="POST">
        <div>
            勤務日:<input name="kinmubi" type="date">
            出勤時刻:<input name="shukkin_jikoku" type="time">
            退勤時刻:<input name="taikin_jikoku" type="time">
        </div>
        <div>
            <input type="submit" value="勤怠入力">
        </div>
    </form>
</body>
</html>