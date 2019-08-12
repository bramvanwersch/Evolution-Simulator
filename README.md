# EvolutionaryGame
A rough program that aims to simulate evolution of genetic information through the visual simulation of species interacting with a very
basic enviroment. Each species has an individual genome. A pool of genes is pre-determined. This is necessary to be able to have species
evolve new genes. Ideally there would be a different solution for this in the future.
To read the data files and get some useful information out of it I wrote some python scripts that have to stil be intergrated.
There are 3 species available herbivore, carnivore and omnivore. 

## Working on:
- At the moment I am working on making an option interface to easily configure the game instead of changing hard coded values. --> more information.
- add genomes to plants
- make non essential genes
- make more genes
- fix predators being smaller then prey
- make predators more viable
- fix chase mechanic
- fix either energy consumption droppign to zero for a frame or species having zero energy consumption for a frame
- fix species standing still for no reason.
- fix species count going from 0 to 2. There is no species 1
- give predators an ability to eat species bigger then themselves. --> give it a cap.
- Fix graph not displaying the right data. --> lines dont match with innitial starting values.
- Make restart and new button actualy work.
- Add all values that are saved in the option menu into the game.
