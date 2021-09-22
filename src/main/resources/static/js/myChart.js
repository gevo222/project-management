var chartDataStr = decodeHtml(chartData);
var chartJsonArray = JSON.parse(chartDataStr);

var arrayLength	= chartJsonArray.length;

var numericData = [];
var labelData = [];

for(var i = 0; i < arrayLength; i++) {
	numericData[i] = chartJsonArray[i].value;
	labelData[i] = chartJsonArray[i].label;
}

const data = {
  labels: labelData,
  datasets: [{
    label: 'Project Stage Data Count',
    data: numericData,
    backgroundColor: [
      'rgb(255, 99, 132)',
      'rgb(54, 162, 235)',
      'rgb(255, 205, 86)'
    ],
    hoverOffset: 4
  }]
};

new Chart(document.getElementById("myPieChart"), {
	type: 'pie',
  	data: data,
})


function decodeHtml(html){
	var txt = document.createElement("textarea");
	txt.innerHTML = html;
	return txt.value;
}
