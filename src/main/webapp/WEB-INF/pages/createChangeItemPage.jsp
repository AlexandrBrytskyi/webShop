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
        <h4>${category.name} -> ${subcategory.name} -> new Item creation<i class="arrow"></i></h4>
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

        <c:if test="${not isChanging}"><c:set var="url" value="${baseURL}shop/item/create"></c:set></c:if>
        <c:if test="${isChanging}"><c:set var="url" value="${baseURL}shop/item/update/${item.id}"></c:set></c:if>

        <form id="create_item_form" action="${url}" method="post" enctype="multipart/form-data">
            <table class="table table-hover">

                <tbody>


                <tr>
                    <th scope="row">Category</th>
                    <td colspan="2">${category.name}</td>
                </tr>

                <tr>
                    <th scope="row">Subcategory</th>
                    <td colspan="2">${subcategory.name}</td>
                </tr>
                <input type="text" value="${subcategory.id}" hidden="hidden" name="subCategoryId"/>

                <tr>
                    <th scope="row">Price</th>
                    <td colspan="2">
                        <input type="text" name="price"
                               class="form-control" placeholder="Price"
                               aria-describedby="sizing-addon2"
                               name="price" value="${item.price}" style="height: 100%"></td>
                </tr>

                <tr>
                    <th scope="row">Description</th>
                    <td colspan="2">
                        <textarea name="description" form="create_item_form"
                                  placeholder="Description" title=""
                                  style="width: 100%; margin: auto">${item.description}</textarea></td>
                </tr>

                <tr>
                    <th scope="row">Amount</th>
                    <td colspan="2">
                        <textarea name="amountAvailable" form="create_item_form"
                                  placeholder="Amount" title=""
                                  style="width: 100%; margin: auto">${item.description}</textarea></td>
                </tr>

                <tr>
                    <th scope="row">Picture</th>
                    <td colspan="2">
                        <input type="file" class="form-control" placeholder="Picture"
                               name="picture"
                               aria-describedby="sizing-addon2"
                               style="height: 100%">
                    </td>
                </tr>


                <c:forEach items="${characterVsValues}" var="charVSValue">
                    <tr>
                        <th scope="row">${charVSValue.charecteristic.name}</th>

                        <td colspan="2">
                            <select name="${charVSValue.charecteristic.id}__select" form="create_item_form"/>

                            <c:forEach items="${charVSValue.values}" var="charactValue">
                                <option value="${charactValue.id}__charact_val_id">${charactValue.value}</option>
                            </c:forEach>

                            </select>
                        </td>
                    </tr>
                </c:forEach>


                </tbody>
            </table>

            <div class="input-group">

                <c:if test="${not isChanging}">
                    <div class="btn_form">
                        <input type="submit" class="btn btn-default navbar-btn" value="CREATE" title=""
                               style="height: 100%"/>
                    </div>
                </c:if>

                <c:if test="${isChanging}">
                    <div class="btn_form">
                        <input type="submit" class="btn btn-default navbar-btn" value="UPDATE" title=""
                               style="height: 100%"/>
                    </div>
                </c:if>

            </div>

        </form>

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

