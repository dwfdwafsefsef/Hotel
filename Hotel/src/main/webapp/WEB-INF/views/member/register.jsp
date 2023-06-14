<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
    }

    .container {
        margin: 0 auto;
        max-width: 500px;
        padding: 20px;
        background-color: #fff;
        border-radius: 5px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
        text-align: center;
    }

    .logo {
        display: block;
        margin: 0 auto 20px;
        max-width: 200px;
    }

    h2 {
        font-size: 24px;
        font-weight: bold;
        margin-bottom: 20px;
        color: #444;
    }

    form {
        text-align: left;
    }

    label {
        display: block;
        font-size: 16px;
        font-weight: bold;
        margin-bottom: 5px;
        color: #555;
    }

    input[type="text"],
    input[type="password"] {
        display: block;
        width: 100%;
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 5px;
        margin-bottom: 20px;
        font-size: 16px;
        color: #555;
        box-sizing: border-box;
    }

    input[type="submit"] {
        background-color: blue;
        border: none;
        color: #fff;
        padding: 10px 20px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s;
    }

    input[type="submit"]:hover {
        background-color: lightblue;
    }

    #useBtn,
    #returnBtn {
        background-color: #ccc;
        border: none;
        color: #fff;
        padding: 10px 20px;
        text-align: center;
        text-decoration: none;
        display: none;
        font-size: 16px;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s;
        margin-bottom: 10px;
    }
    
    #sendEmailBtn {
    	background-color: #ccc;
        border: none;
        color: #fff;
        padding: 10px 20px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s;
        margin-bottom: 10px;
    }

    #useBtn:hover,
    #returnBtn:hover,
    #sendEamilBtn:hover {
        background-color: #555;
    }

    #messageId,
    #messagePw,
    #messagePwCk,
    #messagePhone {
        display: block;
        margin-top: -10px;
        margin-bottom: 10px;
        font-size: 14px;
        color: red;
    }
