$(document).ready(function() {
	var ERRORTEXT = "Hero, Mutant, Origin Type names must be alphanumeric and can contain underscores and" +
	" hypens only and must be between 3 and 13 characters, costume image must hav a valid exetension, and height and weight can" +
	" only be numbers";
	$("#name, #headquarters, .heros1, #heros2, #publicsupportlevel, #nameupdate, #headquartersupdate," +
			" .heros3, #heros4, #publicsupportlevelupdate").focus( function(){
 		$(".errors").empty();
 	});
        $("#createteam").click(function() {
            var team = {};

            team.name = $("#name").val();
            team.headquarters = $("#headquarters").val();
            team.publicSupportLevel = $("#publicsupportlevel").val();
            team.team = [];
            $(".heros1").each(function(){
            	team.team.push({"id": $(this).val()});
            });
            team.teamLeader = {};
            team.teamLeader.id = $("#heros2").val();
            if(validateTeam(team.name, team.headquarters, team.publicSupportLevel)){
            $.ajax({
                url : '/teams',
                method : 'POST',
                data : JSON.stringify(team),
                contentType : 'application/json'
            }).then(function(res) {
            	$(".errors").append(res);
            	listTeamTable();
            	listTeams();
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
       function listHeros(){
    	   $(".heros1, #heros2,.heros3, #heros4").children().remove();
                    $.ajax({
                                url : '/heros',
                                method : 'GET'
                            })
                            .then(
                                    function(heros) {
                                        for (var i = 0; i < heros.length; i++) {
                                            var hero = heros[i];
                                            var row = '<option value="' + hero.id + '">'
                                                    + hero.id
                                                    + ', '
                                                    + hero.mutantName
                                                    + '</option>';
                                            $(".heros1, #heros2, .heros3, #heros4").append(row);
                                        }
                                    });
       }
       listHeros();

                    $("#removeBtn").click(function() {
                        $.ajax({
                            url : '/teams/' + $("#teams2").val(),
                            method : 'DELETE'
                        }).then(function() {
                        	listTeamTable();
                        	listTeams();
                        }, function(errors) {
                        	$(".errors").empty();
                          	$(".errors").append("Error: " + errors);
                        });
                    });
                function listTeamTable(){
                	 $("#result").children().remove();
                    $.ajax({
                        url : '/teams',
                        method : 'GET'
                    }).then(
                            function(teams) {
                                for (var i = 0; i < teams.length; i++) {
                                    var team = teams[i];
                                    var members = ""
                                    for(j=0; j<team.team.length;j++){
                                    	members += team.team[j].mutantName
                                    	members += ', ';
                                    }
                                    var row = "<tr><td>" + team.id
                                            + "</td><td>" + team.name
                                            + "</td><td>" + team.headquarters
                                            + "</td><td>" + team.publicSupportLevel 
                                            + "</td><td>" + team.teamLeader.mutantName
                                            + "</td><td>" + members
                                            + "</td></tr>";
                                    $("#result").append(row);
                                }
                            });
                }
                listTeamTable();
                
                function listTeams(){
                	 $("#teams1, #teams2").children().remove();
                    $.ajax({
                            url: '/teams',
                            method: 'GET'
                        })
                        .then(
                            function(teams) {
                                for (var i = 0; i < teams.length; i++) {
                                    var team = teams[i];
                                    var row = '<option value="' + team.id + '">' + team.name + ', ' + team.headquarters + '</option>';
                                    $("#teams1, #teams2").append(row);
                                }
                            });
                }
                listTeams();
                
                    $("#teams2").change(function(){
                    	$("#teamid").val($("#teams2").val());
                    });
                    $("#updateteam").click(function() {
                        var team = {};
                        team.id = $("#teamid").val();
                        team.name = $("#nameupdate").val();
                        team.headquarters = $("#headquartersupdate").val();
                        team.publicSupportLevel = $("#publicsupportlevelupdate").val();
                        team.team = [];
                        $(".heros3").each(function(){
                        	team.team.push({"id": $(this).val()});
                        });
                        team.teamLeader = {};
                        team.teamLeader.id = $("#heros4").val();
                        if(validateTeam(team.name, team.headquarters, team.publicSupportLevel)){
                        $.ajax({
                            url: '/teams/' + team.id,
                            method: 'PUT',
                            data: JSON.stringify(team),
                            contentType: 'application/JSON'
                        }).then(function() {
                        	listTeamTable();
                        	listTeams();
                        	$(".errors").empty();
                          	$(".errors").append(errors);
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
                    $("#addhero").click(function(){
                   	 var row = '<div class="powers">\
                         <label for="teammembers">Team Members</label>\
                   		 	<select class="heros1">\
                   		 	</select>\
                   		 </div>';
                        $("#addhero").parent().after(row);
                        listHeros();
                   })
                   $("#addheroupdate").click(function(){
                   	 var row = '<div class="powers">\
                         <label for="teammembers">Team Members</label>\
                   		 	<select class="heros3">\
                   		 	</select>\
                   		 </div>';
                        $("#addheroupdate").parent().after(row);
                        listHeros();
                   })
                   function validateTeam(name, headquarters, publicsupportlevel){
                    	var inputPatternName = new RegExp(/^[a-z0-9_ -]{3,30}$/i);
                    	var isNameValid = inputPatternName.test(name);
                    	var isHeadquartersValid = inputPatternName.test(headquarters);
                        var inputPatternSupport = new RegExp("^\s*$");
                        var isPublicSupportValid = (!inputPatternSupport.test(publicsupportlevel) && publicsupportlevel.length<100);
                        return (isHeadquartersValid && isNameValid && isPublicSupportValid)? true: false;
                    };
                    $("#searchSubmit").click(function(){
                        var searchTeam = {};
                        var search = $("#search").val();
                        var searchArr = search.split(' ');
                        searchArr = searchArr.filter(function(str) {
                            return /\S/.test(str);
                        });
                        searchTeam.searchList = searchArr;
                        searchTeam.all = $('.all').prop('checked');
                        searchTeam.type = "team";
                        $.ajax({
                                url: '/search',
                                method: 'POST',
                                data: JSON.stringify(searchTeam),
                                contentType: 'application/JSON'
                        }).then(
		                        function(teams) {
		                        	$("#result").children().remove();
		                            for (var i = 0; i < teams.length; i++) {
		                                var team = teams[i];
		                                var members = ""
		                                for(j=0; j<team.team.length;j++){
		                                	members += team.team[j].mutantName
		                                	members += ', ';
		                                }
		                                var row = "<tr><td>" + team.id
		                                        + "</td><td>" + team.name
		                                        + "</td><td>" + team.headquarters
		                                        + "</td><td>" + team.publicSupportLevel 
		                                        + "</td><td>" + team.teamLeader.mutantName
		                                        + "</td><td>" + members
		                                        + "</td></tr>";
		                                $("#result").append(row);
		                           }
                        	});
                        });
                        $("#clearSearch").click(function(){
                        	$("#search").val('');
                        	$(".all").attr("checked", true);
                        	listTeamTable();
                        })

    });