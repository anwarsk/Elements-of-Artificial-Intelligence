This is instructions file for the search program.

The program consists of 3 search algorithms-

1. Depth First Search
2. Breadth First Search
3. Iterative Deepening Depth First Search

==================================================
					INPUT
==================================================

1. Input File
----------------
Program takes csv file as a input. Stored in same directory with name 'input.csv'.

Input format is comma separated values city1,city2,<distance_between_cities>

e.g.

Arad,Sibiu,100
Sibiu,Bucharest,23.3

2. Input in the program
------------------------
Once you execute the program it will ask you for Source,Destination,Algorithm

a) Source: Name of the source city
b) Destination: Name of the destination city
c) Algorithm: Name of the search algorithm you want to execute values you have-
			  - 'DFS'
			  - 'BFS'
			  - 'IDDFS'

=======================================================
					OUTPUT
=======================================================

Program will output on console the path from source to destination if exists other-wise mentions there is 
no path between source and destination.

