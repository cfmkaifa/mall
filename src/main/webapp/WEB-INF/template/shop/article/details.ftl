<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link href="${base}/resources/common/css/bootstrap.css" rel="stylesheet">
    <link href="${base}/resources/common/css/base.css" rel="stylesheet">
    <link href="${base}/resources/shop/css/base.css" rel="stylesheet">
    <link href="${base}/resources/common/css/iconfont.css" rel="stylesheet">
    <link href="${base}/resources/common/css/demo.css" rel="stylesheet">
    <link href="${base}/resources/shop/css/articledetails.css" rel="stylesheet">
    <!--[if lt IE 9]>
		<script src="${base}/resources/common/js/html5shiv.js"></script>
		<script src="${base}/resources/common/js/respond.js"></script>
        <![endif]-->
    <script src="${base}/resources/common/js/jquery.js"></script>
    <script src="${base}/resources/common/js/bootstrap.js?version=0.1"></script>
    <script src="${base}/resources/common/js/underscore.js"></script>
    <script src="${base}/resources/common/js/base.js?version=0.1"></script>
    <script src="${base}/resources/shop/js/base.js"></script>
    <title>${message("shop.product.logo")}</title>
</head>
<body>
[#include "/shop/include/main_newheader.ftl" /]
<main>
    <div class="detail">
        <div class="details">
            <div class="detailsNav">
                <ul>
                    [#list page.content as temparticle]
                        <li>
                            <div class="detailsTime">
                                <span>${temparticle.createdDate}</span>
                            </div>
                            <div>
                                <p><a href="${base}${temparticle.path}">${temparticle.title}</a></a></p>
                            </div>
                        </li>
                    [/#list]
                </ul>
            </div>
            <div class="detailsContent">
                <div class="content_title">
                    <h2 label="label">${article.title}</h2>
                    <span class="content_time">
					        <p>${message("shop.article.author")}${article.author}</p>
					        <p>${article.createdDate}</p>
					    </span>
                    <div class="content_img">
                        [#noautoesc]
                            ${article.getPageContent(pageNumber)}
                        [/#noautoesc]
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
[#include "/shop/include/main_footer.ftl" /]
</body>
</html>
