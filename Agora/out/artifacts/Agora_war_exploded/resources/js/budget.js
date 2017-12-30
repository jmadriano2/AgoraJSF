var budget_materials = document.getElementById("budget_materials").innerHTML;
var budget_operations = document.getElementById("budget_operations").innerHTML;
var budget_management = document.getElementById("budget_management").innerHTML;
var budget_labor = document.getElementById("budget_labor").innerHTML;
var budget_misc = document.getElementById("budget_misc").innerHTML;

var chart = AmCharts.makeChart("chartdiv",
    {
        "type": "serial",
        "theme": "light",
        "dataProvider": [{
            "name": "Materials",
            "points": budget_materials,
            "color": "#98fb98",
            "bullet": "../resources/images/materials.png"
        }, {
            "name": "Operations",
            "points": budget_operations,
            "color": "#FEC514",
            "bullet": "../resources/images/operations.png"
        }, {
            "name": "Management",
            "points": budget_management,
            "color": "#7F8DA9",
            "bullet": "../resources/images/management.png"
        }, {
            "name": "Labor",
            "points": budget_labor,
            "color": "#DB4C3C",
            "bullet": "../resources/images/labor.png"
        }, {
            "name": "Miscellaneous",
            "points": budget_misc,
            "color": "#DAF0FD",
            "bullet": "../resources/images/misc.png"
        }],
        "valueAxes": [{
            "maximum": 1500000,
            "minimum": 0,
            "axisAlpha": 0,
            "dashLength": 4,
            "position": "left"
        }],
        "startDuration": 1,
        "graphs": [{
            "balloonText": "<span style='font-size:13px;\'>[[category]]: <b>[[value]]</b></span>",
            "bulletOffset": 10,
            "bulletSize": 52,
            "colorField": "color",
            "cornerRadiusTop": 8,
            "customBulletField": "bullet",
            "fillAlphas": 0.8,
            "lineAlpha": 0,
            "type": "column",
            "valueField": "points"
        }],
        "marginTop": 0,
        "marginRight": 0,
        "marginLeft": 0,
        "marginBottom": 0,
        "autoMargins": false,
        "categoryField": "name",
        "categoryAxis": {
            "axisAlpha": 0,
            "gridAlpha": 0,
            "inside": true,
            "tickLength": 0
        },
        "export": {
            "enabled": true
        }
    });