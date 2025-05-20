// Everything in JavaScript happes inside an execution context.
// It has two main components:
// 1.Memory component (Variable Environment) - In memory component variable and functions values can be stored in a  key value format.
// 2.Code component (Thread of execution) - In code component the code is executed line by line.

//Javascript is a synchronous single threaded language. 
// It means that it can only execute one piece of code at a time. But it can handle asynchronous code using the event loop.

var n=2;
function square(num){
    var ans=num*num;
    return ans;
}
var square2=square(n);
var square4=square(4);
console.log(square2);
console.log(square4);

// Whenever a javascript code is executed, a global execution context is created. Javascript scans the code twice.
// 1. Creation phase - In this phase, the code is scanned and memory is allocated for variables and functions. variables are initialized with undefined and functions are stored in memory.
// 2. Execution phase - In this phase, the code is executed line by line. The value of the variables are assigned and the functions are executed.
// Whenever a function is called, a new execution context is created for that function. This is called function execution context and it has its own memory component and code component.

// We have call stack in JS. It maintains the order of execution of Execution contexts. Global Execution context is pushed at start of the program.
//  Whenever a function is called, a new execution context is created and pushed to the call stack. 
// When the function returns, the execution context is popped from the call stack.