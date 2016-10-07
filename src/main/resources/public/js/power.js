 $(document).ready(function() {
	 	var ERRORTEXT = "Power Name must be alphanumeric and can contain underscores and" +
	 			" hypens only and contain between 3 and 13 characters, and descrption must" +
	 			" be between 3 and 100 characters";
	 	$("#name, #powertypes1, #description, #updatename, #powertypes2, #updatedescription", "#removepower").focus( function(){
	 		$(".errors").empty();
	 	});
        $("#createpower").click(function() {

            var power = {};

            power.name = $("#name").val();
            power.type = {};
            power.type.id = $("#powertypes1").val();
            power.description = $("#description").val();
            if(validatePower(power.name,power.description)){
            $.ajax({
                url : '/powers',
                method : 'POST',
                data : JSON.stringify(power),
                contentType : 'application/json'
            }).then(function(errors) {
            	listPowerTable();
            	listPowers();
            	$(".errors").empty();
              	$(".errors").append(errors);
              	if(errors==""){
              		toast("Power Added", true);
              	}
              	else{
              		toast("Erroe, no power added",false);
              	}
            }, function(errors) {
            	$(".errors").empty();
              	$(".errors").append("Error: " + errors);
            });
            }
            else{
            	$(".errors").empty();
            	$(".errors").append("Error: " + ERRORTEXT);
            }
        });
       function listPowerTypes(){
                    $.ajax({
                                url : '/powertypes',
                                method : 'GET'
                            })
                            .then(
                                    function(powertypes) {
                                        for (var i = 0; i < powertypes.length; i++) {
                                            var type = powertypes[i];
                                            var row = '<option value="' + type.id + '">'
                                                    + type.id
                                                    + ', '
                                                    + type.type
                                                    + '</option>';
                                            $("#powertypes1, #powertypes2, #searchpowertype").append(row);
                                        }
                                    });
       }
       listPowerTypes();

                    $("#removeBtn").click(function() {
                        $.ajax({
                            url : '/powers/' + $("#removepower").val(),
                            method : 'DELETE'
                        }).then(function(errors) {
                        	listPowerTable();
                        	listPowers();
                        	$(".errors").empty();
                          	$(".errors").append(errors);
                        }, function(errors) {
                        	$(".errors").empty();
                          	$(".errors").append(errors);
                        });
                    });
                function listPowerTable(){
                	 $("#result").children().remove();
                    $.ajax({
                        url : '/powers',
                        method : 'GET'
                    }).then(
                            function(powers) {
                                for (var i = 0; i < powers.length; i++) {
                                    var power = powers[i];
                                    var row = "<tr><td>" + power.id
                                            + "</td><td>" + power.name
                                            + "</td><td>" + power.type.type
                                            + "</td><td>" + power.description 
                                            + "</td></tr>";
                                    $("#result").append(row);
                                }
                            });
                }
                listPowerTable();
                function listPowers(){
                	 $("#removepower, #powers").children().remove();
                    $.ajax({
                            url: '/powers',
                            method: 'GET'
                        })
                        .then(
                            function(powers) {
                                for (var i = 0; i < powers.length; i++) {
                                    var power = powers[i];
                                    var row = '<option value="' + power.id + '">' + power.name + ', ' + power.type.type + '</option>';
                                    $("#removepower,#powers").append(row);
                                }
                            });
                }
                listPowers();
                
                    $("#powers").change(function(){
                    	$("#powerId").val($("#powers").val());
                    });
                    $("#updatepower").click(function() {
                        var power = {};
                        power.id = $("#powerId").val();
                        power.name = $("#updatename").val();
                        power.description = $("#updatedescription").val();
                        power.type = {};
                        power.type.id =	$("#powertypes2").val();
                      if(power.name,power.description){
                        $.ajax({
                            url: '/powers/' + power.id,
                            method: 'PUT',
                            data: JSON.stringify(power),
                            contentType: 'application/JSON'
                        }).then(function() {
                        	listPowerTable();
                        	listPowers();
                        }, function(errors) {
                        	$(".errors").empty();
                          	$(".errors").append("Error: " + errors);
                        });
                      }
                      else{
                    	 $(".errors").empty();
                      	$(".errors").append("Error: " + ERRORTEXT);
                      }
                    });
                    
                    function validatePower(name, description){
                    	var inputPatternName = new RegExp(/^[a-z0-9_ -]{3,30}$/i);
                    	var isNameValid = inputPatternName.test(name);
                    	var patternDescription = "^\s*$"
                        var inputPatternDescription = new RegExp(patternDescription);
                        var isDescriptionValid = (!inputPatternDescription.test(description) && description.length<100);
                        return (isDescriptionValid && isNameValid)? true: false;
                    }
                    function toast(message, successful) {
                        toastr.options = {
                            "positionClass": "toast-top-center",
                            "preventDuplicates": true
                        }

                        var status;
                        status = successful ? "Success" : "Error";

                        toastr[status.toLowerCase()](message, status);

                    }
                    
                    $("#searchSubmit").click(function(){
                    var searchPower = {};
                    var search = $("#search").val();
                    var searchArr = search.split(' ');
                    searchArr = searchArr.filter(function(str) {
                        return /\S/.test(str);
                    });
                    searchPower.searchList = searchArr;
                    searchPower.all = $('.all').prop('checked');
                    searchPower.type = "power";
                    
                    $.ajax({
                            url: '/search',
                            method: 'POST',
                            data: JSON.stringify(searchPower),
                            contentType: 'application/JSON'
                    }).then(function(powers) {
                        	$("#result").children().remove();
                        	for (var i = 0; i < powers.length; i++) {
                                var power = powers[i];
                                var row = "<tr><td>" + power.id
                                        + "</td><td>" + power.name
                                        + "</td><td>" + power.type.type
                                        + "</td><td>" + power.description 
                                        + "</td></tr>";
                                $("#result").append(row);
                        }
                    });
                    });
                    $("#clearSearch").click(function(){
                    	$("#search").val('');
                    	$(".all").attr("checked", true);
                    	listPowerTable();
                    })
                
                   
    });