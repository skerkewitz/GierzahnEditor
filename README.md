# GierzahnEditor
The somewhat clunky editor for [Blubber-Blasen](https://github.com/skerkewitz/blubber-blasen).

## How to use it

### Place and remove solid blocks
Select the Solid layer in the layer tool.
* Left mouse will place solid blocks (you can also drag).
* Right mouse button will remove solid block (you can also drag).

### Setting up airflow
Select the airflow layer in the layer tool.
* Use W, A, S, D key to set airflow at the cursor position.
* Left mouse click will place the cursor.
* The cursor can also moved with the cursor keys.
* Right mouse button will earse the airflow (you can also drag).
* Left mouse button draw will clone the airflow at the cursor position to the mouse position.
* CTRL + SHIFT + A will auto fill the current map with air flow up (will override any current airflow!).

### Enemies
* E will place a enemy at the cursor position.
* F will flip the enemy at the cursor position.
* Q will remoe the enemy at the cursor position.

### Misc
* F5 will mirror copy the left half of the map to the right halt of the map (will overwrite any solids and airflow on the right map half).
* You can load a backdrop as reference image if you want to recreate a map you have a picture of.

## Requirements
* Java JDK11
* Gradle 5.0

## Usage
Clone, build and run the application:
```
git clone https://github.com/skerkewitz/GierzahnEditor.git
cd GierzahnEditor
gradlew run
```

