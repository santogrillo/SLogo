CompSci 308: Cell Society API Discussion
===================

## Simulation

* Internal API
	- updateCell(): Makes every extension of simulation be able to handle different rules
	- primeCell(): Allows the correct type of cell to spawn on the grid depending on the type of simulation
* External API
	- getters and setters: Handle instance variables

## Configuration

* Internal API
	- queryAttributes(): Pulls the values from string to initialize simulation with correct variables
	- changeInitConfig(): Changes the initial configuration of the grid depending to fit the current simulation
* External API
	- variableList(): Sends the list of parameters to the grid

## Visualization

* Internal API
	- updateCellArray(): Overrides different gridtypes
	- updateSeries(): Updates the screen accordingly
* External API
	- getColor(): Sends the color of the cell to render
