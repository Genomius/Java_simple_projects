<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<spring:url value="/static/css/style.css" var="styleCss" />
<spring:url value="/static/bootstrap/3.3.7/css/bootstrap.min.css" var="bootstrapCss" />

<html>
    <head>
        <title>${title}</title>
        <link href="${styleCss}" rel="stylesheet"/>
        <link href="${bootstrapCss}" rel="stylesheet"/>
    </head>
    <body>
        <div class="container">
            <header>

            </header>