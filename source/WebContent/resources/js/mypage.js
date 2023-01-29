var chart = null;
window.onload = function() {
	let range = document.getElementsByName('range')[0];
	
	var servey_avg_data = document.getElementById('servey_avg_data');
	var score_avg_data = document.getElementById('score_avg_data');
	
	var result_avgScore =  avgScore();
	console.log(typeof result_avgScore[1]);
	if(typeof result_avgScore[0] || typeof result_avgScore[1]) {
		servey_avg_data.innerHTML = "-";
		score_avg_data.innerHTML = "-";
	} else {
		servey_avg_data.innerHTML = result_avgScore[0].toFixed(1);
		score_avg_data.innerHTML = result_avgScore[1].toFixed(1);
		
	}
	graph();
	
	range.onchange = function() {
		var result_avgScore =  avgScore();
		if(typeof result_avgScore[0] || typeof result_avgScore[1]) {
			servey_avg_data.innerHTML = "-";
			score_avg_data.innerHTML = "-";
		} else {
			servey_avg_data.innerHTML = result_avgScore[0].toFixed(1);
			score_avg_data.innerHTML = result_avgScore[1].toFixed(1);
			
		}
		
		graph();
	}
	
	
}


function avgScore() {
	var result = [];
	var todayOrg = new Date();
	const year = todayOrg.getFullYear();
	const month = todayOrg.getMonth() + 1;
	const date = todayOrg.getDate();
	var today = `${year}-${month >= 10 ? month : '0' + month}-${date >= 10 ? date : '0' + date}`;
	let range = document.getElementsByName('range')[0];
	let scvo_date;
	let scvo_ser_1;
	let scvo_ser_2;
	let scvo_ser_3;
	let scvo_ser_4;
	let scvo_ser_5;
	let scvo_score_1;
	let arr_servey = [];
	let arr_score = [];
	$.ajax({
		url: getContextPath() + "/callAllServey.do", //해당 날짜 설문내용 호출
		type: "post",
		async: false,
		data: {
			mid: $('#mid').val()
		},
		dataType: "json",
		success: function(value) {
			
			if (value === "fail") {

			} else {
				if(range.value === 'week') {
					var lastweekOrg = new Date(todayOrg.setDate(todayOrg.getDate() - 7));
					const year = lastweekOrg.getFullYear();
					const month = lastweekOrg.getMonth() + 1;
					const date = lastweekOrg.getDate();
					var lastWeek = `${year}-${month >= 10 ? month : '0' + month}-${date >= 10 ? date : '0' + date}`;
				} else if(range.value === 'month') {
					var lastMonthOrg = new Date(todayOrg.setMonth(todayOrg.getMonth() - 1)) ;
					const year = lastMonthOrg.getFullYear();
					const month = lastMonthOrg.getMonth() + 1;
					const date = lastMonthOrg.getDate();
					var lastMonth = `${year}-${month >= 10 ? month : '0' + month}-${date >= 10 ? date : '0' + date}`;
				} else if(range.value === 'year') {
					var lastYearOrg = new Date(todayOrg.setYear(todayOrg.getYear() - 1)) ;
					const year = lastYearOrg.getFullYear();
					const month = lastYearOrg.getMonth() + 1;
					const date = lastYearOrg.getDate();
					var lastYear = `${year}-${month >= 10 ? month : '0' + month}-${date >= 10 ? date : '0' + date}`;
				}
				
				for (let i = 0; i < value.list.length; i++) {
					scvo_date = value.list[i].dDate;
					scvo_ser_1 = value.list[i].ser_1;
					scvo_ser_2 = value.list[i].ser_2;
					scvo_ser_3 = value.list[i].ser_3;
					scvo_ser_4 = value.list[i].ser_4;
					scvo_ser_5 = value.list[i].ser_5;
					scvo_score_1 = value.list[i].score_1;
					scvo_ser_diary = value.list[i].ser_diary;
					if(range.value === 'week') {
						if(scvo_date <= today && scvo_date > lastWeek) {
							var sum = scvo_ser_1+scvo_ser_2+scvo_ser_3+scvo_ser_4+scvo_ser_5;
							arr_servey.push(sum/5);
							arr_score.push(scvo_score_1);
						}
					} else if(range.value === 'month') {
						if(scvo_date <= today && scvo_date > lastMonth) {
							var sum = scvo_ser_1+scvo_ser_2+scvo_ser_3+scvo_ser_4+scvo_ser_5;
							arr_servey.push(sum/5);
							arr_score.push(scvo_score_1);
						}
					} else if(range.value === 'year') {
						if(scvo_date <= today && scvo_date > lastYear) {
							var sum = scvo_ser_1+scvo_ser_2+scvo_ser_3+scvo_ser_4+scvo_ser_5;
							arr_servey.push(sum/5);
							arr_score.push(scvo_score_1);
						}
					}
				}
				
				
				let arr_serveySum = 0;
				let arr_scoreSum = 0;
				for(let j=0; j<arr_servey.length; j++) {
					arr_serveySum += arr_servey[j];
					arr_scoreSum += arr_score[j];
				}
				var servey_avg = arr_serveySum/arr_servey.length;
				result.push(servey_avg);
				var score_avg = arr_scoreSum/arr_score.length;
				result.push(score_avg);
			}
		},
		error: function(request, status, error) {
			alert("code" + request.status + "\n" + "message : " + request.responseText + "\nerror" + error);
		}
	});
	console.log("#### result ####");
	console.log(result);
	return result;
}

