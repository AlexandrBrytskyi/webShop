<%@include file="include.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>BOX SHOP a E-commerce online Shopping Category Flat Bootstarp responsive Website Template| Single ::
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
    <link rel="stylesheet" href="${baseURL}assets/css/etalage.css">
    <script src="${baseURL}assets/js/jquery.etalage.min.js"></script>
    <script>
        jQuery(document).ready(function ($) {

            $('#etalage').etalage({
                thumb_image_width: 300,
                thumb_image_height: 400,
                source_image_width: 800,
                source_image_height: 1000,
                show_hint: true,
                click_callback: function (image_anchor, instance_id) {
                    alert('Callback example:\nYou clicked on an image with the anchor: "' + image_anchor + '"\n(in Etalage instance: "' + instance_id + '")');
                }
            });

        });
    </script>
    <link rel="stylesheet" href="${baseURL}assets/css/flexslider.css" type="text/css" media="screen"/>
    <script type="text/javascript">
        $(window).load(function () {
            $("#flexiselDemo").flexisel({
                visibleItems: 4,
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
<div class="header">
    <div class="top-header">

        <!--show div wrap only to users, not to admin-->
        <div class="wrap">

            <c:if test="${not isAdmin}">

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
                <a href="index.html"><img src="${baseURL}assets/images/logo.jpg" class="img-responsive" alt=""/></a>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>

<div class="wrap">
    <div class="navigation-strip">
        <h4>Most Popular<i class="arrow"></i></h4>
        <div class="top-menu">
            <!-- start header menu -->
            <ul class="megamenu skyblue">
                <li><a class="color2" href="${baseURL}shop/itemsMainMenu">Main Page</a></li>

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


    <div style="width: 80%; margin: auto">

        <table class="table table-hover">
            <thead>
            <tr>
                <th>Purchase #</th>
                <th>Cateogry</th>
                <th>Amount</th>
                <th>Item price</th>
                <th>Common price</th>
                <th colspan="2">Customer Contacts</th>
                <th>Item state</th>
                <!--if admin-->
                <c:if test="${isAdmin}">
                    <th colspan="2">Action</th>
                </c:if>

            </tr>
            </thead>
            <tbody>

            <!--here must be all basketItems-->
            <c:forEach items="${purchases}" var="purch">
                <%--by default admin will see last 20 purchases--%>
                <tr>
                    <th scope="row">${purch.id}</th>
                    <td>
                        <a href="${baseURL}shop/item/?itemId=${purch.itemBought.id}">${purch.itemBought.categoriya.name}</a>
                    </td>
                    <td>${purch.itemsAmount}</td>
                    <td>${purch.itemBought.price}</td>
                    <td>${purch.itemBought.price * purch.itemsAmount}</td>
                    <td>${purch.customerBought.phone}</td>
                    <td>${purch.customerBought.name}</td>
                    <td>${purch.state}</td>
                    <!--if admin and need cancel-->
                    <c:if test="${isAdmin}">
                        <c:if test="${purch.state=='CREATED'}">
                            <td>
                                <form action="${baseURL}shop/admin/purchase/cancel/${purch.id}" method="post">
                                    <div class="input-group">
                                        <div class="btn_form">
                                            <input type="submit" class="btn btn-default navbar-btn" value="Cancel"
                                                   title=""
                                                   style="height: 100%; margin: auto"/>
                                        </div>
                                    </div>
                                </form>
                            </td>
                            <td>
                                <form action="${baseURL}shop/admin/purchase/submit/${purch.id}" method="post">

                                    <div class="input-group">
                                        <div class="btn_form">
                                            <input type="submit" class="btn btn-default navbar-btn" value="Submit"
                                                   title=""
                                                   style="height: 100%; margin: auto"/>
                                        </div>
                                    </div>
                                </form>
                            </td>
                        </c:if>
                    </c:if>
                </tr>
            </c:forEach>

            </tbody>


        </table>


    </div>

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