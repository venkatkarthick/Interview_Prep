function x() {
    console.log("Inside x");
    var a=10;
    function y() {
        console.log(a);
    }
    return y; //While returning y, we are not only returning the function but also the lexical environment of y. (i.e) the closure of y.
}
let z=x();
console.log(z);
z(); //Even after x has executed and removed from the stack, y is still able to access the variable a because of closure.
x()(); //We can also call y like this.
//-----------------

function xx() {
    var b=200;
    return function x() {
        var a=100;
        return function y() {
            console.log(a, b);
        }
    }
}
xx()()(); //y forms a closure with x and xx.
//-----------------



// 1. Closure = Function along with its lexical environment.
// 2. Closure is a function that has access to the parent scope, even after the parent function has closed. 
// 3. In  Js, functions are first class citizens. They can be passed as arguments, returned from other functions and assigned to variables.

//Uses of closures:
//1. Module Design Pattern
//2. Currying
//3. Functions like once, memoize
//4. Maintaining state in async functions
//5. SetTimeout
//6. Data hiding/abstraction/encapsulation
//7. Iterators