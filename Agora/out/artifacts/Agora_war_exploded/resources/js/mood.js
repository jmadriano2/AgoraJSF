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
    var chart = new google.visualization.PieChart(document.getElementById('j_idt65:mood'));
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
        chart.draw(data, options);
    }

    google.visualization.events.addListener(chart, 'select', selectHandler);
}

function changeButton(currentMood) {
    if (currentMood == 0) {
        document.getElementById("j_idt65:my-mood").firstChild.innerHTML = "Sad";
        document.getElementById("j_idt65:hidden").value = "Sad";
        document.getElementById("j_idt65:my-mood").childNodes[0].setAttribute("style", "height:50px; font-size: 24px; font-weight: bold; background: #3366cc; color: white;")
    } else if (currentMood == 1) {
        document.getElementById("j_idt65:my-mood").firstChild.innerHTML = "Angry";
        document.getElementById("j_idt65:hidden").value = "Angry";
        document.getElementById("j_idt65:my-mood").childNodes[0].setAttribute("style", "height:50px; font-size: 24px; font-weight: bold; background: #dc3912; color: white;")
    } else if (currentMood == 2) {
        document.getElementById("j_idt65:my-mood").firstChild.innerHTML = "Happy";
        document.getElementById("j_idt65:hidden").value = "Happy";
        document.getElementById("j_idt65:my-mood").childNodes[0].setAttribute("style", "height:50px; font-size: 24px; font-weight: bold; background: #ff9900; color: white;")
    } else if (currentMood == 3) {
        document.getElementById("j_idt65:my-mood").firstChild.innerHTML = "Disgusted";
        document.getElementById("j_idt65:hidden").value = "Disgusted";
        document.getElementById("j_idt65:my-mood").childNodes[0].setAttribute("style", "height:50px; font-size: 24px; font-weight: bold; background: #109618; color: white;")
    } else if (currentMood == 4) {
        document.getElementById("j_idt65:my-mood").firstChild.innerHTML = "Fearful";
        document.getElementById("j_idt65:hidden").value = "Fearful";
        document.getElementById("j_idt65:my-mood").childNodes[0].setAttribute("style", "height:50px; font-size: 24px; font-weight: bold; background: #990099; color: white;")
    }
}

