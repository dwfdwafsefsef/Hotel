<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${vo.boardTitle}</title>
<!-- jQuery 링크 추가 -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<style type="text/css">
.reply_item {
  margin-bottom: 10px;
}
</style>
</head>
<body>
	<a href="/hotel"><img
		src="<spring:url value="/resources/hotelImg/main.png"/>"></a>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-6 offset-md-3">
				<h3>제목: ${vo.boardTitle}</h3>
				<p>작성자: ${vo.memberId}</p>
				<fmt:formatDate value="${vo.boardDateCreated}"
					pattern="yyyy-MM-dd HH:mm:ss" var="boardDateCreated" />
				<p>작성일: ${boardDateCreated}</p>
				<div class="form-group">
					<textarea class="form-control" readonly>${vo.boardContent}</textarea>
				</div>
				<a href="list?page=${page}" class="btn btn-primary">글 목록</a>
				<c:if test="${sessionScope.memberId == vo.memberId }">
					<a href="update?boardId=${vo.boardId}&page=${page}"
						class="btn btn-secondary">글 수정</a>
				</c:if>
				<form action="delete" method="POST" class="d-inline">
					<input type="hidden" name="boardId" value="${vo.boardId}">
					<c:if test="${sessionScope.memberId == vo.memberId }">
						<button class="btn btn-danger">글 삭제</button>
					</c:if>
				</form>
			</div>
		</div>
	</div>

	<input type="hidden" id="boardId" value="${vo.boardId }">

	<c:if test="${not empty sessionScope.memberId }">
		<div class="container">
			<div class="row">
				<div class="col-md-6 offset-md-3 mt-4">
					<div class="input-group mb-3">
						<input type="text" id="memberId" value="${sessionScope.memberId}"
							class="form-control" readonly> <input type="text"
							id="replyContent" class="form-control">
						<div class="input-group-append">
							<button id="btn_add" class="btn btn-primary">작성</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:if>

	<c:if test="${empty sessionScope.memberId}">
		<div class="container">
			<div class="row">
				<div class="col-md-6 offset-md-3 mt-4">
					<div class="alert alert-warning" role="alert">
						댓글은 로그인이 필요한 기능입니다. <a href="../login" class="alert-link">로그인하기</a>
					</div>
				</div>
			</div>
		</div>
	</c:if>

	<hr>
	<div class="row">
		<div class="col-md-6 offset-md-3">
			<div class="text-center">
				<div id="replies">
					<!-- 내용 -->
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

	<script type="text/javascript">
		$(document).ready(function(){
			getAllReplies();
			
			$('#btn_add').click(function(){
				var boardId = $('#boardId').val(); // 게시판 번호 데이터
				var memberId = $('#memberId').val(); // 작성자 데이터
				var replyContent = $('#replyContent').val(); // 댓글 내용
				var obj = {
						'boardId' : boardId,
						'memberId' : memberId,
						'replyContent' : replyContent
				}
				console.log(obj);
				
				// $.ajax로 송수신
				$.ajax({
					type : 'POST', 
					url : 'replies',
					headers : {
						'Content-Type' : 'application/json'
					}, 
					data : JSON.stringify(obj), // JSON으로 변환
					success : function(result) {
						console.log(result);
						if(result == 1) {
							alert('댓글 입력 성공');
							getAllReplies();
							$('#replyContent').val(''); // 입력 후 input 비우기
						}
					}
				});
			}); // end btn_add.click()
			
			// 게시판 댓글 전체 가져오기
			function getAllReplies() {
				var boardId = $('#boardId').val();
				
				var url = 'replies/all/' + boardId;
				$.getJSON(
					url,
					function(data) {
						// data : 서버에서 전송받은 list 데이터가 저장되어 있음.
						// getJSON()에서 json 데이터는 
						// javascript object로 자동 parsing됨.
						console.log(data);
						var memberId = $('#memberId').val();
						var list = ''; // 댓글 데이터를 HTML에 표현할 문자열 변수
						
						// $(컬렉션).each() : 컬렉션 데이터를 반복문으로 꺼내는 함수
						$(data).each(function(){
							// this : 컬렉션의 각 인덱스 데이터를 의미
							console.log(this);
							var replyDate = moment(this.replyDateCreated).format('YYYY-MM-DD HH:mm:ss'); 
			                // moment = 시간 날짜를 파싱 해주는 객체 (라이브러리 추가)
							var hidden = 'hidden';
							var readonly = 'readonly';
							
							if(memberId == this.memberId) { 
								hidden = '';
								readonly = '';
							}
							
							list += '<div class="reply_item">'
						        + '<pre>'
						        + '<input type="hidden" id="replyId" value="' + this.replyId + '">'
						        + this.memberId + '님의 댓글 :'
						        + '&nbsp;&nbsp;'
						        + '<input type="text" ' + readonly + ' id="replyContent" value="' + this.replyContent + '">'
						        + '&nbsp;&nbsp;'
						        + replyDate
						        + '&nbsp;&nbsp;'
						        + '<button class="btn btn-primary btn_update" ' + hidden + '>수정</button>'
						        + '<button class="btn btn-danger btn_delete" ' + hidden + '>삭제</button>'
						        + '</pre>'
						        + '</div>';
						}); // end each()
							
						$('#replies').html(list);
					} // end function()
				); // end getJSON()
			} // end getAllReplies()
			
			// 수정 버튼을 클릭하면 선택된 댓글 수정
			$('#replies').on('click', '.reply_item .btn_update', function(){
				console.log(this);
				
				// 선택된 댓글의 replyId, replyContent 값을 저장
				// prevAll() : 선택된 노드 이전에 있는 모든 형제 노드를 접근
				var replyId = $(this).prevAll('#replyId').val();
				var replyContent = $(this).prevAll('#replyContent').val();
				console.log("선택된 댓글 번호 : " + replyId + ", 댓글 내용 : " + replyContent);
				
				// ajax 요청
				$.ajax({
					type : 'PUT', 
					url : 'replies/' + replyId,
					headers : {
						'Content-Type' : 'application/json'
					},
					data : JSON.stringify({ 
						replyId: replyId, 
						replyContent: replyContent 
						}),
					success : function(result) {
						console.log(result);
						if(result == 1) {
							alert('댓글 수정 성공!');
							getAllReplies();
						}
					}
				});
				
			}); // end replies.on()
			
			// 삭제 버튼을 클릭하면 선택된 댓글 삭제
			$('#replies').on('click', '.reply_item .btn_delete', function(){
				console.log(this);
				var boardId = $('#boardId').val();
				var replyId = $(this).prevAll('#replyId').val();
				
				// ajax 요청
				$.ajax({
					type : 'DELETE', 
					url : 'replies/' + replyId,
					headers : {
						'Content-Type' : 'application/json'
					},
					data : boardId,
					success : function(result) {
						console.log(result);
						if(result == 1) {
							alert('댓글 삭제 성공!');
							getAllReplies();
						}
					}
				});
			});
			
		}); // end document()
	</script>

</body>
</html>