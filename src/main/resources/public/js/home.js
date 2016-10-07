 $(document).ready(function() {
        $("#submit").click(function() {

            var employee = {};

            employee.firstname = $("#firstname").val();
            employee.lastname = $("#lastname").val();
            employee.age = $("#age").val();
            employee.active = $("#active").val();
            employee.password = $("#password").val();
            employee.username = $("#username").val();

            $.ajax({
                url : '/employees',
                method : 'POST',
                data : JSON.stringify(employee),
                contentType : 'application/json'
            }).then(function() {
                window.location.href = "/employee/index";
            }, function(errors) {
                $("#errorMsgs").text(errors);
            });

        });
       
         function() {
                    $
                            .ajax({
                                url : '/employees',
                                method : 'GET'
                            })
                            .then(
                                    function(employees) {
                                        for (var i = 0; i < employees.length; i++) {
                                            var employee = employees[i];
                                            var row = '<option value="' + employee.employeeId + '">'
                                                    + employee.lastname
                                                    + ', '
                                                    + employee.firstname
                                                    + '</option>';
                                            $("#employees").append(row);
                                        }
                                    });

                    $("#removeBtn").click(function() {
                        $.ajax({
                            url : '/employees/' + $("#employees").val(),
                            method : 'DELETE'
                        }).then(function() {
                            window.location.href = "/employee/index";
                        }, function(errors) {

                        });
                    });
                };
                
                function() {
                    $.ajax({
                        url : '/employees',
                        method : 'GET'
                    }).then(
                            function(employees) {
                                for (var i = 0; i < employees.length; i++) {
                                    var employee = employees[i];
                                    var row = "<tr><td>" + employee.employeeId
                                            + "</td><td>" + employee.firstname
                                            + "</td><td>" + employee.lastname
                                            + "</td><td>" + employee.username 
                                            + "</td><td>" + employee.age
                                            + "</td><td>" + employee.active
                                            + "</td></tr>";
                                    $("#result").append(row);
                                }
                            });
                }
                
                
                function() {
                    $.ajax({
                            url: '/employees',
                            method: 'GET'
                        })
                        .then(
                            function(employees) {
                                for (var i = 0; i < employees.length; i++) {
                                    var employee = employees[i];
                                    var row = '<option value="' + employee.employeeId + '">' + employee.lastname + ', ' + employee.firstname + '</option>';
                                    $("#employees").append(row);
                                }
                            });

                    $("#employees").change(function() {

                        $.ajax({
                            url: '/employees/' + $("#employees").val(),
                            method: 'GET'
                        }).then(function(employee) {
                            $("#employeeId").val(employee.employeeId);
                            $("#firstname").val(employee.firstname);
                            $("#lastname").val(employee.lastname);
                            $("#age").val(employee.age);
                            $("#active").val(employee.active.toString());
                            $("#username").val(employee.username);
                            $("#password").val(employee.password);

                            //window.location.href = "/employee/index";
                        }, function(errors) {

                        });

                    });

                    $("#submit").click(function() {

                        var employee = {};
                        employee.employeeId = $("#employeeId").val();
                        employee.firstname = $("#firstname").val();
                        employee.lastname = $("#lastname").val();
                        employee.age = $("#age").val();
                        employee.active = $("#active").val();
                        employee.username = $("#username").val();
                        employee.password = $("#password").val();

                        $.ajax({
                            url: '/employees/' + employee.employeeId,
                            method: 'PUT',
                            data: JSON.stringify(employee),
                            contentType: 'application/JSON'
                        }).then(function() {
                            window.location.href = "/employee/index";
                        }, function(errors) {

                        });
                    });
                };
                

    });