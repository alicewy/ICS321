SELECT func, year, SUM(pce) AS annualpce 
FROM pcefunc 
GROUP BY func, year;