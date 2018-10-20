-- Lists the game records in chronological order.
SELECT * 
FROM Game
ORDER BY startTime;

-- The WHERE clause is optional.
SELECT * 
FROM Player;

-- Using name=NULL doesn't work like you think it does.
SELECT *
FROM Player
WHERE name IS NULL;

SELECT *
FROM Player
WHERE name = NULL;

-- Get all the users with Calvin email addresses.
SELECT *
FROM Player
WHERE emailAddress LIKE '%calvin%';

-- Get the highest score ever recorded.
SELECT score
FROM PlayerGame
ORDER BY score DESC
LIMIT 1;

-- Get the cross-product of all the tables.
SELECT *
FROM Player, PlayerGame, Game;

-- Get the name of the player with the highest recorded score.
SELECT Player.name, score
FROM Player, PlayerGame
WHERE Player.ID = PlayerGame.playerID
ORDER BY score DESC
LIMIT 1;

-- Get the names of all the players that played in game #2.
SELECT Player.name, score
FROM Player, PlayerGame, Game
WHERE Player.ID = PlayerGame.playerID
  AND PlayerGame.gameID = Game.ID
  AND Game.ID = 2;

-- Get the names of the players who share the same name.
SELECT P1.name
FROM Player AS P1, Player AS P2
WHERE P1.name = P2.name
  AND P1.ID < P2.ID;