The idea around the code is to use a chain of responsibility in a minimal way
since the rules that are need to be applied are very simple.
Then i used a enum to describe the rules and it's functions.

The good part of doing this is that to expand is very simple 
and since it's very clear what each rule is doing I think
it also helps while coding to avoid mistakes.

For the database I'm using a simple h2 and the table is being created in the startUp.


### To run:

`./gradlew bootRun`


I'm using java 11.