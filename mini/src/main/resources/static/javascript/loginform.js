/*logintest.jsp*/
$(document).ready(function(){
    var urlParams = new URLSearchParams(window.location.search);
    if (urlParams.get('result') == 'wrong') {
        alert("로그인 실패! 다시 시도해 주세요.");
    }
	if (urlParams.get('registerresult') == 'success'){
		alert("회원가입 성공! 로그인하세요.");
	}
});