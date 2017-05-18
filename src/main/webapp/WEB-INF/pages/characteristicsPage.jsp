<%@ page import="app.model.items.characteristics.CharacteristicType" %>
<%@include file="include.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>BOX SHOP a E-commerce online Shopping Category Flat Bootstarp responsive Website Template| Products ::
        w3layouts</title>
    <link href="${baseURL}assets/css/bootstrap.css" rel='stylesheet' type='text/css'/>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${baseURL}assets/js/jquery.min.js"></script>
    <!-- Custom Theme files -->
    <link href="${baseURL}assets/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <!-- Custom Theme files -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="BOX SHOP Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design"/>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);
    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <!--webfont-->
    <link href='http://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic'
          rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
          rel='stylesheet' type='text/css'>
    <!-- start menu -->
    <link href="${baseURL}assets/css/megamenu.css" rel="stylesheet" type="text/css" media="all"/>
    <script type="text/javascript" src="${baseURL}assets/js/megamenu.js"></script>
    <script>$(document).ready(function () {
        $(".megamenu").megamenu();
    });</script>
    <!-- start slider -->
    <script src="${baseURL}assets/js/responsiveslides.min.js"></script>
    <script>
        $(function () {
            $("#slider").responsiveSlides({
                auto: true,
                speed: 500,
                namespace: "callbacks",
                pager: true,
            });
        });
    </script>
    <!--end slider -->
    <link rel="stylesheet" href="${baseURL}assets/css/flexslider.css" type="text/css" media="screen"/>
    <script type="text/javascript">
        $(window).load(function () {
            $("#flexiselDemo").flexisel({
                visibleItems: 5,
                animationSpeed: 1000,
                autoPlay: true,
                autoPlaySpeed: 3000,
                pauseOnHover: true,
                enableResponsiveBreakpoints: true,
                responsiveBreakpoints: {
                    portrait: {
                        changePoint: 480,
                        visibleItems: 1
                    },
                    landscape: {
                        changePoint: 640,
                        visibleItems: 2
                    },
                    tablet: {
                        changePoint: 768,
                        visibleItems: 3
                    }
                }
            });

        });
    </script>
    <script type="text/javascript" src="${baseURL}assets/js/jquery.flexisel.js"></script>
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


<!-- header-section-starts -->
<div class="header">

</div>
<!-- header-section-ends -->
<div class="wrap">
    <div class="navigation-strip">
        <h4>${categoryName}<i class="arrow"></i></h4>
        <div class="top-menu">
            <!-- start header menu -->
            <ul class="megamenu skyblue">


                <li><a class="color2" href="${baseURL}shop/admin/categories/all">Categories</a></li>
                <li><a class="color2" href="${baseURL}shop/admin/subcategories/all">Subcategories</a></li>
                <li><a class="color2" href="${baseURL}shop/itemsMainMenu">Items</a></li>
                <li><a class="color2" href="${baseURL}shop/purchase">Purchases</a></li>

            </ul>
        </div>
        <div class="clearfix"></div>
    </div>


    <div style="width: 80%; margin: auto">

        <table class="table table-hover">
            <thead>
            <tr>
                <th>id</th>
                <th>Name</th>
                <th>Type</th>
                <th>Show in filter</th>
                <th colspan="2">Actions</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${characteristics}" var="characteristic">
                <%--<c:out value="${characteristic}"/>--%>
                <tr>
                    <th scope="row">${characteristic.id}</th>
                    <td>${characteristic.name}</td>
                    <td>${characteristic.type}</td>
                    <td>${characteristic.showInFilter}</td>
                    <td>
                        <div class="btn_form">
                            <form style="width: 100%; margin: auto"
                                  action="${baseURL}shop/admin/characteristics/delete/${subcategoryId}"
                                  method="post">
                                <input type="text" value="${characteristic.id}" hidden="hidden"
                                       name="characteristicId"/>
                                <input type="submit" value="REMOVE" title="" style="width: 100%; margin: auto"/>
                            </form>
                        </div>
                    </td>
                    <td>
                        <div class="btn_form">
                            <form style="width: 100%; margin: auto"
                                  action="${baseURL}shop/admin/characteristics/values/${subcategoryId}/${characteristic.id}"
                                  method="get">
                                <input type="text" value="${characteristic.id}" hidden="hidden"
                                       name="characteristicId"/>
                                <input type="submit" value="Manage values" title="" style="width: 100%; margin: auto"/>
                            </form>
                        </div>
                    </td>
                </tr>

            </c:forEach>

            </tbody>
        </table>


        <form id="crete_characteristic_form" action="${baseURL}shop/admin/characteristics/create/${subcategoryId}"
              method="post">

            <div class="input-group">
                <input type="text" class="form-control" placeholder="Characteristic name" name="characteristicName"
                       aria-describedby="sizing-addon2"
                       style="height: 100%">
                <span class="input-group-addon" id="sizing-addon2" style="height: 100%">Type
                    <select name="characteristicType" form="crete_characteristic_form">
                        <c:set var="types" value="<%=CharacteristicType.values()%>"/>
                        <c:forEach items="${types}" var="typ">
                            <option value="${typ}">${typ}</option>
                        </c:forEach>
                            </select>
                    </span>
                <span class="input-group-addon" id="sizing-addon3" style="height: 100%">Show in filter
                    <select name="showInFilterCharacteristic" form="crete_characteristic_form">
                    <option value="true">true</option>
                    <option value="false">false</option>
                </select>
                    </span>
                <div class="btn_form">
                    <input type="submit" class="btn btn-default navbar-btn" value="CREATE" title=""
                           style="height: 100%"/>
                </div>
            </div>
        </form>

    </div>


</div>


<div class="footer">
    <div class="wrap">


        <div class="copyright text-center">
            <p>Copyright &copy; 2015 All rights reserved | Template by <a href="http://w3layouts.com"> W3layouts</a></p>
        </div>

    </div>
</div>
</body>
</html>

