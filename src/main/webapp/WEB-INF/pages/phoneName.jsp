<%@include file="include.jsp" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- CSS -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" href="${baseURL}assets/login/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${baseURL}assets/login/assets/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${baseURL}assets/login/assets/css/form-elements.css">
    <link rel="stylesheet" href="${baseURL}assets/login/assets/css/style.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Favicon and touch icons -->
    <link rel="shortcut icon" href="assets/ico/favicon.png">
    <link rel="apple-touch-icon-precomposed" sizes="144x144"
          href="${baseURL}assets/login/assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114"
          href="${baseURL}assets/login/assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72"
          href="${baseURL}assets/login/assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="${baseURL}assets/login/assets/ico/apple-touch-icon-57-precomposed.png">


    <%--scripts--%>
    <script src="${baseURL}assets/login/assets/js/jquery-1.11.1.min.js"></script>
    <script src="${baseURL}assets/login/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="${baseURL}assets/login/assets/js/scripts.js"></script>


    <%--<script>--%>
    <%--<%@ include file="${baseURL}assets/js/signin.js"%>--%>
    <%--</script>--%>

    <title>Login</title>


</head>

<body>

<c:set var="message" value="${message}"/>
<c:if test="${not empty message or param.containsKey('message')}">
    <div class="alert alert-danger alert-dismissable fade in" id="error_alert">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        <strong>${message}!</strong>
    </div>
    <script>
        setTimeout(function () {
            $(".alert").alert('close');
        }, 2000);
    </script>
</c:if>

<!-- Top content -->
<div class="top-content">

    <div class="inner-bg">
        <div class="container">

            <div class="row">
                <div class="col-sm-8">

                    <div class="form-box">
                        <div class="form-top">
                            <div class="form-top-left">
                                <h3>Introduce yourself, please</h3>
                                <p>Enter your name and phone:</p>
                            </div>
                            <div class="form-top-right">
                                <i class="fa fa-key"></i>
                            </div>
                        </div>
                        <div class="form-bottom">
                            <form role="form" action="${baseURL}shop/basket/login" method="post" class="login-form">
                                <div class="form-group">
                                    <label class="sr-only" for="form-username">Name</label>
                                    <input type="text" name="name" placeholder="Name..."
                                           class="form-username form-control" id="form-username">
                                </div>
                                <div class="form-group">
                                    <label class="sr-only" for="form-password">Phone</label>
                                    <input type="tel" name="phone" placeholder="Phone..."
                                           class="form-password form-control" id="form-password">
                                </div>

                                <input type="hidden" name="${_csrf.parameterName}"
                                       value="${_csrf.token}"/>

                                <button type="submit" class="btn">Ok!</button>
                            </form>
                        </div>
                    </div>

                </div>

                <%--<div class="col-sm-1 middle-border"></div>--%>
                <%--<div class="col-sm-1"></div>--%>

                <%--<div class="col-sm-5">--%>

                    <%--<div class="form-box">--%>
                        <%--<div class="form-top">--%>
                            <%--<div class="form-top-left">--%>
                                <%--<h3>Sign up now</h3>--%>
                                <%--<p>Fill in the form below to get instant access:</p>--%>
                            <%--</div>--%>
                            <%--<div class="form-top-right">--%>
                                <%--<i class="fa fa-pencil"></i>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="form-bottom">--%>
                            <%--<form role="form" action="/signup" method="post" class="registration-form">--%>
                                <%--<div class="form-group">--%>
                                    <%--<label class="sr-only" for="form-first-name">Login</label>--%>
                                    <%--<input type="text" name="login" placeholder="Login..."--%>
                                           <%--class="form-first-name form-control" id="form-first-name">--%>
                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                    <%--<label class="sr-only" for="form-last-name">Password</label>--%>
                                    <%--<input type="password" name="password" placeholder="password"--%>
                                           <%--class="form-last-name form-control" id="form-last-name">--%>
                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                    <%--<label class="sr-only" for="form-last-name">Phone</label>--%>
                                    <%--<input type="tel" name="phone" placeholder="phone"--%>
                                           <%--class="form-last-name form-control" id="form-phone">--%>
                                <%--</div>--%>
                                <%--<button type="submit" class="btn">Sign me up!</button>--%>
                            <%--</form>--%>
                        <%--</div>--%>
                    <%--</div>--%>

                <%--</div>--%>
            </div>

        </div>
    </div>

</div>


</body>
</html>
