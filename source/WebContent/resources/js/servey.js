











function addServey() {
	let mid = $('#mid').val();
	let date = document.getElementsByName('date')[0];
	let q_1 = document.querySelector('input[type=radio][name=q_1]:checked');
	let q_2 = document.querySelector('input[type=radio][name=q_2]:checked');
	let q_3 = document.querySelector('input[type=radio][name=q_3]:checked');
	let q_4 = document.querySelector('input[type=radio][name=q_4]:checked');
	let q_5 = document.querySelector('input[type=radio][name=q_5]:checked');
	let score = document.getElementsByName('score')[0];
	let diary = document.getElementsByName('diary')[0];
	let chk = 0;
	console.log(date.value);
	console.log(q_1.value);
	console.log(score.value);
	console.log(diary.value);
	
	$.ajax({
		url: getContextPath()+"/insertServey.do",
		type: "post",
		async: false,
		data: {
			mid: mid,
			date: date.value,
			q_1: q_1.value,
			q_2: q_2.value,
			q_3: q_3.value,
			q_4: q_4.value,
			q_5: q_5.value,
			score: score.value,
			diary: diary.value
		},
		dataType: "text",
		success: function(value) {
			if(value === 'fail') {
				alert('제출에 실패하였습니다.');
			} else {
				chk++;
			}
		},
		error: function(request, status, error) {
			alert("code" + request.status + "\n" + "message : " + request.responseText + "\nerror" + error);
		}	
		
	});
	if(chk > 0) {
		location.href = getContextPath()+"/main";
	}
}