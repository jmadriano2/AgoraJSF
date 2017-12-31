google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);

function drawChart() {
    var data = google.visualization.arrayToDataTable([
        ['Task', 'Hours per Day'],
        ['Sad', 1],
        ['Anger', 1],
        ['Joy', 1],
        ['Disgust', 1],
        ['Fear', 1]
    ]);

    var options = {
        title: 'How People Feel About It',
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