<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>edit</title>
    <%@ include file="../itemfile.jspf" %>
    <!-- 서머노트를 위해 추가해야할 부분 -->
    <script src="${pageContext.request.contextPath}/resources/summernote/summernote-lite.js"></script>
    <script src="${pageContext.request.contextPath}/resources/summernote/lang/summernote-ko-KR.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/summernote/summernote-lite.css">
    <script src="https://cdn.jsdelivr.net/npm/summernote-sticky-toolbar@1.0.0/summernote-sticky-toolbar.min.js"></script>
</head>
    <style>
        textarea {
            display: flex;
            width: 100%;
            border: none;
            resize: none;
        }

        .form-control, input {
            border: none;
        }

        .btn-group {
            position: fixed;
            bottom: 0;
        }
    </style>
    <script>
        $(function() {
            $("#Update").click(function(){
                document.form1.submit();
            });
        });
    </script>
<body>
<form name="form1" method="post" action="${path}/review/update">
<div class="container"><br>
        <input type="hidden" name="review_num" value="${review.review_num}" />
        <select class="form-select w-25" aria-label="Default select example" name="l_num">
        <option selected value="${review.l_num}">${review.local.k_name}</option>
        <option value="1">서울</option>
        <option value="2">부산</option>
        <option value="3">대구</option>
        <option value="4">인천</option>
        <option value="5">광주</option>
        <option value="6">대전</option>
        <option value="7">울산</option>
        <option value="8">경기</option>
        <option value="9">강원</option>
        <option value="10">충북</option>
        <option value="11">충남</option>
        <option value="12">전북</option>
        <option value="13">전남</option>
        <option value="14">경북</option>
        <option value="15">경남</option>
        <option value="16">제주</option>
    </select>
    <hr>
    <input class="form-control" type="text" id="title" name="title" value="${review.title}" aria-label="default input example">
    <hr>
    <textarea class="summernote" id="content" name="content">${review.content}</textarea>
    <script>
        $('.summernote').summernote({
            height: 750,
            lang: "ko-KR",
            minHeight: null,
            maxHeight: null,
            focus: true,
            toolbar: [
                ['fontname', ['fontname']],
                ['fontsize', ['fontsize']],
                ['style', ['bold', 'italic', 'underline', 'strikethrough', 'clear']],
                ['color', ['forecolor', 'color']],
                ['table', ['table']],
                ['para', ['ul', 'ol', 'paragraph']],
                ['height', ['height']],
                ['insert', ['picture', 'link', 'video']],
                ['view', ['fullscreen', 'help']]

            ],
            fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New', '맑은 고딕', '궁서', '굴림체', '굴림', '돋음체', '바탕체'],
            fontSizes: ['8', '9', '10', '11', '12', '14', '16', '18', '20', '22', '24', '28', '30', '36', '50', '72'],
            stickyToolbar: {
                enabled: true,
                offset: 0,
                zIndex: 9999
            }
        });
    </script>
    <hr>
    <div name="filename" value="이미지 이름">asdasd.jpg</div><br><br>
    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
        <div class="btn-group m-3" role="group" aria-label="First group">
            <button type="button" id="Update" class="btn btn-success btn-lg">수정</button>
        </div>
    </div>
        <script>
            console.log(${review.l_num})
        </script>
    <br>
    <%@ include file="../footer.jspf" %>
</div>
</form>
</body>
</html>