</style>
</head>
<body>
	 <div class="container">
        <a href="/hotel">
            <img src="<spring:url value="/resources/hotelImg/main.png"/>" class="logo">
        </a>
        <h2>회원가입</h2>
        <form action="register" method="POST">
            <div>
                <label for="memberId">아이디</label>
                <input type="text" name="memberId" id="memberId" placeholder="아이디 입력" autofocus required>
                <span id="messageId"></span><br>
                <button id="useBtn">사용</button>
                <button id="returnBtn">다시 입력</button>
            </div>
            <div>
                <label for="memberPw">비밀번호</label>
                <input type="password" name="memberPw" id="memberPw" placeholder="비밀번호 입력" required>
                <span id="messagePw"></span>
            </div>
            <div>
                <label for="memberPwCk">비밀번호 확인</label>
                <input type="password" name="memberPwCk" id="memberPwCk" placeholder="비밀번호 입력확인" required>
                <span id="messagePwCk"></span>
            </div>
            <div>
                <label for="memberName">이름</label>
                <input type="text" name="memberName" placeholder="이름 입력" required>
            </div>
            <div>
                <label for="memberPhone">휴대폰 번호</label>
                <input type="text" name="memberPhone" id="memberPhone" placeholder="휴대폰 번호 입력" required>
                <span id="messagePhone"></span>
            </div>
            <div>
            	<label for="memberEmail">이메일</label>
          		<input type="text" name="memberEmail" id="memberEmail" placeholder="이메일 입력" required>
       		</div>
            <input type="submit" id="registerBtn" value="회원가입">
        </form>
    </div>
	
	<input type="hidden" id="alert" value="${result }">


	<script type="text/javascript">
		$(document).ready(function() {
			var isIdAvailable = false;
			var isPwAvailable = false;
			var isPhoneAvailable = false;
			
			$("#memberId").blur(function() {
				var memberId = $(this).val(); // 아이디 입력값 가져오기
				if (memberId != "") { // 아이디 입력했을때
					$.ajax({
						type : 'post',
						url : '/hotel/member/idCheck', // controller의 idcheck로 보냄
						data : {
							memberId : memberId
						},
						success : function(data) {

							if (data == 1) {// 중복된 아이디가 있을때
								$("#messageId").html("이미 사용중인 아이디입니다!").css("color", "red");
								$("#useBtn, #returnBtn").hide();
								isIdAvailable = false;
							} else { // 중복된 아이디 없을때
								$("#messageId").html("사용 가능한 아이디입니다!").css("color", "blue");
								$("#useBtn, #returnBtn").show();
								isIdAvailable = true;
							}
						},
						error : function() {
							$("#messageId").html("서버에서 오류가 발생하였습니다!").css("color", "red");
							isIdAvailable = false;
							//console.log(data);
						}
					}); // end ajax
				}
			}); // end memberId.blur()
			
			// 사용 버튼을 누르면
			$("#useBtn").click(function() {
				var memberId = $('#memberId').val();
				$(this).hide();
			    $("#memberId").prop("readonly", true).css("color", "blue");
			    $("#messageId").html('회원님의 아이디는 : ' + memberId + '입니다.').css("color", "blue");
			}); // end useBtn.click()
			
			// 다시입력 버튼을 누르면
			$('#returnBtn').click(function(){
				$('#memberId').prop('readonly', false).val('');
				$("#useBtn, #returnBtn").hide();
				$("#messageId").html('아이디를 다시 입력 후 중복체크를 해주세요').css("color", "red");
			}); // end returnBtn.click()

			// 비밀번호 확인 이벤트
			$("#memberPwCk").blur(function() {
				var memberPwCk = $(this).val();
				var memberPw = $("#memberPw").val();
				if (memberPwCk == memberPw) {
					$("#messagePwCk").html("비밀번호가 일치합니다!").css("color", "blue");
				} else {
					$("#messagePwCk").html("비밀번호가 일치하지 않습니다!").css("color", "red");
				}
			}); // end memberPwCk.blur()

			// 정규 표현식
			$("#memberPw").blur(function() {
    			var memberPw = $("#memberPw").val();
    			// 비밀번호 정규 표현식
    			var exp = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,}$/;
    			if (!exp.test(memberPw)) {
        			$("#messagePw").html("비밀번호는 영문, 숫자, 특수문자를 각각 하나 이상 포함하고, 최소 8자 이상이어야 합니다!").css("color", "red");
        			isPwAvailable = false;
    			} else {
        			$("#messagePw").html("사용 가능한 비밀번호입니다!").css("color", "blue");
        			isPwAvailable = true;
    			}
			}); // end memberPwCk.blur()

			// 휴대폰 번호 중복체크
			$("#memberPhone").blur(function() {
				var memberPhone = $(this).val(); // 휴대폰 번호 입력값 가져오기
				if (memberPhone != "") { // 휴대폰 번호 입력했을때
					$.ajax({
						type : 'POST',
						url : '/hotel/member/phoneCheck', // controller의 phonecheck로 보냄
						data : {
							memberPhone : memberPhone
						},
						success : function(data) {
							if (data == 1) {// 중복된 휴대번호 있을때
								$("#messagePhone").html("이미 사용중인 휴대폰 번호입니다!").css("color", "red");
								isPhoneAvailable = false;
							} else { // 중복된 휴대번호 없을때
								$("#messagePhone").html("사용 가능한 휴대폰 번호입니다!").css("color", "blue");
								isPhoneAvailable = true;
							}
						},
						error : function() {
							$("#messagePhone").html("서버에서 오류가 발생하였습니다!").css("color", "red");
							isPhoneAvailable = false;
							//console.log(data);
						}
					}); // end ajax
				}
			}); // end memberPhone.blur()
			
			// 이메일 인증
			$("#sendEamilBtn").click(function() {
			    var email = $("#memberEmail").val(); // 이메일 입력값 가져오기
			    console.log(email);
			    // 이메일 주소 유효성 검사 등 필요한 검증 로직 수행
			    
			    // 서버로 이메일 전송 요청 보내기
			    $.ajax({
			        type: "POST",
			        url: "hotel/member/sendEmail", // 이메일 전송을 처리하는 서버의 URL
			        data: { email: email },
			        success: function(response) {
			            alert("인증번호가 이메일로 전송되었습니다.");
			        },
			        error: function(xhr, status, error) {
			            alert("이메일 전송에 실패했습니다. 다시 시도해주세요.");
			        }
			    });
			});
			
			// 회원 가입 양식 체크 후 가입 허용 여부 판단
			$("form").submit(function(e){
				var memberPw = $("#memberPw").val();
				var memberPwCk = $("#memberPwCk").val();
				
				if (!isIdAvailable) {
					alert("아이디 중복 체크를 다시해주세요!");
					e.preventDefault();
					return;
				} else if (!isPwAvailable) {
				    alert("비밀번호 형식을 다시 확인해주세요!");
				    e.preventDefault();
				    return;
				} else if (!isPhoneAvailable) {
				    alert("휴대폰 번호 중복 체크를 다시해주세요!");
				    e.preventDefault();
				    return;
				} else if (memberPw !== memberPwCk) {
					alert("비밀번호와 비밀번호 확인이 일치하지 않습니다!");
					e.preventDefault(); // 가입을 막음
					return;
				}
			}); // end form.submit()
			
			$('#useBtn, #returnBtn').click(function(e){
				 e.preventDefault(); // 폼 제출 동작 막기
			}); // end click
			
		}); // end document
	</script>
</body>
</html>