google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);

function drawChart() {
    var mood_happy = parseInt(document.getElementById("mood_happy").innerHTML);
    var mood_sad = parseInt(document.getElementById("mood_sad").innerHTML);
    var mood_angry = parseInt(document.getElementById("mood_angry").innerHTML);
    var mood_disgusted = parseInt(document.getElementById("mood_disgusted").innerHTML);
    var mood_fearful = parseInt(document.getElementById("mood_fearful").innerHTML   );

    var data = google.visualization.arrayToDataTable([
        ['Task', 'Hours per Day'],
        ['Sad', mood_sad],
        ['Angry', mood_angry],
        ['Happy', mood_happy],
        ['Disgusted', mood_disgusted],
        ['Fearful', mood_fearful]
    ]);

    var options = {
        pieHole: 0.5,
    };
    var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
    chart.draw(data, options);

    function selectHandler() {
        var selectedItem = chart.getSelection()[0];
        if (selectedItem) {
            var toppings = data.getValue(selectedItem.row, 1);
            toppings++;
        }
        data.setValue(selectedItem.row, 1, toppings);
        chart.draw(data, options);
    }
    google.visualization.events.addListener(chart, 'select', selectHandler);
    chart.draw(data, options);
}