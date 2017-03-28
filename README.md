# test spring transaction

How to configure spring transaction with [annotation](01-transactional), with [bean](02-transactional) and an [example with problem](03-transactional).

# run

Go each of folders and execute:

```sh
./gradlew bootRun
```

Sorry for not use test yet, the correct output expected is something like that:

```sh
...
people count: 3
Person{id=1, name='Mary', addresses=[Address{street='1st Street', number=10, addOn=Optional.empty}], phones=[Phone{number='432'}], emails=[]}
Person{id=3, name='Peter', addresses=[Address{street='5th Avenue', number=5, addOn=Optional[house 3]}], phones=[], emails=[]}
Person{id=5, name='Bruce', addresses=[Address{street='1st Avenue', number=30, addOn=Optional.empty}], phones=[Phone{number='1232'}], emails=[Phone{email='bruce@email.com'}]}
Started App in 2.63 seconds (JVM running for 3.142)
...
```
