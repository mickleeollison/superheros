$(document).ready(function() {
		var ERRORTEXT = "Hero, Mutant, Origin Type names must be alphanumeric and can contain underscores and" +
		" hypens only and must be between 3 and 13 characters, costume image must hav a valid exetension, and height and weight can" +
		" only be numbers";
		$("#heroname, #mutantname, .powers1, #origintype, #costumeimage, #height, #weight," +
				" #heronameupdate, #mutantnameupdate, .powers2, #origintypeupdate, #costumeimageupdate, #heightupdate, #weightupdate").focus( function(){
	 		$(".errors").empty();
	 	});
        $("#createhero").click(function() {

            var hero = {};

            hero.heroName = $("#heroname").val();
            hero.mutantName = $("#mutantname").val();
            hero.originType = $("#origintype").val();
            hero.costumeImage = $("#costumeimage").val();
            hero.height = $("#height").val();
            hero.weight = $("#weight").val();
            hero.powers = [];
            $(".powers1").each(function(){
            	hero.powers.push({"id": $(this).val()});
            });
            if(validateHero(hero.heroName,  hero.mutantName,  hero.originType, hero.height, hero.weight, hero.costumeImage)){
            $.ajax({
                url : '/heros',
                method : 'POST',
                data : JSON.stringify(hero),
                contentType : 'application/json'
            }).then(function(res) {
            	$(".errors").empty();
            	$(".errors").append(res);
            	listHeroTable();
            	listHeros();
            	clearForms();
            }, function(errors) {
            	$(".errors").empty();
              	$(".errors").append(errors);
              	});
            }
            else{
           	 $(".errors").empty();
             	$(".errors").append("Error: " + ERRORTEXT);
             }
        });
       function listPowers(){
    	   $(".powers1, .powers2").children().remove();
                    $.ajax({
                                url : '/powers',
                                method : 'GET'
                            })
                            .then(
                                    function(powers) {
                                        for (var i = 0; i < powers.length; i++) {
                                            var power = powers[i];
                                            var row = '<option value="' + power.id + '">'
                                                    + power.id
                                                    + ', '
                                                    + power.name
                                                    + '</option>';
                                            $(".powers1, .powers2").append(row);
                                        }
                                    });
       }
       listPowers();

         $("#removeBtn").click(function() {
             $.ajax({
                            url : '/heros/' + $("#removehero").val(),
                            method : 'DELETE'
                        }).then(function(error) {
                        	listHeroTable();
                        	listHeros();
                        	$(".errors").empty();
                          	$(".errors").append(error);
                        }, function(errors) {
                        	$(".errors").empty();
                          	$(".errors").append("Error: " + errors);
                        });
                    });
                function listHeroTable(){
                	 $("#result").children().remove();
                    $.ajax({
                        url : '/heros',
                        method : 'GET'
                    }).then(
                            function(heros) {
                                for (var i = 0; i < heros.length; i++) {
                                    var hero = heros[i];
                                    var powers = " "
                                    for(j=0; j<hero.powers.length;j++){
                                    	powers += hero.powers[j].name
                                    	powers += ', ';
                                    }
                                    var row = "<tr><td>" + hero.id
                                            + "</td><td>" + hero.heroName
                                            + "</td><td>" + hero.mutantName
                                            + "</td><td>" + hero.originType 
                                            + "</td><td>" + Math.floor(hero.height/12) + "\' " + hero.height%12 + "\""
                                            + "</td><td>" + hero.weight + "lb."
                                            + "</td><td>" + powers
                                    		+ "</td><td>" +  '<img src="' + hero.costumeImage + '" class="img-thumbnail">' 
                                            + "</td></tr>";
                                    $("#result").append(row);
                                }
                            });
                }
                listHeroTable();
                function listHeros(){
                	 $("#removehero, #heros").children().remove();
                    $.ajax({
                            url: '/heros',
                            method: 'GET'
                        })
                        .then(
                            function(heros) {
                                for (var i = 0; i < heros.length; i++) {
                                    var hero = heros[i];
                                    var row = '<option value="' + hero.id + '">' + hero.heroName + ', ' + hero.mutantName + '</option>';
                                    $("#removehero, #heros").append(row);
                                }
                            });
                }
                listHeros();
                
                    $("#heros").change(function(){
                    	$("#heroid").val($("#heros").val());
                    });
                    $("#updatehero").click(function() {
                        var hero = {};
                        hero.id = $("#heroid").val();
                        hero.heroName = $("#heronameupdate").val();
                        hero.mutantName = $("#mutantnameupdate").val();
                        hero.originType = $("#origintypeupdate").val();
                        hero.height = $("#heightupdate").val();
                        hero.weight = $("#weightupdate").val();
                        hero.costumeImage = $("#costumeimageupdate").val();
                        hero.powers = [];
                        $(".powers2").each(function(){
                    		hero.powers.push({"id": $(this).val()});
                        });
                        if(validateHero(hero.heroName,  hero.mutantName,  hero.originType, hero.height, hero.weight, hero.costumeImage)){
                        $.ajax({
                            url: '/heros/' + hero.id,
                            method: 'PUT',
                            data: JSON.stringify(hero),
                            contentType: 'application/JSON'
                        }).then(function(res) {
                        	$(".errors").empty();
                        	$(".errors").append("Error: " + res);
                        	listHeroTable();
                        	listHeros();
                        	clearForms();
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
                    
                    $("#addpower").click(function(){
                    	 var row = '<div class="powers">'
                            + '<label for="powers">Power:</label>'
                         +'<select class="powers1">'
                         + '</select>'
                      + '</div>';
                         $("#addpower").parent().append(row);
                         listPowers();
                    })
                    $("#addpowerupdate").click(function(){
                    	 var row = '<div class="powers">'
                            + '<label for="powers">Power:</label>'
                         +'<select class="powers2">'
                         + '</select>'
                      + '</div>';
                         $("#addpowerupdate").parent().append(row);
                         listPowers();
                    })
                    function validateHero(heroname, mutantname, origintype, height, weight, costumeimage){
                    	var inputPatternName = new RegExp(/^[a-z0-9_ -]{3,30}$/i);
                    	var isHeronameValid = inputPatternName.test(heroname);
                    	var isMutantnameValid = inputPatternName.test(mutantname);
                    	var isOrigintypeValid = inputPatternName.test(origintype);
                        var inputPattern = new RegExp(/^[0-9]{1,10}$/);
                        var isHeightValid = inputPattern.test(height);
                        var isWeightValid = inputPattern.test(weight);
                        return (isHeronameValid && isMutantnameValid && isOrigintypeValid && isHeightValid && isWeightValid)? true: false;

                    }
                    $(".close, btn").click(function(){
                    	clearForms();
                    });
                    function clearForms(){
                    	$("#heroname").val('');
                    	$("#mutantname").val('');
                    	$("#origintype").val('');
                    	$("#height").val('');
                    	$("#weight").val('');
                    	$("#costumeimage").val('');
                    	$("#heronameupdate").val('');
                    	$("#mutantnameupdate").val('');
                    	$("#origintypeupdate").val('');
                    	$("#heightupdate").val('');
                    	$("#weightupdate").val('');
                    	$("#costumeimageupdate").val('');
                    }
                    $("#searchSubmit").click(function(){
                        var searchHero = {};
                        var search = $("#search").val();
                        var searchArr = search.split(' ');
                        searchArr = searchArr.filter(function(str) {
                            return /\S/.test(str);
                        });
                        searchHero.searchList = searchArr;
                        searchHero.all = $('.all').prop('checked');
                        searchHero.type = "hero";
                        $.ajax({
                                url: '/search',
                                method: 'POST',
                                data: JSON.stringify(searchHero),
                                contentType: 'application/JSON'
                        }).then(
                        		function(heros) {
	                            	$("#result").children().remove();
	                                for (var i = 0; i < heros.length; i++) {
	                                    var hero = heros[i];
	                                    var powers = " "
	                                    for(j=0; j<hero.powers.length;j++){
	                                    	powers += hero.powers[j].name
	                                    	powers += ', ';
	                                    }
	                                    var row = "<tr><td>" + hero.id
	                                            + "</td><td>" + hero.heroName
	                                            + "</td><td>" + hero.mutantName
	                                            + "</td><td>" + hero.originType 
	                                            + "</td><td>" + Math.floor(hero.height/12) + "\' " + hero.height%12 + "\""
	                                            + "</td><td>" + hero.weight + "lb."
	                                            + "</td><td>" + powers
	                                    		+ "</td><td>" +  '<img src="' + hero.costumeImage + '" class="img-thumbnail">' 
	                                            + "</td></tr>";
	                                    $("#result").append(row);
                                }
                        	});
                        });
                        $("#clearSearch").click(function(){
                        	$("#search").val('');
                        	$(".all").attr("checked", true);
                        	listHeroTable();
                        })

    });