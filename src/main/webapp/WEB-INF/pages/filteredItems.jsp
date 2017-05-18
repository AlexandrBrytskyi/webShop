<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
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
<!-- header-section-starts -->
<div class="header">
    <div class="top-header">
        <div class="wrap">
            <c:if test="${not isAdmin}">
                <!--show div wrap only to users, not to admin-->
                <div class="header-right">
                    <ul>
                        <li>
                            <i class="user"></i>
                            <c:if test="${showPurch}"><a href="${baseURL}shop/purchase">My Purchases</a></c:if>
                        </li>
                        <li>
                            <i class="cart"></i>
                            <a href="${baseURL}shop/basket">Shopping Cart</a>
                        </li>
                        <li class="last">${fn:length(basket.itemAmountEntries)}</li>
                    </ul>
                </div>
                <div class="clearfix"></div>
            </c:if>
            <div class="clearfix"></div>
        </div>
    </div>
    <div class="wrap">
        <div class="header-bottom">
            <div class="logo">
                <a href="index.html"><img src="../../assets/images/logo.jpg" class="img-responsive" alt=""/></a>
            </div>
            <div class="search">
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
<!-- header-section-ends -->
<div class="wrap">
    <div class="navigation-strip">
        <h4>${category.name}->${category.parent.name}</><i class="arrow"></i></h4>
        <div class="top-menu">


            <c:if test="${not isAdmin}">
                <ul class="megamenu skyblue">
                    <li><a class="color2" href="${baseURL}shop/itemsMainMenu">Main Page</a></li>
                    <li><a class="color2" href="#">HELP</a></li>
                </ul>
            </c:if>


            <c:if test="${isAdmin}">
                <ul class="megamenu skyblue">
                    <li><a class="color2" href="${baseURL}shop/itemsMainMenu">Main Page</a></li>
                    <li><a class="color2" href="${baseURL}shop/admin/categories/all">Categories</a></li>
                    <li><a class="color2" href="${baseURL}shop/admin/subcategories/all">Subcategories</a></li>
                    <li><a class="color2" href="${baseURL}shop/purchase">Purchases</a></li>

                </ul>
            </c:if>
            </ul>

        </div>
        <div class="clearfix"></div>
    </div>

    <div class="main">
        <div class="content">
            <div class="content_top">
                <div class="heading">
                    <h3>Select what you need</h3>
                    <%--</div>--%>
                    <form id="filters" action="${baseURL}shop/item/filtered">
                        <input type="text" hidden="hidden" name="categoryId" value="${category.id}">
                        <%--<div class="sort">--%>
                        <p>Price sort:
                            <select form="filters" name="priceAsc"
                                    onchange="this.form.submit()">
                                <option value="true">Lowest Price</option>
                                <option
                                        <c:if test="${not priceAsc}">selected="selected"</c:if> value="false">Highest
                                    Price
                                </option>
                            </select> |
                            <c:forEach items="${category.charecteristics}" var="charact">
                                <c:if test="${charact.showInFilter}">
                                    ${charact.name}:
                                    <select name="${charact.id}__select" form="filters" onchange="this.form.submit()">
                                        <option value="-1" selected>Disabled</option>
                                        <c:forEach items="${charact.characteristicValues}" var="charactValue">

                                            <c:set var="cvid" value="${charactValue.id}"></c:set>

                                            <% Object cvid = pageContext.getAttribute("cvid");
                                                System.out.println("got cvid " + cvid);
                                                String attrName = cvid + "__charact_val_id";
                                                Object resultAttr = request.getAttribute(attrName);
                                                System.out.println("got from attr from request " + resultAttr);
                                                boolean isSelected = false;
                                                if (resultAttr == null) {
                                                    isSelected = false;
                                                } else isSelected = (boolean) resultAttr;
                                                pageContext.setAttribute("show", isSelected);
                                            %>
                                            <option
                                                    <c:if test="${show}">selected="selected"</c:if>
                                                    value="${charactValue.id}__charact_val_id">${charactValue.value}</option>
                                        </c:forEach>
                                    </select>
                                </c:if>
                            </c:forEach>
                        </p>
                </div>

                <div class="page-no">
                    <p>Result Pages:
                    <ul>
                        <select form="filters" name="page" onchange="this.form.submit()">
                            <c:if test="${not (page == 0)}">
                                <li>
                                    <option value="${page-1}">${page-1}</option>
                                </li>
                            </c:if>
                            <li class="active">
                                <option selected="selected" value="${page}">${page}</option>
                            </li>
                            <c:if test="${fn:length(results) == 20}">
                                <li>
                                    <option value="
                ${page+1}">${page+1}</option>
                                </li>
                            </c:if>
                        </select>
                    </ul>
                    </p>
                </div>
                </form>

                <div class="clearfix"></div>
            </div>
            <div class="section group">
                <c:forEach items="${results}" var="res">
                    <div class="grid_1_of_4 images_1_of_4">

                        <a href="${baseURL}shop/item/?itemId=${res.id}"><img
                                src="${baseURL}shop/item/picture?itemId=${res.id}" alt=""/></a>
                        <h2>${res.categoriya.name}</h2>
                        <p>${res.description}</p>
                        <span class="price">$${res.price}</span></p>
                        <c:if test="${not isAdmin}">
                        <div class="button"><span><img src="${baseURL}assets/images/cart.jpg" alt=""/><a
                                href="${baseURL}shop/basket/addItem?itemId=${res.id}"
                                class="cart-button">Add to Cart</a></span></c:if>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>


<div class="footer">
    <div class="wrap">


        <div class="copyright text-center">
            <p>Copyright &copy; 2015 All rights reserved | Template by <a href="http://w3layouts.com"> W3layouts</a>
            </p>
        </div>

    </div>
</div>
</body>
</html>