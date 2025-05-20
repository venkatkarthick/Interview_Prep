// 1. Undefined is like a placeholder till a variable is not assigned a value. It is a data type.
// 2. Not defined is a reference error. It means that the variable is not declared in the current scope.
// 2. undefined !== not defined
// 3. JS- loosely typed language since it doesn't depend on data type declarations.

//Scopes - Where can I access the variables? 
// Scope is directly dependent on the lexical environment.
// Memory component of the execution context holds the variables, functions and the reference to the parent lexical environment. 
// Thus the whole memory component is called the lexical environment.
// Scope chain is the chain of lexical environments where the occurence of a variable is searched.
function b(){
    var x=10;
    c();
    function c(){
        console.log(x);
    }
}
b();
console.log(x);