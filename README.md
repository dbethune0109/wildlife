## Wildlife Tracker##
October 4, 2016
David Bethune
dbethune1970@gmail.com
dbethune0109@ github


##Setup##
In command windows type : psql wildlife_tracker<wildlife_tracker.sql
then gradle run.
 to run tests: \c wildlife_tracker
 create database wildlife_tracker_test with template wildlife_tracker
 gradle test:

##specs##
Wildlife Tracker
The Forest Service is considering a proposal from a timber company to clearcut a nearby forest of Douglas Fir. Before this proposal may be approved, they must complete an environmental impact study. You have been asked to build an application that allows Rangers to track wildlife sightings in the area.

The application must track two categories of wildlife:

Animals

At the very least, require:

id
name
Endangered Animals

Due to their dwindling numbers, Rangers must record additional information about EndangeredAnimals:

id
name
health
Use constants to define options like "healthy", "ill", and "okay".
age (an estimated guess by the ranger)
Use constants to define options like "newborn", "young", or "adult".
Each time an animal species of either category is seen, a Sighting must be reported:

Sightings

When wildlife is spotted, a Ranger submits a form to record a Sighting containing the following:

id of Animal or EndangeredAnimal species
location
(Conveyed in any manner you choose ie: "Zone A", "Near the River", "NE Quadrant", or latitude and longitude values are all acceptable.)
rangerName



restore databse by using psql    create database wildlife_tracker;
create database wildlife_tracker_test with template wildlife_tracker;
gradle run in command line.


##specs##
you can enter ranger name, animal type, whether it is endangered species and the location of sighting.
ex ranger name:David, animal bald eagle, is endangered, was seen in the sky.



##TECH Used##
.gradle
psql
postgresql
```html
```css
command line



##Open Source##
No license needed open source.




```
```
