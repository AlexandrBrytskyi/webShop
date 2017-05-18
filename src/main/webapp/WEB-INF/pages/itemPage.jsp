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
        <c:if test="${not isAdmin}">
            <!--show div wrap only to users, not to admin-->
            <div class="wrap">

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
            </div>

        </c:if>
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

<div class="wrap">
    <div class="navigation-strip">
        <h4>Most Popular<i class="arrow"></i></h4>
        <div class="top-menu">
            <!-- start header menu -->

            <c:if test="${not isAdmin}">
                <ul class="megamenu skyblue">
                    <li><a class="color2" href="${baseURL}shop/itemsMainMenu">Main Page</a></li>
                    <li><a class="color2" href="#">HELP</a></li>
                </ul>
            </c:if>


            <c:if test="${isAdmin}">
                <c:out value="${isAdmin}"></c:out>
                <ul class="megamenu skyblue">

                    <li><a class="color2" href="${baseURL}shop/itemsMainMenu">Main Page</a></li>
                    <li><a class="color2" href="${baseURL}shop/admin/categories/all">Categories</a></li>
                    <li><a class="color2" href="${baseURL}shop/admin/subcategories/all">Subcategories</a></li>
                    <li><a class="color2" href="${baseURL}shop/purchase">Purchases</a></li>

                </ul>
            </c:if>

        </div>
        <div class="clearfix"></div>
    </div>
    <!-- start main -->
    <div class="main_bg">
        <div class="main">
            <!-- start span1_of_1 -->
            <div class="left_content" style="width: 100%;">

                <div class="grid images_3_of_2">
                    <ul id="etalage">
                        <li>
                            <a href="optionallink.html">
                                <img class="etalage_thumb_image" src="${baseURL}shop/item/picture?itemId=${item.id}"
                                     class="img-responsive"/>
                                <img class="etalage_source_image" src="${baseURL}shop/item/picture?itemId=${item.id}"
                                     title=""/>
                            </a>
                        </li>
                    </ul>
                    <div class="clearfix"></div>
                </div>

                <!-- start span1_of_1 -->
                <div class="span1_of_1_des" style="width: 60%">
                    <div class="desc1" style="width: 100%">
                        <h3>${item.description}</h3>
                        <h5>$ ${item.price} <a href="#"></a></h5>

                        <table class="table table-hover">
                            <tbody>

                            <tr>
                                <th scope="row">Category</th>
                                <td>${item.categoriya.parent.name}</td>
                            </tr>

                            <tr>
                                <th scope="row">Subcategory</th>
                                <td>${item.categoriya.name}</td>
                            </tr>

                            <c:forEach items="${item.characteristicValues}" var="charval">
                                <tr>
                                    <th scope="row">${charval.charecteristic.name}</th>
                                    <td>${charval.value}</td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>

                        <div class="btn_form">
                            <!--if user-->
                            <c:if test="${not isAdmin}">
                                <form action="${baseURL}shop/basket/addItem" method="get">
                                    <input type="text" name="itemId" value="${item.id}" hidden="hidden">
                                    <input type="submit" value="add to cart" title=""/>
                                </form>
                            </c:if>
                            <!--else-->
                            <c:if test="${isAdmin}">
                                <form action="${baseURL}shop/item/update/${item.id}" method="get">
                                    <input type="submit" value="change" title=""/>
                                </form>
                            </c:if>
                        </div>
                        <div class="clearfix"></div>
                    </div>

                </div>


            </div>
            <div class="clearfix"></div>

            <div class="reviews-tabs">
                <!-- Main component for a primary marketing message or call to action -->
                <ul class="nav nav-tabs responsive hidden-xs hidden-sm" id="myTab">
                    <li class="test-class active"><a class="deco-none"
                                                     href="#source">Reviews ${fn:length(item.comments)}</a>
                    </li>
                </ul>

                <div class="tab-content responsive hidden-xs hidden-sm">

                    <div class="tab-pane active" id="source">
                        <div class="response">

                            <div class="clearfix"></div>

                            <!--comment start-->
                            <c:forEach items="${item.comments}" var="comment">
                                <div class="media response-info">
                                    <div class="media-left response-text-left">
                                        <h5><a href="#">${comment.owner.login} said: </a></h5>
                                    </div>
                                    <div class="media-body response-text-right">
                                        <p>${comment.content}</p>
                                        <ul>
                                            <li>${comment.date}</li>
                                            <!--<li><a href="single.html">Reply</a></li>-->
                                        </ul>
                                    </div>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="clearfix"></div>
                                <!--comment end-->
                            </c:forEach>

                        </div>


                    </div>
                </div>

                <c:if test="${canComment}">
                    <div class="btn_form" style="width: 100%; margin: auto">
                        <br>
                        <br>
                        <form id="comment_form" style="width: 100%; margin: auto"
                              action="${baseURL}/shop/item/createComment" method="post">
                            <a href="${baseURL}shop/item/createCommand"></a>>
                            <ul>
                                <li>
                                    <textarea name="comment" form="comment_form"
                                              placeholder="leave your comment, please" title=""
                                              style="width: 100%; margin: auto"></textarea>
                                    <input type="text" value="${item.id}" hidden="hidden" name="itemId"/>

                                </li>
                                <li>
                                    <input type="submit" value="SEND" title="" style="width: 100%; margin: auto"/>
                                </li>
                            </ul>
                        </form>
                    </div>
                </c:if>
            </div>

        </div>


        <div class="clearfix"></div>
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