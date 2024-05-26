# path-finder

***** 
### extra assumptions made
* for any given latitude or longitude there cant be more than one physial thing. 

*****
## Models
#### location - class representing latittudes and longitudes.
#### restaurant - class representing restaurant , data attributes are restaurant location,name and average preparation time in minutes.
#### consumer - class representing consumers, , data attributes are  consumer location and name.
#### order - class representing delivery order, data attributes are restaurant details,consumer details and orderId.

## relation between models.
* restaurant has a location.
* consumer has a location.
* order has a restaurant,consumer.
*****
## interfaces
### locationService 
* only single method to calculate distance between two locations.
*****
## services
### HaversineLocationService
* Implementation of locationService using haversine formula.

### DeliveryService
* contains business logic of calculating time to reach between two locations.
* contains business logic of finding optimal time and optimal path for a given batch of orders.

******

## algorithm used
* for given set of locations(restaurants + consumers) produce all different possible permutations.
* validate each permutation if its possible in real scenario based on constraints.(validate consumers should be visited only after its respective restaurant).
* calculate total time taken for each valid permutation and decide optimal route, optimal time.

******