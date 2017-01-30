<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<spring:url value="/static/jquery/3.1.1/jquery.js" var="jqueryJs" />
<spring:url value="/static/bootstrap/3.3.7/js/bootstrap.min.js" var="bootstrapJs" />
<spring:url value="/static/js/script.js" var="scriptJs" />

            <footer>
                <script src="${jqueryJs}"></script>
                <script src="${bootstrapJs}"></script>
                <script src="${scriptJs}"></script>
            </footer>
        </div>
    </body>
</html>