function graph() {
	var result = [];
	var todayOrg = new Date();
	const year = todayOrg.getFullYear();
	const month = todayOrg.getMonth() + 1;
	const date = todayOrg.getDate();
	var today = `${year}-${month >= 10 ? month : '0' + month}-${date >= 10 ? date : '0' + date}`;
	let range = document.getElementsByName('range')[0];
	let arr_servey = [];
	let arr_score = [];
	$.ajax({
		url: getContextPath() + "/callAllServey.do", //해당 날짜 설문내용 호출
		type: "post",
		async: false,
		data: {
			mid: $('#mid').val()
		},
		dataType: "json",
		success: function(value) {
			if (value === "fail") {

			} else {
				
				var graph_data_1 = [];
				var graph_data_2 = [];
				var graph_range = [];
				
				if(range.value === 'week') {
					for(let k=0; k<7; k++){
						let graphDate = new Date();
						let dayRangeOrg = new Date(graphDate.setDate(graphDate.getDate() -6 +k));
						const year = dayRangeOrg.getFullYear();
						const month = dayRangeOrg.getMonth() + 1;
						const date = dayRangeOrg.getDate();
						let dayRange = `${month >= 10 ? month : '0' + month}-${date >= 10 ? date : '0' + date}`;	
						graph_range.push(dayRange);
						
						let dayRangeFull = `${year}-${month >= 10 ? month : '0' + month}-${date >= 10 ? date : '0' + date}`;
						console.log(dayRangeFull);
						if(value.list.find(e => e.dDate === dayRangeFull) !== undefined) {
							let servey_arr = value.list.find(e => e.dDate === dayRangeFull);
							console.log(servey_arr);
							let servey_sum = 0;
							servey_sum += servey_arr.ser_1 + servey_arr.ser_2 + servey_arr.ser_3 + servey_arr.ser_4 + servey_arr.ser_5;
							
							graph_data_1.push(servey_sum/5);
							
							let score_arr = value.list.find(e => e.dDate === dayRangeFull);
							let score = score_arr.score_1;
							
							graph_data_2.push(score);
						} else {
							graph_data_1.push(0);
							graph_data_2.push(0);
						}
					}
					console.log("###  graph data 1 ####");
				} else if(range.value === 'month') {
					for(let k=0; k<31; k++){
						let graphDate = new Date();
						let monthRangeOrg = new Date(graphDate.setDate(graphDate.getDate() -30 +k));
						const year = monthRangeOrg.getFullYear();
						const month = monthRangeOrg.getMonth() + 1;
						const date = monthRangeOrg.getDate();
						let monthRange = `${month >= 10 ? month : '0' + month}-${date >= 10 ? date : '0' + date}`;	
						graph_range.push(monthRange);
						
						let monthRangeFull = `${year}-${month >= 10 ? month : '0' + month}-${date >= 10 ? date : '0' + date}`;
						if(value.list.find(e => e.dDate === monthRangeFull) !== undefined) {
							let servey_arr = value.list.find(e => e.dDate === monthRangeFull);
							let servey_sum = 0;
							servey_sum += servey_arr.ser_1 + servey_arr.ser_2 + servey_arr.ser_3 + servey_arr.ser_4 + servey_arr.ser_5;
							
							graph_data_1.push(servey_sum/5);
							
							let score_arr = value.list.find(e => e.dDate === monthRangeFull);
							let score = score_arr.score_1;
							
							graph_data_2.push(score);
						} else {
							graph_data_1.push(0);
							graph_data_2.push(0);
						}
						
					}
					console.log("###  graph data 2  ####");
				} else if(range.value === 'year') {
					for(let k=0; k<12; k++){
						let graphDate = new Date();
						let yearRangeOrg = new Date(graphDate.setMonth(graphDate.getMonth() -11 +k));
						const year = yearRangeOrg.getFullYear();
						const month = yearRangeOrg.getMonth() + 1;
						const date = yearRangeOrg.getDate();
						let yearRange = `${year}-${month >= 10 ? month : '0' + month}`;
						graph_range.push(yearRange);
						let yearRangeFull = `${year}-${month >= 10 ? month : '0' + month}-${date >= 10 ? date : '0' + date}`;
						
						let firstDay = new Date(year, month-1, 1);
						const year_first = firstDay.getFullYear();
						const month_first = firstDay.getMonth()+1;
						const date_first = firstDay.getDate();
						let firstDayFull = `${year_first}-${month_first >= 10 ? month_first : '0' + month_first}-${date_first >= 10 ? date_first : '0' + date_first}`;

						let lastDay = new Date(year, month, 0);
						const year_last = lastDay.getFullYear();
						const month_last = lastDay.getMonth() + 1;
						const date_last = lastDay.getDate();
						let lastDayFull = `${year_last}-${month_last >= 10 ? month_last : '0' + month_last}-${date_last >= 10 ? date_last : '0' + date_last}`;
						
						let data_table_each;
						let servey_sum_month = [];
						let score_sum_month = [];
						let yearRangeFull_s = new Date(firstDay.setDate(firstDay.getDate()-1));
						for(let a=0; a<(date_last); a++) { //한달 내의 데이터들의 평균값 도출
							yearRangeFull_s = new Date(firstDay.setDate(firstDay.getDate()+1));
							const year_s = yearRangeFull_s.getFullYear();
							const month_s = yearRangeFull_s.getMonth() + 1;
							const date_s = yearRangeFull_s.getDate();
							let dateSample = `${year_s}-${month_s >= 10 ? month_s : '0' + month_s}-${date_s >= 10 ? date_s : '0' + date_s}`;
							data_table_each = value.list.find(e => e.dDate ===dateSample);
							if(value.list.find(e => e.dDate === dateSample)) {
								//각 데이터테이블을 배열에 삽입
								let servey_sum = 0;
								servey_sum += data_table_each.ser_1 + data_table_each.ser_2 + data_table_each.ser_3 + data_table_each.ser_4 + data_table_each.ser_5;
								servey_sum_month.push(servey_sum/5);
								
								score_sum_month.push(data_table_each.score_1);
							}
						}
						let month_sum = 0;
						let score_sum = 0;
						for(let f=0; f<servey_sum_month.length; f++) {
							month_sum += servey_sum_month[f];
							score_sum += score_sum_month[f];
						}
						let month_avg = (month_sum/servey_sum_month.length);
						let score_avg = (score_sum/score_sum_month.length);
						
						if(month_avg > 0 && score_avg > 0) {
							graph_data_1.push(month_avg);
							graph_data_2.push(score_avg);
						} else {
							graph_data_1.push(0);
							graph_data_2.push(0);
						}
						
					}
					
					console.log("###  graph data  ####");
					console.log(graph_data_1);
					console.log(graph_data_2);
				}
				//console.log(chart);
				console.log(document.getElementById('myChart').getContext('2d'));
				
				var ctx = document.getElementById('myChart').getContext('2d'); // 그래프
				if(chart == null){
					chart = new Chart(ctx, {
				        type: 'bar',
				        data: {
				            labels: graph_range,
				            datasets: [{
				                label: '설문 평균',
				                backgroundColor: 'rgb(255, 99, 132)',
				                borderColor: 'rgb(255, 99, 132)',
				                data: graph_data_1
				            	},
								{
				                label: '점수 평균',
				                backgroundColor: 'rgb(255,160,123)',
				                borderColor: 'rgb(255,160,123)',
				                data: graph_data_2
				            }]
			        	}
			    	});
				} else {
					chart.config = {
				        type: 'bar',
				        data: {
				            labels: graph_range,
				            datasets: [{
				                label: '설문 평균',
				                backgroundColor: 'rgb(255, 99, 132)',
				                borderColor: 'rgb(255, 99, 132)',
				                data: graph_data_1
				            	},
								{
				                label: '점수 평균',
				                backgroundColor: 'rgb(255,160,123)',
				                borderColor: 'rgb(255,160,123)',
				                data: graph_data_2
				            }]
				        }
			    	};
					chart.update();
				}
			    
			}
		},
		error: function(request, status, error) {
			alert("code" + request.status + "\n" + "message : " + request.responseText + "\nerror" + error);
		}
	});
}

