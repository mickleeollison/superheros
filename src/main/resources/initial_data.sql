insert into powertype (type) values ('movement');
insert into powertype (type) values ('flight');
insert into powertype (type) values ('speed');
insert into powertype (type) values ('strength');
insert into powertype (type) values ('potions');

insert into power (description, name, type_id ) values ('cool power for movement', 'speedy', 1);
insert into power (description, name, type_id ) values ('cool power for flight', 'flying high', 2);
insert into power (description, name, type_id ) values ('cool power for speed', 'lightning fast', 3);
insert into power (description, name, type_id ) values ('cool power for strength', 'mr. universe', 4);
insert into power (description, name, type_id ) values ('cool power for potions', 'snape', 5);

insert into superhero (costumeimage, height, heroname, mutantname, origintype, weight ) values ('url1', 55, 'batman', 'robin', 'ramen',99);
insert into superhero (costumeimage, height, heroname, mutantname, origintype, weight ) values ('url2', 55, 'kingkong', 'mutant', 'raven',99);
insert into superhero (costumeimage, height, heroname, mutantname, origintype, weight) values ('url3', 55, 'lordy', 'singer', 'fan',99);
insert into superhero (costumeimage, height, heroname, mutantname, origintype, weight ) values ('url4', 55, 'yolo', 'drinker', 'tired',99);
insert into superhero (costumeimage, height, heroname, mutantname, origintype, weight ) values ('url5', 55, 'old', 'young', 'different',99);

insert into superhero_power (superhero_id, power_id) values (1,1 );
insert into superhero_power (superhero_id, power_id) values (2,2 );
insert into superhero_power (superhero_id, power_id) values (3,3 );
insert into superhero_power (superhero_id, power_id) values (4,4 );
insert into superhero_power (superhero_id, power_id) values (5,5 );
insert into superhero_power (superhero_id, power_id) values (5,1 );

insert into superheroteam (headquarters, name, publicsupportlevel, teamleader_id) values ('headquarters', 'bestteam', 'goodsupport', 1);
