<%@include file="include.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>BOX SHOP an E-Commerce online Shopping Category Flat Bootstarp responsive Website Template| Home ::
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
                autoPlay: false,
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

                <div class="header-right">
                    <ul>
                        <li>
                            <a href="${baseURL}admin">I am admin</a>
                        </li>
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
                <a href="index.html"><img src="${baseURL}assets/images/logo.jpg" class="img-responsive" alt=""/></a>
            </div>
            <%--<div class="search">--%>
            <%--<div class="search2">--%>
            <%--<form>--%>
            <%--<input type="submit" value="">--%>
            <%--<input type="text" value="Search for a product, category or brand" onfocus="this.value = '';"--%>
            <%--onblur="if (this.value == '') {this.value = 'Search for a product, category or brand';}"/>--%>
            <%--</form>--%>
            <%--</div>--%>
            <%--</div>--%>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
<!-- header-section-ends -->
<div class="wrap">
    <div class="navigation-strip">
        <h4>Menu<i class="arrow"></i></h4>
        <div class="top-menu">
            <!-- start header menu -->
            <ul class="megamenu skyblue">

                <c:forEach items="${categories}" var="cate">
                    <li><a class="color6" href="#">${cate.name}</a>
                        <div class="megapanel">
                            <div class="row">

                                <div class="col1">
                                    <div class="h_nav">
                                        <h4>${cate.name}</h4>
                                        <ul>
                                            <c:forEach items="${cate.subcategories}" var="subca">
                                                <li>
                                                    <a href="${baseURL}shop/item/filtered?categoryId=${subca.id}&priceAsc=true&page=0">${subca.name}</a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col2"></div>
                                <div class="col1"></div>
                                <div class="col1"></div>
                                <div class="col1"></div>
                                <div class="col1"></div>
                            </div>
                        </div>
                    </li>
                </c:forEach>
                <c:if test="${isAdmin}">
                    <li><a class="color2" href="${baseURL}shop/admin/categories/all">Categories</a></li>
                    <li><a class="color2" href="${baseURL}shop/admin/subcategories/all">Subcategories</a></li>
                    <li><a class="color2" href="${baseURL}shop/purchase">Purchases</a></li>
                </c:if>
                <li><a class="color2" href="#">HELP</a></li>
            </ul>
        </div>
        <div class="clearfix"></div>
    </div>
    <div class="main-top">

    </div>


    <link rel="stylesheet" href="css/flexslider.css" type="text/css" media="screen"/>
    <script type="text/javascript">
        $(window).load(function () {
            $("#flexiselDemo1").flexisel({
                visibleItems: 7,
                animationSpeed: 1000,
                autoPlay: false,
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
    <script type="text/javascript" src="js/jquery.flexisel.js"></script>

    <c:if test="${not noItems}">

        <h3>Most popular items</h3>
        <div class="main">
            <div class="content">
                    <%--<div class="content_top">--%>
                    <%--<div class="heading">--%>
                    <%--<h3>Select what you need</h3>--%>
                    <%--</div>--%>
                    <%--<form id="filters" action="${baseURL}shop/item/filtered">--%>
                    <%--<input type="text" hidden="hidden" name="categoryId" value="${category.id}">--%>
                    <%--<div class="sort">--%>
                    <%--<p>Price sort:--%>
                    <%--<select form="filters" name="priceAsc"--%>
                    <%--onchange="this.form.submit()">--%>
                    <%--<option value="true">Lowest Price</option>--%>
                    <%--<option--%>
                    <%--<c:if test="${not priceAsc}">selected="selected"</c:if> value="false">Highest--%>
                    <%--Price--%>
                    <%--</option>--%>
                    <%--</select>--%>
                    <%--</p>--%>
                    <%--</div>--%>

                    <%--<div class="page-no">--%>
                    <%--<p>Result Pages:--%>
                    <%--<ul>--%>
                    <%--<select form="filters" name="page" onchange="this.form.submit()">--%>
                    <%--<li>--%>
                    <%--<option value="${page-1}">${page-1}</option>--%>
                    <%--</li>--%>
                    <%--<li class="active">--%>
                    <%--<option selected="selected" value="${page}">${page}</option>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                    <%--<option value="--%>
                    <%--${page+1}">${page+1}</option>--%>
                    <%--</li>--%>
                    <%--</select>--%>
                    <%--</ul>--%>
                    <%--</p>--%>
                    <%--</div>--%>
                    <%--</form>--%>

                    <%--<div class="clearfix"></div>--%>
                    <%--</div>--%>
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
    </c:if>

</div>

<c:if test="${noItems}">
    <div class="wrap">
        <h2>No available items yet</h2>
    </div>
</c:if>

</div>

</body>
</html>