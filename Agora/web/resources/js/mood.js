google.charts.load('current', {'packages': ['corechart']});
google.charts.setOnLoadCallback(drawChart);

function drawChart() {
    var mood_happy = parseInt(document.getElementById("mood_happy").innerHTML);
    var mood_sad = parseInt(document.getElementById("mood_sad").innerHTML);
    var mood_angry = parseInt(document.getElementById("mood_angry").innerHTML);
    var mood_disgusted = parseInt(document.getElementById("mood_disgusted").innerHTML);
    var mood_fearful = parseInt(document.getElementById("mood_fearful").innerHTML);

    var mood = parseInt(document.getElementById("selected-mood").innerHTML);
    changeButton(mood);

    var data = google.visualization.arrayToDataTable([
        ['Mood', 'Number of Votes'],
        ['Sad', mood_sad],
        ['Angry', mood_angry],
        ['Happy', mood_happy],
        ['Disgusted', mood_disgusted],
        ['Fearful', mood_fearful]
    ]);

    var options = {
        title: 'How People Feel About It',
        pieHole: 0.5
    };
    var chart = new google.visualization.PieChart(document.getElementById('update:mood'));
    chart.draw(data, options);

    function selectHandler() {
        var votes;
        var selectedItem = chart.getSelection()[0];
        var selectedMood = parseInt(document.getElementById("selected-mood").innerHTML);
        if (selectedItem) {
            votes = data.getValue(selectedItem.row, 1);
            document.getElementById("selected-mood").innerHTML = selectedItem.row;
            votes++;
            data.setValue(selectedItem.row, 1, votes);

            changeButton(selectedItem.row);

            if (selectedMood == 0) {
                votes = data.getValue(selectedMood, 1);
                votes--;
                data.setValue(selectedMood, 1, votes);
            } else if (selectedMood == 1) {
                votes = data.getValue(selectedMood, 1);
                votes--;
                data.setValue(selectedMood, 1, votes);
            } else if (selectedMood == 2) {
                votes = data.getValue(selectedMood, 1);
                votes--;
                data.setValue(selectedMood, 1, votes);
            } else if (selectedMood == 3) {
                votes = data.getValue(selectedMood, 1);
                votes--;
                data.setValue(selectedMood, 1, votes);
            } else if (selectedMood == 4) {
                votes = data.getValue(selectedMood, 1);
                votes--;
                data.setValue(selectedMood, 1, votes);
            }
        }

        document.getElementById("update:hidden_sad").value = data.getValue(0,1);
        document.getElementById("update:hidden_angry").value = data.getValue(1,1);
        document.getElementById("update:hidden_happy").value = data.getValue(2,1);
        document.getElementById("update:hidden_disgusted").value = data.getValue(3,1);
        document.getElementById("update:hidden_fearful").value = data.getValue(4,1);

        chart.draw(data, options);
    }

    google.visualization.events.addListener(chart, 'select', selectHandler);
}

function changeButton(currentMood) {
    if (currentMood == 0) {
        document.getElementById("update:my-mood").firstChild.innerHTML = "Sad";
        document.getElementById("update:hidden").value = "Sad";
        document.getElementById("update:my-mood").childNodes[0].setAttribute("style", "height:50px; font-size: 24px; font-weight: bold; background: #3366cc; color: white;")
    } else if (currentMood == 1) {
        document.getElementById("update:my-mood").firstChild.innerHTML = "Angry";
        document.getElementById("update:hidden").value = "Angry";
        document.getElementById("update:my-mood").childNodes[0].setAttribute("style", "height:50px; font-size: 24px; font-weight: bold; background: #dc3912; color: white;")
    } else if (currentMood == 2) {
        document.getElementById("update:my-mood").firstChild.innerHTML = "Happy";
        document.getElementById("update:hidden").value = "Happy";
        document.getElementById("update:my-mood").childNodes[0].setAttribute("style", "height:50px; font-size: 24px; font-weight: bold; background: #ff9900; color: white;")
    } else if (currentMood == 3) {
        document.getElementById("update:my-mood").firstChild.innerHTML = "Disgusted";
        document.getElementById("update:hidden").value = "Disgusted";
        document.getElementById("update:my-mood").childNodes[0].setAttribute("style", "height:50px; font-size: 24px; font-weight: bold; background: #109618; color: white;")
    } else if (currentMood == 4) {
        document.getElementById("update:my-mood").firstChild.innerHTML = "Fearful";
        document.getElementById("update:hidden").value = "Fearful";
        document.getElementById("update:my-mood").childNodes[0].setAttribute("style", "height:50px; font-size: 24px; font-weight: bold; background: #990099; color: white;")
    }
}

