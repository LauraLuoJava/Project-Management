var chartDataStr = decodeHtml(chartData);
var chartJsonArray = JSON.parse(chartDataStr);
var arrayLength = chartJsonArray.length;
var numericData = [];
var labelData = [];

for (var i =0; i < arrayLength; i++){
    numericData[i] = chartJsonArray[i].value;
    labelData[i] = chartJsonArray[i].label;
}
new Chart(document.getElementById("myPieChart"), {
    type: 'pie',
    data : {
        labels: labelData,
        datasets: [{
            label: 'My First Dataset',
            data: numericData,
            backgroundColor: [
                'rgb(255, 99, 132)',
                'rgb(54, 162, 235)',
                'rgb(255, 205, 86)'
            ],
            hoverOffset: 4
        }]
    },
    options:{}
});

function decodeHtml(html){
    var txt = document.createElement("textarea");
    txt.innerHTML = html;
    return txt.value;
}