function changeName() {
	if($('#nameInputArea').length > 0) {
		
	} else {
		let nameArea = document.getElementById('nameArea');
		let nameInput = document.createElement('div');
		nameInput.setAttribute('id', 'nameInputArea');
		nameArea.appendChild(nameInput);
		
		
		let nameInputTag_1 = document.createElement('input');
		nameInputTag_1.setAttribute('type', 'text');
		nameInputTag_1.setAttribute('name', 'newName');
		nameInputTag_1.setAttribute('placeholder', '변경할 닉네임을 입력해 주세요.');
		nameInput.appendChild(nameInputTag_1);
	}
	
}

function changePwd() {
	if($('#pwdInputArea').length > 0) {
		
	} else {
		let pwdArea = document.getElementById('pwdArea');
		let pwdInput = document.createElement('div');
		pwdInput.setAttribute('id', 'pwdInputArea')
		pwdArea.appendChild(pwdInput);
		
		let pwdInputTag_1 = document.createElement('input');
		pwdInputTag_1.setAttribute('type', 'password');
		pwdInputTag_1.setAttribute('name', 'newPwd');
		pwdInputTag_1.setAttribute('placeholder', '비밀번호를 입력해 주세요.');
		pwdInput.appendChild(pwdInputTag_1);
		
		let pwdInputTag_2 = document.createElement('input');
		pwdInputTag_2.setAttribute('type', 'password');
		pwdInputTag_2.setAttribute('name', 'newPwd_2');
		pwdInputTag_2.setAttribute('placeholder', '위와 동일하게 입력해 주세요.');
		pwdInput.appendChild(pwdInputTag_2);
	}
	
}

function change() {
	
}

















