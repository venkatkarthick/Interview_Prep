getName()
console.log(x);

var x=7;
function getName(){
    console.log("Namaste JS");
}

// Hoisting is behaviour wherein we access variables and functions before they are declared.


// When a JavaScript program runs, a global execution context is created. This context consists of two phases:
// a) Memory Creation Phase :
// .Variable declarations are scanned and allocated memory, initialized with the value 'undefined'.
// .Function declarations are scanned and stored in memory with their entire code.
// b) Code Execution Phase:
// The code is executed line by line.

// Hoisting is not about physically moving code to the top, but rather about how JavaScript allocates memory for variables and functions during the memory creation phase of the execution context. 
// This mechanism allows for certain behaviors like using functions before they appear in the code, but it's crucial to understand that variables are initialized as 'undefined' until they're assigned a value during code execution. 
// You can call a function before its declaration in the code because the entire function is available in memory from the start. Trying to access a variable before its declaration will result in 'undefined'.

// Function expressions and arrow functions, being treated as variables, hence cannot be called before their declaration in the code. 
// Variable and function declarations are processed before any code is executed. 
// However, only the declarations are processed, not the initializations. 
// This gives the impression that declarations are "moved to the top" of their scope, but it's more accurate to say they're processed early in the execution context's creation.