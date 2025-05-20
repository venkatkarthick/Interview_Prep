{
    var a = 10;
    let b = 20;
    const c = 30;
    console.log(a); 
    console.log(b);    
    console.log(c); 
}  //This is a block. let and const are block scoped variables.
console.log(a);
// console.log(b); // ReferenceError: b is not defined
// console.log(c); // ReferenceError: c is not defined
//---------------------

console.log("Shadowing in JS");
var a=100;
let b = 200;
const c = 300;
{
    var a = 10;
    let b = 20;
    const c = 30;
    console.log(a); 
    console.log(b);    
    console.log(c); 
}
console.log(a); //Overrides the value of a.
console.log(b); //Shadowing of b
console.log(c); //Shadowing of c. let and const types inside block are stored in "block memory" and outside are stored in "Script" memory.
//---------------------

//Illegal shadowing
console.log("Illegal shadowing in JS");
let d = 10;
var e = 20;
{
    // var d=20; // redeclaring let with var is not allowed inside block scope, will give SyntaxError
    function f(){
        var d=30; // redeclaring let with var is allowed inside function scope
    }
    let e=30; // redeclaring let is allowed, will give SyntaxError

} //blocks also follow lexicographical scoping. (lexical - hierarchy)
//---------------------


// 1. Code inside curly bracket is called block.
// 2. Multiple statements are grouped inside a block so it can be written where JS expects single statements like in if, else, loop, function etc.
// 3. Block values are stored inside separate memory than global. They are stored in block. (Because of this, let and const are called block scope)
// 4. Shadowing is the process of using the same name for a variable in different scopes.
// 5. The shadow should not cross the scope of original variable otherwise it will give error.
// 6. shadowing let with var is illegal shadowing and gives error.
// 7. var value is stored in global scope and hence can be accessed outside block as well whereas same is not the case with let and const.

