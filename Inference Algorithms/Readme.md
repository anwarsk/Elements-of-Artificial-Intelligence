## This program is implementation of different inference algorithms

### 1. Executing the program
We have execute the infer.jar file with parameters \<algorithm_name\> \<sample_size\>

For Example:

```cmd
java -jar infer.jar r 100
2 2
A t
B f
J
E
J 0.058
E 9.999999E-4
```

### 2. Input
This program takes the input as mentioned in IOFormat.pdf on canvas thorough stdin.

For Example:
```cmd
2 2
A t
B f
J
E
```
### 3. Output
This program produces the output as mentioned in IOFormat.pdf on canvas thorough stdout.

For Example:
```cmd
J 0.058
E 9.999999E-4
```

##### Note:
As mentioned in PA2.pdf we have taken average over result from 10 iterations while running the program for sampling.
