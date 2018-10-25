-- Ty Vredeveld and Quentin Barnes
-- Lab08

-- 8.1a
select * from Game
order by time desc;

-- 8.1b
select * from Game
where DATE_PART('day', NOW() - time) < 7;

-- 8.1c
select * from Player
where name is not null;

-- 8.1d
select distinct playerID from PlayerGame
where score > 2000;

-- 8.1e
select * from Player
where emailAddress like '%gmail.edu';

-- 8.2a
select score from PlayerGame inner join Player
on PlayerGame.playerID = Player.ID
where name = 'The King';

-- 8.2b
select name from Player inner join PlayerGame
on Player.ID = PlayerGame.playerID inner join Game
on Game.ID = PlayerGame.gameID
and score = (select max(score) from PlayerGame 
             where gameID = (select ID from Game 
                             where time = '2006-06-28 13:20:00'));

-- 8.2c
-- The P1.ID < P2.ID clause in the last example query is how this
-- query is determining that the two players aren't the same person.
-- In this case, it looks to see if one ID is less than the second one.

-- 8.2d
-- A realistic situation that would warrant the use of a self-join
-- could be if a table contains employee information and who each 
-- employee reports to. 